package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.entità.Auto;
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


@WebServlet(value = "/PrenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PrenotazioneServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione= request.getParameter("azione");
        switch (azione){
            case "Approva prenotazioni" :
                approvaPrenotazioni(request, response);
                break;
            case "Approva" :
                approva(request, response);
                break;
            case "Cancella":
                cancella(request, response);
                break;
            case "Cancella prenotazioni":
                cancellaPrenotazioni(request, response);
                break;
            case  "Prenota" :
                prenota(request, response);
                break;
            default:
        }
    }

    private void cancellaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioni();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/cancellaPrenotazioni.jsp");
        dispatcher.forward(request, response);

    }

    private void approvaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioniDaApprovare();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/approvaPrenotazioni.jsp");
        dispatcher.forward(request, response);

    }

    private void approva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();

        Prenotazione prenotazione = prenotazioneDao.getPrenotazione(Integer.parseInt(request.getParameter("idPrenotazione")));

        prenotazione.setApprovata(true);

        prenotazioneDao.salvaOAggiornaPrenotazione(prenotazione);

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioniDaApprovare();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/approvaPrenotazioni.jsp");
        dispatcher.forward(request, response);
    }

    private void cancella(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();

        Prenotazione prenotazione = prenotazioneDao.getPrenotazione(Integer.parseInt(request.getParameter("prenotazione")));

        prenotazioneDao.cancellaPrenotazione(prenotazione);

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioni();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/cancellaPrenotazioni.jsp");
        dispatcher.forward(request, response);

    }

    private void prenota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        AutoDao autoDao = new AutoDao();
        Auto auto = autoDao.getAutoPerId(Integer.parseInt(request.getParameter("idAuto")));
        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAuto(auto);
        prenotazione.setUtente(utente);
        prenotazione.setDataInizio((LocalDate) request.getSession().getAttribute("dataInizio"));
        prenotazione.setDataFine((LocalDate) request.getSession().getAttribute("dataFine"));
        prenotazioneDao.salvaOAggiornaPrenotazione(prenotazione);
        request.getSession().removeAttribute("dataInizio");
        request.getSession().removeAttribute("dataFine");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/profilo.jsp");
        dispatcher.forward(request, response);

    }

}

