package com.htkj.cfdScenic.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.ExpressDao;
import com.htkj.cfdScenic.app.dao.GoodsOrderDao;
import com.htkj.cfdScenic.app.dao.HotelOrderDao;
import com.htkj.cfdScenic.app.dao.OrderPersonDao;
import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.dao.RefundCauseDao;
import com.htkj.cfdScenic.app.dao.RestaurantOrderDao;
import com.htkj.cfdScenic.app.dao.UserAccountDao;
import com.htkj.cfdScenic.app.dao.UserCommentDao;
import com.htkj.cfdScenic.app.model.Express;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.RefundCause;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class OrderDetailService {

	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private GoodsOrderDao goodsOrderDao;
	@Autowired
	private RestaurantOrderDao restaurantOrderDao;
	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private PictureLibraryDao pictureLibraryDao;
	@Autowired
	private ExpressDao expressDao;
	@Autowired
	private RefundCauseDao refundCauseDao;
	@Autowired
	private OrderPersonDao orderPersonDao;
	@Autowired
	private UserAccountDao userAccountDao;
	
	//酒店订单
	public DataGrid findHotelOrder(PageRequest<Map<String, Object>> pageRequest) {
		Page page = hotelOrderDao.findHotelOrder(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}
	//酒店订单数量
	public Integer findHotelOrderCount(Map map) {
		return hotelOrderDao.findHotelOrderCount(map);
	}
	//特产订单
	public DataGrid findGoodsOrder(PageRequest<Map<String, Object>> pageRequest) {
		Page page = goodsOrderDao.findGoodsOrder(pageRequest); 
		return DataGrid.pageToDataGrid(page);
	}
	
	//特产订单
	public DataGrid findShopGoodsOrder(PageRequest<Map<String, Object>> pageRequest) {
		Page page = goodsOrderDao.findShopGoodsOrder(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}
	//特产订单-数量
	public Integer findShopGoodsOrderCount(Map map) {
		return goodsOrderDao.findShopGoodsOrderCount(map);
	}
	
	//饭店未使用订单
	public DataGrid findRestaurantOrder(PageRequest<Map<String, Object>> pageRequest) {
		Page page = restaurantOrderDao.findRestaurantOrder(pageRequest);
		return DataGrid.pageToDataGrid(page);

	}
	//饭店未使用订单数量
	public Integer findRestaurantOrderCount(Map map) {
		return restaurantOrderDao.findRestaurantOrderCount(map);
	}
	//获取酒店订单详情
	public List<Map<String,Object>> findHotelOrderDetail(Long orderId) {
		return hotelOrderDao.findHotelOrderDetail(orderId);
	}
	//获取商品订单详情
	public List<Map<String,Object>> findGoodsOrderDetail(Long orderId) {
		return goodsOrderDao.findGoodsOrderDetail(orderId);
	}
	//获取饭店订单详情
	public List<Map<String,Object>> findRestaurantOrderDetail(Long orderId) {
		return restaurantOrderDao.findRestaurantOrderDetail(orderId);
	}
	//修改退付酒店订单
	public void hotelBackMoney(Map map) {
		hotelOrderDao.hotelBackMoney(map);
	}
	//修改商品订单
	public void shopOrderChange(Map map) {
		goodsOrderDao.shopOrderChange(map);
	}
	
	//修改退付商品订单
	public void goodsBackMoney(Map map) {
		goodsOrderDao.goodsBackMoney(map);
	}
	public void restaurantBackMoney(Map map) {
		restaurantOrderDao.restaurantBackMoney(map);
	}
	public void saveUserComment(UserComment userComment) {
		userCommentDao.saveUserComment(userComment);
	}
	public void setPhotoimage(String contextUrl) {
		
	}
	public void saveCommentPic(PictureLibrary pictureLibrary) {
		pictureLibraryDao.saveCommentPic(pictureLibrary);
	}
	public void deleteHotelOrder(Long orderCode) {
		hotelOrderDao.deleteHotelOrder(orderCode);
	}
	public void updateHotelOrder(Long orderCode) {
		hotelOrderDao.updateHotelOrderByOrderCode(orderCode);
	}
	;
	public void deleteRestaurantOrder(Long orderCode) {
		restaurantOrderDao.deleteRestaurantOrder(orderCode);
	}
	public void updateRestaurantOrder(Long orderCode) {
		restaurantOrderDao.updateRestaurantOrderByOrderCode(orderCode);
	}
	public void deleteGoodsOrder(Long orderCode) {
		goodsOrderDao.deleteGoodsOrder(orderCode);
	}
	public void updateGoodsOrderByOrderCode(Long orderCode) {
		goodsOrderDao.updateGoodsOrderByOrderCode(orderCode);
	}
	public List<Map<String, Object>> informationHotelOrderDetail(Map paraMap) {
		return hotelOrderDao.informationHotelOrderDetail(paraMap);
	}
	public List<Map<String, Object>> informationRestaurantOrderDetail(Map paraMap) {
		return restaurantOrderDao.informationRestaurantOrderDetail(paraMap);
	}
	public List<Map<String, Object>> informationGoodsOrderDetail(Map paraMap) {
		return goodsOrderDao.informationGoodsOrderDetail(paraMap);
	}
	public void saveExpress(Express express) {
		expressDao.saveExpress(express);
	}
	public Map<String,String> getExpressInfo(Map exMap) {
		return expressDao.getExpressInfo(exMap);
	}
	public void saveRefundCause(RefundCause refundCause) {
		refundCauseDao.saveRefundCause(refundCause);
	}
	public void updateGoodsOrder(GoodsOrder goodsOrder) {
		goodsOrderDao.updateGoodsOrderByorderCodeAll(goodsOrder);
	}
	public List<String> getPersonName(Long hotelOrderId) {
		return orderPersonDao.getPersonName(hotelOrderId);
	}
	public List<Map<String, Object>> shopFindGoodsOrder(Map paraMap) {
		return goodsOrderDao.shopFindGoodsOrder(paraMap);
	}
	public List<Map<String, Object>> shopFindHotelOrderDetail(Map para) {
		return hotelOrderDao.shopFindHotelOrderDetail(para);
	}
	public List<Map<String, Object>> shopFindRestaurantOrderDetail(Map para) {
		return restaurantOrderDao.shopFindRestaurantOrderDetail(para);
	}
	public List<Map<String, Object>> shopFindGoodsOrderDetail(Map para) {
		return goodsOrderDao.shopFindGoodsOrderDetail(para);
	}
	public RefundCause getRefundCause(Map para) {
		return refundCauseDao.getRefundCause(para);
	}
	public Long getShopInformation(Long orderCode) {
		return goodsOrderDao.getShopInformation(orderCode);
	}
	public UserAccount getUserAccount(Long shopUserId) {
		return userAccountDao.findByUserId(shopUserId);
	}
	public void updateUserAccount(UserAccount shop) {
		userAccountDao.updateBalanceByShopUserId(shop);
	}
	public Map<String, Object> getTodayMoney(Map para, Integer type) {
		Map<String, Object> money = new HashMap<String, Object>();
		switch (type) {
		case 1:
			money = hotelOrderDao.getTodayMoney(para);
			break;
		case 2:
			money = restaurantOrderDao.getTodayMoney(para);
			break;
		case 3:
			money = goodsOrderDao.getTodayMoney(para);
			break;
		}
		return money;
	}
	//订单统计
	public List<Map<String, Object>> findShopGoodsOrderNotIn(Map map) {
		return goodsOrderDao.findShopGoodsOrderNotIn(map);
	}
	public List<Map<String, Object>> findHotelOrderNotIn(Map map) {
		return hotelOrderDao.findHotelOrderNotIn(map);
	}
	public List<Map<String, Object>> findRestaurantOrderNotIn(Map map) {
		return restaurantOrderDao.findRestaurantOrderNotIn(map);
	}
	//订单搜索
	public List<Map<String, Object>> selectHotelOrder(Map para) {
		return hotelOrderDao.selectHotelOrder(para);
	}
	public List<Map<String, Object>> selectRestaurantOrder(Map para) {
		return restaurantOrderDao.selectRestaurantOrder(para);
	}
	public List<Map<String, Object>> selectGoodsOrder(Map para) {
		return goodsOrderDao.selectGoodsOrder(para);
	}
	public DataGrid selectorderManage(
			PageRequest<Map<String, Object>> pageRequest, Long type) {
		DataGrid data = new DataGrid();
		try {
			if(type == 1){
				Page page = goodsOrderDao.pageGetHotelOrder(pageRequest);
				data = DataGrid.pageToDataGrid(page);
			}
			if(type == 2){
				Page page = goodsOrderDao.pageGetRestaurantOrder(pageRequest);
				data = DataGrid.pageToDataGrid(page);
			}
			if(type == 3 || type == 4){
				Page page = goodsOrderDao.pageGetorder(pageRequest);
				data = DataGrid.pageToDataGrid(page);
			}
		} catch (Exception e) {
			throw new BusinessException("查询权限列表出错",e);
		}
		return data;
	}
	public Map<String, Object> getIsDeliverFeeByOrderCode(String orderCode) {
		return goodsOrderDao.getIsDeliverFeeByOrderCode(orderCode);
	}
	public Integer findGoodsOrderCount(Map goodsMap) {
		return goodsOrderDao.findGoodsOrderCount(goodsMap);
	}
	public Integer findRefundGoodsOrderCount(Map goodsMap) {
		return goodsOrderDao.findRefundGoodsOrderCount(goodsMap);
	}
	public void updateHotelOverdueOrder(Long userId) {
		hotelOrderDao.updateHotelOverdueOrder(userId);
	}
	public void updateRestaurantOverdueOrder(Long userId) {
		restaurantOrderDao.updateRestaurantOverdueOrder(userId);
	}
	public void updateGoodsOverdueOrder(Long userId) {
		goodsOrderDao.updateGoodsOverdueOrder(userId);
	}
	
	
}
