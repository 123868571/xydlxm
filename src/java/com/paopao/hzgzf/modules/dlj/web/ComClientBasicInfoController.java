/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.paopao.hzgzf.common.config.Global;
import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.utils.FtpUtil;
import com.paopao.hzgzf.common.web.BaseController;
import com.paopao.hzgzf.modules.dlj.entity.ComAttachUpload;
import com.paopao.hzgzf.modules.dlj.entity.ComClientBasicInfo;
import com.paopao.hzgzf.modules.dlj.entity.ComRecord;
import com.paopao.hzgzf.modules.dlj.service.ComAttachUploadService;
import com.paopao.hzgzf.modules.dlj.service.ComClientBasicInfoService;
import com.paopao.hzgzf.modules.dlj.service.ComRecordService;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;
import com.sun.star.uno.RuntimeException;

/**
 * 客户资料管理Controller
 * @author zdk
 * @version 2016-07-14
 */
@Controller
@RequestMapping(value = "${adminPath}/dlj/comClientBasicInfo")
public class ComClientBasicInfoController extends BaseController {

	@Autowired
	private ComClientBasicInfoService comClientBasicInfoService;
	
	@Autowired
	private ComRecordService comRecordService;
	
	@Autowired
	private ComAttachUploadService comAttachUploadService;
	
	@Autowired
	private FtpUtil ftpUtil;
	
	@ModelAttribute
	public ComClientBasicInfo get(@RequestParam(required=false) String id) {
		ComClientBasicInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comClientBasicInfoService.get(id);
		}
		if (entity == null){
			entity = new ComClientBasicInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("dlj:comClientBasicInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComClientBasicInfo comClientBasicInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComClientBasicInfo> page = comClientBasicInfoService.findPage(new Page<ComClientBasicInfo>(request, response), comClientBasicInfo); 
		model.addAttribute("page", page);
		return "modules/dlj/comClientBasicInfoList";
	}

	@RequiresPermissions("dlj:comClientBasicInfo:view")
	@RequestMapping(value = "form")
	public String form(ComClientBasicInfo comClientBasicInfo, Model model) {
		model.addAttribute("comClientBasicInfo", comClientBasicInfo);
		return "modules/dlj/comClientBasicInfoForm";
	}
	
	@RequestMapping(value = "view")
	public String view(ComClientBasicInfo comClientBasicInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("comClientBasicInfo", comClientBasicInfo);
		ComRecord comRecord = new ComRecord();
		comRecord.setReferId(comClientBasicInfo.getId());
		comRecord.setReferType(ComClientBasicInfo.REFER_TYPE_CLIENT);
		Page<ComRecord> page = comRecordService.findPage(new Page<ComRecord>(request, response), comRecord); 
		model.addAttribute("page", page);
		return "modules/dlj/comClientBasicInfoView";
	}

	@RequiresPermissions("dlj:comClientBasicInfo:edit")
	@RequestMapping(value = "save")
	public String save(ComClientBasicInfo comClientBasicInfo, Model model, 
			HttpServletRequest request,HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comClientBasicInfo)){
			return form(comClientBasicInfo, model);
		}
		ComClientBasicInfo old = comClientBasicInfoService.get(comClientBasicInfo.getId());
		// 处理修改记录
		comClientBasicInfoService.save(comClientBasicInfo, old);
		addMessage(redirectAttributes, "保存客户资料管理成功");
		try {
			// 处理上传文件
			dealWithUploadFiles(comClientBasicInfo, request, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:"+Global.getAdminPath()+"/dlj/comClientBasicInfo/?repage";
	}
	
	private void dealWithUploadFiles(ComClientBasicInfo comClientBasicInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		try {
			if (multipartResolver.isMultipart(request)) {
				// 转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 取得request中的所有文件名
				Iterator<String> iter = multiRequest.getFileNames();
				List<ComAttachUpload> attachs = new ArrayList<ComAttachUpload>();
				ftpUtil.openConnect(); // 开启上传链接
				while (iter.hasNext()) {
					// 记录上传过程起始时的时间，用来计算上传时间
					int pre = (int) System.currentTimeMillis();
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if (file != null && file.getSize() > 0) {
						// 取得当前上传文件的文件名称
						String myFileName = file.getOriginalFilename();
						// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtils.isNotEmpty(myFileName.trim())) {
							logger.info(myFileName);
							// 重命名上传后的文件名
							String fileName = file.getOriginalFilename();
							// 定义上传路径
							ftpUtil.upload(fileName, file.getInputStream());
							// 生成上传记录
							ComAttachUpload attach = new ComAttachUpload();
							attach.setCurrentUser(UserUtils.getUser());
							attach.setFileName(fileName);
							attach.setUploadPath(ftpUtil.getRemotePath());
							attach.setFileSize(String.valueOf(file.getSize()));
							attach.setReferId(comClientBasicInfo.getId());
							attach.setReferType(ComClientBasicInfo.REFER_TYPE_CLIENT);
							attachs.add(attach);
							// 添加修改记录
							dealWithModRecord(comClientBasicInfo, attach);
							//String path = "H:/" + fileName;
//							File localFile = new File(path);
//							file.transferTo(localFile);
						}
					}
					for (ComAttachUpload attach : attachs) {
						comAttachUploadService.save(attach);
					}
					// 记录上传该文件后的时间
					int finaltime = (int) System.currentTimeMillis();
					logger.info("time cost: " + (finaltime - pre));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		} finally {
			if (ftpUtil != null) {
				ftpUtil.closeConnect();
			}
		}
	}

	private void dealWithModRecord(ComClientBasicInfo comClientBasicInfo,
			ComAttachUpload attach) {
		ComRecord comRecord = new ComRecord();
		//comRecord.set
		//comRecordService.save(comRecord);
		
	}

	@RequiresPermissions("dlj:comClientBasicInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ComClientBasicInfo comClientBasicInfo, RedirectAttributes redirectAttributes) {
		comClientBasicInfoService.delete(comClientBasicInfo);
		addMessage(redirectAttributes, "删除客户资料管理成功");
		return "redirect:"+Global.getAdminPath()+"/dlj/comClientBasicInfo/?repage";
	}

}