package com.htkj.cfdScenic.app.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.PublicPlacesDao;
import com.htkj.cfdScenic.app.model.PublicPlaces;
import com.htrj.common.base.BaseService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.utils.GenerateSequenceUtil;

@Service
@Transactional
public class PublicPlacesService extends BaseService{

	@Autowired
	private PublicPlacesDao publicPlacesDao;
	
	public DataGrid checkPublicPlaces(PageRequest<Map<String, Object>> pageRequest) {
		Page page = publicPlacesDao.checkPublicPlaces(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}

	public Json savePublicPlaces(PublicPlaces publicPlaces) {
		Json json = new Json();
		try {
			if (publicPlaces.getId() != null) {
				publicPlacesDao.updatePublicPlaces(publicPlaces);
				json.setSuccess(true);
			} else {
				publicPlaces.setId(GenerateSequenceUtil.getUniqueId());
				publicPlaces.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				publicPlacesDao.savePublicPlaces(publicPlaces);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("error");
			json.setSuccess(false);
		}
		return json;
	}

	public PublicPlaces selectPublicPlaces(Long id) {
		PublicPlaces publicPlaces = new PublicPlaces();
		try {
			publicPlaces = publicPlacesDao.selectPublicPlaces(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicPlaces;
	}

	public Json deletePublicPlaces(Long id) {
		Json json = new Json();
		try {
			publicPlacesDao.deletePublicPlaces(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMessage("删除失败，请重试");
			json.setSuccess(false);
		}
		return json;
	}
	
}
