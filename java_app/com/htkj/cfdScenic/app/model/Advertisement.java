package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

/**
 * 广告（轮播图，广告位）
 * @author:lishilong
 * @date:2016年7月27日
 */
public class Advertisement extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 2210343197614462410L;
	
	private Long id;//主键
	private Long linkId;//链接id( 商品，商户主页，景点等等  )
	private String imgUrl;//图片URL
	private String title;//名称
	private String advertDescribe;//描述
	private Integer type;//类型（轮播图，广告位）
	private Integer state;//状态（0可用    1不可用）
	private String memo;//备注
	private Integer source;//轮播图来源（1.首页，2景区等）
//	private Integer shopType;//仅用于商城轮播图（1酒店，2饭店，3特产，4小吃）
	private Integer advertisementType;//播图跳转类型（1酒店，2饭店，3特产，4小吃，5景点详情，6景区详情）
	private Integer idDelete;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getLinkId() {
		return linkId;
	}
	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAdvertDescribe() {
		return advertDescribe;
	}
	public void setAdvertDescribe(String advertDescribe) {
		this.advertDescribe = advertDescribe;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public static Advertisement selectImgUrl() {
		// TODO Auto-generated method stub
		return null;
	}
//	public Integer getShopType() {
//		return shopType;
//	}
//	public void setShopType(Integer shopType) {
//		this.shopType = shopType;
//	}
	public Integer getAdvertisementType() {
		return advertisementType;
	}
	public void setAdvertisementType(Integer advertisementType) {
		this.advertisementType = advertisementType;
	}
	public Integer getIdDelete() {
		return idDelete;
	}
	public void setIdDelete(Integer idDelete) {
		this.idDelete = idDelete;
	}
	
}
