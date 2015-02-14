package logic;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration configuration = new Configuration().configure(); 
			serviceRegistry = new ServiceRegistryBuilder().
					applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public HibernateUtil(){
    	sessionFactory = buildSessionFactory();
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
	public static void closeSessionFactory(){
		if(!sessionFactory.isClosed())
			sessionFactory.close();
	}
	
}
