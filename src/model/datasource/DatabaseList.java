package model.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


import entities.Consultation;
import entities.Consultation_Drug;
import entities.Doctor;
import entities.Drug;
import entities.Login;
import entities.Login.Authorization;
import entities.Patient;
import entities.Patient.Blood_Group;
import entities.Patient.Sex;
import model.backend.Backend;

public class DatabaseList implements Backend {
	
	ArrayList<Consultation> ConsultationList=new ArrayList<Consultation>();
	ArrayList<Consultation_Drug> Consultation_DrugList=new ArrayList<Consultation_Drug>();
	ArrayList<Doctor> DoctorList=new ArrayList<Doctor>();
	ArrayList<Drug> DrugList=new ArrayList<Drug>();
	ArrayList<Login> LoginList=new ArrayList<Login>() ;
	ArrayList<Patient> PatientList=new ArrayList<Patient>();
	//ArrayList<String> ListOfAllergy = new ArrayList<String>();
	long ConsultationCounter=0;
	long DoctorCounter=333222111;
	long DrugCounter=0;
	long PatientCounter=333444555;
	
	public DatabaseList()
	{
	    
		setLists();
	}

	@Override
	public void addDrugToConsultation(Consultation_Drug consultation_Drug) throws Exception {
		for(Consultation_Drug Consultation_DrugItem : Consultation_DrugList)
			if(Consultation_DrugItem.getTheConsultation_Id()==consultation_Drug.getTheConsultation_Id() && Consultation_DrugItem.getTheDrug_Id()== consultation_Drug.getTheDrug_Id() )
				throw new Exception("This Drug already existing in this consultation");
		
        
		Consultation_DrugList.add(consultation_Drug);

	}

	@Override
	public void addConsultation(Consultation consultation) throws Exception {
		for(Consultation ConsultationItem : ConsultationList)
			if(ConsultationItem.equals(consultation))
				throw new Exception("This consultation already existing in the data base of the medical center");
		
        consultation.setConsultation_Id(ConsultationCounter++);
        ConsultationList.add(consultation);
	}

	@Override
	public void addDoctor(Doctor doctor) throws Exception {
		for(Doctor DoctorItem : DoctorList)
			if(DoctorItem.equals(doctor))
				throw new Exception("This Doctor already existing in the data base of the medical center");
		
		doctor.setDoctor_Id(DoctorCounter++);
        DoctorList.add(doctor);

	}

	@Override
	public void addDrug(Drug drug) throws Exception {
		for(Drug DrugItem : DrugList)
			if(DrugItem.equals(drug))
				throw new Exception("This Drug already existing in the data base of the medical center");
		
		drug.setDrug_Id(DrugCounter++);
		DrugList.add(drug);

	}

	@Override
	public void addLogin(Login login) throws Exception {
		for(Login LoginItem : LoginList)
			if(LoginItem.equals(login))
				throw new Exception("This Login already existing in the data base of the medical center");
		
		
		LoginList.add(login);


	}

	@Override
	public void addPatient(Patient patient) throws Exception {
		for(Patient PatientItem : PatientList)
			if(PatientItem.equals(patient))
				throw new Exception("This Patient already existing in the data base of the medical center");
		
		patient.setPatient_Id(PatientCounter++);
		PatientList.add(patient);

	}

	
	//delete specifics drug of a specific consultation
	@Override
	public void deleteDrugToConsultation(long consultationId ,long drugId) throws Exception {
		boolean flag =false;
		for(Consultation_Drug Consultation_DrugItem :Consultation_DrugList)
			if(Consultation_DrugItem.getTheConsultation_Id()==consultationId && Consultation_DrugItem.getTheDrug_Id()==drugId)
				{
				   Consultation_DrugList.remove(Consultation_DrugItem);
				   flag=true;
				}
		if(flag)return;
		else throw new Exception ("can't delete this drug in this consultation ,ther is no one to delete");

	}
	
