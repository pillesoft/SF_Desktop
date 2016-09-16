/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.dal.IBHDatabaseException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author ihorvath
 */
public class LoginDialog extends javax.swing.JDialog {

  java.util.ResourceBundle _bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N

  /**
   * Creates new form LoginDialog
   */
  public LoginDialog(java.awt.Frame parent, boolean modal) {
    super(parent, modal);
    initComponents();

    lblStatus.setText("");
    pack();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    lblUserName = new javax.swing.JLabel();
    lblPwd = new javax.swing.JLabel();
    txtUserName = new javax.swing.JTextField();
    txtPwd = new javax.swing.JPasswordField();
    txtEncrPwd = new javax.swing.JPasswordField();
    lblEncrPwd = new javax.swing.JLabel();
    lblStatus = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    btnOptions = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    cmdNewDb = new javax.swing.JButton();
    cmdLogin = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    setTitle(bundle.getString("LoginDialog.title")); // NOI18N
    setLocation(new java.awt.Point(20, 20));
    setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
    setResizable(false);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

    lblUserName.setText(bundle.getString("LoginDialog.lblUserName.text")); // NOI18N
    lblUserName.setMaximumSize(new java.awt.Dimension(100, 14));
    lblUserName.setPreferredSize(new java.awt.Dimension(100, 14));

    lblPwd.setText(bundle.getString("LoginDialog.lblPwd.text")); // NOI18N
    lblPwd.setMaximumSize(new java.awt.Dimension(100, 14));
    lblPwd.setPreferredSize(new java.awt.Dimension(100, 14));

    txtUserName.setText(bundle.getString("LoginDialog.txtUserName.text")); // NOI18N

    txtPwd.setText(bundle.getString("LoginDialog.txtPwd.text")); // NOI18N
    txtPwd.setPreferredSize(new java.awt.Dimension(170, 20));

    txtEncrPwd.setText(bundle.getString("LoginDialog.txtEncrPwd.text")); // NOI18N
    txtEncrPwd.setPreferredSize(new java.awt.Dimension(170, 20));

    lblEncrPwd.setText(bundle.getString("LoginDialog.lblEncrPwd.text")); // NOI18N

    lblStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    lblStatus.setForeground(java.awt.Color.red);
    lblStatus.setText(bundle.getString("LoginDialog.lblStatus.text")); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(lblPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(lblEncrPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(txtUserName)
              .addComponent(txtPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(txtEncrPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(13, 13, 13))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(txtUserName))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblPwd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(txtPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(txtEncrPwd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(lblEncrPwd))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(lblStatus)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

    btnOptions.setText(bundle.getString("LoginDialog.btnOptions.text")); // NOI18N
    btnOptions.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOptionsActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(btnOptions)
        .addContainerGap())
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(btnOptions)
        .addContainerGap(16, Short.MAX_VALUE))
    );

    getContentPane().add(jPanel3, java.awt.BorderLayout.WEST);

    cmdNewDb.setText(bundle.getString("LoginDialog.cmdNewDb.text")); // NOI18N
    cmdNewDb.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdNewDbActionPerformed(evt);
      }
    });

    cmdLogin.setText(bundle.getString("LoginDialog.cmdLogin.text")); // NOI18N
    cmdLogin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdLoginActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(cmdLogin)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cmdNewDb)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(cmdNewDb)
          .addComponent(cmdLogin))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);
    jPanel2.getAccessibleContext().setAccessibleParent(this);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed

    lblStatus.setText("");
    pack();

    try {
      if (!((MainFrame) getParent()).getBL().Login(txtUserName.getText().trim(), txtPwd.getPassword(), txtEncrPwd.getPassword())) {
        lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.text"));
        pack();
      } else {
        this.dispose();
      }
    } catch (IBHDatabaseException dbe) {
      lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.DBNOTAvailable"));
      pack();
    }
  }//GEN-LAST:event_cmdLoginActionPerformed

  private void cmdNewDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewDbActionPerformed

    if (txtUserName.getText().trim().isEmpty()) {
      lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.UserNameEmpty"));
      pack();
    } else if (HasSpaceinEncrPwd()) {
      lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.HasSpace"));
      pack();
    } else {
      lblStatus.setText("");
      pack();

      try {
        if (!((MainFrame) getParent()).getBL().CreateDB(txtUserName.getText().trim(), txtPwd.getPassword(), txtEncrPwd.getPassword())) {
          lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.text"));
          pack();
        } else {
          this.dispose();
        }
      } catch (IBHDatabaseException dbe) {
        lblStatus.setText(_bundle.getString("LoginDialog.lblStatus.DBAvailable"));
        pack();
      }
    }
  }//GEN-LAST:event_cmdNewDbActionPerformed

  private Boolean HasSpaceinEncrPwd() {
    for (char chr : txtEncrPwd.getPassword()) {
      if (Character.isSpaceChar(chr)) {
        return true;
      }
    }
    return false;
  }

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    if (((MainFrame) getParent()).getBL().getLoggedInName() == null) {

      if (JOptionPane.showConfirmDialog(this,
              _bundle.getString("LoginDialog.CloseConfirmation.text"),
              _bundle.getString("LoginDialog.CloseConfirmation.title"),
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
        System.exit(0);
      } else {
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
      }
    }
  }//GEN-LAST:event_formWindowClosing

  private void btnOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOptionsActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_btnOptionsActionPerformed

  private byte[] toBytes(char[] chars) {
    CharBuffer charBuffer = CharBuffer.wrap(chars);
    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
            byteBuffer.position(), byteBuffer.limit());
    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
    return bytes;
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnOptions;
  private javax.swing.JButton cmdLogin;
  private javax.swing.JButton cmdNewDb;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JLabel lblEncrPwd;
  private javax.swing.JLabel lblPwd;
  private javax.swing.JLabel lblStatus;
  private javax.swing.JLabel lblUserName;
  private javax.swing.JPasswordField txtEncrPwd;
  private javax.swing.JPasswordField txtPwd;
  private javax.swing.JTextField txtUserName;
  // End of variables declaration//GEN-END:variables

}
