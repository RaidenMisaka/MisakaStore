package test;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionTest {
	
	@Test
	public void test() {
		AbstractApplicationContext ac = new ClassPathXmlApplicationContext("spring-service.xml");
		BasicDataSource dataSource = ac.getBean("dataSource",BasicDataSource.class);
		try {
			Connection conn = dataSource.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ac.close();
		}
		
	}
}
