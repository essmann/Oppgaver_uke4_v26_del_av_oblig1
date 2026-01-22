package data102.filmarkiv.adt;

import data102.filmarkiv.impl.Film;

public interface FilmarkivADT {

    /**
     *Finner en film med gitt nummer.
     * @param nr Film nummeret til filmen.
     * @return En film med samsvarende film nummer.
     */
    Film finnFilm(int nr);

    /**
     * Legger til en film med gitt nr.
     * @param nyFilm Filmen du vil legge til.
     */
    boolean leggTilFilm(Film nyFilm);

    /**
     * Sletter en film med gitt nr.
     * @param filmnr Filmnummeret til filmen du vil slette.
     * @return sann om du fikk lagt den til, usant om det gikk gale.
     */
    boolean slettFilm(int filmnr);

    /**
     * Søker og henter filmer etter delstreng i film tittel.
     * @param delstreng
     * @return Array med filmer.
     */
    Film[] sokTittel(String delstreng);

    /**
     * Søker og henter filmer med en gitt delstreng i filmprodusent
     * @param delstreng
     * @return
     */
    Film[] soekProdusent(String delstreng);
    /**
     * Finner antall filmer med gitt sjanger
     * @param sjanger
     * @return antall filmer av gitt sjanger.
     */
    int antall(Film.Sjanger sjanger);
    /**
     * @return antall filmer i arkivet
     */
    int antall();
}
