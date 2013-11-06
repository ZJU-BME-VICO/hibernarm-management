package org.hibernarm.management.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static final Configuration conf;
	private static final SessionFactory factory;
	private static final ThreadLocal<Session> sessions=new ThreadLocal<Session>();
	static {
		conf = new Configuration();
		conf.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(conf.getProperties()).buildServiceRegistry();
		factory = conf.buildSessionFactory(serviceRegistry);
	}
    public static Session currentSession(){
    	Session s=sessions.get();
    	if(s==null){
    		s=factory.openSession();
    		sessions.set(s);
    	}
    	return s;
    }
    public static void closeSession(){
    	Session s=(Session)sessions.get();
    	if(s!=null){
    		s.close();
            sessions.set(null);
    	}
    }

}
