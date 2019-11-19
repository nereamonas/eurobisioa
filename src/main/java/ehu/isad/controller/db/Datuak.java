package ehu.isad.controller.db;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Datuak {
    private ImageView bandera;
    private String izena;
    private String artista;
    private String abestia;
    private int puntuak;

    public Datuak(String bandera, String izena, String artista, String abestia, int puntuak) {
        FileInputStream input = null;
        try {
            input = new FileInputStream("/opt/eurovision/src/main/resources/banderak/" + bandera + ".png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(50);
        this.bandera = imageView;
        this.izena = izena;
        this.artista = artista;
        this.abestia = abestia;
        this.puntuak = puntuak;
    }


    public ImageView getBandera() {
        return bandera;
    }

    public String getIzena() {
        return izena;
    }

    public String getArtista() {
        return artista;
    }

    public String getAbestia() {
        return abestia;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak( int puntuak) {
        this.puntuak = puntuak;
    }


}
