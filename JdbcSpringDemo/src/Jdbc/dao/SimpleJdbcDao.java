package Jdbc.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDao extends SimpleJdbcDaoSupport {
	
	public int getEmployeeCount() {
		
		String sql="SELECT COUNT(*) FROM EMPLOYEE";
		
		return this.getJdbcTemplate().queryForObject(sql,Integer.class);
		
	}
	

}
