/**  
* @Title: MonitorPointController.java
* @Package com.htkj.cfdScenic.app.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author 张伟烁 
* @date 2016年10月31日 下午4:14:03
*/ 
package com.htkj.cfdScenic.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.MonitorPoint;
import com.htkj.cfdScenic.app.service.MonitorPointService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

/**
* @ClassName: MonitorPointController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 张伟烁
* @date 2016年10月31日 下午4:14:03
*
*/
@Controller
@RequestMapping("/monitorPoint")
public class MonitorPointController extends BaseController {
	
	@Autowired
	private MonitorPointService monitorPointService;
	
	/**
	 * 获取监控信息
	 */
	@RequestMapping(value="getAllMonitor",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getAllMonitor(PagerForm pagerForm){
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			pagerForm.setRows(999);
			responseMsg.setData(monitorPointService.getAllMonitor(pagerForm.getPageRequest(new HashMap<String,Object>())));
			responseMsg.setHearder(0, "成功");
		} catch (Exception e) {
			responseMsg.setHearder(1, "error");
		}
		return JSONObject.toJSONString(responseMsg,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 获取ip
	 */
	@RequestMapping(value="getIP",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getIP(){
		ResponseMsg msg = new ResponseMsg();
		try {
			msg.setData(monitorPointService.getIp());
			msg.setHearder(0, "ok");
		} catch (Exception e) {
			msg.setHearder(1,"error");
			e.printStackTrace();
		}
		String json = JSONObject.toJSONString(msg);
		return json;
	}
	/**
	 * 跳转监控列表页面
	 */
	@RequestMapping("/toMonitorListPage")
	public String toAdvertisManage(){
		return "/background/monitorPoint/Manager";
	}
	/**
	 * 后台监控列表
	 */
	@RequestMapping("monitorList")
	@ResponseBody
	public DataGrid monitorList(PagerForm pagerForm){
		DataGrid dataGrid = new DataGrid();
		try {
			dataGrid = monitorPointService.getMonitorList(pagerForm.getPageRequest(new HashMap()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataGrid;
	}
	/**
	 * 监控添加和修改
	 */
	@RequestMapping("editMonitor")
	public String editMonitor(MonitorPoint mp){
		try {
			if(mp.getId() == null){
				mp.setId(GenerateSequenceUtil.getUniqueId());
				monitorPointService.saveMonitorPoint(mp);
			}else{
				monitorPointService.editMonitor(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/background/monitorPoint/Manager";
	}
	
	/**
	 * 跳转到修改或者新增页面
	 */
	@RequestMapping("toEditPage")
	public String toAddAndEditPage(ModelMap model,Long id){
		if(id != null){
			model.put("model",monitorPointService.getMonitorPointById(id));
			return "/background/monitorPoint/updatePage";
		}else{
			return "/background/monitorPoint/addPage";
		}
	}
	/**
	 * 删除
	 */
	@RequestMapping("deleteMonitor")
	@ResponseBody
	public Json deleteMonitor(ModelMap model,Long id){
		Json json = new Json();
		try {
			monitorPointService.deleteMonitorById(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 跳转修改IP页面
	 */
	@RequestMapping("toUpdateIPPage")
	public String toUpdateIPPage(ModelMap model){
		model.put("model",monitorPointService.getIp());
		return "/background/monitorPoint/updateIP";
	}
	
	/**
	 * 后台修改ip地址
	 */
	@RequestMapping("updateIP")
	@ResponseBody
	public Json updateIP(String ip,String user,String password,String port_number){
		Json json = new Json();
		try {
			Map map = new HashMap();
			map.put("ip", ip);
			map.put("user",user);
			map.put("password",password);
			map.put("port_number",port_number);
			monitorPointService.updateIP(map);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
