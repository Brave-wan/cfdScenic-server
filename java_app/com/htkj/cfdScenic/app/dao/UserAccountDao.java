package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.UserAccount;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserAccountDao extends BaseDao<UserAccount, Integer>{

	public Integer getRemainingPoints(Long userId) {
		return getSqlSession().selectOne(getStatementName("getRemainingPoints"),userId);
	}

	public Map getMyBalance(Long userId) {
		return getSqlSession().selectOne(getStatementName("getMyBalance"),userId);
	}

	public void updateBalanceUserAccount(Map map) {
		getSqlSession().update(getStatementName("updateBalanceUserAccount"),map);
	}

	public void updateCutDowmBalanceUserAccount(Map map) {
		getSqlSession().update(getStatementName("updateCutDowmBalanceUserAccount"),map);
	}
	
	public void updateBalanceShopToUser(Map map) {
		getSqlSession().update(getStatementName("updateBalanceShopToUser"),map);
	}
	
	public void updateBalanceUserAccountByUID(Map map) {
		getSqlSession().update(getStatementName("updateBalanceUserAccountByUID"),map);
	}

	public Map getMyIntegral(Long userId) {
		return getSqlSession().selectOne(getStatementName("getMyIntegral"),userId);
	}

	public Integer updateMoney(Map<String, Object> content) {
		return getSqlSession().update(getStatementName("updateMoney"),content);
	}

	public UserAccount findByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("findByUserId"),userId);
	}

	public void updateBalanceMessage(UserAccount userAccountMessage) {
		getSqlSession().update(getStatementName("updateBalanceMessage"),userAccountMessage);
	}

	public void insertUserAccount(UserAccount userAccount) {
		getSqlSession().insert(getStatementName("insertUserAccount"),userAccount);
	}

	public void updateUserAccountByUserId(Map map) {
		getSqlSession().update(getStatementName("updateUserAccountByUserId"),map);
	}

	public Double getUserAccount(Long userId) {
		return getSqlSession().selectOne(getStatementName("getUserAccount"),userId);
	}

	public Integer getIntegrationByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("getIntegrationByUserId"),userId);
	}

	public void updateAccountByMap(Map map) {
		getSqlSession().update(getStatementName("updateAccountByMap"),map);
	}

    public Page getUserAccountList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getUserAccountList"),pageRequest);
    }

    public UserAccount selectBalance(Long id) {
        return getSqlSession().selectOne(getStatementName("selectBalance"),id);
    }

    public void addBalance(UserAccount userAccount) {
        getSqlSession().update(getStatementName("addBalance"),userAccount);
    }

	public void updateBalanceByShopUserId(UserAccount shop) {
		getSqlSession().update(getStatementName("updateBalanceByShopUserId"),shop);
	}

    public UserAccount selectByUserId(Long userId) {
        return getSqlSession().selectOne(getStatementName("selectByUserId"),userId);
    }

    public void addBalanceByUserId(UserAccount userAccount) {
        getSqlSession().update(getStatementName("addBalanceByUserId"),userAccount);
    }

	public UserAccount getUserAccountByUserId(Long id) {
		return getSqlSession().selectOne(getStatementName("getUserAccountByUserId"),id);
	}

	public void updateUserAccount(UserAccount userAccount) {
		getSqlSession().update(getStatementName("updateUserAccount"),userAccount);
	}
}
