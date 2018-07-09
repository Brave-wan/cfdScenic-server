package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.AdvertisDao;
import com.htkj.cfdScenic.app.model.Advertisement;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
@Service
@Transactional
public class AdvertisService {
	@Autowired
	private AdvertisDao advertisDao;
	public DataGrid toadvertisManage(
			PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = advertisDao.pageGetAdvertis(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询广告信息列表出错",e);
		}
		return data;
	}


	public int insert(Advertisement advertisement) {
		return advertisDao.insert(advertisement);
		
	}

	public int updateByPrimaryKeySelective(Advertisement advertisement) {
		return advertisDao.updateByPrimaryKeySelective(advertisement);
	}


	public Advertisement selectByPrimaryKey(Long id) {
		return advertisDao.selectByPrimanrKey(id);
	}


	public int deleteByPrimaryKey(Long id) {
		return advertisDao.deleteByPrimaryKey(id);
		
	}


	public List<Map<String, Object>> scenicSpotLimitPage(Map map) {
		return advertisDao.scenicSpotLimitPage(map);
	}


	public Map<String, Object> scenicSpotParticulars(Long id) {
		return advertisDao.scenicSpotParticulars(id);
	}


	public List<String> selectPictureLibrary(Long cId) {
		return advertisDao.selectPictureLibrary(cId);
	}


	public List<Map<String, String>> obscureSelect(String name) {
		return advertisDao.obscureSelect(name);
	}

}
