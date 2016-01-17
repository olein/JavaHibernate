package javaHibernate;

import java.util.Iterator;
import java.util.List;

public class EmployeeController {

	public static void main(String[] args) {

		int id;

		EmployeeService es = new EmployeeService();

		/* Add few employee records in database */
//		Employee emp = new Employee("Fahim", "Fahad Olein", 70000);
//		new EmployeeService().add(emp);

//		// get emp by id
//		id = 6;
//		Employee employee = new EmployeeService().getEmployeeById(id);

		// get emp by first name
		String firstname = "fahim";
		Employee employeeByName = new EmployeeService().getEmployeeByFirstname(firstname);
		System.out.println(employeeByName.getId());

//		// update emp
//		employee.setSalary(65000);
//		new EmployeeService().update(employee);
//
//		// delete emp
//		id = 1;
//		new EmployeeService().delete(id);

//		// List emp
//		List<Employee> employees = new EmployeeService().list(emp);
//		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
//			employee = (Employee) iterator.next();
//			System.out.print("First Name: " + employee.getFirstname());
//			System.out.print("  Last Name: " + employee.getLastname());
//			System.out.println("  Salary: " + employee.getSalary());
//		}
	}
}