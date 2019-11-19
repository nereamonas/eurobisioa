package ehu.isad.controller.ui;

import ehu.isad.Main;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    private String herrialdea;

    public void setMainApp(Main main) {
        this.mainApp = mainApp;
    }

    public void setHerrialdea(String herrialde) {
        herrialdea=herrialde;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void onClick(ActionEvent actionEvent) {
    }
}