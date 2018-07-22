package DAO;

import Domain.Student;
import org.hibernate.Session;

import java.io.Serializable;

public class StudentDAO extends GenericDAO<Student> {
	public static Serializable register(Student o) {
		Session session = HibernatUtil.getSession();
		session.beginTransaction();
		Serializable id=session.save(o);
		session.getTransaction().commit();
		HibernatUtil.shutDown();
		return id;
	}
}
