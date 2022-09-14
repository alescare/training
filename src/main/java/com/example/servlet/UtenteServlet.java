package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.dao.UtenteDao;
import com.example.entita.Prenotazione;
import com.example.entita.Utente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PrenotazioneDao prenotazioneDao = PrenotazioneDao.getInstance();
    private UtenteDao utenteDao = UtenteDao.getInstance();

    public UtenteServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione = request.getParameter("azione");
        switch (azione) {
            case "Gestisci utenti":
                gestisciUtenti(request, response);
                break;
            case "Visualizza profilo":
                visualizzaProfilo(request, response);
                break;
            case "Home":
                home(request, response);
                break;
            case "Esci":
                esci(request, response);
                break;
            default:
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione = request.getParameter("azione");
        switch (azione) {
            case "modifica credenziali":
                modificaCredenziali(request, response);
                break;
            case "aggiungi utente":
                aggiungi(request, response);
                break;
            case "Entra":
                entra(request, response);
                break;
            case "cancella utente":
                cancella(request, response);
                break;
            default:
        }
    }

    private void gestisciUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("listaUtenti", utenteDao.listaUtenti());
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/gestioneUtenti.jsp");
        dispatcher.forward(request, response);

    }

    private void aggiungi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = new Utente();
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setUsername(request.getParameter("username"));
        utente.setPassword(request.getParameter("password"));
        utente.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
        try {
            utenteDao.salvaOAggiornaUtente(utente);
        } catch (Exception e) {
            utente = null;
        }
        request.setAttribute("utente", utente);
        gestisciUtenti(request, response);

    }

    private void entra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = new Utente();
        String jsp = "";
        try {
            utente = utenteDao.cercaUtentePerCredenziali(request.getParameter("username"), request.getParameter("password"));
        } catch (Exception e) {
            jsp = "/index.jsp";
            request.setAttribute("loginFallito", "Accesso non riuscito, riprova");
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher(jsp);
            dispatcher.forward(request, response);
        }

        request.getSession().setAttribute("utenteLoggato", utente);
        if (utente.isAdmin()) {
            jsp = "/superUserHome.jsp";
        } else {
            jsp = "/customerHome.jsp";
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    private void visualizzaProfilo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioniUtente(utente);
        request.setAttribute("listaPrenotazioni", listaPrenotazioni);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/profilo.jsp");
        dispatcher.forward(request, response);

    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utenteLoggato = (Utente) request.getSession().getAttribute("utenteLoggato");
        if (utenteLoggato.isAdmin()) {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/superUserHome.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/customerHome.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void esci(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().invalidate();
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }

    private void modificaCredenziali(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        if (!(password.equals(utente.getPassword()))) {
            utente.setPassword(password);
            utenteDao.salvaOAggiornaUtente(utente);
        }
        if (!(username.equals(utente.getUsername()))) {
            utente.setUsername(request.getParameter("username"));
            utenteDao.salvaOAggiornaUtente(utente);
        }
        visualizzaProfilo(request, response);

    }

    private void cancella(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = utenteDao.cercaUtentePerId(Integer.parseInt(request.getParameter("idUtente")));
        utenteDao.cancellaUtente(utente);
        gestisciUtenti(request, response);
    }

}

