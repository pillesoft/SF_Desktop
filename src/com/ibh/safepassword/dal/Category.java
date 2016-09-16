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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ihorvath
 */
@Entity()
@Table(name="CATEGORIES_DICT")
public class Category implements Serializable {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Integer id;

  @Column(name = "NAME", nullable = false, length = 50, unique = true)
  @NotNull
  @Size(min = 5, max = 50)
  private String name;

  @Column(name = "COLOR", nullable = false, length = 20)
  @NotNull
  private String color;

  public Category() {
  }

  public Category(String name) {
    this.name = name;
    this.color = "128,128,128,255";
  }

  public Category(String name, int red, int green, int blue, int alpha) {
    this.name = name;
    this.color = String.format("%s,%s,%s,%s", red, green, blue, alpha);
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
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
    if (!(o instanceof Category)) {
      return false;
    }
    Category categ = (Category) o;
    return Objects.equals(id, categ.id);
  }
}
