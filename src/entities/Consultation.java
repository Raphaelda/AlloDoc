package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonAutoDetect(fieldVisibility=Visibility.ANY,getterVisibility=Visibility.ANY,isGetterVisibility=Visibility.ANY)
public class Consultation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty(value="consultation_Id")
	private long consultation_Id;
	@JsonProperty(value="theDoctor_Id")
	private long theDoctor_Id;
	@JsonProperty(value="thePatient_Id")
	private long thePatient_Id;
	@JsonProperty(value="consultation_Price")
	private float consultation_Price;
	@JsonProperty(value="consultation_Report")
	private String consultation_Report;
	//private String Consultation_Prescription;
	@JsonProperty(value="consultation_Date")
	private String consultation_Date;
	@JsonProperty(value="consultation_Hours")
	private String consultation_Hours;
	
	//Constructor
	@JsonCreator
	public Consultation(@JsonProperty("theDoctor_Id")long theDoctor_Id,@JsonProperty("thePatient_Id")long thePatient_Id,@JsonProperty("consultation_Price") float consultation_Price,@JsonProperty("consultation_Report")String consultation_Report) 
	{
		String month = "";
		String day = "";
		String hours = "";
		String minute = "";
		String sec = "";
		this.theDoctor_Id = theDoctor_Id;
		this.thePatient_Id = thePatient_Id;
		this.consultation_Price = consultation_Price;
		this.consultation_Report = consultation_Report;
		//Consultation_Prescription = consultation_Prescription;
		//code for display hour and date
		Calendar c = Calendar.getInstance();
		if(c.get(Calendar.DAY_OF_MONTH)<10)
			day = "0";
		day += String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		if(c.get(Calendar.MONTH)+1<10)
			 month = "0";
		month += String.valueOf(c.get(Calendar.MONTH)+1);
	    String years=String.valueOf(c.get(Calendar.YEAR));
	    consultation_Date=day+"/"+month+"/"+years;
	    if(c.get(Calendar.HOUR)<10)
	    	hours = "0";
	    hours += String.valueOf(c.get(Calendar.HOUR));
	    if(c.get(Calendar.MINUTE)<10)
	    	minute = "0";
	    minute += String.valueOf(c.get(Calendar.MINUTE));
	    if(c.get(Calendar.SECOND)<10)
	    	sec = "0";
	    sec += String.valueOf(c.get(Calendar.SECOND));
	    consultation_Hours=hours+":"+minute+":"+sec;				
	}
	@JsonCreator
	public Consultation(@JsonProperty("theDoctor_Id")long theDoctor_Id,@JsonProperty("thePatient_Id")long thePatient_Id,@JsonProperty("consultation_Report")String consultation_Report) 
	{
		String month = "";
		String day = "";
		String hours = "";
		String minute = "";
		String sec = "";
		this.theDoctor_Id = theDoctor_Id;
		this.thePatient_Id = thePatient_Id;
		consultation_Price = 0;
		this.consultation_Report = consultation_Report;
		//Consultation_Prescription = consultation_Prescription;
		//code for display hour and date
				Calendar c = Calendar.getInstance();
				if(c.get(Calendar.DAY_OF_MONTH)<10)
					day = "0";
				day += String.valueOf(c.get(Calendar.DAY_OF_MONTH));
				if(c.get(Calendar.MONTH)+1<10)
					 month = "0";
				month += String.valueOf(c.get(Calendar.MONTH)+1);
			    String years=String.valueOf(c.get(Calendar.YEAR));
			    consultation_Date=day+"/"+month+"/"+years;
			    if(c.get(Calendar.HOUR)<10)
			    	hours = "0";
			    hours += String.valueOf(c.get(Calendar.HOUR));
			    if(c.get(Calendar.MINUTE)<10)
			    	minute = "0";
			    minute += String.valueOf(c.get(Calendar.MINUTE));
			    if(c.get(Calendar.SECOND)<10)
			    	sec = "0";
			    sec += String.valueOf(c.get(Calendar.SECOND));
			    consultation_Hours=hours+":"+minute+":"+sec;				
	}
	@JsonCreator
	public Consultation()
	{
		
		String month = "";
		String day = "";
		String hours = "";
		String minute = "";
		String sec = "";
		consultation_Id = 0;
		theDoctor_Id = 0;
		thePatient_Id = 0;
		consultation_Price = 0;
		consultation_Report = "";
		//Consultation_Prescription = "";
		//code for display hour and date
				Calendar c = Calendar.getInstance();
				if(c.get(Calendar.DAY_OF_MONTH)<10)
					day = "0";
				day += String.valueOf(c.get(Calendar.DAY_OF_MONTH));
				if(c.get(Calendar.MONTH)+1<10)
					 month = "0";
				month += String.valueOf(c.get(Calendar.MONTH)+1);
			    String years=String.valueOf(c.get(Calendar.YEAR));
			    consultation_Date=day+"/"+month+"/"+years;
			    if(c.get(Calendar.HOUR)<10)
			    	hours = "0";
			    hours += String.valueOf(c.get(Calendar.HOUR));
			    if(c.get(Calendar.MINUTE)<10)
			    	minute = "0";
			    minute += String.valueOf(c.get(Calendar.MINUTE));
			    if(c.get(Calendar.SECOND)<10)
			    	sec = "0";
			    sec += String.valueOf(c.get(Calendar.SECOND));
			    consultation_Hours=hours+":"+minute+":"+sec;	
		
	}
	
	
	//Methods
	@Override
	public boolean equals (Object obj)
		{
			if (obj == this) return true;
			
			if (obj == null || obj.getClass() != this.getClass()) return false;
			
			Consultation consultation = (Consultation) obj;
			return (
			 consultation_Id == consultation.getConsultation_Id() &&
			 theDoctor_Id == consultation.getConsultation_Id() &&
			 thePatient_Id == consultation.getThePatient_Id() &&
			 consultation_Price == consultation.getConsultation_Price() &&
			 consultation_Report.equals(consultation.consultation_Report) &&
			 consultation_Date.equals(consultation.consultation_Date) &&
			 consultation_Hours.equals(consultation.consultation_Hours)
	               );	
		}
		
	//GET AND SET
	public long getConsultation_Id() {
		return consultation_Id;
	}
	public void setConsultation_Id(long l) {
		consultation_Id = l;
	}
	public long getTheDoctor_Id() {
		return theDoctor_Id;
	}
	public void setTheDoctor_Id(long theDoctor_Id) {
		this.theDoctor_Id = theDoctor_Id;
	}
	public long getThePatient_Id() {
		return thePatient_Id;
	}
	public void setThePatient_Id(long thePatient_Id) {
		this.thePatient_Id = thePatient_Id;
	}
	public float getConsultation_Price() {
		return consultation_Price;
	}
	public void setConsultation_Price(float consultation_Price) {
		this.consultation_Price = consultation_Price;
	}
	public String getConsultation_Report() {
		return consultation_Report;
	}
	public void setConsultation_Report(String consultation_Report) {
		this.consultation_Report = consultation_Report;
	}
	public String getConsultation_Date() {
		return consultation_Date;
	}
	public void setConsultation_Date(String consultation_Date) {
		this.consultation_Date = consultation_Date;
	}
	public String getConsultation_Hours() {
		return consultation_Hours;
	}
	public void setConsultation_Hours(String consultation_Hours) {
		this.consultation_Hours = consultation_Hours;
	}
	
}
