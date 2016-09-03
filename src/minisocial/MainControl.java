/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minisocial;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;



/**
 *
 * @author Ephraim
 */
public class MainControl<T> {
  private   Session session;
    public MainControl(){  
        try{
    session = HibernateUtilities.getSessionFactory().openSession();}
        catch(Exception e){
        System.out.println("the error" + "\n"+e.getMessage()+"here");
        }
    }
    public void begin(){
    session.beginTransaction();
    }
    
    public void commit(){
    session.getTransaction().commit();
    }
    
    public void closeSession(){
    session.close();
    }
    
    public void save(T object){
       
    session.save(object);}
    
    public Query query(String param){
     return session.createQuery(param);
    }
        public void update(T object){
        session.update(object);
        }
    
    public  void load(Class<T> cls,int id){
        T load;
       load = (T)session.get(cls,id);
    
    
    }
}
