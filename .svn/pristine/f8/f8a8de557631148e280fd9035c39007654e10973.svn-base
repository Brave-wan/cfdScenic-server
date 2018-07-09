package com.htrj.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htrj.base.dao.PerRoleDao;
import com.htrj.base.model.PerRole;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class PerRoleService {

	@Autowired
	private PerRoleDao dao;
	public List<PerRole> findAll(PerRole perRole){
		return dao.findAll(perRole);
	}
	public Page pagePerRoleByParam(PageRequest<Map<String, Object>> pageRequest){
		return dao.pagePerRoleByParam(pageRequest);
	}
	public int insert(PerRole perRole){
		return dao.insert(perRole);
	}
	public int update(PerRole perRole){
		return dao.update(perRole);
	}
	public int delPerRoleById(int id){
		return dao.delPerRoleById(id);
	}
}
