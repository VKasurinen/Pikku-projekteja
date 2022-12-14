package com.example.laskin;


import java.io.Serializable;

/**
 * Tämän luokan avulla luomme kentät ja metodit laskinta varten.
 * @author Väinö Kasurinen
 */


public class Laskinluokka implements Serializable {   //tässä on kentät 2 yhteenlaskettavalle luvulle, operaattoreille ja tulokselle.
    private boolean nro2=false;
    private int numero1=0;
    private int numero2=0;
    private char operaattorit;
    private String tulos = "";

    /**
     * parametriton alustaja laskimelle.
     * luo tyhjän laskinluokan
     */

    public Laskinluokka(){  //parametriton alustaja

    }

    //tein toisen konstruktoin kopiota varten, että laskimen historia toimisi
    //muutoin se nollaisi aina tuloksen yhteenlaskun jälkeen, jos kopiota ei tehdä.

    /**
     * talletetaan kopio tiedoista, jotta historia toimisi oikein.
     * @param kopio tallentaa kopioidut kentät
     */

    public Laskinluokka(Laskinluokka kopio){
        this.numero1=kopio.getNumero1();
        this.numero2=kopio.getNumero2();
        this.operaattorit=kopio.getOperaattorit();
        this.tulos=kopio.getTulos();
        this.nro2=kopio.isNro2();
    }


    /**
     * konstruktori
     *
     * @param numero1 ensimmäinen yhteenlaskettava
     * @param numero2 toinen yhteenlaskettava
     * @param operaattorit mitä luvuille tehdään
     * @param tulos tarvitaan tiedostoissa laskutoimituksen esittämiseen
     */

    public Laskinluokka(int numero1,int numero2,char operaattorit, String tulos){ //normaali konstruktori kentille
        this.numero1=numero1;
        this.numero2=numero2;
        this.operaattorit=operaattorit;
        this.tulos=tulos;
    }


    /**
     * palauttaa laskutoimituksen
     * @return String tulos
     */

    public String getTulos() {
        return tulos;
    }

    /**
     * palauttaa laskimen ensimmäisen numeron
     * @return int numero1
     */

    public int getNumero1() {
        return numero1;
    }


    /**
     * palauttaa totuusarvon onko numero1 vai 2
     * @return boolean nro2
     */

    public boolean isNro2() {
        return nro2;
    }

    /**
     *  palauttaa laskimen toisen numeron
     * @return int numero2
     */

    public int getNumero2() {
        return numero2;
    }

    /**
     * palauttaa operaattorin kirjaimen
     * @return char operaattorit
     */

    public char getOperaattorit() {
        return operaattorit;
    }



    /**
     * Tämä metodi laskee käyttäjän syöttämän laskun
     * Switch casella
     */
    public void laskutoimitus(){
        switch (operaattorit){
            case '+':
                this.numero1=this.numero1 + this.numero2;
                break;

            case '-':
                this.numero1=this.numero1 - this.numero2;
                break;

            case '/':
                this.numero1=this.numero1 / this.numero2;
                break;

            case '*':
                this.numero1=this.numero1 * this.numero2;
                break;

        }
            this.numero2=0; //numero2 pitää asettaa nollaksi, että voidaan laskea isompia laskuja
                            // esim 2+2+2=6 tai 3+3+3+3=12
    }

    /**
     * Tämä vastaanottaa käyttäjän syötteen numeroista
     * Sen jälkeen tarkastaa onko se ensimmäinen vai toinen luku
     * @param luku laskimen luku
     */

    public int numerovastaanotin(int luku){
        if(this.nro2==false){
            if(this.numero1==0){
                this.numero1=luku;
            }

            else {
                String str = Integer.toString(this.numero1);
                str = str.concat(Integer.toString(luku));
                this.numero1=Integer.parseInt(str);
            }
        }
         else {
            if(this.numero2==0){
                this.numero2=luku;
            }
            else {
                String str = Integer.toString(this.numero2);
                str = str.concat(Integer.toString(luku));
                this.numero2=Integer.parseInt(str);
            }
        }
         if (nro2 == true){
             return this.numero2;
         } else {
             return this.numero1;
         }

        }


    /**
     * tässä tarkastetaan, että mikä operaattori on kyseessä
     * perustuen käyttäjän syötteeseen
     * @param kirjain parametri kirjaimille
     */

    public void operaattorivastaanotin(char kirjain){

        if (this.nro2 == false){
            this.nro2 = true;
            this.operaattorit = kirjain;
        }
        else {
            laskutoimitus();
        }
    }

    /**
     * Tässä metodissa luodaan "C" nappulalle toiminto
     * eli nollaus
     */

    public void nollaus(){
        nro2=false;
        this.numero1=0;
        this.numero2=0;
    }

    /**
     * Tämä metodi luo pohjan "=" merkille
     * @return palauttaa numero1 eli laskun tuloksen
     */

    public int yhtäsuurikuin(){
        this.tulos = Integer.toString(numero1) + Character.toString(operaattorit) + Integer.toString(numero2);
        System.out.println("Laskutoimitus on " + tulos);
        laskutoimitus();
        System.out.println("Tulos on: " + numero1);
        return numero1;
    }

}
