/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.ftp.entity.ComFtpConf;
import com.paopao.hzgzf.common.ftp.dao.ComFtpConfDao;

/**
 * ftp服务器路径配置Service
 * @author zdk
 * @version 2016-07-17
 */
@Service
@Transactional(readOnly = true)
public class ComFtpConfService extends CrudService<ComFtpConfDao, ComFtpConf> {

	public ComFtpConf get(String id) {
		return super.get(id);
	}
	
	public List<ComFtpConf> findList(ComFtpConf comFtpConf) {
		return super.findList(comFtpConf);
	}
	
	public Page<ComFtpConf> findPage(Page<ComFtpConf> page, ComFtpConf comFtpConf) {
		return super.findPage(page, comFtpConf);
	}
	
	@Transactional(readOnly = false)
	public void save(ComFtpConf comFtpConf) {
		super.save(comFtpConf);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComFtpConf comFtpConf) {
		super.delete(comFtpConf);
	}
	
}