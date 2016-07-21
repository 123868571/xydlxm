/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.ftp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.ftp.entity.ComFtp;
import com.paopao.hzgzf.common.ftp.dao.ComFtpDao;

/**
 * ftp服务器配置Service
 * @author zdk
 * @version 2016-07-17
 */
@Service
@Transactional(readOnly = true)
public class ComFtpService extends CrudService<ComFtpDao, ComFtp> {

	public ComFtp get(String id) {
		return super.get(id);
	}
	
	public List<ComFtp> findList(ComFtp comFtp) {
		return super.findList(comFtp);
	}
	
	public Page<ComFtp> findPage(Page<ComFtp> page, ComFtp comFtp) {
		return super.findPage(page, comFtp);
	}
	
	@Transactional(readOnly = false)
	public void save(ComFtp comFtp) {
		super.save(comFtp);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComFtp comFtp) {
		super.delete(comFtp);
	}
	
}