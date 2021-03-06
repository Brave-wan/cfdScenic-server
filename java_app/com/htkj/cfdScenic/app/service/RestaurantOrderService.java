package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.RestaurantOrderDao;
import com.htkj.cfdScenic.app.dao.ShopGoodsDao;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
@Transactional
public class RestaurantOrderService extends BaseService{
	@Autowired
	private RestaurantOrderDao restaurantOrderDao;
	@Autowired
	private ShopGoodsDao shopGoodsDao;

	public DataGrid findOrderList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = restaurantOrderDao.findOrderList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	public List<Map<String,Object>> selectOrderBycode(Map<String,Object> map) {
		return restaurantOrderDao.selectOrderBycode(map);
	}
	public Map<String, Object> findOrderFp(String orderCode) {
		return restaurantOrderDao.findOrderFp(orderCode);
	}
	public List<RestaurantOrder> findOrder(String orderCode) {
		return restaurantOrderDao.findOrder(orderCode);
	}
	public void updateOrderState(RestaurantOrder restaurantOrder) {
		restaurantOrderDao.updateOrderState(restaurantOrder);
	}

	public void updateState(Map ro) {
		restaurantOrderDao.updateState(ro);
	}
	public List<Map<String, Object>> getOrderDetailByShop(Map goods) {
		return restaurantOrderDao.getOrderDetailByShop(goods);
	}
	public Map getRestaurantOrderByOrderCode(Map goods) {
		return restaurantOrderDao.getRestaurantOrderByOrderCode(goods);
	}
	public List getShopGoodsByIds(Map sg) {
		return shopGoodsDao.getShopGoodsByIds(sg);
	}
	public void deleteState(Map ro) {
		restaurantOrderDao.deleteState(ro);
	}
	public List<Map<String, Object>> shopFindRestaurantOrder(Map para) {
		return restaurantOrderDao.shopFindRestaurantOrder(para);
	}

    public DataGrid getRestaurantOrderList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data;
        try {
            Page page = restaurantOrderDao.getRestaurantOrderList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
            throw new BusinessException("查询饭店订单信息列表出错",e);
        }
        return data;
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return restaurantOrderDao.selectByPrimaryKey(id);
    }

    public void deleteVisitorsOrder(Long id) {
        restaurantOrderDao.deleteVisitorsOrder(id);
    }

    public void updateByOrderCode(RestaurantOrder restaurantOrder) {
        restaurantOrderDao.updateByOrderCode(restaurantOrder);
    }
	public int updateBybilling(RestaurantOrder restaurantOrder) {
		// TODO Auto-generated method stub
		return  restaurantOrderDao.updateBybilling(restaurantOrder);
	}
	public int updateBillingByOrderCode(RestaurantOrder restaurantOrder) {
		// TODO Auto-generated method stub
		return  restaurantOrderDao.updateBillingByOrderCode(restaurantOrder);
	}

    public Double getTurnover(Long shopInformationId) {
        return restaurantOrderDao.getTurnover(shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return restaurantOrderDao.getTodayTurnover(shopInformationId);
    }
	public DataGrid getgroupOrderList(
			PageRequest<Map<String, Object>> pageRequest) {
		 DataGrid data;
	        try {
	            Page page = restaurantOrderDao.getgroupOrderList(pageRequest);
	            data = DataGrid.pageToDataGrid(page);
	        } catch (Exception e) {
	            throw new BusinessException("查询饭店订单信息列表出错",e);
	        }
	        return data;
	}
	public Json toFinshOrder(String orderCode, Long shopInformationId) {
		Json json = new Json();
		try {
			RestaurantOrder ro = new RestaurantOrder(); 
			ro.setOrderCode(orderCode);
			ro.setShopInformationId(shopInformationId);
			ro.setOrderState(4);
			restaurantOrderDao.updateToFinshOrder(ro);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage("操作失败！");
			e.printStackTrace();
		}
		return json;
	}
}
