package com.example.trainingprogram.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DayOfWeek {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long dayOfWeekId;
	private String dayOfWeek;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dayOfWeek")
	private List<Training> trainings;

	public DayOfWeek() {

	}

	public DayOfWeek(String dayOfWeek) {
		super();
		this.dayOfWeek = dayOfWeek;
	}

	public long getDayOfWeekId() {
		return dayOfWeekId;
	}

	public void setDayOfWeekId(long dayOfWeekId) {
		this.dayOfWeekId = dayOfWeekId;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@Override
	public String toString() {
		return "DayOfWeek [dayOfWeek=" + dayOfWeek + ", training=" + trainings + "]";
	}
}
