package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaDBKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HerrialdeKud implements Initializable {

    // Reference to the main application.
    private Main mainApp;

    @FXML
    private ComboBox cBox;

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        kargatu();
    }

    public void kargatu(){
        List<String> herrialdeak = EurobisioaDBKud.getInstantzia().lortuHerrialdeIzenak(); //aqui que el select me devuelva un array o algo
        ObservableList<String> herrialdeList = FXCollections.observableArrayList();
        herrialdeak.forEach((elem) -> {
            herrialdeList.add(elem);
        });
        cBox.setItems(herrialdeList);
    }

    public void onClick(ActionEvent actionEvent) {
        //si ya ha votado erakutsi3 si no erakutsi5
        String herrialdea = (String) cBox.getValue();
        try {
            if(EurobisioaDBKud.getInstantzia().bozkatuDu(herrialdea)){
                mainApp.herrialdeaEmanal3(herrialdea);
                mainApp.irekiInfo();
            }else{
                mainApp.herrialdeaEmanalt5(herrialdea);
                mainApp.irekiInfo();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}