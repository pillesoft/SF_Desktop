/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.Timer;

/**
 *
 * @author ihorvath
 */
public class AuthInfoTimer extends Timer implements ActionListener {
  
  private int secstoshow;
  private final AuthInfoDialog dialog;
  private final ResourceBundle bundle;
  
  public AuthInfoTimer(int i, ActionListener al, AuthInfoDialog aid, int secstoshow) {
    super(i, al);
    
    this.secstoshow = secstoshow;
    this.dialog = aid;
    bundle = java.util.ResourceBundle.getBundle("com/ibh/safepassword/gui/Bundle"); // NOI18N
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (((Timer)ae.getSource()).isRepeats()) {
      secstoshow--;
      dialog.cmdClose.setText(String.format("%s (%s)", bundle.getString("AuthInfoDialog.cmdClose.text"), secstoshow));
//      System.out.println(secstoshow);
    }

    if (secstoshow == 0) {
      ((Timer) ae.getSource()).setRepeats(false);
      dialog.setVisible(false);
      dialog.dispose();
    }
  }

}
