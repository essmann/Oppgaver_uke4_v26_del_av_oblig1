package data102.filmarkiv.impl;

import data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {

    private Film[] filmer;
    private int antall;
    private int KAPASITET;

    public Filmarkiv(int kapasitet) {
        this.KAPASITET = kapasitet;
        this.filmer = new Film[kapasitet];
    }

    @Override
    public Film finnFilm(int nr) {
        for (int i = 0; i < antall; i++) {  // Kun gjennom faktiske filmer
            if (filmer[i].getFilmNr() == nr) {
                return filmer[i];
            }
        }
        return null;
    }

    @Override
    public boolean leggTilFilm(Film nyFilm) {
        if (antall == KAPASITET) {
            Film[] nyFilmer = new Film[KAPASITET * 2];

            for (int i = 0; i < KAPASITET; i++) {
                nyFilmer[i] = this.filmer[i];
            }
            this.KAPASITET *= 2;
            this.filmer = nyFilmer;
        } //Dobler tabellen hvis den er full og kopierer filmene fra den gamle tabellen.
        if (finnFilm(nyFilm.getFilmNr()) != null) {
            //Duplicate film, ikke legg til.
            return false;
        }
        filmer[antall] = nyFilm;
        antall++;
        return true;
    }

    @Override
    public boolean slettFilm(int filmnr) {
        for (int i = 0; i < antall; i++) {
            if (filmer[i].getFilmNr() == filmnr) {

                for (int j = i; j < antall - 1; j++) {
                    filmer[j] = filmer[j + 1];
                }
                filmer[antall - 1] = null;
                antall--;
                return true;
            }
        }
        return false;
    }

    @Override
    public Film[] sokTittel(String delstreng) {
        Film[] midlertidig = new Film[antall];  // Maks mulige treff
        int funnet = 0;

        for (int i = 0; i < antall; i++) {
            String tittel = filmer[i].getTittel().toLowerCase();
            String sokStreng = delstreng.toLowerCase();

            if (tittel.contains(sokStreng)) {
                midlertidig[funnet] = filmer[i];
                funnet++;
            }
        }

        // Returner array med riktig størrelse
        Film[] resultat = new Film[funnet];
        for (int i = 0; i < funnet; i++) {
            resultat[i] = midlertidig[i];
        }

        return resultat;
    }

    @Override
    public Film[] soekProdusent(String delstreng) {
        Film[] midlertidig = new Film[antall];  // Maks mulige treff
        int funnet = 0;

        for (int i = 0; i < antall; i++) {
            String produsent = filmer[i].getNavn().toLowerCase();
            String sokStreng = delstreng.toLowerCase();

            if (produsent.contains(sokStreng)) {
                midlertidig[funnet] = filmer[i];
                funnet++;
            }
        }

        // Returner array med riktig størrelse
        Film[] resultat = new Film[funnet];
        for (int i = 0; i < funnet; i++) {
            resultat[i] = midlertidig[i];
        }

        return resultat;
    }

    @Override
    public int antall(Film.Sjanger sjanger) {
        int funnet = 0;
        for (int i = 0; i < antall; i++) {
            if (filmer[i].getSjanger() == sjanger) {
                funnet++;
            }
        }
        return funnet;
    }

    @Override
    public int antall() {
        return this.antall;
    }

    //hjelpemetoder

}
