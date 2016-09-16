/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import com.ibh.safepassword.bl.BusinessLogic;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Component;
import java.util.logging.Level;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ihorvath
 */
public class MainFrame extends javax.swing.JFrame {

  private BusinessLogic bl;
  private TableRowSorter<IBHTableModel> sorter;

  // the auth show dialog closes the dialog after 10 secs
  private final int _AuthInfoDialogShowTime = 10;
  private int _AuthInfoDialogShowTimeCounter;
  private final ResourceBundle _bundle;
  private final int _FieldPosDescription = 4;
  private final int _FieldPosId = 5;
  private final int _FieldPosColor = 6;

  /**
   * Creates new form MainFrame
   */
  public MainFrame() {
    initComponents();

    java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

    _bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    txtFilterTitle.getDocument().addDocumentListener(
            new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        applyFilter();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        applyFilter();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        applyFilter();
      }
    });

    txtFilterCategory.getDocument().addDocumentListener(
            new DocumentListener() {
      @Override
      public void changedUpdate(DocumentEvent e) {
        applyFilter();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        applyFilter();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        applyFilter();
      }
    });

    bl = new BusinessLogic();

    LoginDialog ld = new LoginDialog(this, true);
    ld.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        setVisible(true);
        setTitle(getTitle() + " - " + bl.getLoggedInName());

        initTable();

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

    String[] columnNames = {
      _bundle.getString("MainFrame.tblData.ColTitle.text"),
      _bundle.getString("MainFrame.tblData.ColCategory.text"),
      _bundle.getString("MainFrame.tblData.ColWebUrl.text"),
      _bundle.getString("MainFrame.tblData.ColValidFrom.text"),
      "Description",
      "ID",
      "COLOR"
    };

    IBHTableModel dtm = new IBHTableModel();
    sorter = new TableRowSorter<>(dtm);
    dtm.setColumnIdentifiers(columnNames);
    tblData.setModel(dtm);
    tblData.setRowSorter(sorter);

    tblData.removeColumn(tblData.getColumnModel().getColumn(_FieldPosColor));
    tblData.removeColumn(tblData.getColumnModel().getColumn(_FieldPosId));
    tblData.removeColumn(tblData.getColumnModel().getColumn(_FieldPosDescription));

    tblData.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent lse) {
        int selrow = tblData.getSelectedRow();
        txtDescription.setText("");
        if (selrow > -1) {
          Object descvalue = tblData.getModel().getValueAt(selrow, _FieldPosDescription);
          if (descvalue != null) {
            txtDescription.setText(descvalue.toString());
          }
        }
      }
    });

    tblData.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
      @Override
      public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component c = super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, col);
        
        String[] colors = jtable.getModel().getValueAt(row, _FieldPosColor).toString().split(",");
        Color color = new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));
        c.setForeground(Color.WHITE);
        c.setBackground(color);

        if (isSelected) {
          c.setForeground(jtable.getSelectionForeground());
          c.setBackground(jtable.getSelectionBackground());
        }
        
        return c;
      }
      
    });

  }

  private void setTable() {

    DefaultTableModel tm = (DefaultTableModel) tblData.getModel();
    if (tm.getRowCount() > 0) {
      for (int i = tm.getRowCount(); i > 0; i--) {
        tm.removeRow(i - 1);
      }
    }
    List data = bl.getAuthRepos().GetAuthLimited("", "");
    for (Object row : data) {
      Object[] rowdata = (Object[]) row;
      tm.addRow(rowdata);

    }

    
//        tblData = new javax.swing.JTable() {
//      public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//        Component c = super.prepareRenderer(renderer, row, column);
//        String[] colors = getValueAt(row, _FieldPosColor).toString().split(",");
//        Color color = new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]), Integer.parseInt(colors[3]));
//        c.setBackground(color);
//        return c;
//      }
//    };
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
    txtDescription = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
    setTitle(bundle.getString("MainFrame.title")); // NOI18N
    getContentPane().setLayout(new java.awt.BorderLayout(5, 5));

    pnlButtons.setMaximumSize(new java.awt.Dimension(150, 46));
    pnlButtons.setMinimumSize(new java.awt.Dimension(150, 46));
    pnlButtons.setPreferredSize(new java.awt.Dimension(150, 300));
    java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
    flowLayout1.setAlignOnBaseline(true);
    pnlButtons.setLayout(flowLayout1);

    cmdShow.setText(bundle.getString("MainFrame.cmdShow.text")); // NOI18N
    cmdShow.setMaximumSize(new java.awt.Dimension(150, 50));
    cmdShow.setPreferredSize(new java.awt.Dimension(150, 40));
    cmdShow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdShowActionPerformed(evt);
      }
    });
    pnlButtons.add(cmdShow);

    cmdNew.setText(bundle.getString("MainFrame.cmdNew.text")); // NOI18N
    cmdNew.setMaximumSize(new java.awt.Dimension(150, 50));
    cmdNew.setPreferredSize(new java.awt.Dimension(150, 40));
    cmdNew.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdNewActionPerformed(evt);
      }
    });
    pnlButtons.add(cmdNew);

    cmdMod.setText(bundle.getString("MainFrame.cmdMod.text")); // NOI18N
    cmdMod.setMaximumSize(new java.awt.Dimension(150, 50));
    cmdMod.setPreferredSize(new java.awt.Dimension(150, 40));
    cmdMod.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdModActionPerformed(evt);
      }
    });
    pnlButtons.add(cmdMod);

    cmdDel.setText(bundle.getString("MainFrame.cmdDel.text")); // NOI18N
    cmdDel.setMaximumSize(new java.awt.Dimension(150, 50));
    cmdDel.setPreferredSize(new java.awt.Dimension(150, 40));
    cmdDel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdDelActionPerformed(evt);
      }
    });
    pnlButtons.add(cmdDel);

    cmdHistory.setText(bundle.getString("MainFrame.cmdHistory.text")); // NOI18N
    cmdHistory.setMaximumSize(new java.awt.Dimension(150, 50));
    cmdHistory.setPreferredSize(new java.awt.Dimension(150, 40));
    cmdHistory.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cmdHistoryActionPerformed(evt);
      }
    });
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
    tblData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane1.setViewportView(tblData);

    txtDescription.setEditable(false);
    txtDescription.setColumns(20);
    txtDescription.setRows(5);
    jScrollPane2.setViewportView(txtDescription);

    javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
    pnlMain.setLayout(pnlMainLayout);
    pnlMainLayout.setHorizontalGroup(
      pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(pnlFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
          .addComponent(jScrollPane2))
        .addContainerGap())
    );
    pnlMainLayout.setVerticalGroup(
      pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(pnlMainLayout.createSequentialGroup()
        .addComponent(pnlFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
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

  private int getSelectedRowID() {
    int selrow = tblData.getSelectedRow();
    int id = 0;
    if (selrow > -1) {
      Object intvalue = tblData.getModel().getValueAt(selrow, _FieldPosId);
      if (intvalue != null) {
        id = Integer.parseInt(intvalue.toString());
      }
    }
    return id;
  }

  private void cmdShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdShowActionPerformed

    int id = getSelectedRowID();
    if (id != 0) {
      _AuthInfoDialogShowTimeCounter = _AuthInfoDialogShowTime;
      AuthInfoDialog aid = new AuthInfoDialog(this, true, bl, id, _AuthInfoDialogShowTime);

      Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          if (((Timer) ae.getSource()).isRepeats()) {
            _AuthInfoDialogShowTimeCounter--;
            aid.cmdClose.setText(String.format("%s (%s)", _bundle.getString("AuthInfoDialog.cmdClose.text"), _AuthInfoDialogShowTimeCounter));
//      System.out.println(secstoshow);
          }

          if (_AuthInfoDialogShowTimeCounter <= 0) {
            ((Timer) ae.getSource()).setRepeats(false);
            aid.setVisible(false);
            aid.dispose();
          }
        }
      });
      //AuthInfoTimer timer = new AuthInfoTimer(1000, AuthInfoTimer, aid, secstoshow);

      aid.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
          // needs to clear the clipboard
          try {
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();

            clpbrd.setContents(new Transferable() {
              @Override
              public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[0];
              }

              @Override
              public boolean isDataFlavorSupported(DataFlavor flavor) {
                return false;
              }

              @Override
              public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
                throw new UnsupportedFlavorException(flavor);
              }
            }, null);
          } catch (IllegalStateException ise) {
          }
        }

      }
      );

      timer.start();

      aid.setVisible(true);
    }
  }//GEN-LAST:event_cmdShowActionPerformed

  private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
    AuthCRUDDialog acrudd = new AuthCRUDDialog(this, true, CRUDEnum.New, bl, 0);
    acrudd.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent we) {
        setTable();
      }

    });
    acrudd.setVisible(true);

  }//GEN-LAST:event_cmdNewActionPerformed

  private void cmdModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdModActionPerformed
    int id = getSelectedRowID();
    if (id != 0) {

      AuthCRUDDialog acrudd = new AuthCRUDDialog(this, true, CRUDEnum.Update, bl, id);
      acrudd.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent we) {
          setTable();
        }

      });
      acrudd.setVisible(true);
    }
  }//GEN-LAST:event_cmdModActionPerformed

  private void cmdDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDelActionPerformed
    int id = getSelectedRowID();
    if (id != 0) {

      AuthCRUDDialog acrudd = new AuthCRUDDialog(this, true, CRUDEnum.Delete, bl, id);
      acrudd.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent we) {
          setTable();
        }

      });
      acrudd.setVisible(true);
    }
  }//GEN-LAST:event_cmdDelActionPerformed

  private void cmdHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdHistoryActionPerformed
    int id = getSelectedRowID();
    if (id != 0) {
      ShowHistoryDialog histd = new ShowHistoryDialog(this, true, bl, id);

      histd.setVisible(true);
      
    }
  }//GEN-LAST:event_cmdHistoryActionPerformed

  private void applyFilter() {
    List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
    filters.add(RowFilter.regexFilter(txtFilterTitle.getText(), 0));
    filters.add(RowFilter.regexFilter(txtFilterCategory.getText(), 1));

    RowFilter<IBHTableModel, Object> rf = null;
    //If current expression doesn't parse, don't update.
    try {
      rf = RowFilter.andFilter(filters);
    } catch (java.util.regex.PatternSyntaxException e) {
      return;
    }
    sorter.setRowFilter(rf);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cmdClearFilter;
  private javax.swing.JButton cmdDel;
  private javax.swing.JButton cmdHistory;
  private javax.swing.JButton cmdMod;
  private javax.swing.JButton cmdNew;
  private javax.swing.JButton cmdShow;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel lblCategory;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JPanel pnlButtons;
  private javax.swing.JPanel pnlFilter;
  private javax.swing.JPanel pnlMain;
  private javax.swing.JTable tblData;
  private javax.swing.JTextArea txtDescription;
  private javax.swing.JTextField txtFilterCategory;
  private javax.swing.JTextField txtFilterTitle;
  // End of variables declaration//GEN-END:variables

}
