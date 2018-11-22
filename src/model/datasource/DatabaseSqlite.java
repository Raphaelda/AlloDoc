package model.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import entities.Consultation;
import entities.Consultation_Drug;
import entities.Doctor;
import entities.Drug;
import entities.Login;
import entities.Patient;
import entities.Login.Authorization;
import entities.Patient.Blood_Group;
import entities.Patient.Sex;
import model.backend.Backend;
import model.datasource.SQLNAME;

public class DatabaseSqlite extends SQLiteOpenHelper implements Backend {
	

	int ConsultationCounter=1;
	int DoctorCounter=333222111;
	int DrugCounter=1;
	int PatientCounter=333444555;
	
	public DatabaseSqlite(Context context) {
		// TODO Auto-generated constructor stub
		super(context, SQLNAME.DATABASE_NAME, null, SQLNAME.DATABASE_VERSION);
		//setLists();

	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		
		
		String CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_LOGIN + "("
				+SQLNAME.KEY_LOGIN_ID + " INTEGER NOT NULL PRIMARY KEY," 
				+SQLNAME.KEY_LOGIN_PASSWORD + " TEXT,"
				+SQLNAME.KEY_LOGIN_AUTHORIZATION + " TEXT"
			    +")";
		db.execSQL(CREATE_TABLE);
		
		
		CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_DOCTOR + "("
				+ SQLNAME.KEY_DOCTOR_ID + " INTEGER,"
				+SQLNAME.KEY_DOCTOR_FIRSTNAME +	" TEXT,"
				+SQLNAME.KEY_DOCTOR_LASTNAME + " TEXT,"
				+SQLNAME.KEY_DOCTOR_TYPE + " TEXT,"
				+SQLNAME.KEY_DOCTOR_TEL + " TEXT,"
				+SQLNAME.KEY_DOCTOR_MAIL + " TEXT,"
				+ "PRIMARY KEY(" + SQLNAME.KEY_DOCTOR_ID + "),"
				+"FOREIGN KEY ("+SQLNAME.KEY_DOCTOR_ID+") REFERENCES " + SQLNAME.TABLE_LOGIN + "(" +SQLNAME.KEY_LOGIN_ID+")"
			+")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_PATIENT + "(" 
				+SQLNAME.KEY_PATIENT_ID + " INTEGER,"
				+SQLNAME.KEY_PATIENT_FIRSTNAME + " TEXT,"
				+SQLNAME.KEY_PATIENT_LASTNAME + " TEXT,"
				+SQLNAME.KEY_PATIENT_ADRESS + " TEXT,"
				+SQLNAME.KEY_PATIENT_POSTAL_CODE + " TEXT,"
				+SQLNAME.KEY_PATIENT_CITY + " TEXT,"
				+SQLNAME.KEY_PATIENT_TEL + " TEXT,"
				+SQLNAME.KEY_PATIENT_MAIL + " TEXT,"
				+SQLNAME.KEY_PATIENT_BIRTHDATE + " TEXT,"
				+SQLNAME.KEY_PATIENT_WEIGHT + " REAL,"
				+SQLNAME.KEY_PATIENT_HEIGHT + " REAL,"
				+SQLNAME.KEY_PATIENT_SEX + " TEXT,"
			    +SQLNAME.KEY_PATIENT_BLOOD_GROUP + " TEXT, "
				+"PRIMARY KEY("+SQLNAME.KEY_PATIENT_ID+"),"
				+"FOREIGN KEY ("+SQLNAME.KEY_PATIENT_ID+") REFERENCES " + SQLNAME.TABLE_LOGIN + "("+SQLNAME.KEY_LOGIN_ID+")"
			+")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_DRUG + "("
				+SQLNAME.KEY_DRUG_ID + " INTEGER NOT NULL PRIMARY KEY,"
				+SQLNAME.KEY_DRUG_PRICE + " REAL,"
				+SQLNAME.KEY_DRUG_NAME + " TEXT,"
				+SQLNAME.KEY_DRUG_ORDONNANCE + " INTEGER CHECK("+SQLNAME.KEY_DRUG_ORDONNANCE+" == 0 ||"+ SQLNAME.KEY_DRUG_ORDONNANCE +" ==  1),"
				+SQLNAME.KEY_DRUG_QUANTITY + " INTEGER CHECK("+SQLNAME.KEY_DRUG_QUANTITY +">= 0)"
			+")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_CONSULTATION + "("
				+SQLNAME.KEY_CONSULTATION_ID + " INTEGER NOT NULL PRIMARY KEY,"
				+SQLNAME.KEY_CONSULTATION_PRICE + " REAL CHECK("+SQLNAME.KEY_CONSULTATION_PRICE +">= 0),"
				+SQLNAME.KEY_CONSULTATION_REPORT + " TEXT,"
				+SQLNAME.KEY_CONSULTATION_DATE + " TEXT,"
				+SQLNAME.KEY_CONSULTATION_HOURS + " TEXT,"
				+SQLNAME.KEY_CONSULTATION_DOCTOR_ID + " INTEGER NOT NULL,"
				+SQLNAME.KEY_CONSULTATION_PATIENT_ID + " INTEGER NOT NULL,"
				+"FOREIGN KEY ("+SQLNAME.KEY_CONSULTATION_DOCTOR_ID+") REFERENCES " + SQLNAME.TABLE_DOCTOR + "("+SQLNAME.KEY_DOCTOR_ID+"),"
				+"FOREIGN KEY ("+SQLNAME.KEY_CONSULTATION_PATIENT_ID+") REFERENCES " + SQLNAME.TABLE_PATIENT + "("+SQLNAME.KEY_PATIENT_ID+")"
			+")";
		db.execSQL(CREATE_TABLE);
		
		CREATE_TABLE = "CREATE TABLE " + SQLNAME.TABLE_CONSULTATION_DRUG + "("
				+SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID + " INTEGER NOT NULL,"
				+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID + " INTEGER NOT NULL,"
				+SQLNAME.KEY_CONSULTATIONDRUG_QUANTITY + " INTEGER NOT NULL,"
				+SQLNAME.KEY_CONSULTATIONDRUG_DURATION + " INTEGER NOT NULL,"
				+"PRIMARY KEY("+SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID+","+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID+"),"
				+"FOREIGN KEY ("+SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID+") REFERENCES " + SQLNAME.TABLE_CONSULTATION + "("+SQLNAME.KEY_CONSULTATION_ID+"),"
				+"FOREIGN KEY ("+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID+") REFERENCES " + SQLNAME.TABLE_DRUG + "("+SQLNAME.KEY_DRUG_ID+")"
			+")";
		db.execSQL(CREATE_TABLE);
		
		//setLists();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_DOCTOR);
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_PATIENT);
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_DRUG);
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_CONSULTATION);
		db.execSQL("DROP TABLE IF EXISTS " + SQLNAME.TABLE_CONSULTATION_DRUG);
		
		onCreate(db);

	}