	//delete all the drugs of a specific consultation
	@Override
	public void deleteConectorByConsultation(long consultationId)throws Exception
	{
		boolean flag=false;
		for (Consultation_Drug Consultation_DrugItem : Consultation_DrugList )
		{
		   if (Consultation_DrugItem.getTheConsultation_Id()==consultationId)
			   {
			      Consultation_DrugList.remove(Consultation_DrugItem);
			      flag=true;
			   }
		}
		
		if(flag)return;
		else throw new Exception ("There is no drug in this consultation our data base");
		
	}
	@Override
	public void deleteConsultation(long consultationId) throws Exception {
		//delete all drug which are bound to this consultation in the Consultation_DrugList 
		deleteConectorByConsultation(consultationId);
		for(Consultation ConsultationItem : ConsultationList)
			if(ConsultationItem.getConsultation_Id()==consultationId)
				{
				   ConsultationList.remove(ConsultationItem);
				   return;
				}
		throw new Exception ("There is no consultation with this ID in our data base");

	}

	@Override
	public void deleteDoctor(long doctorId) throws Exception {
		for(Doctor DoctorItem : DoctorList)
			if(DoctorItem.getDoctor_Id()==doctorId)
				{
				   DoctorList.remove(DoctorItem);
				   return;
				}
		throw new Exception ("There is no Doctor with this ID in our data base");


	}

	@Override
	public void deleteDrug(long drugId) throws Exception {
		for(Drug DrugItem : DrugList)
			if(DrugItem.getDrug_Id()==drugId)
				{
				  DrugList.remove(DrugItem);
				  return;
				}
		throw new Exception ("There is no drug with this drugID in our data base");

	}

	@Override
	public void deleteLogin(long login) throws Exception {
		for(Login LoginItem : LoginList)
			if(LoginItem.getLogin()==login)
				{
				   LoginList.remove(LoginItem);
				   return;
				}
		throw new Exception ("There is no peron with this Login in our data base");

	}

	@Override
	public void deletePatient(long patientId) throws Exception {
		for(Patient PatientItem : PatientList)
			if(PatientItem.getPatient_Id()==patientId)
				{
				  PatientList.remove(PatientItem);
				  return;
				}
		throw new Exception ("There is no patient with this ID in our data base");

	}

	

	@Override
	public void updateDrugToConsultation(Consultation_Drug consultation_Drug)throws Exception {
		for(Consultation_Drug Consultation_DrugItem : Consultation_DrugList)
		{
			if(Consultation_DrugItem.getTheConsultation_Id()==consultation_Drug.getTheConsultation_Id() && 
			   Consultation_DrugItem.getTheDrug_Id()==consultation_Drug.getTheDrug_Id())
			{
				Consultation_DrugItem.setDuration(consultation_Drug.getDuration());
				Consultation_DrugItem.setQuantity(consultation_Drug.getQuantity());
                return;
			}
		}
		
		throw new Exception ("There is no Consultation_Drug with this ID in our data base");

	}

	@Override
	public void updateConsultation(Consultation consultation) throws Exception {
		for(Consultation ConsultationItem : ConsultationList )
		{
			if(ConsultationItem.getConsultation_Id()==consultation.getConsultation_Id())
	
			{
				ConsultationItem.setConsultation_Date(consultation.getConsultation_Date());
				ConsultationItem.setConsultation_Hours(consultation.getConsultation_Hours());
				ConsultationItem.setConsultation_Id(consultation.getConsultation_Id());
				//ConsultationItem.setConsultation_Prescription(consultation.getConsultation_Prescription());
				ConsultationItem.setConsultation_Price(consultation.getConsultation_Price());
				ConsultationItem.setConsultation_Report(consultation.getConsultation_Report());
				ConsultationItem.setTheDoctor_Id(consultation.getTheDoctor_Id());
				ConsultationItem.setThePatient_Id(consultation.getThePatient_Id());
				return;
			}
		}
		
		throw new Exception ("This consultation can't be updated , it isn't in our database");

	}

	@Override
	public void updateDoctor(Doctor doctor) throws Exception {
		for(Doctor DoctorItem : DoctorList)
		{
			if(DoctorItem.getDoctor_Id()==doctor.getDoctor_Id())
			{
				DoctorItem.setDoctor_FirstName(doctor.getDoctor_FirstName());
				DoctorItem.setDoctor_LastName(doctor.getDoctor_LastName());
				DoctorItem.setDoctor_Id(doctor.getDoctor_Id());
				DoctorItem.setDoctor_Mail(doctor.getDoctor_Mail());
				DoctorItem.setDoctor_Tel(doctor.getDoctor_Tel());
				DoctorItem.setDoctor_Type(doctor.getDoctor_Type());
				return;
			}
		}
		
		throw new Exception ("This Doctor can't be updated , it isn't in our database");

	}

