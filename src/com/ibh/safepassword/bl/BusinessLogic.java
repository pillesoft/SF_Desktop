/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.bl;

import com.ibh.safepassword.dal.CategoryRepository;
import com.ibh.safepassword.dal.DbContext;

/**
 *
 * @author ihorvath
 */
public class BusinessLogic {

  private CategoryRepository categoryRepository;
  
  public BusinessLogic() {
    DbContext.Init();
    categoryRepository = new CategoryRepository();
  }

  public CategoryRepository getCategoryRepository() {
    return categoryRepository;
  }
  
  
  
}
