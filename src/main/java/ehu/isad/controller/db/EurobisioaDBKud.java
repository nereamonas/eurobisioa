package ehu.isad.controller.db;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EurobisioaDBKud {

  // singleton patroia

  private static EurobisioaDBKud instantzia = new EurobisioaDBKud();

  public static EurobisioaDBKud getInstantzia(){
      return instantzia;
  };

  private EurobisioaDBKud(){}

  public ObservableList<Datuak> sql3(String hautatutakoHerrialde){
      ObservableList<Datuak> emaitza = FXCollections.observableArrayList();
      DBKudeatzaile dbkud =DBKudeatzaile.getInstantzia();
      String query ="select H.bandera, H.izena, O.artista, O.abestia from Herrialde H, Ordezkaritza O WHERE H.izena=O.herrialdea AND O.urtea=strftime('%Y','now')";
      ResultSet rs =dbkud.execSQL(query);

      try{
        while (rs.next() ) {
          String bandera = rs.getString("bandera");
          String izena = rs.getString("izena");
          String artistaIzena = rs.getString("artista");
          String abestiIzena = rs.getString("abestia");

          Datuak dBat= new Datuak(bandera,izena,artistaIzena,abestiIzena,0);
          emaitza.add(dBat);
        }
      }catch  ( SQLException e) {
        System.err.println( e);
      }
      return emaitza;
  }

  public void sql4(String hautatutakoHerrialde, String bozkatutakoHerrialde, int emandakoPuntuak){
      DBKudeatzaile dbkud =DBKudeatzaile.getInstantzia();
      String query ="INSERT INTO Bozkaketa VALUES ("+bozkatutakoHerrialde+","+hautatutakoHerrialde+",strftime('%Y','now'),"+emandakoPuntuak+")";
      ResultSet rs =dbkud.execSQL(query);

  }

  public void sql5(String bozkatutakoHerrialde, int emandakoPuntuak){
      DBKudeatzaile dbkud =DBKudeatzaile.getInstantzia();
      String query ="UPDATE Ordezkaritza set puntuak=puntuak+"+"\""+emandakoPuntuak+"\" WHERE herrialdea="+ "\""+bozkatutakoHerrialde ;
      ResultSet rs =dbkud.execSQL(query);
  }

   public Image bilatuIzenazHerrialdea(String herriIzena){
       DBKudeatzaile dbkud =DBKudeatzaile.getInstantzia();
       String query ="select bandera from Herrialde WHERE izena="+herriIzena;
       ResultSet rs =dbkud.execSQL(query);
       ImageView imageView=null;
       Image image=null;
       try{
       while (rs.next() ) {
           String bandera = rs.getString("bandera");
           FileInputStream input = null;
           try {
               input = new FileInputStream("/opt/eurovision/src/main/resources/banderak/" + bandera + ".png");
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           }
           image = new Image(input);
           imageView = new ImageView(image);
           imageView.setFitHeight(30);
           imageView.setFitWidth(50);
       }
       }catch  ( SQLException e) {
           System.err.println( e);
       }
       return image;
   }

    public List<String> lortuHerrialdeIzenak(){
        List<String> emaitza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "select izena from ParteHartzea where etorrikoDa='bai' and urtea=strftime('%Y',date('now'))";
        ResultSet rs = dbkud.execSQL(query);
        try {
            while (rs.next()) {
                String herrIzena = rs.getString("izena");
                String herr = herrIzena;
                emaitza.add(herrIzena);
            }
        }catch (SQLException e){
            System.err.println(e);
        }
        return emaitza;
    }


    public boolean bozkatuDu(String herrialde) throws SQLException {
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        String query = "select * from Bozkaketa where bozkatuDu='"+herrialde+"' and urtea=strftime('%Y',date('now'))";
        ResultSet rs = dbkud.execSQL(query);
        System.out.println(rs);
        System.out.println(query);
        if(!rs.next()){
            return false;
        }
        return true;
    }




    public ArrayList<ArrayList<String>> lortuDatuak(){

        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();

        String query = "select o.herrialdea , o.puntuak , h.bandera from Ordezkaritza o, Herrialde h where o.herrialdea=h.izena and urtea=strftime('%Y','now' ) order by puntuak desc limit 3 ";

        ResultSet rs = dbkud.execSQL(query);


        ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();

        try {
            while (rs.next()) {
                String izena=rs.getString("herrialdea");
                int puntuak= rs.getInt("puntuak");
                String bandera=rs.getString("bandera");

                ArrayList<String> lerroa = new ArrayList<String>();
                lerroa.add(bandera);
                lerroa.add(izena);
                lerroa.add(Integer.toString(puntuak));
                lista.add(lerroa);
            }
        }catch (SQLException e){
            System.err.println(e);
        }


        return lista;
    }

}

