/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword;

import com.ibh.safepassword.bl.BusinessLogic;
import com.ibh.safepassword.guifx.BaseController;
import com.ibh.safepassword.guifx.LoginController;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ihorvath
 */
public class App extends Application {
  private Stage primaryStage;
  private BorderPane rootlayout;
  private BusinessLogic _bl;

  
  private void showLogin() {
    try {
      // load person overview
      URL fxmlUrl = this.getClass().getClassLoader().getResource("com/ibh/safepassword/guifx/Login.fxml");

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(fxmlUrl);
      GridPane login = (GridPane)loader.load();
      
      AnchorPane anchorpane = new AnchorPane();
      anchorpane.getChildren().addAll(login);
      AnchorPane.setTopAnchor(login, 10.0);
      
      rootlayout.setCenter(anchorpane);
      
      LoginController controller = loader.getController();
      controller.setApp(this);
     
      MessageService.Register(LoginMessage.class, new Runnable() {
        @Override
        public void run() {
          System.out.println(".run()");
          setContent();
        }
      });
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void setContent() {
    try {
      URL fxmlUrl = this.getClass().getClassLoader().getResource("com/ibh/safepassword/guifx/ViewAuth.fxml");

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(fxmlUrl);
      AnchorPane content = (AnchorPane)loader.load();
      
      rootlayout.setCenter(content);
      
      primaryStage.sizeToScene();
      
      BaseController controller = loader.getController();
      controller.setApp(this);
     
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
    
  private void initRootLayout() {
    try {
      // load root layout from fxml file
      URL fxmlUrl = this.getClass().getClassLoader().getResource("com/ibh/safepassword/guifx/RootLayout.fxml");
      
      this.rootlayout =  FXMLLoader.<BorderPane>load(fxmlUrl);
      
      // show the scene containing the root layout
      Scene scene = new Scene(rootlayout);

      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  
  @Override
  public void start(Stage stage) {
    
    this.primaryStage = stage;
    this.primaryStage.setTitle("Safe Password");

    _bl = new BusinessLogic();
    
    //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book.png"));
    initRootLayout();
    
    showLogin();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
  public Stage getPrimaryStage() {
    return primaryStage;
  }
  
  public BusinessLogic getBL() {
    return _bl;
  }
  
  public void PostLogin() {
    
  }

}
