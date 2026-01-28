package data102.filmarkiv.test;

import data102.filmarkiv.impl.Film;
import data102.filmarkiv.impl.Filmarkiv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        // Legg til filmene
        filmarkiv.leggTilFilm(film1);
        filmarkiv.leggTilFilm(film2);
        filmarkiv.leggTilFilm(film3);
        filmarkiv.leggTilFilm(film4);
        filmarkiv.leggTilFilm(film5);
        filmarkiv.leggTilFilm(film6);
    }

    // ===== SØKETESTER =====

    @Test
    void testFilmSokTittel() {
        Film[] filmer = filmarkiv.sokTittel("Fuzz");
        assertEquals(1, filmer.length);
        assertEquals(film6.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testFilmSokTittelCaseInsensitive() {
        Film[] filmer = filmarkiv.sokTittel("INCEPTION");
        assertEquals(1, filmer.length);
        assertEquals(film1.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testFilmSokTittelFlereResultater() {
        Film[] filmer = filmarkiv.sokTittel("the");
        assertTrue(filmer.length >= 1);
    }

    @Test
    void testFilmSokTittelIngenResultater() {
        Film[] filmer = filmarkiv.sokTittel("xyz123");
        assertEquals(0, filmer.length);
    }

    @Test
    void testProdusentSok() {
        Film[] filmer = filmarkiv.soekProdusent("Spielberg");
        assertEquals(1, filmer.length);
        assertEquals(film3.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testProdusentSokCaseInsensitive() {
        Film[] filmer = filmarkiv.soekProdusent("NOLAN");
        assertEquals(1, filmer.length);
        assertEquals(film1.getFilmNr(), filmer[0].getFilmNr());
    }

    @Test
    void testProdusentSokIngenResultater() {
        Film[] filmer = filmarkiv.soekProdusent("Michael Bay");
        assertEquals(0, filmer.length);
    }

    // ===== FINN FILM TESTER =====

    @Test
    void testFinnFilmEksisterer() {
        Film funnet = filmarkiv.finnFilm(3);
        assertNotNull(funnet);
        assertEquals(film3.getFilmNr(), funnet.getFilmNr());
        assertEquals("Jurassic Park", funnet.getTittel());
    }

    @Test
    void testFinnFilmEksistererIkke() {
        Film funnet = filmarkiv.finnFilm(999);
        assertNull(funnet);
    }

    // ===== LEGG TIL FILM TESTER =====

    @Test
    void testLeggTilNyFilm() {
        Film nyFilm = new Film(7, "James Cameron", "Avatar", 2009,
                Film.Sjanger.SCIFI, "20th Century Fox");
        boolean resultat = filmarkiv.leggTilFilm(nyFilm);

        assertTrue(resultat);
        assertEquals(7, filmarkiv.antall());
        assertNotNull(filmarkiv.finnFilm(7));
    }

    @Test
    void testLeggTilDuplikatFilm() {
        Film duplikat = new Film(1, "Test Produsent", "Test Film", 2020,
                Film.Sjanger.ACTION, "Test Studio");
        boolean resultat = filmarkiv.leggTilFilm(duplikat);

        assertFalse(resultat);
        assertEquals(6, filmarkiv.antall()); // Antallet skal ikke endre seg
    }

    @Test
    void testLeggTilFilmMedUtvidelse() {
        // Arkivet har kapasitet 6, legg til film nummer 7 og 8
        Film film7 = new Film(7, "Denis Villeneuve", "Dune", 2021,
                Film.Sjanger.SCIFI, "Warner Bros");
        Film film8 = new Film(8, "Greta Gerwig", "Barbie", 2023,
                Film.Sjanger.KOMEDIE, "Warner Bros");

        assertTrue(filmarkiv.leggTilFilm(film7));
        assertTrue(filmarkiv.leggTilFilm(film8));
        assertEquals(8, filmarkiv.antall());
    }

    // ===== SLETT FILM TESTER =====

    @Test
    void testSlettFilmEksisterer() {
        boolean resultat = filmarkiv.slettFilm(3);

        assertTrue(resultat);
        assertEquals(5, filmarkiv.antall());
        assertNull(filmarkiv.finnFilm(3));
    }

    @Test
    void testSlettFilmEksistererIkke() {
        boolean resultat = filmarkiv.slettFilm(999);

        assertFalse(resultat);
        assertEquals(6, filmarkiv.antall());
    }

    @Test
    void testSlettForsteFilm() {
        boolean resultat = filmarkiv.slettFilm(1);

        assertTrue(resultat);
        assertEquals(5, filmarkiv.antall());
        assertNull(filmarkiv.finnFilm(1));
    }

    @Test
    void testSlettSisteFilm() {
        boolean resultat = filmarkiv.slettFilm(6);

        assertTrue(resultat);
        assertEquals(5, filmarkiv.antall());
        assertNull(filmarkiv.finnFilm(6));
    }

    // ===== ANTALL TESTER =====

    @Test
    void testAntallTotalt() {
        assertEquals(6, filmarkiv.antall());
    }

    @Test
    void testAntallTomtArkiv() {
        Filmarkiv tomtArkiv = new Filmarkiv(10);
        assertEquals(0, tomtArkiv.antall());
    }

    @Test
    void testAntallEtterSletting() {
        filmarkiv.slettFilm(2);
        filmarkiv.slettFilm(4);
        assertEquals(4, filmarkiv.antall());
    }

    @Test
    void testAntallPerSjanger() {
        assertEquals(1, filmarkiv.antall(Film.Sjanger.SCIFI));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.DRAMA));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.HORROR));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.KOMEDIE));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.ACTION));
        assertEquals(1, filmarkiv.antall(Film.Sjanger.THRILLER));
    }

    @Test
    void testAntallSjangerIngenFilmer() {
        // Assuming there's a ROMANTIKK genre with no films
        assertEquals(0, filmarkiv.antall(Film.Sjanger.ROMANTIKK));
    }

    @Test
    void testAntallSjangerEtterSletting() {
        filmarkiv.slettFilm(1); // Slett Inception (SCIFI)
        assertEquals(0, filmarkiv.antall(Film.Sjanger.SCIFI));
    }

    @Test
    void testAntallSjangerFlereSammeType() {
        Film film7 = new Film(7, "Denis Villeneuve", "Blade Runner 2049", 2017,
                Film.Sjanger.SCIFI, "Warner Bros");
        filmarkiv.leggTilFilm(film7);

        assertEquals(2, filmarkiv.antall(Film.Sjanger.SCIFI));
    }

    // ===== EDGE CASE TESTER =====

    @Test
    void testSokMedTomStreng() {
        Film[] filmer = filmarkiv.sokTittel("");
        assertEquals(6, filmer.length); // Tom streng matcher alle
    }

    @Test
    void testSokProdusentMedTomStreng() {
        Film[] filmer = filmarkiv.soekProdusent("");
        assertEquals(6, filmer.length); // Tom streng matcher alle
    }

    @Test
    void testSlettAlleFilmer() {
        for (int i = 1; i <= 6; i++) {
            filmarkiv.slettFilm(i);
        }
        assertEquals(0, filmarkiv.antall());
    }

    @Test
    void testLeggTilEtterSletting() {
        filmarkiv.slettFilm(3);
        Film nyFilm = new Film(7, "Christopher Nolan", "Interstellar", 2014,
                Film.Sjanger.SCIFI, "Warner Bros");

        assertTrue(filmarkiv.leggTilFilm(nyFilm));
        assertEquals(6, filmarkiv.antall());
    }

    @Test
    void testRekkefolgeBevartEtterSletting() {
        filmarkiv.slettFilm(3); // Slett film i midten

        Film[] filmer = filmarkiv.sokTittel(""); // Få alle filmer

        // Sjekk at rekkefølgen er bevart (minus den slettede filmen)
        assertEquals(film1.getFilmNr(), filmer[0].getFilmNr());
        assertEquals(film2.getFilmNr(), filmer[1].getFilmNr());
        assertEquals(film4.getFilmNr(), filmer[2].getFilmNr());
        assertEquals(film5.getFilmNr(), filmer[3].getFilmNr());
        assertEquals(film6.getFilmNr(), filmer[4].getFilmNr());
    }
}