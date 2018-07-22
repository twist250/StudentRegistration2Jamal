package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernatUtil {
	private static SessionFactory sessionFactory;

	public static Session getSession() {
		setSessionFactory();
		return sessionFactory.openSession();
	}

	private static void setSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			HibernatUtil.sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void shutDown() {
		sessionFactory.close();
	}
}
