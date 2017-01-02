/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import com.ibh.safepassword.bl.Crypt;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ihorvath
 */
public class AuthenticationRepository extends BaseRepository<Authentication> {
  public List GetAuthLimited(String filtertitle, String filtercateg) {
    List ret = GetList("SELECT a.title, a.category.name, a.weburl, DATEDIFF('DAY', a.validfrom, CURRENT_DATE()) as numbofdays, a.description, a.id, a.category.color FROM Authentication a");
    
    return ret;
  }

  public ObservableList<AuthLimited> GetAuthLimited1() {
    
    List ret = GetList("SELECT a.title, a.category.name, a.weburl, DATEDIFF('DAY', a.validfrom, CURRENT_DATE()) as numbofdays, a.description, a.id, a.category.color FROM Authentication a");
    
    ArrayList<AuthLimited> list_al = new ArrayList<AuthLimited>();
    
    for (Object r : ret) {
      Object[] row = (Object[])r;
      
      String webaddr = "";
      if (row[2] != null) {
        webaddr = row[2].toString();
      }
      String descr = "";
      if (row[4] != null) {
        descr = row[4].toString();
      }

      AuthLimited al = new AuthLimited(Integer.decode(row[5].toString()), row[1].toString(), row[0].toString(), webaddr, Integer.decode(row[3].toString()), descr, row[6].toString());
      list_al.add(al);
    }
    
    return FXCollections.observableList(list_al);
  }
  
  @Override
  public Authentication GetbyId(int id) {
    Authentication a = super.GetbyId(id);
    a.setPwdClearInit();
    
    return a;
  }
  
  public AuthInfo GetAuthInfo(int id) {
    Authentication a = GetbyId(id);
    AuthInfo ret = new AuthInfo(a.getId(), a.getUsername(), a.getPassword(), a.getTitle());
    return ret;
  }

  @Override
  public void Update(Authentication obj) throws IBHDbConstraintException {
    Authentication old = GetbyId(obj.getId());

    Session sess = DbContext.getSessionFactory().openSession();
    sess.beginTransaction();
    try {
      if (obj.getIsPwdChanged()) {
        // make pwdchnaged
        AuthPwdHistory hist = new AuthPwdHistory(obj, old.getPassword());
        sess.save(hist);
      }
      sess.merge(obj);
      sess.getTransaction().commit();
    } catch (ConstraintViolationException exc) {
      throw parseConstraintExc(exc);
    } finally {
      sess.close();
    }
  }
  
  
          
}
