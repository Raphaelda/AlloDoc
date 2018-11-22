package model.backend;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.Consultation_Drug;
import entities.Treatement_Item;
import entities.Consultation;
import entities.Doctor;
import entities.Drug;
import entities.Login;
import entities.Patient;

public interface Backend 
{
	
	
	
		public void addDrugToConsultation (Consultation_Drug consultation_Drug) throws Exception;
		public void addConsultation (Consultation consultation) throws Exception;
		public void addDoctor (Doctor doctor) throws Exception;
		public void addDrug (Drug drug) throws Exception;
		public void addLogin (Login login) throws Exception;
		public void addPatient (Patient patient) throws Exception;
		//public void addTreatement_Item (Treatement_Item treatement_Item) throws Exception;
		
		//delete all the drug off specific consultation
		public void deleteConectorByConsultation(long consultationId)throws Exception;
		public void deleteDrugToConsultation (long consultationId,long  drugId) throws Exception;
		public void deleteConsultation (long consultationId) throws Exception;
		public void deleteDoctor (long doctorId) throws Exception;
		public void deleteDrug (long drugId) throws Exception;
		public void deleteLogin (long login) throws Exception;
		public void deletePatient (long patientId) throws Exception;
		//public void deleteTreatement_Item (Treatement_Item treatement_Item) throws Exception;

		public void updateDrugToConsultation (Consultation_Drug consultation_Drug) throws Exception;
		public void updateConsultation (Consultation consultation) throws Exception;
		public void updateDoctor (Doctor doctor) throws Exception;
		public void updateDrug (Drug drug) throws Exception;
		public void updatePassword (Login login) throws Exception;
		public void updatePatient (Patient patient) throws Exception;
		//public void updateTreatement_Item (Treatement_Item treatement_Item) throws Exception;
		
		//public ArrayList<Consultation_Drug> getConsultation_DrugList () throws Exception;
		//public ArrayList<Treatement_Item> getTreatement_ItemID (long treatement_ItemID) throws Exception;
		//public Product_Shop getConectorByShopAndProductID(long productID,long shopID, ArrayList<Product_Shop> conector ) throws Exception;

//get all the consultation of a patient , by patient id
		public ArrayList<Consultation> getConsultationListByPatientID (long patientID) throws Exception;
//get all the doctor of a patient , by patient id
		public ArrayList<Doctor> getDoctorListByPatientID (long patientID) throws Exception;
//get all the drug of a consultation , by consultation id
		public ArrayList<Drug> getDrugListByConsultationID (long consultationID) throws Exception;
//get all the patient of a specific doctor , by doctor id
		public ArrayList<Patient> getPatientListByDoctorID (long doctorID) throws Exception;
//get all the doctor in the medical center	
		public ArrayList<Doctor> getDoctorList() throws Exception;
//get all the password of each person in the medical center
		public ArrayList<Login> getPasswordList () throws Exception;
//get all the patient in the medical center
		public ArrayList<Patient> getPatientList()throws Exception;
//get all the drug which are  in the medical center
		public  ArrayList<Drug> getDrugList() throws Exception;
//get all the consultation in they are in this medical center
		public ArrayList<Consultation> getConsultationList() throws Exception;
//initialize  the list
		public void setLists();
//We use this function at the Login Page to return the doctor name		
		public String findDoctorName(long doctorId) throws Exception;
//we use this function to check login
		public boolean checkLogin (long id,String password)  throws Exception;
//we use this function in the home page to find Patient by Id and to check if he exist
	    public Patient findPatientById(long PatientId) throws Exception;
//find doctor by doctorId
	    public Doctor findDoctorById(long DoctorId) throws Exception;
//get all drug's names in a list
	    public ArrayList<String> getNameDrugList() throws Exception;
//get all the details of the consultation //all the consultation_drug which are bound to this consultation
	    public ArrayList<Consultation_Drug> getDetailsConsultation(long consultation) throws Exception;
//get the name of the drug by drug Id
	    public String getDrugNameById (long DrugId) throws Exception;
//get the Id of a drug by Name
	    public long getDrugIdByName(String drugName) throws Exception;
	    
	    public HashMap<Long,List<Consultation_Drug>> getTheDetailsOfAllTheConsultation(/*long patientId,*/ArrayList<Consultation> listConsultationByPatientId) throws Exception;
	    
	    public HashMap<Long ,String> DoctorHash_Id_Name()throws Exception;
	    
	    public HashMap<Long ,String> DrugHash_Id_Name()throws Exception;
	    
	   // public ArrayList<String> getListAllergyOfPatient(long patient_Id);  


}



