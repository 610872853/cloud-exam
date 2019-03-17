package cn.yan.exam.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component("redisDao")
public class RedisDaoImpl implements RedisDao{
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public boolean set(String key, String value) {
		if(key == null) {
			return false;
		}
		redisTemplate.opsForValue().set(key, value);
		return true;
	}

	@Override
	public boolean set(String key, String value, long timeout) {
		if(key == null) {
			return false;
		}
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public boolean expired(String key, long timeout) {
		if(key == null) {
			return false;
		}
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		return true;
	}

	@Override
	public String get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public boolean isExist(String key) {
		return redisTemplate.hasKey(key);
	}
	
	
	
}
