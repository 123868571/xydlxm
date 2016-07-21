/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.dlj.entity.ComClientBasicInfo;
import com.paopao.hzgzf.modules.dlj.entity.ComRecord;
import com.paopao.hzgzf.modules.dlj.dao.ComClientBasicInfoDao;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * 客户资料管理Service
 * @author zdk
 * @version 2016-07-14
 */
@Service
@Transactional(readOnly = true)
public class ComClientBasicInfoService extends CrudService<ComClientBasicInfoDao, ComClientBasicInfo> {
	
	@Autowired
	private ComRecordService comRecordService;

	public ComClientBasicInfo get(String id) {
		return super.get(id);
	}
	
	public List<ComClientBasicInfo> findList(ComClientBasicInfo comClientBasicInfo) {
		return super.findList(comClientBasicInfo);
	}
	
	public Page<ComClientBasicInfo> findPage(Page<ComClientBasicInfo> page, ComClientBasicInfo comClientBasicInfo) {
		return super.findPage(page, comClientBasicInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ComClientBasicInfo comClientBasicInfo) {
		super.save(comClientBasicInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ComClientBasicInfo newObj, ComClientBasicInfo old) {
		super.save(newObj);
		if (StringUtils.isNotEmpty(newObj.getId())) {
			// 处理修改日志
			dealWithRecord(newObj, old);
		}
	}
	
	private void dealWithRecord(ComClientBasicInfo newObj, ComClientBasicInfo oldObj) {
		List<ComRecord> records = new ArrayList<ComRecord>();
		if (!StringUtils.equals(newObj.getAddress(), oldObj.getAddress())) {
			records.add(genNewRecord("address", "用电地址", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getAddress(), newObj.getAddress(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getArchiveNo(), oldObj.getArchiveNo())) {
			records.add(genNewRecord("archiveNo", "档案编号", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getArchiveNo(), newObj.getArchiveNo(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getClientName(), oldObj.getClientName())) {
			records.add(genNewRecord("clientName", "客户姓名", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getClientName(), newObj.getClientName(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getCardId(), oldObj.getCardId())) {
			records.add(genNewRecord("cardId", "身份证号码", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getCardId(), newObj.getCardId(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getEstateLicense(), oldObj.getEstateLicense())) {
			records.add(genNewRecord("estateLicense", "房产证号", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getEstateLicense(), newObj.getEstateLicense(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getIssuingAuthority(), oldObj.getIssuingAuthority())) {
			records.add(genNewRecord("issuingAuthority", "发证机关", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getIssuingAuthority(), newObj.getIssuingAuthority(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getOperatorCardId(), oldObj.getOperatorCardId())) {
			records.add(genNewRecord("operatorCardId", "代办人身份证号", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getOperatorCardId(), newObj.getOperatorCardId(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getOperatorName(), oldObj.getOperatorName())) {
			records.add(genNewRecord("operatorName", "代办人姓名", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getOperatorName(), newObj.getOperatorName(), oldObj.getOpId()));
		}
		if (!StringUtils.equals(newObj.getRemark(), oldObj.getRemark())) {
			records.add(genNewRecord("remark", "备注", oldObj.getId(), 
					ComClientBasicInfo.REFER_TYPE_CLIENT,
					oldObj.getRemark(), newObj.getRemark(), oldObj.getOpId()));
		}
		if (records.isEmpty()) {
			return;
		}
		for (ComRecord comRecord : records) {
			comRecord.setIsAttach(ComRecord.NOT_ATTACH);
			comRecord.setModifyType(ComRecord.MOD_NOR);
			comRecord.setOpId(UserUtils.getUser().getId());
			comRecordService.save(comRecord);
		}
		
	}

	private ComRecord genNewRecord(String column, String columnDesc, String referId,
			String referType, String befValue, String aftValue, String opId) {
		ComRecord comRecord = new ComRecord();
		comRecord.setReferId(referId);
		comRecord.setReferType(referType);
		comRecord.setChangeColumn(column);
		comRecord.setChangeColumnDesc(columnDesc);
		comRecord.setBefValue(befValue);
		comRecord.setAftValue(aftValue);
		comRecord.setOpId(opId);
		return comRecord;
	}

	@Transactional(readOnly = false)
	public void delete(ComClientBasicInfo comClientBasicInfo) {
		super.delete(comClientBasicInfo);
	}
	
}