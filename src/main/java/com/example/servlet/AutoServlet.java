package com.example.servlet;


import com.example.dao.AutoDao;
import com.example.dao.PrenotazioneDao;
import com.example.entita.Auto;
import com.example.entita.Utente;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            case "pagina di prenotazione auto":
                prenotaAuto(request, response);
                break;
            case "Gestisci auto":
                gestisciAuto(request, response);
                break;
            default:
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String azione= request.getParameter("azione");
        switch (azione){
            case "Salva":
                salva(request, response);
                break;
            case "cerca auto disponibili":
                cerca(request, response);
                break;
            case "elimina auto":
                elimina(request, response);
                break;
            case "modifica auto":
                modifica(request, response);
                break;
            default:
        }

    }


    private void salva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //comprende sia la creazione di una nuova auto sia la modifica di una già esistente
        Auto auto = new Auto();
        auto.setModello(request.getParameter("modello"));
        auto.setCostruttore(request.getParameter("costruttore"));
        auto.setTipologia(request.getParameter("tipologia"));
        auto.setTarga(request.getParameter("targa"));
        auto.setAnnoImmatricolazione(Integer.parseInt(request.getParameter("annoImmatricolazione")));
        String id = request.getParameter("idAutoDaModificare");
        if (!("".equals(id))){
            auto.setId(Integer.parseInt(id));
        }
        new AutoDao().salvaOAggiornaAuto(auto);
        gestisciAuto(request, response);

    }

    private void modifica(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Auto auto = new AutoDao().getAutoPerId(Integer.parseInt(request.getParameter("idAuto")));
        request.setAttribute("autoDaModificare", auto);
        gestisciAuto(request, response);

    }

    private void gestisciAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        List<Auto> listaAuto = new AutoDao().getListaAuto();
        request.setAttribute("listaAuto", listaAuto);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/gestioneAuto.jsp");
        dispatcher.forward(request, response);
    }


    private void elimina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AutoDao autoDao = new AutoDao();
        Auto auto = autoDao.getAutoPerId(Integer.parseInt(request.getParameter("idAuto")));
        autoDao.cancellaAuto(auto);
        gestisciAuto(request, response);

    }

    private void cerca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("idAuto") == null) {
            LocalDate dataInizio = LocalDate.parse(request.getParameter("dataInizio"));
            LocalDate dataFine = LocalDate.parse(request.getParameter("dataFine"));
            List<Auto> listaAutoDisponibili = new ArrayList<>();

            try {
                listaAutoDisponibili = new AutoDao().listaAutoDisponibiliNelPeriodo(dataInizio, dataFine);
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getSession().setAttribute("dataInizio", dataInizio);
            request.getSession().setAttribute("dataFine", dataFine);
            request.setAttribute("listaAutoDisponibili", listaAutoDisponibili);
            prenotaAuto(request, response);
        }
    }

    private void prenotaAuto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Utente utente = (Utente) request.getSession().getAttribute("utenteLoggato");
        String jsp = "";
        if (new PrenotazioneDao().prenotazioneInCorsoUtente(utente)) {
            request.setAttribute("prenotazioneInCorso", "Hai già una prenotazione in corso");
            jsp = "/customerHome.jsp";
        } else {
            jsp = "/prenotaAuto.jsp";
        }
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(jsp);
        dispatcher.forward(request, response);

    }

}

