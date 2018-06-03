package data;

import java.util.Calendar;

public class Rezervation {
	
	private int rezervation_id;
	private int hall_id;
	private String nume;
	private String date;
	private String start_hour;
	private String end_hour;
	public int getRezervation_id() {
		return rezervation_id;
	}
	public void setRezervation_id(int rezervation_id) {
		this.rezervation_id = rezervation_id;
	}
	public int getHall_id() {
		return hall_id;
	}
	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStart_hour() {
		return start_hour;
	}
	public void setStart_hour(String start_hour) {
		this.start_hour = start_hour;
	}
	public String getEnd_hour() {
		return end_hour;
	}
	public void setEnd_hour(String end_hour) {
		this.end_hour = end_hour;
	}
	public Rezervation(int rezervation_id, int hall_id, String nume, String date, String start_hour, String end_hour) {
		this.rezervation_id = rezervation_id;
		this.hall_id = hall_id;
		this.nume = nume;
		this.date = date;
		this.start_hour = start_hour;
		this.end_hour = end_hour;
	}
	
}
