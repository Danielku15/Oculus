package at.itb13.oculus.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 * 
 * This class creates a SessionFactory object which can open up new sessions.
 *
 */
public final class HibernateUtil {
		
	private static SessionFactory _sessionFactory;
	
	static {
		Configuration configuration= new Configuration();
		configuration.configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		_sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	private HibernateUtil() {}
	
	public static Session openSession() {
		return _sessionFactory.openSession();
	}
}
