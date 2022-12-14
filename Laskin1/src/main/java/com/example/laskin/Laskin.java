package com.example.laskin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Tässä luokassa on laskimen käyttöliittymä ja siihen on myös linkitetty toiminnallisuus Laskinluokasta
 * @author Väinö Kasurinen
 * @version 1.0 2022/03/14
 */

public class Laskin extends Application{

    private ArrayList<Laskinluokka> lukeminen = new ArrayList<Laskinluokka>();
    private TextField textField = new TextField();
    private Laskinluokka nyklaskutoimitus = new Laskinluokka();
    private TableView tableView = new TableView();

    /**
     * ohjelman käynnistävä metodi.
     * @param args kutusparametreja ei käytetä
     */

    public static void main(String[] args) {
        launch(args);
    }


    /**
     * Tässä metodissa luodaan käyttöliittymän osat ja nappuliin toiminnallisuus
     * @param primaryStage stagen parametri, johon liitetään käyttöliittymän osat
     */

    @Override
    public void start(Stage primaryStage) {   //luodaan käyttöliittymän osia


        textField.setFont(Font.font(20));
        textField.setAlignment(Pos.CENTER);
        textField.setEditable(false);
        Button historia = new Button("ANS");

        StackPane paneeli = new StackPane();
        paneeli.setPadding(new Insets(10));
        paneeli.getChildren().add(textField);


        TilePane tilepane = new TilePane();
        tilepane.setHgap(10);
        tilepane.setVgap(10);


        tiedostoluku();  //kutsutaan tiedoston lukemista

        TableColumn<Laskinluokka, String> sarake = new TableColumn<>("Lasku");  //luodaan tableviewille sarakkeet
        sarake.setCellValueFactory(new PropertyValueFactory<>("tulos"));        //laitetaan niihin luetut tiedot.

        TableColumn<Laskinluokka, String> sarake2 = new TableColumn<>("Tulos");
        sarake2.setCellValueFactory(new PropertyValueFactory<>("numero1"));

        tableView.getColumns().add(sarake);
        tableView.getColumns().add(sarake2);

        for (Laskinluokka l : lukeminen){
            tableView.getItems().add(l);
        }




        Button[] nappulat = new Button[16];  //taulukko, johon kaikki laskimen buttonit laitetaan


        tilepane.getChildren().addAll(   //tehdään tilepanella kaikki buttonit ja laitetaan ne taulukkoon.
                nappulat[7]=numerot("7"),
                nappulat[8]=numerot("8"),
                nappulat[9]=numerot("9"),
                nappulat[10]=operaattorit("/"),

                nappulat[4]=numerot("4"),
                nappulat[5]=numerot("5"),
                nappulat[6]=numerot("6"),
                nappulat[11]=operaattorit("*"),

                nappulat[1]=numerot("1"),
                nappulat[2]=numerot("2"),
                nappulat[3]=numerot("3"),
                nappulat[12]=operaattorit("-"),

                nappulat[0] = numerot("0"),
                nappulat[13]=tyhjennä("C"),
                nappulat[14]=operaattorit("="),
                nappulat[15]=operaattorit("+")
        );


        //tapahtumankäsittelijöitä buttoneille, jotka on linkitettynnä luokkaan

        nappulat[0].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(0)));

        });

        nappulat[1].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(1)));
        });

        nappulat[2].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(2)));

        });

        nappulat[3].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(3)));
        });

        nappulat[4].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(4)));

        });

        nappulat[5].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(5)));
        });

        nappulat[6].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(6)));
        });

        nappulat[7].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(7)));
        });

        nappulat[8].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(8)));
        });

        nappulat[9].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.numerovastaanotin(9)));
        });

        nappulat[10].setOnAction(e->{
            nyklaskutoimitus.operaattorivastaanotin('/');
        });

        nappulat[11].setOnAction(e->{
            nyklaskutoimitus.operaattorivastaanotin('*');
        });

        nappulat[12].setOnAction(e->{
            nyklaskutoimitus.operaattorivastaanotin('-');
        });

        nappulat[13].setOnAction(e->{
            nyklaskutoimitus.nollaus();
            textField.setText("0");
        });

        nappulat[14].setOnAction(e->{
            textField.setText(Integer.toString(nyklaskutoimitus.yhtäsuurikuin()));
            tiedostokirjoitus();
            nyklaskutoimitus.nollaus();
        });

        nappulat[15].setOnAction(e->{
            nyklaskutoimitus.operaattorivastaanotin('+');
        });

        historia.setOnAction(e->{
            Stage stage = new Stage();
            Pane tiedostopaneeli = new Pane();
            tiedostopaneeli.getChildren().add(tableView);

            stage.setTitle("Historia");
            stage.setScene(new Scene(tiedostopaneeli,200,300));
            stage.show();

        });



        BorderPane paapaneeli = new BorderPane();


        paapaneeli.setRight(historia);
        paapaneeli.setTop(paneeli);
        paapaneeli.setCenter(tilepane);
        Scene scene = new Scene(paapaneeli,275,300);
        primaryStage.setTitle("Laskin");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Tässä metodissa kirjoitetaan tiedostoon "Tilit.dat"
     * ja ne laitetaan myös laitetaan arraylistista arrayihin.
     * @throws Exception varmistaa kirjoitetaanko tiedostoon oikein, jos ei kirjoita niin se heittää virheen
     */

    public void tiedostokirjoitus(){

        try {
            FileOutputStream outstream = new FileOutputStream("Tilit.dat");
            ObjectOutputStream objstream = new ObjectOutputStream(outstream);

            lukeminen.add(nyklaskutoimitus);
            Laskinluokka[] array = lukeminen.toArray(new Laskinluokka[0]);
            objstream.writeObject(array);
            Laskinluokka uusi = new Laskinluokka(nyklaskutoimitus);


            tableView.getItems().add(uusi);

            outstream.close();
            objstream.close();

        }
        catch (Exception e){
            System.out.println("Virhe tiedoston kirjoittamisessa");
        }
    }

    /**
     * metodi, jossa luetaan tiedostosta
     * data laitetaan arrayhin lopulta, että sitä voidaan käyttää tableviewissä
     * @throws Exception varmistaa luetaanko tiedosto oikein, jos ei lue niin se heittää virheen.
     */

    public void tiedostoluku(){

        try {
            File file = new File("Tilit.dat");
            FileInputStream instream = new FileInputStream("Tilit.dat");
            ObjectInputStream objstream = new ObjectInputStream(instream);

            if(file.exists()){
                Laskinluokka[] array = (Laskinluokka[]) objstream.readObject();
                lukeminen.clear();
                Collections.addAll(lukeminen, array);

                objstream.close();
                instream.close();
            }
        }
        catch (Exception e){
            System.out.println("Virhe tiedoston lukemisessa");
        }

    }


    //tein seuraavakasi metodit kaikille buttoneille, jolloin tilepanen koodi näyttäisi siistimmälle.

    /**
     * tämän metodin avulla tehdään numero buttonit
     *
     * @param n parametri nappulan luontiin String n
     * @return returnaa kaikki buttonit
     */

    private Button numerot(String n){
        Button btn = new Button(n);
        btn.setPrefSize(50,50);
        return btn;

    }

    /**
     * Teemme operaattorit tämän metodin avulla
     * @param ch parametri nappulan luontiin String ch
     * @return returnaa kaikki operaattorit esim. +, -, *
     */

    private Button operaattorit(String ch){
        Button btno = new Button(ch);
        btno.setPrefSize(50,50);
        return btno;
    }

    /**
     * @param ty parametri nappulan luontiin string ty
     * @return returnaa C nappulan, joka tyhjentää laskimen historian ja laskun.
     */

    private Button tyhjennä(String ty){
        Button btnt = new Button(ty);
        btnt.setPrefSize(50,50);
        return btnt;
    }

}
