package com.htrj.base.dao;
/**
 * 角色Dao
 * 张腾跃
 * 2016年9月30日10:08:34
 */
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htrj.base.model.PerRole;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class PerRoleDao extends BaseDao<PerRole, Integer>  {
	
	public List<PerRole> findAll(PerRole perRole){
		List<PerRole> PerRoleList = getSqlSession().selectList(getStatementName("findAll"), perRole);
		return PerRoleList;
	}
	
	public Page pagePerRoleByParam(PageRequest<Map<String, Object>> pageRequest){
		return pageQuery(getStatementName("findAll"), pageRequest);
	}
	
	public int insert(PerRole perRole){
		int count = getSqlSession().insert(getStatementName("insert"), perRole);
		return count;
	}
	
	public int update(PerRole perRole){
		int count = getSqlSession().insert(getStatementName("update"), perRole);
		return count;
	}
	
	public int delPerRoleById(int id){
		int count = getSqlSession().delete(getStatementName("del"), id);
		return count;
	}
}
