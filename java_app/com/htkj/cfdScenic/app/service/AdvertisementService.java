package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.AdvertisementDao;
import com.htkj.cfdScenic.app.model.Advertisement;
import com.htkj.cfdScenic.app.model.Jump;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdvertisementService {
	@Autowired
	private AdvertisementDao advertisementDao;
	//景区查首页轮播图
	public List<Map<String, String>> selectImgUrl() {
		return advertisementDao.selectImgUrl();
	}
	public List<Map<String, String>> selectPrimarySideImgUrl() {
		return advertisementDao.selectPrimarySideImgUrl();
	}
	public List<Map<String, String>> adPositionId() {
		return advertisementDao.adPositionId();
	}

	public DataGrid getAdvertisementList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = advertisementDao.pageGetAdvertisement(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询轮播图信息出错",e);
		}
		return data;
	}

	public int insert(Advertisement advertisement){
		return advertisementDao.insert(advertisement);
	}

	public int deleteAdvertisement(Long id) {
		return advertisementDao.deleteAdvertisement(id);
	}

    public int updateByPrimaryKeySelective(Advertisement advertisement) {
        return advertisementDao.updateByPrimaryKeySelective(advertisement);
    }


    public Advertisement selectByPrimaryKey(Long id) {
        return  advertisementDao.selectByPrimaryKey(id);
    }
	public List<Jump> getVisitorsAndShopInformation() {
		return advertisementDao.getVisitorsAndShopInformation();
	}
	public List<Map> getJumpType(Map map) {
		return advertisementDao.getJumpType(map);
	}
	public List<Map<String, String>> getCarouselImg() {
		return advertisementDao.getCarouselImg();
	}
}
