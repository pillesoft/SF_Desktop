/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.dal.Authentication;
import com.ibh.safepassword.dal.Category;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author ihorvath
 */
public class AuthCRUDDialog extends javax.swing.JDialog {

  private final BusinessLogic _bl;
  private final CRUDEnum _mode;
  private final ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
  private Authentication _instance;
 
  /**
   * Creates new form AuthCRUDDialog
   */
  public AuthCRUDDialog(java.awt.Frame parent, boolean modal, CRUDEnum mode, BusinessLogic bl, int id) {
    super(parent, modal);
    initComponents();
    
    this._bl = bl;
    this._mode = mode;

    List<Category> categlist = _bl.getCategRepos().GetList();
    
    // settings depending on the mode
    switch (_mode) {
      case New:
        setTitle(bundle.getString("AuthCRUDDialog.title_new"));
        _instance = new Authentication();
        _instance.setId(id);
        _instance.setCategory(categlist.get(1));
        break;
      case Update:
        setTitle(bundle.getString("AuthCRUDDialog.title_update"));
        _instance = bl.getAuthRepos().GetbyId(id);
        break;
      case Delete:
        setTitle(bundle.getString("AuthCRUDDialog.title_delete"));
        cmdSave.setText(bundle.getString("AuthCRUDDialog.cmbSave.text_delete"));
        _instance = bl.getAuthRepos().GetbyId(id);
        break;
    }
    
    Category[] categarray = categlist.toArray(new Category[0]);

    DefaultComboBoxModel<Category> cbm = new DefaultComboBoxModel<Category>(categarray);
    cmbCategory.setModel(cbm);

    cmbCategory.setRenderer(new IBHCategoryComboRenderer());
    cmbCategory.setEditor(new IBHCategoryComboEditor());
    
    setFields();
    
    this.pack();
  }

  private void setFields() {
    txtTitle.setText(_instance.getTitle());
    txtUserName.setText(_instance.getUsername());
    txtPassword.setText(_instance.getPassword());
    txtWebUrl.setText(_instance.getWeburl());
    txtDescription.setText(_instance.getDescription());
    cmbCategory.setSelectedItem(_instance.getCategory());
  }
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jPanel1 = new javax.swing.JPanel();
    lblTitle = new javax.swing.JLabel();
    lblCategory = new javax.swing.JLabel();
    lblUserName = new javax.swing.JLabel();
    lblPassword = new javax.swing.JLabel();
    lblWebUrl = new javax.swing.JLabel();
    txtTitle = new javax.swing.JTextField();
    cmbCategory = new javax.swing.JComboBox<>();
    txtUserName = new javax.swing.JTextField();
    txtPassword = new javax.swing.JTextField();
    txtWebUrl = new javax.swing.JTextField();
    lblDescription = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    txtDescription = new javax.swing.JTextArea();
    jPanel2 = new javax.swing.JPanel();
    cmdSave = new javax.swing.JButton();
    cmdCancel = new javax.swing.JButton();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

    lblTitle.setLabelFor(txtTitle);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    lblTitle.setText(bundle.getString("AuthCRUDDialog.lblTitle.text")); // NOI18N

    lblCategory.setLabelFor(cmbCategory);
    lblCategory.setText(bundle.getString("AuthCRUDDialog.lblCategory.text")); // NOI18N

    lblUserName.setLabelFor(txtUserName);
    lblUserName.setText(bundle.getString("AuthCRUDDialog.lblUserName.text")); // NOI18N

    lblPassword.setLabelFor(txtPassword);
    lblPassword.setText(bundle.getString("AuthCRUDDialog.lblPassword.text")); // NOI18N

    lblWebUrl.setLabelFor(lblWebUrl);
    lblWebUrl.setText(bundle.getString("AuthCRUDDialog.lblWebUrl.text")); // NOI18N

    cmbCategory.setEditable(true);

    txtUserName.setText(bundle.getString("AuthCRUDDialog.jTextField1.text")); // NOI18N

    txtPassword.setText(bundle.getString("AuthCRUDDialog.jTextField1.text")); // NOI18N

    txtWebUrl.setText(bundle.getString("AuthCRUDDialog.jTextField1.text")); // NOI18N

    lblDescription.setText(bundle.getString("AuthCRUDDialog.lblDescription.text")); // NOI18N

    txtDescription.setColumns(20);
    txtDescription.setRows(5);
    txtDescription.setTabSize(4);
    jScrollPane2.setViewportView(txtDescription);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lblDescription)
          .addComponent(lblWebUrl)
          .addComponent(lblPassword)
          .addComponent(lblUserName)
          .addComponent(lblCategory)
          .addComponent(lblTitle))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txtWebUrl)
          .addComponent(txtUserName)
          .addComponent(txtTitle)
          .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(txtPassword)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblTitle)
          .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblCategory)
          .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(lblUserName))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblPassword)
          .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblWebUrl)
          .addComponent(txtWebUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(lblDescription)
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(jScrollPane2))
        .addContainerGap())
    );

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

    cmdSave.setText(bundle.getString("AuthCRUDDialog.cmdSave.text")); // NOI18N
    cmdSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdSaveActionPerformed(evt);
      }
    });

    cmdCancel.setText(bundle.getString("AuthCRUDDialog.cmdCancel.text")); // NOI18N
    cmdCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdCancelActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap(234, Short.MAX_VALUE)
        .addComponent(cmdSave)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(cmdCancel)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cmdCancel)
          .addComponent(cmdSave))
        .addContainerGap())
    );

    getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
    
    // validate if authentication is OK
//    _instance = new Authentication();
    _instance.setTitle(txtTitle.getText().trim());
    _instance.setUsername(txtUserName.getText().trim());
    _instance.setPassword(txtPassword.getText().trim());
    _instance.setWeburl(txtWebUrl.getText().trim());
    _instance.setDescription(txtDescription.getText().trim());
    _instance.setCategory((Category)cmbCategory.getSelectedItem());
    
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Authentication>> errors = validator.validate(_instance);
    
    System.out.println(errors.size());
    
    for (ConstraintViolation<Authentication> error : errors) {
      System.out.println(String.format("%s - %s", error.getPropertyPath().toString(), error.getMessage()));
    }
        
    switch (_mode) {
      case New:
        
        break;
      case Update:
        
        break;
      case Delete:
        
        break;
      
    }
  }//GEN-LAST:event_cmdSaveActionPerformed

  private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
    this.dispose();
  }//GEN-LAST:event_cmdCancelActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<Category> cmbCategory;
  private javax.swing.JButton cmdCancel;
  private javax.swing.JButton cmdSave;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JLabel lblCategory;
  private javax.swing.JLabel lblDescription;
  private javax.swing.JLabel lblPassword;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JLabel lblUserName;
  private javax.swing.JLabel lblWebUrl;
  private javax.swing.JTextArea txtDescription;
  private javax.swing.JTextField txtPassword;
  private javax.swing.JTextField txtTitle;
  private javax.swing.JTextField txtUserName;
  private javax.swing.JTextField txtWebUrl;
  // End of variables declaration//GEN-END:variables
}