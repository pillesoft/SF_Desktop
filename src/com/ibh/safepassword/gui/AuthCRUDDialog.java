/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.dal.Authentication;
import com.ibh.safepassword.dal.Category;
import com.ibh.safepassword.dal.IBHDbConstraintException;
import java.awt.Color;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.validation.ConstraintViolation;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author ihorvath
 */
public class AuthCRUDDialog extends javax.swing.JDialog {

  private final BusinessLogic _bl;
  private final CRUDEnum _mode;
  private final ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
  private Authentication instance;
  private BindingGroup bindingGroup;

  private final Logger logger = LogManager.getLogger(AuthCRUDDialog.class.getName());

  /**
   * Creates new form AuthCRUDDialog
   */
  public AuthCRUDDialog(java.awt.Frame parent, boolean modal, CRUDEnum mode, BusinessLogic bl, int id) {
    super(parent, modal);
    initComponents();

    this._bl = bl;
    this._mode = mode;

//    List<String> categlist = _bl.getCategRepos().GetList("select c.name from Category c order by c.name");
    List<Category> categlist = _bl.getCategRepos().GetList();

    // settings depending on the mode
    switch (_mode) {
      case New:
        setTitle(bundle.getString("AuthCRUDDialog.title_new"));
        instance = new Authentication();
        setPropChangeListener();
        instance.setId(id);
        instance.setTitle("");
        instance.setUsername("");
        Date validfrom = Calendar.getInstance().getTime();
        instance.setValidfrom(validfrom);
//        if (!categlist.isEmpty()) {
//          instance.setCategname(categlist.get(0));
//        }
        break;
      case Update:
        setTitle(bundle.getString("AuthCRUDDialog.title_update"));
        instance = bl.getAuthRepos().GetbyId(id);
        setPropChangeListener();
        break;
      case Delete:
        setTitle(bundle.getString("AuthCRUDDialog.title_delete"));
        cmdSave.setText(bundle.getString("AuthCRUDDialog.cmbSave.text_delete"));
        instance = bl.getAuthRepos().GetbyId(id);
        setPropChangeListener();
        break;
    }

//    String[] categarray = categlist.toArray(new String[0]);
//    DefaultComboBoxModel<String> cbm;
    Category[] categarray = categlist.toArray(new Category[0]);
    DefaultComboBoxModel<Category> cbm;
    if (categarray.length > 0) {
      cbm = new DefaultComboBoxModel<>(categarray);
    } else {
      Vector categv = new Vector();
      categv.add(new Category(""));
      cbm = new DefaultComboBoxModel<>(categv);
    }
    cmbCategory.setModel(cbm);

    cmbCategory.setRenderer(new IBHCategoryComboRenderer());
    cmbCategory.setEditor(new IBHCategoryComboEditor());

    this.pack();
  }

  private void setPropChangeListener() {
    setBindings();

    instance.addPropertyChangeListener((PropertyChangeEvent pce) -> {
      String propname = pce.getPropertyName();

      logger.debug("most valtozott property " + propname);

      javax.swing.JComponent p = getComponent(propname);
      logger.debug(p.getName());

      Set<ConstraintViolation<Authentication>> errors = instance.getErrors().get(propname);
      if (errors != null && errors.size() > 0) {
        logger.debug("errors.size " + errors.size());
        String errmess = "";
        for (ConstraintViolation<Authentication> error : errors) {
          errmess += error.getMessage() + "\n";
        }

        setError(p, errmess);
        //p.setBackground(Color.PINK);
//        logger.debug(p.getBorder());
//        if(p.getClientProperty("origborder") == null) {
//          p.putClientProperty("origborder", p.getBorder());
//        }
//        Border border = BorderFactory.createMatteBorder(2, 2, 2, 5, Color.RED);
//        p.setBorder(border);
      } else {
//        p.setBackground(Color.WHITE);
//        p.setBorder(null);
//        p.setBorder((Border)p.getClientProperty("origborder"));
        clearError(p);
      }

      cmdSave.setEnabled(instance.getIsValid());
      //this.pack();
    });
  }

  private JComponent getComponent(String propname) {
    Binding b = bindingGroup.getBinding(propname);
    javax.swing.JComponent p = (javax.swing.JComponent) b.getTargetObject();
    return p;
  }

  private void setError(JComponent comp, String errmess) {
    comp.setToolTipText(errmess);
    JLabel l = (JLabel) comp.getClientProperty("labeledBy");
    l.setForeground(Color.RED);
  }

