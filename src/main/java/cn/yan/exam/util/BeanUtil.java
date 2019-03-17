package cn.yan.exam.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.alibaba.fastjson.JSON;

/**
 * bean与map转换、bean与json转换工具类
 *	  使用Dozer进行转换
 * @author yan
 *
 */
public class BeanUtil {
	private static Logger logger = Logger.getLogger(BeanUtil.class);
	private static DozerBeanMapper mapper = new DozerBeanMapper();
	
	/**
	 * 基于Dozer将bean转换成map
	 * @param bean 实体类
	 * @return
	 */
	public static Map<String,Object> beanToMap(Object bean) {
		if(bean == null) {
			return new HashMap<String,Object>();
		}
		Map<String,Object> map = mapper.map(bean, Map.class);
		return map;
	}
	
	/**
	 * 基于Dozer将map转换成bean
	 * @param map map集合
	 * @param requiredClass 要转换成的实体类名
	 * @return
	 */
	public static <T> T mapToBean(Map<String,Object> map,Class<T> requiredClass) {
		if(map == null) {
			return null;
		}
		T bean = mapper.map(map, requiredClass);
		return bean;
	}
	
	/**
	 * 基于Dozer将map转换成object
	 * @param map
	 * @return
	 */
	public static Object mapToObject(Map<String,Object> map,Class requiredClass) {
		return mapToBean(map,requiredClass);
	}
	
	/**
	 * 基于fastjson将bean转换成json字符串
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		if(bean == null) {
			return null;
		}
		String json = JSON.toJSONString(bean);
		return json;
	}
	
	
	/**
	 * 基于fastjson将json字符串转换成bean
	 * @param json
	 * @param requiredClass
	 * @return
	 */
	public static <T> T jsonToBean(String json,Class<T> requiredClass) {
		if(json == null) {
			return null;
		}
		try {
			T bean = JSON.parseObject(json, requiredClass);
			return bean;
		} catch (Exception e) {
			logger.error(json + " 无法转换成实体");
			return null;
		}
	}
	
}
