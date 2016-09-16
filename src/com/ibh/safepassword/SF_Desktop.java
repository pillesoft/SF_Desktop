/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword;

import com.ibh.safepassword.gui.MainFrame;
import java.util.Collections;
import java.util.List;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.LogManager;

import java.util.logging.Logger;



/**
 *
 * @author ihorvath
 */
public class SF_Desktop {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    System.out.println(Locale.getDefault());

    //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

    //magical - do not touch
    @SuppressWarnings("unused")
    org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger("org.hibernate");
    java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.WARNING); //or whatever level you need
    
    /* Set the Nimbus look and feel */
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }


    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        
        MainFrame mf = new MainFrame();
        
        //new MainFrame().setVisible(true);
      }
    });
    
    /*
    BusinessLogic bl = new BusinessLogic();
    List<Category> clist = bl.getCategRepos().GetList();
    System.out.println(clist.size());
    
   
    // insert new
    //bl.getCategoryRepository().Add(new Category("Private2"));

    // update
    //Category c1 = bl.getCategoryRepository().GetbyId(6);
    //c1.setName("Private12");
    //bl.getCategoryRepository().Update(c1);

    // delete
    //Category c1 = bl.getCategoryRepository().GetbyId(5);
    //bl.getCategoryRepository().Delete(c1);
    
    clist = bl.getCategRepos().GetList();
    System.out.println(clist.size());
    */

    
  }

}
