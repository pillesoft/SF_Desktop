/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

/**
 *
 * @author ihorvath
 */
public class AuthInfo {
  private final int id;
  private final String username;
  private final String password;
  private final String title;

  public int getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getTitle() {
    return title;
  }

  
  public AuthInfo(int id, String UserName, String Password, String title) {
    this.id = id;
    this.username = UserName;
    this.password = Password;
    this.title = title;
  }
  
}
