package data;

public class Employee {
	
	private int employee_id;
	private String nume;
	private String prenume;
	private String user;
	private String password;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public Employee(int ID,String user, String password, String nume, String prenume)
	{
		this.employee_id = ID;
		this.user = user;
		this.password = password;
		this.nume = nume;
		this.prenume = prenume;
	}
}
