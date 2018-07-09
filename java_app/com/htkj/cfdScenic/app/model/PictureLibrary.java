package com.htkj.cfdScenic.app.model;

import java.util.ArrayList;
import java.util.List;

import com.htrj.common.base.BaseEntity;
/**
 * 图片库
 * @author:lishilong
 * @date:2016年7月27日
 */
public class PictureLibrary extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -4240486501339938602L;
	private Long id;//主键
	private String name;//名称
	private String picDescribe;//描述
	private String imgUrl;//图片显示地址
	private Long linkId;//关联id（景区，评论等）
	private Integer type;//类型（1,景区，2评论等）
	private String createTime;//保存时间
	private String memo;//保存时间
	private String imgRootUrl;//图片存放地址
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPicDescribe() {
		return picDescribe;
	}
	public void setPicDescribe(String picDescribe) {
		this.picDescribe = picDescribe;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getImgRootUrl() {
		return imgRootUrl;
	}
	public void setImgRootUrl(String imgRootUrl) {
		this.imgRootUrl = imgRootUrl;
	}
	
}
