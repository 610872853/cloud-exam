package cn.yan.exam.token;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yan.exam.entity.User;
import cn.yan.exam.redis.RedisDao;

@Component("tokenDao")
public class RedisTokenDaoImpl implements TokenDao{
	private final static long EXPIRED_TIME = 60 * 1000 * 8;      //过期时间为8小时
	private final static String TOKEN_PREFFIX = "Token:";       //Token键名前缀
	@Autowired
	private RedisDao redisDao;

	@Override
	public int getByToken(String token) {
		String key = TOKEN_PREFFIX + token;
		String value = redisDao.get(key);
		if(value == null || !StringUtils.isNumeric(value)) {
			return -1;
		}
		int userid = Integer.valueOf(value);
		
		return userid;
	}

	@Override
	public String createToken(int userid) {
		String token = getUID();
		String key = TOKEN_PREFFIX + token;
		String value = userid + "";
		redisDao.set(key, value, EXPIRED_TIME);
		return token;
	}
	
	@Override
	public String refreshToken(String token) {
		String key = TOKEN_PREFFIX + token;
		if(!redisDao.isExist(key)) {
			return null;
		}
		redisDao.expired(key, EXPIRED_TIME);
		return token;
	}
	
	private String getUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
}
