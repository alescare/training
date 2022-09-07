package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.entità.Auto;
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


@WebServlet(value = "/AutoServlet")
public class AutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AutoServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione= request.getParameter("azione");
        switch (azione){
            case "Aggiungi":
                aggiungi(request, response);
                break;
            case "Cerca":
                cerca(request, response);
                break;
            case "Prenota auto":
                prenotaAuto(request, response);
                break;
            case "Aggiungi auto":
                aggiungiAuto(request, response);
                break;
            default:
        }

    }

    private void aggiungiAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        List<Auto> listaAuto = autoDao.getListaAuto();
        request.setAttribute("listaAuto", listaAuto);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiAuto.jsp");
        dispatcher.forward(request, response);
    }

    private void aggiungi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        Auto auto = new Auto();
        auto.setModello(request.getParameter("modello"));
        auto.setCostruttore(request.getParameter("costruttore"));
        auto.setTarga(request.getParameter("targa"));
        auto.setTipologia(request.getParameter("tipologia"));
        auto.setAnnoImmatricolazione(Integer.parseInt(request.getParameter("annoImmatricolazione")));
        try{
            autoDao.salvaOAggiornaAuto(auto);
        } catch (Exception e) {
            auto = null;
        }
        List<Auto> listaAuto = autoDao.getListaAuto();
        request.setAttribute("listaAuto", listaAuto);
        request.setAttribute("auto", auto);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiAuto.jsp");
        dispatcher.forward(request, response);

    }

    private void cerca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        if (request.getParameter("idAuto") == null) {
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
        }
    }

    private void prenotaAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/prenotaAuto.jsp");
        dispatcher.forward(request, response);

    }

}

