package test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.User;
import cn.misaka.store.mapper.UserMapper;

public class UserMapperTest {
		
	
	@Test
	public void insertTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setUsername("admins");
		user.setPassword("123456");
		user.setPhone("12345678911");
		user.setEmail("444444");
		Integer result = userMapper.insert(user);
		System.out.println("受到影响的行数：" + result);
		ac.close();
	}
	
	@Test
	public void findUserByUsername() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = userMapper.findUserByUsername("admin");
		System.out.println(user);
		ac.close();
	}
	
	@Test
	public void findUserByIdTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = userMapper.findUserById(1);
		System.out.println(user);
		ac.close();
	}
	
	@Test
	public void changePasswordTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		Integer result = userMapper.changePassword(10,"a123456","admin",new Date());
		System.out.println(result);
		ac.close();
	}
	
	@Test
	public void changeInfoTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper = ac.getBean("userMapper",UserMapper.class);
		User user = new User();
		user.setId(12);
		user.setUsername("mysql456");
		user.setGender(1);
		user.setModifiedTime(new Date());
		user.setModifiedUser("mysql123");
		user.setEmail("1349376700@qq.com");
		Integer result = userMapper.changeInfo(user);
		System.out.println(result);
		ac.close();
	}
}
