package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.RestaurantPackageDao;
import com.htkj.cfdScenic.app.model.RestaurantPackage;
import com.htkj.cfdScenic.app.model.ShopGoods;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class RestaurantPackageService extends BaseService{

	@Autowired
	private RestaurantPackageDao dao;
	
	public List findPackageGoodsById(Long id) {
		return dao.findPackageGoodsById(id);
	}

	public Map<String, Object> findPackageByPackageId(Long id) {
		return dao.findPackageByPackageId(id);
	}

	public List selectRestaurantOrderByOrderCode(String orderCode) {
		return dao.selectRestaurantOrderByOrderCode(orderCode);
	}

	public int updateByPrimaryKeySelective(RestaurantPackage restaurantPackage) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(restaurantPackage);
		
	}

	public int insert(RestaurantPackage restaurantPackage) {
		// TODO Auto-generated method stub
		return dao.insert(restaurantPackage);
	}
	public int delete(int id){
		return dao.deleteById(id);
	}

	public DataGrid getList(PageRequest<Map<String,Object>> pageRequest){
	    DataGrid data = new DataGrid();
	    try {
            Page page = dao.getList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
	    } catch (Exception e) {
	    	throw new BusinessException("商品查询出错",e);
	    }
	    return data;
    }

	public Map<String, Object> getshopInformationList(Long id) {
		// TODO Auto-generated method stub
		return dao.getshopInformationList(id);
	}

	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return dao.deleteById(id);
	}
	
}
