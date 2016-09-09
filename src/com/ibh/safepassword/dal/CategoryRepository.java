/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.util.HashMap;
import java.util.List;


/**
 *
 * @author ihorvath
 */
public class CategoryRepository extends BaseRepository<Category> {
  public Category AddorGet(String categname) {
    Category c;
    HashMap<String, Object> param = new HashMap<>();
    param.put("name", categname);
    List l = GetList("from Category c where c.name = :name", param);
    if (l.isEmpty()) {
      c = Add(new Category(categname));
    }
    else {
      c = (Category)l.get(0);
    }
    return c;
  }
  
//  public List<String> getCategoryName() {
//    List<String> l = GetList("select c.name from Category order by c.name");
//    return l;
//  }
}
