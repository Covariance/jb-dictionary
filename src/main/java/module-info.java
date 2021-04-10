module ru.covariance.jb.dictionary {
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.graphics;

  exports ru.covariance.jb.dictionary;

  opens ru.covariance.jb.dictionary to javafx.fxml;
}