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
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


@WebServlet(value = "/ApprovaPrenotazioniServlet")
public class ApprovaPrenotazioniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ApprovaPrenotazioniServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        if(request.getParameter("idPrenotazione") != null) {
            Prenotazione prenotazione = prenotazioneDao.getPrenotazione(Integer.parseInt(request.getParameter("prenotazione")));
            prenotazioneDao.approvaPrenotazione(prenotazione);
        }

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioniDaApprovare();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiUtente.jsp");
        dispatcher.forward(request, response);

    }

}

