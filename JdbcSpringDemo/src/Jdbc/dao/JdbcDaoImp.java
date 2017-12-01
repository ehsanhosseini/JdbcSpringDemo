package Jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import Jdbc.Model.Employee;

@Component
public class JdbcDaoImp {
	

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate  nameParameterJdbcTemplate;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate= new JdbcTemplate(dataSource);
		this.nameParameterJdbcTemplate= new  NamedParameterJdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
/*
	public Employee getEmployee(int empid) {
		
		Connection con=null;
		try {
		
		con=dataSource.getConnection();
		
		PreparedStatement ps=con.prepareStatement("SELECT * FROM employee where id = ? ");
		ps.setInt(1, empid);
		
		
		Employee employee=null;
		ResultSet rs=ps.executeQuery();
		
		if(rs.next()) {
			employee=new Employee(empid, rs.getString("name"));
		}
		
		rs.close();
		ps.close();
		return employee;
		}catch(Exception e) { throw new RuntimeException(e);}
		finally {
			try {
			con.close();
			}catch(SQLException e) {}
		}
	}
*/

	public int getEmployeeCount() {
	
		String sql="SELECT COUNT(*) FROM EMPLOYEE";
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
		
	}
	
	
	public String getEmployeeName(int employeeId) {
		
		String sql="SELECT NAME FROM EMPLOYEE WHERE ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {employeeId}, String.class);
		
	}
	
public Employee getEmployeeForId(int employeeId) {
		
		String sql="SELECT * FROM EMPLOYEE WHERE ID=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {employeeId}, new EmployeeMapper());
		
}
	


public List<Employee> getAllEmployee(){
	
	String sql="SELECT * FROM EMPLOYEE";
	return jdbcTemplate.query(sql, new EmployeeMapper());
	
}
/*

public void InsertEmployee(Employee employee) {
	
	String sql="INSERT INTO EMPLOYEE (ID,NAME) VALUES(?,?)";
	jdbcTemplate.update(sql,new Object[] {employee.getId(), employee.getName()});
}
*/

public void InsertEmployee(Employee employee) {
	
	String sql="INSERT INTO EMPLOYEE (ID,NAME) VALUES(:id,:name)";
	SqlParameterSource namedParameter=new MapSqlParameterSource("id", employee.getId()).addValue("name", employee.getName());
	nameParameterJdbcTemplate.update(sql, namedParameter);
}


public void CreatTable() {
	
	String sql="CREATE TABLE STUDENT (ID INTEGER, NAME VARCHAR(50))";
	jdbcTemplate.execute(sql);
}

	
	public static final class EmployeeMapper implements RowMapper<Employee>{
		@Override
		public Employee mapRow(ResultSet resultset, int rownum) throws SQLException{
			
			Employee em=new Employee();
			em.setName(resultset.getString("Name"));
			em.setId(resultset.getInt("ID"));
			return em;
			
		}
		
	}
	
	
	
	
	
	
	
}
