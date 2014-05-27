package com.baba.chat;


import java.io.BufferedReader; 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class TestConnection extends Activity
{
	private CharSequence status = null;
	Button b;
	EditText e1,e2;
	String ip;
	String sid,username,roll,macid;
	
	private Socket client;
	private PrintWriter printwriter;
	
	Socket socket = null;
	DataOutputStream dataOutputStream = null;
	DataInputStream dataInputStream = null;
	
	StringBuffer br=new StringBuffer();  //For Appending purpose
	
	TextView textIn;
	
	
		@Override
	protected void onCreate(Bundle kaushik) 
	{
			// TODO Auto-generated method stub
		super.onCreate(kaushik);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.test);
		b=(Button)findViewById(R.id.testbutton);
		e1=(EditText)findViewById(R.id.iptest);
		e2=(EditText)findViewById(R.id.sidtest);
		textIn=(TextView)findViewById(R.id.read);
		
		//Getting username and roll from previous activity
		
		username = getIntent().getExtras().getString("username1");
		roll = getIntent().getExtras().getString("roll1");
		
		// getting macid 
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		macid = wInfo.getMacAddress();
	
		
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
						
						Intent rc=new Intent("com.baba.chat.DOUBT");
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
		       
		        startActivity(new Intent("com.baba.chat.FIRSTMAINACTIVITY"));
		        finish();
		    }
		
}