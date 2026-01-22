package data102.filmarkiv.test;


import data102.filmarkiv.impl.Film;
import data102.filmarkiv.impl.Filmarkiv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFilmArkiv {
    // Test Film-objekter
    private Filmarkiv filmarkiv;
    private Film film1, film2, film3, film4, film5, film6;


    @BeforeEach
    void setUp() {
        // Opprett nytt arkiv før hver test
        filmarkiv = new Filmarkiv(6);

        // Opprett filmene
        film1 = new Film(1, "Christopher Nolan", "Inception", 2010,
                Film.Sjanger.SCIFI, "Warner Bros");
        film2 = new Film(2, "Quentin Tarantino", "Pulp Fiction", 1994,
                Film.Sjanger.THRILLER, "Miramax");
        film3 = new Film(3, "Steven Spielberg", "Jurassic Park", 1993,
                Film.Sjanger.ACTION, "Universal Pictures");
        film4 = new Film(4, "Ridley Scott", "Alien", 1979,
                Film.Sjanger.HORROR, "20th Century Fox");
        film5 = new Film(5, "Frank Darabont", "The Shawshank Redemption", 1994,
                Film.Sjanger.DRAMA, "Columbia Pictures");
        film6 = new Film(6, "Edgar Wright", "Hot Fuzz", 2007,
                Film.Sjanger.KOMEDIE, "Universal Pictures");

        // Legg til filmene - dette er OK fordi vi tester andre metoder
        filmarkiv.leggTilFilm(film1);
        filmarkiv.leggTilFilm(film2);
        filmarkiv.leggTilFilm(film3);
        filmarkiv.leggTilFilm(film4);
        filmarkiv.leggTilFilm(film5);
        filmarkiv.leggTilFilm(film6);
    }

    @Test
    void TestFilmSok() {
        Film[] filmer = filmarkiv.sokTittel("Fuzz");
        assertEquals(film6.getFilmNr(), filmer[0].getFilmNr());
    }
}
