package test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.misaka.store.entity.Area;
import cn.misaka.store.mapper.AreaMapper;

public class AreaMapperTest {
	@Test
	public void selectAreaListTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AreaMapper areaMapper = ac.getBean("areaMapper", AreaMapper.class);
		List<Area> areas = areaMapper.getAreaListByCityCode("140500");
		for (Area a : areas) {
			System.out.println(a);
		}
		ac.close();
	}

	@Test
	public void getAreaByCodeTest() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-dao.xml");
		AreaMapper areaMapper = ac.getBean("areaMapper", AreaMapper.class);
		Area area = areaMapper.getAreaByCode("140581");
		System.out.println(area);
		ac.close();
	}
}
