package com.htrj.common.beanutils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * apache BeanUtils的等价类，只是将check exception改为uncheck exception
 * 
 * @author badqiu
 */
public class BeanUtils {

	private static void handleReflectionException(Exception e) {
		ReflectionUtils.handleReflectionException(e);
	}

	public static Object cloneBean(Object bean) {
		try {
			return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T copyProperties(Class<T> destClass, Object orig) {
		try {
			Object target = destClass.newInstance();
			copyProperties(target, orig);
			return (T) target;
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static void copyProperties(Object dest, Object orig) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static void copyProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperty(bean, name, value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	public static String[] getArrayProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getArrayProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getIndexedProperty(Object bean, String name, int index) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(bean, name, index);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getIndexedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getIndexedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getMappedProperty(Object bean, String name, String key) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(bean, name, key);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getMappedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getMappedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getNestedProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getNestedProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static String getSimpleProperty(Object bean, String name) {
		try {
			return org.apache.commons.beanutils.BeanUtils.getSimpleProperty(bean, name);
		} catch (Exception e) {
			handleReflectionException(e);
			return null;
		}
	}

	public static void setProperty(Object bean, String name, Object value) {
		try {
			org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, value);
		} catch (Exception e) {
			handleReflectionException(e);
		}
	}

	/**
	 * Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	 * 
	 * @param 源Map
	 * @param 目标Bean
	 */
	public static void transMap2Bean(Map<String, Object> map, Object obj) {
		if(map != null && !map.isEmpty()){
			for(String key:map.keySet()){
				if(StringUtils.isNotBlank(key) && map.get(key) != null)
				try {
					org.apache.commons.beanutils.BeanUtils.setProperty(obj, key, map.get(key));
				} catch (IllegalAccessException e) {
					handleReflectionException(e);
				} catch (InvocationTargetException e) {
					handleReflectionException(e);
				}
			}
		}

//		try {
//			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//
//			for (PropertyDescriptor property : propertyDescriptors) {
//				String key = property.getName();
//
//				if (map.containsKey(key)) {
//					Object value = map.get(key);
//					// 得到property对应的setter方法
//					Method setter = property.getWriteMethod();
//					setter.invoke(obj, value);
//				}
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("transMap2Bean Error " + e);
//		}

		return;

	}

	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * 
	 * @param 源Bean
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj, new Object[0]);
					map.put(key, value);

				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
}
