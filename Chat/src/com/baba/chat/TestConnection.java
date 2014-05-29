package com.baba.chat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
<<<<<<< HEAD
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
=======
>>>>>>> parent of 133152e... Upto demo doubtsession with working testconnection
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class TestConnection extends Activity
{
<<<<<<< HEAD
	private CharSequence status = null;
	Button b;
	EditText e1,e2;
	String ip;
	String sid,username,roll,macid;
	ImageView iv;
	
	private Socket client;
	private PrintWriter printwriter;
	
	Socket socket = null;
	DataOutputStream dataOutputStream = null;
	DataInputStream dataInputStream = null;
	
	StringBuffer br=new StringBuffer();  //For Appending purpose
	
	TextView textIn;
	
=======
Button b;
EditText e1,e2;
String ip;
String sid;

Socket socket = null;
DataOutputStream dataOutputStream = null;
DataInputStream dataInputStream = null;

StringBuffer br=new StringBuffer();  //For Appending purpose

TextView textIn;

>>>>>>> parent of 133152e... Upto demo doubtsession with working testconnection
	
	@Override
	protected void onCreate(Bundle kaushik) {
		// TODO Auto-generated method stub
		super.onCreate(kaushik);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.testnew);
		b=(Button)findViewById(R.id.testbutton);
		e1=(EditText)findViewById(R.id.iptest);
		e2=(EditText)findViewById(R.id.sidtest);
		//textIn=(TextView)findViewById(R.id.read);
		
		
	
<<<<<<< HEAD
		// Setting dp on testconnection
		
		iv=(ImageView)findViewById(R.id.dpconnect);
		String mypath=Environment.getExternalStorageDirectory().toString()+"/AakashApp/";

		Bitmap bmp = BitmapFactory.decodeFile(mypath+username+"/dp.jpg");

		iv.setImageBitmap(bmp);
		TextView banner =(TextView)findViewById(R.id.banner);
		banner.setText("Hi "+username);
		
		//Connect button
		b.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View v) 
				{
					InputMethodManager inputManager = (InputMethodManager)
		            getSystemService(Context.INPUT_METHOD_SERVICE); 
		
					inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);	
		
					
					ip = e1.getText().toString();
					sid = e2.getText().toString();
					
					
					Thread t=new Thread(new Runnable()
					{
						

						public void run()
						{
							try
							{
								//ip="10.105.15.204";
							
								Socket server= new Socket(ip, 4444); // connect to the server
							
								DataOutputStream dos=new DataOutputStream(server.getOutputStream());
								DataInputStream dis=new DataInputStream(server.getInputStream());
								String sss="1";
								if(sss.equals(dis.readUTF()))
								{
									//dos.writeUTF(ip);
									dos.writeUTF(sid);
									status=dis.readUTF();
									
									////////////////
									
									
									if (status.equals("1"))
									{
										Log.d("Lavish","GHUS GAYA");
										if(username!=null)
										dos.writeUTF(username);
										if(roll!=null)
										dos.writeUTF(roll);
										if(macid!=null)
										dos.writeUTF(macid);
										
										
										
										
										
									}
									
									
									
									
								}
							}
							catch(Exception exp)
							{
								//Toast.makeText(getApplicationContext(), "you got some exception", Toast.LENGTH_LONG).show();
								Log.d("Lavish","your Exception : "+exp);
								//textIn.setText(exp.toString());
							}
							//
							
						}
					});
				
					t.start();
					
					try {
						t.join(0);
					} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
					////////////////////////////////////////////////////
					
					// Possible status values
					
					if (status==null)
					{
						Toast.makeText(getApplicationContext(), "Please check your Connection and IP settings", Toast.LENGTH_LONG).show();
					}
					
					
					else if (status.equals("0"))
					{
						Toast.makeText(getApplicationContext(), "Session ID Mismatch. \nConnection Aborted.", Toast.LENGTH_LONG).show();
					}
					
					else if (status.equals("1"))
					{
						Toast.makeText(getApplicationContext(), "CONNECTION SUCCESSFUL...", Toast.LENGTH_LONG).show();
						
						Intent rc=new Intent("com.baba.chat.AUDIOMAINACTIVITY");
						startActivity(rc);
						
						
					}
					
					else 
					{
						Toast.makeText(getApplicationContext(), "Something Went Wrong...", Toast.LENGTH_LONG).show();
					}
					
					status=null;
					
					
					//////////////////////////////////////////////
					
				}
			});

	}
		
		
		  @Override
		    public void onBackPressed() {
		       
		        startActivity(new Intent("com.baba.chat.AUDIOMAINACTIVITY"));
		        finish();
		    }
		
}
=======
	

b.setOnClickListener(new View.OnClickListener() {
 
public void onClick(View v) {
	InputMethodManager inputManager = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE); 

inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
               InputMethodManager.HIDE_NOT_ALWAYS);	
	

ip = e1.getText().toString();
sid = e2.getText().toString();


SendMessage sendMessageTask = new SendMessage();
sendMessageTask.execute();


}

	
	
});
}
 
private class SendMessage extends AsyncTask<Void, Void, Void> {
 
@Override
protected Void doInBackground(Void... params) {
try {
	   socket = new Socket(ip, 8889);
	  dataOutputStream = new DataOutputStream(socket.getOutputStream());
	  dataInputStream = new DataInputStream(socket.getInputStream());
	  dataOutputStream.writeUTF(e2.getText().toString());
	  textIn.setText(dataInputStream.readUTF());
	  socket.close(); // closing the connection
 
} catch (UnknownHostException e) {
Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
} catch (IOException e) {
	Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
}
return null;
}
 
}

@Override
public void onBackPressed() {
	Toast.makeText(getApplicationContext(), "You Are Logged Out...", Toast.LENGTH_LONG).show();
    startActivity(new Intent("com.baba.chat.FIRSTMAINACTIVITY"));
    finish();
}






}
	


>>>>>>> parent of 133152e... Upto demo doubtsession with working testconnection
