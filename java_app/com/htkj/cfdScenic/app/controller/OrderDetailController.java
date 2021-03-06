package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.Express;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.RefundCause;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.OrderDetailService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;
import com.htrj.common.upload.UploadFile;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/interFace/orderDetail")
public class OrderDetailController extends BaseController {
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private ShopUserService shopUserService;
	/**
	 * 获取所有订单的的列表（酒店，饭店，商品）（未使用，已使用，已过期）
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/findOrder?type=1&status=1&page=1&rows=3 
	 * 参数 type(1酒店，2特产，3饭店) status(1未使用，2已使用，3已过期，4待收货（商品用）)
	 * 流程：
	 * 1，判断状态status，给payState，orderState赋值的（数据库通过payState，orderState 来查询是哪种状态的）
	 * 2，通过类型来查询订单
	 * 调用表
	 * hotel_order goods_order restaurant_order shop_information
	 */
	@RequestMapping(value = "/findOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findOrder(Integer type, Integer status,PagerForm page) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
					Map parameterMap = new HashMap();
					Map goodsMap = new HashMap();
					parameterMap.put("page",page.getPage());
					parameterMap.put("rows",page.getRows());
					goodsMap.put("page",page.getPage());
					goodsMap.put("rows",page.getRows());
					switch (status) {
					case 1: // 未使用"
						parameterMap.put("userId", userId);
						parameterMap.put("payState","0,1");
						parameterMap.put("orderState","2");

						break;
					case 2: // 已使用
						parameterMap.put("userId", userId);
						parameterMap.put("payState", "1");
						parameterMap.put("orderState", "4");
						break;
					case 3: // 已过期
						parameterMap.put("userId", userId);
						parameterMap.put("payState", "0,1");
						parameterMap.put("orderState", "5");
						break;
					}
					switch (type) {
					case 1:// 酒店
						//先更新过期的
						orderDetailService.updateHotelOverdueOrder(userId);
						
						DataGrid dataGrid =orderDetailService.findHotelOrder(page.getPageRequest(parameterMap)); 
						Integer hotelOrderCount = orderDetailService.findHotelOrderCount(parameterMap);
						orderList = dataGrid.getRows();
						for(int i=0;i<orderList.size();i++){
							String isDeliverFee = (orderList.get(i).get("is_deliver_fee"))+"";
							if(isDeliverFee.equals("0")){
							}
						}
						Map hoMap = new HashMap();
						hoMap.put("orderCount",hotelOrderCount);
						hoMap.put("orderList",orderList);
						dataGrid.setRows(orderList);
						msg.setData(dataGrid);
						msg.setHearder(0, "ok");
						break;
					case 2:// 饭店
						//先更新过期的
						orderDetailService.updateRestaurantOverdueOrder(userId);
						
						DataGrid rdataGrid = orderDetailService.findRestaurantOrder(page.getPageRequest(parameterMap));
						Integer restaurantOrderCount = orderDetailService.findRestaurantOrderCount(parameterMap);
						orderList = rdataGrid.getRows();
						for(int i=0;i<orderList.size();i++){
							String isDeliverFee = (orderList.get(i).get("is_deliver_fee"))+"";
							if(isDeliverFee.equals("0")){
							}
						}
						Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap.containsKey(orderCode)) {
								listMap.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								listMap.put(orderCode,l);
							}
						}
						List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
						Iterator<String> it = listMap.keySet().iterator();
						while(it.hasNext()){
							String key = it.next()+"";
							System.out.println(listMap.get(key));
							List<Map<String,Object>> goList = listMap.get(key);
							returnlist.add(goList);
						}
						rdataGrid.setRows(returnlist);
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("orderCount",restaurantOrderCount);
						map1.put("orderList", rdataGrid);
						msg.setData(map1);
						msg.setHearder(0, "ok");
						break;
					case 3:// 特产，小吃
						switch (status) {
						case 1: // 代付款
							goodsMap.put("userId", userId);
							goodsMap.put("payState","0");
							goodsMap.put("orderState","1");
							break;
						case 2: // 已付款
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState", "2");
							break;
						case 3: //待收货
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState","3,4");
							break;
						case 4://已完成
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState","5");
							break;
						}
						DataGrid gdataGrid = orderDetailService.findGoodsOrder(page.getPageRequest(goodsMap)); 
						Integer goodsOrderCount = orderDetailService.findGoodsOrderCount(goodsMap); 
						orderList = gdataGrid.getRows();
						Map<String,List<Map<String,Object>>> listMap1 = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap1.containsKey(orderCode)) {
								listMap1.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								listMap1.put(orderCode,l);
							}
						}
						List<List<Map<String,Object>>> returnlist1 = new ArrayList<List<Map<String,Object>>>(); 
						Iterator<String> it1 = listMap1.keySet().iterator();
						while(it1.hasNext()){
							String key = it1.next()+"";
							System.out.println(listMap1.get(key));
							List<Map<String,Object>> goList = listMap1.get(key);
							returnlist1.add(goList);
						}
						gdataGrid.setRows(returnlist1);
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("orderCount",goodsOrderCount);
						map2.put("orderList", gdataGrid);
						msg.setData(map2);
						msg.setHearder(0, "ok");
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 查看订单的详细信息
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/findOrderDetail?type=1&orderId=1 
	 * 参数 orderId(订单ID),type(订单类型（1酒店，2特产，3饭店）)
	 * 流程
	 * 1，通过type来查询订单
	 * 调用表
	 * shop_goods shop_information goods_order hotel_order restaurant_order
	 */
	@RequestMapping(value = "/findOrderDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findOrderDetail(Integer type,Long orderId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
					switch (type) {
					case 1:// 酒店
						map = orderDetailService.findHotelOrderDetail(orderId);
						break;
					case 2:// 饭店
						map = orderDetailService.findRestaurantOrderDetail(orderId);
						break;
					case 3:// 特产
						map = orderDetailService.findGoodsOrderDetail(orderId);
						break;
					}
					if (map != null) {
						msg.setData(map);
						msg.setHearder(0, "ok");
					} else {
						msg.setHearder(4, "dont have order");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * （酒店饭店）申请退款   （酒店饭店商品）确认完成 
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/backMoney?type=1&orderState=7&orderCode=1
	 * type 订单类型  orderState 订单状态  id 订单Id
	 * 流程
	 * 修改订单状态
	 * 调用表
	 * hotel_order goods_order restaurant_order
	 */
	@RequestMapping(value = "/backMoney", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String backMoney(Integer type,Integer orderState,Long orderCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String, Number> map = new HashMap<String, Number>();
					map.put("orderState",orderState);
					map.put("orderCode",orderCode);
					switch (type) {
					case 1:// 酒店
						orderDetailService.hotelBackMoney(map);
						break;
					case 2:// 饭店
						orderDetailService.restaurantBackMoney(map);
						break;
					case 3:// 特产
						orderDetailService.goodsBackMoney(map);
						break;
					}
						msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商品申请退款 + 退款原因
	 */
	@RequestMapping(value = "/refundCauseInfo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refundCauseInfo(Integer orderState,Long orderCode,String name,String telPhone,String cause){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Long siId = orderDetailService.getShopInformation(orderCode);
					RefundCause refundCause = new RefundCause();
					refundCause.setCause(cause);
					refundCause.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					refundCause.setId(GenerateSequenceUtil.getUniqueId());
					refundCause.setOrderCode(orderCode);
					refundCause.setUserId(userId);
					refundCause.setUserName(name);
					refundCause.setShopInformationId(siId);
					refundCause.setUserPhone(telPhone);
					orderDetailService.saveRefundCause(refundCause);
					Map<String, Number> map = new HashMap<String, Number>();
					map.put("orderState",orderState);
					map.put("orderCode",orderCode);
					orderDetailService.goodsBackMoney(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 删除订单 
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/backMoney?type=1&orderState=7&orderCode=1
	 * type 订单类型  orderState 订单状态  id 订单Id
	 * 流程
	 * 修改订单状态
	 * 调用表
	 * hotel_order goods_order restaurant_order
	 */
	@RequestMapping(value = "/deleteMyTickets", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteMyTickets(Integer type,Integer orderState,Long orderCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					switch (type) {
					case 1:// 酒店
						if(orderState == 1){
							orderDetailService.deleteHotelOrder(orderCode);
						}else{
							orderDetailService.updateHotelOrder(orderCode);
						}
						break;
					case 2:// 饭店
						if(orderState == 1){
							orderDetailService.deleteRestaurantOrder(orderCode);
						}else{
							orderDetailService.updateRestaurantOrder(orderCode);
						}
						break;
					case 3:// 商品
						if(orderState == 1){
							orderDetailService.deleteGoodsOrder(orderCode);
						}else{
							orderDetailService.updateGoodsOrderByOrderCode(orderCode);
						}
						break;
					}
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 *  评论
	 *  http://localhost:8080/cfdScenic/interFace/orderDetail/saveUserComment?linkId=1&content=好好吃&satisfyState=3&haveImg=0&memo=备注&isTravels=0
	 *  参数 
	 *  传参：linkId content satisfyState haveImg memo isTravels
	 *  默认：id user_id comment_type create_time
	 */
	@RequestMapping(value = "/saveUserComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveUserComment(UserComment userComment){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Long id = GenerateSequenceUtil.getUniqueId();
					userComment.setId(id);
					userComment.setUserId(userId);
					userComment.setCommentType(2);
					userComment.setCreateTime(sdf.format(new Date()));
					orderDetailService.saveUserComment(userComment);
					msg.setData(id);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 *  评论	-- 上传图片
	 *  http://localhost:8080/cfdScenic/interFace/orderDetail/saveUserComment?name&picDescribe&linkId&type&memo
	 *  参数 
	 *  传参：linkId content satisfyState haveImg memo isTravels
	 *  默认：id user_id comment_type create_time
	 */
	@RequestMapping(value = "/uploadPic", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadPic(@RequestParam("file") MultipartFile[] imageFiles,HttpServletRequest request,HttpServletResponse response,PictureLibrary pictureLibrary){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					UploadFile uploadFile=new UploadFile();
					List<Map<String, String>> uploadImage = uploadFile.uploadImage(imageFiles,userId+"",request,response);
					for(Map<String, String> img : uploadImage){
						String contextUrl = img.get("contextUrl");
						pictureLibrary.setCreateTime(sdf.format(new Date()));
						pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
						pictureLibrary.setImgRootUrl(contextUrl);
						pictureLibrary.setImgUrl(contextUrl);
						orderDetailService.saveCommentPic(pictureLibrary);
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:我的退款订单
	 * @author:lishilong
	 * @date:2016年9月27日
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/refundOrder?type=1&status=1&page=1&rows=3 
	 */
	@RequestMapping(value = "/refundOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String refundOrder(Integer type,@RequestParam(value="status",required=false)Integer status,PagerForm page){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
					Map parameterMap = new HashMap();
					Map goodsMap = new HashMap();
					parameterMap.put("page",page.getPage());
					parameterMap.put("rows",page.getRows());
					goodsMap.put("page",page.getPage());
					goodsMap.put("rows",page.getRows());
					//酒店饭店状态
					parameterMap.put("userId", userId);
					parameterMap.put("payState","0,1");
					parameterMap.put("orderState","3,6,7");
					switch (type) {
					case 1:// 酒店
						DataGrid dataGrid =orderDetailService.findHotelOrder(page.getPageRequest(parameterMap)); 
						orderList = dataGrid.getRows();
						int orderCount3 = 0;
						for(int i=0;i<orderList.size();i++){
							orderCount3 += 1;
						}
						Map hoMap = new HashMap();
						hoMap.put("orderCount",orderCount3);
						hoMap.put("orderList",dataGrid);
						msg.setData(hoMap);
						msg.setHearder(0, "ok");
						break;
					case 2:// 饭店
						DataGrid rdataGrid =orderDetailService.findRestaurantOrder(page.getPageRequest(parameterMap)); 
						orderList = rdataGrid.getRows();
						int orderCount = 0;
						for(int i=0;i<orderList.size();i++){
							orderCount += 1;
						}
						Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap.containsKey(orderCode)) {
								listMap.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								listMap.put(orderCode,l);
							}
						}
						List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
						Iterator<String> it = listMap.keySet().iterator();
						while(it.hasNext()){
							String key = it.next()+"";
							System.out.println(listMap.get(key));
							List<Map<String,Object>> goList = listMap.get(key);
							returnlist.add(goList);
						}
						rdataGrid.setRows(returnlist);
						Map<String, Object> map1 = new HashMap<String, Object>();
						map1.put("orderCount",orderCount);
						map1.put("orderList", rdataGrid);
						msg.setData(map1);
						msg.setHearder(0, "ok");
						break;
					case 3:// 特产，小吃
						switch (status) {
						case 1: // 待审核
							goodsMap.put("userId", userId);
							goodsMap.put("payState","0,1");
							goodsMap.put("orderState","6,10");
							break;
						case 2: // 审核通过
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState", "7,11");
							break;
						case 3: //退款中
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState","8");
							break;
						case 4://退款完成
							goodsMap.put("userId", userId);
							goodsMap.put("payState", "1");
							goodsMap.put("orderState","9");
							break;
						}
						DataGrid gdataGrid = orderDetailService.findGoodsOrder(page.getPageRequest(goodsMap)); 
						Integer goodsOrderCount = orderDetailService.findRefundGoodsOrderCount(goodsMap);
						orderList = gdataGrid.getRows(); 
						int orderCount1 = 0;
						Map<String,List<Map<String,Object>>> listMap1 = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap1.containsKey(orderCode)) {
								listMap1.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								Map<String,Object> isDeliverFee = orderDetailService.getIsDeliverFeeByOrderCode(orderCode);
								l.add(isDeliverFee);
								listMap1.put(orderCode,l);
								orderCount1 += 1;
							}
						}
						List<List<Map<String,Object>>> returnlist1 = new ArrayList<List<Map<String,Object>>>(); 
						Iterator<String> it1 = listMap1.keySet().iterator();
						while(it1.hasNext()){
							String key = it1.next()+"";
							System.out.println(listMap1.get(key));
							List<Map<String,Object>> goList = listMap1.get(key);
							returnlist1.add(goList);
						}
						gdataGrid.setRows(returnlist1);
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("orderCount",goodsOrderCount);
						map2.put("orderList", gdataGrid);
						msg.setData(map2);
						msg.setHearder(0, "ok");
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/**
	 * 商户端确认发货
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/saveExpressOfUser
	 */
	@RequestMapping(value = "/saveExpressOfUser", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveExpressOfUser(Integer orderState,Long orderCode,String expressName,String expressCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String, Number> map = new HashMap<String, Number>();
					//创建快递数据
					Express express = new Express();
					express.setId(GenerateSequenceUtil.getUniqueId());
					express.setCreateDate(sdf.format(new Date()));
					express.setUserId(userId);
					express.setExpressName(expressName);
					express.setExpressCode(expressCode);
					express.setOrderCode(orderCode);
					orderDetailService.saveExpress(express);
					map.put("orderCode",orderCode);
					map.put("orderState",orderState);
					orderDetailService.goodsBackMoney(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * 商品版--订单中心--订单列表
	 * post
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/shopOrderList
	 *
	 * 请求参数

		status			//状态(1代发货2已完成3已发货)
	 */
	@RequestMapping(value="shopOrderList",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderList(Integer status,PagerForm page){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		if(token!=null){
			if(userId!=null){
				
				ShopUser user=	shopUserService.selectByUserId(userId);
				Long shopInformationId=user.getShopInformationId();
				map.put("sfId", shopInformationId);
				map.put("page", page.getPage());
				map.put("rows", page.getRows());
				
				switch (status) {
				//代发货
				case 1:
					map.put("status","2");
					break;
				//已完成
				case 2:
					map.put("status","5");
					break;
				//已发货
				case 3:
					map.put("status","3");
					break;
				}
				try {
					//获取订单列表
					DataGrid dataGrid = orderDetailService.findShopGoodsOrder(page.getPageRequest(map));
					Integer shopGoodsOrderCount = orderDetailService.findShopGoodsOrderCount(map);
					List<Map<String,Object>> orderList = dataGrid.getRows();
					int orderCount = 0;
					for(int i=0;i<orderList.size();i++){
						orderCount += 1;
					}
					Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
					for (int i = 0; i < orderList.size(); i++) {
						Map<String,Object> go = orderList.get(i);
						String orderCode = go.get("order_code")+"";
						if (listMap.containsKey(orderCode)) {
							listMap.get(orderCode).add(go);
						} else {
							List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
							l.add(go);
							listMap.put(orderCode,l);
						}
					}
					List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
					Iterator<String> it = listMap.keySet().iterator();
					while(it.hasNext()){
						String key = it.next()+"";
						System.out.println(listMap.get(key));
						List<Map<String,Object>> goList = listMap.get(key);
						returnlist.add(goList);
					}
					dataGrid.setRows(returnlist);
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("orderCount",shopGoodsOrderCount);
					map1.put("orderList", dataGrid);
					orderList.add(map1);
					msg.setHearder(0, "success");
					if(map1 != null)
					{
						msg.setData(map1);
					}else{
						msg.setData(new HashMap<Object, Object>());
					}
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		String json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	/*
	 * 商品版--订单中心--订单列表
	 * post
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/shopOrderChange
	 *
	 * 请求参数

		status			//状态(1确认发货2确认完成)
	 */
	@RequestMapping(value="shopOrderChange",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopOrderChange(Integer status,String orderCode){
		String token = webContext.getRequest().getHeader("Authorization");
		Long userId = shopUserService.getShopUserIdByToken(token);
		Map<String,Object> map=new HashMap<String,Object>();
		ResponseMsg msg = new ResponseMsg();
		if(token!=null){
			if(userId!=null){
				map.put("orderCode", orderCode);
				switch (status) {
				case 1:
					map.put("status","11");
					break;
				case 2:
					map.put("status","5");
					break;
				}
				
				try {
					//获取订单列表
					 orderDetailService.shopOrderChange(map);
					msg.setHearder(0, "success");
					
				} catch (Exception e) {
					e.printStackTrace();
					msg.setHearder(1, "error");
				}
			}else{
				msg.setHearder(3, "异地登入!");
			}
		}else{
			msg.setHearder(2, "认证失败,请重新登入!");
		}
		String json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 查看订单的详细信息
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/informationFindOrderDetail
	 * 参数 orderId(订单ID),type(订单类型（1酒店，2饭店，3特产）)
	 * 流程
	 * 1，通过type来查询订单
	 * 调用表
	 * shop_goods shop_information goods_order hotel_order restaurant_order
	 */
	@RequestMapping(value = "/informationFindOrderDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String informationFindOrderDetail(Integer type,Long informationId,Long orderCode) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
					Map paraMap = new HashMap();
					paraMap.put("informationId",informationId);
					paraMap.put("orderCode",orderCode);
					switch (type) {
					case 1:// 酒店
						map = orderDetailService.informationHotelOrderDetail(paraMap);
						for(int i = 0 ; i < map.size();i++ ){
							Long hotelOrderId = Long.parseLong(((map.get(i).get("id"))+""));
							List<String> personName = orderDetailService.getPersonName(hotelOrderId);
							map.get(i).put("personName",personName);
						}
						if (map != null) {
							msg.setData(map);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					case 2:// 饭店
						map = orderDetailService.informationRestaurantOrderDetail(paraMap);
						if (map != null) {
							msg.setData(map);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					case 3:// 特产
						map = orderDetailService.informationGoodsOrderDetail(paraMap);
						Map exMap = new HashMap();
						exMap.put("userId",userId);
						exMap.put("orderCode",orderCode);
						Map<String,String> express = new HashMap<String,String>();
						express = orderDetailService.getExpressInfo(exMap);
						if(express == null){
							express = new HashMap<String,String>();
							express.put("express_name","");
							express.put("express_code","");
							express.put("expressCreateDate","");
						}
						Map returnMap = new HashMap();
						returnMap.put("map",map);
						returnMap.put("express",express);
						if (returnMap != null) {
							msg.setData(returnMap);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/siBackMoney?type=1&orderState=7&orderCode=1&siId=店铺ID
	 * type 订单类型  orderState 订单状态  id 订单Id
	 * 流程
	 * 修改订单状态
	 * 调用表
	 * hotel_order goods_order restaurant_order
	 */
	@RequestMapping(value = "/siBackMoney", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String siBackMoney(Integer type,Integer orderState,Long orderCode,Long siId){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String, Number> map = new HashMap<String, Number>();
					map.put("orderState",orderState);
					map.put("orderCode",orderCode);
					map.put("siId",siId);
					switch (type) {
					case 1:// 酒店
						orderDetailService.hotelBackMoney(map);
						break;
					case 2:// 饭店
						orderDetailService.restaurantBackMoney(map);
						break;
					case 3:// 特产
						orderDetailService.goodsBackMoney(map);
						break;
					}
						msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商户端确认发货
	 */
	@RequestMapping(value = "/saveExpress", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveExpress(Integer orderState,Long orderCode,Long siId,String expressName,String expressCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String, Number> map = new HashMap<String, Number>();
					//创建快递数据
					Express express = new Express();
					express.setId(GenerateSequenceUtil.getUniqueId());
					express.setCreateDate(sdf.format(new Date()));
					express.setUserId(userId);
					express.setExpressName(expressName);
					express.setExpressCode(expressCode);
					express.setOrderCode(orderCode);
					orderDetailService.saveExpress(express);
					map.put("orderCode",orderCode);
					map.put("siId",siId);
					map.put("orderState",orderState);
					orderDetailService.goodsBackMoney(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 *  http://localhost:8080/cfdScenic/interFace/orderDetail/updateGoodsOrder 
	 */
	@RequestMapping(value = "/updateGoodsOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateGoodsOrder(GoodsOrder goodsOrder){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					orderDetailService.updateGoodsOrder(goodsOrder);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商铺端 - 退款订单
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/shopRefundORder 
	 */
	@RequestMapping(value = "/shopRefundORder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopRefundORder(Long siId,Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map paraMap = new HashMap();
					paraMap.put("siId",siId);
					switch (type) {
					case 1:	//待审核
						paraMap.put("orderState","6,10");
						orderList = orderDetailService.shopFindGoodsOrder(paraMap);
						break;
					case 2:	//收货中
						paraMap.put("orderState","7,8,11");
						orderList = orderDetailService.shopFindGoodsOrder(paraMap);
						break;
					case 3:	//已退款
						paraMap.put("orderState","9");
						orderList = orderDetailService.shopFindGoodsOrder(paraMap);
						break;
					}
					int orderCount1 = 0;
					Map<String,List<Map<String,Object>>> listMap1 = new HashMap<String,List<Map<String,Object>>>();
					for (int i = 0; i < orderList.size(); i++) {
						Map<String,Object> go = orderList.get(i);
						String orderCode = go.get("order_code")+"";
						if (listMap1.containsKey(orderCode)) {
							listMap1.get(orderCode).add(go);
						} else {
							List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
							l.add(go);
							listMap1.put(orderCode,l);
							orderCount1 += 1;
						}
					}
					List<List<Map<String,Object>>> returnlist1 = new ArrayList<List<Map<String,Object>>>(); 
					Iterator<String> it1 = listMap1.keySet().iterator();
					while(it1.hasNext()){
						String key = it1.next()+"";
						System.out.println(listMap1.get(key));
						List<Map<String,Object>> goList = listMap1.get(key);
						returnlist1.add(goList);
					}
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("orderCount",orderCount1);
					map2.put("orderList", returnlist1);
					msg.setData(map2);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 查看退款订单的详细信息
	 * http://localhost:8080/cfdScenic/interFace/orderDetail/findOrderDetail?type=1&orderId=1 
	 * 参数 orderId(订单ID),type(订单类型（1酒店，2特产，3饭店）)
	 * 流程
	 * 1，通过type来查询订单
	 * 调用表
	 * shop_goods shop_information goods_order hotel_order restaurant_order
	 */
	@RequestMapping(value = "/shopFindOrderDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findOrderDetail(Integer type,Long orderCode,Long siId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> map = new ArrayList<Map<String,Object>>();
					Map para = new HashMap();
					para.put("siId",siId);
					para.put("orderCode",orderCode);
					switch (type) {
					case 1:// 酒店
						map = orderDetailService.shopFindHotelOrderDetail(para);
						if (map != null) {
							msg.setData(map);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					case 2:// 饭店
						map = orderDetailService.shopFindRestaurantOrderDetail(para);
						if (map != null) {
							msg.setData(map);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					case 3:// 特产
						map = orderDetailService.shopFindGoodsOrderDetail(para);
						RefundCause refundCause =  orderDetailService.getRefundCause(para);
						//查询用户的订单信息
						Map expressMap = new HashMap();
						if(map.size() >0 && map.get(0) != null){
						expressMap.put("userId",map.get(0).get("user_id"));
						expressMap.put("orderCode",map.get(0).get("order_code"));
						}
						Map express = orderDetailService.getExpressInfo(expressMap);
						if(express == null){
							express = new HashMap();
							express.put("express_name","");
							express.put("express_code","");
							express.put("expressCreateDate","");
						}
						Map refund = new HashMap();
						refund.put("express",express);
						refund.put("refundCause",refundCause);
						refund.put("map",map);
						if (refund != null) {
							msg.setData(refund);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "dont have order");
						}
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商铺 - 订单退钱
	 * interFace/orderDetail/shopRefundFinsh
	 * （酒店，饭店，商品）
	 */
	@RequestMapping(value = "/shopRefundFinsh", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String shopRefundFinsh(Long shopUserId,Long useId,Double balance,Long siId,Long orderCode,Integer orderState,Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					//更新商铺的余额
					UserAccount shopUserAccount = orderDetailService.getUserAccount(shopUserId);
					if(shopUserAccount.getBalance() > balance){
					UserAccount shop = new UserAccount();
					shop.setBalance(shopUserAccount.getBalance() - balance);
					shop.setUserId(shopUserId);
					orderDetailService.updateUserAccount(shop);
					//更新用户的余额
					UserAccount userAccount = orderDetailService.getUserAccount(useId);
					UserAccount user = new UserAccount();
					user.setBalance(userAccount.getBalance() + balance);
					orderDetailService.updateUserAccount(user);
					//修改订单的状态
					Map map = new HashMap();
					map.put("orderCode",orderCode);
					map.put("siId",siId);
					map.put("orderState",orderState);
					switch (type) {
					case 1:	//酒店
						orderDetailService.hotelBackMoney(map);
						break;
					case 2:	//饭店
						orderDetailService.restaurantBackMoney(map);
						break;
					case 3:	//商品
						orderDetailService.goodsBackMoney(map);
						break;
					}
					msg.setHearder(0,"ok");
					}else{
						msg.setHearder(4,"余额不足");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 账户统计
	 * interFace/orderDetail/orderCount   Long siId,Integer type(1酒店2饭店3特产),String createTime
	 * （酒店，饭店，商品）
	 */
	@RequestMapping(value = "/orderCount", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String orderCount(Long siId,Integer type,String createTime){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					//返回数据
					Map returnMap = new HashMap();
					//查询条件
					Map para = new HashMap();
					para.put("siId",siId);
					para.put("createTime",createTime);
					//按照时间来查询总数
					Map<String,Object> today = orderDetailService.getTodayMoney(para,type);
					//查询总数
					Map para1 = new HashMap();
					para1.put("siId",siId);
					Map<String,Object> all = orderDetailService.getTodayMoney(para1,type);
					returnMap.put("all", all);
					returnMap.put("today", today);
					List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
					List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
					Map map = new HashMap();
					map.put("siId",siId);
					map.put("createTime",createTime);
					switch (type) {
					case 1:
						orderList = orderDetailService.findHotelOrderNotIn(map);
						returnMap.put("orderList", orderList);
						break;
					case 2:
						orderList = orderDetailService.findRestaurantOrderNotIn(map);
						Map<String,List<Map<String,Object>>> listMap = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap.containsKey(orderCode)) {
								listMap.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								listMap.put(orderCode,l);
							}
						}
						Iterator<String> it = listMap.keySet().iterator();
						while(it.hasNext()){
							String key = it.next()+"";
							System.out.println(listMap.get(key));
							List<Map<String,Object>> goList = listMap.get(key);
							returnlist.add(goList);
						}
						returnMap.put("orderList", returnlist);
						break;
					case 3:
						//获取订单列表
						orderList = orderDetailService.findShopGoodsOrderNotIn(map);
						Map<String,List<Map<String,Object>>> listMap1 = new HashMap<String,List<Map<String,Object>>>();
						for (int i = 0; i < orderList.size(); i++) {
							Map<String,Object> go = orderList.get(i);
							String orderCode = go.get("order_code")+"";
							if (listMap1.containsKey(orderCode)) {
								listMap1.get(orderCode).add(go);
							} else {
								List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
								l.add(go);
								listMap1.put(orderCode,l);
							}
						}
						Iterator<String> it1 = listMap1.keySet().iterator();
						while(it1.hasNext()){
							String key = it1.next()+"";
							System.out.println(listMap1.get(key));
							List<Map<String,Object>> goList = listMap1.get(key);
							returnlist.add(goList);
						}
						returnMap.put("orderList", returnlist);
						break;
					}
					msg.setData(returnMap);
					msg.setHearder(0,"ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 订单搜索
	 * interFace/orderDetail/selectOrder   Long siId,Integer type(1酒店2饭店3特产),String orderCode,String name
	 * （酒店，饭店，商品）
	 */
	@RequestMapping(value = "/selectOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectOrder(Long siId,Integer type,String orderCode,String name){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = shopUserService.getShopUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					//返回数据
					Map returnMap = new HashMap();
					//查询条件
					Map para = new HashMap();
					para.put("siId",siId);
					if(orderCode != null && orderCode!=""){
						para.put("orderCode",orderCode);
					}
					if(name != null && name != ""){
						para.put("name",name);
					}
					List<Map<String,Object>> orderList = new ArrayList<Map<String,Object>>();
					List<List<Map<String,Object>>> returnlist = new ArrayList<List<Map<String,Object>>>(); 
					switch (type) {
					case 1:
						orderList = orderDetailService.selectHotelOrder(para);
						returnMap.put("orderList", orderList);
						break;
					case 2:
						orderList = orderDetailService.selectRestaurantOrder(para);
							Map<String, List<Map<String, Object>>> listMap = new HashMap<String, List<Map<String, Object>>>();
							for (int i = 0; i < orderList.size(); i++) {
								Map<String, Object> go = orderList.get(i);
								String copyOrderCode = orderList.get(i).get("order_code") + "";
								if (listMap.containsKey(copyOrderCode)) {
									listMap.get(copyOrderCode).add(go);
								} else {
									List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
									l.add(go);
									listMap.put(copyOrderCode, l);
								}
							}
							Iterator<String> it = listMap.keySet().iterator();
							while (it.hasNext()) {
								String key = it.next() + "";
								System.out.println(listMap.get(key));
								List<Map<String, Object>> goList = listMap.get(key);
								returnlist.add(goList);
							}
						
						returnMap.put("orderList", returnlist);
						break;
					case 3:
						//获取订单列表
						orderList = orderDetailService.selectGoodsOrder(para);
							Map<String,List<Map<String,Object>>> listMap1 = new HashMap<String,List<Map<String,Object>>>();
							for (int i = 0; i < orderList.size(); i++) {
								Map<String,Object> go = orderList.get(i);
								String copyOrderCode = orderList.get(i).get("order_code") + "";
								if (listMap1.containsKey(copyOrderCode)) {
									listMap1.get(copyOrderCode).add(go);
								} else {
									List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
									l.add(go);
									listMap1.put(copyOrderCode,l);
								}
							}
							Iterator<String> it1 = listMap1.keySet().iterator();
							while(it1.hasNext()){
								String key = it1.next()+"";
								System.out.println(listMap1.get(key));
								List<Map<String,Object>> goList = listMap1.get(key);
								returnlist.add(goList);
							}
						returnMap.put("orderList", returnlist);
						break;
					}
					msg.setData(returnMap);
					msg.setHearder(0,"ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
}
