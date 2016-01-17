package javaHibernate;

import java.util.List;

public class EmployeeService {
	EmployeeDao empd = new EmployeeDao();

	public void add(Employee emp) {		
		empd.add(emp);
	}
	
	public void update(Employee employee) {
		empd.update(employee);		
	}

	public void delete(int id) {
		empd.delete(id);		
	}

	public List<Employee> list(Employee emp) {		
		return empd.list();
	}

	public Employee getEmployeeById(int id) {
		return empd.getEmployeeById(id);
	}	

	public Employee getEmployeeByFirstname(String firstname) {
		return empd.getEmployeeByFirstname(firstname);
	}
}
