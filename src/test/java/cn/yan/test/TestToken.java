package cn.yan.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.yan.exam.Application;
import cn.yan.exam.entity.User;
import cn.yan.exam.token.TokenDao;

@SpringBootTest(classes=Application.class)
@RunWith(SpringRunner.class)
public class TestToken {
	@Autowired
	private TokenDao tokenDao;
	
	@Test
	public void testTokenDao() {
		String token = tokenDao.createToken(1);
		System.out.println("token:" + token);
	}
}
