package Jdbc.Model;

public class Employee {
	private int id;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Employee(int empid, String empname ) {
			setId(empid);
			setName(empname);
	}
	public Employee() {
		// TODO Auto-generated constructor stub
	}

}
