package Jdbc.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Jdbc.Model.Employee;
import Jdbc.dao.JdbcDaoImp;
import Jdbc.dao.SimpleJdbcDao;

public class JdbcDemo {

	public static void main(String[] args) {
		
		ApplicationContext apx=new ClassPathXmlApplicationContext("spring.xml");
		SimpleJdbcDao dao=apx.getBean("simpleJdbcDao",SimpleJdbcDao.class);
		
		System.out.println(dao.getEmployeeCount());
		
		//System.out.println(dao.getEmployeeForId(1).getName());
		//Employee employee=dao.getEmployee(1);
		//System.out.println(employee.getName());
		
		//dao.InsertEmployee(new Employee(5,"Hamid"));
	//	System.out.println(dao.getAllEmployee().size());
		//dao.CreatTable();
	}
}
