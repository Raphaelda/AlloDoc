package control;

import java.util.ArrayList;

import model.backend.Backend;
import model.backend.BackendFactory;

import com.example.my_first_application.R;

import entities.Doctor;
import entities.Patient;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Home_activity extends Activity
{

	Patient patient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Button Search = (Button) findViewById(R.id.button2); 
		Button New =(Button)findViewById(R.id.button1);
		Intent intentDoctorDetails=getIntent();
		final Doctor doctor=(Doctor)intentDoctorDetails.getSerializableExtra("DoctorDetails");
		String UserNameDoctor= doctor.getDoctor_FirstName() +" " + doctor.getDoctor_LastName();
		TextView userNameDoctor=(TextView)findViewById(R.id.textView2);
		final EditText PatientId=(EditText)findViewById(R.id.editText1);
		
		PatientId.setText("333444555");
		
		final Backend backend = BackendFactory.getInstance(getApplicationContext());
		//ArrayList<String> drg;
		//drg= backend.getNameDrugList();
		//String[] drug = new String[drg.size()];
		//
		//	for(int i = 0 ; i<drg.size(); i++)
		//		drug[i] = drg.get(i);
				
				
		userNameDoctor.setText("Dr'  "+UserNameDoctor);
		
		New.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String patientId=PatientId.getText().toString();
				final long patientID=Long.valueOf(patientId);
				try 
				{
					//final Backend backend=BackendFactory.getInstance(getApplicationContext());
					new MyAsyncTask(Home_activity.this,
									new MyFunc(){
						 				@Override
						 				public void run() throws Exception {
						 					patient=backend.findPatientById(patientID);}
								  },new MyFunc(){
									  	@Override
									  	public void run() throws Exception {
									  		Intent intent = new Intent(Home_activity.this,Patient_activity.class);
									  		intent.putExtra("PatientDetails", patient);
									  		intent.putExtra("DoctorDetails",doctor);
									  		startActivity(intent);}
								  }).execute();
					
					
					
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
				 
				
			}
			
		});
		Search.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				 Intent intent = new Intent(Home_activity.this,Search_activity.class);
				 startActivity(intent);
				
				
			}
			
		});
		
		//code of Drop Down List 
		//code taken in the site http://developer.android.com/guide/topics/ui/controls/spinner.html
		Spinner Search_Choice=(Spinner)findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.Search_Choice, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		Search_Choice.setAdapter(adapter);
	}


}
