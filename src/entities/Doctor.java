package entities;

import java.io.Serializable;

public class Doctor implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private long doctor_Id;
	private String doctor_FirstName;
	private String doctor_LastName;
	private String doctor_Type;
	private String doctor_Tel;
	private String doctor_Mail;
	
	
	//Constructor
	public Doctor(String doctor_FirstName,
			String doctor_LastName,  String doctor_Type,
			String doctor_Tel, String doctor_Mail) {
		this.doctor_FirstName = doctor_FirstName;
		this.doctor_LastName = doctor_LastName;
		this.doctor_Type = doctor_Type;
		this.doctor_Tel = doctor_Tel;
		this.doctor_Mail = doctor_Mail;
	}
	public Doctor() {
		doctor_Id = (long) 0;
		doctor_FirstName = "";
		doctor_LastName = "";
		doctor_Type = "";
		doctor_Tel = "";
		doctor_Mail = "";
	}
	
	//Methods
	@Override
	public boolean equals (Object obj)
	{
		if (obj == this) return true;
				
		if (obj == null || obj.getClass() != this.getClass()) return false;
				
		Doctor doctor = (Doctor) obj;
		return (
				  doctor_Id == doctor.getDoctor_Id() &&
				  doctor_FirstName.equals(doctor.getDoctor_FirstName()) &&
				  doctor_LastName.equals(doctor.getDoctor_LastName()) &&
				  doctor_Type.equals(doctor.getDoctor_Type()) &&
				  doctor_Tel.equals(doctor.getDoctor_Tel()) &&
				  doctor_Mail.equals(doctor.getDoctor_Mail())
				);	
	}
	
	//GET AND SET
	public long getDoctor_Id() {
		return doctor_Id;
	}
	public void setDoctor_Id(long l) {
		doctor_Id = l;
	}
	public String getDoctor_FirstName() {
		return doctor_FirstName;
	}
	public void setDoctor_FirstName(String doctor_FirstName) {
		this.doctor_FirstName = doctor_FirstName;
	}
	public String getDoctor_LastName() {
		return doctor_LastName;
	}
	public void setDoctor_LastName(String doctor_LastName) {
		this.doctor_LastName = doctor_LastName;
	}

	public String getDoctor_Type() {
		return doctor_Type;
	}
	public void setDoctor_Type(String doctor_Type) {
		this.doctor_Type = doctor_Type;
	}
	public String getDoctor_Tel() {
		return doctor_Tel;
	}
	public void setDoctor_Tel(String doctor_Tel) {
		this.doctor_Tel = doctor_Tel;
	}
	public String getDoctor_Mail() {
		return doctor_Mail;
	}
	public void setDoctor_Mail(String doctor_Mail) {
		this.doctor_Mail = doctor_Mail;
	}
}
