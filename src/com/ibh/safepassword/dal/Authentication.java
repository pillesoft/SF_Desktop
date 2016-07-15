/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ihorvath
 */
@Entity()
@Table(name = "AUTHENTICATIONS")
public class Authentication implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Integer id;
  
  @Column(name = "TITLE", nullable = false, unique = true, length = 100)
  @NotNull
  @Size(min = 10, max = 100)
  private String title;
  @Column(name = "USERNAME", nullable = false, length = 100)
  @NotNull
  @Size(min = 4, max = 100)
  private String username;
  @Column(name = "PASSWORD", length = 100)
  @Size(max = 100)
  private String password;
  @Column(name = "WEBURL", length = 200)
  @Size(max = 200)
  private String weburl;
  @Column(name = "DESCRIPTION", length = 500)
  @Size(max = 500)
  private String description;
  
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID", nullable = false, foreignKey = @ForeignKey(name = "CATEGORY_ID_FK"))
  @NotNull
  private Category category;

  public Authentication() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getWeburl() {
    return weburl;
  }

  public void setWeburl(String weburl) {
    this.weburl = weburl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }
   
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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
    if (!(o instanceof Authentication)) {
      return false;
    }
    Authentication auth = (Authentication) o;
    return Objects.equals(id, auth.id);
  }

}
