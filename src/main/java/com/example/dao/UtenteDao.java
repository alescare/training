package com.example.dao;


import com.example.entita.Utente;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UtenteDao {


    static {
        Utente admin = new Utente("admin", "admin", true);
        UtenteDao utenteDao = new UtenteDao();
        if (utenteDao.cercaUtentePerCredenziali("admin", "admin") == null) {
            utenteDao.salvaOAggiornaUtente(admin);
        }
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

    public Utente cercaUtentePerCredenziali(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utente where username = '" + username + "' and password = '" +
                    password + "'", Utente.class).getSingleResult();
        }
    }

    public List<Utente> listaUtenti() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utente", Utente.class).getResultList();
        }

    }

    public Utente cercaUtentePerId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Utente where id = '" + id + "'", Utente.class).getSingleResult();
        }
    }

    public void cancellaUtente(Utente utente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(utente);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
