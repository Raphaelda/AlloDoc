package model.backend;

import android.content.Context;

public final class BackendFactory 
{
	  static Backend instance = null; 
	  public  static String mode = "Service";  
	  public  final  static Backend getInstance(Context context) 
	  {       
		  if (mode == "lists") 
		  {    
			  if (instance == null)  
			  {
				  instance = new model.datasource.DatabaseList(); 
				  //instance.setLists();
			  }
			  return  instance;   
		   }       
		  if (mode == "sql") 
		  {    
			  if (instance == null)
			  {
				  instance = new model.datasource.DatabaseSqlite(context);
				  try 
				  {
					if(instance.getDoctorList().size()==0) 
						 instance.setLists();
				  } 
				  catch (Exception e) 
				  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }
			  }
			  return  instance;   
		  }    
		  if (mode == "Service") 
		  {    
			  if (instance == null)     
				  instance = new model.datasource.DatabaseService();    
			  return  instance;   
		  }   
		  else 
		  {    
			  return  null;   
		  }  
	  } 
}


