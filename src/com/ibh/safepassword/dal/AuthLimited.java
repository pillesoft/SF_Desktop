/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.dal;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ihorvath
 */
public class AuthLimited {
  
  private final int id;
  private final StringProperty category;
  private final StringProperty title;
  private final StringProperty webaddress;
  private final IntegerProperty numbofdays;
  private final StringProperty description;
  private final StringProperty color;

  public int getId() {
    return id;
  }

  public StringProperty getCategory() {
    return category;
  }

  public StringProperty getTitle() {
    return title;
  }

  public StringProperty getWebAddress() {
    return webaddress;
  }

  public IntegerProperty getNumberOfDays() {
    return numbofdays;
  }

  public StringProperty getDescription() {
    return description;
  }

  public StringProperty getCategColor() {
    return color;
  }
  
  public AuthLimited(int id, String category, String title, String webaddress, int numbofdays, String description, String categcolor) {
    this.id = id;
    this.category = new SimpleStringProperty(category);
    this.title = new SimpleStringProperty(title);
    this.webaddress = new SimpleStringProperty(webaddress);
    this.numbofdays = new SimpleIntegerProperty(numbofdays);
    this.description = new SimpleStringProperty(description);
    this.color = new SimpleStringProperty(categcolor);
  }

 
}
