package fragments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.my_first_application.R;




























import entities.Patient;
import android.R.color;
import android.support.v7.app.ActionBarActivity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class PatientDetailsFrag_activity extends Fragment 
{
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) 
	{
		Intent patientIntent = getActivity().getIntent();
		Patient patient=(Patient)patientIntent.getSerializableExtra("PatientDetails");
        ScrollView sv = (ScrollView)inflater.inflate(R.layout.activity_patient_details_frag, container, false);
        
        
        
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.marc_zuckerberg);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = 250;
        int newHeight = 250;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // create the new Bitmap object
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,height, matrix, true);
        BitmapDrawable bmd = new BitmapDrawable(resizedBitmap);
        ImageView imageView = (ImageView)sv.findViewById(R.id.imageView1);
        imageView.setImageDrawable(bmd);
        imageView.setScaleType(ScaleType.CENTER);

        TextView FirstName = (TextView)sv.findViewById(R.id.textView2);
        TextView LastName = (TextView)sv.findViewById(R.id.textView4);
        TextView Sex = (TextView)sv.findViewById(R.id.textView6);
        TextView Age = (TextView)sv.findViewById(R.id.textView8);
        TextView Adress = (TextView)sv.findViewById(R.id.textView10);
        TextView City = (TextView)sv.findViewById(R.id.textView12);
        TextView Mail = (TextView)sv.findViewById(R.id.textView14);
        TextView Telephonne = (TextView)sv.findViewById(R.id.textView16);
        TextView Postal_Code = (TextView)sv.findViewById(R.id.textView18);
        TextView Weigth = (TextView)sv.findViewById(R.id.textView20);
        TextView Heigth = (TextView)sv.findViewById(R.id.textView22);
        TextView BloodGroup = (TextView)sv.findViewById(R.id.textView24);
        
        
        
        String dat = patient.getPatient_Birthdate();
        dat = dat.substring(6,10);
		int age = Integer.parseInt(dat);
		Calendar c = Calendar.getInstance();
		age = c.get(Calendar.YEAR) - age;
		Age.setText(String.valueOf(age));
		
        FirstName.setText(patient.getPatient_FirstName());
        LastName.setText(patient.getPatient_LastName());
        Sex.setText(patient.getPatient_Sex().toString());
        Adress.setText(patient.getPatient_Adress());
        City.setText(patient.getPatient_City());
        Mail.setText(patient.getPatient_Mail());
        Telephonne.setText(patient.getPatient_Tel());
        Postal_Code.setText(patient.getPatient_Postal_Code());
        Weigth.setText(Float.toString(patient.getPatient_Weight()));
        Heigth.setText(Float.toString(patient.getPatient_Height()));;
        BloodGroup.setText(patient.getPatient_BloodGroup().toString());
        
        
       /* LinearLayout ll =(LinearLayout) sv.getChildAt(0); 
        TextView allergy = new TextView(getActivity());
        TextView vide = new TextView(getActivity());
        vide.setText("        ");
        ll.addView(vide);
        allergy.setText("Allergy:");
        allergy.setTypeface(null, Typeface.BOLD);
        allergy.setTextSize(20);
        allergy.setTextColor(Color.parseColor("#FFFFFF"));
        LinearLayout horizon = new LinearLayout (getActivity());
        horizon.setOrientation(LinearLayout.HORIZONTAL);
        horizon.addView(allergy);
        ll.addView(horizon);
        List<String> arrListAllergy = new ArrayList<String>();
        arrListAllergy = patient.getAllergy();
        int count = 0;
        while (arrListAllergy!=null &&arrListAllergy.size()>count)
        {
        	arrListAllergy = arrListAllergy.subList(count,arrListAllergy.size());
        	final LinearLayout lhorizontal = new LinearLayout(getActivity());
			lhorizontal.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
			lhorizontal.setLayoutParams(params);
			for (String StringItem : arrListAllergy)
        	{
				TextView str1 = new TextView(getActivity());
        		params.setMargins(60,2,0,2);
        		str1.setLayoutParams(params);
        		str1.setText(StringItem);
        		str1.setBackgroundResource(R.drawable.border);
        		lhorizontal.addView(str1);
             	count = count + 1;
        		break;
			}
        	ll.addView(lhorizontal);
        }
        
        TextView operation = new TextView(getActivity());
        TextView vide1 = new TextView(getActivity());
        vide1.setText("        ");
        ll.addView(vide1);
        operation.setText("Operation:");
        operation.setTypeface(null, Typeface.BOLD);
        operation.setTextSize(20);
        operation.setTextColor(Color.parseColor("#FFFFFF"));
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
        LinearLayout horizon1 = new LinearLayout (getActivity());
        params1.weight=0;
        horizon1.setLayoutParams(params1);
        horizon1.setOrientation(LinearLayout.HORIZONTAL);
        horizon1.addView(operation);
        ll.addView(horizon1);
        List<String> arrListOperation = new ArrayList<String>();
        arrListOperation = patient.getOperation();
        int count1 = 0;
        while (arrListOperation!=null && arrListOperation.size()>count1)
        {
        	arrListOperation = arrListOperation.subList(count1,arrListOperation.size());
        	final LinearLayout lhorizontal = new LinearLayout(getActivity());
			lhorizontal.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
    		lhorizontal.setLayoutParams(params);
        	for (String StringItem : arrListOperation)
        	{
        		TextView str1 = new TextView(getActivity());
        		params.setMargins(60,2,0,2);
        		str1.setText(StringItem);
        		str1.setBackgroundResource(R.drawable.border);
        		lhorizontal.addView(str1);
             	count1 = count1 + 1;
        		break;	
        	}
        	ll.addView(lhorizontal);
        }
        
        TextView Disease = new TextView(getActivity());
        TextView vide2 = new TextView(getActivity());
        vide2.setText("        ");
        ll.addView(vide2);
        Disease.setText("Disease:");
        Disease.setTextSize(20);
        Disease.setTypeface(null, Typeface.BOLD);
        Disease.setTextColor(Color.parseColor("#FFFFFF"));
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
        LinearLayout horizon2 = new LinearLayout (getActivity());
        params2.weight=0;
        horizon2.setLayoutParams(params2);
        horizon2.setOrientation(LinearLayout.HORIZONTAL);
        horizon2.addView(Disease);
        ll.addView(horizon2);
        List<String> arrListDisease = new ArrayList<String>();
        arrListDisease = patient.getDiseases();
        int count2 = 0;
        while (arrListDisease!=null && arrListDisease.size()>count1)
        {
        	arrListDisease = arrListDisease.subList(count1,arrListDisease.size());
        	final LinearLayout lhorizontal = new LinearLayout(getActivity());
			lhorizontal.setOrientation(LinearLayout.HORIZONTAL);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
    		lhorizontal.setLayoutParams(params);
        	for (String StringItem : arrListDisease)
        	{
        		TextView str1 = new TextView(getActivity());
        		params.setMargins(60,2,0,2);
        		str1.setText(StringItem);
        		str1.setBackgroundResource(R.drawable.border);
        		lhorizontal.addView(str1);
             	count1 = count1 + 1;
        		break;	
        	}
        	ll.addView(lhorizontal);
        }
        
        if(arrListAllergy==null)
        {
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
        	params.setMargins(45,2,0,2);
        	TextView noAllergy = new TextView(getActivity());
        	noAllergy.setText("No Allergy");
        	noAllergy.setLayoutParams(params);
        	noAllergy.setBackgroundResource(R.drawable.border);
        	horizon.addView(noAllergy);
        	
        }
        if(arrListOperation==null)
        {
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
        	TextView noOperation = new TextView(getActivity());
        	params.setMargins(30,2,0,2);
        	noOperation.setLayoutParams(params);
        	noOperation.setBackgroundResource(R.drawable.border);
        	noOperation.setText("No Operation");
        	horizon1.addView(noOperation);
        	
        }
        if(arrListDisease==null)
        {
        	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,1f);
        	params.setMargins(45,2,0,2);
        	TextView noDisease = new TextView(getActivity());
        	noDisease.setLayoutParams(params);
        	noDisease.setBackgroundResource(R.drawable.border);
        	noDisease.setText("No Disease");
        	horizon2.addView(noDisease);
        	
        }*/
        
        
        
        
        
        return sv;
    }
	
	
	 private Object getRessource() {
		// TODO Auto-generated method stub
		return null;
	}


	

}