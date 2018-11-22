package control;

import model.backend.Backend;
import model.backend.BackendFactory;
import model.datasource.AndroidDatabaseManager;

import com.example.my_first_application.R;

import entities.Doctor;
import entities.Patient;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login_activity extends Activity 
{
	 EditText Doctor_Id; 
	 EditText Doctor_Password ;
	 Doctor doctor;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Button Login = (Button) findViewById(R.id.button1); 
		final EditText Doctor_Id =(EditText)findViewById(R.id.editText1);
		final EditText Doctor_Password =(EditText)findViewById(R.id.editText2);
		Doctor_Id.setText("333222111");
		Doctor_Password.setText("daniel");
		//Initiate the progressedialog for the asynctask
		ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.dismiss();
		setProgressBarIndeterminateVisibility(false);
		
		


		Login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) 
			{
				final EditText The_Doctor_Id =(EditText)findViewById(R.id.editText1);
				final EditText The_Doctor_Password =(EditText)findViewById(R.id.editText2);
				final String DoctorId=The_Doctor_Id.getText().toString();
				final String The_DoctorPassword=Doctor_Password.getText().toString();
				
				
			
				try 
			    {
					
					
					
					//Intent dbmanager = new Intent(Login_activity.this,AndroidDatabaseManager.class);
		            //startActivity(dbmanager);
					
					
					
					
			    //convert a Edit Text into a string
				
				
					if(DoctorId.length()==0 && The_DoctorPassword.length()==0)
						throw new Exception("Password and Id can't be empty");
					if(DoctorId.length()==0)
						throw new Exception("Id can't be empty");
					if(The_DoctorPassword.length()==0)
						 throw new Exception("Password can't be empty");
			
				new MyAsyncTask
				(Login_activity.this,
				 new MyFunc()
				  {
					@Override 
					public void run() throws Exception
					    {
						 final Backend backend=BackendFactory.getInstance(getApplicationContext());
						 long doctorId=Long.valueOf(DoctorId);
						 doctor =backend.findDoctorById(doctorId);
						 backend.checkLogin(doctorId, The_DoctorPassword);
					    
					
						}
				  },
				  new MyFunc()
				  {
					@Override
					public void run() throws Exception
					{
						 
						 Intent intent = new Intent(Login_activity.this,Home_activity.class);
						 intent.putExtra("DoctorDetails", doctor);						 
						 startActivity(intent);
					}
				  }
				  
				).execute();
				
		    }
			catch (Exception e) 
			{
				AlertDialog ad = new AlertDialog.Builder(Login_activity.this).create();
				ad.setTitle("Problem");
				ad.setMessage(e.getMessage());
				ad.show();
			}
			
		}
		
   });
	
}
	
}
