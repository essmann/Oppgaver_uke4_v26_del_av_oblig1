package data102.filmarkiv.impl;

import data102.filmarkiv.adt.FilmarkivADT;

import java.util.Scanner;

public class Meny {
    private Tekstgrensesnitt tekstgr;
    private FilmarkivADT filmarkiv;

    public Meny(FilmarkivADT filmarkiv) {
        tekstgr = new Tekstgrensesnitt();
        this.filmarkiv = filmarkiv;
    }

    public void start() {
        Film[] filmer = new Film[10];

        // Add some dummy films
        filmer[0] = new Film(1, "Produsent A", "The Matrix", 1999, Film.Sjanger.SCIFI, "Keanu Reeves");
        filmer[1] = new Film(2, "Produsent B", "Inception", 2010, Film.Sjanger.SCIFI, "Leonardo DiCaprio");
        filmer[2] = new Film(3, "Produsent C", "The Shawshank Redemption", 1994, Film.Sjanger.DRAMA, "Tim Robbins");
        filmer[3] = new Film(4, "Produsent D", "Pulp Fiction", 1994, Film.Sjanger.ACTION, "John Travolta");
        filmer[4] = new Film(5, "Produsent E", "Forrest Gump", 1994, Film.Sjanger.DRAMA, "Tom Hanks");
        filmer[5] = new Film(6, "Produsent F", "The Dark Knight", 2008, Film.Sjanger.ACTION, "Christian Bale");
        filmer[6] = new Film(7, "Produsent G", "Schindler's List", 1993, Film.Sjanger.DRAMA, "Liam Neeson");
        filmer[7] = new Film(8, "Produsent H", "The Godfather", 1972, Film.Sjanger.DRAMA, "Marlon Brando");

        for (Film film : filmer) {
            if(film!=null){
            filmarkiv.leggTilFilm(film);

            }


        }

        Scanner input = new Scanner(System.in);
        while (true) {
            visMeny();
            int valg = input.nextInt();
            behandleValg(valg, tekstgr);
        }

    }

    private void visMeny() {
        System.out.println();
        System.out.println("Meny");
        System.out.println("=========================");
        System.out.println("1. Legg til ny film");
        System.out.println("2. Søk etter film (tittel)");
        System.out.println("3. Søk filmer fra produsent");
        System.out.println("4. Finn antall filmer");
        System.out.println("5. Vis statistikk");
        System.out.println("0. Avslutt");
        System.out.print("Velg: ");


    }
    private void behandleValg(int valg, Tekstgrensesnitt grensesnitt){
        switch(valg){
            case 1:
                // Legg til ny film
                Film film = grensesnitt.lesFilm();
                filmarkiv.leggTilFilm(film);
                System.out.println("Film lagt til!");
                break;

            case 2:
                // Søk etter film (tittel)
                System.out.print("Søk etter tittel (delstreng): ");
                String tittelSok = new Scanner(System.in).nextLine();
                grensesnitt.skrivUtFilmDelstrengITittel(filmarkiv, tittelSok);
                break;

            case 3:
                // Søk filmer fra produsent
                System.out.print("Søk etter produsent (delstreng): ");
                String produsentSok = new Scanner(System.in).nextLine();

                grensesnitt.skrivUtFilmProdusent(filmarkiv, produsentSok);
                break;

            case 4:
                // Finn antall filmer
                int antall = filmarkiv.antall();
                System.out.println("Totalt antall filmer: " + antall);
                break;

            case 5:
                // Vis statistikk
                grensesnitt.skrivUtStatistikk(filmarkiv);
                break;

            case 0:
                // Avslutt
                System.out.println("Avslutter programmet...");
                System.exit(0);
                break;

            default:
                System.out.println("Ugyldig valg. Prøv igjen.");
                break;
        }
    }

}