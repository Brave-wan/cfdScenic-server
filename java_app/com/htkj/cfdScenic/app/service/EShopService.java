package com.htkj.cfdScenic.app.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.AdvertisementDao;
import com.htkj.cfdScenic.app.dao.ConsumerUserDao;
import com.htkj.cfdScenic.app.dao.GoodsOrderDao;
import com.htkj.cfdScenic.app.dao.HotelOrderDao;
import com.htkj.cfdScenic.app.dao.OrderPersonDao;
import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.dao.RestaurantOrderDao;
import com.htkj.cfdScenic.app.dao.RestaurantPackageDao;
import com.htkj.cfdScenic.app.dao.ShopCartDao;
import com.htkj.cfdScenic.app.dao.ShopGoodsDao;
import com.htkj.cfdScenic.app.dao.ShopInformationDao;
import com.htkj.cfdScenic.app.dao.ShopUserDao;
import com.htkj.cfdScenic.app.dao.ShopsGroupDao;
import com.htkj.cfdScenic.app.dao.UserAccountDao;
import com.htkj.cfdScenic.app.dao.UserAccountLogDao;
import com.htkj.cfdScenic.app.dao.UserAddressDao;
import com.htkj.cfdScenic.app.dao.UserCollectDao;
import com.htkj.cfdScenic.app.dao.HtmlTextDao;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.OrderPerson;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htkj.cfdScenic.app.model.ShopCart;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.UserCollect;
import com.htkj.cfdScenic.app.util.MD5;
import com.htrj.common.base.BaseService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.JPushUtils;

@Service
@Transactional
public class EShopService extends BaseService{
	
	@Autowired
	private ShopGoodsDao shopGoodsDao;
	
	@Autowired
	private ShopInformationDao shopInformationDao;
	
	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Autowired
	private ShopsGroupDao shopsGroupDao;
	
	@Autowired
	private ShopCartDao shopCartDao;
	
	@Autowired
	private PictureLibraryDao pictureLibraryDao;
	
	@Autowired
	private HtmlTextDao visitorsProfilesDao;
	
	@Autowired
	private UserAddressDao userAddressDao;
	
	@Autowired
	private GoodsOrderDao goodsOrderDao;
	
	@Autowired
	private HotelOrderDao hotelOrderDao;
	
	@Autowired
	private OrderPersonDao orderPersonDao;
	
	@Autowired
	private RestaurantOrderDao restaurantOrderDao;
	
	@Autowired
	private UserAccountDao userAccountDao;
	
	@Autowired
	private UserAccountLogDao userAccountLogDao;
	
	@Autowired
	private RestaurantPackageDao restaurantPackageDao;
	
	@Autowired
	private ConsumerUserDao consumerUserDao;
	
	@Autowired
	private UserCollectDao userCollectDao;
	
