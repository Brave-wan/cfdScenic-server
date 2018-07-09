package com.htrj.common.utils;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ListDataSource implements JRDataSource {
	private List<Map<String, Object>>	data;
	private int							index	= -1;

	public ListDataSource(List<Map<String, Object>> data) {
		this.data = data;
	}

	/**
	 * 实现了 JRDataSource 中的方法．判断是否还有下一个．
	 */
	public boolean next() throws JRException {
		index++;
		return (index < data.size());
	}

	/**
	 * 实现了 JRDataSource 中的方法．
	 * 
	 * @param field
	 *            是对应报表中的要填充的字段的名称．
	 */
	public Object getFieldValue(JRField field) throws JRException {
		String fieldName = field.getName();
		Map<String, Object> obj = data.get(index);
		return obj.get(fieldName);
	}
}
