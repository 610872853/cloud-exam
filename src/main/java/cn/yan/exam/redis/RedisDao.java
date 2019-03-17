package cn.yan.exam.redis;

public interface RedisDao {
	/**
	 * 设置键值对
	 */
	public boolean set(String key,String value);
	
	/**
	 * 设置键值对，并设置过期时间
	 */
	public boolean set(String key,String value,long timeout);
	
	/**
	 * 设置过期时间
	 */
	public boolean expired(String key,long timeout);
	
	/**
	 * 根据键获取值
	 */
	public String get(String key);
	
	/**
	 * 根据键值判断是否存在该键
	 */
	public boolean isExist(String key);
}
