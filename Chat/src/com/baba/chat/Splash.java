package com.baba.chat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity
{

	
	@Override
	protected void onCreate(Bundle babarocks) {
		// TODO Auto-generated method stub
		super.onCreate(babarocks);
		setContentView(R.layout.splash);
		
		Thread timer1 =new Thread()
		{
			public void run()
			{
				try {
					sleep(3500);
					} 
				catch (InterruptedException e)
					{
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				finally
				{
					Intent openactivity1 =new Intent("com.baba.chat.LOGIN");
					startActivity(openactivity1);
				}
			}
			
		};
		timer1.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		finish();
	}
	
	
	
	
}
