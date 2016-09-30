/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import com.ibh.safepassword.bl.Crypt;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ihorvath
 */
public class AuthInfo {
  private final int id;
  private final String username;
  private final String title;
  private String pwdclear;

  public int getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getTitle() {
    return title;
  }

  public String getPwdClear() {
    return pwdclear;
  }
  
  public AuthInfo(int id, String UserName, String Password, String title) {
    this.id = id;
    this.username = UserName;
    this.title = title;
    this.pwdclear = "";
    
    try {
      if (Password != null) {
        this.pwdclear = new String(Crypt.decrypt(Password));
      }
    } catch (InvalidKeyException ex) {
      Logger.getLogger(AuthInfo.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidAlgorithmParameterException ex) {
      Logger.getLogger(AuthInfo.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  
}
