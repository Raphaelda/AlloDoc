package control;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import model.backend.Backend;
import model.backend.BackendFactory;

import com.example.my_first_application.R;

import entities.Consultation;
import entities.Doctor;
import entities.Patient;
import entities.Treatement_Item;
import fragments.NewTreatement_activity;
import fragments.PatientDetailsFrag_activity;
import fragments.PatientTreatements_activity;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class Patient_activity extends  Activity  
{
	ActionBar.Tab Tab1, Tab2, Tab3;
	PatientDetailsFrag_activity fragmentDetailsPatient;
	PatientTreatements_activity fragmentListTreatements;
	NewTreatement_activity fragmentNewTreatement;
	//ArrayList<Consultation> consultation;
	Backend backend;
	Patient patient;
	Doctor doctor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient);
		
		
		Intent intentDetails=getIntent();
		patient=(Patient)intentDetails.getSerializableExtra("PatientDetails");
		doctor = (Doctor)intentDetails.getSerializableExtra("DoctorDetails");
		
		
		
		//consultation=new ArrayList<Consultation>();
	    backend = BackendFactory.getInstance(null);
	   /* try {
	    	new MyAsyncTask(Patient_activity.this,
	    			new MyFunc(){
	    							@Override
	    							public void run() throws Exception 
	    							{
	    								consultation = backend.getConsultationListByPatientID(patient.getPatient_Id());
	    							}
	    						},
	    			new MyFunc(){
	    							@Override
	    							public void run() throws Exception 
	    							{}
	    						}).execute();
			   
		    } 
	    catch (Exception e) 
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	    final FragmentManager fm = getFragmentManager();
	    fragmentDetailsPatient = (PatientDetailsFrag_activity) fm.findFragmentByTag("DetailsTag");
		if(fragmentDetailsPatient==null){fragmentDetailsPatient = new PatientDetailsFrag_activity();}
		fragmentListTreatements = (PatientTreatements_activity) fm.findFragmentByTag("ListTag");
		if(fragmentListTreatements==null) {fragmentListTreatements = new PatientTreatements_activity();}//consultation
		fragmentNewTreatement = (NewTreatement_activity) fm.findFragmentByTag("NewTag");
		if(fragmentNewTreatement==null) {fragmentNewTreatement = new NewTreatement_activity();}
		
		
		
		ActionBar actionBar = getActionBar();
		 
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);
 
		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);
 
		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
		// Set Tab Icon and Titles
		Tab1 = actionBar.newTab().setText("Details");
		Tab2 = actionBar.newTab().setText("Treatements");
		Tab3 = actionBar.newTab().setText("New-Treatement");
		
 
		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentDetailsPatient));
		Tab2.setTabListener(new TabListener(fragmentListTreatements));
		Tab3.setTabListener(new TabListener(fragmentNewTreatement));
 
		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		
	    } 
		   
	//en java il y'a une fonction instanceOf pour verifier si un objet est de la classe que tu veut
	public class TabListener implements ActionBar.TabListener {
		 
		Fragment fragment,fragment1;
	    FragmentManager fm;
		public TabListener(Fragment fragment) {
			// TODO Auto-generated constructor stub
			this.fragment = fragment;
			fragment1=fragment;
			fm = getFragmentManager();
		}
	 
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft = getFragmentManager().beginTransaction();
			if(fragment.getClass()==PatientTreatements_activity.class)
			{
				fragment = (PatientTreatements_activity)fm.findFragmentByTag("ListTag");
				if(fragment == null)
				{
					//ft.add(fragment1, "ListTag");
					ft.replace(R.id.LinearLayout1, fragment1,"ListTag");
					fragment = fragment1;
					ft.commit();
					return;
				}
			}
			if(fragment.getClass()==PatientDetailsFrag_activity.class)
			{
				fragment = (PatientTreatements_activity)fm.findFragmentByTag("DetailsTag");
				if(fragment == null)
				{
					//ft.add(fragment1, "DetailsTag");
					ft.replace(R.id.LinearLayout1, fragment1,"DetailsTag");
					fragment = fragment1;
					ft.commit();
					return;
				}
			}
			if(fragment.getClass()==NewTreatement_activity.class)
			{
				fragment = (PatientTreatements_activity)fm.findFragmentByTag("NewTag");
				if(fragment == null)
				{
					//ft.add(fragment1, "NewTag");
					ft.replace(R.id.LinearLayout1, fragment1,"NewTag");
					fragment = fragment1;
					ft.commit();
					return;
				}
			}
			ft.show(fragment);
			ft.commit();
		}
	 
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) 
		{
			//ft.remove(fragment1);
		}
	 
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) 
		{
			// TODO Auto-generated method stub
			
			
			
			ft = getFragmentManager().beginTransaction();
			if(fragment.getClass()==PatientTreatements_activity.class)
			{
				fragment = (PatientTreatements_activity)fm.findFragmentByTag("ListTag");
				if(fragment == null)
				{
					ft.replace(R.id.LinearLayout1, fragment1,"ListTag");
					ft.commit();
					return;
				}
			}
			if(fragment.getClass()==PatientDetailsFrag_activity.class)
			{
				fragment = (PatientDetailsFrag_activity)fm.findFragmentByTag("DetailsTag");
				if(fragment == null)
				{
					ft.replace(R.id.LinearLayout1, fragment1,"DetailsTag");
					ft.commit();
					return;
				}
			}
			if(fragment.getClass()==NewTreatement_activity.class)
			{
				fragment = (NewTreatement_activity)fm.findFragmentByTag("NewTag");
				if(fragment == null)
				{
					ft.replace(R.id.LinearLayout1, fragment1,"NewTag");
					ft.commit();
					return;
				}
			}
			ft.show(fragment);
			ft.commit();
	
					
		}
	}
		
}














/*void initTreatement_ItemList(int size) 
{  
	myItemList = new ArrayList<Treatement_Item>();   
    for (int i = 0; i < size; i++)  
    {    
    	int day = i % 27 + 1;   
    	int month = i % 12;   
    	int year = i % 10 + 1990;      
    	Calendar calender = new GregorianCalendar(year, month, day);
    	Treatement_Item item = new Treatement_Item("Treatement " + i, "Doctor " + i, calender.getTime());   
    	myItemList.add(item);  
    } 
} 

void initItemByListView(int size) 
{  
	initTreatement_ItemList(size);    
	ArrayAdapter<Treatement_Item> adapter = new ArrayAdapter<Treatement_Item>(this,R.layout.treatement_row, myItemList)  
	{    @Override  
		 public View getView(int position, View convertView, ViewGroup parent)   
	     {   
		      if(convertView == null)
		      {
		         convertView = View.inflate(Patient_activity.this,R.layout.treatement_row, null);
		      }
		      
		      TextView TreatementIdTextView = (TextView) convertView.findViewById(R.id.textView1);
		      TextView DoctorNameTextView = (TextView) convertView.findViewById(R.id.textView2);
		      TextView DateTextView = (TextView) convertView.findViewById(R.id.textView4);
		      TextView TextView3 = (TextView) convertView.findViewById(R.id.textView3);
		      TextView3.setText(null);
		      TreatementIdTextView.setText(myItemList.get(position).getTreatement_ID());
		      DoctorNameTextView.setText(myItemList.get(position).getDoctor_Name());
		      CharSequence date =DateFormat.format("dd/MM/yyyy",myItemList.get(position).getDate());
		      DateTextView.setText(date);
		      
		      return convertView;
		 }  
	};   
		      
	lv.setAdapter(adapter);   */
