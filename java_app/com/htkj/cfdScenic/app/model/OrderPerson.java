package com.htkj.cfdScenic.app.model;

import com.htrj.common.base.BaseEntity;
/**
 * 保存多个房间每个人姓名用
 * @author:lishilong
 * @date:2016年7月27日
 */
public class OrderPerson extends BaseEntity{
	/**
	 * Title:
	 * HttpUrl:
	 * @author:Administrator
	 * @date:2016年7月27日
	 */
	private static final long serialVersionUID = 6238558962778975510L;
	
	private Long id; //主键id
	private Long hotelOrderId; //酒店订单id
	private String name; // 姓名
	private String idCard;//身份证号
	private String createTime;//创建时间
	private Integer state;//状态（0正常，1停用）
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getHotelOrderId() {
		return hotelOrderId;
	}
	public void setHotelOrderId(Long hotelOrderId) {
		this.hotelOrderId = hotelOrderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
