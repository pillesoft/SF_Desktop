/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.bl.Crypt;
import com.ibh.safepassword.dal.Authentication;
import java.awt.Color;
import java.awt.Component;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdesktop.beansbinding.BindingGroup;

/**
 *
 * @author ihorvath
 */
public class ShowHistoryDialog extends javax.swing.JDialog {

  private TableRowSorter<IBHTableModel> sorter;

  private final BusinessLogic _bl;
  private final ResourceBundle _bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N

  private final Logger logger = LogManager.getLogger(ShowHistoryDialog.class.getName());

  /**
   * Creates new form ShowHistoryDialog
   */
  public ShowHistoryDialog(java.awt.Frame parent, boolean modal, BusinessLogic bl, int id) {
    super(parent, modal);
    initComponents();

    this._bl = bl;

    initTable();

    setData(id);
  }

  private void initTable() {

    String[] columnNames = {
      _bundle.getString("ShowHistoryDialog.tblPwdHistory.ColPassword.text"),
      _bundle.getString("ShowHistoryDialog.tblPwdHistory.ColExpired.text"),};

    IBHTableModel dtm = new IBHTableModel();
    //sorter = new TableRowSorter<>(dtm);
    dtm.setColumnIdentifiers(columnNames);
    tblPwdHistory.setModel(dtm);
    //tblPwdHistory.setRowSorter(sorter);

  }

  private void setData(int id) {

    String queryexpr = "select a.title, a.username from Authentication a where a.id = :id";
    HashMap<String, Object> parameters = new HashMap<>();
    parameters.put("id", id);
    List authl = _bl.getAuthRepos().GetList(queryexpr, parameters);
    Object[] auth = (Object[]) authl.get(0);

    setTitle(String.format(_bundle.getString("ShowHistoryDialog.title"), auth[0]));
    txtUserName.setText(auth[1].toString());

    DefaultTableModel tm = (DefaultTableModel) tblPwdHistory.getModel();
    if (tm.getRowCount() > 0) {
      for (int i = tm.getRowCount(); i > 0; i--) {
        tm.removeRow(i - 1);
      }
    }
    queryexpr = "select a.password, a.expired from AuthPwdHistory a where a.authentication.id = :id order by a.expired desc";
    parameters = new HashMap<>();
    parameters.put("id", id);
    List data = _bl.getAuthHistRepos().GetList(queryexpr, parameters);
    if (data.isEmpty()) {
      JOptionPane.showMessageDialog(this, _bundle.getString("ShowHistoryDialog.PwdListEmpty"), this.getTitle(), JOptionPane.INFORMATION_MESSAGE);
      this.dispose();
    } else {
      for (Object row : data) {
        Object[] rowdata = (Object[]) row;
        try {
          if (rowdata[0] != null) {
            rowdata[0] = new String(Crypt.decrypt(rowdata[0].toString()));
          }
        } catch (InvalidKeyException ex) {
          logger.error("decrypt error", ex);
        } catch (InvalidAlgorithmParameterException ex) {
          logger.error("decrypt error", ex);
        }
        tm.addRow(rowdata);
      }
    }

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
    txtUserName = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tblPwdHistory = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    setTitle(bundle.getString("ShowHistoryDialog.title")); // NOI18N
    lblUserName.setText(bundle.getString("ShowHistoryDialog.lblUserName.text")); // NOI18N

    txtUserName.setEditable(false);
    txtUserName.setText(bundle.getString("ShowHistoryDialog.txtUserName.text")); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(lblUserName)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(15, 15, 15)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(lblUserName)
          .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(15, 15, 15))
    );

    tblPwdHistory.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jScrollPane1.setViewportView(tblPwdHistory);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        .addGap(0, 0, 0))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblUserName;
  private javax.swing.JTable tblPwdHistory;
  private javax.swing.JTextField txtUserName;
  // End of variables declaration//GEN-END:variables
}
