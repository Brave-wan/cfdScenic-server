package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.UserCollectDao;
import com.htkj.cfdScenic.app.model.UserCollect;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class UserCollectService {
	@Autowired
	private UserCollectDao userCollectDao;

	@SuppressWarnings("rawtypes")
	public DataGrid selectCollectList(PageRequest<Map<String, Object>> pageRequest) {
		//return userCollectDao.selectCollectList(userId);
		Page returnPage = userCollectDao.selectCollectList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public Integer deleteCollect(Map map) {
		return userCollectDao.deleteCollect(map);
	}
	
	public Integer delCollectByGoodsAndUid(Map map){
		return userCollectDao.delCollectByGoodsAndUid(map);
	}

	public void insertCollect(UserCollect userCollect) {
		 userCollectDao.insertCollect(userCollect);
	}
	
	public Integer queryCollect(UserCollect userCollect) {
		return userCollectDao.queryCollect(userCollect);
	}

}
