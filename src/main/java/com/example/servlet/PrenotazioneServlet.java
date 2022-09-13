package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.entita.Auto;
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


@WebServlet(value = "/PrenotazioneServlet")
public class PrenotazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PrenotazioneServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione = request.getParameter("azione");
        switch (azione) {
            case "prenotazioni da approvare":
                approvaPrenotazioni(request, response);
                break;
            case "prenotazioni da cancellare":
                cancellaPrenotazioni(request, response);
                break;
            default:
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione = request.getParameter("azione");
        switch (azione) {
            case "approva prenotazione":
                approva(request, response);
                break;
            case "cancella prenotazione":
                cancella(request, response);
                break;
            case "prenota auto":
                prenota(request, response);
                break;
            default:
        }
    }


    private void cancellaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Prenotazione> listaPrenotazioni = new PrenotazioneDao().getPrenotazioni();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/cancellaPrenotazioni.jsp");
        dispatcher.forward(request, response);

    }

    private void approvaPrenotazioni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Prenotazione> listaPrenotazioni = new PrenotazioneDao().getPrenotazioniDaApprovare();
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
        cancellaPrenotazioni(request, response);

    }

    private void prenota(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        Auto auto = new AutoDao().getAutoPerId(Integer.parseInt(request.getParameter("idAuto")));
        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setAuto(auto);
        prenotazione.setUtente(utente);
        prenotazione.setDataInizio((LocalDate) request.getSession().getAttribute("dataInizio"));
        prenotazione.setDataFine((LocalDate) request.getSession().getAttribute("dataFine"));
        prenotazioneDao.salvaOAggiornaPrenotazione(prenotazione);
        request.getSession().removeAttribute("dataInizio");
        request.getSession().removeAttribute("dataFine");
        request.setAttribute("listaPrenotazioni", prenotazioneDao.getPrenotazioniUtente(utente));
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/profilo.jsp");
        dispatcher.forward(request, response);

    }

}

