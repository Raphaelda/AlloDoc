package control; 
  
import control.MyFunc;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
  
  public  class  MyAsyncTask  extends AsyncTask<Void, Void, Void> 
  {     
	  private ProgressDialog progressDialog;
	  private Activity myAct;
	  private Exception exceptionThrown;
	  private MyFunc back, post;
	  
	  public MyAsyncTask(Activity activity, ProgressDialog progress, MyFunc b, MyFunc p)
	   {   super();
	       myAct = activity;
	       back = b;
	       post = p;
	       progressDialog = progress;
	   } 
	  
	  public MyAsyncTask(Activity activity, MyFunc b, MyFunc p)
	   {   super();
	       myAct = activity;
	       back = b;
	       post = p;
	       progressDialog = null;
	   }
	  
	  @Override protected Void doInBackground(Void... params)
	   {  try  
	      {  
		   back.run();
		  } 
	      catch (Exception e)
	      {  exceptionThrown = e;
	         if (e.getMessage().contains("Read timed out")) 
	          {   exceptionThrown = new Exception("No response from server. Try again.");
	          
	          }
	          else  
	        	  if (e.getMessage().contains("@@@"))   
	                {  String s = e.getMessage();
	                   s = s.replaceAll("[^@]*@@@|###[^@]*", "");
	                   exceptionThrown = new Exception(s);
	                }     
	        	  else {  exceptionThrown = e;}
	       } 
	   return  null;
	   }
	  
	  @Override protected void onPreExecute()
	  {
		  try
		  {
			  progressDialog = ProgressDialog.show(myAct, "Please Wait", "wait",true);
		  }
		  catch (Exception e) 
		  {
			  e.printStackTrace();
		  }
	  }
	  
	  @Override protected  void onPostExecute(Void result) 
	  {  
		  try  
		  {   
			  if (progressDialog.isShowing())
			  {    
				  progressDialog.dismiss();
			  }   
			  if (exceptionThrown != null) 
			  {    
				  Toast.makeText(myAct, exceptionThrown.getMessage(),  Toast.LENGTH_SHORT).show();
			  }   
			  else   {    post.run();    } 
		  }  
		  catch (Exception e)  
		  {  
			  e.printStackTrace(); 
		  }     
		}  
	}
 
	   
	  
	  
	 