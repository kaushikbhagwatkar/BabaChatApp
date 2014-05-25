package com.baba.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;


public class Signin extends Activity implements OnItemSelectedListener {

	InputStream in,in1,in2,in3;
	BufferedReader reader;
	String line,line1,line2,line3;
	TextView t1;
	EditText e1,e2,e3,e4;
	Button savebutton;
	
	 Spinner namespin;
		// TextView selVersion;
		
	
	 
	 String[] state = {"","","","","","","","","",""};
	 String mypath;
	 int statecount;

	
	@Override
	protected void onCreate(Bundle kkrcker) {
		
		
	// TODO Auto-generated method stub
		super.onCreate(kkrcker);
		setContentView(R.layout.signin);
		
		
		mypath=Environment.getExternalStorageDirectory().toString()+"/AakashApp/";
		File rootfile = new File(mypath);
		File[] users; 
	    users = rootfile.listFiles();
	       int sd=0;statecount=0;
	    while (sd<4)
	    {
	    	if (users[sd].isDirectory())
	    	{
	    		state[statecount]=users[sd].getName();
	    		statecount++;
	    	}
	    	sd++;
	    }
	        
		
		namespin = (Spinner)findViewById(R.id.namespinner);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, state);
		adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		namespin.setAdapter(adapter_state);
		namespin.setOnItemSelectedListener(this);
		
		
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
