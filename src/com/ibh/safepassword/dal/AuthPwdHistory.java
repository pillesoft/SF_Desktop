/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ihorvath
 */
@Entity()
@Table(name = "AUTHPWDHISTORY")
public class AuthPwdHistory  implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Integer id;

  @ManyToOne()
  @JoinColumn(name = "AUTHENTICATIONS_ID", nullable = false, foreignKey = @ForeignKey(name = "AUTHENTICATION_ID_FK"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private final Authentication authentication;
  
  @Column(name = "PASSWORD", length = 200)
  private final String password;

  @Column(name = "EXPIRED")
  @Temporal(TemporalType.TIMESTAMP)
  private final Date expired;

  public AuthPwdHistory(Authentication authentication, String password) {
    this.authentication = authentication;
    this.password = password;
    this.expired = Calendar.getInstance().getTime();
  }

  public Integer getId() {
    return id;
  }

  public Authentication getAuthentication() {
    return authentication;
  }

  public String getPassword() {
    return password;
  }

  public Date getExpired() {
    return expired;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AuthPwdHistory)) {
      return false;
    }
    AuthPwdHistory authhist = (AuthPwdHistory)o;
    return Objects.equals(id, authhist.id);
  }

  
}
