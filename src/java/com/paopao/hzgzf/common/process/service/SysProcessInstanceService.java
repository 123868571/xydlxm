/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.common.process.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.process.dao.SysProcessInstanceDao;
import com.paopao.hzgzf.common.process.entity.SysProcessInstance;
import com.paopao.hzgzf.common.process.entity.SysProcessInstanceDetail;
import com.paopao.hzgzf.common.process.entity.SysProcessStepConf;
import com.paopao.hzgzf.common.process.util.ProcConst;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComResidentElecApply;
import com.sun.star.uno.RuntimeException;

/**
 * 流程实例管理Service
 * @author zhoudk
 * @version 2016-06-23
 */
@Service
@Transactional(readOnly = true)
public class SysProcessInstanceService extends CrudService<SysProcessInstanceDao, SysProcessInstance> {
	
	@Autowired
	private SysProcessInstanceDetailService detailService;
	
	@Autowired
	private SysProcessStepConfService configService;

	public SysProcessInstance get(String id) {
		return super.get(id);
	}
	
	public List<SysProcessInstance> findList(SysProcessInstance sysProcessInstance) {
		return super.findList(sysProcessInstance);
	}
	
	public Page<SysProcessInstance> findPage(Page<SysProcessInstance> page, SysProcessInstance sysProcessInstance) {
		return super.findPage(page, sysProcessInstance);
	}
	
	@Transactional(readOnly = false)
	public void save(SysProcessInstance sysProcessInstance) {
		super.save(sysProcessInstance);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysProcessInstance sysProcessInstance) {
		super.delete(sysProcessInstance);
	}
	
	/**   
	* @Function: createProcess
	* @Description: 创建流程实例
	* 通过配置，把流程实例都生成
	*/
	@Transactional(readOnly = false)
	public void createProcess(ComResidentElecApply elecApply, String processType) throws Exception {
		// 先判断当前的applyId和processType对应的流程实例是否存在，且是否已经完成
		boolean isFinish = checkIsFinish(elecApply.getId(), processType);
		if (!isFinish) {
			throw new RuntimeException("当前业务存在未完成的流程！申请序列号【" + elecApply.getSerial() + "】");
		}
		SysProcessStepConf stepConf = new SysProcessStepConf();
		stepConf.setProcessType(processType);
		List<SysProcessStepConf> stepConfList = configService.findList(stepConf);
		//找到配置后，将配置的内容生成流程实例对象
		if (stepConfList == null || stepConfList.isEmpty()) {
			throw new RuntimeException("当前流程类型未定义流程步骤配置！流程类型【" + processType + "】");
		}
		// 生成流程实例主表对象
		SysProcessInstance pi = new SysProcessInstance();
		pi.setApplyId(elecApply.getId());
		pi.setProcessType(processType);
		pi.setStatus(ProcConst.PROCESSING); // 处理中
		pi.setStartTime(new Date());
		this.save(pi);
		// 生成流程实例明细，通过配置数据生成
		for (SysProcessStepConf conf : stepConfList) {
			SysProcessInstanceDetail detail = new SysProcessInstanceDetail();
			detail.setInstanceId(pi.getId());
			detail.setStepId(conf.getStep());
			detail.setStepName(conf.getStepName());
			detail.setRollId(StringUtils.isEmpty(conf.getRollId()) ? "" : conf.getRollId());
			detail.setStatus(ProcConst.DETAIL_PROCESSING); // 这里设置成未处理
			detailService.save(detail);
		}
	}

	private boolean checkIsFinish(String applyId, String processType) {
		SysProcessInstance proInst = new SysProcessInstance();
		proInst.setApplyId(applyId);
		proInst.setProcessType(processType);
		List<SysProcessInstance> instList = this.findList(proInst);
		if (instList == null || instList.isEmpty()) {
			return true;
		}
		boolean isFinish = true;
		for (SysProcessInstance pi : instList) {
			if (ProcConst.PROCESSING == pi.getStatus()) {
				isFinish = false;
				break;
			}
		}
		return isFinish;
	}
	
	public boolean isProcessFinish(SysProcessInstance processInst) {
		List<SysProcessInstanceDetail> details = getProcessDetails(processInst);
		sortProcessDetails(details);
		boolean isFinish = true;
		for (SysProcessInstanceDetail d : details) {
			if (d.getStatus() == ProcConst.DETAIL_PROCESSING) {
				isFinish = false;
				break;
			}
		}
		return isFinish;
	}
	
	/**   
	* @Function: getNextProcess
	* @Description: 获取下一个流程明细
	*/
	public SysProcessInstanceDetail getNextProcessDetail(SysProcessInstance processInst) {
		List<SysProcessInstanceDetail> details = getProcessDetails(processInst);
		// 排序流程实例明细（按照流程步骤的ID排序，暂时没用单独的排序号）
		sortProcessDetails(details);
		for (SysProcessInstanceDetail d : details) {
			if (d.getStatus() != ProcConst.DETAIL_PROCESSING) {
				return d;
			}
		}
		return null;
	}
	
	public static void sortProcessDetails(List<SysProcessInstanceDetail> details) {
		Collections.sort(details, new Comparator<SysProcessInstanceDetail>() {
			public int compare(SysProcessInstanceDetail arg0, SysProcessInstanceDetail arg1) {
				if (NumberUtils.isDigits(arg0.getStepId()) && NumberUtils.isDigits(arg1.getStepId())) {
					return Double.valueOf(arg0.getStepId()).intValue() - Double.valueOf(arg1.getStepId()).intValue();
				}
				return arg0.getStepId().compareTo(arg1.getStepId());
			}
		});
	}
	
	public static void main(String[] args) {
		List<SysProcessInstanceDetail> details = new ArrayList<SysProcessInstanceDetail>();
		for (int i=0; i<10; i++) {
			SysProcessInstanceDetail d = new SysProcessInstanceDetail();
			d.setStepId(String.valueOf((10 - i)));
			details.add(d);
		}
		sortProcessDetails(details);
		for (SysProcessInstanceDetail d : details) {
			System.out.println(d.getStepId());
		}
	}

	/**   
	* @Function: getNextProcess
	* @Description: 获取前一个流程明细
	*/
	public SysProcessInstanceDetail getPreProcessDetail(SysProcessInstance processInst) {
		return null;
	}
	
	/**   
	* @Function: getCurProcess
	* @Description: 获取当前流程明细
	*/
	public SysProcessInstanceDetail getCurProcessDetail(SysProcessInstance processInst) {
		return null;
	}
	
	/**   
	* @Function: endProcess
	* @Description: 结束流程
	*
	*/
	public void endProcess(SysProcessInstance processInst) {
		
	}
	
	/**   
	* @Function: getProcessDetails
	* @Description: 获取流程实例的所有流程实例步骤
	* 2016-7-11     zdk           v1.0.0               修改原因
	*/
	public List<SysProcessInstanceDetail> getProcessDetails(SysProcessInstance processInst) {
		SysProcessInstanceDetail detail = new SysProcessInstanceDetail();
		detail.setInstanceId(processInst.getId());
		return detailService.findList(detail);
	}
	
}