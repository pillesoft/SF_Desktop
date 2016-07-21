/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.bl;

import com.ibh.safepassword.dal.AuthenticationRepository;
import com.ibh.safepassword.dal.CategoryRepository;
import com.ibh.safepassword.dal.DbContext;

/**
 *
 * @author ihorvath
 */
public class BusinessLogic {

  private final CategoryRepository categRepos;
  private final AuthenticationRepository authRepos;
  private String loggedInName;
  
  public BusinessLogic() {
    categRepos = new CategoryRepository();
    authRepos = new AuthenticationRepository();
  }

  public CategoryRepository getCategRepos() {
    return categRepos;
  }

  public AuthenticationRepository getAuthRepos() {
    return authRepos;
  }

  public String getLoggedInName() {
    return loggedInName;
  }
  
  public boolean Login(String DbName, char[] pwd, char[] encrpwd) {
    try {
      DbContext.Connect(DbName, pwd, encrpwd);
      loggedInName = DbName;
      return true;
    }
    catch (Exception e) {
      java.util.logging.Logger.getLogger(BusinessLogic.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
      return false;
    }
  }

  public boolean Create(String DbName, char[] pwd, char[] encrpwd) {
    try {
      DbContext.CreateDatabase(DbName, pwd, encrpwd);
      loggedInName = DbName;
      return true;
    }
    catch (Exception e) {
      java.util.logging.Logger.getLogger(BusinessLogic.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
      return false;
    }
  }
  
}
