package com.example.treeniohjelma.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Viikonpaiva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long viikonpaivaid;
	private String viikonpaiva;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "viikonpaiva")
	private List<Treeni> treenit;

	public Viikonpaiva() {

	}

	public Viikonpaiva(String viikonpaiva) {
		this.viikonpaiva = viikonpaiva;
	}

	public long getViikonpaivaid() {
		return viikonpaivaid;
	}

	public void setViikonpaivaid(long viikonpaivaid) {
		this.viikonpaivaid = viikonpaivaid;
	}

	public String getViikonpaiva() {
		return viikonpaiva;
	}

	public void setViikonpaiva(String viikonpaiva) {
		this.viikonpaiva = viikonpaiva;
	}

	@Override
	public String toString() {
		return "Viikonpaiva [viikonpaivaid=" + viikonpaivaid + ", viikonpaiva=" + viikonpaiva + "]";
	}
}
