package com.htrj.common.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Hejue
 */
public class DatePropertyEditor extends PropertyEditorSupport {

	String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS",
			"HH:mm:ss", "yyyy-MM-dd", "MM/dd/yyyy", "MM/dd/yy" };

	Date defaultDate = new Date();

	@Override
	public String getAsText() {
		return getValue() == null ? "" : CalendarHelper.format(
				(Date) getValue(), "yyyy-MM-dd");
	}

	@Override
	public void setAsText(String textValue) {
		if (StringUtils.isBlank(textValue)) {
//			setValue(defaultDate);
			return;
		} else {
			Date retDate = defaultDate;
			try {
				retDate = DateUtils.parseDate(textValue, formats);
			} catch (ParseException e) {
				log.error("Cannot parse " + textValue + " as date.", e);
			}
			setValue(retDate);
		}
	}

	private final Logger log = LoggerFactory.getLogger(getClass());

}
