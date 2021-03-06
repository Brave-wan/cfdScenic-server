package com.htkj.cfdScenic.app.model;

import java.math.BigDecimal;

import com.htrj.common.base.BaseEntity;
/**
 * 商品订单
 * @author:lishilong
 * @date:2016年7月27日
 */
public class GoodsOrder extends BaseEntity{
	
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -4243070277451917931L;
	private Long id; //主键id
	private String name;//订单名称
	private String orderDescribe;//订单描述
	private Double price; //原价
	private String deliverDate;//开始有效期
	private Integer quantity;//数量
	private Integer payWay;//支付方式（1余额2支付宝3微信）
	private Integer payState;//支付状态（0未支付1已支付）
	private Integer orderState;//订单状态（1确认订单，2取消订单 3已支付 4以退付 5已完成）
	private String createTime;//订单生成时间
	private String payTime;//支付时间
	private String refundTime;//退付时间
	private Long userId;//用户id
	private Double realPrice;//应付价格
	private String orderCode;//订单号
	private Integer isComment;//是否评价
	private Long goodsId;//商品id
	private Long shopInformationId;//商户id
	private Long addressId;//收货地址id
	private Double deliverFee;//配送费
	private Integer isPickup;//是否自提
	private Integer isUpdatePrice;//是否修改过价格
	private	Integer isDeliverFee;//是否是配送费
	private Integer isDelete;//是否删除
	private Integer billing;//开票信息（0未开1已开）
	private String state;//虚拟实体
	
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
	public String getDeliverDate() {
		return deliverDate;
	}
	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
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
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Integer getIsPickup() {
		return isPickup;
	}
	public void setIsPickup(Integer isPickup) {
		this.isPickup = isPickup;
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
	public Double getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(Double deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Integer getIsUpdatePrice() {
		return isUpdatePrice;
	}
	public void setIsUpdatePrice(Integer isUpdatePrice) {
		this.isUpdatePrice = isUpdatePrice;
	}
	public Integer getIsDeliverFee() {
		return isDeliverFee;
	}
	public void setIsDeliverFee(Integer isDeliverFee) {
		this.isDeliverFee = isDeliverFee;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getBilling() {
		return billing;
	}
	public void setBilling(Integer billing) {
		this.billing = billing;
	}
	
}
