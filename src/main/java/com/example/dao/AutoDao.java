package com.example.dao;

import com.example.entità.Auto;
import com.example.entità.Prenotazione;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class AutoDao {

    public void salvaAuto(Auto auto) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.saveOrUpdate(auto);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Auto> getListaAuto() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Auto", Auto.class).list();
        }
    }

    public Auto getAutoPerId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Auto where id = '" + id + "'", Auto.class).getSingleResult();
        }
    }

    public List<Auto> listaAutoDisponibiliNelPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        List<Auto> listaAutodisponibili = getListaAuto();
        PrenotazioneDao prenotazioneDao = new PrenotazioneDao();
        List<Prenotazione> listaPrenotazioni = prenotazioneDao.getListaPrenotazioniNelPeriodo(dataInizio, dataFine);
        for (Prenotazione prenotazione : listaPrenotazioni) {
            listaAutodisponibili.remove(prenotazione.getAuto());
        }
        return listaAutodisponibili;
    }
}
