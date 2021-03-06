package com.htkj.cfdScenic.app.model;

import java.util.Date;

import com.htrj.common.base.BaseEntity;

/**
* @ClassName: RecommendWay
* @Description: TODO(推荐路线)
* @author 张伟烁
* @date 2016年10月28日 上午10:37:05
*
*/
public class RecommendWay extends BaseEntity {
    private Long id;

    private Long groupId;
    
    private String groupName;
    
    private Integer soft;

    private String xPoint;

    private String yPoint;

    private String name;

    private String info;

    private Integer type;

    private Date creatime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Integer getSoft() {
        return soft;
    }

    public void setSoft(Integer soft) {
        this.soft = soft;
    }

    public String getxPoint() {
        return xPoint;
    }

    public void setxPoint(String xPoint) {
        this.xPoint = xPoint == null ? null : xPoint.trim();
    }

    public String getyPoint() {
        return yPoint;
    }

    public void setyPoint(String yPoint) {
        this.yPoint = yPoint == null ? null : yPoint.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}