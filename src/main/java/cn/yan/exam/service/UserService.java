package cn.yan.exam.service;

import cn.yan.exam.entity.User;

public interface UserService {
	/**
	 * 根据用户名密码查询用户
	 * @param user
	 * @return
	 */
	public User findByUsernameAndPassword(User user);
	
	
	/**
	 * 统计用户数量
	 * @return
	 */
	public int countUser();
}
