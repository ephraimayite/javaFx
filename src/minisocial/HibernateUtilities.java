/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 *
 * @author Ephraim
 */
public class HibernateUtilities {
    
    private static SessionFactory sessionFactory;
    private static  ServiceRegistry serviceRegistry;
    
    
    static {
    try{
        Configuration configuration;
        configuration = new Configuration().configure("hibernate.cfg.xml");
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    catch(HibernateException exception){
    System.out.println("Problem creating Session factory .....");
   System.out.println(exception.getMessage()+"is the message");
    }
    
     
    }
    
     static SessionFactory getSessionFactory(){
    return sessionFactory;
    }
}
