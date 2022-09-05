package com.example.servlet;


import com.example.dao.PrenotazioneDao;
import com.example.entità.Prenotazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(value = "/ApprovaPrenotazioniServlet")
public class CancellaPrenotazioniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CancellaPrenotazioniServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        if(request.getParameter("idPrenotazione") != null) {
            Prenotazione prenotazione = prenotazioneDao.getPrenotazione(Integer.parseInt(request.getParameter("prenotazione")));
            prenotazioneDao.cancellaPrenotazione(prenotazione);
        }

        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getPrenotazioni();

        request.setAttribute("listaPrenotazioni", listaPrenotazioni);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiUtente.jsp");
        dispatcher.forward(request, response);

    }

}

