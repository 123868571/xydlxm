/**
 * Copyright &copy; 2012-2014  All rights reserved.
 */
package com.paopao.hzgzf.modules.dlj.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paopao.hzgzf.common.persistence.Page;
import com.paopao.hzgzf.common.service.BaseService;
import com.paopao.hzgzf.common.service.CrudService;
import com.paopao.hzgzf.common.utils.CacheUtils;
import com.paopao.hzgzf.modules.dlj.entity.ComStaff;
import com.paopao.hzgzf.modules.dlj.dao.ComStaffDao;
import com.paopao.hzgzf.modules.sys.dao.UserDao;
import com.paopao.hzgzf.modules.sys.entity.Role;
import com.paopao.hzgzf.modules.sys.entity.User;
import com.paopao.hzgzf.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.paopao.hzgzf.modules.sys.utils.UserUtils;

/**
 * 员工Service
 * @author panjs
 * @version 2016-05-26
 */
@Service
@Transactional(readOnly = true)
public class ComStaffService extends CrudService<ComStaffDao, ComStaff> {

	public ComStaff get(String id) {
		return super.get(id);
	}
	
	public List<ComStaff> findList(ComStaff comStaff) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		User user = UserUtils.getUser();
		//comStaff.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		comStaff.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
		return super.findList(comStaff);
	}
	
	public Page<ComStaff> findPage(Page<ComStaff> page, ComStaff comStaff) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		User user = UserUtils.getUser();
		comStaff.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "c", "u"));
//		dataScopeFilter(user, "dsf", "id=a.office_id", "id=a.create_by");
		return super.findPage(page, comStaff);
	}
	
	@Transactional(readOnly = false)
	public void save(ComStaff comStaff) {
		super.save(comStaff);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComStaff comStaff) {
		super.delete(comStaff);
	}
}