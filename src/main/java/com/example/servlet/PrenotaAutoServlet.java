package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.dao.UtenteDao;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/PrenotaAutoServlet")
public class PrenotaAutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PrenotaAutoServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        if(request.getParameter("idAuto") == null) {
            LocalDate dataInizio = LocalDate.parse(request.getParameter("dataInizio"));
            LocalDate dataFine = LocalDate.parse(request.getParameter("dataFine"));
            List<Auto> listaAutoDisponibili = new ArrayList<>();

            try {
                listaAutoDisponibili = autoDao.listaAutoDisponibiliNelPeriodo(dataInizio, dataFine);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("dataInizio", dataInizio);
            request.getSession().setAttribute("dataFine", dataFine);
            request.setAttribute("listaAutoDisponibili", listaAutoDisponibili);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/prenotaAuto.jsp");
            dispatcher.forward(request, response);
        } else {
            PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
            Auto auto = autoDao.getAutoPerId(Integer.parseInt(request.getParameter("idAuto")));
            Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setAuto(auto);
            prenotazione.setUtente(utente);
            prenotazione.setDataInizio((LocalDate) request.getSession().getAttribute("dataInizio"));
            prenotazione.setDataFine((LocalDate) request.getSession().getAttribute("dataFine"));
            prenotazioneDao.salvaPrenotazione(prenotazione);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/customerHome.jsp");
            dispatcher.forward(request, response);
        }

    }

}

