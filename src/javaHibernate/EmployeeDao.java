package javaHibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class EmployeeDao {

	//SessionFactory sessionFactory = HibernateConfiguration.getSessionFactoryForAnnotation();
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = (new HibernateUtil()).session();
	Transaction tx = null;

	public void add(Employee emp) {
		try {
			tx = session.beginTransaction();
			session.save(emp);
			tx.commit();
			System.out.println("Sussessfully saved");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(Employee emp) {
		try {
			tx = session.beginTransaction();
			session.update(emp);
			tx.commit();
			System.out.println("Sussessfully upated");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void delete(int id) {
		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, id);
			session.delete(employee);
			tx.commit();
			System.out.println("Sussessfully deleted");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings({ "finally", "unchecked" })
	public List<Employee> list() {
		List<Employee> employees = null;
		try {
			tx = session.beginTransaction();
			employees = session.createQuery("FROM Employee").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			return employees;
		}
	}

	public Employee getEmployeeById(int id) {
		Employee emp = (Employee) session.get(Employee.class, id);
		return emp;
	}

	public Employee getEmployeeByFirstname(String firstname) {

		String hql = "FROM Employee E WHERE E.firstname = :firstname";
		Query query = session.createQuery(hql);
		query.setParameter("firstname", firstname);
		List<Employee> results = query.list();
		return results.get(0);
	}

	public Employee getEmployeeByFirstnameUsingCriteria(String firstname) {

		Criteria crit = session.createCriteria(Employee.class);
		crit.add(Restrictions.eq("firstname", firstname));
		List<Employee> emp = crit.list();
		return emp.get(0);

	}
	public List<Employee> getEmployeeByCriteria(){
		Criterion salary = Restrictions.gt("salary", 2000);
		Criterion name = Restrictions.ilike("firstname","fa%");
		Criteria cr = session.createCriteria(Employee.class);

		// To get records matching with OR condistions
		LogicalExpression orExp = Restrictions.or(salary, name);
		cr.add( orExp );
		cr.addOrder(Order.desc("salary"));

		// To get records matching with AND condistions
		LogicalExpression andExp = Restrictions.and(salary, name);
		cr.add( andExp );

		List emps = cr.list();
		return emps;
	}

}
