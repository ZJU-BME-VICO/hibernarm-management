package edu.zju.bme.hibernarm.management.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final Configuration conf;
	private static final SessionFactory factory;
	private static final ThreadLocal<Session> sessions = new ThreadLocal<Session>();

	static {
		conf = new Configuration();
		conf.configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(conf.getProperties()).build();
		factory = conf.buildSessionFactory(serviceRegistry);
	}

	public static Session currentSession() {
		Session s = sessions.get();
		if (s == null) {
			s = factory.openSession();
			sessions.set(s);
		}
		return s;
	}

	public static void closeSession() {
		Session s = (Session) sessions.get();
		if (s != null) {
			s.close();
			sessions.set(null);
		}
	}
}
