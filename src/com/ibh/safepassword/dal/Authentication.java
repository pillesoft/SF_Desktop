/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

  @Transient
  private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

  @Transient
  private final HashMap<String, Set<ConstraintViolation<Authentication>>> errors = new HashMap<>();
  @Transient
  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  @Transient
  private final Validator validator = factory.getValidator();

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.addPropertyChangeListener(listener);
  }

  public void removePropertyChangeListener(PropertyChangeListener listener) {
    changeSupport.removePropertyChangeListener(listener);
  }

  public Authentication() {
  }

  public HashMap<String, Set<ConstraintViolation<Authentication>>> getErrors() {
    return errors;
  }

  public boolean getIsValid() {
    return errors.isEmpty();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    String old = this.title;
    this.title = title.trim();
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "title");
    if (err.size() > 0) {
      errors.put("title", err);
    } else {
      errors.remove("title");
    }
    changeSupport.firePropertyChange("title", old, title);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    String old = this.username;
    this.username = username.trim();
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "username");
    if (err.size() > 0) {
      errors.put("username", err);
    } else {
      errors.remove("username");
    }
    changeSupport.firePropertyChange("username", old, username);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    String old = this.password;
    this.password = password.trim();
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "password");
    if (err.size() > 0) {
      errors.put("password", err);
    } else {
      errors.remove("password");
    }
    changeSupport.firePropertyChange("password", old, password);
  }

  public String getWeburl() {
    return weburl;
  }

  public void setWeburl(String weburl) {
    String old = this.weburl;
    this.weburl = weburl.trim();
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "weburl");
    if (err.size() > 0) {
      errors.put("weburl", err);
    } else {
      errors.remove("weburl");
    }
    changeSupport.firePropertyChange("weburl", old, weburl);
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    String old = this.description;
    this.description = description.trim();
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "description");
    if (err.size() > 0) {
      errors.put("description", err);
    } else {
      errors.remove("description");
    }
    changeSupport.firePropertyChange("description", old, description);
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    Category old = this.category;
    this.category = category;
    Set<ConstraintViolation<Authentication>> err = validator.validateProperty(this, "category");
    if (err.size() > 0) {
      errors.put("category", err);
    } else {
      errors.remove("category");
    }
    changeSupport.firePropertyChange("category", old, category);
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
