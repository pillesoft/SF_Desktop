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
public class IBHDatabaseException extends RuntimeException {
  private final String status;

  public static String AVAILABLE = "AVAILABLE";
  public static String NOTAVAILABLE = "NOTAVAILABLE";
  
  public IBHDatabaseException(String status) {
    super();
    this.status = status;
  }
  
  
}
