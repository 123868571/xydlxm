package com.paopao.hzgzf.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paopao.hzgzf.common.ftp.entity.ComFtp;
import com.paopao.hzgzf.common.ftp.entity.ComFtpConf;
import com.paopao.hzgzf.common.ftp.service.ComFtpConfService;
import com.paopao.hzgzf.common.ftp.service.ComFtpService;

@Component
public class FtpUtil {
	
	private static Logger logger = Logger.getLogger(FtpConsts.class);
	
	private String currentPath = "";
	private double response = 0;
	private final String REMOTE_PATH = "\\";
	public static final int BIN = 0;
	public static final int ASC = 1;
	private static final int FTP_PORT = 21;
	
	private String remotePath = "";
	
	private FTPClient ftpClient = new FTPClient();
	
	@Autowired
	private ComFtpService comFtpService;
	
	@Autowired
	private ComFtpConfService comFtpConfService;

	public void openConnect(String cfgFtpCode) {
		ftpClient.setControlEncoding("GBK");
	}

	public void openConnect() throws IOException {
		ftpClient.setControlEncoding("GBK");
		try {
			openConnectByCode(FtpConsts.DEFAULT_FTP_CODE, FtpConsts.SUB_CLASS_CLIENT_INFO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * 打开一个可配置的ftp连接(通过标识cfg_ftp_code进行连接)
	 * 
	 * @param ftpCode ftp编码
	 * @param subClass 子分类
	 */
	public void openConnectByCode(String ftpCode, String subClass) throws Exception {
		ftpClient.setControlEncoding("GBK");
		int reply;
		ComFtp comFtp = new ComFtp();
		comFtp.setFtpCode(ftpCode);
		List<ComFtp> ftps = comFtpService.findList(comFtp);
		if (ftps == null || ftps.isEmpty()) {
			throw new RuntimeException("未找到对应的ftp配置，ftpCode：" + ftpCode);
		}
		ComFtp ftp = ftps.get(0);
		ftpClient.connect(ftp.getHostIp(), FTP_PORT);
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			throw new Exception("connect fail: " + reply);
		}
		ftpClient.login(ftp.getUser(), ftp.getPassword());
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			throw new Exception("connect fail: " + reply);
		} else {
			FTPClientConfig config = new FTPClientConfig();
			config.setServerLanguageCode("zh");
			ftpClient.configure(config);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		}
		ComFtpConf ftpConf = new ComFtpConf();
		ftpConf.setFtpCode(ftpCode);
		ftpConf.setSubClass(subClass);
		List<ComFtpConf> resultConfs = comFtpConfService.findList(ftpConf);
		if (resultConfs == null || resultConfs.isEmpty()) {
			throw new RuntimeException("未找到ftp路径配置，ftpCode:" + ftpCode + ", subClass:" + subClass);
		}
		ComFtpConf resultConf = resultConfs.get(0);
		ftpClient.changeWorkingDirectory(resultConf.getRemotePath());
		remotePath = resultConf.getRemotePath();
	}

	/**
	 * close FTP connection
	 * 
	 * @throws IOException
	 */
	public void closeConnect() {
		try {
			if (ftpClient != null) {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Result download(String remotePath, String fileName, String localPath)
			throws IOException {
		boolean flag = true;
		Result result = null;
		currentPath = remotePath;
		response = 0;
		ftpClient.changeWorkingDirectory(remotePath);
		FTPFile[] ftpFiles = ftpClient.listFiles();
		for (FTPFile ftpFile : ftpFiles) {
			if (ftpFile.getName().equals(fileName)) {
				File file = new File(localPath + "/" + fileName);
				Date startTime = new Date();
				if (ftpFile.isDirectory()) {
					flag = downloadMany(file);
				} else {
					flag = downloadSingle(file, ftpFile);
				}
				Date endTime = new Date();
				result = new Result(flag, Util.getFormatTime(endTime.getTime()
						- startTime.getTime()), Util.getFormatSize(response));
			}
		}
		return result;
	}

	private boolean downloadSingle(File localFile, FTPFile ftpFile)
			throws IOException {
		boolean flag = true;
		OutputStream outputStream = new FileOutputStream(localFile);
		response += ftpFile.getSize();
		flag = ftpClient.retrieveFile(localFile.getName(), outputStream);
		outputStream.close();
		return flag;
	}

	public boolean createDirectories(String dirs) throws IOException {
		if (dirs.startsWith("/")) {
			dirs = dirs.substring(1);
		}
		int end = dirs.indexOf("/");
		if (end == -1) {
			return true;
		}
		String dir = dirs.substring(0, end);
		dirs = dirs.substring(end + 1);
		if (!"".equals(dir)) {
			if (!ftpClient.changeWorkingDirectory(dir)) {
				if (ftpClient.makeDirectory(dir)) {
					ftpClient.changeWorkingDirectory(dir);
					createDirectories(dirs);
				} else {
					return false;
				}
			} else {
				createDirectories(dirs);
			}
		}
		return true;
	}

	private boolean downloadMany(File localFile) throws IOException {
		boolean flag = true;
		if (!currentPath.equals(REMOTE_PATH)) {
			currentPath = currentPath + REMOTE_PATH + localFile.getName();
		} else {
			currentPath = currentPath + localFile.getName();
		}
		localFile.mkdir();
		ftpClient.changeWorkingDirectory(currentPath);
		FTPFile[] ftpFiles = ftpClient.listFiles();
		for (FTPFile ftpFile : ftpFiles) {
			File file = new File(localFile.getPath() + "/" + ftpFile.getName());
			if (ftpFile.isDirectory()) {
				flag = downloadMany(file);
			} else {
				flag = downloadSingle(file, ftpFile);
			}
		}
		return flag;
	}

	public Result uploading(File localFile, String remotePath)
			throws IOException {
		boolean flag = true;
		Result result = null;
		currentPath = remotePath;
		response = 0;
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		Date startTime = new Date();
		if (localFile.isDirectory()) {
			flag = uploadingMany(localFile);
		} else {
			flag = uploadingSingle(localFile);
		}
		Date endTime = new Date();
		result = new Result(flag, Util.getFormatTime(endTime.getTime()
				- startTime.getTime()), Util.getFormatSize(response));
		return result;
	}

	public Result uploading(InputStream inputStream, String remoteFile)
			throws IOException {
		boolean flag = true;
		Result result = null;
		int index = remoteFile.lastIndexOf("\\");
		currentPath = remoteFile.substring(0, index + 1);
		response = 0;
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);
		createDirectories(remoteFile);
		Date startTime = new Date();
		flag = uploadingSingle(inputStream, remoteFile.substring(index + 1));
		Date endTime = new Date();
		result = new Result(flag, Util.getFormatTime(endTime.getTime()
				- startTime.getTime()), Util.getFormatSize(response));
		return result;
	}

	private boolean uploadingSingle(File localFile) throws IOException {
		boolean flag = true;
		InputStream inputStream = new FileInputStream(localFile);
		response += (double) inputStream.available() / 1;
		flag = ftpClient.storeFile(currentPath + localFile.getName(),
				inputStream);
		inputStream.close();
		return flag;
	}

	private boolean uploadingSingle(InputStream inputStream, String fileName)
			throws IOException {
		boolean flag = true;
		response += (double) inputStream.available() / 1;
		flag = ftpClient.storeFile(
				fileName.substring(fileName.lastIndexOf("/") + 1), inputStream);
		inputStream.close();
		return flag;
	}

	private boolean uploadingMany(File localFile) throws IOException {
		boolean flag = true;
		if (!currentPath.equals(REMOTE_PATH)) {
			currentPath = currentPath + REMOTE_PATH + localFile.getName();
		} else {
			currentPath = currentPath + localFile.getName();
		}
		ftpClient.makeDirectory(currentPath);
		ftpClient.changeWorkingDirectory(currentPath);
		File[] files = localFile.listFiles();
		for (File file : files) {
			if (file.isHidden()) {
				continue;
			}
			if (file.isDirectory()) {
				flag = uploadingMany(file);
			} else {
				flag = uploadingSingle(file);
			}
		}
		return flag;
	}

	public static class Result {
		private String response;

		private boolean succeed;

		private String time;

		public Result() {
		}

		public Result(String res) {
			this.response = res;
		}

		public Result(boolean suc, String ti, String res) {
			this.succeed = suc;
			this.time = ti;
			this.response = res;
		}

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

		public boolean isSucceed() {
			return succeed;
		}

		public void setSucceed(boolean succeed) {
			this.succeed = succeed;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

	}

	public final static class Util {
		private static final int STOREUNIT = 1024;

		private static final int TIMEMSUNIT = 1000;
		private static final int TIMEUNIT = 60;

		private Util() {
		}

		public static String getFormatSize(double size) {
			double kiloByte = size / STOREUNIT;
			if (kiloByte < 1) {
				return size + " Byte";
			}

			double megaByte = kiloByte / STOREUNIT;
			if (megaByte < 1) {
				BigDecimal result = new BigDecimal(Double.toString(kiloByte));
				return result.setScale(2, BigDecimal.ROUND_HALF_UP)
						.toPlainString() + " KB";
			}

			double gigaByte = megaByte / STOREUNIT;
			if (gigaByte < 1) {
				BigDecimal result = new BigDecimal(Double.toString(megaByte));
				return result.setScale(2, BigDecimal.ROUND_HALF_UP)
						.toPlainString() + " MB";
			}

			double teraBytes = gigaByte / STOREUNIT;
			if (teraBytes < 1) {
				BigDecimal result = new BigDecimal(Double.toString(gigaByte));
				return result.setScale(2, BigDecimal.ROUND_HALF_UP)
						.toPlainString() + " GB";
			}
			BigDecimal result = new BigDecimal(teraBytes);
			return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
					+ " TB";
		}

		public static String getFormatTime(long time) {
			double second = (double) time / TIMEMSUNIT;
			if (second < 1) {
				return time + " MS";
			}

			double minute = second / TIMEUNIT;
			if (minute < 1) {
				BigDecimal result = new BigDecimal(Double.toString(second));
				return result.setScale(2, BigDecimal.ROUND_HALF_UP)
						.toPlainString() + " SEC";
			}

			double hour = minute / TIMEUNIT;
			if (hour < 1) {
				BigDecimal result = new BigDecimal(Double.toString(minute));
				return result.setScale(2, BigDecimal.ROUND_HALF_UP)
						.toPlainString() + " MIN";
			}

			BigDecimal result = new BigDecimal(Double.toString(hour));
			return result.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
					+ " H";
		}

		public static String convertString(String source, String encoding) {
			try {
				byte[] data = source.getBytes("ISO8859-1");
				return new String(data, encoding);
			} catch (UnsupportedEncodingException ex) {
				return source;
			}
		}
	}

	public boolean download(String filepath, String fileName,
			HttpServletResponse response) throws Exception {
		boolean flag = false;

		if (fileName == null || "".equals(fileName)) {
			int index = filepath.lastIndexOf("\\");
			if (index == -1) {
				index = filepath.lastIndexOf("/");
			}
			fileName = filepath.substring(index + 1);
			filepath = filepath.substring(0, index + 1);
		}
		try {
			String path1 = null;
			if (filepath.startsWith("/")) {
				path1 = filepath.substring(filepath.indexOf("/") + 1);
			}
			FTPFile[] names = ftpClient.listFiles(path1 + fileName);
			if (names.length == 0) {
				return false;
			}
			ftpClient.setControlEncoding("GBK");
			if (fileName.indexOf("(") != -1) {
				response.setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(
										fileName.substring(0,
												fileName.lastIndexOf("("))
												+ fileName.substring(fileName
														.lastIndexOf(")") + 1),
										"UTF-8"));
			} else {
				response.setHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(fileName, "UTF-8"));
			}
			OutputStream outputStream = response.getOutputStream();
			flag = ftpClient.retrieveFile(
					filepath.substring(filepath.indexOf("/") + 1) + fileName,
					outputStream);
			outputStream.flush();
			outputStream.close();
			flag = true;
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return flag;
	}

	public boolean rangeDownload(String filepath, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		boolean flag = false;

		if (fileName == null || "".equals(fileName)) {
			int index = filepath.lastIndexOf("\\");
			if (index == -1) {
				index = filepath.lastIndexOf("/");
			}
			fileName = filepath.substring(index + 1);
			filepath = filepath.substring(0, index + 1);
		}
		try {
			String path1 = null;
			if (filepath.startsWith("/")) {
				path1 = filepath.substring(filepath.indexOf("/") + 1);
			}
			FTPFile[] names = ftpClient.listFiles(path1 + fileName);
			if (names.length == 0) {
				return false;
			}
			ftpClient.setControlEncoding("GBK");
			OutputStream outputStream = response.getOutputStream();
			InputStream is = ftpClient.retrieveFileStream(filepath
					.substring(filepath.indexOf("/") + 1) + fileName);
			BufferedInputStream bis = new BufferedInputStream(is);
			int fileLength = is.available();

			long p = 0L;
			long toLength = 0L;
			long contentLength = 0L;
			int rangeSwitch = 0; // 0,从头开始的全文下载；1,从某字节开始的下载（bytes=27000-）；2,从某字节开始到某字节结束的下载（bytes=27000-39000）
			String rangBytes = "";
			response.reset();
			response.setHeader("Accept-Ranges", "bytes");
			String range = request.getHeader("Range");
			if (range != null && range.trim().length() > 0
					&& !"null".equals(range)) {
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				rangBytes = range.replaceAll("bytes=", "");
				if (rangBytes.endsWith("-")) { // bytes=270000-
					rangeSwitch = 1;
					p = Long.parseLong(rangBytes.substring(0,
							rangBytes.indexOf("-")));
					contentLength = fileLength - p; // 客户端请求的是270000之后的字节（包括bytes下标索引为270000的字节）
				} else { // bytes=270000-320000
					rangeSwitch = 2;
					String temp1 = rangBytes.substring(0,
							rangBytes.indexOf("-"));
					String temp2 = rangBytes.substring(
							rangBytes.indexOf("-") + 1, rangBytes.length());
					p = Long.parseLong(temp1);
					toLength = Long.parseLong(temp2);
					contentLength = toLength - p + 1; // 客户端请求的是 270000-320000
														// 之间的字节
				}
			} else {
				contentLength = fileLength;
			}

			// 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。
			// Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
			response.setHeader("Content-Length",
					new Long(contentLength).toString());

			// 断点开始
			// 响应的格式是:
			// Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
			if (rangeSwitch == 1) {
				String contentRange = new StringBuffer("bytes ")
						.append(new Long(p).toString()).append("-")
						.append(new Long(fileLength - 1).toString())
						.append("/").append(new Long(fileLength).toString())
						.toString();
				response.setHeader("Content-Range", contentRange);
				bis.skip(p);
			} else if (rangeSwitch == 2) {
				String contentRange = range.replace("=", " ") + "/"
						+ new Long(fileLength).toString();
				response.setHeader("Content-Range", contentRange);
				bis.skip(p);
			} else {
				String contentRange = new StringBuffer("bytes ").append("0-")
						.append(fileLength - 1).append("/").append(fileLength)
						.toString();
				response.setHeader("Content-Range", contentRange);
			}

			response.setContentType("application/octet-stream");
			if (fileName.indexOf("(") != -1) {
				response.addHeader(
						"Content-disposition",
						"attachment;filename="
								+ URLEncoder.encode(
										fileName.substring(0,
												fileName.lastIndexOf("("))
												+ fileName.substring(fileName
														.lastIndexOf(")") + 1),
										"UTF-8"));
			} else {
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ URLEncoder.encode(fileName, "UTF-8"));
			}

			OutputStream out = response.getOutputStream();
			int n = 0;
			long readLength = 0;
			int bsize = 1024;
			byte[] bytes = new byte[bsize];
			if (rangeSwitch == 2) {
				// 针对 bytes=27000-39000 的请求，从27000开始写数据
				while (readLength <= contentLength - bsize) {
					n = bis.read(bytes);
					readLength += n;
					out.write(bytes, 0, n);
				}
				if (readLength <= contentLength) {
					n = bis.read(bytes, 0, (int) (contentLength - readLength));
					out.write(bytes, 0, n);
				}
			} else {
				while ((n = bis.read(bytes)) != -1) {
					out.write(bytes, 0, n);
				}
			}
			out.flush();
			out.close();
			bis.close();

			outputStream.flush();
			outputStream.close();
			flag = true;
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 供boss使用
	 * 
	 * @param filepath
	 * @param fileName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean download1(String filepath, String fileName,
			HttpServletResponse response) throws Exception {
		boolean success = false;
		try {
			ftpClient.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(
					FTPClientConfig.SYST_UNIX);
			conf.setServerLanguageCode("zh");
			ftpClient.changeWorkingDirectory(filepath.substring(filepath
					.indexOf("/") + 1));
			FTPFile[] fs = ftpClient.listFiles();
			for (int i = 0; i < fs.length; i++) {
				FTPFile ff = fs[i];
				if (ff == null)
					continue;
				if (ff.getName().indexOf("(") != -1
						&& ff.getName().indexOf(")") != -1) {
					String temp1 = ff.getName().substring(
							ff.getName().lastIndexOf("("));
					if (temp1.equals(fileName)) {
						response.setHeader(
								"Content-disposition",
								"attachment;filename="
										+ URLEncoder
												.encode(ff.getName().substring(
														0,
														ff.getName()
																.lastIndexOf(
																		"("))
														+ ff.getName()
																.substring(
																		ff.getName()
																				.lastIndexOf(
																						")") + 1),
														"UTF-8"));
						OutputStream outputStream = response.getOutputStream();
						ftpClient.retrieveFile(ff.getName(), outputStream);
						outputStream.flush();
						outputStream.close();
						success = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 供boss使用 add by gengyl
	 * 
	 * @param filepath
	 * @param fileName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean downloadbboss(String filepath, String fileName,
			HttpServletResponse response) throws Exception {
		boolean success = false;
		try {
			ftpClient.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(
					FTPClientConfig.SYST_UNIX);
			conf.setServerLanguageCode("zh");
			ftpClient.changeWorkingDirectory(filepath.substring(filepath
					.indexOf("/") + 1));
			FTPFile[] fs = ftpClient.listFiles();
			for (int i = 0; i < fs.length; i++) {
				FTPFile ff = fs[i];
				if (ff == null)
					continue;
				String temp1 = ff.getName();
				if (temp1.equals(fileName)) {
					response.setHeader(
							"Content-disposition",
							"attachment;filename="
									+ URLEncoder.encode(ff.getName(), "UTF-8"));
					OutputStream outputStream = response.getOutputStream();
					ftpClient.retrieveFile(ff.getName(), outputStream);
					outputStream.flush();
					outputStream.close();
					success = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 检查ftp文件是否存在 add by myxuee
	 * 
	 * @param filepath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public boolean checkFileExist(String filepath, String fileName)
			throws Exception {
		boolean isExist = false;
		try {
			ftpClient.setControlEncoding("GBK");
			FTPClientConfig conf = new FTPClientConfig(
					FTPClientConfig.SYST_UNIX);
			conf.setServerLanguageCode("zh");
			ftpClient.changeWorkingDirectory(filepath.substring(filepath
					.indexOf("/") + 1));
			FTPFile[] fs = ftpClient.listFiles();
			for (int i = 0; i < fs.length; i++) {
				FTPFile ff = fs[i];
				if (ff == null)
					continue;
				String temp1 = ff.getName();
				if (temp1.equals(fileName)) {
					isExist = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return isExist;
	}

	public boolean deleteFile(String fileName, String filePath)
			throws Exception {
		boolean result = false;
		try {
			if (filePath.startsWith("/")) {
				filePath = filePath.substring(filePath.indexOf("/") + 1);
			}
			ftpClient.changeWorkingDirectory(filePath);
			ftpClient.deleteFile(fileName);
			return result;
		} catch (Exception e) {
			return result;
		} finally {

			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

		}
	}

	/**
	 * 
	 * @param remoteFileName
	 *            String
	 * @param input
	 *            InputStream
	 * @param mode
	 *            int
	 * @throws Exception
	 */
	public void upload(String remoteFileName, InputStream input, int mode)
			throws Exception {
		if (mode == BIN) {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} else if (mode == ASC) {
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
		} else {
			throw new Exception("不支持的传输模式:" + mode);
		}
		upload(remoteFileName, input);
	}

	/**
	 * 
	 * @param remoteFileName
	 *            String
	 * @param input
	 *            InputStream
	 * @throws Exception
	 */
	public void upload(String remoteFileName, InputStream input)
			throws Exception {
		ftpClient.storeFile(remoteFileName, input);
	}

	/**
	 * 供ESOP以流形式读取文件
	 * 
	 * @param remoteFileName
	 *            String
	 * @throws Exception
	 * @return InputStream
	 */
	public InputStream readRemote(String RemotePath, String remoteFileName)
			throws Exception {
		ftpClient.changeWorkingDirectory(RemotePath);
		return ftpClient.retrieveFileStream(remoteFileName);
	}

	/**
	   * 
	   */
	/**
	 * 供ESOP展示图片用
	 * 
	 * @param remoteFileName
	 *            String
	 * @param localFileName
	 *            String
	 * @param mode
	 *            int
	 * @throws Exception
	 */
	public void download(String remoteFileName, OutputStream output, int mode)
			throws Exception {
		if (mode == BIN) {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} else if (mode == ASC) {
			ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
		}

		else {
			throw new Exception("不支持的传输模式:" + mode);
		}
		boolean rtn = ftpClient.retrieveFile(remoteFileName, output);
		if (rtn == false) {
			throw new Exception("下载远程文件:" + remoteFileName + ",不成功");
		}
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}
	
}
