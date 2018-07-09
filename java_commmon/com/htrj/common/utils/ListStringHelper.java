package com.htrj.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.htrj.common.beanutils.BeanUtils;


public class ListStringHelper {
	/**
	 * 定义分割常量 （#在集合中的含义是每个元素的分割，|主要用于map类型的集合用于key与value中的分割）
	 */
	private static final String	SEP1	= "#";
	private static final String	SEP2	= "|";

	/**
	 * List转换String
	 * 
	 * @param list
	 *            :需要转换的List
	 * @return String转换后的字符串
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static String ListToString(List<?> list) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		StringBuffer sb = new StringBuffer();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == null || list.get(i) == "") {
					continue;
				}
				// 如果值是list类型则调用自己
				if (list.get(i) instanceof List) {
					sb.append(ListToString((List<?>) list.get(i)));
					sb.append(SEP1);
				} else if (list.get(i) instanceof Map) {
					sb.append(MapToString((Map<?, ?>) list.get(i)));
					sb.append(SEP1);
				} else if (Introspector.getBeanInfo(list.get(i).getClass()) instanceof BeanInfo) {
					sb.append(MapToString((Map<?, ?>) BeanUtils.transBean2Map(list.get(i))));
					sb.append(SEP1);
				} else {
					sb.append(list.get(i));
					sb.append(SEP1);
				}
			}
		}
		return "L" + sb.toString();
	}

	/**
	 * Map转换String
	 * 
	 * @param map
	 *            :需要转换的Map
	 * @return String转换后的字符串
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static String MapToString(Map<?, ?> map) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		StringBuffer sb = new StringBuffer();
		// 遍历map
		for (Object obj : map.keySet()) {
			if (obj == null) {
				continue;
			}
			Object key = obj;
			Object value = map.get(key);
			if (value instanceof List<?>) {
				sb.append(key.toString() + SEP1 + ListToString((List<?>) value));
				sb.append(SEP2);
			} else if (value instanceof Map<?, ?>) {
				sb.append(key.toString() + SEP1 + MapToString((Map<?, ?>) value));
				sb.append(SEP2);
			} else if (value != null) {
				sb.append(key.toString() + SEP1 + value.toString());
				sb.append(SEP2);
			} else {
				sb.append(key.toString() + SEP1 + "");
				sb.append(SEP2);
			}
		}
		return "M" + sb.toString();
	}

	/**
	 * String转换Map
	 * 
	 * @param mapText
	 *            :需要转换的字符串
	 * @param KeySeparator
	 *            :字符串中的分隔符每一个key与value中的分割
	 * @param ElementSeparator
	 *            :字符串中每个元素的分割
	 * @return Map<?,?>
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Map<String, Object> StringToMap(String mapText) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {

		if (mapText == null || mapText.equals("")) {
			return null;
		}
		mapText = mapText.substring(1);
		Map<String, Object> map = new HashMap<String, Object>();
		String[] text = mapText.split("\\" + SEP2); // 转换为数组
		for (String str : text) {
			String[] keyText = str.split("\\" + SEP1); // 转换key与value的数组
			if (keyText.length < 1) {
				continue;
			}
			String key = keyText[0]; // key
			Object value = null;// value
			if (keyText.length > 1) {
				value = keyText[1]; // value
			}
			if (value != null)
				if (value.toString().charAt(0) == 'M') {
					Map<?, ?> map1 = StringToMap(value.toString());
					map.put(key, map1);
				} else if (value.toString().charAt(0) == 'L') {
					List<?> list = StringToList(value.toString());
					map.put(key, list);
				} else {
					map.put(key, value);
				}
		}
		return map;
	}

	/**
	 * String转换List
	 * 
	 * @param listText
	 *            :需要转换的文本
	 * @return List<?>
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static List<Object> StringToList(String listText) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		if (listText == null || listText.equals("")) {
			return null;
		}
		listText = listText.substring(1);
		List<Object> list = new ArrayList<Object>();
		String[] text = listText.split("\\|#");
		for (String str : text) {
			System.out.println("str.charAt(0) =" + str.charAt(0));
			if (str.charAt(0) == 'M') {
				Map<?, ?> map = StringToMap(str);
				list.add(map);
			} else if (str.charAt(0) == 'L') {
				List<?> lists = StringToList(str);
				list.add(lists);
			} else {
				list.add(str);
			}
		}
		return list;
	}

}
