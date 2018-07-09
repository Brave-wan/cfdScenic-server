package com.htrj.base.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.Advertisement;
import com.htrj.base.model.Role;
import com.htrj.common.base.BaseDao;

@Repository
public class RoleDao extends BaseDao{

	public int insert(Role role){
		return getSqlSession().insert(getStatementName("insert"),role);
	}
	public int update(Role role) {
	    return  getSqlSession().update(getStatementName("update"),role);
	}
	public List findAll(Map map){
		return getSqlSession().selectList(getStatementName("findAll"),map);
	}
}
