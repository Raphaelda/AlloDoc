package fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.backend.Backend;
import model.backend.BackendFactory;

import com.example.my_first_application.R;
import com.example.my_first_application.R.color;
import com.example.my_first_application.R.id;
import com.example.my_first_application.R.layout;
import com.example.my_first_application.R.menu;

import control.DisplayTreatement_activity;
import control.MyAsyncTask;
import control.MyFunc;
import control.Patient_activity;
import entities.Consultation;
import entities.Consultation_Drug;
import entities.Patient;
import entities.Treatement_Item;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import entities.Treatement_Item;

public class PatientTreatements_activity extends Fragment 
{
	ArrayList<Consultation> consultation;
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	//for each ConsultationId there is list of ConsultationDrug
	HashMap<Long, List<Consultation_Drug>> listDataChild;// = new HashMap<Long, List<Consultation_Drug>>() ;
	final Backend backend = BackendFactory.getInstance(null);
	ArrayList<ArrayList<Consultation_Drug>> allConsulDrug = new ArrayList<ArrayList<Consultation_Drug>>();
	Patient patient;
	HashMap<Long , String> doctorID_Name = new HashMap<Long , String>();
    HashMap<Long , String> drugID_Name = new HashMap<Long , String>();
    
    public PatientTreatements_activity()
    {
    	new MyAsyncTask(getActivity(),
			  new MyFunc(){
		  						@Override
		  						public void run() throws Exception 
		  						{
		  							doctorID_Name=backend.DoctorHash_Id_Name();
		  							drugID_Name=backend.DrugHash_Id_Name();
		  						}
		  				   },
		  	   new MyFunc(){	@Override
		  					   	public void run() throws Exception{}
		  					  }).execute();
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    }
    
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	 {
		 
		 // Inflate the layout for this fragment
	     final  LinearLayout ll =(LinearLayout) inflater.inflate(R.layout.activity_patient_treatements, container, false);
	     expListView = (ExpandableListView)ll.findViewById(R.id.expandableListView1);
	    
	     Intent intentDetails=getActivity().getIntent();
		 patient = (Patient)intentDetails.getSerializableExtra("PatientDetails");

	    	  new MyAsyncTask(getActivity(),
	    			  new MyFunc(){
	    		  						@Override
	    		  						public void run() throws Exception 
	    		  						{
	    		  							//recharge la liste de consultation
	    		  							consultation = backend.getConsultationListByPatientID(patient.getPatient_Id());
	    		  							listDataChild = backend.getTheDetailsOfAllTheConsultation(/*patient.getPatient_Id(),*/consultation);
	    		  						}
	    		  				   },
	    		  	   new MyFunc(){
	    		  					   	@Override
	    		  					   	public void run() throws Exception 
	    		  					   	{
	    		  					   		listAdapter = new ExpandableListAdapter(consultation, listDataChild);
	    		  					   		expListView.setAdapter(listAdapter);
	    		  					   	}
	    		  				   }).execute();
	    return ll;
	
	 }



	 public class ExpandableListAdapter extends BaseExpandableListAdapter
	 {
         private HashMap<Long, List<Consultation_Drug>> listDataChild;
         ArrayList<Consultation> consultation;
        
		 public ExpandableListAdapter(ArrayList<Consultation> listDataHeader,
		            HashMap<Long, List<Consultation_Drug>> listDataChild) 
		 {
		        this.consultation = listDataHeader;
		        this.listDataChild = listDataChild;
		 }
		@Override
		public int getGroupCount() {
			return consultation.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return listDataChild.get(consultation.get(groupPosition).getConsultation_Id()).size();
		}

		@Override
		public Consultation getGroup(int groupPosition) {
			return consultation.get(groupPosition);
		}

		@Override
		public Consultation_Drug getChild(int groupPosition, int childPosition) 
		{
			return this.listDataChild.get(this.consultation.get(groupPosition).getConsultation_Id()).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,View convertView, ViewGroup parent) 
		{
			final int group_Position = groupPosition;//i use this variable in the assyncTask
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = inflater.inflate(R.layout.treatement_row, parent , false); 
			ImageView img = (ImageView)row.findViewById(R.id.imageView1);
			if(groupPosition >= 0){
				int imageResourceId = isExpanded ? R.drawable.arrow_up : R.drawable.arrow_down;
				img.setImageResource(imageResourceId);
			 
				img.setVisibility(View.VISIBLE);
			} else {
				img.setVisibility(View.INVISIBLE);
			}
			final TextView date = (TextView) row.findViewById(R.id.textView1);        
			final TextView hours= (TextView) row.findViewById(R.id.textView2); 
	        final TextView Dr = (TextView)row.findViewById(R.id.textView3);
	       
	        try {
	        	
		    		 Dr.setText(doctorID_Name.get(consultation.get(groupPosition).getTheDoctor_Id()));
		    		 date.setText(consultation.get(group_Position).getConsultation_Date());
		    		 hours.setText(consultation.get(group_Position).getConsultation_Hours());
		    		  					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			return (row);
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,boolean isLastChild, View convertView, ViewGroup parent) 
		{
			
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View row;
			
			if(childPosition==0)
			{
				
					
					row = inflater.inflate(R.layout.prescription, parent , false);
					TextView report = (TextView)row.findViewById(R.id.textView2);
					if(getGroup(groupPosition).getConsultation_Report().equals(""))
					{
						report.setText("There is no report for this consultation");
					}
					else
					{
					   report.setText(getGroup(groupPosition).getConsultation_Report());
					}
					final TextView Name = (TextView)row.findViewById(R.id.TextView01);
					final TextView Quantity = (TextView)row.findViewById(R.id.textView4);
					final TextView Duration = (TextView)row.findViewById(R.id.textView6);
			
			  try {
				
				    if(getChild(groupPosition,childPosition).getTheDrug_Id()==-1)
				    {
				    	TextView drug = (TextView)row.findViewById(R.id.TextView02);
						TextView quantity = (TextView)row.findViewById(R.id.TextView03);
						TextView duration = (TextView)row.findViewById(R.id.textView5);
						drug.setVisibility(View.GONE);
						quantity.setVisibility(View.GONE);
						duration.setVisibility(View.GONE);
				    	Name.setText("No Prescription for this consultation");
				    	Name.setGravity(Gravity.CENTER);
				    	Quantity.setVisibility(View.GONE);
				    	Duration.setVisibility(View.GONE);
				    }
				    else
				    {
				    	
				    	Name.setText(drugID_Name.get(getChild(groupPosition,childPosition).getTheDrug_Id()));//backend.getDrugNameById(getChild(group_Position,child_Position).getTheDrug_Id()));
				    	Quantity.setText(String.valueOf(getChild(groupPosition,childPosition).getQuantity()));
					   	Duration.setText(String.valueOf(getChild(groupPosition,childPosition).getDuration()));
				    	
				    }
				    
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			else 
			{
			
				row = inflater.inflate(R.layout.drug_row, parent , false);
				final TextView Name = (TextView)row.findViewById(R.id.textView2);
				final TextView Quantity = (TextView)row.findViewById(R.id.textView4);
				final TextView Duration = (TextView)row.findViewById(R.id.textView6);
			
				try 
				{
					Name.setText(drugID_Name.get(getChild(groupPosition,childPosition).getTheDrug_Id()));
					Quantity.setText(String.valueOf(getChild(groupPosition,childPosition).getQuantity()));
					Duration.setText(String.valueOf(getChild(groupPosition,childPosition).getDuration()));
				} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			return row;
			
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			
			return false;
		}
		 
	 }
	
}
