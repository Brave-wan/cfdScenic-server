package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserOpinion;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class UserOpinionDao extends BaseDao<UserOpinion, Integer>{

	public void insertMessage(UserOpinion userOpinion) {
		getSqlSession().insert(getStatementName("insertMessage"),userOpinion);
	}

	public Page getAll(PageRequest<Map<String, Object>> pageRequest) {
		// TODO Auto-generated method stub
		return pageQuery(getStatementName("getAll"),pageRequest);
	}

	public Map selectById(long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne(getStatementName("selectById"),id);
	}
}