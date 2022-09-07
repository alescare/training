package com.example.dao;

import com.example.entità.Prenotazione;
import com.example.entità.Utente;
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
            return session.createQuery("from Prenotazione", Prenotazione.class).getSingleResult();
        }
    }

    public List<Prenotazione> getPrenotazioniDaApprovare() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where approvata = '" + false +"'", Prenotazione.class).list();
        }
    }

    public List<Prenotazione> getPrenotazioniUtente(Utente utente) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where utente = '" + utente.getId() + "'", Prenotazione.class).list();
        }
    }

    public void cancellaPrenotazione(Prenotazione prenotazione){
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

    public List<Prenotazione> getListaPrenotazioniNelPeriodo(LocalDate dataInizio, LocalDate dataFine) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Prenotazione where not (data_inizio between '" + dataInizio + "' and '" + dataFine + "')" +
                    " and not (data_fine between '" + dataInizio + "' and '" + dataFine + "')", Prenotazione.class).list();
        }
    }
}
