package ru.covariance.jb.dictionary;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public final class MainController {

  @FXML
  private TextField search;

  @FXML
  private ListView<String> listing;

  private Dictionary dictionary;

  @FXML
  void initialize() {
    assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'main.fxml'.";
    assert listing != null : "fx:id=\"listing\" was not injected: check your FXML file 'main.fxml'.";

    dictionary = new Dictionary(listing.getItems());
    search.setOnKeyTyped(keyEvent -> dictionary.query(search.getText()));
  }
}
