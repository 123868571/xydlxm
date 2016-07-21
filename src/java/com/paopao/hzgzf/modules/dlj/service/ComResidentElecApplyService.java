/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.process.service.SysProcessInstanceService;
import com.paopao.hzgzf.common.process.util.ProcConst;
import com.paopao.hzgzf.common.process.util.ProcConst.PROCESS_TYPE;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.dao.ComResidentElecApplyDao;
import com.paopao.hzgzf.modules.dlj.entity.ComResidentElecApply;

/**
 * 城乡居民生活用电需求表Service
 * @author zhoudk
 * @version 2016-06-23
 */
@Service
@Transactional(readOnly = true)
public class ComResidentElecApplyService extends CrudService<ComResidentElecApplyDao, ComResidentElecApply> {
	
	public static final Logger logger = LoggerFactory.getLogger(ComResidentElecApplyService.class);
	
	@Autowired
	private SysProcessInstanceService instanceService;

	public ComResidentElecApply get(String id) {
		return super.get(id);
	}
	
	public List<ComResidentElecApply> findList(ComResidentElecApply comResidentElecApply) {
		return super.findList(comResidentElecApply);
	}
	
	public Page<ComResidentElecApply> findPage(Page<ComResidentElecApply> page, ComResidentElecApply comResidentElecApply) {
		return super.findPage(page, comResidentElecApply);
	}
	
	@Transactional(readOnly = false)
	public void save(ComResidentElecApply comResidentElecApply) {
		super.save(comResidentElecApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComResidentElecApply comResidentElecApply) {
		super.delete(comResidentElecApply);
	}
	
	/**   
	* @Function: saveApplyToSubmit
	* @Description: 提交申请
	*/
	@Transactional(readOnly = false)
	public void saveApplyToSubmit(ComResidentElecApply comResidentElec) throws Exception {
		comResidentElec.setCheckStatus(ProcConst.PROCESSING);
		save(comResidentElec);
		instanceService.createProcess(comResidentElec, PROCESS_TYPE.RESIDENT_ELEC_APPLY);
	}
	
	/**
     * 
     * 审核. <br/>
     * 
     * @author yuliqian
     * @param id
     * @param bit
     * @param verify
     * @param checkContent
     * @since JDK 1.6
     */
    @Transactional(readOnly = false)
    public void check(String id, int bit, int verify, String checkContent) {
    }
	
}