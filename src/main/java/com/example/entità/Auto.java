package com.example.entità;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "veicolo")
public class Auto {
    @Id
    @Column(name = "targa")
    private String targa;
    @Column(name = "costruttore")
    private String costruttore;
    @Column(name = "modello")
    private String modello;
    @Column(name = "anno_immatricolazione")
    private int annoImmatricolazione;
    @Column(name = "tipologia")
    private String tipologia;

    @OneToMany(mappedBy="veicolo")
    private Set<Prenotazione> prenotazioni;

    public Auto() {
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getCostruttore() {
        return costruttore;
    }

    public void setCostruttore(String costruttore) {
        this.costruttore = costruttore;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(int annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }


}
