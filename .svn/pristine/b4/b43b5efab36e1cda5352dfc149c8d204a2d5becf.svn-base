package com.htrj.common.base;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtilsBean;

import com.htrj.common.utils.JsonHelper;

/**
 * @author Hejue
 */
@SuppressWarnings("serial")
public abstract class BaseEntity implements java.io.Serializable {

	@Override
	public String toString() {
		String json = null;
		try {
			json = JsonHelper.serialize(this);
		} catch (Exception ex) {
			return super.toString();
		}
		return json;
	}
	
	/**
	 * 
	 * @Title: toMap 
	 * @Description: TODO(将实体类转化成Map) 
	 * @param: @return    
	 * @return: Map<String,Object>    
	 * @throws
	 */
	public Map<String, Object> toMap(){
		Map<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(this); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                Object value = propertyUtilsBean.getNestedProperty(this, name);
                if (value != null) { 
                    params.put(name, value); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params; 
	}

}