  private void clearError(JComponent comp) {
    JLabel l = (JLabel)comp.getClientProperty("labeledBy");
    l.setForeground(Color.BLACK);
    comp.setToolTipText(null);
  }
  
  private void setBindings() {
    bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

    Binding binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("title"),
            txtTitle,
            BeanProperty.create("text"),
            "title"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("category"),
            cmbCategory,
            BeanProperty.create("selectedItem"),
            "category"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("username"),
            txtUserName,
            BeanProperty.create("text"),
            "username"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("password"),
            txtPassword,
            BeanProperty.create("text"),
            "password"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("weburl"),
            txtWebUrl,
            BeanProperty.create("text"),
            "weburl"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("description"),
            txtDescription,
            BeanProperty.create("text"),
            "description"
    );
    bindingGroup.addBinding(binding);

    binding = Bindings.createAutoBinding(
            AutoBinding.UpdateStrategy.READ_WRITE,
            instance,
            BeanProperty.create("validfrom"),
            txtValidFrom,
            BeanProperty.create("value"),
            "validfrom"
    );
    bindingGroup.addBinding(binding);

    bindingGroup.bind();
  }

  public Authentication getInstance() {
    return instance;
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
    lblValidFrom = new javax.swing.JLabel();
    txtValidFrom = new javax.swing.JFormattedTextField();
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

    lblWebUrl.setLabelFor(txtWebUrl);
    lblWebUrl.setText(bundle.getString("AuthCRUDDialog.lblWebUrl.text")); // NOI18N

    cmbCategory.setEditable(true);

    lblDescription.setText(bundle.getString("AuthCRUDDialog.lblDescription.text")); // NOI18N

    txtDescription.setColumns(20);
    txtDescription.setRows(5);
    txtDescription.setTabSize(4);
    jScrollPane2.setViewportView(txtDescription);

    lblValidFrom.setLabelFor(txtValidFrom);
    lblValidFrom.setText(bundle.getString("AuthCRUDDialog.lblValidFrom.text")); // NOI18N

    txtValidFrom.setText(bundle.getString("AuthCRUDDialog.txtValidFrom.text")); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(lblWebUrl)
          .addComponent(lblPassword)
          .addComponent(lblUserName)
          .addComponent(lblCategory)
          .addComponent(lblTitle)
          .addComponent(lblValidFrom)
          .addComponent(lblDescription))
        .addGap(39, 39, 39)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txtWebUrl)
          .addComponent(txtUserName)
          .addComponent(txtTitle)
          .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(txtPassword)
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
          .addComponent(txtValidFrom))
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
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblValidFrom)
          .addComponent(txtValidFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    String categname = ((javax.swing.JTextField) ((IBHCategoryComboEditor) cmbCategory.getEditor()).getEditorComponent()).getText();
//    String categname = (String)cmbCategory.getSelectedItem();
    logger.debug("categname " + categname);
//    instance.setCategname(categname);

    try {
      Category realcateg = _bl.getCategRepos().AddorGet(categname);
      instance.setCategory(realcateg);

      switch (_mode) {
        case New:
          _bl.getAuthRepos().Add(instance);
          break;
        case Update:
          _bl.getAuthRepos().Update(instance);
          break;
        case Delete:
          _bl.getAuthRepos().Delete(instance);
          break;

      }
      this.dispose();

    }
    catch (IBHDbConstraintException constrexc) {
      JComponent comp = getComponent(constrexc.getFieldName().toLowerCase());
      JLabel l = (JLabel)comp.getClientProperty("labeledBy");
      
      String text = String.format(bundle.getString("AuthUniqueConstraint"), l.getText());
      JOptionPane.showMessageDialog(this, text, "Save Error", JOptionPane.ERROR_MESSAGE);

    }
    catch(Exception exc) {
      logger.fatal("Unexpected Error", exc);
      JOptionPane.showMessageDialog(this, "Unexpected Error Occured. Check the Log", "Save Error", JOptionPane.ERROR_MESSAGE);
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
  private javax.swing.JLabel lblValidFrom;
  private javax.swing.JLabel lblWebUrl;
  private javax.swing.JTextArea txtDescription;
  private javax.swing.JTextField txtPassword;
  private javax.swing.JTextField txtTitle;
  private javax.swing.JTextField txtUserName;
  private javax.swing.JFormattedTextField txtValidFrom;
  private javax.swing.JTextField txtWebUrl;
  // End of variables declaration//GEN-END:variables
}
