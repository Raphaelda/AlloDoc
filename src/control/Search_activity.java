package control;

import java.util.ArrayList;
import java.util.List;

import com.example.my_first_application.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class Search_activity extends  Activity 
{
	long msTime;
	List<String> myList;
	ListView lv;
    int size = 10;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	    msTime = System.currentTimeMillis(); 
	    lv=(ListView)findViewById(R.id.listView1);  
	    initByListView(size); 
		
	}
	@Override protected void onStart() 
	{  
		super.onStart();  
		msTime = System.currentTimeMillis() - msTime;   
		Toast.makeText(this, "start after " + msTime + "ms", Toast.LENGTH_SHORT).show(); 
	}
	private void initList(int size) 
	{  
		myList = new ArrayList<String>();   
		for (int i = 0; i < size; i++)  
			myList.add("user " + i); 
	}
	
	
	 void initByListView(int size) 
	 {  
		 initList(size);  
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>  (this,R.layout.row_button,myList);   
		 lv.setAdapter(adapter);    
     } 
	


	
}
	




