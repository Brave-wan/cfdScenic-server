package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.CommentSum;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.Travelogs;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htkj.cfdScenic.app.service.ConsumerUserService;
import com.htkj.cfdScenic.app.service.PlayCircleService;
import com.htkj.cfdScenic.app.service.UserCommentService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.CalendarHelper;
import com.htrj.common.utils.GenerateSequenceUtil;
import com.htrj.common.utils.wxPay.WXPay;

@Controller
@RequestMapping("/interFace/PlayCircle")
public class PlayCircleController extends BaseController{
	
	@Autowired
	private PlayCircleService playCircleService;
	@Autowired
	private ConsumerUserService consumerUserService;
	@Autowired
	private UserCommentService userCommentService;
	@Autowired
	private VisitorsService visitorsService;
	
	/**
	 * 游乐圈 - 精彩游记 - 分页查询
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/getTraveLogs
	 * 参数
	 * 	page页数 rows条数 type  0 全部 1 自己
	 * 流程：
	 * 1，按照时间倒序分页查询出所有的游记
	 * 2，for循环查处对应游记的赞数，评论，分享数
	 * 
	 * 调用表
	 * travelogs
	 */
	@RequestMapping(value = "/getTraveLogs", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTraveLogs(PagerForm pagerForm,Integer type) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			if(type==null||type==0)
			{
					Map map = new HashMap();
					map.put("userId", "");
					DataGrid dataGrid = playCircleService.getTraveLogs(pagerForm.getPageRequest(map));
					List<Map<String,Object>> list = dataGrid.getRows();
					for(int i=0;i<list.size();i++){
						//获取图片
						Long travelId = Long.parseLong((list.get(i).get("id")+""));
						List<String> picList = playCircleService.getPicByTravelId(travelId);
						
						int favorCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),1);
						int commentCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),2);
						int shareCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),3);
						String token = webContext.getRequest().getHeader("Authorization");
						if (token != null) {
							Long userId = consumerUserService.getUserIdByToken(token);
							if (userId == null) {
								list.get(i).put("isFavor", 2);
							} else {
								Map<String,Object> tem=new HashMap<String,Object>();
								tem.put("userId", userId);
								tem.put("id", String.valueOf(list.get(i).get("id")));
								
								Integer isFavor=playCircleService.getFavorUserIdCount(tem);
								if(isFavor>0&&isFavor!=null)
								{
									list.get(i).put("isFavor", 1);
								}else
								{
									list.get(i).put("isFavor", 0);
								}
							}
						}else
						{
							list.get(i).put("isFavor", 2);
						}
						
						list.get(i).put("favorCount", favorCount);
						list.get(i).put("commentCount", commentCount);
						list.get(i).put("shareCount", shareCount);
						list.get(i).put("picList", picList);
					}
					dataGrid.setRows(list);
					msg.setData(dataGrid);
					msg.setHearder(0, "ok");

			}else
			{
				String token = webContext.getRequest().getHeader("Authorization");
				if (token != null) {
					Long userId = consumerUserService.getUserIdByToken(token);
					if (userId == null) {
						msg.setHearder(3, "认证信息错误，请重新登录！");
					} else {
						Map map = new HashMap();
						map.put("userId", userId);
						DataGrid dataGrid = playCircleService.getTraveLogs(pagerForm.getPageRequest(map));
						List<Map<String,Object>> list = dataGrid.getRows();
						for(int i=0;i<list.size();i++){
							Long travelId = Long.parseLong((list.get(i).get("id")+""));
							List<String> picList = playCircleService.getPicByTravelId(travelId);
							int favorCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),1);
							int commentCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),2);
							int shareCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),3);
							
							Map<String,Object> tem=new HashMap<String,Object>();
							tem.put("userId", userId);
							tem.put("id", String.valueOf(list.get(i).get("id")));
							
