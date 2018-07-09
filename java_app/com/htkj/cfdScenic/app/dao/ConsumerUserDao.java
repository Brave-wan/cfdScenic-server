package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ConsumerUserDao extends BaseDao<ConsumerUser, Integer>{
	/**
	 * @Title: getUserIdByToken
	 * @Description: TODO(跟据token查询用户ID)
	 * @param token
	 * @return
	 */
	public Long getUserIdByToken(String token){
		Long id = getSqlSession().selectOne(getStatementName("getUserIdByToken"),token);
		return id;
	}

	public void editDatum(ConsumerUser consumerUser) {
		getSqlSession().update(getStatementName("editDatum"),consumerUser);
	}

	public Integer selectById(Long userId) {
		return getSqlSession().selectOne(getStatementName("selectById"),userId);
	}

	public ConsumerUser selectByPhone(String mobileNo) {
		return getSqlSession().selectOne(getStatementName("selectByPhone"),mobileNo);
	}
	
	public ConsumerUser selectByUserId(Long id) {
		return getSqlSession().selectOne(getStatementName("selectByUserId"),id);
	}
	public ConsumerUser selectByUserIdTwo(Long id) {
		return getSqlSession().selectOne(getStatementName("selectByUserIdTwo"),id);
	}

	public Integer updateUUID(ConsumerUser userMessage) {
		return getSqlSession().update(getStatementName("updateUUID"),userMessage);
	}

	public Integer insertMessage(ConsumerUser user) {
		return getSqlSession().insert(getStatementName("insertMessage"),user);
	}

	public ConsumerUser findUserByOpenId(String openId) {
		return getSqlSession().selectOne(getStatementName("findUserByOpenId"),openId);
	}

	public void updateMessage(ConsumerUser user) {
		getSqlSession().update(getStatementName("updateMessage"),user);
	}

	public Map<String,Object> findUserMessage(String telphone) {
		return getSqlSession().selectOne(getStatementName("findUserMessage"),telphone);
	}
	
	
	public String getPayPassWord(Long userId) {
		return getSqlSession().selectOne(getStatementName("getPayPassWord"),userId);
	}

	public void updatePayPassWord(Map map) {
		getSqlSession().update(getStatementName("updatePayPassWord"),map);
	}

	public Integer updateMessages(ConsumerUser consumerUser) {
		return getSqlSession().update(getStatementName("updateMessages"),consumerUser);
	}

	public String getUserPassWordByUserId(Long userId) {
		return getSqlSession().selectOne(getStatementName("getUserPassWordByUserId"),userId);
	}

    public void updateState(ConsumerUser consumerUser) {
        getSqlSession().update(getStatementName("updateState"),consumerUser);
    }

    public Page getConsumerUserList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getConsumerUserList"),pageRequest);
    }

	public String getUserPassWordById(Long userId) {
		return getSqlSession().selectOne(getStatementName("getUserPassWordByUserId"),userId);
	}
}
