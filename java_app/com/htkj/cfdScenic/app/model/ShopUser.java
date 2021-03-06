package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;
/**
 * 商户信息
 * @author:lishilong
 * @date:2016年7月27日
 */
public class ShopUser extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -6142941095241143523L;
	private Long id;//主键id
	private String name;//姓名
	private String telPhone;//电话
	private String nickName;//昵称
	private String passWord;//昵称
	private Integer sex;//性别（0男 1女）
	private Integer age;//年龄
	private String realName;//真实姓名
	private String createTime;//创建时间
	private String shopToken;//token 
	private String idCard;//身份证
	private Integer state;//商家状态（0，正常，1停用，2删除）
	private Long shopInformationId;//店铺信息
	private Long BRID;	//判断身份  1超级管理员 2管理员 3商户
/*	private String idCard;//身份证
	private String holdCardImg;//手持证件照
	private String faceCardImg;//身份证正面照
	private String backCardImg;//身份证反面照
	private String shopName;//商家名称
	private Long shopType;//商家类型 （字典表）
	private String businessScope;//经营范围
	private Long accountType;//账户类型 （字典表）
	private String accountName;//账户名称	
	private String bankCard;//银行卡号
	private String accountBank;//开户行
	private Integer isLicense;//是否有营业执照
	private String licenseImg;//营业执照照片
	private String otherImg1;//其他证件照1
	private String otherImg2;//其他证件照2
	private String createTime;//创建时间
	private String shopToken;//token 
	private String shopImg;//商家logo 
	private Integer state;//商家状态（0，正常，1停用，2删除）
	private Integer isAudit;//审核（0，提交审核，1审核通过，2审核不通过）
	private String cashPassWord;//提现密码
*/	public Long getId() {
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
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getShopToken() {
		return shopToken;
	}
	public void setShopToken(String shopToken) {
		this.shopToken = shopToken;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getShopInformationId() {
		return shopInformationId;
	}
	public void setShopInformationId(Long shopInformationId) {
		this.shopInformationId = shopInformationId;
	}
	public Long getBRID() {
		return BRID;
	}
	public void setBRID(Long bRID) {
		BRID = bRID;
	}
	
}
