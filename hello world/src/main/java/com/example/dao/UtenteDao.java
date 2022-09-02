package com.example.dao;


import com.example.entità.Utente;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class UtenteDao {
    private static Utente utente = new Utente("admin", "admin", LocalDate.now(), true);

    static {
        UtenteDao utenteDao = new UtenteDao();
        utenteDao.salvaUtente(utente);
    }
    public void salvaUtente(Utente utente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(utente);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Utente loginUtente(String nome, String cognome) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utente where nome = '" + nome +"' and cognome = '" +
                                            cognome + "'", Utente.class).getSingleResult();
        }
    }
}
