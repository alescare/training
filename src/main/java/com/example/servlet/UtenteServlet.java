package com.example.servlet;


import com.example.dao.PrenotazioneDao;
import com.example.dao.UtenteDao;
import com.example.entità.Prenotazione;
import com.example.entità.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(value = "/UtenteServlet")
public class UtenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UtenteServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione= request.getParameter("azione");
        switch (azione){
            case "Aggiungi" :
                aggiungi(request, response);
                break;
            case "Aggiungi customer" :
                aggiungiCustomer(request, response);
                break;
            case "Entra" :
                entra(request, response);
                break;
            case "Visualizza profilo" :
                visualizzaProfilo(request, response);
                break;
            case "Home" :
                home(request, response);
                break;
            case "Esci" :
                esci(request, response);
                break;
            default:
        }

    }

    private void aggiungiCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiCustomer.jsp");
        dispatcher.forward(request, response);

    }

    private void aggiungi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtenteDao utenteDao = new UtenteDao();
        Utente utente = new Utente();
        utente.setNome(request.getParameter("nome"));
        utente.setCognome(request.getParameter("cognome"));
        utente.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
        try{
            utenteDao.salvaOAggiornaUtente(utente);
        } catch (Exception e) {
            utente = null;
        }
        request.setAttribute("utente", utente);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiUtente.jsp");
        dispatcher.forward(request, response);

    }

    private void entra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtenteDao utenteDao = new UtenteDao();

        Utente utente = utenteDao.cercaUtentePerNomeCognome(request.getParameter("nome"), request.getParameter("cognome"));

        String jsp = "";

        if (utente == null) {
            jsp = "/loginFallito.jsp";
        } else {
            request.getSession().setAttribute("utenteLoggato", utente);
            if (utente.isAdmin()) {
                jsp = "/superUserHome.jsp";
            } else {
                jsp = "/customerHome.jsp";
            }
        }
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);
    }

    private void visualizzaProfilo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioniUtente(utente);
        request.setAttribute("listaPrenotazioni", listaPrenotazioni);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/profilo.jsp");
        dispatcher.forward(request, response);

    }

    private void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Utente utenteLoggato = (Utente) request.getSession().getAttribute("utenteLoggato");
        if(utenteLoggato.isAdmin()) {
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

}

