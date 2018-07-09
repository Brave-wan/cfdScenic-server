package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.HotelOrderDao;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htrj.common.base.BaseService;
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
public class HotelOrderService extends BaseService{
	@Autowired
	private HotelOrderDao hotelOrderDao;

	public DataGrid findOrderList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = hotelOrderDao.findOrderList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	public DataGrid findall(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = hotelOrderDao.findall(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	public List<Map<String,Object>> selectOrderBycode(Map<String,Object> map) {
		return hotelOrderDao.selectOrderBycode(map);
	}
	

	public HotelOrder findOrder(String orderCode) {
		return hotelOrderDao.findOrder(orderCode);
	}

	public HotelOrder getHotelOrderByOrderId(Long id) {
		return hotelOrderDao.getHotelOrderByOrderId(id);
	}
	public Map<String, Object> getHotelOrderByOrderIdyl(Long id) {
		return hotelOrderDao.getHotelOrderByOrderIdyl(id);
	}
	public Integer deleteMessage(String orderCode) {
		return hotelOrderDao.deleteMessage(orderCode);
	}

	public void updateOrderState(HotelOrder hotelOrder) {
		hotelOrderDao.updateOrderState(hotelOrder);
	}
	public void updateHotelOrderById(HotelOrder hotelOrder) {
		hotelOrderDao.updateHotelOrderById(hotelOrder);
	}
	public void updateState(Map go) {
		hotelOrderDao.updateState(go);
	}

	public void deleteState(Map go) {
		hotelOrderDao.deleteState(go);
	}


	public List<Map<String, Object>> shopFindHotelOrder(Map para) {
		return hotelOrderDao.shopFindHotelOrder(para);
	}
	public int updatekaipiao(HotelOrder hotelOrder) {
		// TODO Auto-generated method stub
		return hotelOrderDao.updatekaipiao(hotelOrder);
	}
	public int updateBillingByOrderCode(HotelOrder hotelOrder) {
		// TODO Auto-generated method stub
		return hotelOrderDao.updateBillingByOrderCode(hotelOrder);
	}
	public Map<String, Object> getHotelOrderByFp(Long code) {
		// TODO Auto-generated method stub
		return hotelOrderDao.getHotelOrderByFp(code);
	}

    public Double getTurnover(Long shopInformationId) {
        return hotelOrderDao.getTurnover(shopInformationId);
    }

    public Double getTodayTurnover(Long shopInformationId) {
        return hotelOrderDao.getTodayTurnover(shopInformationId);
    }
}
