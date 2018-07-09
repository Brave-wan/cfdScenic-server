package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class UserAccountLogDao extends BaseDao<UserAccountLog, Integer>{

	public List<Map<String, String>> selectUserMessage(Long userId) {
		return getSqlSession().selectList(getStatementName("selectUserMessage"),userId);
	}

	public List<Map<String, String>> selectShopMessage(Long userId) {
		return getSqlSession().selectList(getStatementName("selectShopMessage"),userId);
	}

	public List getTradeLog(Map map) {
		return getSqlSession().selectList(getStatementName("getTradeLog"),map);
	}
	public List getShopTradeLog(Map map) {
		return getSqlSession().selectList(getStatementName("getShopTradeLog"),map);
	}
	public void saveUserAccountLog(UserAccountLog userAccountLog) {
		getSqlSession().insert(getStatementName("saveUserAccountLog"),userAccountLog);
	}

	public Page selectShopList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectShopList"), pageRequest);
	}

	public Page selectUserList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectUserList"), pageRequest);
	}

	public Integer insertMessage(UserAccountLog userAccountLog) {
		return getSqlSession().insert(getStatementName("insertMessage"),userAccountLog);
	}
}
