package com.htrj.common.utils;

import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonHelper {

	public static String serialize(Object value) {
		String json = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			json = mapper.writeValueAsString(value);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		
		return json;
	}
	

	@SuppressWarnings("unchecked")
	public static Map<String,Object> readValue(String value) {
		Map<String,Object> map = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(value, Map.class);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		
		return map;
	}
	
}
