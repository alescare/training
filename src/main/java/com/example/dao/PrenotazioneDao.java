package com.example.dao;

import com.example.entita.Prenotazione;
import com.example.entita.Utente;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class PrenotazioneDao {

    public void salvaOAggiornaPrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(prenotazione);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Prenotazione> getPrenotazioni() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione", Prenotazione.class).list();
        }
    }

    public Prenotazione getPrenotazione(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where id = '" + id + "'", Prenotazione.class).getSingleResult();
        }
    }

    public List<Prenotazione> getPrenotazioniDaApprovare() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where approvata = '" + false + "'", Prenotazione.class).list();
        }
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente utente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where utente = '" + utente.getId() + "'", Prenotazione.class).list();
        }
    }

    public boolean prenotazioneInCorsoUtente(Utente utente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (session.createQuery("from Prenotazione where utente = '" + utente.getId() + "'" +
                    " and dataFine >= '" + LocalDate.now() + "'", Prenotazione.class).getSingleResult() != null);
        }
    }

    public void cancellaPrenotazione(Prenotazione prenotazione) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(prenotazione);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Prenotazione> getListaPrenotazioniNelPeriodo(LocalDate dataInizioTemp, LocalDate dataFineTemp) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where ( dataInizio >= '" + dataInizioTemp + "' and dataFine <= '" + dataFineTemp + "' )" +
                    " or (dataInizio between '" + dataInizioTemp + "' and '" + dataFineTemp + "')" +
                    " or (dataFine between '" + dataInizioTemp + "' and '" + dataFineTemp + "')", Prenotazione.class).list();
        }
    }
}