	@Override
	public void addDrugToConsultation(Consultation_Drug consultation_Drug)throws Exception
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID, consultation_Drug.getTheConsultation_Id());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID, consultation_Drug.getTheDrug_Id());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_QUANTITY, consultation_Drug.getQuantity());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_DURATION, consultation_Drug.getDuration());
		
		db.insertWithOnConflict(SQLNAME.TABLE_CONSULTATION_DRUG, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();
	}

	@Override
	public void addConsultation(Consultation consultation) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		String SelectQuerry = "SELECT * FROM "+SQLNAME.TABLE_CONSULTATION;
		SQLiteDatabase dbr = this.getReadableDatabase();
		Cursor cursor = dbr.rawQuery(SelectQuerry, null);
		
		int count = cursor.getCount();
		int consultationId;
		if(count>0)
		{
			cursor.moveToPosition(count-1);
			consultationId = cursor.getInt(0);
			consultation.setConsultation_Id(++consultationId);
		}
		else
		{
			consultationId = ConsultationCounter;
			consultation.setConsultation_Id(consultationId);
		}
		
		
		values.put(SQLNAME.KEY_CONSULTATION_ID,consultation.getConsultation_Id());
		values.put(SQLNAME.KEY_CONSULTATION_DOCTOR_ID, consultation.getTheDoctor_Id());
		values.put(SQLNAME.KEY_CONSULTATION_HOURS, consultation.getConsultation_Hours());
		values.put(SQLNAME.KEY_CONSULTATION_DATE, consultation.getConsultation_Date());
		values.put(SQLNAME.KEY_CONSULTATION_REPORT, consultation.getConsultation_Report());
		values.put(SQLNAME.KEY_CONSULTATION_PATIENT_ID, consultation.getThePatient_Id());
		values.put(SQLNAME.KEY_CONSULTATION_PRICE, consultation.getConsultation_Price());
		
		
		db.insertWithOnConflict(SQLNAME.TABLE_CONSULTATION, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();

	}

	@Override
	public void addDoctor(Doctor doctor) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		String SelectQuerry = "SELECT * FROM "+SQLNAME.TABLE_DOCTOR;
		SQLiteDatabase dbr = this.getReadableDatabase();
		Cursor cursor = dbr.rawQuery(SelectQuerry, null);
		
		int count = cursor.getCount();
		int doctorId;
		if(count>0)
		{
			cursor.moveToPosition(count-1);
			doctorId = cursor.getInt(0);
			doctor.setDoctor_Id(++doctorId);
		}
		else
		{
			doctorId = DoctorCounter;
			doctor.setDoctor_Id(doctorId);
		}
		
		
		
		values.put(SQLNAME.KEY_DOCTOR_ID,doctor.getDoctor_Id());
		values.put(SQLNAME.KEY_DOCTOR_FIRSTNAME,doctor.getDoctor_FirstName());
		values.put(SQLNAME.KEY_DOCTOR_LASTNAME, doctor.getDoctor_LastName());
		values.put(SQLNAME.KEY_DOCTOR_TYPE, doctor.getDoctor_Type());
		values.put(SQLNAME.KEY_DOCTOR_TEL, doctor.getDoctor_Tel());
		values.put(SQLNAME.KEY_DOCTOR_MAIL, doctor.getDoctor_Mail());
		
		db.insertWithOnConflict(SQLNAME.TABLE_DOCTOR, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();

	}

	@Override
	public void addDrug(Drug drug) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		String SelectQuerry = "SELECT * FROM "+SQLNAME.TABLE_DRUG;
		SQLiteDatabase dbr = this.getReadableDatabase();
		Cursor cursor = dbr.rawQuery(SelectQuerry, null);
		
		int count = cursor.getCount();
		int drugId;
		if(count>0)
		{
			cursor.moveToPosition(count-1);
			drugId = cursor.getInt(0);
			drug.setDrug_Id(++drugId);
		}
		else
		{
			drugId = DrugCounter;
			drug.setDrug_Id(drugId);
		}
		
		values.put(SQLNAME.KEY_DRUG_ID,DrugCounter++);
		values.put(SQLNAME.KEY_DRUG_NAME,drug.getDrug_Name());
		if(drug.getWhith_Ordinance().toString().equals("true"))
			values.put(SQLNAME.KEY_DRUG_ORDONNANCE,1);
		if(drug.getWhith_Ordinance().toString().equals("false"))
			values.put(SQLNAME.KEY_DRUG_ORDONNANCE,0);
		values.put(SQLNAME.KEY_DRUG_PRICE,drug.getDrug_Price());
		values.put(SQLNAME.KEY_DRUG_QUANTITY,(int)drug.getDrug_BoxQuantity());
		
		
		db.insertWithOnConflict(SQLNAME.TABLE_DRUG, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();	

	}

	@Override
	public void addLogin(Login login) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_LOGIN_ID,login.getLogin());
		values.put(SQLNAME.KEY_LOGIN_PASSWORD,login.getPassword());
		values.put(SQLNAME.KEY_LOGIN_AUTHORIZATION,login.getAuthorization().toString());
		
		
		db.insertWithOnConflict(SQLNAME.TABLE_LOGIN, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();	

	}

		

	@Override
	public void addPatient(Patient patient) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		String SelectQuerry = "SELECT * FROM "+SQLNAME.TABLE_PATIENT;
		SQLiteDatabase dbr = this.getReadableDatabase();
		Cursor cursor = dbr.rawQuery(SelectQuerry, null);
		
		int count = cursor.getCount();
		int patientId;
		if(count>0)
		{
			cursor.moveToPosition(count-1);
			patientId = cursor.getInt(0);
			patient.setPatient_Id(++patientId);
		}
		else
		{
			patientId = PatientCounter;
			patient.setPatient_Id(patientId);
		}
		
		values.put(SQLNAME.KEY_PATIENT_ID,patient.getPatient_Id());
		values.put(SQLNAME.KEY_PATIENT_FIRSTNAME,patient.getPatient_FirstName());
		values.put(SQLNAME.KEY_PATIENT_LASTNAME,patient.getPatient_LastName());
		values.put(SQLNAME.KEY_PATIENT_ADRESS,patient.getPatient_Adress());
		values.put(SQLNAME.KEY_PATIENT_POSTAL_CODE,patient.getPatient_Postal_Code());
		values.put(SQLNAME.KEY_PATIENT_CITY,patient.getPatient_City());
		values.put(SQLNAME.KEY_PATIENT_TEL, patient.getPatient_Tel());
		values.put(SQLNAME.KEY_PATIENT_MAIL, patient.getPatient_Mail());
		values.put(SQLNAME.KEY_PATIENT_BIRTHDATE, patient.getPatient_Birthdate());
		values.put(SQLNAME.KEY_PATIENT_WEIGHT,patient.getPatient_Weight());
		values.put(SQLNAME.KEY_PATIENT_HEIGHT,patient.getPatient_Height());
		values.put(SQLNAME.KEY_PATIENT_SEX, patient.getPatient_Sex().toString());
		values.put(SQLNAME.KEY_PATIENT_BLOOD_GROUP,patient.getPatient_BloodGroup().toString());

				
		db.insertWithOnConflict(SQLNAME.TABLE_PATIENT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		db.close();	

	}

	@Override
	public void deleteDrugToConsultation(long consultationId, long drugId)throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLNAME.TABLE_CONSULTATION_DRUG,SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID + " = ? AND "
				+ SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID +" = ?" , 
				new String[] {String.valueOf(consultationId),String.valueOf(drugId)});

	}

	@Override
	public void deleteConsultation(long consultationId) throws Exception 
	{
		//delete all the drugs of this consultation
		deleteConectorByConsultation(consultationId);
		//delete the consultation 
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLNAME.TABLE_CONSULTATION,SQLNAME.KEY_CONSULTATION_ID + " = ? ", 
				new String[] {String.valueOf(consultationId)});
		

	}

	@Override
	public void deleteDoctor(long doctorId) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLNAME.TABLE_DOCTOR,SQLNAME.KEY_DOCTOR_ID + " = ? ", 
				new String[] {String.valueOf(doctorId)});

	}

	@Override
	public void deleteDrug(long drugId) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(SQLNAME.TABLE_DRUG,SQLNAME.KEY_DRUG_ID + " = ? ", 
			new String[] {String.valueOf(drugId)});

	}

	@Override
	public void deleteLogin(long loginId) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(SQLNAME.TABLE_LOGIN,SQLNAME.KEY_LOGIN_ID + " = ? ", 
			new String[] {String.valueOf(loginId)});

	}

	@Override
	public void deletePatient(long patientId) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(SQLNAME.TABLE_PATIENT,SQLNAME.KEY_PATIENT_ID + " = ? ", 
			new String[] {String.valueOf(patientId)});

	}

	@Override
	public void updateDrugToConsultation(Consultation_Drug consultation_Drug)throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID, consultation_Drug.getTheConsultation_Id());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID, consultation_Drug.getTheDrug_Id());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_QUANTITY, consultation_Drug.getQuantity());
		values.put(SQLNAME.KEY_CONSULTATIONDRUG_DURATION, consultation_Drug.getDuration());
		
		db.update(SQLNAME.TABLE_CONSULTATION_DRUG,values,SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID + " = ? AND " 
				+ SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID + " = ?",new String[]{String.valueOf(consultation_Drug.getTheConsultation_Id()),String.valueOf(consultation_Drug.getTheDrug_Id())});
		
		db.close();

	}

	@Override
	public void updateConsultation(Consultation consultation) throws Exception 
	{
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_CONSULTATION_ID,consultation.getConsultation_Id());
		values.put(SQLNAME.KEY_CONSULTATION_DOCTOR_ID, consultation.getTheDoctor_Id());
		values.put(SQLNAME.KEY_CONSULTATION_HOURS, consultation.getConsultation_Hours());
		values.put(SQLNAME.KEY_CONSULTATION_DATE, consultation.getConsultation_Date());
		values.put(SQLNAME.KEY_CONSULTATION_REPORT, consultation.getConsultation_Report());
		values.put(SQLNAME.KEY_CONSULTATION_PATIENT_ID, consultation.getThePatient_Id());
		values.put(SQLNAME.KEY_CONSULTATION_PRICE, consultation.getConsultation_Price());
		
		db.update(SQLNAME.TABLE_CONSULTATION,values,SQLNAME.KEY_CONSULTATION_ID + " = ? " 
				,new String[]{String.valueOf(consultation.getConsultation_Id())});
		
		db.close();

	}

	@Override
	public void updateDoctor(Doctor doctor) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_DOCTOR_ID,doctor.getDoctor_Id());
		values.put(SQLNAME.KEY_DOCTOR_FIRSTNAME,doctor.getDoctor_FirstName());
		values.put(SQLNAME.KEY_DOCTOR_LASTNAME, doctor.getDoctor_LastName());
		values.put(SQLNAME.KEY_DOCTOR_TYPE, doctor.getDoctor_Type());
		values.put(SQLNAME.KEY_DOCTOR_TEL, doctor.getDoctor_Tel());
		values.put(SQLNAME.KEY_DOCTOR_MAIL, doctor.getDoctor_Mail());
		
		db.update(SQLNAME.TABLE_DOCTOR,values,SQLNAME.KEY_DOCTOR_ID + " = ? " 
				,new String[]{String.valueOf(doctor.getDoctor_Id())});
		
		db.close();

	}

	@Override
	public void updateDrug(Drug drug) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_DRUG_ID,DrugCounter++);
		values.put(SQLNAME.KEY_DRUG_NAME,drug.getDrug_Name());
		if(drug.getWhith_Ordinance().toString().equals("true"))
			values.put(SQLNAME.KEY_DRUG_ORDONNANCE,1);
		if(drug.getWhith_Ordinance().toString().equals("false"))
			values.put(SQLNAME.KEY_DRUG_ORDONNANCE,0);
		values.put(SQLNAME.KEY_DRUG_PRICE,drug.getDrug_Price());
		values.put(SQLNAME.KEY_DRUG_QUANTITY,(int)drug.getDrug_BoxQuantity());
		
		db.update(SQLNAME.TABLE_DRUG,values,SQLNAME.KEY_DRUG_ID + " = ? " 
				,new String[]{String.valueOf(drug.getDrug_Id())});
		
		db.close();

	}

	@Override
	public void updatePassword(Login login) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_LOGIN_ID,login.getLogin());
		values.put(SQLNAME.KEY_LOGIN_PASSWORD,login.getPassword());
		values.put(SQLNAME.KEY_LOGIN_AUTHORIZATION,login.getAuthorization().toString());
		
		db.update(SQLNAME.TABLE_LOGIN,values,SQLNAME.KEY_LOGIN_ID + " = ? " 
				,new String[]{String.valueOf(login.getLogin())});
		
		db.close();

	}

	@Override
	public void updatePatient(Patient patient) throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(SQLNAME.KEY_PATIENT_ID,patient.getPatient_Id());
		values.put(SQLNAME.KEY_PATIENT_FIRSTNAME,patient.getPatient_FirstName());
		values.put(SQLNAME.KEY_PATIENT_LASTNAME,patient.getPatient_LastName());
		values.put(SQLNAME.KEY_PATIENT_ADRESS,patient.getPatient_Adress());
		values.put(SQLNAME.KEY_PATIENT_POSTAL_CODE,patient.getPatient_Postal_Code());
		values.put(SQLNAME.KEY_PATIENT_CITY,patient.getPatient_City());
		values.put(SQLNAME.KEY_PATIENT_TEL, patient.getPatient_Tel());
		values.put(SQLNAME.KEY_PATIENT_MAIL, patient.getPatient_Mail());
		values.put(SQLNAME.KEY_PATIENT_BIRTHDATE, patient.getPatient_Birthdate());
		values.put(SQLNAME.KEY_PATIENT_WEIGHT,patient.getPatient_Weight());
		values.put(SQLNAME.KEY_PATIENT_HEIGHT,patient.getPatient_Height());
		values.put(SQLNAME.KEY_PATIENT_SEX, patient.getPatient_Sex().toString());
		values.put(SQLNAME.KEY_PATIENT_BLOOD_GROUP,patient.getPatient_BloodGroup().toString());
		
		db.update(SQLNAME.TABLE_PATIENT,values,SQLNAME.KEY_PATIENT_ID + " = ? " 
				,new String[]{String.valueOf(patient.getPatient_Id())});
		
		db.close();

		

	}

	@Override
	public ArrayList<Consultation> getConsultationListByPatientID(long patientID)throws Exception 
	{
		ArrayList<Consultation> result = new ArrayList<Consultation>();
		String SelectQuerry = "SELECT * FROM "+SQLNAME.TABLE_CONSULTATION+" WHERE "+SQLNAME.KEY_CONSULTATION_PATIENT_ID+" = "+patientID;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Consultation consultation = new Consultation();
				consultation.setConsultation_Id(cursor.getLong(0));
				consultation.setConsultation_Price(cursor.getFloat(1));
				consultation.setConsultation_Report(cursor.getString(2));
				consultation.setConsultation_Date(cursor.getString(3));
				consultation.setConsultation_Hours(cursor.getString(4));
				consultation.setTheDoctor_Id(cursor.getLong(5));
				consultation.setThePatient_Id(cursor.getLong(6));
				
				result.add(consultation);
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no consultation for the patient id "+patientID+" in our dabase SQLITE [getConsultationListByPatientID]");
		
	}

	@Override//on lutilise pas je ne l'implemente pa maintenant
	public ArrayList<Doctor> getDoctorListByPatientID(long patientID)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Drug> getDrugListByConsultationID(long consultationID) throws Exception 
	{
		ArrayList<Drug> result = new ArrayList<Drug>();
		String SelectQuerry = "SELECT * " +
							  " FROM " + SQLNAME.TABLE_DRUG +" tdrug, (SELECT tconsult_drug."+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID+
							  										  " FROM "+SQLNAME.TABLE_CONSULTATION_DRUG+" tconsult_drug "+
							  										  " WHERE tconsult_drug."+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID+" = "+consultationID+")tcons_drug"+ //peut etre j'auais du mettre SQLNAME.KEY_CONSULTATIONDRUG_CONSULTATION_ID
							  " WHERE tdrug."+SQLNAME.KEY_DRUG_ID+" = tcons_drug."+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID;
		
		//a la facon tutoriel
		/*String SelectQuerry = "SELECT * "+
							  " FROM "+SQLNAME.TABLE_DRUG+" tdrug,"+SQLNAME.TABLE_CONSULTATION_DRUG+" tconsul_drug "+
							  " WHERE tdrug."+SQLNAME.KEY_DRUG_ID+" = tconsul_drug."+SQLNAME.KEY_CONSULTATIONDRUG_DRUG_ID+
							  		  " AND tconsul_drug."+SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID+" = "+consultationID;*/
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Drug drug = new Drug();
				drug.setDrug_Id(cursor.getLong(0));
				drug.setDrug_Price(cursor.getFloat(1));
				drug.setDrug_Name(cursor.getString(2));
				drug.setWhith_Ordinance(Boolean.valueOf(String.valueOf(cursor.getInt(3)))); //it's not so good
				drug.setDrug_BoxQuantity(cursor.getShort(4));
				result.add(drug);
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no prescription for the consultation id "+consultationID+"SQLIte [getDrugListByConsultationID]");
	}

	@Override
	public ArrayList<Patient> getPatientListByDoctorID(long doctorID)throws Exception 
	{
		ArrayList<Patient> result = new ArrayList<Patient>();
		String SelectQuerry = "SELECT * " +
							  " FROM " + SQLNAME.TABLE_PATIENT +" tpatient, (SELECT tconsultation."+SQLNAME.KEY_CONSULTATION_PATIENT_ID+
							  												" FROM "+SQLNAME.TABLE_CONSULTATION+" tconsultation "+
							  												" WHERE tconsultation."+SQLNAME.KEY_CONSULTATION_DOCTOR_ID +" = " +doctorID+")tconsult"+
							  " WHERE tpatient."+SQLNAME.KEY_PATIENT_ID+" = tconsult."+SQLNAME.KEY_CONSULTATION_PATIENT_ID;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Patient patient = new Patient();
				patient.setPatient_Id(cursor.getLong(0));
				patient.setPatient_FirstName(cursor.getString(1));
				patient.setPatient_LastName(cursor.getString(2));
				patient.setPatient_Adress(cursor.getString(3));
				patient.setPatient_Postal_Code(cursor.getString(4));
				patient.setCity(cursor.getString(5));
				patient.setPatient_Tel(cursor.getString(6));
				patient.setPatient_Mail(cursor.getString(7));
				patient.setPatient_Birthdate(cursor.getString(8));
				patient.setPatient_Weight(cursor.getFloat(9));
				patient.setPatient_Height(cursor.getFloat(10));
				if(cursor.getString(11).equals("Masculin"))
					  patient.setPatient_Sex(Patient.Sex.M);
					if(cursor.getString(11).equals("Feminin"))
						patient.setPatient_Sex(Patient.Sex.F);
					if(!cursor.getString(11).equals("Masculin") && !cursor.getString(11).equals("Feminin"))
						throw new Exception("the sex of the patient is illegal SQLITE [getPatientListByDoctorID]");
					if(cursor.getString(12).equals("O+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Opos);
					if(cursor.getString(12).equals("O-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Oneg);
					if(cursor.getString(12).equals("A+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.ABpos);
					if(cursor.getString(12).equals("A-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Aneg);
					if(cursor.getString(12).equals("B+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Bpos);
					if(cursor.getString(12).equals("B-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Bneg);
					if(cursor.getString(12).equals("AB+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.ABpos);
					if(cursor.getString(12).equals("AB-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.ABneg);
					else
						throw new Exception("the blood group of the patient is illegal SQLITE [getPatientListByDoctorID]");
												
				
				result.add(patient);
				
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no patient for the doctor id "+ doctorID +" in our dabase SQLITE [getPatientListByDoctorID]");

	}

	@Override
	public ArrayList<Doctor> getDoctorList() throws Exception {
		ArrayList<Doctor> result = new ArrayList<Doctor>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_DOCTOR;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{	
				Doctor doctor = new Doctor();
				doctor.setDoctor_Id(cursor.getLong(0));
				doctor.setDoctor_FirstName(cursor.getString(1));
				doctor.setDoctor_LastName(cursor.getString(2));
				doctor.setDoctor_Type(cursor.getString(3));
				doctor.setDoctor_Tel(cursor.getString(4));
				doctor.setDoctor_Mail(cursor.getString(5));
				
				result.add(doctor);
			}while(cursor.moveToNext());
			return result;
		}
		throw new Exception("there is no doctor in our dabase SQLITE [getDoctorList]");
	}

	@Override
	public ArrayList<Login> getPasswordList() throws Exception {
		ArrayList<Login> result = new ArrayList<Login>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_LOGIN;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Login login = new Login();
				login.setLogin(cursor.getLong(0));
				login.setPassword(cursor.getString(1));
				login.setAuthorization(Login.Authorization.valueOf(cursor.getString(2)));
				
				result.add(login);
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no patient in our dabase SQLITE [getPasswordList]");
	}


	@Override
	public ArrayList<Patient> getPatientList() throws Exception {
		ArrayList<Patient> result = new ArrayList<Patient>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_PATIENT;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Patient patient = new Patient();
				patient.setPatient_Id(cursor.getLong(0));
				patient.setPatient_FirstName(cursor.getString(1));
				patient.setPatient_LastName(cursor.getString(2));
				patient.setPatient_Adress(cursor.getString(3));
				patient.setPatient_Postal_Code(cursor.getString(4));
				patient.setCity(cursor.getString(5));
				patient.setPatient_Tel(cursor.getString(6));
				patient.setPatient_Mail(cursor.getString(7));
				patient.setPatient_Birthdate(cursor.getString(8));
				patient.setPatient_Weight(cursor.getFloat(9));
				patient.setPatient_Height(cursor.getFloat(10));
				if(cursor.getString(11).equals("Masculin"))
				  patient.setPatient_Sex(Patient.Sex.M);
				if(cursor.getString(11).equals("Feminin"))
					patient.setPatient_Sex(Patient.Sex.F);
				if(!cursor.getString(11).equals("Masculin") && !cursor.getString(11).equals("Feminin"))
					throw new Exception("the sex of the patient is illegal SQLITE [getPatientList]");
				if(cursor.getString(12).equals("O+"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Opos);
				else if(cursor.getString(12).equals("O-"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Oneg);
				else if(cursor.getString(12).equals("A+"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Apos);
				else if(cursor.getString(12).equals("A-"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Aneg);
				else if(cursor.getString(12).equals("B+"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Bpos);
				else if(cursor.getString(12).equals("B-"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.Bneg);
				else if(cursor.getString(12).equals("AB+"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.ABpos);
				else if(cursor.getString(12).equals("AB-"))
					patient.setPatient_BloodGroup(Patient.Blood_Group.ABneg);
				else
					throw new Exception("the blood group of the patient is illegal SQLITE [getPatientList]");
											
				result.add(patient);
				
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no patient in our dabase SQLITE [getPatientList]");
	}


	@Override
	public ArrayList<Drug> getDrugList() throws Exception 
	{
		ArrayList<Drug> result = new ArrayList<Drug>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_DRUG ;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Drug drug = new Drug();
				drug.setDrug_Id(cursor.getLong(0));
				drug.setDrug_Price(cursor.getFloat(1));
				drug.setDrug_Name(cursor.getString(2));
				if(cursor.getInt(3)==1)
					drug.setWhith_Ordinance(true);
				if(cursor.getInt(3)==0)
					drug.setWhith_Ordinance(false);
				drug.setDrug_BoxQuantity(cursor.getShort(4));
				result.add(drug);
			}while(cursor.moveToNext());
			return result;
		}
		
		
		throw new Exception("there is no drug in our dabase SQLITE [getDrugList]");
	}
	
	@Override
	public ArrayList<Consultation> getConsultationList() throws Exception 
	{
		ArrayList<Consultation> result = new ArrayList<Consultation>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_CONSULTATION ;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Consultation consultation = new Consultation();
				consultation.setConsultation_Id(cursor.getLong(0));
				consultation.setConsultation_Price(cursor.getFloat(1));
				consultation.setConsultation_Report(cursor.getString(2));
				consultation.setConsultation_Date(cursor.getString(3));
				consultation.setConsultation_Hours(cursor.getString(4));
				consultation.setTheDoctor_Id(cursor.getLong(5));
				consultation.setThePatient_Id(cursor.getLong(6));
				
				result.add(consultation);
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no consultation in our dabase SQLITE [getConsultationList]");
		
	}

	@Override
	public void setLists() 
	{
		try
		{
			
			
			
		 	
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
		 addPatient(new Patient("Larry","Page","25 rehov havaad haleoumi","94109","Jerusalem","0536789219","Google@gmail.com","08/10/1975",(float)80,(float)190,Sex.M,Blood_Group.ABneg));
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
        
		 
		 
		 ArrayList <Doctor> DoctorList = getDoctorList();
		 ArrayList <Drug> DrugList = getDrugList();
		 ArrayList <Patient> PatientList = getPatientList();
		
		 addConsultation(new Consultation(DoctorList.get(0).getDoctor_Id(),PatientList.get(0).getPatient_Id(),(float)150,"Angine"));
		 addConsultation(new Consultation(DoctorList.get(1).getDoctor_Id(),PatientList.get(1).getPatient_Id(),(float)200,"Gastro"));
		 addConsultation(new Consultation(DoctorList.get(2).getDoctor_Id(),PatientList.get(2).getPatient_Id(),(float)100,"Fievre"));
		 addConsultation(new Consultation(DoctorList.get(0).getDoctor_Id(),PatientList.get(3).getPatient_Id(),(float)150,"Sinusite"));
		 addConsultation(new Consultation(DoctorList.get(1).getDoctor_Id(),PatientList.get(4).getPatient_Id(),(float)100,"Mal de dos"));
		 addConsultation(new Consultation(DoctorList.get(2).getDoctor_Id(),PatientList.get(5).getPatient_Id(),(float)180,"Bronchite"));
       
		 addLogin(new Login(DoctorList.get(0).getDoctor_Id(),"daniel",Authorization.level2));
		 addLogin(new Login(DoctorList.get(1).getDoctor_Id(),"raphael",Authorization.level2));
		 addLogin(new Login(DoctorList.get(2).getDoctor_Id(),"lea",Authorization.level2));
		 
		 ArrayList <Consultation> ConsultationList = getConsultationList();
        
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
	public void deleteConectorByConsultation(long consultationId)throws Exception 
	{
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SQLNAME.TABLE_CONSULTATION_DRUG, " WHERE "+SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID +" = ?",new String[]{String.valueOf(consultationId)});
	}

	@Override
	public String findDoctorName(long doctorId) throws Exception 
	{
		String fname,lname;
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DOCTOR_FIRSTNAME+","+SQLNAME.KEY_DOCTOR_LASTNAME +" FROM " + SQLNAME.TABLE_DOCTOR + " WHERE " + SQLNAME.KEY_DOCTOR_ID + " = " + doctorId;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				fname = cursor.getString(0);
				lname = cursor.getString(1);
				
			}while(cursor.moveToNext());
			
			return fname +" " + lname;
		}
		
		throw new Exception("there is no patient " + doctorId + " in our dabase SQLITE [findDoctorName]" );
	}

	

	@Override
	public boolean checkLogin(long id, String password) throws Exception 
	{
		long ID;
		String passw;
		//String SelectQuerry = "SELECT " + SQLNAME.KEY_LOGIN_ID + "," +SQLNAME.KEY_LOGIN_PASSWORD+" FROM " + SQLNAME.TABLE_LOGIN + " WHERE " + SQLNAME.KEY_LOGIN_ID +" = "+ id +" AND "+SQLNAME.KEY_LOGIN_PASSWORD +" = "+password;
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_LOGIN + "  tlogin  WHERE  tlogin." + SQLNAME.KEY_LOGIN_ID +" = "+ id +" AND  tlogin."+SQLNAME.KEY_LOGIN_PASSWORD + " =  '"+ password +"'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				ID = cursor.getLong(0);
				passw = cursor.getString(1);
			}while(cursor.moveToNext());
			
			if(ID==id && passw.equals(password))
				return true;
			else
				return false;
		}
		
		throw new Exception("the password doesn't match with the login [checkLogin]");
	}

	
	@Override
	public Patient findPatientById(long PatientId) throws Exception {
		Patient patient = new Patient();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_PATIENT + " WHERE " + SQLNAME.KEY_PATIENT_ID +" = "+ PatientId ;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		String s;
		
		if(cursor.moveToFirst())
		{
			do
			{
				patient.setPatient_Id(cursor.getLong(0));
				patient.setPatient_FirstName(s=cursor.getString(1));
				patient.setPatient_LastName(s=cursor.getString(2));
				patient.setPatient_Adress(s=cursor.getString(3));
				patient.setPatient_Postal_Code(s=cursor.getString(4));
				patient.setCity(s=cursor.getString(5));
				patient.setPatient_Tel(s=cursor.getString(6));
				patient.setPatient_Mail(s=cursor.getString(7));
				patient.setPatient_Birthdate(s=cursor.getString(8));
				patient.setPatient_Weight(cursor.getFloat(9));
				patient.setPatient_Height(cursor.getFloat(10));
				if(cursor.getString(11).equals("Masculin"))
					  patient.setPatient_Sex(Patient.Sex.M);
					if(cursor.getString(11).equals("Feminin"))
						patient.setPatient_Sex(Patient.Sex.F);
					if(!cursor.getString(11).equals("Masculin") && !cursor.getString(11).equals("Feminin"))
						throw new Exception("the sex of the patient is illegal SQLITE [getPatientList]");
					if(cursor.getString(12).equals("O+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Opos);
					else if(cursor.getString(12).equals("O-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Oneg);
					else if(cursor.getString(12).equals("A+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Apos);
					else if(cursor.getString(12).equals("A-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Aneg);
					else if(cursor.getString(12).equals("B+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Bpos);
					else if(cursor.getString(12).equals("B-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.Bneg);
					else if(cursor.getString(12).equals("AB+"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.ABpos);
					else if(cursor.getString(12).equals("AB-"))
						patient.setPatient_BloodGroup(Patient.Blood_Group.ABneg);
					else
						throw new Exception("the blood group of the patient is illegal SQLITE [findPatientById]");
												
				
			}while(cursor.moveToNext());
			return patient;
		}
		
			 throw new Exception("the  patient whith id " +PatientId+ " in our dabase SQLITE [findPatientById]");
		
	}

	@Override
	public ArrayList<String> getNameDrugList() throws Exception {
		ArrayList<String> result = new ArrayList<String>();
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DRUG_NAME +" FROM " + SQLNAME.TABLE_DRUG;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		String s;
		if(cursor.moveToFirst())
		{
			do
			{
				result.add(s=cursor.getString(0));
				
			}while(cursor.moveToNext());
			return result;
		}
		
		throw new Exception("there is no drug  in our dabase SQLITE [getNameDrugList]");
		
	}

	@Override
	public ArrayList<Consultation_Drug> getDetailsConsultation(long consultationId)throws Exception 
	{
		ArrayList<Consultation_Drug> result = new ArrayList<Consultation_Drug>();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_CONSULTATION_DRUG + " WHERE " + SQLNAME.KEY_CONSULTATIONDRUG_CONSULTAION_ID + " = " + consultationId;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				Consultation_Drug item = new Consultation_Drug();
				item.setTheConsultation_Id(cursor.getLong(0));
				item.setTheDrug_Id(cursor.getLong(1));
				item.setQuantity(cursor.getInt(2));
				item.setDuration(cursor.getInt(3));
				result.add(item);
			}while(cursor.moveToNext());
			
		}
		if(result.size()>=0)
			return result;
		else 
		throw new Exception("there is no prescription for the consultationId " + consultationId  +" in our dabase SQLITE [getDetailsConsultation]" );
		
	}

	@Override
	public String getDrugNameById(long DrugId) throws Exception {
		String Name;
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DRUG_NAME +" FROM " + SQLNAME.TABLE_DRUG + " WHERE " + SQLNAME.KEY_DRUG_ID + " = " + DrugId;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				return Name=cursor.getString(2);
				
			}while(cursor.moveToNext());
		}
		
		throw new Exception("there is no drug " + DrugId + " in our dabase SQLITE [getDrugNameById]" );
	}

	@Override
	public Doctor findDoctorById(long DoctorId) throws Exception 
	{
		Doctor doctor = new Doctor();
		String SelectQuerry = "SELECT * FROM " + SQLNAME.TABLE_DOCTOR + " WHERE " + SQLNAME.KEY_DOCTOR_ID + " = " + DoctorId;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				doctor.setDoctor_Id(cursor.getLong(0));
				doctor.setDoctor_FirstName(cursor.getString(1));
				doctor.setDoctor_LastName(cursor.getString(2));
				doctor.setDoctor_Type(cursor.getString(3));
				doctor.setDoctor_Tel(cursor.getString(4));
				doctor.setDoctor_Mail(cursor.getString(5));
				
			}while(cursor.moveToNext());
		}
		return doctor;
	}

	@Override
	public long getDrugIdByName(String drugName) throws Exception {
		long id;
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DRUG_ID +" FROM " + SQLNAME.TABLE_DRUG + " WHERE " + SQLNAME.KEY_DRUG_NAME + " =  '" + drugName+"'";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				return id=cursor.getLong(0);
				
			}while(cursor.moveToNext());
		}
		
		throw new Exception("there is no drug " + drugName + " in our dabase SQLITE [getDrugIdByName]" );
	}

	@Override
	public HashMap<Long, List<Consultation_Drug>> getTheDetailsOfAllTheConsultation(ArrayList<Consultation> listConsultationByPatientId) throws Exception 
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
		if(listResult.size()>0)
			return listResult;
		else
			throw new Exception("there is no consultation for the pstient id "+patientId+" SQLITE [getTheDetailsOfAllTheConsultation]" );
	}

	@Override
	public HashMap<Long, String> DoctorHash_Id_Name() 
	{
		HashMap<Long, String> result = new HashMap<Long, String>();
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DOCTOR_ID+","+SQLNAME.KEY_DOCTOR_FIRSTNAME+","+SQLNAME.KEY_DOCTOR_LASTNAME +
							  " FROM " + SQLNAME.TABLE_DOCTOR;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				result.put(cursor.getLong(0),cursor.getString(1)+" "+cursor.getString(2));
			
			}while(cursor.moveToNext());
		}
				
		return result;
		
	}

	@Override
	public HashMap<Long, String> DrugHash_Id_Name() 
	{
		
		HashMap<Long, String> result = new HashMap<Long, String>();
		String SelectQuerry = "SELECT "+SQLNAME.KEY_DRUG_ID+","+SQLNAME.KEY_DRUG_NAME+
				              " FROM " + SQLNAME.TABLE_DRUG;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(SelectQuerry, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				result.put(cursor.getLong(0),cursor.getString(1));
			
			}while(cursor.moveToNext());
		}
				
		return result;
	}
	
	
	
	
	public ArrayList<Cursor> getData(String Query){
		//get writable database
		SQLiteDatabase sqlDB = this.getWritableDatabase();
		String[] columns = new String[] { "mesage" };
		//an array list of cursor to save two cursors one has results from the query 
		//other cursor stores error message if any errors are triggered
		ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
		MatrixCursor Cursor2= new MatrixCursor(columns);
		alc.add(null);
		alc.add(null);
		
		
		try{
			String maxQuery = Query ;
			//execute the query results will be save in Cursor c
			Cursor c = sqlDB.rawQuery(maxQuery, null);
			

			//add value to cursor2
			Cursor2.addRow(new Object[] { "Success" });
			
			alc.set(1,Cursor2);
			if (null != c && c.getCount() > 0) {

				
				alc.set(0,c);
				c.moveToFirst();
				
				return alc ;
			}
			return alc;
		} catch(SQLException sqlEx){
			Log.d("printing exception", sqlEx.getMessage());
			//if any exceptions are triggered save the error message to cursor an return the arraylist
			Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
			alc.set(1,Cursor2);
			return alc;
		} catch(Exception ex){

			Log.d("printing exception", ex.getMessage());

			//if any exceptions are triggered save the error message to cursor an return the arraylist
			Cursor2.addRow(new Object[] { ""+ex.getMessage() });
			alc.set(1,Cursor2);
			return alc;
		}

		
	}

}
