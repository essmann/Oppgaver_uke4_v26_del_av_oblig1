package data102.filmarkiv.impl;

import data102.filmarkiv.adt.FilmarkivADT;

import java.util.Scanner;

public class Tekstgrensesnitt {
    Scanner input = new Scanner(System.in);

    // Leser inn opplysninger om en film fra tastatur og returnere et Film-objekt
    public Film lesFilm() {
        input.nextLine();

        System.out.println("Tittel: ");
        String tittel = input.nextLine();

        System.out.println("FilmNr: ");
        int filmNr = input.nextInt();
        input.nextLine();

        System.out.println("Produsent navn: ");
        String navn = input.nextLine();

        System.out.println("Lansering: ");
        int lansering = input.nextInt();
        input.nextLine();

        System.out.println("Sjanger: ");
        System.out.println(Film.Sjanger.sjangerToString());
        String sjangerInput = input.nextLine();
        Film.Sjanger sjanger = Film.Sjanger.finnSjanger(sjangerInput);

        System.out.println("Filmselskap: ");
        String filmselskap = input.nextLine();

        return new Film(filmNr, navn, tittel, lansering, sjanger, filmselskap);
    }

    // Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)
    public void skrivUtFilm(Film film) {
        if (film == null) {
            System.out.println("Ingen film å vise.");
            return;
        }

        System.out.println("=================================");
        System.out.println("FilmNr: " + film.getFilmNr());
        System.out.println("Tittel: " + film.getTittel());
        System.out.println("Produsent: " + film.getNavn());
        System.out.println("Lansering: " + film.getLansering());
        System.out.println("Sjanger: " + film.getSjanger());
        System.out.println("Filmselskap: " + film.getFilmselskap());
        System.out.println("=================================");
    }

    // Skriver ut alle filmer med en spesiell delstreng i tittelen
    public void skrivUtFilmDelstrengITittel(FilmarkivADT arkiv, String delstreng) {
        Film[] filmer = arkiv.sokTittel(delstreng);

        if (filmer == null || filmer.length == 0) {
            System.out.println("Ingen filmer funnet med '" + delstreng + "' i tittelen.");
            return;
        }

        System.out.println("\nFilmer med '" + delstreng + "' i tittelen:");
        System.out.println("Antall treff: " + filmer.length);
        for (Film film : filmer) {
            skrivUtFilm(film);
        }
    }

    // Skriver ut alle Filmer av en produsent (produsent er delstreng)
    public void skrivUtFilmProdusent(FilmarkivADT arkiv, String delstreng) {
        Film[] filmer = arkiv.soekProdusent(delstreng);

        if (filmer == null || filmer.length == 0) {
            System.out.println("Ingen filmer funnet fra produsent '" + delstreng + "'.");
            return;
        }

        System.out.println("\nFilmer fra produsent '" + delstreng + "':");
        System.out.println("Antall treff: " + filmer.length);
        for (Film film : filmer) {
            skrivUtFilm(film);
        }
    }

    // Skriver ut en enkel statistikk som inneholder antall filmer totalt
// og hvor mange det er i hver sjanger.
    public void skrivUtStatistikk(FilmarkivADT arkiv) {
        int totaltAntall = arkiv.antall();

        System.out.println("\n=== STATISTIKK ===");
        System.out.println("Totalt antall filmer: " + totaltAntall);
        System.out.println("\nAntall filmer per sjanger:");
        System.out.println("---------------------------------");

        // Iterate through all genres
        for (Film.Sjanger sjanger : Film.Sjanger.values()) {
            int antallSjanger = arkiv.antall(sjanger);
            System.out.println(sjanger + ": " + antallSjanger);
        }
        System.out.println("=================================");
    }
// osv ... andre metoder
}