package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.Province;
import cn.misaka.store.mapper.ProvinceMapper;


public class ProvinceMapperTest {
	@Test
	public void selectProvinceListTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		ProvinceMapper provinceMapper = ac.getBean("provinceMapper", ProvinceMapper.class);
		List<Province> provinces = provinceMapper.getProvinceList();
		for ( Province p : provinces ) {
			System.out.println(p);
		}
		ac.close();
	}
	
	@Test
	public void getProvinceByCodeTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		ProvinceMapper provinceMapper = ac.getBean("provinceMapper", ProvinceMapper.class);
		Province province = provinceMapper.getProvinceByCode("110000");
		System.out.println(province);
		ac.close();
	}
}
