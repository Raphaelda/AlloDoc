package entities;

import java.util.Date;

public class Treatement_Item 
{
  
   String Treatement_ID;
   String Doctor_Name;
   Date date;
   public Treatement_Item()
   {
	   Treatement_ID="";
	   Doctor_Name="";
	   date=null;
   }
   public Treatement_Item(String treatement_ID, String doctor_Name, Date date) 
   {
		
		Treatement_ID = treatement_ID;
		Doctor_Name = doctor_Name;
		this.date = date;
	}
    public String getTreatement_ID() 
    {
	    return Treatement_ID;
    }
    public void setTreatement_ID(String treatement_ID) 
    {
	    Treatement_ID = treatement_ID;
    }
    public String getDoctor_Name() 
    {
	    return Doctor_Name;
    }
    public void setDoctor_Name(String doctor_Name)  
    {
	    Doctor_Name = doctor_Name;
    }
    public Date getDate() 
    {
	  return date;
    }
    public void setDate(Date date) 
    {
	  this.date = date;
    }
   
  
}
