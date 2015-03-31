/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 31.03.2015
 */
package at.itb13.oculus.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * @author Manu
 *
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;
    
    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }

        return sessionFactory;
    }
    
    public static Session getSession() {
    	if(session == null) {
    		session = getSessionFactory().openSession();
    	}
    	
    	return session;
    }
    
    public static void closeSession() {
    	if(session != null) {
    		session.close();
    		session = null;
    	}
    }
	
}
