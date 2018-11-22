
package model.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import entities.Consultation;
import entities.Consultation_Drug;
import entities.Doctor;
import entities.Drug;
import entities.Login;
import entities.Patient;
import model.backend.Backend;

public class DatabaseService implements Backend {
	
	private final Charset UTF8_CHARSET = Charset.forName("UTF-8"); 
	private static final String URL = "https://allodoc.herokuapp.com/api/db/";
	 
	/*String decodeUTF8(byte[] bytes) 
	{ 
		return new String(bytes, UTF8_CHARSET); 
	} 
	 
	byte[] encodeUTF8(String string) 
	{ 
		return string.getBytes(UTF8_CHARSET); 
	} */
	
	private String GET(String url) throws Exception
	{ 
		 InputStream inputStream = null; 
		 String result = ""; 
		  
		 try 
		 { 
			 HttpClient httpclient = new DefaultHttpClient(); 
			 HttpResponse httpResponse = httpclient.execute(new HttpGet(url)); 
			 inputStream = httpResponse.getEntity().getContent(); 
			 if(inputStream != null) 
			 { 
				 result = convertInputStreamToString(inputStream); 
				 byte  ptext[] = result.getBytes(); 
				 result = new String(ptext, "UTF-8"); 
			 } 
			 else 
				 result = "Did not work!"; 
		 	} 
		 catch (Exception e) 
		 { 
		   e.getMessage();
		   throw new Exception("Problem in the function GET [DatabaseService]" +result);
		 }  
		 return  result; 
	} 
	
	private String POST(String url, String jsonParamsAsString) throws Exception
	{ 
		 String result = ""; 
		 try 
		 { 
			 DefaultHttpClient httpClient = new DefaultHttpClient(); 
			 HttpPost postRequest = new HttpPost(url); 
			 StringEntity input = new StringEntity(jsonParamsAsString); 
			 input.setContentType("application/json; charset=utf-8"); 
			 postRequest.setEntity(input); 
			 HttpResponse response = httpClient.execute(postRequest); 
			 result = convertInputStreamToString(response.getEntity().getContent()); 
			 byte  ptext[] = result.getBytes(); 
			 result = new String(ptext, "UTF-8"); 
		 } 
		 catch (Exception e) 
		 { 
		   e.getMessage();
		   throw new Exception("Problem in the function POST [DatabaseService] " +result);
		 } 
		 return  result; 
	} 
	
	
	private String convertInputStreamToString(InputStream inputStream) throws IOException
	{ 
		BufferedReader bufferedReader =  
		new BufferedReader( new InputStreamReader(inputStream)); 
		String line = ""; 
		String result = ""; 
		while((line = bufferedReader.readLine()) != null) 
		 result += line; 
		inputStream.close(); 
		return  result; 
		 
	} 

