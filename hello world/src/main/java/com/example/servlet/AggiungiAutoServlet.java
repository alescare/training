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


@WebServlet(value = "/AggiungiAutoServlet")
public class AggiungiAutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AggiungiAutoServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        Auto auto = new Auto();
        auto.setModello(request.getParameter("modello"));
        auto.setCostruttore(request.getParameter("costruttore"));
        auto.setTarga(request.getParameter("targa"));
        auto.setTipologia(request.getParameter("tipologia"));
        auto.setAnnoImmatricolazione(Integer.parseInt(request.getParameter("annoImmatricolazione")));
        try{
            autoDao.salvaAuto(auto);
        } catch (Exception e) {
            auto = null;
        }
        request.setAttribute("auto", auto);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiAuto.jsp");
        dispatcher.forward(request, response);

    }

}

