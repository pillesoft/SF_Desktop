/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.bl.BusinessLogic;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ihorvath
 */
public class MainFrame extends javax.swing.JFrame {

  
  private BusinessLogic bl;
  
  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    initComponents();

    initTable();
    
    bl = new BusinessLogic();
    
    LoginDialog ld = new LoginDialog(this, true);
    ld.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          setVisible(true);
          setTitle(getTitle() + " - " + bl.getLoggedInName());
          
          setTable();
        }
                
      }
    );
    ld.setVisible(true);
    
  }

  public BusinessLogic getBL() {
    return bl;
  }
  
  private void initTable() {
    String[] columnNames = {"Title",
                        "Category",
                        "Web Address"};

    
    DefaultTableModel tdm = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };
    tblData.setModel(tdm);
    
    TableColumnModel tcm = tblData.getColumnModel();
    for (String columnName : columnNames) {
      TableColumn tc = new TableColumn();
      tc.setHeaderValue(columnName);
      tcm.addColumn(tc);
    }

  }
  
  private void setTable() {
    
    TableModel tm = tblData.getModel();
    List data = bl.getAuthRepos().GetAuthLimited("", "");
    int rowidx = 1;
    for (Object row : data) {
      Object[] rowdata = (Object[])row;
      for (int colidx = 1; colidx <= 5; colidx++) {
        tm.setValueAt(rowdata[colidx-1], rowidx, colidx);
      }
      rowidx++;
      
    }
    
    String sa = "adf";
  }
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    pnlButtons = new javax.swing.JPanel();
    cmdShow = new javax.swing.JButton();
    cmdNew = new javax.swing.JButton();
    cmdMod = new javax.swing.JButton();
    cmdDel = new javax.swing.JButton();
    cmdHistory = new javax.swing.JButton();
    pnlMain = new javax.swing.JPanel();
    pnlFilter = new javax.swing.JPanel();
    lblTitle = new javax.swing.JLabel();
    lblCategory = new javax.swing.JLabel();
    txtFilterTitle = new javax.swing.JTextField();
    txtFilterCategory = new javax.swing.JTextField();
    cmdClearFilter = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    tblData = new javax.swing.JTable();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    setTitle(bundle.getString("MainFrame.title")); // NOI18N

    pnlButtons.setMaximumSize(new java.awt.Dimension(100, 46));
    pnlButtons.setMinimumSize(new java.awt.Dimension(100, 46));
    pnlButtons.setPreferredSize(new java.awt.Dimension(100, 300));
    java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
    flowLayout1.setAlignOnBaseline(true);
    pnlButtons.setLayout(flowLayout1);

    cmdShow.setText(bundle.getString("MainFrame.cmdShow.text")); // NOI18N
    cmdShow.setMaximumSize(new java.awt.Dimension(100, 50));
    cmdShow.setPreferredSize(new java.awt.Dimension(100, 40));
    pnlButtons.add(cmdShow);

    cmdNew.setText(bundle.getString("MainFrame.cmdNew.text")); // NOI18N
    cmdNew.setMaximumSize(new java.awt.Dimension(100, 50));
    cmdNew.setPreferredSize(new java.awt.Dimension(100, 40));
    pnlButtons.add(cmdNew);

    cmdMod.setText(bundle.getString("MainFrame.cmdMod.text")); // NOI18N
    cmdMod.setMaximumSize(new java.awt.Dimension(100, 50));
    cmdMod.setPreferredSize(new java.awt.Dimension(100, 40));
    pnlButtons.add(cmdMod);

    cmdDel.setText(bundle.getString("MainFrame.cmdDel.text")); // NOI18N
    cmdDel.setMaximumSize(new java.awt.Dimension(100, 50));
    cmdDel.setPreferredSize(new java.awt.Dimension(100, 40));
    pnlButtons.add(cmdDel);

    cmdHistory.setText(bundle.getString("MainFrame.cmdHistory.text")); // NOI18N
    cmdHistory.setMaximumSize(new java.awt.Dimension(100, 50));
    cmdHistory.setPreferredSize(new java.awt.Dimension(100, 40));
    pnlButtons.add(cmdHistory);

    getContentPane().add(pnlButtons, java.awt.BorderLayout.LINE_START);

    lblTitle.setText(bundle.getString("MainFrame.lblTitle.text")); // NOI18N

    lblCategory.setText(bundle.getString("MainFrame.lblCategory.text")); // NOI18N

    txtFilterTitle.setText(bundle.getString("MainFrame.txtFilterTitle.text")); // NOI18N

    txtFilterCategory.setText(bundle.getString("MainFrame.txtFilterCategory.text")); // NOI18N

    cmdClearFilter.setText(bundle.getString("MainFrame.cmdClearFilter.text")); // NOI18N
    cmdClearFilter.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdClearFilterActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout pnlFilterLayout = new javax.swing.GroupLayout(pnlFilter);
    pnlFilter.setLayout(pnlFilterLayout);
    pnlFilterLayout.setHorizontalGroup(
      pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnlFilterLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(lblCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(txtFilterTitle)
          .addComponent(txtFilterCategory))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cmdClearFilter)
        .addGap(4, 4, 4))
    );
    pnlFilterLayout.setVerticalGroup(
      pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnlFilterLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(pnlFilterLayout.createSequentialGroup()
            .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(lblTitle)
              .addComponent(txtFilterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(pnlFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(lblCategory)
              .addComponent(txtFilterCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 3, Short.MAX_VALUE))
          .addComponent(cmdClearFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );

    tblData.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jScrollPane1.setViewportView(tblData);

    jTextArea1.setEditable(false);
    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane2.setViewportView(jTextArea1);

    javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
    pnlMain.setLayout(pnlMainLayout);
    pnlMainLayout.setHorizontalGroup(
      pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnlFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
          .addComponent(jScrollPane2))
        .addContainerGap())
    );
    pnlMainLayout.setVerticalGroup(
      pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnlMainLayout.createSequentialGroup()
        .addComponent(pnlFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void cmdClearFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClearFilterActionPerformed
    // TODO add your handling code here:
    txtFilterTitle.setText("");
    txtFilterCategory.setText("");
    setTable();
  }//GEN-LAST:event_cmdClearFilterActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cmdClearFilter;
  private javax.swing.JButton cmdDel;
  private javax.swing.JButton cmdHistory;
  private javax.swing.JButton cmdMod;
  private javax.swing.JButton cmdNew;
  private javax.swing.JButton cmdShow;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JLabel lblCategory;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JPanel pnlButtons;
  private javax.swing.JPanel pnlFilter;
  private javax.swing.JPanel pnlMain;
  private javax.swing.JTable tblData;
  private javax.swing.JTextField txtFilterCategory;
  private javax.swing.JTextField txtFilterTitle;
  // End of variables declaration//GEN-END:variables
}
