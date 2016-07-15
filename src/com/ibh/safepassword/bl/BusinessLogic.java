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

  private CategoryRepository categRepos;
  private AuthenticationRepository authRepos;
  
  public BusinessLogic() {
    DbContext.Init();
    categRepos = new CategoryRepository();
    authRepos = new AuthenticationRepository();
  }

  public CategoryRepository getCategRepos() {
    return categRepos;
  }

  public AuthenticationRepository getAuthRepos() {
    return authRepos;
  }
  
  
  
}
