package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.Address;
import cn.misaka.store.service.IAddressService;

public class AddressServiceTest {
	
	@Test
	public void addTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IAddressService addressService = ac.getBean("addressService",IAddressService.class);
		Address address = new Address();
		String username = "admin";
		address.setUid(66);
		address.setRecvName("zhangsansan");
		addressService.add(username, address);
		ac.close();
	}
}