	@Override
	public void updateDrug(Drug drug) throws Exception {
		for(Drug DrugItem : DrugList)
		{
			if(DrugItem.getDrug_Id()==drug.getDrug_Id())
			{
				DrugItem.setDrug_BoxQuantity(drug.getDrug_BoxQuantity());
				DrugItem.setDrug_Id(drug.getDrug_Id());
				DrugItem.setDrug_Name(drug.getDrug_Name());
				DrugItem.setDrug_Price(drug.getDrug_Price());
				DrugItem.setWhith_Ordinance(drug.getWhith_Ordinance());
				return;
			}
		}
		
		throw new Exception ("This Drug can't be updated , it isn't in our database");
	}

	@Override
	public void updatePassword(Login login) throws Exception {
		for(Login LoginItem : LoginList)
		{
			if(LoginItem.getLogin()==login.getLogin())
			{
				LoginItem.setAuthorization(login.getAuthorization());
				LoginItem.setPassword(login.getPassword());
				LoginItem.setLogin(login.getLogin());//not necessary
				return;
				
			}
		}
		
		throw new Exception ("This User ID can't be updated , it isn't in our database");
	}

	@Override
	public void updatePatient(Patient patient) throws Exception {
		for(Patient PatientItem : PatientList)
		{
			if(PatientItem.getPatient_Id()==patient.getPatient_Id())
			{
				/*ArrayList<String> allergy=patient.getAllergy();
				ArrayList<String> diseases=patient.getDiseases();
				ArrayList<String> operation=patient.getOperation();
				
				for(int i=0;i < allergy.size();i++)
		   		  {
					PatientItem.setAllergy(allergy.get(i));
		   		  }
				for(int i=0;i < operation.size();i++)
		 		  {
					PatientItem.setOperation(operation.get(i));
		 		  }
				for(int i=0;i < diseases.size();i++)
				  {
					PatientItem.setDiseases(diseases.get(i));
				  }*/
				PatientItem.setCity(patient.getPatient_City());
				PatientItem.setPatient_Adress(patient.getPatient_Adress());
				PatientItem.setPatient_Birthdate(patient.getPatient_Birthdate());
				PatientItem.setPatient_BloodGroup(Patient.Blood_Group.valueOf(patient.getPatient_BloodGroup()));
				PatientItem.setPatient_FirstName(patient.getPatient_FirstName());
				PatientItem.setPatient_Height(patient.getPatient_Height());
				PatientItem.setPatient_Id(patient.getPatient_Id());//not necessary
				PatientItem.setPatient_LastName(patient.getPatient_LastName());
				PatientItem.setPatient_Mail(patient.getPatient_Mail());
				PatientItem.setPatient_Postal_Code(patient.getPatient_Postal_Code());
				PatientItem.setPatient_Sex(Patient.Sex.valueOf(patient.getPatient_Sex()));
				PatientItem.setPatient_Tel(patient.getPatient_Tel());
				PatientItem.setPatient_Weight(patient.getPatient_Weight());
				//PatientItem.setTreating_Doctor(patient.getTreating_Doctor());
				return;
			}
		}
		
		throw new Exception ("This Patient can't be updated , it isn't exist in our database");

	}

//get all the consultation of a patient , by patient id	
	@Override
	public ArrayList<Consultation> getConsultationListByPatientID(long patientId) throws Exception {
		boolean flag=false;
		ArrayList<Consultation> consultationList = new ArrayList<Consultation>();
		for(Consultation ConsultationItem :ConsultationList)
		{
			if(ConsultationItem.getThePatient_Id()==patientId)
			{
				consultationList.add(ConsultationItem);
				flag=true;
			}
		}
		if(flag)return consultationList;
		else throw new Exception ("they are no consultation for this patient in our data base");
	}

