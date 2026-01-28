package data102.filmarkiv.impl;

public class Film {
    private int filmNr;
    private String navn;
    private String tittel;
    private int lansering;
    private Sjanger sjanger;
    private String filmselskap;

    public enum Sjanger {
        ACTION, KOMEDIE, DRAMA, HORROR, SCIFI, ROMANTIKK, THRILLER;
        public static Sjanger finnSjanger(String navn){
            for(Sjanger s : Sjanger.values()){
                if(s.toString().equals(navn.toUpperCase())){
                    return s;
                }
            }
            return null;
        }

        public static String sjangerToString(){
            StringBuilder sb = new StringBuilder();
            for (Sjanger s : Sjanger.values()) {
                sb.append(s.name()).append(", ");
            }
            // Remove last comma and space
            if(sb.length() > 2){
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }
    }

    public Film(){

    }

    public Film(int filmNr, String navn, String tittel, int lansering, Sjanger sjanger, String filmselskap){
        this.filmNr = filmNr;
        this.navn = navn;
        this.tittel = tittel;
        this.lansering = lansering;
        this.sjanger = sjanger;
        this.filmselskap = filmselskap;
    }

    // Getters
    public int getFilmNr() {
        return filmNr;
    }

    public String getNavn() {
        return navn;
    }

    public String getTittel() {
        return tittel;
    }

    public int getLansering() {
        return lansering;
    }

    public Sjanger getSjanger() {
        return sjanger;
    }

    public String getFilmselskap() {
        return filmselskap;
    }


    // Setters
    public void setFilmNr(int filmNr) {
        this.filmNr = filmNr;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public void setLansering(int lansering) {
        this.lansering = lansering;
    }

    public void setSjanger(Sjanger sjanger) {
        this.sjanger = sjanger;
    }

    public void setFilmselskap(String filmselskap) {
        this.filmselskap = filmselskap;
    }

    //Overkjøre metoder
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        if(this.filmNr == ((Film) obj).filmNr){
            return true;
        }
        return false;
    };
    @Override
    public int hashCode(){
        return Integer.hashCode(filmNr);
    }
}