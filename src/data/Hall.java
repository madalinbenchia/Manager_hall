package data;

public class Hall {
	private int hall_id;
	private int nr_persoane;
	private String proiector;
	private String nume_sala;
	
	public int getHall_id() {
		return hall_id;
	}
	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}
	public int getNr_persoane() {
		return nr_persoane;
	}
	public void setNr_persoane(int nr_persoane) {
		this.nr_persoane = nr_persoane;
	}
	public String getProiector() {
		return proiector;
	}
	public void setProiector(String proiector) {
		this.proiector = proiector;
	}
	
	public String getNume_sala() {
		return nume_sala;
	}
	public void setNume_sala(String nume) {
		this.nume_sala = nume;
	}
	
	public Hall(int ID, int nr, String proiector) {
		this.hall_id = ID;
		this.nr_persoane = nr;
		this.proiector = proiector;
	}
	
}