	public DatabaseService() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDrugToConsultation(Consultation_Drug consultation_Drug)throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addDrugToConsultation", gson.toJson(consultation_Drug, Consultation_Drug.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addDrugToConsultation [DatabaseService] "+ result); 

	}

	@Override
	public void addConsultation(Consultation consultation) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addConsultation", gson.toJson(consultation, Consultation.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addConsultation [DatabaseService] "+ result); 

	}

	@Override
	public void addDoctor(Doctor doctor) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addDoctor", gson.toJson(doctor, Doctor.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addDoctor [DatabaseService] "+ result); 

	}

	@Override
	public void addDrug(Drug drug) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addDrug", gson.toJson(drug, Drug.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addDrug [DatabaseService] "+ result);

	}

	@Override
	public void addLogin(Login login) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addLogin", gson.toJson(login, Login.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addLogin [DatabaseService] "+ result);

	}

	@Override
	public void addPatient(Patient patient) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"addPatient", gson.toJson(patient, Patient.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems addPatient [DatabaseService] "+ result);

	}

	

	@Override
	public void deleteDrugToConsultation(long consultationId,long drugId)throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteDrugToConsultation?consultationId="+consultationId+"&drugId="+drugId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteDrugToConsultation [DatabaseService] " + result); 
		return;  

	}

	@Override
	public void deleteConsultation(long consultationId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteConsultation?consultationID="+consultationId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteConsultation [DatabaseService] " + result); 
		return; 

	}

	@Override
	public void deleteDoctor(long doctorId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteDoctor?doctorId="+doctorId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteDoctor [DatabaseService] " + result); 
		return; 

	}

	@Override
	public void deleteDrug(long drugId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteDrug?drugId="+drugId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteDrug [DatabaseService] " + result); 
		return;

	}

	@Override
	public void deleteLogin(long loginId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteLogin?loginId="+loginId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteLogin [DatabaseService] " + result); 
		return;

	}

	@Override
	public void deletePatient(long patientId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deletePatient?patientId="+patientId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deletePatient [DatabaseService] " + result); 
		return;

	}

	public void updateDrugToConsultation(Consultation_Drug consultation_Drug)throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updateDrugToConsultation", gson.toJson(consultation_Drug, Consultation_Drug.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updateDrugToConsultation [DatabaseService] "+result);
	    return;
	}

	@Override
	public void updateConsultation(Consultation consultation) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updateConsultation", gson.toJson(consultation, Consultation.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updateConsultation [DatabaseService] "+result);
	    return;

	}

	@Override
	public void updateDoctor(Doctor doctor) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updateDoctor", gson.toJson(doctor, Doctor.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updateDoctor [DatabaseService] "+result);
	    return;

	}

	@Override
	public void updateDrug(Drug drug) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updateDrug", gson.toJson(drug, Drug.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updateDrug [DatabaseService] "+result);
	    return;

	}

	@Override
	public void updatePassword(Login login) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updatePassword", gson.toJson(login, Login.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updatePassword [DatabaseService] "+result);
	    return;

	}

	@Override
	public void updatePatient(Patient patient) throws Exception 
	{
		Gson gson = new Gson(); 
	    String result = POST(URL+"updatePatient", gson.toJson(patient, Patient.class)); 
	    if (!result.equals("success")) 
	    	throw  new Exception("Connection Problems updatePatient [DatabaseService] "+result);
	    return;

	}

	

	@Override
	public ArrayList<Consultation> getConsultationListByPatientID(long patientID)throws Exception 
	{
		Gson gson; 
		Type ArrayListType = new TypeToken<ArrayList<Consultation>>(){}.getType();
		ArrayList<Consultation> temp = new ArrayList<Consultation>();
		String result = GET(URL+"getConsultationListByPatientID?patientID="+patientID); 
		gson = new Gson(); 
		temp = gson.fromJson(result,ArrayListType); 
		return temp;
	}

	@Override
	public ArrayList<Doctor> getDoctorListByPatientID(long patientID)throws Exception 
	{
		
		return null;
	}

	@Override
	public ArrayList<Drug> getDrugListByConsultationID(long consultationID)throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getDrugListByConsultationID?consultationID="+consultationID); 
		gson = new Gson(); 
		return  new ArrayList<Drug>(Arrays.asList(gson.fromJson(result,Drug[].class)));
	}

	@Override
	public ArrayList<Patient> getPatientListByDoctorID(long doctorID)throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getPatientListByDoctorID?doctorID="+doctorID); 
		gson = new Gson(); 
		return  new ArrayList<Patient>(Arrays.asList(gson.fromJson(result,Patient[].class)));
	}

	@Override
	public ArrayList<Doctor> getDoctorList() throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getDoctorList"); 
		gson = new Gson(); 
		return  new ArrayList<Doctor>(Arrays.asList(gson.fromJson(result,Doctor[].class)));
	}

	@Override
	public ArrayList<Login> getPasswordList() throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getPasswordList"); 
		gson = new Gson(); 
		return  new ArrayList<Login>(Arrays.asList(gson.fromJson(result,Login[].class)));
	}

	@Override
	public ArrayList<Patient> getPatientList() throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getPatientList"); 
		gson = new Gson(); 
		return  new ArrayList<Patient>(Arrays.asList(gson.fromJson(result,Patient[].class)));
	}

	@Override
	public ArrayList<Drug> getDrugList() throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getDrugList"); 
		gson = new Gson(); 
		return  new ArrayList<Drug>(Arrays.asList(gson.fromJson(result,Drug[].class)));
	}

	@Override
	public ArrayList<Consultation> getConsultationList() throws Exception 
	{
		Gson gson; 
		String result = GET(URL+"getConsultationList"); 
		gson = new Gson(); 
		return  new ArrayList<Consultation>(Arrays.asList(gson.fromJson(result,Consultation[].class)));
	}

	@Override
	public void setLists() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteConectorByConsultation(long consultationId)throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"deleteConectorByConsultation?consultationId="+consultationId); 
		if (!result.equals("success")) 
			  throw  new Exception("Connection Problems deleteConectorByConsultation [DatabaseService] " + result); 
		return;

	}

	@Override
	public String findDoctorName(long doctorId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"findDoctorName?doctorId="+doctorId); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems findDoctorName [DatabaseService] " + result); 
		return gson.fromJson(result, String.class);

	}


	@Override
	public boolean checkLogin(long id, String password) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"checkLogin?id="+id+"&password="+password); 
		if (result.equals("false")) 
			  throw  new Exception("Connection Problems checkLogin [DatabaseService] " + result); 
		return gson.fromJson(result, Boolean.class);

	}

	@Override
	public Patient findPatientById(long PatientId) throws Exception 
	{
		Gson gson = new Gson();
		Patient patient = new Patient();
		String result = GET(URL+"findPatientById?PatientId="+PatientId); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems findPatientById [DatabaseService] " + result); 
		patient = gson.fromJson(result, Patient.class);
		return  patient;
	}
	
	@Override
	public Doctor findDoctorById(long DoctorId) throws Exception 
	{
		Gson gson = new Gson(); 
		Doctor doctor = new Doctor();
		String result = GET(URL+"findDoctorById?DoctorId="+DoctorId); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems findDoctorById [DatabaseService] " + result); 
		
		doctor= gson.fromJson(result, Doctor.class);
	
		return doctor;
	}


	@Override
	public ArrayList<String> getNameDrugList() throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"getNameDrugList"); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems getNameDrugList [DatabaseService] " + result); 
		return new ArrayList<String>(Arrays.asList(gson.fromJson(result,String[].class)));
	}

	@Override
	public ArrayList<Consultation_Drug> getDetailsConsultation(long consultation)throws Exception 
	{/*
		Gson gson = new Gson(); 
		String result = GET(URL+"getDetailsConsultation?consultationId="+consultation); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems getDetailsConsultation [DatabaseService] " + result); 
		return new ArrayList<Consultation_Drug>(Arrays.asList(gson.fromJson(result,Consultation_Drug[].class)));*/
		return null;
	}
	

	@Override
	public String getDrugNameById(long DrugId) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"getDrugNameById?DrugId="+DrugId); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems getDrugNameById [DatabaseService] " + result); 
		return gson.fromJson(result, String.class);
	}

	
	@Override
	public long getDrugIdByName(String drugName) throws Exception 
	{
		Gson gson = new Gson(); 
		String result = GET(URL+"getDrugIdByName?drugName="+drugName); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems getDrugIdByName [DatabaseService] " + result); 
		return gson.fromJson(result, long.class);
	}

	@Override
	public HashMap<Long, List<Consultation_Drug>> getTheDetailsOfAllTheConsultation(ArrayList<Consultation> listConsultationByPatientId)throws Exception 
	{
		//HashMap<Long, List<Consultation_Drug>> ret = new  HashMap<Long, List<Consultation_Drug>>();
		Type ArrayListType = new TypeToken<ArrayList<Consultation>>(){}.getType();
		Type HashMapType = new TypeToken<HashMap<Long,List<Consultation_Drug>>>(){}.getType();
		Gson gson = new Gson(); 
		String strJson;
		strJson = gson.toJson(listConsultationByPatientId,ArrayListType);
		String result = POST(URL+"getTheDetailsOfAllTheConsultation", gson.toJson(listConsultationByPatientId,ArrayListType)); 
	    //if (!result.equals("success")) 
	    	//throw  new Exception("Connection Problems updatePatient [DatabaseService] "+ result);
	    return  gson.fromJson(result,HashMapType); 
	}

	@Override
	public HashMap<Long, String> DoctorHash_Id_Name() throws Exception 
	{
		Type HashMapType = new TypeToken<HashMap<Long,String>>(){}.getType();
		HashMap<Long,String> temp = new HashMap<Long,String>();
		Gson gson = new Gson(); 
		String result = GET(URL+"DoctorHash_Id_Name"); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems DoctorHash_Id_Name [DatabaseService] " + result); 
		temp=gson.fromJson(result,HashMapType);
		return temp;
	}

	@Override
	public HashMap<Long, String> DrugHash_Id_Name() throws Exception
	{
		Type HashMapType = new TypeToken<HashMap<Long,String>>(){}.getType();
		HashMap<Long,String> temp = new HashMap<Long,String>();
		Gson gson = new Gson(); 
		String result = GET(URL+"DrugHash_Id_Name"); 
		if (result.equals("Did not work!")) 
			  throw  new Exception("Connection Problems DrugHash_Id_Name [DatabaseService] " + result); 
		temp=gson.fromJson(result,HashMapType);
		return temp;
	}
	
	

}
