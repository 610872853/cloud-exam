package cn.yan.exam.token;

import cn.yan.exam.entity.User;

public interface TokenDao {
	
	/**
	 *  根据token获取到用户id
	 */
	public int getByToken(String token);
	
	/**
	 * 生成token
	 */
	public String createToken(int userid);
	
	/**
	 * 刷新token过期时间
	 */
	public String refreshToken(String token);
}
