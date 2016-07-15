/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author ihorvath
 */
public class BaseRepository<T> implements IRepository<T>  {

  final T entityType;
  final String entityTypeName;
  
  public BaseRepository() {
    this.entityType = (T)(Class)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    this.entityTypeName = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
  }
  
  
  
  @Override
  public void Add(T obj) {
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    
    sess.save(obj);
    
    sess.getTransaction().commit();
    sess.close();
  }

  @Override
  public void Update(T obj) {
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    
    sess.merge(obj);
    
    sess.getTransaction().commit();
    sess.close();
  }

  @Override
  public void Delete(T obj) {
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    
    sess.delete(obj);
    
    sess.getTransaction().commit();
    sess.close();
  }

  @Override
  public T GetbyId(int id) {
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();

    T ret = sess.get((Class<T>)entityType, id);
    
    sess.getTransaction().commit();
    sess.close();
    
    return ret;
  }

  @Override
  public List<T> GetList(String queryexpr) {
    
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    
    Query q = sess.createQuery(queryexpr);
    List ret = q.getResultList();
    
    sess.getTransaction().commit();
    sess.close();
    
    return ret;
    
  }

  @Override
  public List<T> GetList() {
    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    
    String query = "from " + entityTypeName;
    Query q = sess.createQuery(query);
    List ret = q.getResultList();
    
    sess.getTransaction().commit();
    sess.close();
    
    return ret;
  }
  
}