	@Override
	public ArrayList<Doctor> getDoctorListByPatientID(long patientId) throws Exception {
		boolean flag=false;
		ArrayList<Doctor> doctorList =getDoctorList();
		ArrayList<Doctor> doctorListResult =new ArrayList<Doctor>();
		ArrayList<Consultation> consultationList=getConsultationListByPatientID(patientId);
		for(Consultation ConsultationItem :consultationList)
		{
			for(Doctor DoctorItem : doctorList)
			{
				if(DoctorItem.getDoctor_Id()==ConsultationItem.getTheDoctor_Id() && !doctorListResult.contains(DoctorItem))
				{
					doctorListResult.add(DoctorItem);
					flag=true;
				}
			}
		}
		if(flag)return doctorListResult;
		else throw new Exception ("they are no doctor  which treat  this patient in our data base");
	
	}
//get all the drugs of a medical consultation
	@Override
	public ArrayList<Drug> getDrugListByConsultationID(long consultationID) throws Exception {
		boolean flag = false;
		ArrayList<Drug> drugList = new ArrayList<Drug>();
		for(Consultation_Drug Consultation_DrugItem : Consultation_DrugList )
		{
			if(Consultation_DrugItem.getTheConsultation_Id()==consultationID)
			{
				for(Drug DrugItem : DrugList)
				{
					if(DrugItem.getDrug_Id()==Consultation_DrugItem.getTheDrug_Id())
					{
						drugList.add(DrugItem);
						flag = true;
					}
				}
			}
		}
		if(flag)return drugList;
		else throw new Exception ("they are no Drug  for  this consultation in our data base");
	}

	@Override
	public ArrayList<Patient> getPatientListByDoctorID(long doctorID) throws Exception {
		boolean flag = false;
		ArrayList<Patient> patientList=new ArrayList<Patient>();
		for(Consultation ConsultationItem : ConsultationList)
		{
			if(ConsultationItem.getTheDoctor_Id()==doctorID)
			{
				for(Patient PatientItem : PatientList)
				{
					if(ConsultationItem.getThePatient_Id()==PatientItem.getPatient_Id() && !patientList.contains(PatientItem))
					{
						patientList.add(PatientItem);
						flag = true;
						
					}
				}
			}
		}
		if(flag)return null;
		else throw new Exception ("this Doctor don't have any patient at this date  in our data base");
	}

	@Override
	public ArrayList<Doctor> getDoctorList() throws Exception {
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		for (Doctor DoctorItem : DoctorList)
		{
			
			doctorList.add(DoctorItem);
		}
		if(doctorList.size() != 0)
		   return doctorList;
		else
		   throw new Exception("There is no doctor in our medical center!");
	}

	@Override
	public ArrayList<Login> getPasswordList() throws Exception {
		ArrayList<Login> loginList = new ArrayList<Login>();
		for (Login LoginItem : LoginList)
		{
			
			loginList.add(LoginItem);
		}
		if(loginList.size() != 0)
		   return loginList;
		else
		   throw new Exception("There is no UserName and Login in our medical center!");
	}

	@Override
	public ArrayList<Patient> getPatientList() throws Exception {
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		for (Patient PatientItem : PatientList)
		{
			
			patientList.add(PatientItem);
		}
		if(patientList.size() != 0)
		   return patientList;
		else
		   throw new Exception("There is no Patient in our medical center!");
	}

	@Override
	public ArrayList<Drug> getDrugList() throws Exception {
		ArrayList<Drug> drugList = new ArrayList<Drug>();
		for (Drug DrugItem : DrugList)
		{
			
			drugList.add(DrugItem);
		}
		if(drugList.size() != 0)
		   return drugList;
		else
		   throw new Exception("There is no Drug in our medical center!");
	}

	@Override
	public ArrayList<Consultation> getConsultationList() throws Exception {
		ArrayList<Consultation> consultationList = new ArrayList<Consultation>();
		for (Consultation ConsultationItem : ConsultationList)
		{
			
			consultationList.add(ConsultationItem);
		}
		if(consultationList.size() != 0)
		   return consultationList;
		else
		   throw new Exception("There is no consultation in our medical center!");
	}
	
	public String findDoctorName(long doctorId) throws Exception
	{
		
				for(Doctor DoctorItem :DoctorList)
				{
					if(doctorId==DoctorItem.getDoctor_Id())
						{ 
						   return DoctorItem.getDoctor_FirstName()+" "+DoctorItem.getDoctor_LastName();
						}
				}
			
		throw new Exception("This Doctor dosn't found in our Data base");
	}
	
