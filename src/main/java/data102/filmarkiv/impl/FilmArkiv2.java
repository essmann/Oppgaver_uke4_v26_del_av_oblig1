package data102.filmarkiv.impl;

import data102.filmarkiv.adt.FilmarkivADT;

public class FilmArkiv2 implements FilmarkivADT {

    private int antall;
    private LinearNode<Film> start;


    public FilmArkiv2() {
        this.start = null;
    }

    @Override
    public Film finnFilm(int nr) {


        var currentNode = start;
        if (start == null) {
            return null;
        }
        if (start.data.getFilmNr() == nr) {
            return start.data;
        }
        while (currentNode.neste != null) {
            if (currentNode.neste.data.getFilmNr() == nr) {
                return currentNode.neste.data;
            }
            currentNode = currentNode.neste;
        }

        return null;
    }

    ;

    private LinearNode<Film> getNodeAtIndex(int index) {
        if (index < 0 || index >= antall) {
            return null;
        }

        LinearNode<Film> currentNode = start;
        int i = 0;

        while (i < index) {
            currentNode = currentNode.neste;
            i++;
        }

        return currentNode;
    }

    @Override
    public boolean leggTilFilm(Film nyFilm) {
        if (finnFilm(nyFilm.getFilmNr()) != null) return false;

        var newNode = new LinearNode(nyFilm, start);
        //Peker nå på startNoden

        //Vi må nå gjøre newNode om til den nye startNoden

        this.start = newNode;

        antall++;

        return true;

    }

    ;;

    @Override
    public boolean slettFilm(int filmnr) {
        LinearNode<Film> current = start;
        if (start == null) return false;

        //check first node
        if (start.data.getFilmNr() == filmnr) {
            LinearNode<Film> toDelete = start;
            start = start.neste; // move head
            toDelete.neste = null;
            antall--;
            return true;
        }
        while (current.neste != null) {
            if (current.neste.data.getFilmNr() == filmnr) {
                LinearNode<Film> toDelete = current.neste;
                current.neste = toDelete.neste;
                toDelete.neste = null;
                antall--;
                return true;
            }
            current = current.neste;
        }
        return false;
    }

    @Override
    public Film[] sokTittel(String delstreng) {
        Film[] midlertidig = new Film[antall];  // Maks mulige treff
        int funnet = 0;

        for (int i = 0; i < antall; i++) {
            var node = getNodeAtIndex(i);
            if(node == null){break;}

            String tittel = node.data.getTittel().toLowerCase();
            String sokStreng = delstreng.toLowerCase();

            if (tittel.contains(sokStreng)) {
                midlertidig[funnet] = node.data;
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
            var node = getNodeAtIndex(i);
            if(node == null){break;}

            String produsent = node.data.getNavn().toLowerCase();
            String sokStreng = delstreng.toLowerCase();

            if (produsent.contains(sokStreng)) {
                midlertidig[funnet] = node.data;
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
            var node = getNodeAtIndex(i);
            if(node == null){break;}
            if (node.data.getSjanger() == sjanger) {
                funnet++;
            }
        }
        return funnet;
    }

    @Override
    public int antall() {
        return this.antall;
    }
}
