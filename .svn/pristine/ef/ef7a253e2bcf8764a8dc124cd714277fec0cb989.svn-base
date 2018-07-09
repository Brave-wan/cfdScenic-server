package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.AdvertisingPageDao;
import com.htkj.cfdScenic.app.model.AdvertisingPage;
import com.htrj.common.base.BaseService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class AdvertisingPageService extends BaseService{

	@Autowired 
	private AdvertisingPageDao advertisingPageDao;
	
	
	public DataGrid getAdvertisingPage(PageRequest<Map<String, Object>> pageRequest) {
		return DataGrid.pageToDataGrid(advertisingPageDao.getAdvertisingPage(pageRequest));
	}


	public Map getAdvertisingPageDetail(Long id) {
		return advertisingPageDao.getAdvertisingPageDetail(id);
	}


	public void saveAdvertisingPage(AdvertisingPage ap) {
		advertisingPageDao.saveAdvertisingPage(ap);
	}


	public void updateAdvertisingPage(AdvertisingPage ap) {
		advertisingPageDao.updateAdvertisingPage(ap);
	}


	public void deleteById(Long id) {
		advertisingPageDao.deleteById(id);
	}


	public String getAdvertisingPageById(Long id) {
		return advertisingPageDao.getAdvertisingPageById(id);
	}
	
}
