package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;

public class ConsumerUser extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = -134705004497336045L;
	private Long id;				//用户表id
	private String trueName;		//真实姓名
	private String userName;		//用户名称
	private String nickName;		//昵称
	private Integer gender;			//性别 0 男 1 女
	private String headImg;			//头像
	private String mobileNo;		//手机号
	private String password;		//密码
	private String salt;			//
	private Integer isVirtual;		//是否是虚拟账户
	private Integer state;			//状态  0 可用   1 停用
	private String createTime;		//申请日期
	private String creator;			//申请人
	private String openId;			//第三方关联字段
	private Integer source;			//来源 1.iOS 2 Android 3 qq 4 weixin 5 weibo
	private Integer userType;		//用户类型  1 用户  2 商户  3 后台
	private String token;			//用户标志
	private String address;			//地址
	private String latitude;		//经度
	private String longitude;		//纬度
	private String cashPassword;	//提现密码
	private String idCard;			//身份证
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getIsVirtual() {
		return isVirtual;
	}
	public void setIsVirtual(Integer isVirtual) {
		this.isVirtual = isVirtual;
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCashPassword() {
		return cashPassword;
	}
	public void setCashPassword(String cashPassword) {
		this.cashPassword = cashPassword;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
