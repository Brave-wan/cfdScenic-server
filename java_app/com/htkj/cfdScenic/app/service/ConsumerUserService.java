package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.ConsumerUserDao;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class ConsumerUserService {
	
	@Autowired
	private ConsumerUserDao consumerUserDao;
	
	
	/**
	 * @Title: getUserIdByToken
	 * @Description: TODO(跟据token查询用户ID)
	 * @param token
	 * @return
	 */
	public Long getUserIdByToken(String token){
		return consumerUserDao.getUserIdByToken(token);
	}


	public void editDatum(ConsumerUser consumerUser) {
		consumerUserDao.editDatum(consumerUser);
	}


	public Integer selectById(Long userId) {
		return consumerUserDao.selectById(userId);
	}


	public ConsumerUser selectByPhone(String mobileNo) {
		return consumerUserDao.selectByPhone(mobileNo);
	}


	public Integer updateUUID(ConsumerUser userMessage) {
		return consumerUserDao.updateUUID(userMessage);
	}


	public Integer insertMessage(ConsumerUser user) {
		return consumerUserDao.insertMessage(user);
	}


	public ConsumerUser findUserByOpenId(String openId) {
		return consumerUserDao.findUserByOpenId(openId);
	}


	public void updateMessage(ConsumerUser user) {
		consumerUserDao.updateMessage(user);
	}


	public Integer update(ConsumerUser consumerUser) {
		return consumerUserDao.updateMessages(consumerUser);
	}

    public void updateState(ConsumerUser consumerUser) {
        consumerUserDao.updateState(consumerUser);
    }

    public DataGrid getConsumerUserList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data;
        try {
            Page page = consumerUserDao.getConsumerUserList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
        	throw new BusinessException("查询用户列表失败",e);
        }
        return data;
    }


	public String getUserPassWordById(Long userId) {
		return consumerUserDao.getUserPassWordById(userId);
	}

}
