package com.example.treeniohjelma.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Treeni {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String treeniMuoto;
	private String treeniKesto;
	private String paikka;
	private LocalDate paivamaara;
	private LocalTime kellonaika;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "viikonpaivaid")
	private Viikonpaiva viikonpaiva;

	public Treeni() {

	}

	public Treeni(String treeniMuoto, String treeniKesto, String paikka, LocalDate paivamaara, LocalTime kellonaika,
			Viikonpaiva viikonpaiva) {
		super();
		this.treeniMuoto = treeniMuoto;
		this.treeniKesto = treeniKesto;
		this.paikka = paikka;
		this.paivamaara = paivamaara;
		this.kellonaika = kellonaika;
		this.viikonpaiva = viikonpaiva;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTreeniMuoto() {
		return treeniMuoto;
	}

	public void setTreeniMuoto(String treeniMuoto) {
		this.treeniMuoto = treeniMuoto;
	}

	public String getTreeniKesto() {
		return treeniKesto;
	}

	public void setTreeniKesto(String treeniKesto) {
		this.treeniKesto = treeniKesto;
	}

	public String getPaikka() {
		return paikka;
	}

	public void setPaikka(String paikka) {
		this.paikka = paikka;
	}

	public LocalDate getPaivamaara() {
		return paivamaara;
	}

	public void setPaivamaara(LocalDate paivamaara) {
		this.paivamaara = paivamaara;
	}

	public LocalTime getKellonaika() {
		return kellonaika;
	}

	public void setKellonaika(LocalTime kellonaika) {
		this.kellonaika = kellonaika;
	}

	public Viikonpaiva getViikonpaiva() {
		return viikonpaiva;
	}

	public void setViikonpaiva(Viikonpaiva viikonpaiva) {
		this.viikonpaiva = viikonpaiva;
	}

	@Override
	public String toString() {
		if (this.viikonpaiva != null) {
			return "Treeni [id=" + id + ", treeniMuoto=" + treeniMuoto + ", treeniKesto=" + treeniKesto + ", paikka="
					+ paikka + ", paivamaara=" + paivamaara + ", kellonaika=" + kellonaika + ", viikonpaiva="
					+ this.getViikonpaiva() + "]";
		} else {
			return "Treeni [id=" + id + ", treeniMuoto=" + treeniMuoto + ", treeniKesto=" + treeniKesto + ", paikka="
					+ paikka + ", paivamaara=" + paivamaara + ", kellonaika=" + kellonaika + "]";
		}
	}
}