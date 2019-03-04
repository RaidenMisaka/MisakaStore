package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.User;
import cn.misaka.store.service.IUserService;
import cn.misaka.store.service.ex.ServiceException;

public class UserServiceTest {

	@Test
	public void regTest() {
		AbstractApplicationContext ac = 
				new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ac.getBean("userService", IUserService.class);
		try {
			User user = new User();
			user.setUsername("abcd");
			user.setPassword("6666999");
			user.setPhone("21231321");
			user.setEmail("8889@163.com");
			userService.reg(user);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
	
	@Test
	public void loginTest() {
		AbstractApplicationContext ac = 
				new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ac.getBean("userService", IUserService.class);
		try {
			userService.login("root111", "123456");
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
	
	@Test
	public void changePasswordTest() {
		AbstractApplicationContext ac = 
				new ClassPathXmlApplicationContext("spring-dao.xml", "spring-service.xml");
		IUserService userService = ac.getBean("userService", IUserService.class);
		try {
			Integer result = userService.changePassword(11,  "123456","1a23456");
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
}
