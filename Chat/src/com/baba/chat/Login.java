package com.baba.chat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Login extends Activity
{
	CheckBox cb;
	ImageButton login;
	ImageView fblogin,donate;
	
	
	
	
	@Override
	protected void onCreate(Bundle babaji) {
		// TODO Auto-generated method stub
		super.onCreate(babaji);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		// Buttons and checkbox working
		login=(ImageButton)findViewById(R.id.loginbutton);
		fblogin=(ImageView)findViewById(R.id.fblogin);
		donate=(ImageView)findViewById(R.id.donatebutton);
		
		cb=(CheckBox)findViewById(R.id.checkBox1);
		
		login.setEnabled(false);
		
		
		// Checkbox managing
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		            login.setAlpha((float)1.0);
		            login.setEnabled(true);
		        }

		        else
		        {   login.setAlpha((float) 0.5);
		        		login.setEnabled(false);
		        }
		    }
		});
		
		
		// login button
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				Intent i=new Intent("com.baba.chat.MID");
				startActivity(i);
				
			}
		});
		
		// fblogin button
		
				fblogin.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						Toast.makeText(getApplicationContext(), "Go Away FB Addict...",Toast.LENGTH_SHORT).show();
						
					}
				});
				
				// Donate button
				
				donate.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						Toast.makeText(getApplicationContext(), "Baba's Ashirwad is With U....",Toast.LENGTH_SHORT).show();
						
					}
				});	
			
		
		
		
	}
	
	
	
	
	
	
}
