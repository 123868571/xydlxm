/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComAttachUpload;
import com.paopao.hzgzf.modules.dlj.dao.ComAttachUploadDao;

/**
 * 通用附件上传Service
 * @author zdk
 * @version 2016-07-14
 */
@Service
@Transactional(readOnly = true)
public class ComAttachUploadService extends CrudService<ComAttachUploadDao, ComAttachUpload> {

	public ComAttachUpload get(String id) {
		return super.get(id);
	}
	
	public List<ComAttachUpload> findList(ComAttachUpload comAttachUpload) {
		return super.findList(comAttachUpload);
	}
	
	public Page<ComAttachUpload> findPage(Page<ComAttachUpload> page, ComAttachUpload comAttachUpload) {
		return super.findPage(page, comAttachUpload);
	}
	
	@Transactional(readOnly = false)
	public void save(ComAttachUpload comAttachUpload) {
		super.save(comAttachUpload);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComAttachUpload comAttachUpload) {
		super.delete(comAttachUpload);
	}
	
}