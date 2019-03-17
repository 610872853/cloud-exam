package cn.yan.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yan.exam.dao.UserDao;
import cn.yan.exam.entity.User;
import cn.yan.exam.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public User findByUsernameAndPassword(User user) {
		return userDao.findByUsernameAndPassword(user);
	}

	@Override
	public int countUser() {
		return userDao.countUser();
	}
	
}
