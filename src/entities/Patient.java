package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Patient  implements Serializable
{

   
   private static final long serialVersionUID = 1L;
   private long patient_Id;
   private String patient_FirstName;
   private String patient_LastName;
   private String patient_Adress;
   private String patient_Postal_Code;
   private String patient_City;
   private String patient_Tel;
   private String patient_Mail;
   private String patient_Birthdate;
  // private String Treating_Doctor;
   private float patient_Weight;
   private float patient_Height;
  // private ArrayList<String> Allergy;
  // private ArrayList<String> Operation;
  // private ArrayList<String> Diseases;
   public enum Sex 
   {
	   M("Masculin"),F("Feminin");
	   private String name="";
	   
       Sex(String Name)
       {
	        this.name=Name;
       }
       public String toString()
       {
    	   return name;
       }
   }
   private Sex patient_Sex;  
   public enum Blood_Group
   {
	   Opos("O+"),Oneg("O-"),Apos("A+"),Aneg("A-"),Bpos("B+"),Bneg("B-"),ABpos("AB+"),ABneg("AB-");
	   private String name="";
   
       Blood_Group(String Name)
       {
	        this.name=Name;
       }
       public String toString()
       {
    	   return name;
       }
   }
   private Blood_Group patient_BloodGroup;
	    
//Constructor
   public Patient(String patient_FirstName,
   			String patient_LastName, String patient_Adress, String patient_postal_Code,
   			String patient_city, String patient_Tel, String patient_Mail,
   			String patient_Birthdate,
   			float patient_Weight, float patient_Height,
   		    Sex patient_Sex,   //ArrayList<String> allergy, ArrayList<String> operation,
   			//ArrayList<String> diseases,
   			Blood_Group patient_BloodGroup) {
	   this.patient_FirstName = patient_FirstName;
	   this.patient_LastName = patient_LastName;
	   this.patient_Adress = patient_Adress;
	   this.patient_Postal_Code = patient_postal_Code;
	   this.patient_City = patient_city;
	   this.patient_Tel = patient_Tel;
	   this.patient_Mail = patient_Mail;
	   this.patient_Birthdate = patient_Birthdate;
   		//Treating_Doctor = treating_Doctor;
	   this.patient_Weight = patient_Weight;
	   this.patient_Height = patient_Height;
   		//Allergy=allergy;
        //Diseases=diseases;
        //Operation=operation;
	   this.patient_Sex = patient_Sex;
	   this.patient_BloodGroup = patient_BloodGroup;
   	}
   public Patient() {
   	patient_Id = 0;
   	patient_FirstName = "";
   	patient_LastName = "";
   	patient_Adress = "";
   	patient_Postal_Code = "";
   	patient_City = "";
   	patient_Tel = "";
   	patient_Mail = "";
   	patient_Birthdate = "";
   	//Treating_Doctor = "";
   	patient_Weight = 0;
   	patient_Height = 0;
   //	Allergy = null;
   //	Operation = null;
   //	Diseases = null;
   	patient_Sex = null;
   	patient_BloodGroup = null;
   }
  
//Methods
   @Override
   public boolean equals(Object obj)
   {
       if (obj == this)
           return true;
       if (obj == null || obj.getClass()!= this.getClass())
           return false;
       Patient pat = (Patient)obj;
       return (this.patient_Id==pat.getPatient_Id() 
            && this.patient_Adress.equals(pat.getPatient_Adress())
            && this.patient_Birthdate.equals(pat.getPatient_Birthdate())
            && this.patient_FirstName.equals(pat.getPatient_FirstName())
            && this.patient_Height==pat.getPatient_Height()
            && this.patient_LastName.equals(pat.getPatient_LastName())
            && this.patient_Mail.equals(pat.getPatient_Mail())
            && this.patient_Tel.equals(pat.getPatient_Tel())
            && this.patient_Weight==pat.getPatient_Weight()
            && this.patient_City.equals(pat.getPatient_City())
            && this.patient_Postal_Code.equals(pat.getPatient_Postal_Code())
            && this.patient_BloodGroup.equals(pat.getPatient_BloodGroup())
            && this.patient_Sex.equals(pat.getPatient_Sex()));
           /* && this.Allergy.equals(patient.Allergy)
            && this.Diseases.equals(patient.Diseases)
            && this.Operation.equals(patient.Operation));*/
   }
   
   
//GET AND SET
public long getPatient_Id() {
	return patient_Id;
}
public void setPatient_Id(long l) {
	patient_Id = l;
}
public String getPatient_FirstName() {
	return patient_FirstName;
}
public void setPatient_FirstName(String patient_FirstName) {
	this.patient_FirstName = patient_FirstName;
}
public String getPatient_LastName() {
	return patient_LastName;
}
public void setPatient_LastName(String patient_LastName) {
	this.patient_LastName = patient_LastName;
}
public String getPatient_Adress() {
	return patient_Adress;
}
public void setPatient_Adress(String patient_Adress) {
	this.patient_Adress = patient_Adress;
}
public String getPatient_Postal_Code() {
	return patient_Postal_Code;
}
public void setPatient_Postal_Code(String patient_postal_Code) {
	this.patient_Postal_Code = patient_postal_Code;
}
public String getPatient_City() {
	return patient_City;
}
public void setCity(String patient_city) {
	this.patient_City = patient_city;
}
public String getPatient_Tel() {
	return patient_Tel;
}
public void setPatient_Tel(String patient_Tel) {
	this.patient_Tel = patient_Tel;
}
public String getPatient_Mail() {
	return patient_Mail;
}
public void setPatient_Mail(String patient_Mail) {
	this.patient_Mail = patient_Mail;
}
public String getPatient_Birthdate() {
	return patient_Birthdate;
}
public void setPatient_Birthdate(String patient_Birthdate) {
	this.patient_Birthdate = patient_Birthdate;
}

public String getPatient_BloodGroup() {
	return patient_BloodGroup.toString();
}
public void setPatient_BloodGroup(Blood_Group patient_BloodGroup) {
	this.patient_BloodGroup = patient_BloodGroup;
}
public float getPatient_Weight() {
	return patient_Weight;
}
public void setPatient_Weight(float patient_Weight) {
	this.patient_Weight = patient_Weight;
}
public float getPatient_Height() {
	return patient_Height;
}
public void setPatient_Height(float patient_Height) {
	this.patient_Height = patient_Height;
}
/*public ArrayList<String> getAllergy() {
	ArrayList<String> allergy=new ArrayList<String>();
	if(Allergy==null)return null;
	for(int i =0 ;i<Allergy.size();i++)
		allergy.add(Allergy.get(i));
	return allergy;
}
public void setAllergy(String allergy) {
	
		Allergy.add(allergy);
	return;
}
public ArrayList<String> getOperation() {
	ArrayList<String> operation=new ArrayList<String>();
	if(Operation==null)return null;
	for(int i =0 ;i<Operation.size();i++)
		operation.add(Operation.get(i));
	return operation;
}
public void setOperation(String operation) {

	Operation.add(operation);
	return;
}
public ArrayList<String> getDiseases() {
	ArrayList<String> diseases=new ArrayList<String>();
	if(Diseases==null)return null;
	for(int i =0 ;i<Diseases.size();i++)
		diseases.add(Diseases.get(i));
	return diseases;
}
public void setDiseases(String diseases) {

		Diseases.add(diseases);	
	return;
}*/
public String getPatient_Sex() {
	return patient_Sex.toString();
}
public void setPatient_Sex(Sex patient_Sex) {
	this.patient_Sex = patient_Sex;
}
}