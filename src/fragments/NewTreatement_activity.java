package fragments;

import java.util.ArrayList;
import java.util.List;

import model.backend.Backend;
import model.backend.BackendFactory;

import com.example.my_first_application.R;
import com.example.my_first_application.R.id;
import com.example.my_first_application.R.layout;
import com.example.my_first_application.R.menu;

import control.Login_activity;
import control.MyAsyncTask;
import control.MyFunc;
import entities.Consultation;
import entities.Consultation_Drug;
import entities.Doctor;
import entities.Patient;
import android.support.v7.app.ActionBarActivity;
import android.transition.Visibility;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NewTreatement_activity extends Fragment {
	
	
	Spinner sp ;
	EditText Qty;
	AutoCompleteTextView at;
	ArrayList<String> drg = new ArrayList<String>();
	ArrayList<String> drug = new ArrayList<String>();
	ArrayList<String> drugTemp = new ArrayList<String>();
	ArrayList<String> Quantity = new ArrayList<String>();
	MyAdapter spinnerAdapter;
	MyAdapterTemp spinnerAdapterTemp;
	Intent intentDetails;
	Patient patient;
	Doctor doctor; 
	ArrayAdapter<String> adapter = null;
	public NewTreatement_activity()
	{
		drugTemp.add("Drugs");
		
				
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		
		new MyAsyncTask
	       (this.getActivity() ,
			new MyFunc()
			{
			@Override
	    	public void run() throws Exception
			 {
				Backend backend = BackendFactory.getInstance(getActivity().getApplicationContext());
			    drg = backend.getNameDrugList();
			 }
			 },
			 new MyFunc()
			 {	 @Override
				 public void run() throws Exception{}
			 }
			).execute();
		
		intentDetails = getActivity().getIntent();
		final Patient patient=(Patient)intentDetails.getSerializableExtra("PatientDetails");
		final Doctor doctor = (Doctor)intentDetails.getSerializableExtra("DoctorDetails");
		final ScrollView sv = (ScrollView)inflater.inflate(R.layout.activity_new_treatement,container,false);
		
		// CODE FOR THE AUTOCOMPLETED EDITTEXT OF DRUGS
		at =(AutoCompleteTextView)sv.findViewById(R.id.scrollView1).findViewById(R.id.linearLayout1).
				findViewById(R.id.linearLayout2).findViewById(R.id.autoCompleteTextView1);
		 
		
					
		adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,drg);
		at.setThreshold(1);
	    at.setAdapter(adapter);
		at.setText("");
				 
		
		
		
        //CODE FOR THE SPINNER TO SHOW THE LIST DRUGS THAT THE DOCTOR CHOICE
		sp = (Spinner)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).
				findViewById(R.id.linearLayout1).findViewById(R.id.spinner3);
		spinnerAdapter = new MyAdapter(getActivity(),R.layout.spinner_drop_down_row, drug);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_drop_down_row);
        
        if(drug.size()==0)
        {
          spinnerAdapterTemp = new MyAdapterTemp (getActivity(),R.layout.spinner_drop_down_row, drugTemp);
          spinnerAdapterTemp.setDropDownViewResource(R.layout.spinner_drop_down_row);
          sp.setAdapter(spinnerAdapterTemp);
        }
        else
          sp.setAdapter(spinnerAdapter);
		Button add = (Button)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).
				findViewById(R.id.linearLayout1).findViewById(R.id.button1);
		Qty= (EditText)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).findViewById(R.id.linearLayout1).
				findViewById(R.id.linearLayout2).findViewById(R.id.editText2);
		Qty.setText("");
		final EditText report = (EditText)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).
				findViewById(R.id.linearLayout5).findViewById(R.id.EditText01);
		report.setText("");
		final CheckBox confirm = (CheckBox)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).
				findViewById(R.id.linearLayout5).findViewById(R.id.linearLayout7).findViewById(R.id.checkBox1);
		
		add.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				try
				{
				if(Qty.getText().toString().equals("") || at.getText().toString().equals(""))
				{
					throw new Exception("you must select a quantity and Drug!");
				}
				boolean found = false;
				for(int i =0 ; i<drug.size(); i++)
				{
				     if(at.getText().toString().equals(drug.get(i)) && (!Qty.getText().toString().equals(Quantity.get(i))
				    		 ||Qty.getText().toString().equals(Quantity.get(i))))
				     {
				        Quantity.set(i, Qty.getText().toString());
				        found = true;
				        
				     }
				     break;
				}
				if(found == false)
				{
					
				    boolean find = false;
					for(int i =0 ; i<drg.size(); i++)
					{
						//check if the text match to one of the drugs in the druglist.
					    if(at.getText().toString().equals(drg.get(i)))
					    {
					    	find = true;
					    }
					}
					if(find)
					{
						Quantity.add(Qty.getText().toString());
						drug.add(at.getText().toString());
					}
					else
						Toast.makeText(getActivity().getApplicationContext(), "Please choose a drug that exist in our medical center",
								   Toast.LENGTH_LONG).show();
						
				}
				at.setText("");
				Qty.setText("");
				if(drug.size()!=0)
				   sp.setAdapter(spinnerAdapter);
				}
				catch(Exception e)
				{
					AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
					ad.setTitle("Problem");
					ad.setMessage(e.getMessage());
					ad.show();
				}
			}
		});
		
		
		
		//THE CODE OF THE BUTTON OK TO ADD THE NEW CONSULTATION
		Button Ok =(Button)sv.findViewById(R.id.scrollView1).findViewById(R.id.LinearLayout0).
				findViewById(R.id.linearLayout5).findViewById(R.id.linearLayout7).findViewById(R.id.button2);
		Ok.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				//verifier ici que y'a au moin un des champ de la page qui est rempli sinon ce n'est pas considerer 
				//comme une consultation
				//CHECK IF AT LEAST ONE OF THE FIELD IS NOT EMPTY
				if(drug.size()>0 || !report.getText().toString().equals(""))
				{
					 if(confirm.isChecked())
					 {
				   
				   try 
				   {
					 //creation de l'objet consultation
					   new MyAsyncTask 
					    (NewTreatement_activity.this.getActivity() ,
					    new MyFunc()  
					     {
					    	@Override 
					    	public void run() throws Exception
					    	{
					    		Backend backend = BackendFactory.getInstance(getActivity().getApplicationContext());
					    		backend.addConsultation(new Consultation(doctor.getDoctor_Id(),patient.getPatient_Id(),
										report.getText().toString()));
								//recuperation du Id de la consultation qui vient d'etre creer
								ArrayList<Consultation> consultation =  backend.getConsultationList();
								int consultationListSize = consultation.size();
								long consultationId = consultation.get(consultationListSize-1).getConsultation_Id();
								//creation d'objet Consultation_drug grace a la consultationId et a la 
								//recuperation des drugId par la fonction DrugIdByName
								//CREATION OF THE OBJECT CONSULTATION_DRUG
								for(int i =0 ; i<drug.size(); i++)
								{
									long drugId = backend.getDrugIdByName(drug.get(i));
								    backend.addDrugToConsultation(new Consultation_Drug(
								    		consultationId,drugId,Integer.valueOf(Quantity.get(i))));
								}
					    		
					    	}
					    	
					     },
					     new MyFunc() 
					      {
					    	 @Override
					    	 public void run() throws Exception
					    	 {
					    			drug.clear();
									Quantity.clear();
									report.setText("");
									confirm.setChecked(false);
									//switch between fragment
									getActivity().getActionBar().getTabAt(1).select();
					    	 }
					    	 
					      }).execute();
					
					
				
					
					
				   } 
				   catch (Exception e) 
				   {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
				   
				}
					 else if(!confirm.isChecked())
					 {
						 Toast.makeText(getActivity().getApplicationContext(), "Please confirm before click to OK",
								   Toast.LENGTH_LONG).show();
						 //customize a toast
						 /*
						 LayoutInflater myInflater=LayoutInflater.from(this);
						 View view=myInflater.inflate(R.layout.your_custom_layout,null);
						 Toast mytoast=new Toast(getActivity());
						 mytoast.setView(view);
						 mytoast.setDuration(Toast.LENGTH_LONG);
						 mytoast.show();*/
					 }
				
				}
				else
				{
					Toast.makeText(getActivity().getApplicationContext(), "Please fill your consultation before click to OK",
							   Toast.LENGTH_LONG).show();
				}
			}
			
		});
        // Inflate the layout for this fragment
        return sv;
    }
	
	
	
	
	
	public class MyAdapter extends ArrayAdapter {
		 
	    Context c;
	    ArrayList<String> drug;
	    int ressource;
	    public MyAdapter(Context context,int ressource, ArrayList<String> drugs) {
	        super(context,ressource,drugs);
	        this.c = context;
	        this.drug = drugs;
	        this.ressource = ressource;
	        
	    }
	    public void setOnItemClickListener(OnItemClickListener onItemClickListener) 
	    {
			// TODO Auto-generated method stub
	    }

		@Override
	    public int getCount() {
	        return drug.size();
	    }
	 
	    @Override
	    public String getItem(int position) {
	        return drug.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) 
	    {
	    	   
	    	    final int pos = position;
	    	    String cur_obj = drug.get(position);
	    	    String cur_Qty = Quantity.get(position);
		        LayoutInflater inflater = getActivity().getLayoutInflater();
		        final View row = inflater.inflate(R.layout.spinner_drop_down_row, parent, false);
		        final TextView medocs = (TextView) row.findViewById(R.id.textView1);
		        final TextView Qt = (TextView)row.findViewById(R.id.textView2);
		        medocs.setText(cur_obj);
		        Qt.setText(cur_Qty);
		        ImageButton img = (ImageButton)row.findViewById(R.id.imageButton1);
			    img.setOnClickListener(new OnClickListener()
			    {

						@Override
						public void onClick(View v) 
						{
							// TODO Auto-generated method stub
							TextView txt = (TextView)row.findViewById(R.id.textView1);
							String str = txt.getText().toString();
							for(int i =0 ; i<drug.size();i++)
								{
									if(drug.get(i).equals(str))
									{
										drug.remove(i);
										if(txt.getText().toString().equals(at.getText().toString()))
											{
												at.setText("");
												Qty.setText("");
											}
										if(drug.size() == 0)
											sp.setAdapter(spinnerAdapterTemp);
										else
										    sp.setAdapter(spinnerAdapter);
										break;
									}
								 }
						}
			        	
			      });
			    medocs.setOnClickListener(new OnClickListener()
			    {

					@Override
					public void onClick(View v) 
					{
						// TODO Auto-generated method stub
						at.setText(medocs.getText().toString());
						Qty.setText(Quantity.get(pos));
						
						
						
					}
			    	
			    });
		        return row;
        }
	    
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	    	LayoutInflater inflater = getActivity().getLayoutInflater();
	        View row = inflater.inflate(R.layout.spinner_view_row, parent, false);
	        TextView medocs = (TextView) row.findViewById(R.id.textView1);
	        TextView Qt = (TextView)row.findViewById(R.id.textView2);
	        //ImageButton img = (ImageButton)row.findViewById(R.id.imageButton1);
	        //img.setVisibility(View.INVISIBLE);
	        medocs.setText("Drugs");
	        Qt.setText("Qty");
	        return row;
	    }
	}

	
	public class MyAdapterTemp extends ArrayAdapter {
		 
	    Context c;
	    ArrayList<String> drug;
	    int ressource;
	    public MyAdapterTemp(Context context,int ressource, ArrayList<String> drugs) {
	        super(context,ressource,drugs);
	        this.c = context;
	        this.drug = drugs;
	        this.ressource = ressource;
	        
	    }
	    public void setOnItemClickListener(OnItemClickListener onItemClickListener) 
	    {
			// TODO Auto-generated method stub
	    }

		@Override
	    public int getCount() {
	        return drug.size();
	    }
	 
	    @Override
	    public String getItem(int position) {
	        return drug.get(position);
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) 
	    {
	    	//return getView(position,convertView,parent);
	    	LayoutInflater inflater = getActivity().getLayoutInflater();
	        View row = inflater.inflate(R.layout.spinner_drop_down_row, parent, false);
	        TextView medocs = (TextView) row.findViewById(R.id.textView1);
	        TextView Qt = (TextView)row.findViewById(R.id.textView2);
	        ImageButton img = (ImageButton)row.findViewById(R.id.imageButton1);
	        img.setVisibility(View.GONE);
	        Qt.setVisibility(View.GONE);
	        Qt.setText("");
	        medocs.setText("           Drug's List Consultation");
	        return row;
        }
	    
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	    	LayoutInflater inflater = getActivity().getLayoutInflater();
	        View row = inflater.inflate(R.layout.spinner_view_row, parent, false);
	        TextView medocs = (TextView) row.findViewById(R.id.textView1);
	        medocs.setText("Drugs");
	        return row;
	    }
	}


}
