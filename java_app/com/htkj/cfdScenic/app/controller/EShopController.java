package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.OrderPerson;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htkj.cfdScenic.app.model.ShopCart;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.UserCollect;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.EShopService;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.JPushUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/interFace/eShop")
public class EShopController extends BaseController {

	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private EShopService eShopService;

	/**
	 * 商城 - 模糊搜索
	 * http://localhost:8080/cfdScenic/interFace/eShop/linkSelect?keyWord=铁板大虾
	 * 
	 * @return 根据keyWord模糊查询(商品表,商户信息)的name和id返回给前端 调用表 shop_user,shop_goods
	 */
	@RequestMapping(value = "/linkSelect", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String linkSelect(String keyWord) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
				Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
					map = eShopService.linkSelect(keyWord);
					msg.setData(map);
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 查询商城轮播图 http://localhost:8080/cfdScenic/interFace/eShop/findPic
	 * 流程 1,通过type，state，source查询商城的轮播图 调用表 advertisement
	 */
	@RequestMapping(value = "/findPic", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findPic() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
				List<Map<String, String>> list = new ArrayList<Map<String, String>>();
					list = eShopService.findPic();
					if(list.size()>0&&list.get(0)!=null)
					{
						msg.setData(list);
					}
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 为你推荐 http://localhost:8080/cfdScenic/interFace/eShop/selectRecommend
	 * 
	 * @return 流程 1,通过state(状态)，is_recomment(是否推荐)查询店铺信息表 调用表 shop_goods
	 */
	@RequestMapping(value = "/selectRecommend", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectRecommend() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					list = eShopService.selectRecommend();
					if(list.size()>0&&list.get(0)!=null)
					{
						msg.setData(list);
					}
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 查询全部分类 http://localhost:8080/cfdScenic/interFace/eShop/getType
	 * 
	 * @return 流程 1,在service层，查询出所有的分类 2,通过分类的id查询出所有的店铺信息 调用表
	 *         shop_information,shops_group
	 */
	@RequestMapping(value = "/getType", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getType() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = eShopService.getType();
			if (list.size() > 0 && list.get(0) != null) {
				msg.setData(list);
			}
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 查询全部分类 - 查询店铺
	 * http://localhost:8080/cfdScenic/interFace/eShop/selectById?informationId=
	 * 2
	 * 
	 * @return 流程 1,通过id查询店铺的详细信息 调用表 shop_information
	 */
	@RequestMapping(value = "/selectById", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectById(Long informationId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
				Map shopInformation = new HashMap();
				shopInformation = eShopService.selectById(informationId);
				msg.setData(shopInformation);
				msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 购物车列表
	 * http://localhost:8080/cfdScenic/interFace/eShop/getShopCartByUserId
	 * 
	 * @return 流程 1,通过userId查询出自己的购物车 2,关联查询出商家名称，购物车信息 调用表 shop_cart shop_goods
	 *         shop_user
	 */
	@RequestMapping(value = "/getShopCartByUserId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getShopCartByUserId() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				List shopCart = new ArrayList();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					shopCart = eShopService.getShopCartGoodsByUserId(userId);
					if(shopCart.size()>0&&shopCart.get(0)!=null)
					{
						msg.setData(shopCart);
					}else{
						msg.setData(new ArrayList());
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
	 * 商城 - 购物车 - 添加商品
	 * http://localhost:8080/cfdScenic/interFace/eShop/saveShopCart?shopInformationId=1&number=2
	 * 
	 * @return 流程 1,插入一条购物车数据 调用表 shop_cart
	 */
	@RequestMapping(value = "/saveShopCart", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveShopCart(Long shopInformationId, Integer number, Long shopId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Long id = GenerateSequenceUtil.getUniqueId();
					ShopCart shopCart = new ShopCart();
					shopCart.setId(id);
					shopCart.setUserId(userId);
					shopCart.setShopInformationId(shopInformationId);
					shopCart.setNumber(number);
					shopCart.setShopId(shopId);
					shopCart.setState(0);
					eShopService.saveShopCart(shopCart);
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
	 * 商城 - 购物车 - 修改商品数量
	 * http://localhost:8080/cfdScenic/interFace/eShop/updateShopCart?id=
	 * 1607300243052900&number=66
	 * 
	 * @return 流程 1,通过id更新数据的数量字段 调用表 shop_cart
	 */
	@RequestMapping(value = "/updateShopCart", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateShopCart(Long id, Integer number) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					eShopService.updateShopCart(id, number);
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
	 * 商城 - 购物车 - 删除商品
	 * http://localhost:8080/cfdScenic/interFace/eShop/deleteShopCartById?ids=3,
	 * 4
	 * 
	 * @return 流程 1,通过id删除数据 调用表 shop_cart
	 */
	@RequestMapping(value = "/deleteShopCartById", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteShopCartById(String ids) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					eShopService.deleteShopCartById(ids);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "delete error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商城 - 创建订单 (点击去支付调用)
	 * http://localhost:8080/cfdScenic/interFace/eShop/clear?goodsOrderJson=[{name:'测试订单',orderDescribe:'测试订单详细',price:200,deliverDate:'2016-07-08',quantity:20,realPrice:4000,goodsId:1,shopInformationId:3,deliverFee:20,isPickup:1,addressId:1},{name:'测试订单1',orderDescribe:'测试订单详细1',price:2001,deliverDate:2016-07-25,quantity:21,realPrice:400001,goodsId:1,shopInformationId:3,deliverFee:20,isPickup:0,addressId:1}]
	 * 传参(name,orderDescribe,price,deliverDate,quantity,realPrice,
	 *  goodsId,shopId,deliverFee,isPickup)
	 *  默认(id,payWay,payState,orderState,createTime,payTime,refundTime,
	 *  userId,orderCode,isComment,addressId) 
	 *1,先生成订单
	 * 调用表 shop_cart
	 */
	@RequestMapping(value = "/createGoodsOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createGoodsOrder(String goodsOrderJson,@RequestParam(value="scIds",required=false)String scIds) {
		System.out.println(goodsOrderJson);
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				Map map = new HashMap();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！"); 
				} else {
						List<GoodsOrder> goodsOrderList = JSONArray.toList(JSONArray.fromObject(goodsOrderJson),new GoodsOrder(), new JsonConfig());
						//list分组
						Map<String,List<GoodsOrder>> listMap = new HashMap<String,List<GoodsOrder>>();
					for (int i = 0; i < goodsOrderList.size(); i++) {
						GoodsOrder go = goodsOrderList.get(i);
						String siId = go.getShopInformationId()+"";
						if (listMap.containsKey(siId)) {
							listMap.get(siId).add(go);
						} else {
							List<GoodsOrder> l = new ArrayList<GoodsOrder>();
							l.add(go);
							listMap.put(siId, l);
						}
					}
						List<List<GoodsOrder>> returnlist = new ArrayList<List<GoodsOrder>>(); 
						Iterator it = listMap.keySet().iterator();
						while(it.hasNext()){
							String key = it.next()+"";
							System.out.println(listMap.get(key));
							List<GoodsOrder> goList = listMap.get(key);
							String orderNumber = GenerateSequenceUtil.getUniqueId()+"";
							
							Double deliverFee = 0D;
							Double allPrice = 0D;
							Integer count = 0;
							
							for(int go=0;go<goList.size();go++){
								goList.get(go).setId(GenerateSequenceUtil.getUniqueId());
								goList.get(go).setPayWay(0);
								goList.get(go).setPayState(0);
								goList.get(go).setOrderState(1);
								goList.get(go).setCreateTime(sdf.format(new Date()));
								goList.get(go).setUserId(userId);
								goList.get(go).setOrderCode(orderNumber);
								goList.get(go).setIsComment(0);
								goList.get(go).setIsDeliverFee(0);
								
								//判断如果是自提的话，配送费为0
								Integer isPickup = goList.get(go).getIsPickup();
								if(isPickup == 1){
									goList.get(go).setDeliverFee(0D);
								}
								
								eShopService.saveGoodsOrder(goList.get(go));
								deliverFee += goList.get(go).getDeliverFee();
								allPrice += goList.get(go).getRealPrice();
								count += 1;
							}
							if(goList.size() >0 && goList.get(0) != null){
								GoodsOrder addGo = new GoodsOrder();
								addGo.setId(GenerateSequenceUtil.getUniqueId());
								addGo.setName("总和");
								addGo.setOrderDescribe("总和");
								addGo.setDeliverDate(goList.get(0).getDeliverDate());
								addGo.setQuantity(count);
								addGo.setPayWay(goList.get(0).getPayWay());
								addGo.setPayState(goList.get(0).getPayState());
								addGo.setOrderState(goList.get(0).getOrderState());
								addGo.setCreateTime(goList.get(0).getCreateTime());
								addGo.setUserId(userId);
								addGo.setPrice(0D);
								addGo.setRealPrice(allPrice);
								addGo.setOrderCode(orderNumber);
								addGo.setIsComment(goList.get(0).getIsComment());
								addGo.setGoodsId(goList.get(0).getGoodsId());
								addGo.setShopInformationId(goList.get(0).getShopInformationId());
								addGo.setAddressId(goList.get(0).getAddressId());
								addGo.setDeliverFee(deliverFee);
								addGo.setIsPickup(goList.get(0).getIsPickup());
								addGo.setIsUpdatePrice(goList.get(0).getIsUpdatePrice());
								addGo.setIsDeliverFee(1);
								eShopService.saveGoodsOrder(addGo);
								goList.add(addGo);
							}
							returnlist.add(goList);
						}
						if(scIds != null){
							eShopService.deleteShopCartById(scIds);
						}
						msg.setData(returnlist);
						msg.setHearder(0, "ok");
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
	 * 商城 - 购物车 - 收货地址
	 * http://localhost:8080/cfdScenic/interFace/eShop/address
	 * 查询收货地址 根据创建时间倒序查询最新的收货地址
	 */
	@RequestMapping(value = "/address", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String address() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				Map map = new HashMap();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					map = eShopService.getUserAdderssByUserId(userId);
					if(map != null){
						msg.setData(map);
						msg.setHearder(0,"ok");
					}else{
						msg.setHearder(4, "address is null");
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
	 * 商城 - 购物车 - 订单回显
	 * http://localhost:8080/cfdScenic/interFace/eShop/findGoodsOrderById?ids=1607310339075530,1607310339076040 
	 * 流程 1,支付的时候通过id查询订单的信息(多条) 调用表
	 * goods_order
	 */
	@RequestMapping(value = "/findGoodsOrderById", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findGoodsOrderById(String orderCode) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {	
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					map.put("orderCode",orderCode);
					List list = eShopService.findGoodsOrderById(map);
					
					if(list.size()>0&&list.get(0)!=null)
					{
						msg.setData(list);
					}
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "select error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 特产商店列表 - 分页查询 - 2
	 * http://localhost:8080/cfdScenic/interFace/eShop/findFeatureTwo?page=1&rows=3
	 * 
	 * @return 流程： (分类表是写死的) 分类表id为4的是特产，通过店铺表的分类id来查询店铺 调用表 shop_information
	 */
	@RequestMapping(value = "/findFeatureTwo", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findFeatureTwo(PageCount page) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
				Map parameter = new HashMap();
				parameter.put("shopId", 3);
				parameter.put("page",page.getPage());
				parameter.put("rows",page.getRows());
				List<Map<String,Object>> list = eShopService.selectShopInformationPage(parameter);
				if(list.size()>0 && list.get(0) != null){
					msg.setData(list);
					msg.setHearder(0, "ok");
				}else{
					msg.setHearder(4, "内容为空！");
				}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "delete error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 特产小吃商品列表 - 分页查询  2
	 * http://localhost/cfdScenic/interFace/eShop/findFeatureShopTwo?informationId=4&page=1&rows=3&type=1
	 * 
	 * 参数 type (2销量,0价格升序,1价格降序)
	 * @return 流程 1,通过店铺的ID查询商品信息 调用表 shop_goods
	 */
	@RequestMapping(value = "/findFeatureShopTwo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findFeatureShopTwo(Long informationId, int type,PageCount page) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map parameter = new HashMap();
			parameter.put("informationId", informationId);
			parameter.put("page",page.getPage());
			parameter.put("rows",page.getRows());
			parameter.put("type",type);
			List<Map<String,Object>> pageList  = eShopService.selectShopGoodsByInformationIdPage(parameter);
			if(pageList.size()>0&&pageList.get(0) != null){
				msg.setData(pageList);
				msg.setHearder(0, "ok");
			}else{
				msg.setHearder(4,"内容为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "delete error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 特产商品 - 查看商品详情(未登录)
	 * http://localhost:8080/cfdScenic/interFace/eShop/findDetailByGoodsId?
	 * goodsId=3
	 * 
	 * @return 参数 goodsId商品的Id 流程 1，查询来商品详情 2，查询轮播图 3，查询详情介绍 调用表 shop_goods
	 *         picture_library visitors_profiles
	 */
	@RequestMapping(value = "/findDetailByGoodsId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findDetailByGoodsId(Long goodsId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map detail = new HashMap();
			List pic = new ArrayList();
			String contentUrl = new String();
			Map detailMap = new HashMap();
			detail = eShopService.findDetailByGoodsId(goodsId);
			pic = eShopService.getPicById(goodsId);
			if (detail != null && !detail.equals("")) {
				contentUrl = eShopService.getContentUrlById(Long.parseLong(detail.get("content_id") + ""));
			}
			detailMap.put("detail", detail);
			if (pic.size() > 0 && pic.get(0) != null) {
				detailMap.put("pic", pic);
			} else {
				detailMap.put("pic", new ArrayList());
			}
			detailMap.put("contentUrl", contentUrl);
			msg.setData(detailMap);
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "find error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 商城 - 特产商品 - 查看商品详情（已登录）
	 * http://localhost:8080/cfdScenic/interFace/eShop/findDetailByGoodsId?
	 * goodsId=3
	 * 
	 * @return 参数 goodsId商品的Id 流程 1，查询来商品详情 2，查询轮播图 3，查询详情介绍 调用表 shop_goods
	 *         picture_library visitors_profiles
	 *         返回isCollect,1关注，0未关注
	 */
	@RequestMapping(value = "/findDetailByGoodsIdAndUid", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findDetailByGoodsIdAndUid(Long goodsId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				Map detail = new HashMap();
				List pic = new ArrayList();
				String contentUrl = new String();
				Map detailMap = new HashMap();
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("goodsId", goodsId);
					map.put("uid", userId);
					detail = eShopService.findDetailByGoodsIdAndUid(map);
					pic = eShopService.getPicById(goodsId);
					if(detail!=null&&!detail.equals(""))
					{
						contentUrl = eShopService.getContentUrlById(Long.parseLong(detail.get("content_id") + ""));
					}
					detailMap.put("detail", detail);
					if(pic.size()>0&&pic.get(0)!=null)
					{
						detailMap.put("pic", pic);
					}else
					{
						detailMap.put("pic", new ArrayList());
					}
					
					detailMap.put("contentUrl", contentUrl);
					msg.setData(detailMap);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "find error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}


	/**
	 * 商城 - 酒店 - 分页查询酒店 - 2
	 * http://localhost:8080/cfdScenic/interFace/eShop//findHotelTwo?page=1&rows=2
	 * 
	 * @return 流程 1，分页查询,查询shop_id为1的酒店。(shop_id是店铺分配表的Id，1是酒店的Id) 调用表
	 *         shop_information
	 */
	@RequestMapping(value = "/findHotelTwo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findHotelTwo(PagerForm page,String startDate,String endDate) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			DataGrid dataGrid = eShopService.findHotelPage(page.getPageRequest(map));
			List<Map<String,Object>> list = dataGrid.getRows();
			if(list.size()>0&&list.get(0) != null ){
				for(int i=0;i<list.size();i++){
					Long id = Long.parseLong(list.get(i).get("id")+"");
					Map para = new HashMap();
					para.put("startDate",startDate);
					para.put("endDate",endDate);
					para.put("id",id);
					int count = eShopService.getGoodsCount(para);
					int stock = eShopService.getGoodsStock(id);
					if (stock <= count){
						list.remove(i);
					}
				}
				dataGrid.setRows(list);
				msg.setData(dataGrid);
				msg.setHearder(0, "ok");
			}else{
				msg.setHearder(4,"内容为空！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "find error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 酒店 - 酒店详细信息
	 * http://localhost/cfdScenic/interFace/eShop/findHotelDetail?id=5 
	 * 参数：
	 * id酒店的ID 流程 1,通过参数Id查询酒店的详细信息 2,查询所有的和酒店有关的商品 3,查询出酒店的轮播图 4,获取酒店的介绍 调用表
	 * Shop_information shop_goods picture_library
	 */
	@RequestMapping(value = "/findHotelDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findHotelDetail(Long id,String startDate,String endDate) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					Map hotelMap = new HashMap();
					List<Map<String,Object>> goodsList = new ArrayList<Map<String,Object>>();
					List hotelPic = new ArrayList();
					Map map = new HashMap<String, Map>();
					hotelMap = eShopService.selectById(id);
					goodsList = eShopService.findHotelGoodsById(id);
					//判断酒店商品能不能预定
					if(goodsList.size()>0&&goodsList.get(0)!=null)
					{
						for(int i=0;i<goodsList.size();i++){
							String goodsId = goodsList.get(i).get("id")+"";
							String goodsStock = goodsList.get(i).get("stock")+"";
							Map paraMap = new HashMap();
							paraMap.put("goodsId",goodsId);
							paraMap.put("startDate",startDate);
							paraMap.put("endDate",endDate);
							Integer goodOrderCount = eShopService.getOrderNumberByGoodsId(paraMap);
							if(goodOrderCount != null){
								if(goodOrderCount < Integer.parseInt(goodsStock)){
									goodsList.get(i).put("isReservation",0); //0可以预定
									goodsList.get(i).put("stock",Integer.parseInt(goodsStock) - goodOrderCount);
								}else{
									goodsList.get(i).put("isReservation",1); //0不可以预定
								}
							}else{
								goodsList.get(i).put("isReservation",0); //0可以预定
							}
						}
					}
					hotelPic = eShopService.findHotelPicBy(id);
					map.put("hotelDetail", hotelMap);
					if(goodsList.size()>0&&goodsList.get(0)!=null)
					{
						map.put("shopGoods", goodsList);
					}else
					{
						map.put("shopGoods",new ArrayList());
					}
					if(hotelPic.size()>0&&hotelPic.get(0)!=null)
					{
						map.put("hotelPic", hotelPic);
					}else
					{
						map.put("hotelPic", new ArrayList());
					}
					msg.setData(map);
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "find error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 酒店 - 单个商品的信息 预定订单的时候调用
	 * http://localhost:8080/cfdScenic/interFace/eShop/findHotelGoodsDetail?goodsId=5
	 *  参数： goodsId(商品的Id) 流程 1,通过商品Id查询商品的详细信息 调用表 hotel_order
	 */
	@RequestMapping(value = "/findHotelGoodsDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findHotelGoodsDetail(Long goodsId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					Map map = new HashMap();
					map = eShopService.findHotelGoodsDetail(goodsId);
					msg.setData(map);
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "find error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 酒店 - 创建订单
	 * http://localhost:8080/cfdScenic/interFace/eShop/saveHotelOrder?name=订单名称&orderDescribe=订单描述&price=200&startDate=2016-07-08&endDate=2016-07-07&quantity=20&goodsId=5&shopInformationId=1&checkDays=20&realPrice=2000&telphone=15373839393&personName=李某某,张某某,王某某,赵某某 
	 * 参数： 传参： name(订单名称)
	 * orderDescribe(订单描述) price(原价) startDate(入住时间) endDate(离店时间)
	 * quantity(房间数量) goodsId(商品id) shopId(商户id) checkDays(入住天数) realPrice(应付价格)
	 * 默认： payWay(支付方式0) payState(支付状态0) orderState(订单状态0) createTime(订单生成时间)
	 * userId(用户id) orderCode(订单号) isComment(0) 
	 * 流程 
	 * 1,订单表插入一条数据 
	 * 2,入住酒店的人员保存到表里
	 * 调用表 
	 * hotel_order
	 * order_person
	 */
	@RequestMapping(value = "/saveHotelOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveHotelOrder(HotelOrder hotelOrder, String personName) {
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
					String orderCode = GenerateSequenceUtil.getUniqueId()+"";
					hotelOrder.setId(id);
					hotelOrder.setStartDate(hotelOrder.getStartDate()+" 12:00:00");
					hotelOrder.setEndDate(hotelOrder.getEndDate()+" 12:00:00");
					hotelOrder.setPayWay(0);
					hotelOrder.setPayState(0);
					hotelOrder.setOrderState(1);
					hotelOrder.setCreateTime(sdf.format(new Date()));
					hotelOrder.setUserId(userId);
					hotelOrder.setOrderCode(orderCode);
					hotelOrder.setIsComment(0);
					hotelOrder.setIsBalance(0);
					hotelOrder.setIsDelete(0);
					eShopService.saveHotelOrder(hotelOrder);
					String person[] = personName.split(",");
					for (int i = 0; i < person.length; i++) {
						OrderPerson orderPerson = new OrderPerson();
						orderPerson.setId(GenerateSequenceUtil.getUniqueId());
						orderPerson.setHotelOrderId(id);
						orderPerson.setName(person[i]);
						orderPerson.setCreateTime(sdf.format(new Date()));
						orderPerson.setState(0);
						eShopService.saveOrderPerson(orderPerson);
					}
					Map map = new HashMap();
					map.put("id",id);
					map.put("orderCode",orderCode);
					msg.setData(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 酒店 - 支付订单前回显信息
	 * http://localhost:8080/cfdScenic/interFace/eShop/payHotelOrder?hotelOrderId=1 
	 * 1,通过id查询订单的信息 
	 * 调用表 hotel_order
	 */
	@RequestMapping(value = "/payHotelOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payHotelOrder(Long hotelOrderId) {
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
					Map map = new HashMap();
					map = eShopService.getHotelOrder(hotelOrderId);
					msg.setData(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

//	/**
//	 * 商城 - 酒店 - 点击支付的时候调用的接口 这个接口用来判断用户所选的酒店的房间是不是满员的
//	 * http://localhost:8080/cfdScenic/interFace/eShop/isNull?shopGoodsId=5&startDate=2016-08-01&endDate=2016-08-03 
//	 * 1,查询这个酒店的商品的订单的房间的总和
//	 * 2,比较商品的数量与上一步的总合 
//	 * 3,如果满员就不让预定 调用表 hotel_order shop_goods
//	 */
//	@RequestMapping(value = "/isNull", produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String isNull(Long shopGoodsId, String startDate, String endDate) {
//		ResponseMsg msg = new ResponseMsg();
//		String json = new String();
//		try {
//			String token = webContext.getRequest().getHeader("Authorization");
//			if (token != null) {
//				Long userId = consumerUserService.getUserIdByToken(token);
//				if (userId == null) {
//					msg.setHearder(3, "认证信息错误，请重新登录！");
//				} else {
//					Map map = new HashMap();
//					map.put("shopGoodsId", shopGoodsId);
//					map.put("startDate", startDate);
//					map.put("endDate", endDate);
//					// 先获取所有的订单的房间数量的总合
//					int countNumber = eShopService.getCountNumberHotelOrder(map);
//					int stockNumber = eShopService.getstockNumber(shopGoodsId);
//					if (countNumber >= stockNumber) {
//						msg.setHearder(4, "The room is full of");
//					} else {
//						msg.setHearder(0, "ok");
//					}
//				}
//			} else {
//				msg.setHearder(2, "token is null");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			msg.setHearder(1, "save error");
//		}
//		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
//		System.out.println(json);
//		return json;
//	}
	/**
	 * 商城 - 酒店 - 支付完成后的回显
	 * http://localhost:8080/cfdScenic/interFace/eShop/payFinshShow?hotelOrderId=1
	 * 流程 通过id查询订单的详细信息 调用表 hotel_order
	 */
	@RequestMapping(value = "/payFinshShow", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payFinshShow(Long hotelOrderId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = eShopService.getHotelOrder(hotelOrderId);
					msg.setData(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商城 - 酒店 - 余额支付
	 * http://localhost:8080/cfdScenic/interFace/eShop/balancePay?hotelOrderId=1
	 * 流程 
	 * 修改订单状态,修改余额表 
	 * 调用表 hotel_order
	 * type(123)
	 */
	@RequestMapping(value = "/balancePay", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String balancePay(Long hotelOrderId,Integer type) {
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
					// 获取订单金额
					Double hotelOrder = eShopService.getHotelOrderById(hotelOrderId);
					// 余额减少,先获取余额，判断余额是否可以支付本次订单
					Double userAccountBalance = eShopService.getUserAccount(userId);
					if (type == 1) {
						if (userAccountBalance > hotelOrder) {
							// 更新账户
							Map map = new HashMap();
							map.put("userId", userId);
							map.put("balance", userAccountBalance - hotelOrder);
							map.put("integration", hotelOrder);
							eShopService.updateUserAccountByUserId(map);
							//添加余额交易记录
							UserAccountLog userAccountLog = new UserAccountLog(); 
							userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
							userAccountLog.setBalance(userAccountBalance);
							userAccountLog.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
							userAccountLog.setName("购买商品");
							userAccountLog.setIntegration(0);
							userAccountLog.setPrice(hotelOrder);
							userAccountLog.setTradeIntegration(0);
							userAccountLog.setType(2);
							userAccountLog.setUserId(userId);
							userAccountLog.setShopId(hotelOrderId);
							userAccountLog.setExtractType(0);
							eShopService.saveUserAccountLog(userAccountLog);
							// 修改订单信息
							Map hotelMap = new HashMap();
							hotelMap.put("id", hotelOrderId);
							hotelMap.put("payTime", sdf.format(new Date()));
							hotelMap.put("payWay", 1);
							hotelMap.put("orderState", 3);
							eShopService.updateHotelOrderByMap(hotelMap);
							msg.setHearder(0, "ok");
						} else {
							msg.setHearder(4, "余额不足");
						}
					} else {
						// 修改订单信息
						Map hotelMap = new HashMap();
						hotelMap.put("id", hotelOrderId);
						hotelMap.put("payTime", sdf.format(new Date()));
						hotelMap.put("payWay",type);
						hotelMap.put("orderState",3);
						eShopService.updateHotelOrderByMap(hotelMap);
						msg.setHearder(0, "ok");
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商城 - 饭店 - 查询所有的饭店 - 2
	 * http://localhost/cfdScenic/interFace/eShop/findAllRestaurantTwo?page=1&rows=2 
	 * 流程 1,分页查询所有的饭店 调用表 shop_information
	 */
	@RequestMapping(value = "/findAllRestaurantTwo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findAllRestaurantTwo(PagerForm page) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			DataGrid dataGrid = eShopService.findAllRestaurantPage(page.getPageRequest(map));
			msg.setData(dataGrid);
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 饭店 - 查询饭店详情
	 * http://localhost:8080/cfdScenic/interFace/eShop/findAllRestaurantDetail?id=1 
	 * 流程 1,通过id查询饭店的详细信息 2,查询饭店的所有商品 3,查询饭店的轮播图 调用表 shop_information
	 * shop_goods picture_library
	 */
	@RequestMapping(value = "/findAllRestaurantDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findAllRestaurantDetail(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					Map restaurantMap = new HashMap();
					List goodsList = new ArrayList();
					List restaurantPic = new ArrayList();
					List packageList = new ArrayList();
					Map map = new HashMap<String, Map>();
					restaurantMap = eShopService.findAllRestaurantDetail(id);
					goodsList = eShopService.findRestaurantGoodsById(id);
					packageList = eShopService.findPackageGoodsById(id);
					restaurantPic = eShopService.findHotelPicBy(id);
					map.put("restaurantDetail", restaurantMap);
					if(goodsList.size()>0&&goodsList.get(0)!=null)
					{
						map.put("shopGoods", goodsList);
					}else
					{
						map.put("shopGoods", new ArrayList());
					}
					if(restaurantPic.size()>0&&restaurantPic.get(0)!=null)
					{
						map.put("restaurantPic", restaurantPic);
					}else
					{
						map.put("restaurantPic", new ArrayList());
					}
					if(packageList.size()>0&&packageList.get(0)!=null)
					{
						map.put("packageList", packageList);
					}else
					{
						map.put("packageList", new ArrayList());
					}
					msg.setData(map);
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 饭店 - 查询套餐详情
	 * http://localhost/cfdScenic/interFace/eShop/findPackageByPackageId?id=1 
	 * 流程 1,通过id查询饭店的详细信息 2,查询饭店的所有商品 3,查询饭店的轮播图 调用表 shop_information
	 * shop_goods picture_library
	 */
	@RequestMapping(value = "/findPackageByPackageId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findPackageByPackageId(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			List list = new ArrayList();
			Map<String,Object> map = eShopService.findPackageByPackageId(id);
			String[] str = (map.get("goods_ids")+"").split(",");
			for(int i=0;i<str.length;i++){
				Long packageId = Long.parseLong(str[i]);
				Map para = eShopService.findPackageDetailByPackageId(packageId);
				list.add(para);
			}
			List<String> picList = eShopService.findPicByPicId(id);
			map.put("goods",list);
			map.put("picList",picList);
			msg.setData(map);
			msg.setHearder(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 商城 - 饭店 -创建订单
	 * http://localhost/cfdScenic/interFace/eShop/saveRestaurantOrder?name=好吃的&orderDescribe=订单描述&price=2000&eatDate=2016-08-02&quantity=2&realPrice=4000&goodsId=7&shopInformationId=1&telphone=12312312312&goodsType=1&isBalance=0 
	 * 参数:
	 * 传参:name(订单名称)orderDescribe(订单描述)price(原价)eatDate(就餐时间)quantity(数量)
	 * realPrice(应付价格)goodsId(商品id)shopId(商户id)telphone(手机号)goodsType(类型)
	 * 默认:id(id)payWay(支付方式0)payState(支付状态0)orderState(订单状态1)createTime(订单生成时间)
	 * userId(用户id)orderCode(订单号)isComment(是否评价0) 
	 * 流程 
	 * 1,通过id查询饭店的详细信息 
	 * 2,查询饭店的所有商品
	 * 3,查询饭店的轮播图 
	 * 调用表 shop_information shop_goods picture_library
	 */
	@RequestMapping(value = "/saveRestaurantOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveRestaurantOrder(String restaurantOrderJson) {
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
					List<RestaurantOrder> goodsOrderList = JSONArray.toList(JSONArray.fromObject(restaurantOrderJson),new RestaurantOrder(), new JsonConfig());
					String orderNumber = GenerateSequenceUtil.getUniqueId()+"";
					for(int i=0;i<goodsOrderList.size();i++){
						goodsOrderList.get(i).setId(GenerateSequenceUtil.getUniqueId());
						goodsOrderList.get(i).setPayWay(0);
						goodsOrderList.get(i).setPayState(0);
						goodsOrderList.get(i).setOrderState(1);
						goodsOrderList.get(i).setCreateTime(sdf.format(new Date()));
						goodsOrderList.get(i).setUserId(userId);
						goodsOrderList.get(i).setOrderCode(orderNumber);
						goodsOrderList.get(i).setIsComment(0);
						goodsOrderList.get(i).setIsDelete(0);
						eShopService.saveRestaurantOrder(goodsOrderList.get(i));
					}
					Map map = new HashMap();
					map.put("orderCode",orderNumber);
					
					if(goodsOrderList.size() > 0){
						//为了不让app端修改代码，这块的参数名不变，参数值修改成订单的ID
						map.put("shopInformationId",goodsOrderList.get(0).getId());
					}else{
						map.put("shopInformationId","");
					}
					msg.setData(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 饭店 - 确定支付和支付完成之后的回显
	 * http://localhost:8080/cfdScenic/interFace/eShop/showOrder?id=1608021021234020 
	 * 流程 关联查询订单表和商铺表的信息 调用表 restaurant_order shop_information
	 */
	@RequestMapping(value = "/showOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String showOrder(String orderCode) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map map = new HashMap();
					map = eShopService.selectRestaurantOrderById(orderCode);
					msg.setData(map);
					msg.setHearder(0, "ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 饭店,特产,酒店 - 取消订单修改订单的支付状态
	 * http://localhost:8080/cfdScenic/interFace/eShop/updateUndoOrder?id=1&type
	 * =1 参数 id(id) type(订单类型 1饭店 2特产 3酒店) 流程 支付完成后修改订单的支付状态 调用表
	 * restaurant_order goods_order hotel_order 
	 */
	@RequestMapping(value = "/updateUndoOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateUndoOrder(Long id, Integer type) {
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
					case 1:
						eShopService.updateUndoRestaurantOrder(id);
						msg.setHearder(0, "ok");
						break;
					case 2:
						eShopService.updateUndoGoodsOrder(id);
						msg.setHearder(0, "ok");
						break;
					case 3:
						eShopService.updateUndoHotelOrder(id);
						msg.setHearder(0, "ok");
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 获取热门商品 
	 * http://localhost:8080/cfdScenic/interFace/eShop/getHotGoods 
	 * 流程
	 * 获取推荐的商品名字和id 
	 * 调用表 shop_goods
	 */
	@RequestMapping(value = "/getHotGoods", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getHotGoods() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					List<Map> list = new ArrayList<Map>();
					list = eShopService.getHotGoods();
					if(list.size()>0&&list.get(0)!=null)
					{
						msg.setData(list);
					}
					msg.setHearder(0, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}

	/**
	 * 商城 - 小吃 - 查询所有的小吃 - 分页 2
	 * http://localhost:8080/cfdScenic/interFace/eShop/findAllSnackTwo?page=1&rows=2 
	 * 流程 1,分页查询所有的饭店 调用表 shop_information
	 */
	@RequestMapping(value = "/findAllSnackTwo", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String findAllSnackTwo(PageCount page) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			map.put("page",page.getPage());
			map.put("rows",page.getRows());
			List<Map<String,Object>> list = eShopService.findAllSnackPage(map);
			if (list.size() >0 && list.get(0) != null) {
				msg.setData(list);
				msg.setHearder(0, "ok");
			} else {
				msg.setHearder(4, "内容为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "save error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:商城订单支付
	 * 
	 * @author:lishilong
	 * @date:2016年9月9日
	 */
	@RequestMapping(value = "payOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payOrder(Long id, Double balance, Double price, Integer orderType, Integer payType, String orderCode,
			String passWord) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {

			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					switch (orderType) {
					// 特产，小吃
					case 1:
						int ok = eShopService.payOrder(id, orderCode, userId, balance, price, payType, passWord);
						if (ok == 0) {
							msg.setHearder(0, "ok");
							msg.setData(orderCode);
						}
						if (ok == 1) {
							msg.setHearder(4, "passWord is error");
						}
						if (ok == 2) {
							msg.setHearder(5, "passWord is null");
						}
						break;
					// 酒店
					case 2:
						int ok1 = eShopService.payHotelOrder(id, orderCode, userId, balance, price, payType, passWord);
						if (ok1 == 0) {
							msg.setHearder(0, "ok");
							msg.setData(orderCode);
						}
						if (ok1 == 1) {
							msg.setHearder(4, "passWord is error");
						}
						if (ok1 == 2) {
							msg.setHearder(5, "passWord is null");
						}
						break;
					// 饭店
					case 3:
						int ok2 = eShopService.payRestaurantOrder(id, orderCode, userId, balance, price, payType,
								passWord);
						if (ok2 == 0) {
							msg.setHearder(0, "ok");
							msg.setData(orderCode);
						}
						if (ok2 == 1) {
							msg.setHearder(4, "passWord is error");
						}
						if (ok2 == 2) {
							msg.setHearder(5, "passWord is null");
						}
						break;
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	/**
	 * Title:商城特产，小吃订单支付完成后回显
	 * @author:lishilong
	 * @date:2016年9月9日
	 */
	@RequestMapping(value="payOrderFinsh", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payOrderFinsh(Long orderCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> list = eShopService.getPayOrderFinshByOrderCode(orderCode);
					if(list.size() >0 && list.get(0)!=null){
						msg.setData(list);
					}else{
						msg.setData(new ArrayList());
					}
					msg.setHearder(0,"ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	/**
	 * Title:获取饭店和酒店首页的图片
	 * @author:lishilong
	 * @date:2016年9月17日
	 * type (1酒店2饭店)
	 */
	@RequestMapping(value="/getPhotoOfHomePage",produces = "text/html;charset=UTF-8" )
	@ResponseBody
	public String getPhotoOfHomePage(Integer type){
		String json = new String();
		ResponseMsg msg = new ResponseMsg();
		try {
			String url = eShopService.getPhotoOfHomePage(type);
			msg.setData(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		json = JSONObject.toJSONString(msg);
		return json;
	}
	/**
	 * Title:购物车，移动商品到文件夹
	 * @author:lishilong
	 * @date:2016年9月17日
	 */
	@RequestMapping(value="/saveCollection",produces = "text/html;charset=UTF-8" )
	@ResponseBody
	public String getPhotoOfHomePage(String ids){
		String json = new String();
		ResponseMsg msg = new ResponseMsg();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					if (ids != null) {
						List<Long> id = new ArrayList<Long>();
						if (ids.indexOf(",") == -1) {
							id.add(Long.parseLong(ids));
						} else {
							String[] str = ids.split(",");
							for(int i=0;i<str.length;i++){
								id.add(Long.parseLong(str[i]));
							}
						}
						// 循环id查询商品id
						List<Long> idList = new ArrayList<Long>();
						for (int i = 0; i < id.size(); i++) {
							idList.add(eShopService.getGoodsIdById(id.get(i)));
						}
						// 保存收藏夹
						for (int i = 0; i < idList.size(); i++) {
							UserCollect userCollect = new UserCollect();
							userCollect.setCreateTime(new Date());
							userCollect.setGoodsId(idList.get(i));
							userCollect.setId(GenerateSequenceUtil.getUniqueId());
							userCollect.setState(0);
							userCollect.setType(0);
							userCollect.setUserId(userId);
							eShopService.saveUserCollect(userCollect);
						}
						// 根据id删除购物车
						for (int i = 0; i < id.size(); i++) {
							eShopService.deleteShopCartById(id.get(i) + "");
						}
					}
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg);
		return json;
	}
	/**
	 * Title:获取购物车数量
	 * @author:lishilong
	 * @date:2016年9月22日
	 */
	@RequestMapping(value="/getNumber",produces = "text/html;charset=UTF-8" )
	@ResponseBody
	public String getNumber(){
		String json = new String();
		ResponseMsg msg = new ResponseMsg();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					//查询购物车数量
					Integer number = eShopService.getNumber(userId);
					msg.setData(number);
					msg.setHearder(0,"ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg);
		return json;
	}
	
	/**
	 * Title:店铺商品搜索接口
	 * @author:lishilong
	 * @date:2016年10月9日
	 */
	@RequestMapping(value="/searchGoodsByName",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String searchGoodsByName(Long siId,String goodsName){
		String json = new String();
		ResponseMsg msg = new ResponseMsg();
		try {
					//查询参数
			Map para = new HashMap();
			para.put("siId",siId);
			para.put("goodsName",goodsName);
			List<Map<String,Object>> list = eShopService.searchGoodsByName(para);
			if(list.size()>0 && list.get(0) != null){
				msg.setData(list);
			}else{
				msg.setData(new ArrayList());
			}
			msg.setHearder(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg);
		return json;
	}
}
