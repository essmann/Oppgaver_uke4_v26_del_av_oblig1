package data102.filmarkiv.example;

import data102.filmarkiv.adt.FilmarkivADT;
import data102.filmarkiv.impl.Film;
import data102.filmarkiv.impl.Filmarkiv;
import data102.filmarkiv.impl.Meny;

public class FilmarkivMain {
//        private Filmarkiv filmarkiv;
    public static void main(String[] args) {

        Filmarkiv filmarkiv = new Filmarkiv(100);

        Meny meny = new Meny(filmarkiv);
        meny.start();
    }
}