	@Autowired
	private ShopUserDao shopUserDao;
	
	
	/**
	 * 模糊查询商品表和商户信息
	 */
	public Map<String, List<Map<String, String>>> linkSelect(String keyWord){
		List<Map<String,String>> shopGoodsMap = new ArrayList<Map<String,String>>();
		List<Map<String,String>> shopUserMap = new ArrayList<Map<String,String>>();
		Map<String,List<Map<String,String>>> returnMap = new HashMap<String,List<Map<String,String>>>();
		try {
			shopGoodsMap = shopGoodsDao.linkSelect(keyWord);
			shopUserMap = shopInformationDao.linkSelect(keyWord);
			returnMap.put("shopGoodsMap", shopGoodsMap);
			returnMap.put("shopUserMap", shopUserMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	/**
	 * 查询轮播图
	 */
	public List<Map<String, String>> findPic(){
		List<Map<String, String>> picList = advertisementDao.findPic();
		return picList;
	}
	/**
	 * 为你推荐
	 */
	public List<Map<String, Object>> selectRecommend(){
		List<Map<String,Object>> list = shopGoodsDao.selectRecommend();
		return list;
	}
	/**
	 * 查询全部分类
	 * 先查出来所有的分类，在按照分类的Id查询店铺
	 */
	public List<Map<String, Object>> getType(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			list = shopInformationDao.selectByShopId();
//			list = shopsGroupDao.getType();
//			for(int i=0;i<list.size();i++){
//				list.get(i).put(String.valueOf(list.get(i).get("id")),shopInformationDao.selectByShopId(String.valueOf(list.get(i).get("id"))));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据id查询店铺信息
	 */
	public Map selectById(Long id) {
		return shopInformationDao.selectById(id);
	}
	/**
	 * 根据userId查询购物车
	 */
	public List getShopCartByUserId(Long userId) {
		return shopCartDao.getShopCartByUserId(userId);
	}
	
	public List getShopCartGoodsByUserId(Long userId){
		List list = shopCartDao.getShopCartShopGroupByUserId(userId);
		for(int i =0; i<list.size(); i++){
			HashMap map = (HashMap) list.get(i);
			List goodsList = shopCartDao.getShopCartGoodsByUserId(map);
			map.put("goodsList", goodsList);
		}
		return list;
	}
	
	public void deleteShopCartById(String ids) {
		Map map = new HashMap();
		map.put("id",ids);
		shopCartDao.deleteShopCartById(map);
	}
	public DataGrid selectShopInformation(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopInformationDao.selectShopInformation(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	/**
	 * 新 - 分页查询
	 * @param parameter 
	 */
	public List<Map<String,Object>> selectShopInformationPage(Map parameter) {
		return shopInformationDao.selectShopInformationPage(parameter);
	}
	public DataGrid selectShopGoodsByInformationId(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopGoodsDao.selectShopGoodsByInformationId(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	/**
	 * 新 - 分页查询
	 */
	public List<Map<String,Object>> selectShopGoodsByInformationIdPage(Map parameter) {
		return shopGoodsDao.selectShopGoodsByInformationIdPage(parameter);
	}
	
	public Map findDetailByGoodsId(Long goodsId) {
		return shopGoodsDao.findDetailByGoodsId(goodsId);
	}
	/**
	 * 通过商品Id和用户获取商品及关注状态
	 */
	public Map findDetailByGoodsIdAndUid(Map map) {
		return shopGoodsDao.findDetailByGoodsIdAndUid(map);
	}
	public List getPicById(Long goodsId) {
		return pictureLibraryDao.getPicById(goodsId);
	}
	public String getContentUrlById(Long goodsId) {
		return visitorsProfilesDao.getContentUrlById(goodsId);
	}
	public void saveShopCart(ShopCart shopCart) {
		shopCartDao.saveShopCart(shopCart);
	}
	public void updateShopCart(Long id, Integer number) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("number", number);
		shopCartDao.updateShopCart(map);
	}
	public Map getUserAdderssByUserId(Long userId) {
		return userAddressDao.getUserAdderssByUserId(userId);
	}
	public void saveGoodsOrder(GoodsOrder goodsOrder){
		goodsOrderDao.saveGoodsOrder(goodsOrder);
	}
	public List findGoodsOrderById(Map map) {
		return goodsOrderDao.findGoodsOrderById(map);
	}
	public DataGrid findHotel(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopInformationDao.findHotel(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	public DataGrid findHotelPage(PageRequest<Map<String, Object>> pageRequest) {
		Page page = shopInformationDao.findHotelPage(pageRequest); 
		return DataGrid.pageToDataGrid(page);
	}
	//通过店铺的id查询所有的商品信息
	public List<Map<String,Object>> findHotelGoodsById(Long id) {
		return shopGoodsDao.findHotelGoodsById(id);
	}
	//通过店铺的id查询所有的商品信息
	public List<Map<String,Object>> findRestaurantGoodsById(Long id) {
		return shopGoodsDao.findRestaurantGoodsById(id);
	}
	//通过店铺的Id查询轮播图
	public List findHotelPicBy(Long id) {
		return pictureLibraryDao.findHotelPicBy(id);
	}
	public void saveHotelOrder(HotelOrder hotelOrder) {
		hotelOrderDao.saveHotelOrder(hotelOrder);
	}
	//获取单个商品的详情
	public Map findHotelGoodsDetail(Long goodsId) {
		return shopGoodsDao.findHotelGoodsDetail(goodsId);
	}
	public void saveOrderPerson(OrderPerson orderPerson) {
		orderPersonDao.saveOrderPerson(orderPerson);
	}
	public Map getHotelOrder(Long gotelOrderId) {
		return hotelOrderDao.getHotelOrder(gotelOrderId);
	}
	public int getCountNumberHotelOrder(Map map) {
		return hotelOrderDao.getCountNumberHotelOrder(map);
	}
	public int getstockNumber(Long shopGoodsId) {
		return shopGoodsDao.getstockNumber(shopGoodsId);
	}
	public DataGrid findAllRestaurant(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopInformationDao.findAllRestaurant(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	/**
	 * 新分页查询
	 */
	public DataGrid findAllRestaurantPage(PageRequest<Map<String, Object>> pageRequest) {
		Page page = shopInformationDao.findAllRestaurantPage(pageRequest); 
		return DataGrid.pageToDataGrid(page);
		
	}
	public Map findAllRestaurantDetail(Long id) {
		return shopInformationDao.findAllRestaurantDetail(id);
	}
	public void saveRestaurantOrder(RestaurantOrder restaurantOrder) {
		restaurantOrderDao.saveRestaurantOrder(restaurantOrder);
	}
	public Map selectRestaurantOrderById(String orderCode) {
		return restaurantOrderDao.selectRestaurantOrderById(orderCode);
	}
	/**
	 * 修改饭店订单的状态
	 */
	public void updateRestaurantOrder(Map map) {
		Integer payWay = Integer.parseInt(map.get("payWay")+"");
		switch (payWay) {
		case 1:
			
			break;
		case 2:
			restaurantOrderDao.updateRestaurantOrder(map);
			break;
		case 3:
			restaurantOrderDao.updateRestaurantOrder(map);
			break;
		}
		
	}
	
	public void updateGoodsOrder(Map map) {
		goodsOrderDao.updateGoodsOrder(map);
	}
	
	public void updateHotelOrder(Map map) {
		hotelOrderDao.updateHotelOrder(map);
	}
	/**
	 * 	取消订单修改订单状态 
	 */
	public void updateUndoRestaurantOrder(Long id) {
		restaurantOrderDao.updateUndoRestaurantOrder(id);
	}
	public void updateUndoGoodsOrder(Long id) {
		goodsOrderDao.updateUndoGoodsOrder(id);
	}
	public void updateUndoHotelOrder(Long id) {
		hotelOrderDao.updateUndoHotelOrder(id);
	}
	public List<Map> getHotGoods() {
		return shopGoodsDao.getHotGoods();
	}
	public DataGrid findAllSnack(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopInformationDao.findAllSnack(pageRequest);
		DataGrid dataGrid=DataGrid.pageToDataGrid(returnPage);
		return dataGrid;
	}
	/**
	 * 分页 - 小吃列表分页2
	 */
	public List<Map<String,Object>> findAllSnackPage(Map map) {
		return shopInformationDao.findAllSnackPage(map);
	}
	public Integer getOrderNumberByGoodsId(Map paraMap) {
		return shopGoodsDao.getOrderNumberByGoodsId(paraMap);
	}
	public Double getHotelOrderById(Long hotelOrderId) {
		return hotelOrderDao.getHotelOrderById(hotelOrderId);
	}
	public void updateUserAccountByUserId(Map map) {
		userAccountDao.updateUserAccountByUserId(map);
	}
	public void updateHotelOrderByMap(Map map) {
		hotelOrderDao.updateHotelOrderByMap(map);
	}
	public Double getUserAccount(Long userId) {
		return userAccountDao.getUserAccount(userId);
	}
	//商品支付
	public int payOrder(Long id,String orderCode,Long userId, Double balance, Double price, Integer payType,String passWord) {
		//获取用户密码
		switch (payType) { 
		case 1:
			String userPassWord = consumerUserDao.getUserPassWordByUserId(userId);
			MD5 getMD5 = new MD5();
			if (passWord != null) {
				if (getMD5.GetMD5Code(passWord).equals(userPassWord)) {
					List<String> orderCodeList = new ArrayList<String>();
					if(orderCode != null && orderCode.indexOf(",") != -1)
					{
						String[] str = orderCode.split(",");
						for(int i=0;i<str.length;i++){
							orderCodeList.add(str[i]);
						}
					}else{
						orderCodeList.add(orderCode);
					}	
					//循环订单号
					for(int i=0;i<orderCodeList.size();i++){
					if (balance > price) {
						balance = balance - price;
						// 获取订单信息
						GoodsOrder GoodsOrder = goodsOrderDao.getGoodsOrderByOrderCode(orderCodeList.get(i));
						String money = (GoodsOrder.getRealPrice()+"").substring(0,(GoodsOrder.getRealPrice()+"").indexOf("."));
						Map map = new HashMap();
						map.put("balance", balance);
						map.put("integration", money);
						map.put("userId", userId);
						// 更新账户余额
						userAccountDao.updateAccountByMap(map);
						//更新商家余额
							//获取商家余额
							UserAccount shua = userAccountDao.getUserAccountByUserId(GoodsOrder.getShopInformationId());
							shua.setBalance(shua.getBalance()+GoodsOrder.getRealPrice());
							//更新商家
							userAccountDao.updateUserAccount(shua);
						//获取用户剩余积分
						UserAccount ua = userAccountDao.findByUserId(userId);
						// 保存余额交易记录
						UserAccountLog userAccountLog = new UserAccountLog();
						userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
						userAccountLog.setName("特产小吃类订单");
						userAccountLog.setType(2);
						userAccountLog.setPrice(GoodsOrder.getRealPrice());
						userAccountLog.setBalance(balance);
						String str = GoodsOrder.getRealPrice()+"";
						str = str.substring(0,str.indexOf("."));
						userAccountLog.setTradeIntegration(Integer.parseInt(str));
						userAccountLog.setIntegration(ua.getIntegration());
						userAccountLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						userAccountLog.setUserId(userId);
						userAccountLog.setShopId(GoodsOrder.getShopInformationId());
						userAccountLog.setExtractType(0);
						userAccountLogDao.saveUserAccountLog(userAccountLog);
						// 修改订单状态
						Map order = new HashMap();
						order.put("id", orderCode);
						order.put("payWay", 1);
						goodsOrderDao.updateGoodsOrderState(order);
						//推送通知
						Long siId = GoodsOrder.getShopInformationId();
						Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
						if(shopUserId != null){
							List<String> ids = new ArrayList<String>();
							ids.add(shopUserId+"");
							
							Map<String, String> extras = new HashMap<String, String>();
							extras.put("content","您有新的订单，请注意查收！");
							extras.put("goodsType","3");
							extras.put("orderCode",GoodsOrder.getOrderCode());
							extras.put("siId",GoodsOrder.getShopInformationId()+"");
							extras.put("type","1");
							JPushUtils.sendPushNotification(ids,extras);
						}
						return 0;
					}
					}
				} else {
					return 1;
				}
			} else {
				return 2;
			}
			
		case 2:
			Map map = new HashMap();
			map.put("integration", price);
			map.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map);
			//修改订单状态
			Map order = new HashMap();
			order.put("id",orderCode);
			order.put("payWay",2);
			goodsOrderDao.updateGoodsOrderState(order);
			// 获取订单信息
			GoodsOrder GoodsOrder = goodsOrderDao.getGoodsOrderByOrderId(id);
			//推送通知
			Long siId = GoodsOrder.getShopInformationId();
			Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
			if(shopUserId != null){
				List<String> ids = new ArrayList<String>();
				ids.add(shopUserId+"");
				
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("content","您有新的订单，请注意查收！");
				extras.put("goodsType","3");
				extras.put("orderCode",GoodsOrder.getOrderCode());
				extras.put("siId",siId+"");
				extras.put("type","1");
				JPushUtils.sendPushNotification(ids,extras);
			}
			return 0;
		case 3:
			Map map1 = new HashMap();
			map1.put("integration", price);
			map1.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map1);
			//修改订单状态
			Map order1 = new HashMap();
			order1.put("id",orderCode);
			order1.put("payWay",3);
			goodsOrderDao.updateGoodsOrderState(order1);
			// 获取订单信息
						GoodsOrder GoodsOrder1 = goodsOrderDao.getGoodsOrderByOrderId(id);
						//推送通知
						Long siId1 = GoodsOrder1.getShopInformationId();
						Long shopUserId1 = shopUserDao.getShopUserIdBySiId(siId1);
						if(shopUserId1 != null){
							List<String> ids = new ArrayList<String>();
							ids.add(shopUserId1+"");
							
							Map<String, String> extras = new HashMap<String, String>();
							extras.put("content","您有新的订单，请注意查收！");
							extras.put("goodsType","3");
							extras.put("orderCode",GoodsOrder1.getOrderCode());
							extras.put("siId",siId1+"");
							extras.put("type","1");
							JPushUtils.sendPushNotification(ids,extras);
						}
			return 0;
		}
		return 0;
	}
	public List<Map<String, Object>> getPayOrderFinshByOrderCode(Long orderCode) {
		return goodsOrderDao.getPayOrderFinshByOrderCode(orderCode);
	}
	public int getGoodsCount(Map para) {
		return hotelOrderDao.getGoodsCount(para);
	}
	public int getGoodsStock(Long id) {
		return shopGoodsDao.getGoodsStock(id);
	}
	public HotelOrder getHotelOrderDetail(Long hotelOrderId) {
		return hotelOrderDao.getHotelOrderDetail(hotelOrderId);
	}
	public List findPackageGoodsById(Long id) {
		return restaurantPackageDao.findPackageGoodsById(id);
	}
	public Map<String, Object> findPackageByPackageId(Long id) {
		return restaurantPackageDao.findPackageByPackageId(id);
	}
	public Map findPackageDetailByPackageId(Long packageId) {
		return shopGoodsDao.findPackageDetailByPackageId(packageId);
	}
	public List<String> findPicByPicId(Long id) {
		return pictureLibraryDao.selectPictureLibrary(id);
	}
	public int payHotelOrder(Long id,String orderCode, Long userId, Double balance, Double price, Integer payType, String passWord) {
		try {
		switch (payType) {
		case 1:
			String userPassWord = consumerUserDao.getUserPassWordByUserId(userId);
			MD5 getMD5 = new MD5();
			if (passWord != null) {
				if (getMD5.GetMD5Code(passWord).equals(userPassWord)) {
			if(balance > price){
				balance = balance - price;
				Map map = new HashMap();
				map.put("balance", balance);
				map.put("integration", price);
				map.put("userId",userId);
					
				//获取订单信息
				HotelOrder GoodsOrder = hotelOrderDao.getHotelOrderByOrderId(id);
				//更新账户余额
				userAccountDao.updateAccountByMap(map);
				//更新商家余额
					//获取商家余额
					UserAccount shua = userAccountDao.getUserAccountByUserId(GoodsOrder.getShopInformationId());
					shua.setBalance(shua.getBalance()+price);
					//更新商家
					userAccountDao.updateUserAccount(shua);
					//获取用户剩余积分
				UserAccount ua = userAccountDao.findByUserId(userId);
				//保存余额交易记录
				UserAccountLog userAccountLog = new UserAccountLog();
				userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
				userAccountLog.setName("酒店类订单");
				userAccountLog.setType(2);
				userAccountLog.setPrice(price);
				userAccountLog.setBalance(balance);
				String str = GoodsOrder.getRealPrice()+"";
				str = str.substring(0,str.indexOf("."));
				userAccountLog.setTradeIntegration(Integer.parseInt(str));
				userAccountLog.setIntegration(ua.getIntegration());
				userAccountLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				userAccountLog.setUserId(userId);
				userAccountLog.setShopId(GoodsOrder.getShopInformationId());
				userAccountLog.setExtractType(0);
				userAccountLogDao.saveUserAccountLog(userAccountLog);
				//修改订单状态
				Map order = new HashMap();
				order.put("id",orderCode);
				order.put("payWay",1);
				hotelOrderDao.updateHotelOrderState(order);
				//推送通知
				Long siId = GoodsOrder.getShopInformationId();
				Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
				if(shopUserId != null){
					List<String> ids = new ArrayList<String>();
					ids.add(shopUserId+"");
					
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("content","您有新的订单，请注意查收！");
					extras.put("goodsType","3");
					extras.put("orderCode",GoodsOrder.getOrderCode());
					extras.put("siId",siId+"");
					extras.put("type","1");
					JPushUtils.sendPushNotification(ids,extras);
				}
				return 0;
			}
				} else {
					return 1;
				}
			} else {
				return 2;
			}
		case 2:
			Map map = new HashMap();
			map.put("integration", price);
			map.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map);
			//修改订单状态
			Map order = new HashMap();
			order.put("id",orderCode);
			order.put("payWay",2);
			hotelOrderDao.updateHotelOrderState(order);
			//获取订单信息
			HotelOrder GoodsOrder = hotelOrderDao.getHotelOrderByOrderId(id);
			//推送通知
			Long siId = GoodsOrder.getShopInformationId();
			Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
			if(shopUserId != null){
				List<String> ids = new ArrayList<String>();
				ids.add(shopUserId+"");
				
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("content","您有新的订单，请注意查收！");
				extras.put("goodsType","3");
				extras.put("orderCode",GoodsOrder.getOrderCode());
				extras.put("siId",siId+"");
				extras.put("type","1");
				JPushUtils.sendPushNotification(ids,extras);
			}
			return 0;
		case 3:
			Map map1 = new HashMap();
			map1.put("integration", price);
			map1.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map1);
			//修改订单状态
			Map order1 = new HashMap();
			order1.put("id",orderCode);
			order1.put("payWay",3);
			hotelOrderDao.updateHotelOrderState(order1);
			//获取订单信息
			HotelOrder GoodsOrder1 = hotelOrderDao.getHotelOrderByOrderId(id);
			//推送通知
			Long siId1 = GoodsOrder1.getShopInformationId();
			Long shopUserId1 = shopUserDao.getShopUserIdBySiId(siId1);
			if(shopUserId1 != null){
				List<String> ids = new ArrayList<String>();
				ids.add(shopUserId1+"");
				
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("content","您有新的订单，请注意查收！");
				extras.put("goodsType","3");
				extras.put("orderCode",GoodsOrder1.getOrderCode());
				extras.put("siId",siId1+"");
				extras.put("type","1");
				JPushUtils.sendPushNotification(ids,extras);
			}
			return 0;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int payRestaurantOrder(Long id,String orderCode, Long userId, Double balance, Double price, Integer payType, String passWord) {
		switch (payType) {
		case 1:
			String userPassWord = consumerUserDao.getUserPassWordByUserId(userId);
			MD5 getMD5 = new MD5();
			if (passWord != null) {
				if (getMD5.GetMD5Code(passWord).equals(userPassWord)) {
			if(balance > price){
				balance = balance - price;
				Map map = new HashMap();
				map.put("balance", balance);
				map.put("integration", price);
				map.put("userId",userId);
				//获取订单信息
				RestaurantOrder GoodsOrder = restaurantOrderDao.getRestaurantOrderByOrderId(id);
				//更新账户余额
				userAccountDao.updateAccountByMap(map);
				//更新商家余额
					//获取商家余额
					UserAccount shua = userAccountDao.getUserAccountByUserId(GoodsOrder.getShopInformationId());
					shua.setBalance(shua.getBalance()+price);
					//更新商家
					userAccountDao.updateUserAccount(shua);
					//获取用户剩余积分
					UserAccount ua = userAccountDao.findByUserId(userId);
				//保存余额交易记录
				UserAccountLog userAccountLog = new UserAccountLog();
				userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
				userAccountLog.setName("饭店类订单");
				userAccountLog.setType(2);
				userAccountLog.setPrice(price);
				userAccountLog.setBalance(balance);
				String str = GoodsOrder.getRealPrice()+"";
				str = str.substring(0,str.indexOf("."));
				userAccountLog.setTradeIntegration(Integer.parseInt(str));
				userAccountLog.setIntegration(ua.getIntegration());
				userAccountLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				userAccountLog.setUserId(userId);
				userAccountLog.setShopId(GoodsOrder.getShopInformationId());
				userAccountLog.setExtractType(0);
				userAccountLogDao.saveUserAccountLog(userAccountLog);
				//修改订单状态
				Map order = new HashMap();
				order.put("id",orderCode);
				order.put("payWay",1);
				restaurantOrderDao.updateRestaurantOrderState(order);
				
				//推送通知
				Long siId = GoodsOrder.getShopInformationId();
				Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
				if(shopUserId != null){
					List<String> ids = new ArrayList<String>();
					ids.add(shopUserId+"");
					
					Map<String, String> extras = new HashMap<String, String>();
					extras.put("content","您有新的订单，请注意查收！");
					extras.put("goodsType", GoodsOrder.getGoodsType()+"");
					extras.put("orderCode",GoodsOrder.getOrderCode());
					extras.put("siId",siId+"");
					extras.put("type","1");
					JPushUtils.sendPushNotification(ids,extras);
				}
				
				return 0;
			}
				} else {
					return 1;
				}
			} else {
				return 2;
			}
		case 2:
			Map map = new HashMap();
			map.put("integration", price);
			map.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map);
			//修改订单状态
			Map order = new HashMap();
			order.put("id",orderCode);
			order.put("payWay",2);
			restaurantOrderDao.updateRestaurantOrderState(order);
			//获取订单信息
			RestaurantOrder GoodsOrder = restaurantOrderDao.getRestaurantOrderByOrderId(id);
			//推送通知
			Long siId = GoodsOrder.getShopInformationId();
			Long shopUserId = shopUserDao.getShopUserIdBySiId(siId);
			if(shopUserId != null){
				List<String> ids = new ArrayList<String>();
				ids.add(shopUserId+"");
				
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("content","您有新的订单，请注意查收！");
				extras.put("goodsType", GoodsOrder.getGoodsType()+"");
				extras.put("orderCode",GoodsOrder.getOrderCode());
				extras.put("siId",siId+"");
				extras.put("type","1");
				JPushUtils.sendPushNotification(ids,extras);
			}
			
			return 0;
		case 3:
			Map map1 = new HashMap();
			map1.put("integration", price);
			map1.put("userId",userId);
			//更新账户积分
			userAccountDao.updateAccountByMap(map1);
			//修改订单状态
			Map order1 = new HashMap();
			order1.put("id",orderCode);
			order1.put("payWay",3);
			restaurantOrderDao.updateRestaurantOrderState(order1);
			//获取订单信息
			RestaurantOrder GoodsOrder1 = restaurantOrderDao.getRestaurantOrderByOrderId(id);
			//推送通知
			Long siId1 = GoodsOrder1.getShopInformationId();
			Long shopUserId1 = shopUserDao.getShopUserIdBySiId(siId1);
			if(shopUserId1 != null){
				List<String> ids = new ArrayList<String>();
				ids.add(shopUserId1+"");
				
				Map<String, String> extras = new HashMap<String, String>();
				extras.put("content","您有新的订单，请注意查收！");
				extras.put("goodsType", GoodsOrder1.getGoodsType()+"");
				extras.put("orderCode",GoodsOrder1.getOrderCode());
				extras.put("siId",siId1+"");
				extras.put("type","1");
				JPushUtils.sendPushNotification(ids,extras);
			}
			return 0;
		}		
		return 0;
	}
	public List selectRestaurantOrderByOrderCode(String orderCode) {
		return restaurantPackageDao.selectRestaurantOrderByOrderCode(orderCode);
	}
	public String getPhotoOfHomePage(Integer type) {
		return pictureLibraryDao.getPhotoOfHomePage(type);
	}
	public Long getGoodsIdById(Long id) {
		return shopCartDao.getGoodsIdById(id);
	}
	public void saveUserCollect(UserCollect userCollect) {
		userCollectDao.saveUserCollect(userCollect);
	}
	public Integer getNumber(Long userId) {
		return shopCartDao.getNumber(userId);
	}
	public List<Map<String, Object>> searchGoodsByName(Map para) {
		return shopGoodsDao.searchGoodsByName(para);
	}
	public void saveUserAccountLog(UserAccountLog userAccountLog) {
		userAccountLogDao.saveUserAccountLog(userAccountLog);
	}
}
