package com.htrj.base.model;

import java.util.Date;

import com.htrj.common.base.BaseEntity;

public class BaseRole extends BaseEntity {

	private static final long serialVersionUID = 1805352418996898317L;
	
	private Long 	brId;			//角色id
	private String 	brName;			//角色名称
	private String 	remark;			//备注
	private String 	optId;			//操作人id
	private String 	optName;		//操作人名称
	private Date 	currTime;		//操作人时间
	private Integer sort;			//排序
	private boolean checked;		//判断是不是勾选true为勾选 false为不勾选
	
	public BaseRole(){}
	
	
	public Long getBrId() {
		return brId;
	}
	public void setBrId(Long brId) {
		this.brId = brId;
	}
	public String getBrName() {
		return brName;
	}
	public void setBrName(String brName) {
		this.brName = brName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOptId() {
		return optId;
	}
	public void setOptId(String optId) {
		this.optId = optId;
	}
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public Date getCurrTime() {
		return currTime;
	}
	public void setCurrTime(Date currTime) {
		this.currTime = currTime;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
    	
}
