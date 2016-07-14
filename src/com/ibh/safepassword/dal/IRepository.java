/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.util.List;

/**
 *
 * @author ihorvath
 */
public interface IRepository<T> {
  void Add(T obj);
  void Update(T obj);
  void Delete(T obj);
  T GetbyId(int id);
  List<T> GetList();
}
