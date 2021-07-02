package domain.model;
public class Show {



    //variables
    private String naam;
    private int score;
    private String uitgekeken;


    //constructors
    public Show(String naam, int score, String uitgekeken) {
        setNaam(naam);
        setScore(score);
        setUitgekeken(uitgekeken);
    }

    public Show() {
    }

    public Show(String naam) {
        this.setNaam(naam);
    }

    public Show(int score, String uitgekeken) {
        this.setScore(score);
        this.setUitgekeken(uitgekeken);
    }

    //methods
    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Vul een geldige naam in.");
        }
        this.naam = naam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score moet tussen 0 en 10 liggen.");
        }
        this.score = score;
    }

    public String getUitgekeken() {
        return uitgekeken;
    }

    public void setUitgekeken(String uitgekeken) {
        if (uitgekeken == null || uitgekeken.trim().isEmpty()) {
            throw new IllegalArgumentException("Specifieer of de show is uitgekeken of niet.");
        }
        this.uitgekeken = uitgekeken;
    }


}
