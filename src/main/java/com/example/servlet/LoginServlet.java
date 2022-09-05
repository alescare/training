package com.example.servlet;


import com.example.dao.UtenteDao;
import com.example.entità.Utente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;



@WebServlet(value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UtenteDao utenteDao = new UtenteDao();

        Utente utente = utenteDao.cercaUtentePerNomeCognome(request.getParameter("nome"), request.getParameter("cognome"));

        if (utente == null) {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/utenteInesistente.jsp");
            dispatcher.forward(request, response);
        } else if (utente.isAdmin()) {
            request.getSession().setAttribute("utenteLoggato", utente);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/superUserHome.jsp");
            dispatcher.forward(request, response);
        } else if (!utente.isAdmin()) {
            request.getSession().setAttribute("utenteLoggato", utente);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/customerHome.jsp");
            dispatcher.forward(request, response);
        }

    }

}

