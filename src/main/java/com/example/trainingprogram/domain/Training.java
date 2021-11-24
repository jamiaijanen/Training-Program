package com.example.trainingprogram.domain;

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
public class Training {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String trainingName;
	private String length;
	private String place;
	private LocalDate date;
	private LocalTime time;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "dayOfWeekId")
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "usernameId")
	private User user;

	public Training() {

	}

	public Training(String trainingName, String length, String place, LocalDate date, LocalTime time) {
		super();
		this.trainingName = trainingName;
		this.length = length;
		this.place = place;
		this.date = date;
		this.time = time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		if (this.dayOfWeek != null) {
			return "Treeni [id=" + id + ", trainingName=" + trainingName + ", length=" + length + ", place=" + place
					+ ", date=" + date + ", time=" + time + ", dayOfWeek=" + this.getDayOfWeek() + "]";
		} else {
			return "Training [id=" + id + ", trainingName=" + trainingName + ", length=" + length + ", place=" + place
					+ ", date=" + date + ", time=" + time + "]";
		}
	}
}