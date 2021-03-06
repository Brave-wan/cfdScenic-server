package com.htkj.cfdScenic.app.model;

import java.math.BigDecimal;

import com.htrj.common.base.BaseEntity;

public class HotelOrder extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -7011141863599776503L;
	private Long id;				//订单id
	private String name;			//订单名称
	private String orderDescribe;	//订单描述
	private String telphone;		//手机号
	private Double price;		//原价
	private String startDate;		//入住时间
	private String endDate;			//离店时间
	private Integer quantity;		//房间数量
	private Integer payWay;			//支付方式（1余额2支付宝3微信）
	private Integer payState;		//支付状态（0未支付1已支付）
	private Integer orderState;		//订单状态（1确认订单，2取消订单 3已支付 4以退付 5已完成）
	private String createTime;		//订单生成时间
	private String payTime;			//支付时间
	private String refundTime;		//退付时间
	private Long userId;			//用户id
	private Double realPrice;	//应付价格
	private String orderCode;		//订单号
	private Integer isComment;		//是否评价
	private Long goodsId;			//商品id
	private Long shopInformationId;	//商户id
	private Integer checkDays;		//入住天数
	private Integer isBalance;		//是否结算
	private Integer isDelete;		//是否删除（0否1是）
	private Integer billing;		//开票（0未开票1已开）
	private String hId;				//虚拟的 用来获取多个id
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
	public String getOrderDescribe() {
		return orderDescribe;
	}
	public void setOrderDescribe(String orderDescribe) {
		this.orderDescribe = orderDescribe;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPayWay() {
		return payWay;
	}
	public void setPayWay(Integer payWay) {
		this.payWay = payWay;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getRefundTime() {
		return refundTime;
	}
	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Integer getIsComment() {
		return isComment;
	}
	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getCheckDays() {
		return checkDays;
	}
	public void setCheckDays(Integer checkDays) {
		this.checkDays = checkDays;
	}
	public Integer getIsBalance() {
		return isBalance;
	}
	public void setIsBalance(Integer isBalance) {
		this.isBalance = isBalance;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Long getShopInformationId() {
		return shopInformationId;
	}
	public void setShopInformationId(Long shopInformationId) {
		this.shopInformationId = shopInformationId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getBilling() {
		return billing;
	}
	public void setBilling(Integer billing) {
		this.billing = billing;
	}
	public String gethId() {
		return hId;
	}
	public void sethId(String hId) {
		this.hId = hId;
	}
	
}