//							Integer isFavor=playCircleService.getFavorUserIdCount(tem);
//							list.get(i).put("isFavor", isFavor);
							Integer isFavor=playCircleService.getFavorUserIdCount(tem);
							if(isFavor>0&&isFavor!=null)
							{
								list.get(i).put("isFavor", 1);
							}else
							{
								list.get(i).put("isFavor", 0);
							}
							list.get(i).put("favorCount", favorCount);
							list.get(i).put("commentCount", commentCount);
							list.get(i).put("shareCount", shareCount);
							list.get(i).put("picList", picList);
						}
						dataGrid.setRows(list);
						msg.setData(dataGrid);
						msg.setHearder(0, "ok");
					}
				} else {
					msg.setHearder(2, "token is null");
				}
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
	 * 游乐圈 - 获取轮播图
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/getImg
	 * 流程
	 * 查询轮播图数据
	 * 调用表
	 * advertisement
	 */
	@RequestMapping(value = "/getImg", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getImg() {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					List list = playCircleService.getImg();
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
	 * 游乐圈 - 景区活动 (活动和结伴游活动公用)
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/getActivity?page=1&rows=2&isTeam=0
	 * 流程
	 * 获取分页景区数据
	 * 调用表
	 * visitors
	 */
	@RequestMapping(value = "/getActivity", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getActivity(PagerForm PagerForm,Integer type) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Map map = new HashMap();
			map.put("type", type);
			DataGrid list = playCircleService.getActivity(PagerForm.getPageRequest(map));
			List<Map<String,Object>> mapList = list.getRows();
			for(int i=0;i<mapList.size();i++){
				String startDate = mapList.get(i).get("start_valid")+"";
				Long startDateLong = sdf.parse(startDate).getTime();
				Long nowDateLong = (new Date()).getTime();
				if(nowDateLong >= startDateLong){
					mapList.get(i).put("dateEnd",1);
				}else{
					mapList.get(i).put("dateEnd",0);
				}
				Integer perNumber = Integer.parseInt((mapList.get(i).get("number")+""));
				Integer nowPerNumber = Integer.parseInt((mapList.get(i).get("buynumber")+""));
				if(perNumber > nowPerNumber){
					mapList.get(i).put("soldOut",0);
				}else{
					mapList.get(i).put("soldOut",1);
				} 
			}
			list.setRows(mapList);
			msg.setData(list);
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
	 * 游乐圈 - 景区活动详情
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/getActivityDetail?id=1
	 * 流程
	 * 1,获取分页景区数据
	 * 2,获取这个活动的订单
	 * 
	 * 调用表
	 * visitors
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getActivityDetail", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getActivityDetail(Long id) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try {
					@SuppressWarnings("rawtypes")
					Map<String,Object> map = playCircleService.getActivityDetail(id);
					if(map!=null)
					{
						String startDate = map.get("start_valid")+"";
						Long startDateLong = date.parse(startDate).getTime();
						Long nowDateLong = date.parse(date.format(new Date())).getTime();
						if(nowDateLong >= startDateLong){
							map.put("dateEnd",1);
						}else{
							map.put("dateEnd",0);
						}
						Integer perNumber = Integer.parseInt((map.get("number")+""));
						Integer nowPerNumber = Integer.parseInt((map.get("buynumber")+""));
						if(perNumber < nowPerNumber){
							map.put("soldOut",1);
						}else{
							map.put("soldOut",0);
						} 
						Map para = new HashMap();
						para.put("id",map.get("id"));
						para.put("start_valid",map.get("start_valid"));
						para.put("end_valid",map.get("end_valid"));
						Integer personCount = playCircleService.getPersonCount(para);
						map.put("personNumber", personCount);
						msg.setData(map);
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
	 * 游乐圈 - 分享
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/saveShare?linkId=1
	 * 流程
	 * 1,插入一条分享数据
	 * 调用表
	 * 
	 */
	@RequestMapping(value = "/saveShare", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveShare(CommentSum commentSum) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					commentSum.setId(GenerateSequenceUtil.getUniqueId());
					commentSum.setFavor(0);
					commentSum.setComment(0);
					commentSum.setShare(1);
					commentSum.setUserId(userId);
					commentSum.setCreateDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
					playCircleService.saveShare(commentSum);
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
	 * 游乐圈 - 赞
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/saveFavor?linkId=1
	 * 流程
	 * 1,添加  赞     或者取消赞
	 * 调用表
	 * 
	 */
	@RequestMapping(value = "/saveFavor", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveFavor(CommentSum commentSum) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String,Object> tem=new HashMap<String,Object>();
					tem.put("userId", userId);
					tem.put("id",commentSum.getLinkId());
					
					Integer isFavor=playCircleService.getFavorUserIdCount(tem);
					
					if(isFavor==0)
					{
						commentSum.setId(GenerateSequenceUtil.getUniqueId());
						commentSum.setFavor(1);
						commentSum.setComment(0);
						commentSum.setShare(0);
						commentSum.setUserId(userId);
						commentSum.setCreateDate((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
						playCircleService.saveShare(commentSum);
						isFavor=1;
						
					}else
					{
						commentSum.setUserId(userId);
						playCircleService.deleteFavor(commentSum);
						isFavor=0;
						
					}
					msg.setHearder(0, "ok");
					msg.setData(isFavor);
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
	 * 游乐圈 - 精彩游记
	 * http://localhost:8080/cfdScenic/interFace/PlayCircle/getWonderful?page=1&rows=2&type=0
	 * 参数 type（类型（0视频攻略1精彩游记2必去清单）） isAll(0全部1我的)
	 */
	@RequestMapping(value = "/getWonderful", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getWonderful(PagerForm pagerForm,Integer type,Integer isAll) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			
			if(isAll==1)
			{
				String token = webContext.getRequest().getHeader("Authorization");
				if (token != null) {
					Long userId = consumerUserService.getUserIdByToken(token);
					if (userId == null) {
						msg.setHearder(3, "认证信息错误，请重新登录！");
					} else {
						Map map = new HashMap();
						map.put("type",type);
						map.put("userId", userId);
						DataGrid dataGrid = playCircleService.getWonderful(pagerForm.getPageRequest(map));
						List<Map<String,Object>> list = dataGrid.getRows();
						for(int i=0;i<list.size();i++){
							//获取图片
							Long travelId = Long.parseLong((list.get(i).get("id")+""));
							List<String> picList = playCircleService.getPicByTravelId(travelId);
							
							int favorCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),1);
							int commentCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),2);
							int shareCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),3);
							
							Map<String,Object> tem=new HashMap<String,Object>();
							tem.put("userId", userId);
							tem.put("id", String.valueOf(list.get(i).get("id")));
							
							Integer isFavor=playCircleService.getFavorUserIdCount(tem);
							if(isFavor>0&&isFavor!=null)
							{
								list.get(i).put("isFavor", 1);
							}else
							{
								list.get(i).put("isFavor", 0);
							}
							list.get(i).put("favorCount", favorCount);
							list.get(i).put("commentCount", commentCount);
							list.get(i).put("shareCount", shareCount);
							list.get(i).put("picList", picList);
						}
						dataGrid.setRows(list);
						msg.setData(dataGrid);
						msg.setHearder(0, "ok");
					}
				} else {
					msg.setHearder(2, "token is null");
				}
			}else
			{
					Map map = new HashMap();
					map.put("type",type);
					map.put("userId", "");
					DataGrid dataGrid = playCircleService.getWonderful(pagerForm.getPageRequest(map));
					List<Map<String,Object>> list = dataGrid.getRows();
					for(int i=0;i<list.size();i++){
						//获取图片
						Long travelId = Long.parseLong((list.get(i).get("id")+""));
						List<String> picList = playCircleService.getPicByTravelId(travelId);

						int favorCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),1);
						int commentCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),2);
						int shareCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),3);
						
						String token = webContext.getRequest().getHeader("Authorization");
						if (token != null) {
							Long userId = consumerUserService.getUserIdByToken(token);
							if (userId == null) {
								list.get(i).put("isFavor", 2);
							} else {
								Map<String,Object> tem=new HashMap<String,Object>();
								tem.put("userId", userId);
								tem.put("id", String.valueOf(list.get(i).get("id")));
								
								Integer isFavor=playCircleService.getFavorUserIdCount(tem);
								if(isFavor>0&&isFavor!=null)
								{
									list.get(i).put("isFavor", 1);
								}else
								{
									list.get(i).put("isFavor", 0);
								}
							}
						}else
						{
							list.get(i).put("isFavor", 2);
						}
						list.get(i).put("favorCount", favorCount);
						list.get(i).put("commentCount", commentCount);
						list.get(i).put("shareCount", shareCount);
						list.get(i).put("picList", picList);
					}
					dataGrid.setRows(list);
					msg.setData(dataGrid);
					msg.setHearder(0, "ok");

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
	 * 根据游记的id获取游记的评论 - 通用
	 */
	@RequestMapping(value = "/getCommentById", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCommentById(Long id) {
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
					map.put("id", id);
					List dataGrid = playCircleService.getCommentById(map);
					msg.setHearder(0, "ok");
					if(dataGrid != null){
					msg.setData(dataGrid);
					}else{
						msg.setData(new ArrayList());
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
	 * 写游记
	 */
	@RequestMapping(value = "/writeCollect", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String writeCollect(Travelogs travelogs,@RequestParam(value="images",required=false)String images) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					travelogs.setId(GenerateSequenceUtil.getUniqueId());
					travelogs.setUserId(userId);
					Map map = playCircleService.getActivityDetail(travelogs.getTravelId());
					System.out.print(map);
					if(map!=null)
					{
						travelogs.setAddress(map.get("address").toString());
						if(map.get("longitude")!=null)
						{
							travelogs.setLongitude(map.get("longitude").toString());
						} 
						if(map.get("latitude")!=null)
						{
							travelogs.setLatitude(map.get("latitude").toString());
						} 
					}
					playCircleService.writeCollect(travelogs);
					if (images != null && !images.equals("")) {
						if (images.indexOf(",") == -1) {
							PictureLibrary pictureLibrary = new PictureLibrary();
							pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
							pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							pictureLibrary.setImgRootUrl(images);
							pictureLibrary.setImgUrl(images);
							pictureLibrary.setLinkId(travelogs.getId());
							pictureLibrary.setMemo("游记图片");
							pictureLibrary.setName(travelogs.getTravelName());
							pictureLibrary.setPicDescribe("游记图片描述");
							pictureLibrary.setType(5);
							playCircleService.savePictureLibrary(pictureLibrary);
						} else {
							String[] img = images.split(",");
							for(int i=0;i<img.length;i++){
								PictureLibrary pictureLibrary = new PictureLibrary();
								pictureLibrary.setId(GenerateSequenceUtil.getUniqueId());
								pictureLibrary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								pictureLibrary.setImgRootUrl(img[i]);
								pictureLibrary.setImgUrl(img[i]);
								pictureLibrary.setLinkId(travelogs.getId());
								pictureLibrary.setMemo("游记图片");
								pictureLibrary.setName(travelogs.getTravelName());
								pictureLibrary.setPicDescribe("游记图片描述");
								pictureLibrary.setType(5);
								playCircleService.savePictureLibrary(pictureLibrary);
							}
						}
					}
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
	 * 游记添加评论
	 * linkId
	 * contentType
	 * content
	 */
	@RequestMapping(value = "/collectComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String collectComment(UserComment userComment) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					userComment.setId(GenerateSequenceUtil.getUniqueId());
					userComment.setCommentType(3);
					userComment.setSatisfyState(0);
					userComment.setHaveImg(0);
					userComment.setMemo("备注");
					userComment.setIsTravels(0);
					userComment.setUserId(userId);
					userComment.setFromUserId(0L);
					playCircleService.writeComment(userComment);
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
	 * 获取对景区的评论 
	 */
	@RequestMapping(value = "/myComment", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String myComment(Long toUserId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					map.put("userId", toUserId);
					List<UserComment> data= userCommentService.scenicUserIdComment(map);
					msg.setHearder(0, "success");
					if(data.size()>0&&data.get(0)!=null)
					{
						msg.setData(data);
					}
				}
				}else {
					msg.setHearder(2, "token is null");
				}
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 获取某个人发布的游记
	 * interFace/PlayCircle/getTravelsByUserId?rows=&page=&toUserId=
	 */
	@RequestMapping(value = "/getTravelsByUserId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTravelsByUserId(PagerForm page,Long toUserId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
					Map map = new HashMap();
					map.put("userId", toUserId);
					map.put("page",page.getPage());
					map.put("rows",page.getRows());
					DataGrid dataGrid = playCircleService.getTravelsByUserId(page.getPageRequest(map));
					List<Map<String,Object>> list = dataGrid.getRows();
					
					for(int i=0;i<list.size();i++){
						//获取图片
						Long travelId = Long.parseLong((list.get(i).get("id")+""));
						List<String> picList = playCircleService.getPicByTravelId(travelId);
						
						int favorCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),1);
						int commentCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),2);
						int shareCount = playCircleService.getTraveLogsCount(String.valueOf(list.get(i).get("id")),3);
						String token = webContext.getRequest().getHeader("Authorization");
						if (token != null) {
							Long userId = consumerUserService.getUserIdByToken(token);
							if (userId == null) {
								list.get(i).put("isFavor", 2);
							} else {
								Map<String,Object> tem=new HashMap<String,Object>();
								tem.put("userId", userId);
								tem.put("id", String.valueOf(list.get(i).get("id")));
								
								Integer isFavor=playCircleService.getFavorUserIdCount(tem);
								if(isFavor>0&&isFavor!=null)
								{
									list.get(i).put("isFavor", 1);
								}else
								{
									list.get(i).put("isFavor", 0);
								}
							}
						}else
						{
							list.get(i).put("isFavor", 2);
						}
						list.get(i).put("favorCount", favorCount);
						list.get(i).put("commentCount", commentCount);
						list.get(i).put("shareCount", shareCount);
						list.get(i).put("picList", picList);
					}
					dataGrid.setRows(list);
					msg.setData(dataGrid);
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
	 * 获取某个人评论的游记
	 * interFace/PlayCircle/getCommentTravelsByUserId?rows=&page=&toUserId=
	 */
	@RequestMapping(value = "/getCommentTravelsByUserId", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCommentTravelsByUserId(PagerForm page,Long toUserId) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			map.put("userId", toUserId);
			map.put("page",page.getPage());
			map.put("rows",page.getRows());
			DataGrid dataGrid = playCircleService.getCommentTravelsByUserId(page.getPageRequest(map));
			List<Map<String,Object>> list = dataGrid.getRows();
			for(int i=0;i<list.size();i++){
				//获取图片
				Long userCommentId = Long.parseLong((list.get(i).get("userCommentId")+""));
				Integer isHave = Integer.parseInt((list.get(i).get("have_img")+""));
				if(isHave != null && isHave ==1){
					List<String> picList = playCircleService.getPicByUserCommentId(userCommentId);
					list.get(i).put("picList", picList);
				}else{
					list.get(i).put("picList", new ArrayList());
				}
			}
			dataGrid.setRows(list);
			msg.setData(dataGrid);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "ckeck error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	/**
	 * 分享查看详情
	 * http://localhost/cfdScenic/interFace/PlayCircle/checkTravelLogDetail?travelLogId=1610110851487470
	 */
	@RequestMapping(value = "/checkTravelLogDetail", produces = "text/html;charset=UTF-8")
	public String checkTravelLogDetail(Long travelLogId,ModelMap modelMap){
		String view = "";
		try {
			Map<String,Object> travelogs = playCircleService.checkTravelLogDetail(travelLogId);
			if(travelogs != null){
			Long travelId = Long.parseLong((travelogs.get("id")+""));
			List<String> picList = playCircleService.getPicByTravelId(travelId);
			
			if(travelogs != null && travelogs.get("travel_video") != null && !(travelogs.get("travel_video")).equals("")){
				modelMap.put("type",1);
			}else if(picList.size() >0 && picList.get(0) != null){
				modelMap.put("pic",picList.get(0));
				modelMap.put("type",0);
			}
			modelMap.put("travelogs",travelogs);
			view = "/background/travel/index";
			}else{
			view = "/background/travel/error";
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 删除我的游记
	 */
	@RequestMapping(value = "/deleteMyTravelLog", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteMyTravelLog(Long id){
		ResponseMsg msg = new ResponseMsg();
		try {
			playCircleService.deleteMyTravelLog(id);
			msg.setHearder(0,"ok");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		return JSONObject.toJSONString(msg);
	}
	
	
}
