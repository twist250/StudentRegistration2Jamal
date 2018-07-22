package DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class GenericDAO<T> {
	public String create(T o) {
		Session session = HibernatUtil.getSession();
			session.beginTransaction();
			session.saveOrUpdate(o);
			session.getTransaction().commit();
			HibernatUtil.shutDown();
			return o+" Well Saved!!";
	}
	public String update(T o) {
		Session session = HibernatUtil.getSession();
		session.beginTransaction();
		session.update(o);
		session.getTransaction().commit();
		HibernatUtil.shutDown();
		return o+" Well Update!!";
	}
	public List<T> getAll(Class c) {
		Session session = HibernatUtil.getSession();
		Query query=session.createQuery("from "+c.getName());
		List<T> list = query.list();
		HibernatUtil.shutDown();
		return list;
	}
	public T getOne(Class c, Serializable id) {
		Session session = HibernatUtil.getSession();
		T o = (T)session.get(c, id);
		HibernatUtil.shutDown();
		return o;
	}
	public String delete(T o) {
		Session session = HibernatUtil.getSession();
		session.beginTransaction();
		session.delete(o);
		session.getTransaction().commit();
		session.flush();
		HibernatUtil.shutDown();
		return o+ " Well Delete";
	}
	public Set<T> query(String s) {
		try {
			Session session = HibernatUtil.getSession();
			Query query=session.createQuery(s);
			Set<T> list= (Set<T>) query.list();
			HibernatUtil.shutDown();
			return list;
		} catch (Exception e) {
			return new HashSet<>();
		}
	}

}
