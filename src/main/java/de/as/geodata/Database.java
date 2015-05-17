package de.as.geodata;

import de.as.geodata.app.entities.PersitentEntity;
import org.hibernate.*;

public class Database {

    protected static SessionFactory SESSION_FACTORY;
    
    private Session session;
    
    protected void openSession(){
        if(session == null){
            session = SESSION_FACTORY.openSession();
        }
    }
    
    public void beginTransaction(){
        getSession().beginTransaction();
    }
    
    public Criteria createCriteriaFor(Class c){
        return getSession().createCriteria(c);
    }
    
    public <T> T findById(Class<T> c, Integer id){
        return (T) getSession().get(c, id);
    }
    
    public void save(PersitentEntity e){
        getSession().saveOrUpdate(e);
    }
    
    
    public void commitTransaction(){
        getSession().getTransaction().commit();
    }
    
    protected void closeSession(){
        getSession().close();
    }
    
    private Session getSession(){
        return SESSION_FACTORY.getCurrentSession();
    }
    

}
