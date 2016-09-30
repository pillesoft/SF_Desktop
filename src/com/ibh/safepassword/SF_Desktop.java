/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword;

import com.ibh.safepassword.gui.MainFrame;

import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




/**
 *
 * @author ihorvath
 */
public class SF_Desktop {

  private static final Logger logger = LogManager.getLogger(SF_Desktop.class.getName());

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    
    System.out.println(Locale.getDefault());
  
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
      logger.error("", ex);
    } catch (InstantiationException ex) {
      logger.error("", ex);
    } catch (IllegalAccessException ex) {
      logger.error("", ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      logger.error("", ex);
    }


    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        
        try {
          MainFrame mf = new MainFrame();
        }
        catch (Exception ex) {
          logger.error("Unexpected Error", ex);
        }
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
