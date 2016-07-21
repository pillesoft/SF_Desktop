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
public class AuthenticationRepository extends BaseRepository<Authentication> {
  public List GetAuthLimited(String filtertitle, String filtercateg) {
    List ret = GetList("SELECT a.title, a.weburl, a.description, a.category.name, a.id FROM Authentication a");
    
    return ret;
  }
}
