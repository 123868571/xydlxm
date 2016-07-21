package com.paopao.hzgzf.modules.pay.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.modules.pay.dao.GzfAcctItemHisMapper;
import com.paopao.hzgzf.modules.pay.entity.GzfAcctItemHis;

@Service
@Transactional(readOnly=true)
public class GzfAcctItemHisService extends CrudService<GzfAcctItemHisMapper, GzfAcctItemHis>{

	@Transactional(readOnly=false)
	public void saveList(List<GzfAcctItemHis> list){
		if (list == null || list.isEmpty()) {
			return;
		}
		for(int i = 0; i < list.size(); i++){
			save(list.get(i));
		}
	}
}
