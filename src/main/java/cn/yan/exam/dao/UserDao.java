package cn.yan.exam.dao;


import org.apache.ibatis.annotations.Mapper;

import cn.yan.exam.entity.User;

@Mapper
public interface UserDao {
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
