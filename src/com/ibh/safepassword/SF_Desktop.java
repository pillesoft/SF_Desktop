/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword;

import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.dal.Category;

import java.util.List;

/**
 *
 * @author ihorvath
 */
public class SF_Desktop {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    
    BusinessLogic bl = new BusinessLogic();
    List<Category> clist = bl.getCategRepos().GetList();
    System.out.println(clist.size());
    
   
    // insert new
    //bl.getCategoryRepository().Add(new Category("Private2"));

    // update
    //Category c1 = bl.getCategoryRepository().GetbyId(6);
    //c1.setName("Private12");
    //bl.getCategoryRepository().Update(c1);

    // delete
    //Category c1 = bl.getCategoryRepository().GetbyId(5);
    //bl.getCategoryRepository().Delete(c1);
    
    clist = bl.getCategRepos().GetList();
    System.out.println(clist.size());
    

    
  }

}
