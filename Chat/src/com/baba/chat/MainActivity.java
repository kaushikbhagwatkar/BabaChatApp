package com.baba.chat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
 
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
private Socket client;
private PrintWriter printwriter;
private EditText textField;
private TextView tview,tview2,tview123;
private Button button;
private String messsage;
InputStream in;
BufferedReader reader;
String line;
 
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_slimple_text_client);

try{
	in = new FileInputStream(Environment.getExternalStorageDirectory()+"/ChatApp/config.txt");
    reader = new BufferedReader(new InputStreamReader(in));
    line = reader.readLine();in.close();
	}
	catch(Exception e)
	{
		//Toast.makeText(getApplicationContext(), "Exception Occured..", Toast.LENGTH_SHORT).show();
	}
	


textField = (EditText) findViewById(R.id.uname); // reference to the text field
button = (Button) findViewById(R.id.startchat); // reference to the send button
tview=(TextView)findViewById(R.id.history);
tview2=(TextView)findViewById(R.id.tv2);
tview123=(TextView)findViewById(R.id.textView123);
tview123.setVisibility(View.GONE);
// Button press event listener
button.setOnClickListener(new View.OnClickListener() {
 
public void onClick(View v) {
	InputMethodManager inputManager = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE); 

inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
               InputMethodManager.HIDE_NOT_ALWAYS);	
	
messsage = textField.getText().toString(); // get the text message on the text field
textField.setText(""); // Reset the text field to blank
SendMessage sendMessageTask = new SendMessage();
sendMessageTask.execute();
button.setText("SEND");
tview2.setText("Enter Message");
tview.setText(tview.getText()+"\n # "+messsage);
tview123.setVisibility(View.VISIBLE);

	

}

	
	
});
}
 
private class SendMessage extends AsyncTask<Void, Void, Void> {
 
@Override
protected Void doInBackground(Void... params) {
try {
 
client = new Socket("10.105.14.34", 4444); // connect to the server
printwriter = new PrintWriter(client.getOutputStream(), true);
printwriter.write(messsage); // write the message to output stream
 
printwriter.flush();
printwriter.close();
client.close(); // closing the connection
 
} catch (Exception e) {
	Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_SHORT).show();
} 
return null;
}
 
}
 
@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
//getMenuInflater().inflate(R.menu.slimple_text_client, menu);
return true;
}
 
}