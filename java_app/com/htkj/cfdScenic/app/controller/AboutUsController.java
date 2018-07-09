package com.htkj.cfdScenic.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htkj.cfdScenic.app.model.HtmlText;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("aboutus")
public class AboutUsController {
	@Autowired
	private HtmlTextService htmlTextService;
	
	@RequestMapping("tolist")
	public String tolist(){
		
		return "/background/aboutus/Manager";
	}
	@RequestMapping("list")
	@ResponseBody
	public DataGrid list(HtmlText htmlText,PagerForm pagerForm){
		return htmlTextService.gethtmlList(pagerForm.getPageRequest(htmlText.toMap()));
	}
	@RequestMapping("toadd")
	public String toadd(){
		return "/background/aboutus/addPage";
	}
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(Long id){
		Json json = new Json();
		try {
			htmlTextService.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage("删除失败，请稍后重试");
		}
		return json;
	}
	@RequestMapping("selectById")
	public String selectById(Long id,ModelMap model){
		HtmlText htmlText = htmlTextService.selectByPrimaryKey(id);
		model.put("model", htmlText);
		return "/background/aboutus/updatePage";
	}
	@RequestMapping("yl")
	public String yl(Long id,ModelMap model){
		HtmlText htmlText = htmlTextService.selectByPrimaryKey(id);
		model.put("model", htmlText);
		return "/background/aboutus/yl";
	}
}
