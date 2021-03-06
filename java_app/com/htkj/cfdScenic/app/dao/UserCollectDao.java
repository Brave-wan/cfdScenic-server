package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserCollect;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class UserCollectDao extends BaseDao<UserAccount, Integer>{
	
	
	@SuppressWarnings("rawtypes")
	public Page selectCollectList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectCollectList"), pageRequest);
	}

	public Integer deleteCollect(Map map) {
		return getSqlSession().delete(getStatementName("deleteCollect"),map);
	}
	
	public Integer delCollectByGoodsAndUid(Map map){
		return getSqlSession().delete(getStatementName("delCollectByGoodsAndUid"),map);
	}

	public void insertCollect(UserCollect userCollect) {
		 getSqlSession().insert(getStatementName("insertCollect"),userCollect);
	}
	
	public Integer queryCollect(UserCollect userCollect) {
		return getSqlSession().selectOne(getStatementName("queryCollect"),userCollect);
	}

	public void saveUserCollect(UserCollect userCollect) {
		getSqlSession().insert(getStatementName("saveUserCollect"),userCollect);
	}
}
