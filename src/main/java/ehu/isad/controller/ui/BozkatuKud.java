package ehu.isad.controller.ui;

import ehu.isad.Main;
import ehu.isad.controller.db.Datuak;
import ehu.isad.controller.db.EurobisioaDBKud;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BozkatuKud implements Initializable {

    public Label label;
    public Label labelG;
    public ImageView bandera;
    // Reference to the main application.
    private Main mainApp;
    private EurobisioaDBKud eurobisioaDBKud;
    public TableView<Datuak> tabla;
    public TableColumn<Datuak, String> argazki;
    public TableColumn<Datuak, String> herrialde;
    public TableColumn<Datuak, String> artista;
    public TableColumn<Datuak, String> abestia;
    public TableColumn<Datuak, Integer> puntuak;

    private String herrialdea;

    public void setMainApp(Main main) {
        this.mainApp = mainApp;
    }

    public void setHerrialdea(String herrialde) {
        herrialdea=herrialde;
    }


        @Override
    public void initialize(URL location, ResourceBundle resources) {
        EurobisioaDBKud eurobisioaDBKud = new EurobisioaDBKud();
        ObservableList<Datuak> lista = eurobisioaDBKud.sql3("Alemania");

        bandera.setImage(eurobisioaDBKud.bilatuIzenazHerrialdea(herrialdea));
        labelG.setText(herrialdea+"horrela nahi ditu bere puntuak banatu");



        tabla.setEditable(true);
        puntuak.setEditable(true);
        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class
        argazki.setCellValueFactory(new PropertyValueFactory<>("bandera"));
        herrialde.setCellValueFactory(new PropertyValueFactory<>("izena"));
        artista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        abestia.setCellValueFactory(new PropertyValueFactory<>("abestia"));
        puntuak.setCellValueFactory(new PropertyValueFactory<>("puntuak"));

        puntuak.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        // para que no deje puntuaar al pais, pero no puedo porq es un int y solo se pueden hacer cosas guais con string jeje
        //Callback<TableColumn<Datuak, String>, TableCell<Datuak, String>> defaultTextFieldCellFactory
        //        = TextFieldTableCell.<Datuak>forTableColumn();
        //puntuak.setCellFactory(col -> {
        //    TableCell<Datuak, String> cell = defaultTextFieldCellFactory.call(col));
        //    cell.itemProperty().addListener((obs, oldValue, newValue) -> {
        //        TableRow row = cell.getTableRow();
        //        if (row == null) {
        //            cell.setEditable(false);
        //        } else {
        //            Datuak item = (Datuak) cell.getTableRow().getItem();
        //            if (item == null) {
        //                cell.setEditable(false);
        //            } else {
        //                cell.setEditable(!item.getIzena().equals("Alemania"));
        //           }
        //        }
        //    });
        //    return cell ;
        //});

        puntuak.setOnEditCommit(
                t -> t.getTableView().getItems().get(t.getTablePosition().getRow())
                        .setPuntuak(t.getNewValue())
        );

        tabla.setItems(lista);
    }

    public void onClick(ActionEvent actionEvent) {
        eurobisioaDBKud= new EurobisioaDBKud();
        Integer puntuTotalak=0;
        for(int i=0;i<tabla.getItems().size();i++) {
            puntuTotalak = puntuTotalak + puntuak.getCellData(i);
        }


        if(puntuTotalak==5){
            for (int i = 0; i < tabla.getItems().size(); i++) {
                if (puntuak.getCellData(i) != 0) {
                    Datuak item = tabla.getItems().get(i);
                    eurobisioaDBKud.sql4("Alemania", item.getIzena(), item.getPuntuak());
                    //eurobisioaDBKud.sql5(item.getIzena(), item.getPuntuak());
                }
            }
            mainApp.irekiPuntuakIkusi();
        }else{
            label.setText("Botoak ez dituzu ondo banatu, saiatu berriro. 5 puntu dituzu banatzeko");
        }
    }








}