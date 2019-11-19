package ehu.isad;

import ehu.isad.controller.ui.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent sarreraUI;
  private Parent herrialdeUI;
  private Parent infoUI;
  private Parent bozkatuUI;
  private Parent puntuakIkusiUI;

  private Stage stage;

  private SarreraKud sarreraKud;
  private HerrialdeKud herrialdeKud;
  private InfoKud infoKud;
  private BozkatuKud bozkatuKud;
  private PuntuakIkusiKud puntuakIkusiKud;

  @Override
  public void start(Stage primaryStage) throws Exception {

    stage = primaryStage;
    pantailakKargatu();

    stage.setTitle("Eurobisioa");
    stage.setScene(new Scene(bozkatuUI));
    stage.show();
  }

  private void pantailakKargatu() throws IOException {

    FXMLLoader loaderSarrera = new FXMLLoader(getClass().getResource("/view/1.fxml"));
    sarreraUI = (Parent) loaderSarrera.load();
    sarreraKud = loaderSarrera.getController();
    sarreraKud.setMainApp(this);

    FXMLLoader loaderHerrialde = new FXMLLoader(getClass().getResource("/view/2.fxml"));
    herrialdeUI = (Parent) loaderHerrialde.load();
    herrialdeKud = loaderHerrialde.getController();
    herrialdeKud.setMainApp(this);

    FXMLLoader loaderInfo = new FXMLLoader(getClass().getResource("/view/3.fxml"));
    infoUI = (Parent) loaderInfo.load();
    infoKud = loaderInfo.getController();
    infoKud.setMainApp(this);

    FXMLLoader loaderBozkatu = new FXMLLoader(getClass().getResource("/view/5.fxml"));
    bozkatuUI = (Parent) loaderBozkatu.load();
    bozkatuKud = loaderBozkatu.getController();
    bozkatuKud.setMainApp(this);

    FXMLLoader loaderPuntuakIkusi = new FXMLLoader(getClass().getResource("/view/4.fxml"));
    puntuakIkusiUI = (Parent) loaderPuntuakIkusi.load();
    puntuakIkusiKud = loaderPuntuakIkusi.getController();
    puntuakIkusiKud.setMainApp(this);
  }


  public static void main(String[] args) {
    launch(args);
  }

  public void irekiHerrialde() {
    stage.setScene(new Scene(herrialdeUI));
    stage.show();
  }

  public void irekiInfo() {
    stage.setScene(new Scene(infoUI));
    stage.show();
  }

  public void irekiBozkatu() {
    stage.setScene(new Scene(bozkatuUI));
    stage.show();
  }

  public void irekiPuntuakIkusi() {
    stage.setScene(new Scene(puntuakIkusiUI));
    stage.show();
  }

  public void herrialdeaEmanalt5(String herrialdea) {
    bozkatuKud.setHerrialdea(herrialdea);
  }

  public void herrialdeaEmanal3(String herrialdea) {
    infoKud.setHerrialdea(herrialdea);
  }


}
