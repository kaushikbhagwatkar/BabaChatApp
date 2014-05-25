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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class TestConnection extends Activity
{
Button b;
EditText e1,e2;
String ip;
String sid;

Socket socket = null;
DataOutputStream dataOutputStream = null;
DataInputStream dataInputStream = null;

StringBuffer br=new StringBuffer();  //For Appending purpose

TextView textIn;

	
	@Override
	protected void onCreate(Bundle kaushik) {
		// TODO Auto-generated method stub
		super.onCreate(kaushik);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.test);
		b=(Button)findViewById(R.id.testbutton);
		e1=(EditText)findViewById(R.id.iptest);
		e2=(EditText)findViewById(R.id.sidtest);
		textIn=(TextView)findViewById(R.id.read);
		
		
	
	

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

}
	


