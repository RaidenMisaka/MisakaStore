package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.City;
import cn.misaka.store.mapper.CityMapper;

public class CityMapperTest {
	@Test
	public void selectProvinceListTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		CityMapper cityMapper = ac.getBean("cityMapper", CityMapper.class);
		List<City> cities = cityMapper.getCityListByProvinceCode("110000");
		for ( City c : cities ) {
			System.out.println(c);
		}
		ac.close();
	}
	
	@Test
	public void getProvinceByCodeTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		CityMapper cityMapper = ac.getBean("cityMapper", CityMapper.class);
		City city = cityMapper.getCityByCode("140500");
		System.out.println(city);
		ac.close();
	}
}
