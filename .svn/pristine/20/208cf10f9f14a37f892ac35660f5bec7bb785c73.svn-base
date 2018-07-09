package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.UserAccountLogDao;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class UserAccountLogService {
	@Autowired
	private UserAccountLogDao userAccountLogDao;

	public List<Map<String, String>> selectUserMessage(Long userId) {
		return userAccountLogDao.selectUserMessage(userId);
	}

	public List<Map<String, String>> selectShopMessage(Long userId) {
		return userAccountLogDao.selectShopMessage(userId);
	}

	public DataGrid selectShopList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = userAccountLogDao.selectShopList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public DataGrid selectUserList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = userAccountLogDao.selectUserList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public Integer insertMessage(UserAccountLog userAccountLog) {
		return userAccountLogDao.insertMessage(userAccountLog);
	}
	

}
