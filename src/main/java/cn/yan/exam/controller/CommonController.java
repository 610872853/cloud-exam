package cn.yan.exam.controller;

import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yan.exam.common.Constants;
import cn.yan.exam.dao.UserDao;
import cn.yan.exam.entity.User;
import cn.yan.exam.rest.CodeResult;
import cn.yan.exam.service.UserService;
import cn.yan.exam.token.TokenDao;
import cn.yan.exam.util.BeanUtil;

@RestController
public class CommonController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TokenDao tokenDao;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="getToken")
	public CodeResult getToken(String username,String password) {
		User userFind = new User();
		userFind.setUsername(username);
		userFind.setPassword(password);
		User user = userService.findByUsernameAndPassword(userFind);
		if(user == null) {
			return CodeResult.error(null);
		}
		String token = tokenDao.createToken(user.getId());
		logger.debug("用户信息" + user + "  token:" + token);
		Map<String, Object> result = BeanUtil.beanToMap(user);
		result.put(Constants.KEY_TOKEN, token);
		return CodeResult.success(result);
	}
}
