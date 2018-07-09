package com.htrj.common.page;

import java.util.List;
import java.util.Map;

/**
 * DataTree 模型
 * 
 * @author water
 */
public class DataTree implements java.io.Serializable {

	private static final long	serialVersionUID	= 375221194420199787L;
	// 静态变量 展开节点
	public static final String	STATE_OPEN			= "open";
	// 静态变量 关闭节点
	public static final String	STATE_CLOASED		= "closed";

	private Long	id;
	private String	text;
	private Long	pid;
	private String	desc;
	private String	iconCls;
	private String	state;

	private Map<String, Object> attributes;
	private List<DataTree> children;
	private boolean checked;

	public List<DataTree> getChildren() {
		return children;
	}

	public void setChildren(List<DataTree> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
