package com.example.laskin;

    import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

    /**
     * Luokka mahdollistaa sählyjoukkueeseen liittymisen ja sisältää pelaajien etu- ja sukunimen, kätisyyden ja pelinumeron, alustajat sekä toString metodin.
     * @author Matias Nykänen
     * @version 1.0 2022/03/16
     */

    public class testi extends Application implements Serializable {
        /**
         * Etunimi merkkijonona.
         */
        private String etunimi;
        /**
         * Sukunimi merkkijonona.
         */
        private String sukunimi;
        /**
         * Kätisyys merkkijonona.
         */
        private String katisyys;
        /**
         * Pelinumero kokonaislukuna.
         */
        private int pelinumero;

        /**
         * Parametritön alustaja pelaajalle.
         * Luo pelaajan ilman tietoja.
         */
        public testi(){
        }

        /**
         * Alustaja pelaajan perustiedoilla.
         * @param etunimi String pelaajan etunimi
         * @param sukunimi String pelaajan sukunimi
         * @param katisyys String pelaajan katisyys
         * @param pelinumero int pelaajan pelinumero
         */
        public testi(String etunimi, String sukunimi, String katisyys, int pelinumero){
            this.etunimi = etunimi;
            this.sukunimi = sukunimi;
            this.katisyys = katisyys;
            this.pelinumero = pelinumero;
        }


        /**
         * toString metodi, jotta pelaajien tiedot voidaan lisätä listaan
         * @return String
         */
        @Override
        public String toString() {
            return this.etunimi + " " + this.sukunimi + " / " + this.katisyys + " / " + this.pelinumero;
        }
        /**
         * Pääohjelma, jossa muodostetaan graafinen käyttöliittymä
         * ja pelaajien tiedostoon tallennus ja sieltä pelaajien tietojen lukeminen.
         * @param alkuIkkuna parametrinä alkuIkkuna-niminen Stage
         */
        @Override
        public void start(Stage alkuIkkuna) {
            HBox paneeli = new HBox();

            VBox tekstit = new VBox(10);
            tekstit.setPadding(new Insets(15, 15, 15, 15));

            VBox luettelo = new VBox(10);
            luettelo.setPadding(new Insets(15, 15, 15, 15));

            //Tekstikentät käyttäjälle:
            TextField etunimi = new TextField();
            TextField sukunimi = new TextField();
            TextField katisyys = new TextField();
            TextField pelinumero = new TextField();

            //Pelinumero kenttään voi syöttää vain kokonaislukuja:
            pelinumero.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));

            TextFlow flow = new TextFlow();
            Text otsikko = new Text("Lisää pelaaja: ");
            otsikko.setStyle("-fx-font-weight: bold");
            flow.getChildren().add(otsikko);

            tekstit.getChildren().add(flow);
            tekstit.getChildren().addAll(new Label("Etunimi: "), etunimi);
            tekstit.getChildren().addAll(new Label("Sukunimi: "), sukunimi);
            tekstit.getChildren().addAll(new Label("Kätisyys: "), katisyys);
            tekstit.getChildren().addAll(new Label("Pelinumero: "), pelinumero);

            Button but1 = new Button("Lisää");
            tekstit.getChildren().add(but1);

            //Pelaajaluettelon muodostus:
            ListView<String> listView = new ListView<>();
            ObservableList<String> list = FXCollections.observableArrayList();
            listView.setItems(list);

            //"Lisää"-napissa on tiedostoon tallennus ja tiedostosta lukeminen:
            but1.setOnAction(e -> {

                testi pel1 = new testi(etunimi.getText(), sukunimi.getText(), katisyys.getText(), Integer.parseInt(pelinumero.getText()));

                String tNimi = "Pelaajat.dat";

                ObjectOutputStream kTiedosto = null;

                try{
                    kTiedosto = new ObjectOutputStream(new FileOutputStream(tNimi));
                    kTiedosto.writeObject(pel1);
                }
                catch(Exception e1){
                    e1.printStackTrace();
                }

                ObjectInputStream lTiedosto = null;

                try{
                    lTiedosto = new ObjectInputStream(new FileInputStream(tNimi));

                    testi pel2 = (testi) lTiedosto.readObject();

                    list.addAll(pel2.toString());

                }

                catch(IOException | ClassNotFoundException e1){
                    e1.printStackTrace();

                } finally{
                    try{
                        if(kTiedosto != null) kTiedosto.close();
                    }
                    catch(IOException e1){
                        e1.printStackTrace();
                    }
                    try{
                        if(lTiedosto != null) lTiedosto.close();
                    }
                    catch(IOException e1){
                        e1.printStackTrace();
                    }
                }
            });

            paneeli.getChildren().add(tekstit);

            TextFlow flow2 = new TextFlow();
            Text otsikko2 = new Text("Pelaajaluettelo(Nimi / Kätisyys / Pelinumero): ");
            otsikko2.setStyle("-fx-font-weight: bold");
            flow2.getChildren().add(otsikko2);

            luettelo.getChildren().addAll(flow2, new ScrollPane(listView));

            paneeli.getChildren().add(luettelo);

            Scene kehys = new Scene(paneeli, 470, 400);
            alkuIkkuna.setTitle("Sählyjoukkueeseen liittyminen");
            alkuIkkuna.setScene(kehys);
            alkuIkkuna.show();
        }


        /**
         * @param args ei parametrejä käytössä
         */
        public static void main(String [] args){
            Application.launch(args);
        }

    }

