/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ibh.safepassword.guifx;

import com.ibh.safepassword.dal.AuthLimited;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ihorvath
 */
public class ViewAuthController extends BaseController implements Initializable {

  @FXML
  private TableView<AuthLimited> authTable;
  @FXML
  private TableColumn<AuthLimited, String> categoryColumn;
  @FXML
  private TableColumn<AuthLimited, String> titleColumn;

  @FXML
  private TextField categoryFilter;
  @FXML
  private TextField titleFilter;

  @FXML
  private Label categoryLabel;
  @FXML
  private Label titleLabel;
  @FXML
  private Hyperlink webAddressLabel;
  @FXML
  private Label howOldLabel;
  @FXML
  private TextArea descriptionLabel;

  ObservableList<AuthLimited> data;
  FilteredList<AuthLimited> filteredData;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {

    showDetails(null);

  }

  @Override
  public void setApp(Application app) {
    super.setApp(app);
    data = mainAppl.getBL().getAuthRepos().GetAuthLimited1();

    categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory());
    titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());

    authTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldvalue, newvalue) -> showDetails(newvalue)));

    filteredData = new FilteredList<>(data, p -> true);

    categoryFilter.textProperty().addListener((observable, oldValue, newValue) -> {
      doFilter();
    });

    titleFilter.textProperty().addListener((obs, oldv, newv) -> {
      doFilter();
    });

    SortedList<AuthLimited> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(authTable.comparatorProperty());

    authTable.setItems(sortedData);

  }

  private void showDetails(AuthLimited al) {
    if (al != null) {
      categoryLabel.setText(al.getCategory().getValue());
      titleLabel.setText(al.getTitle().getValue());
      webAddressLabel.setText(al.getWebAddress().getValue());
      howOldLabel.setText(Integer.toString(al.getNumberOfDays().getValue()));
      descriptionLabel.setText(al.getDescription().getValue());
    } else {
      categoryLabel.setText("");
      titleLabel.setText("");
      webAddressLabel.setText("");
      howOldLabel.setText("");
      descriptionLabel.setText("");
    }
  }

  @FXML
  public void handleNew() {

  }

  @FXML
  public void handleEdit() {

  }

  @FXML
  public void handleDelete() {

  }

  @FXML
  public void handleClearFilter() {
    categoryFilter.clear();
    titleFilter.clear();
    filteredData.setPredicate(a -> true);
  }

  @FXML
  public void handleViewAuth() {

  }

  @FXML
  public void handleWebAddressLink() {
    String command = String.format("start %s %s", "firefox", webAddressLabel.getText());
    try {
      Runtime.getRuntime().exec(new String[]{"cmd", "/c", command});
      // this is linux
      //Runtime.getRuntime().exec(new String[] { "chromium-browser", "http://example.com/" });
    } catch (IOException ex) {
      Logger.getLogger(ViewAuthController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private void doFilter() {
    String cfilter = categoryFilter.getText();
    String tfilter = titleFilter.getText();

    filteredData.setPredicate(a -> {

      String lowercFilter = cfilter.toLowerCase();
      String lowertFilter = tfilter.toLowerCase();
      // none of empty
      if (((cfilter != null && !cfilter.isEmpty()) && a.getCategory().getValue().toLowerCase().contains(lowercFilter))
              && ((tfilter != null && !tfilter.isEmpty()) && a.getTitle().getValue().toLowerCase().contains(lowertFilter))) {
        return true;
      }
      // title is empty
      else if (((cfilter != null && !cfilter.isEmpty()) && a.getCategory().getValue().toLowerCase().contains(lowercFilter))
              && (tfilter == null || tfilter.isEmpty())) {
        return true;
      }
      // category is empty
      else if ((cfilter == null || cfilter.isEmpty())
              && ((tfilter != null && !tfilter.isEmpty()) && a.getTitle().getValue().toLowerCase().contains(lowertFilter))) {
        return true;
      }
      return false;
    }
    );
    
  }
}
