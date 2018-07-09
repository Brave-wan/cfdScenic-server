package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.TravelogsDao;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class TravelogsService {
	@Autowired
	private TravelogsDao travelogsDao;
	
	public DataGrid findOrderList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = travelogsDao.getTraveLogs(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return travelogsDao.deleteById(id);
	}

	public   Map<String,Object> checkTravelLogDetail(Long id){
		// TODO Auto-generated method stub
		return travelogsDao.checkTravelLogDetail(id);
	}
	
	
}
