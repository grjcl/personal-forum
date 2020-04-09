package com.forum.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.forum.entity.User;
import com.forum.service.IUserService;
import com.forum.service.exception.ServiceException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private IUserService userService;

	
	
	//====注册====
	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("李四");
			user.setPassword("1234");
			user.setEmail("roots@qq.com");
			User result = userService.reg(user);
			System.err.println("result=" + result);
		} catch (ServiceException e) {
			System.err.println("错误类型==" + e.getClass().getName());
			System.err.println("错误描述==" + e.getMessage());
		}
	}
	
	//====登录====
	@Test
	public void login() {
		try {
			String username = "张三";
			String password= "1234";
			User result = userService.login(username, password);
			System.err.println("result=" + result);
		} catch (ServiceException e) {
			System.err.println("错误类型==" + e.getClass().getName());
			System.err.println("错误描述==" + e.getMessage());
		}
	}
	
	//====修改密码====
	@Test
	public void changePassword() {
		try {
			Integer uid = 5;
			String oldPassword = "1234";
			String newPassword = "123456";
			userService.changePassword(uid, oldPassword, newPassword);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}
	}
	
	//====修改资料====
	@Test
	public void changeInfo() {
		try {
			User user = new User();
			user.setId(12);
			user.setGender("男");
			user.setBirthday("2020-02-32");
			user.setSynopsis("123456");
			user.setEmail("qwer@qq.com");
			userService.changeInfo(user);
			System.err.println("OK.");
		} catch (ServiceException e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}
	}

}
