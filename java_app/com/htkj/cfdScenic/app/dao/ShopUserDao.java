package com.htkj.cfdScenic.app.dao;


import com.htkj.cfdScenic.app.model.ShopUser;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ShopUserDao extends BaseDao<ShopUser, Integer>{

	public Integer selectById(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectById"),userId);
	}

	public Long getShopUserIdByToken(String token) {
		return getSqlSession().selectOne(getStatementName("getShopUserIdByToken"),token);
	}
	
	public ShopUser selectByPhone(String telPhone) {
		return getSqlSession().selectOne(getStatementName("selectByPhone"),telPhone);
	}
	
	public ShopUser selectByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectByUserId"),userId);
	}

	public void updateUUID(ShopUser userMessage) {
		getSqlSession().update(getStatementName("updateUUID"),userMessage);
	}

	public void updatePassWord(ShopUser userMessage) {
		getSqlSession().update(getStatementName("updatePassWord"),userMessage);
	}
	
	public void updateInformationId(ShopUser userMessage) {
		getSqlSession().update(getStatementName("updateInformationId"),userMessage);
	}

	public void insertMessage(ShopUser user) {
		getSqlSession().insert(getStatementName("insertMessage"),user);
	}

	public Map<String, Object> shopUserMessage(Long id) {
		return getSqlSession().selectOne(getStatementName("shopUserMessage"),id);
	}

	public Map<String, Object> shopAutonymMessage(Long id) {
		return getSqlSession().selectOne(getStatementName("shopAutonymMessage"),id);
	}


    public ShopUser selectByTelPhone(String telphone) {
        return getSqlSession().selectOne(getStatementName("selectByTelPhone"),telphone);
    }

    public void register(ShopUser shopUser) {
        getSqlSession().insert(getStatementName("insertSelective"),shopUser);
    }

	public Long getShopUserIdBySiId(Long siId) {
		return getSqlSession().selectOne(getStatementName("getShopUserIdBySiId"),siId);
	}
    public Page getById(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getById"), pageRequest);
	}
    
    public Page getShopUserList(PageRequest<Map<String, Object>> pageRequest){
    	return pageQuery(getStatementName("getShopUserList"), pageRequest);
    }

}
