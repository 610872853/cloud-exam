package cn.yan.exam.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 实体工具类
 *
 */
public class ObjectUtil {
	/**
	 * Object类型转换成int，如果无法转换，则返回0
	 * @param obejct
	 * @return
	 */
	public static int object2Int(Object object) {
		int value = 0;
		if(object == null) {
			return value;
		}
		String str = String.valueOf(object);
		if(StringUtils.isNumeric(str)) {
			return Integer.valueOf(str);
		}
		return value;
	}
	
	/**
	 * bject转Date实体，如果解析出错，则返回当前日期，默认格式为yyyy-MM-dd HH:mm:ss
	 * @param object
	 * @return
	 */
	public static Date object2Date(Object object) {
		return object2Date(object, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * object按指定格式转Date实体，如果解析出错，则返回当前日期
	 * @param object
	 * @return
	 */
	public static Date object2Date(Object object,String pattern) {
		Date date = new Date();
		if(object == null) {
			return date;
		}
		String strDate = String.valueOf(object);
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		try {
			Date parseDate = dateFormat.parse(strDate);
			return parseDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return date;
		}
	}
	
	/**
	 * object转换成字符串，如果object为null则返回null
	 * @param object
	 * @return
	 */
	public static String object2String(Object object) {
		if(object == null) {
			return null;
		}
		return String.valueOf(object);
	}
}