	public boolean checkLogin (long id,String password)  throws Exception
	{
		for(Login LoginItem : LoginList)
		{
			if(LoginItem.getLogin()==id && LoginItem.getPassword().equals(password))
			{
				return true;
			}
		}
		throw new Exception("This login dosen't match with this password please try again");	
     }
	
	/*public ArrayList<String> createListOfAllergy() throws Exception
	{
		String arr [] = {"Wheat","Tree nut","Tartrazine","Sulfites","Soy Anaphy","Peanut","Oats",
						"Milk","Meat","Hot pepper","Gluten","Garlic","Fruit","Fish","Egg","Tetracycli",
						"Dilantin","Tegretol","Penicilline","Cephalosporins","Sulfonamides","Non-steroidal",
						"Intravenous constrat dye","Local anesthetics","Pollen","Cat","Dog","Insect sting",
						"Mold","Perfume","Cosmetics","Semen","Latex","Water","House dust mite","Nickel",
						"Gold","Chromium","Cobalt","Formaldehyde","Photographic","Fungicide",};
		for(int i =0 ; i<arr.length;i++)
		{
			ListOfAllergy.add(arr[i]);
		}
		return ListOfAllergy;
	}*/
	
	/*public void readScanner ()
	{
		
		try 
		{
			File allergie = new File ("/users/daniel/Desktop/allergy.txt");
			Scanner sc = new Scanner (allergie);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public ArrayList<String> allergyListRand(int sizeGeneration) throws Exception
	{
		ArrayList<String> Allergy = new ArrayList<String>();
		int lower = 0;
		int higher = ListOfAllergy.size();
		int random;
		for(int i = 0 ; i<sizeGeneration ; i++)
		{
			random= (int)(Math.random() * (higher-lower)) + lower;
			Allergy.add(ListOfAllergy.get(random));
		}
        
		return Allergy;
		
	}*/

