package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.EurobisioaDBKud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class PuntuakIkusiKud implements Initializable {
    private ehu.isad.Main mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            top3bistaratu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ImageView lehenaArgazkia;
    @FXML
    private ImageView bigarrenaArgazkia;
    @FXML
    private ImageView hirugarrenaArgazkia;
    @FXML
    private Label lehena;
    @FXML
    private Label bigarrena;
    @FXML
    private Label hirugarrena;
    @FXML
    private ImageView eurobisio;






    public void setMainApp(Main main) {
        this.mainApp=main;
    }



    public void top3bistaratu() throws IOException {


        ArrayList<ArrayList<String>> lista = EurobisioaDBKud.getInstantzia().lortuDatuak();

        lehena.setText(lista.get(0).get(1)+ " - " + lista.get(0).get(2) + " puntu");
        bigarrena.setText(lista.get(1).get(1) + " - " + lista.get(1).get(2) + " puntu");
        hirugarrena.setText(lista.get(2).get(1)+ " - " + lista.get(2).get(2) + " puntu");


        lehenaArgazkia.setImage(lortuIrudia( "banderak/"+lista.get(0).get(0)+ ".png") );
        bigarrenaArgazkia.setImage(lortuIrudia( "banderak/"+lista.get(1).get(0)+ ".png") );
        hirugarrenaArgazkia.setImage(lortuIrudia( "banderak/"+lista.get(2).get(0)+ ".png") );
        eurobisio.setImage(lortuIrudia("eurobisioa.png"));





    }


    public void onClick(ActionEvent actionEvent) {
        mainApp.itxi();

    }

    private Image lortuIrudia(String location) throws IOException {
        FileInputStream input = null;
        try {
            input = new FileInputStream("/opt/eurovision/src/main/resources/"+location);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        return image;

    }


}