package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.entità.Auto;
import com.example.entità.Prenotazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(value = "/ListaAutoServlet")
public class ListaAutoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListaAutoServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();

        List<Auto> listaAuto = autoDao.getListaAuto();

        request.setAttribute("listaAuto", listaAuto);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/listaAuto.jsp");
        dispatcher.forward(request, response);

    }

}

