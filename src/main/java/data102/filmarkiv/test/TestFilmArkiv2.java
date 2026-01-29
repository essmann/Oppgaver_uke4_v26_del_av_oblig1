package data102.filmarkiv.test;

import data102.filmarkiv.impl.Film;
import data102.filmarkiv.impl.FilmArkiv2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFilmArkiv2 {

    private FilmArkiv2 filmarkiv;
    private Film film1, film2, film3, film4, film5, film6;

    @BeforeEach
    void setUp() {
        filmarkiv = new FilmArkiv2();

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

        filmarkiv.leggTilFilm(film1);
        filmarkiv.leggTilFilm(film2);
        filmarkiv.leggTilFilm(film3);
        filmarkiv.leggTilFilm(film4);
        filmarkiv.leggTilFilm(film5);
        filmarkiv.leggTilFilm(film6);
    }

    // ===== SØK TESTER =====

    @Test
    void testSokTittel() {
        Film[] filmer = filmarkiv.sokTittel("Fuzz");
        assertEquals(1, filmer.length);
        assertEquals(film6.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testSokTittelCaseInsensitive() {
        Film[] filmer = filmarkiv.sokTittel("INCEPTION");
        assertEquals(1, filmer.length);
        assertEquals(film1.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testSokTittelIngenResultater() {
        Film[] filmer = filmarkiv.sokTittel("xyz123");
        assertEquals(0, filmer.length);
    }

    @Test
    void testSoekProdusent() {
        Film[] filmer = filmarkiv.soekProdusent("Spielberg");
        assertEquals(1, filmer.length);
        assertEquals(film3.getFilmNr(), filmer[0].getFilmNr());
    }

    // ===== FINN FILM =====

    @Test
    void testFinnFilmEksisterer() {
        Film funnet = filmarkiv.finnFilm(3);
        assertNotNull(funnet);
        assertEquals("Jurassic Park", funnet.getTittel());
    }

    @Test
    void testFinnFilmEksistererIkke() {
        assertNull(filmarkiv.finnFilm(999));
    }

    // ===== LEGG TIL =====

    @Test
    void testLeggTilFilm() {
        Film nyFilm = new Film(7, "James Cameron", "Avatar", 2009,
                Film.Sjanger.SCIFI, "20th Century Fox");

        assertTrue(filmarkiv.leggTilFilm(nyFilm));
        assertEquals(7, filmarkiv.antall());
        assertNotNull(filmarkiv.finnFilm(7));
    }

    @Test
    void testLeggTilDuplikatFilm() {
        Film duplikat = new Film(1, "Test", "Test", 2020,
                Film.Sjanger.ACTION, "Test");

        assertFalse(filmarkiv.leggTilFilm(duplikat));
        assertEquals(6, filmarkiv.antall());
    }

    // ===== SLETT =====

    @Test
    void testSlettFilm() {
        assertTrue(filmarkiv.slettFilm(3));
        assertEquals(5, filmarkiv.antall());
        assertNull(filmarkiv.finnFilm(3));
    }

    @Test
    void testSlettFilmEksistererIkke() {
        assertFalse(filmarkiv.slettFilm(999));
        assertEquals(6, filmarkiv.antall());
    }

    @Test
    void testSlettForsteFilm() {
        assertTrue(filmarkiv.slettFilm(1));
        assertNull(filmarkiv.finnFilm(1));
    }

    @Test
    void testSlettSisteFilm() {
        assertTrue(filmarkiv.slettFilm(6));
        assertNull(filmarkiv.finnFilm(6));
    }

    // ===== ANTALL =====

    @Test
    void testAntallTotalt() {
        assertEquals(6, filmarkiv.antall());
    }

    @Test
    void testAntallPerSjanger() {
        assertEquals(1, filmarkiv.antall(Film.Sjanger.SCIFI));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.KOMEDIE));
    }

    @Test
    void testAntallEtterSletting() {
        filmarkiv.slettFilm(2);
        filmarkiv.slettFilm(4);
        assertEquals(4, filmarkiv.antall());
    }
}