	@Override
	public void setLists() 
	{
		try
		{
		 //readScanner();
		 //createListOfAllergy();
			
		 addDoctor(new Doctor("Daniel","Haim","Neurologue","0526141565","DanielHaim@gmail.com"));
		 addDoctor(new Doctor("Raphael","Dahan","Ginecologue","0542652972","RaphaelDahan@Hotmail.com"));
		 addDoctor(new Doctor("Lea","Salame","Kine","0526783910","LeaSalame@gmail.com"));
				 
		/* addPatient(new Patient("Marc","Zunkenberg","21 rehov havaad haleoumi","94105","Jerusalem","0536789219","Facebook@gmail.com","12/12/1990",80,190,allergyListRand(4),null,null,Sex.M,Blood_Group.ABneg));
		 addPatient(new Patient("Bill","Gates","22 rehov havaad haleoumi","94106","Jerusalem","0536735642","Microsoft@gmail.com","11/11/1961",(float)88,(float)175,null,null,null,Sex.M,Blood_Group.Oneg));
		 addPatient(new Patient("Steve","Jobs","23 rehov havaad haleoumi","94107","Jerusalem","0132789219","Apple@gmail.com","10/10/1962",(float)65,(float)180,null,null,null,Sex.M,Blood_Group.Bpos));
		 addPatient(new Patient("Steve","Vozniak","24 rehov havaad haleoumi","94108","Jerusalem","0536259929","OS@gmail.com","09/09/1955",(float)100,(float)168,null,null,null,Sex.M,Blood_Group.Apos));
		 addPatient(new Patient("Larry","Page","25 rehov havaad haleoumi","94109","Jerusalem","0536789219","Google@gmail.com","08/108/1975",(float)80,(float)190,null,null,null,Sex.M,Blood_Group.ABneg));
		 addPatient(new Patient("Rich","Hickey","26 rehov havaad haleoumi","94110","Jerusalem","0533889219","Clojure@gmail.com","07/07/1969",(float)80,(float)190,null,null,null,Sex.M,Blood_Group.ABneg));*/
       
		 
		 addPatient(new Patient("Marc","Zunkenberg","21 rehov havaad haleoumi","94105","Jerusalem","0536789219","Facebook@gmail.com","12/12/1990",80,190,Sex.M,Blood_Group.ABneg));
		 addPatient(new Patient("Bill","Gates","22 rehov havaad haleoumi","94106","Jerusalem","0536735642","Microsoft@gmail.com","11/11/1961",(float)88,(float)175,Sex.M,Blood_Group.Oneg));
		 addPatient(new Patient("Steve","Jobs","23 rehov havaad haleoumi","94107","Jerusalem","0132789219","Apple@gmail.com","10/10/1962",(float)65,(float)180,Sex.M,Blood_Group.Bpos));
		 addPatient(new Patient("Steve","Vozniak","24 rehov havaad haleoumi","94108","Jerusalem","0536259929","OS@gmail.com","09/09/1955",(float)100,(float)168,Sex.M,Blood_Group.Apos));
		 addPatient(new Patient("Larry","Page","25 rehov havaad haleoumi","94109","Jerusalem","0536789219","Google@gmail.com","08/108/1975",(float)80,(float)190,Sex.M,Blood_Group.ABneg));
		 addPatient(new Patient("Rich","Hickey","26 rehov havaad haleoumi","94110","Jerusalem","0533889219","Clojure@gmail.com","07/07/1969",(float)80,(float)190,Sex.M,Blood_Group.ABneg));
		 
		 
		 
		 addDrug(new Drug(30,"Acamol",true,(short)20));	
		 addDrug(new Drug(31,"Dafalgan",true,(short)10));	
		 addDrug(new Drug(32,"Pivalone",true,(short)25));	
		 addDrug(new Drug(33,"Daflon",true,(short)15));	
		 addDrug(new Drug(34,"Efferalgan",true,(short)22));	
		 addDrug(new Drug(35,"Eludril",true,(short)12));	
		 addDrug(new Drug(36,"Solupred",true,(short)30));	
		 addDrug(new Drug(37,"Magnesium Microsol",true,(short)28));	
		 addDrug(new Drug(38,"Maxidrol",true,(short)5));	
		 addDrug(new Drug(39,"Mercalm",true,(short)23));	
		 addDrug(new Drug(40,"Morphine Aguettant",true,(short)35));	
		 addDrug(new Drug(41,"Yocoral",true,(short)26));	
		 addDrug(new Drug(42,"Remicade",true,(short)16));	
		 addDrug(new Drug(43,"Rennie S/S",true,(short)21));	
		 addDrug(new Drug(44,"Refludan",true,(short)18));	
        
		 addConsultation(new Consultation(DoctorList.get(0).getDoctor_Id(),PatientList.get(0).getPatient_Id(),(float)150,"Angine"));
		 addConsultation(new Consultation(DoctorList.get(1).getDoctor_Id(),PatientList.get(1).getPatient_Id(),(float)200,"Gastro"));
		 addConsultation(new Consultation(DoctorList.get(2).getDoctor_Id(),PatientList.get(2).getPatient_Id(),(float)100,"Fievre"));
		 addConsultation(new Consultation(DoctorList.get(0).getDoctor_Id(),PatientList.get(3).getPatient_Id(),(float)150,"Sinusite"));
		 addConsultation(new Consultation(DoctorList.get(1).getDoctor_Id(),PatientList.get(4).getPatient_Id(),(float)100,"Mal de dos"));
		 addConsultation(new Consultation(DoctorList.get(2).getDoctor_Id(),PatientList.get(5).getPatient_Id(),(float)180,"Bronchite"));
       
		 addLogin(new Login(DoctorList.get(0).getDoctor_Id(),"daniel",Authorization.level2));
		 addLogin(new Login(DoctorList.get(1).getDoctor_Id(),"raphael",Authorization.level2));
		 addLogin(new Login(DoctorList.get(2).getDoctor_Id(),"lea",Authorization.level2));
        
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(0).getConsultation_Id(),DrugList.get(0).getDrug_Id(),2,2));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(0).getConsultation_Id(),DrugList.get(1).getDrug_Id(),1,1));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(1).getConsultation_Id(),DrugList.get(2).getDrug_Id(),3,3));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(1).getConsultation_Id(),DrugList.get(3).getDrug_Id(),2,4));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(2).getConsultation_Id(),DrugList.get(4).getDrug_Id(),3,2));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(2).getConsultation_Id(),DrugList.get(5).getDrug_Id(),5,3));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(3).getConsultation_Id(),DrugList.get(6).getDrug_Id(),6,8));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(3).getConsultation_Id(),DrugList.get(7).getDrug_Id(),9,6));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(4).getConsultation_Id(),DrugList.get(8).getDrug_Id(),2,7));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(4).getConsultation_Id(),DrugList.get(9).getDrug_Id(),2,9));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(5).getConsultation_Id(),DrugList.get(10).getDrug_Id(),3,1));
		 addDrugToConsultation(new Consultation_Drug(ConsultationList.get(5).getConsultation_Id(),DrugList.get(11).getDrug_Id(),4,4));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Patient findPatientById(long PatientId) throws Exception 
	{
	   for(Patient PatientItem : PatientList)
		   if(PatientItem.getPatient_Id()==PatientId)
			   return PatientItem;
	   throw new Exception ("This Id dosen't match with any patient in our data base");
	   
	}

	@Override
	public ArrayList<String> getNameDrugList() throws Exception{
		// TODO Auto-generated method stub
		ArrayList<String> res =new ArrayList<String>();
		for(Drug item:DrugList)
			res.add(item.getDrug_Name());
		if(res.size()>0)
		   return res;
		else		
			throw new Exception("there is no drug in our databalist getNameDrugList");
	}
