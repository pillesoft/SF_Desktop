/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ihorvath
 */
public interface IRepository<T> {
  T Add(T obj);
  void Update(T obj);
  void Delete(T obj);
  T GetbyId(int id);

  List GetList(String queryexpr, Map<String, Object> parameters);
  List GetList(String queryexpr);
  List<T> GetList();
}
