package com.htrj.base.dao;

import com.htrj.base.model.Permission;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PermissionDao  extends BaseDao<Permission, Integer>{

	/**
	 * Title:权限
	 * @author 张腾跃
	 * @date 2016年9月29日13:13:12
	 */

	public Page pageGetpermission(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("pageGetpermissionList"), pageRequest);
	}
	public List selectmenu(Map<String,Object> exMap) {
		return getSqlSession().selectList(getStatementName("selectmenu"),exMap);
	}
}
