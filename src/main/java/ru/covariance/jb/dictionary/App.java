package ru.covariance.jb.dictionary;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class App extends Application {
  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
    primaryStage.setTitle("Dictionary");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }
}
