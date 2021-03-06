package com.htkj.cfdScenic.app.model;

import java.math.BigDecimal;

import com.htrj.common.base.BaseEntity;
/**
 * 景区
 * @author:lishilong
 * @date:2016年7月27日
 */
public class Visitors extends BaseEntity {
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 1762788557861226323L;
	private Long id;//主键
	private String name;//名称
	private String visitorsDescribe;//描述
	private BigDecimal price;//原价
	private BigDecimal newPrice;//折后价
	private String address;//地址
	private String longitude;//经度
	private String latitude;//纬度
	private String detailsId;//详情ID
	private Long noticeId;//须知id
	private Integer isRecommend;//是否推荐（0 正常，1推荐）
	private Integer state;//状态（0 正常，1删除）
	private String createTime;//创建时间
	private String memo;//备注
	private String startValid;//有效期开始时间
	private String endValid;//有效期结束时间
	private Integer integral;//积分
	private String headImg;//主图
	private BigDecimal deliverFee;//配送费
	private Integer number;//人数
	private String satisfaction;//满意度
	private String nameEn;//英语名称
	private Integer type;//1景点2基础活动3结伴游活动4积分商品5积分门票
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
	public String getVisitorsDescribe() {
		return visitorsDescribe;
	}
	public void setVisitorsDescribe(String visitorsDescribe) {
		this.visitorsDescribe = visitorsDescribe;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(BigDecimal newPrice) {
		this.newPrice = newPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Long getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
	public String getStartValid() {
		return startValid;
	}
	public void setStartValid(String startValid) {
		this.startValid = startValid;
	}
	public String getEndValid() {
		return endValid;
	}
	public void setEndValid(String endValid) {
		this.endValid = endValid;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public BigDecimal getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(BigDecimal deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getNameEn() {
		return nameEn;
	}
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	public String getDetailsId() {
		return detailsId;
	}
	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}
	
}
