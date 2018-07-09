package com.htrj.common.page;

public class DeptEmploy implements java.io.Serializable {
	private static final long	serialVersionUID	= 1535372824610721876L;

	public Integer				id;
	public String				code;
	public String				name;
	public String				dept;
	public Integer				type;

	public DeptEmploy() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