@Override
	public ArrayList<Consultation_Drug> getDetailsConsultation(long consultationId) throws Exception 
	{
		ArrayList<Consultation_Drug> result = new ArrayList<Consultation_Drug>();
		for(Consultation_Drug Item : Consultation_DrugList)
		{
			if(Item.getTheConsultation_Id()==consultationId)
			{
				result.add(Item);
			}
		}
		return result;
	}
	

	@Override
	public String getDrugNameById(long DrugId) throws Exception 
	{
		for(Drug Item : DrugList)
			if(Item.getDrug_Id()==DrugId)
				return Item.getDrug_Name();
		throw new Exception ("This Drug dosn't found in our data base");
	}

	@Override
	public Doctor findDoctorById(long DoctorId) throws Exception {
		// TODO Auto-generated method stub
		for(Doctor Item : DoctorList)
		{
			if(Item.getDoctor_Id()==DoctorId)
				return Item;
		}
		 throw new Exception ("This Doctor dosn't fount in our data base");
	}

	@Override
	public long getDrugIdByName(String drugName) throws Exception 
	{
		// TODO Auto-generated method stub
		for(Drug Item : DrugList)
		{
			if(Item.getDrug_Name().equals(drugName))
				return Item.getDrug_Id();
		}
		throw new Exception ("this drug dosn't exist in our data base");
	}

	@Override
	public HashMap<Long, List<Consultation_Drug>> getTheDetailsOfAllTheConsultation(/*long patientId,*/ArrayList<Consultation> listConsultationByPatientId) throws Exception 
	{
		long patientId = listConsultationByPatientId.get(0).getThePatient_Id();
		HashMap<Long, List<Consultation_Drug>> listResult = new HashMap<Long, List<Consultation_Drug>>();
		//ArrayList<Consultation> consultation = getConsultationListByPatientID(patientId);
		ArrayList<Consultation_Drug> drugs = new ArrayList<Consultation_Drug>();
		for(Consultation item : listConsultationByPatientId)
		{
			drugs = getDetailsConsultation(item.getConsultation_Id());
			if(drugs.size() == 0)
				drugs.add(new Consultation_Drug(-1,-1,-1,-1));
			listResult.put(item.getConsultation_Id(),drugs);
		}
		
		return listResult;
	}

	@Override
	public HashMap<Long, String> DoctorHash_Id_Name() {
		 HashMap<Long, String> result = new HashMap<Long, String>();
		 for(Doctor item : DoctorList)
		 {
			 result.put(item.getDoctor_Id(), item.getDoctor_FirstName() + " " + item.getDoctor_LastName());
		 }
		return result;
	}

	@Override
	public HashMap<Long, String> DrugHash_Id_Name() {
		 HashMap<Long, String> result = new HashMap<Long, String>();
		 for(Drug item : DrugList)
		 {
			 result.put(item.getDrug_Id(), item.getDrug_Name());
		 }
		return result;
	}


}
