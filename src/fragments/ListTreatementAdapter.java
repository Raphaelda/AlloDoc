package fragments;


import java.util.ArrayList;

import model.backend.Backend;
import model.backend.BackendFactory;

import com.example.my_first_application.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import fragments.PatientTreatements_activity;
import entities.Consultation;

public class ListTreatementAdapter extends BaseAdapter{

	ArrayList<Consultation> consultation=new ArrayList<Consultation>();
	final Backend backend = BackendFactory.getInstance(null);
	Context mcontext;
	public ListTreatementAdapter(Context context , long patientID) 
	{  
		super();
		mcontext = context;
		try
		{
		   consultation = backend.getConsultationListByPatientID(patientID);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	//PatientTreatements_activity pp =new PatientTreatements_activity(patientID);
	@Override 
	public View getView(int position, View convertView ,ViewGroup parent)
	{   
		
		//LayoutInflater inflater =  (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.treatement_row, parent , false);       
		TextView date = (TextView) row.findViewById(R.id.textView1);        
		TextView hours= (TextView) row.findViewById(R.id.textView2); 
        TextView Dr = (TextView)row.findViewById(R.id.textView3);
        date.setText(consultation.get(position).getConsultation_Date());
        hours.setText(consultation.get(position).getConsultation_Hours());
        try {
			Dr.setText(backend.findDoctorName(consultation.get(position).getTheDoctor_Id()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return (row);     
	} 
	
	@Override public int getCount() 
	{ 
		return consultation.size();       
	} 
	
	@Override public long getItemId(int position) 
	{  
		return position;      
	}    

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
