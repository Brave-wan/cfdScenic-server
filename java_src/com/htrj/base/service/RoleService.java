package com.htrj.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htrj.base.dao.RoleDao;
import com.htrj.base.model.Role;
import com.htrj.common.base.BaseDao;
import com.htrj.common.base.BaseService;

@Service
@Transactional
public class RoleService extends BaseService{
@Autowired private RoleDao roledao;
	public int insert(Role role) {
		// TODO Auto-generated method stub
		return roledao.insert(role);
	}
	public int update(Role role){
		return roledao.update(role);
	}
	public List findAll(Map map){
		return roledao.findAll(map);
	}
}
