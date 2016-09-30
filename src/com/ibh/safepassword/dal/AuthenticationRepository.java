/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import com.ibh.safepassword.bl.Crypt;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
