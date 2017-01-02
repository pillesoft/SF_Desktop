/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.guifx;


import com.ibh.safepassword.App;
import com.ibh.safepassword.LoginMessage;
import com.ibh.safepassword.MessageService;
import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.dal.IBHDatabaseException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 * http://security.stackexchange.com/questions/29019/are-passwords-stored-in-memory-safe?rq=1
 * @author ihorvath
 */
public class LoginController extends BaseController implements Initializable {

  @FXML
  private TextField _txtUserName;
  @FXML
  private PasswordField _txtPassword;
  @FXML
  private PasswordField _txtEncrPassword;
  @FXML
  private Label _lblError;
  
  private BusinessLogic _bl;
  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
 
  @FXML
  private void handleLogin() {
    _lblError.setText("");

    try {
      if (!mainAppl.getBL().Login(_txtUserName.getText().trim(), _txtPassword.getText().toCharArray(), _txtEncrPassword.getText().toCharArray())) {
        //_lblError.setText(_bundle.getString("LoginDialog.lblStatus.text"));
        _lblError.setText("login error");
      } else {
        _lblError.setText("login ok");
        MessageService.Send(LoginMessage.class);
        //mainappl.PostLogin();
//        this.dispose();

      }
    } catch (IBHDatabaseException dbe) {
      //_lblError.setText(_bundle.getString("LoginDialog.lblStatus.DBNOTAvailable"));
      _lblError.setText("db not exists");
    }
  }
  
  @FXML
  private void handleCreateDB() {
    
  }

  @FXML
  private void handleOptions() {
    
  }

}
