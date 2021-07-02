package domain.db;
import domain.model.Show;
import java.util.ArrayList;


public class ShowDB {

    ArrayList<Show> shows;

    public ShowDB() {
        shows = new ArrayList<>();
        this.voegShowToe(new Show("Mr. Robot", 8, "Ja"));
        this.voegShowToe(new Show("LOST", 10, "Nee"));
        this.voegShowToe(new Show("The 100", 0, "Ja"));
        this.voegShowToe(new Show("Trailer Park Boys", 7, "Nee"));
        this.voegShowToe(new Show("Ozark", 9, "Nee"));
    }

    public ArrayList<Show> getShows() {
        if (shows.size() == 0) {
            return null;
        }
        return this.shows;
    }

    public void voegShowToe(Show show) {
        if (show == null) {
            throw new IllegalArgumentException("Geen geldige show.");
        }
        for (Show s : shows) {
            if (s.getNaam().equalsIgnoreCase(show.getNaam()))
                throw new IllegalArgumentException("Deze show is al toegevoegd.");
        }
        shows.add(show);
    }


    public Show find(String naam) {
        for (Show show : shows) {
            if (show.getNaam().equals(naam))
                return show;
        }
        return null;
    }

    public void verwijder(String naam) {
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Geen geldige naam.");
        }
        shows.remove(this.find(naam));
    }

    public Show GetHoogsteScore() {
        if (shows.size() == 0) {
            return null;
        }
        Show hoogste = shows.get(0);
        for (Show show : shows) {
            if (show.getScore() > hoogste.getScore()) {
                hoogste = show;
            }
        }
        return hoogste;
    }

    public Show getLaagsteScore() {
        if (shows.size() == 0) {
            return null;
        }
        Show laagste = shows.get(0);
        for (Show show : shows) {
            if (show.getScore() < laagste.getScore()) {
                laagste = show;
            }
        }
        return laagste;
   }



    public void updateShow(String naam, String score, String uitgekeken) {
        Show show = this.find(naam);
        show.setScore(Integer.parseInt(score));
        show.setUitgekeken(uitgekeken);
    }


}
