package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.UtenteDao;
import com.example.entità.Auto;
import com.example.entità.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;


@WebServlet(value = "/AggiungiCustomerServlet")
public class AggiungiCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AggiungiCustomerServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtenteDao utenteDao = new UtenteDao();
        Utente utente = new Utente();
        utente.setNome(request.getParameter("modello"));
        utente.setCognome(request.getParameter("costruttore"));
        utente.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
        try{
            utenteDao.salvaUtente(utente);
        } catch (Exception e) {
            utente = null;
        }
        request.setAttribute("utente", utente);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/aggiungiUtente.jsp");
        dispatcher.forward(request, response);

    }

}

