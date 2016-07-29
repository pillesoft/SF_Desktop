/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.dal.Category;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 *
 * @author ihorvath
 */
public class IBHCategoryComboEditor extends BasicComboBoxEditor {

  private Category selectedCategory;
  
  public IBHCategoryComboEditor() {
    super();
    this.editor.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
  }
  
  @Override
  public void setItem(Object o) {
    if (o == null) {
      return;
    }
    this.selectedCategory = (Category)o;
    this.editor.setText(this.selectedCategory.getName());
  }


  @Override
  public Object getItem() {
    return this.selectedCategory;
  }

}
