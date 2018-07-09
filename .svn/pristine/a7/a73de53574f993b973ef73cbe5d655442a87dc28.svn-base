package com.htkj.cfdScenic.app.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.NewsNotice;
import com.htkj.cfdScenic.app.model.Travelogs;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.service.NewsNoticeService;
import com.htkj.cfdScenic.app.service.TravelogsService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
/**
 * 张腾跃
 * 2016年10月25日11:30:36
 * 游记
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/travelogs")
public class TravelogsController extends BaseController{
	
	@Autowired
	private TravelogsService travelogsService;
	
	@RequestMapping("tolist")
	public String tolist() {
		return "/background/travelogs/Manager";
	}
	@RequestMapping("list")
	@ResponseBody
	public DataGrid list(Travelogs travelogs,PagerForm pagerForm){
		return travelogsService.findOrderList(pagerForm.getPageRequest(travelogs.toMap()));
	}
	
	@RequestMapping("selectById")
	public String selectById(Long id,Model model){
		Map<String,Object> map = travelogsService.checkTravelLogDetail(id);
		model.addAttribute("model", map);
		return "/background/travelogs/yl";
	}
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(Long id) {
		Json json = new Json();
		try {
			travelogsService.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		return json;
	}
	
}
