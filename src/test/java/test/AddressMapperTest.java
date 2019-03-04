package test;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.Address;
import cn.misaka.store.mapper.AddressMapper;

public class AddressMapperTest {

	@Test
	public void insertTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper = ac.getBean("addressMapper", AddressMapper.class);
		Address address = new Address();
		address.setUid(11);
		address.setRecvProvince("100000");
		address.setRecvCity("110000");
		address.setRecvArea("437000");
		address.setRecvPhone("18671569020");
		address.setRecvName("刘老师");
		Integer result = addressMapper.insert(address);
		System.out.println("受到影响的行数：" + result);
		ac.close();
	}
}
