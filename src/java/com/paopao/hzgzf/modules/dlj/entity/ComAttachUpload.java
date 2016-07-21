/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.entity;

import org.hibernate.validator.constraints.Length;

import com.paopao.hzgzf.common.persistence.DataEntity;

/**
 * 通用附件上传Entity
 * @author zdk
 * @version 2016-07-14
 */
public class ComAttachUpload extends DataEntity<ComAttachUpload> {
	
	private static final long serialVersionUID = 1L;
	private String referId;		// 关联ID
	private String referType;		// 业务类型
	private String uploadPath;		// 上传路径
	private String fileName;		// 文件名
	private String fileSize;		// 文件大小
	private String remark;		// remark
	private String opId;		// op_id
	
	public ComAttachUpload() {
		super();
	}

	public ComAttachUpload(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关联ID长度必须介于 0 和 64 之间")
	public String getReferId() {
		return referId;
	}

	public void setReferId(String referId) {
		this.referId = referId;
	}
	
	@Length(min=0, max=50, message="业务类型长度必须介于 0 和 50 之间")
	public String getReferType() {
		return referType;
	}

	public void setReferType(String referType) {
		this.referType = referType;
	}
	
	@Length(min=0, max=500, message="上传路径长度必须介于 0 和 500 之间")
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	@Length(min=0, max=200, message="文件名长度必须介于 0 和 200 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	
	@Length(min=0, max=255, message="remark长度必须介于 0 和 255 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=11, message="op_id长度必须介于 0 和 11 之间")
	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}
	
}