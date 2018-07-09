package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.UserOpinionDao;
import com.htkj.cfdScenic.app.model.UserOpinion;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class UserOpinionService {
	@Autowired
	private UserOpinionDao userOpinionDao;

	public void insertMessage(UserOpinion userOpinion) {
		userOpinionDao.insertMessage(userOpinion);
	}

	  public DataGrid getAll(PageRequest<Map<String, Object>> pageRequest) {
	        DataGrid data;
	        try {
	            Page page = userOpinionDao.getAll(pageRequest);
	            data = DataGrid.pageToDataGrid(page);
	        } catch (Exception e) {
	            throw new BusinessException("查询商品订单信息列表出错",e);
	        }
	        return data;
	    }

	public Map selectById(long id) {
		// TODO Auto-generated method stub
		return userOpinionDao.selectById(id);
	}

}
