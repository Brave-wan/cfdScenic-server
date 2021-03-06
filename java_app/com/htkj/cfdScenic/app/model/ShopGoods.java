package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;
/**
 * 商品表
 * @author:lishilong
 * @date:2016年7月27日
 */
public class ShopGoods extends BaseEntity{
	
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -6271843676656365633L;
	
	private Long id; //主键id
	private String goodsName;//商品名称
	private String goodsDescribe;//商品描述
	private Double price; //原价
	private Double newPrice;//新价
	private Double deliverFee;//配送费
	private Integer stock;//库存
	private Integer isRecomment;//是否推荐
	private Integer isHot;//是否热销
	private Integer state;//状态（0，正常，1停用）可用于商品上下架
	private Long contentId;//详情id    保存html文件地址到详情表
	private Long shopInformationId;//所属商户id
	private String createTime;//创建时间
	private String describeImg;//商户首页图片
	private Integer goodsType;//商品类型（0，单品1团购）	//数据库无此字段
	private Integer type;//商品类型（1酒店，2特色，3饭店）
	private Integer monthlySales;					//数据库无此字段
	private Long package_id;//团购套餐id（htmlcontent）//数据库无此字段
    private String label;//标签
    private String brand;//品牌/规格
    private Long userId;
    private Integer isNotReturn;//是否是不可退产品
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsDescribe() {
		return goodsDescribe;
	}
	public void setGoodsDescribe(String goodsDescribe) {
		this.goodsDescribe = goodsDescribe;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getIsRecomment() {
		return isRecomment;
	}
	public void setIsRecomment(Integer isRecomment) {
		this.isRecomment = isRecomment;
	}
	public Integer getIsHot() {
		return isHot;
	}
	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getContentId() {
		return contentId;
	}
	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}
	public Long getShopUserId() {
		return shopInformationId;
	}
	public void setShopUserId(Long shopInformationId) {
		this.shopInformationId = shopInformationId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDescribeImg() {
		return describeImg;
	}
	public void setDescribeImg(String describeImg) {
		this.describeImg = describeImg;
	}
	public Integer getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}
	public Long getShopInformationId() {
		return shopInformationId;
	}
	public void setShopInformationId(Long shopInformationId) {
		this.shopInformationId = shopInformationId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMonthlySales() {
		return monthlySales;
	}
	public void setMonthlySales(Integer monthlySales) {
		this.monthlySales = monthlySales;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(Double newPrice) {
		this.newPrice = newPrice;
	}
	public Double getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(Double deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Long getPackage_id() {
		return package_id;
	}
	public void setPackage_id(Long package_id) {
		this.package_id = package_id;
	}

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getIsNotReturn() {
		return isNotReturn;
	}
	public void setIsNotReturn(Integer isNotReturn) {
		this.isNotReturn = isNotReturn;
	}
    
}
