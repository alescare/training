package com.example.dao;


import com.example.entità.Utente;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class UtenteDao {
    private static Utente utente = new Utente("admin", "admin", null, true);

    static {
        UtenteDao utenteDao = new UtenteDao();
        utenteDao.salvaOAggiornaUtente(utente);
    }
    public void salvaOAggiornaUtente(Utente utente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Utente cercaUtentePerNomeCognome(String nome, String cognome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utente where nome = '" + nome +"' and cognome = '" +
                                            cognome + "'", Utente.class).getSingleResult();
        }
    }
}
