package entities;

import java.io.Serializable;

public class Consultation_Drug implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private long theConsultation_Id;
	private long theDrug_Id;
	private int quantity;
	private int duration;
	//Constructor
	public Consultation_Drug(long theConsultation_Id, long theDrug_Id,int quantity,int duration) {
		this.theConsultation_Id = theConsultation_Id;
		this.theDrug_Id = theDrug_Id;
		this.quantity=quantity;
		this.duration=duration;
	}
	//constructor without duration and by default is two week
	public Consultation_Drug(long theConsultation_Id, long theDrug_Id,int quantity) {
		this.theConsultation_Id = theConsultation_Id;
		this.theDrug_Id = theDrug_Id;
		this.quantity=quantity;
		this.duration=2;
	}
	
	public Consultation_Drug() {
		theConsultation_Id = 0;
		theDrug_Id = 0;
		quantity=0;
		duration=0;
	}
	
	//Methods
	@Override
	public boolean equals (Object obj)
	{
		if (obj == this) return true;
		
		if (obj == null || obj.getClass() != this.getClass()) return false;
		
		Consultation_Drug consultation_Drug = (Consultation_Drug) obj;
		return ( theConsultation_Id == consultation_Drug.getTheConsultation_Id() &&
				theDrug_Id == consultation_Drug.getTheDrug_Id()&& quantity==consultation_Drug.getQuantity()
				&& duration==consultation_Drug.getDuration()
		        );	
	}
	
	//GET AND SET
	public long getTheConsultation_Id() {
		return theConsultation_Id;
	}
	public void setTheConsultation_Id(long theConsultation_Id) {
		this.theConsultation_Id = theConsultation_Id;
	}
	public long getTheDrug_Id() {
		return theDrug_Id;
	}
	public void setTheDrug_Id(long theDrug_Id) {
		this.theDrug_Id = theDrug_Id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
