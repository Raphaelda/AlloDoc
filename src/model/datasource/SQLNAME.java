package model.datasource;

public class SQLNAME 
{
	public static final int DATABASE_VERSION = 1;
   
   public static final String DATABASE_NAME = "AlloDoc_DB.db";
   
   public static final String TABLE_DOCTOR = "doctor_table";
   public static final String TABLE_PATIENT = "patient_table";
   public static final String TABLE_DRUG = "drug_table";
   public static final String TABLE_LOGIN = "login_table";
   public static final String TABLE_CONSULTATION = "consultation_table";
   public static final String TABLE_CONSULTATION_DRUG = "consultation_drug_table";
   
   public static final String KEY_DOCTOR_ID = "doctor_id";
   public static final String KEY_DOCTOR_FIRSTNAME = "doctor_firstname";
   public static final String KEY_DOCTOR_LASTNAME = "doctor_lastname";
   public static final String KEY_DOCTOR_TYPE = "doctor_type";
   public static final String KEY_DOCTOR_TEL = "doctor_tel";
   public static final String KEY_DOCTOR_MAIL = "doctor_mail";
   
   public static final String KEY_PATIENT_ID = "patient_id";
   public static final String KEY_PATIENT_FIRSTNAME = "patient_firstname";
   public static final String KEY_PATIENT_LASTNAME = "patient_lastname";
   public static final String KEY_PATIENT_ADRESS = "patient_adress";
   public static final String KEY_PATIENT_POSTAL_CODE = "patient_postal_code";
   public static final String KEY_PATIENT_CITY = "patient_city";
   public static final String KEY_PATIENT_TEL = "patient_tel";
   public static final String KEY_PATIENT_MAIL = "patient_mail";
   public static final String KEY_PATIENT_BIRTHDATE = "patient_birthdate";
   public static final String KEY_PATIENT_WEIGHT = "patient_weight";
   public static final String KEY_PATIENT_HEIGHT = "patient_heigth";
   public static final String KEY_PATIENT_SEX = "patient_sex";
   public static final String KEY_PATIENT_BLOOD_GROUP = "patient_blood_group";
   
   public static final String KEY_LOGIN_ID = "login_id";
   public static final String KEY_LOGIN_PASSWORD = "login_password";
   public static final String KEY_LOGIN_AUTHORIZATION = "login_authorization";
   
   public static final String KEY_DRUG_ID = "drug_id";
   public static final String KEY_DRUG_PRICE = "drug_price";
   public static final String KEY_DRUG_NAME = "drug_name";
   public static final String KEY_DRUG_ORDONNANCE = "drug_ordonnance";
   public static final String KEY_DRUG_QUANTITY = "drug_quantity";
   
   public static final String KEY_CONSULTATION_ID = "consultation_id";
   public static final String KEY_CONSULTATION_PRICE = "consultation_price";
   public static final String KEY_CONSULTATION_REPORT = "consultation_report";
   public static final String KEY_CONSULTATION_DATE = "consultation_date";
   public static final String KEY_CONSULTATION_HOURS = "consultation_hours";
   public static final String KEY_CONSULTATION_DOCTOR_ID = "consultation_doctor_id";
   public static final String KEY_CONSULTATION_PATIENT_ID = "consultation_patient_id";
   
   public static final String KEY_CONSULTATIONDRUG_CONSULTAION_ID = "consultationDrug_consultation_id";
   public static final String KEY_CONSULTATIONDRUG_DRUG_ID = "consultationDrug_drug_id";
   public static final String KEY_CONSULTATIONDRUG_QUANTITY = "consultationDrug_quantity";
   public static final String KEY_CONSULTATIONDRUG_DURATION = "consultationDrug_duration";
   
}
