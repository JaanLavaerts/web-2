package ui.controller;
import domain.db.ShowDB;
import domain.model.Show;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    private ShowDB showDB = new ShowDB();

    public Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = "home";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
        }
        String destination;
        switch (command) {
            case "voegtoe":
                destination = voegtoe(request, response);
                break;
            case "voegToeForm":
                destination = voegToeForm(request, response);
                break;
            case "verwijderConfirm":
                destination =  verwijderConfirm(request, response);
                break;
            case "verwijder":
                destination = verwijder(request, response);
                break;
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "zoek":
                destination = zoek(request, response);
                break;
            case "zoekNaam":
                destination = zoekNaam(request, response);
                break;
            case "update":
                destination = update(request);
                break;
            case "updatevalues":
                destination = updateValues(request, response);
                break;
            case "logboek":
                destination = logboek(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);

    }

    private String home(HttpServletRequest request, HttpServletResponse response){
        Show hoogsteScore = showDB.GetHoogsteScore();
        request.setAttribute("hoogsteScore", hoogsteScore);
        return"index.jsp";
    }

    private String voegToeForm(HttpServletRequest request, HttpServletResponse response){
        return "voeg-toe.jsp";
    }

    private String voegtoe(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<>();

        Show show = new Show();
        setNaam(show, request, errors);
        setScore(show, request, errors);
        setUitgekeken(show, request, errors);

        if (errors.size() == 0) {
            try {
                showDB.voegShowToe(show);
                return overzicht(request, response);
            }
            catch (IllegalArgumentException exc) {
                request.setAttribute("error", exc.getMessage());
                return "voeg-toe.jsp";
            }
        }
        else {
            request.setAttribute("errors", errors);
            return "voeg-toe.jsp";
        }
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response){
        Show hoogsteScore = showDB.GetHoogsteScore();
        Show laagsteScore = showDB.getLaagsteScore();
        request.setAttribute("hoogsteScore", hoogsteScore);
        request.setAttribute("laagsteScore", laagsteScore);
        request.setAttribute("shows", showDB.getShows());
        return "overzicht.jsp";
    }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        return "zoek.jsp";
    }

    private String zoekNaam(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String destination;

        String naam = request.getParameter("naam");  //cookie dat laatste zoekopdracht bijhoudt

        Cookie cookie = new Cookie("naam", URLEncoder.encode(naam, "UTF-8"));
        response.addCookie(cookie);

        if(naam == null){
            destination = "niet-gevonden.jsp";
        }else{
            Show show = showDB.find(naam);

            if(show == null){
                destination = "niet-gevonden.jsp";
            }else{
                logboekVoorSessies(request, show.getNaam());
                destination = "gevonden.jsp";
                request.setAttribute("show", show);
            }
        }
        request.getRequestDispatcher(destination);
        return destination;
    }

    private String update(HttpServletRequest request) {
        Show show = showDB.find(request.getParameter("naam"));
        String naam = request.getParameter("naam");
        String score = request.getParameter("score");
        String uitgekeken = request.getParameter("uitgekeken");
        request.setAttribute("show", show);
        request.setAttribute("naam", naam);
        request.setAttribute("score", score);
        request.setAttribute("uitgekeken", uitgekeken);
        return "update.jsp";
    }

    private String updateValues(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<>();
        Show show = showDB.find(request.getParameter("naam"));

        setNaam(show, request, errors);
        setScore(show, request, errors);
        setUitgekeken(show, request, errors);

        if (errors.size() == 0) {
            try {
                showDB.updateShow(show.getNaam(), Integer.toString(show.getScore()), show.getUitgekeken());
                return overzicht(request, response);
            } catch (IllegalArgumentException exc) {
                errors.add(exc.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "update.jsp";
    }

    private String logboek(HttpServletRequest request, HttpServletResponse response){
        return "logboek.jsp";
    }


    private void logboekVoorSessies(HttpServletRequest request, String search) {
        HttpSession session = request.getSession();

        if (session.getAttribute("shows") == null){
            ArrayList<String> logboek = new ArrayList<>();
            logboek.add(search);
            session.setAttribute("shows", logboek);
        }
        else{
            ArrayList<String> logboek = (ArrayList<String>)session.getAttribute("shows");
            logboek.add(search);
            session.setAttribute("shows", logboek);

        }
    }


    private String verwijder(HttpServletRequest request, HttpServletResponse response){
        String naam = request.getParameter("naam");
        showDB.verwijder(naam);
        return overzicht(request, response);
    }

    private String verwijderConfirm(HttpServletRequest request, HttpServletResponse response){
        return "verwijderConfirm.jsp";
    }


    private void setNaam(Show show, HttpServletRequest request, ArrayList<String> errors) {
        String naam = request.getParameter("naam");

        try {
            show.setNaam(naam);
            request.setAttribute("statusNaam", "has-success");
            request.setAttribute("naamVorigeWaarde", naam);
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
            request.setAttribute("statusNaam", "has-error");
        }
    }

    private void setScore(Show show, HttpServletRequest request, ArrayList<String> errors) {
        int score;
        if (request.getParameter("score").trim().isEmpty() || (request.getParameter("score") == null)) {
            score = -1;
        }
        else {
            score = Integer.parseInt(request.getParameter("score"));
        }

        try {
            show.setScore(score);
            request.setAttribute("statusScore", "has-success");
            request.setAttribute("scoreVorigeWaarde", score);
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
            request.setAttribute("statusScore", "has-error");
        }
    }

    private void setUitgekeken(Show show, HttpServletRequest request, ArrayList<String> errors) {
        String uitgekeken = request.getParameter("uitgekeken");

        try {
            show.setUitgekeken(uitgekeken);
            request.setAttribute("statusUitgekeken", "has-success");
            request.setAttribute("uitgekekenVorigeWaarde", uitgekeken);
        } catch (IllegalArgumentException ecx) {
            errors.add(ecx.getMessage());
            request.setAttribute("statusUitgekeken", "has-error");
        }
    }



}
