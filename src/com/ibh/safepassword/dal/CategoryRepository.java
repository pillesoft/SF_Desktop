/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
