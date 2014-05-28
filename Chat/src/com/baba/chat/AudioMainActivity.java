package com.baba.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AudioMainActivity extends Activity implements OnClickListener {

	Socket s, socket;
	BufferedReader br;
	PrintWriter pw;
	final Context context = this;
	String serverResponse, option, textMsg, textSubject, macAddress;
	Intent confirm;
	ImageButton raiseHandAudio;
	EditText doubtText, doubtSubject;
	Button sendDoubtText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_audiotext);
		init(); // INITIALIZING VARIABLES
		raiseHandAudio.setOnClickListener(this); // LISTENER FOR AUDIO DOUBT
													// BUTTON
		sendDoubtText.setOnClickListener(this); // LISTENER FOR TEXT DOUBT
												// BUTTON
	}

	public void init() {
		raiseHandAudio = (ImageButton) findViewById(R.id.audio_doubt_button);
		doubtText = (EditText) findViewById(R.id.text_doubt_msg);
		sendDoubtText = (Button) findViewById(R.id.send_text_doubt);
		doubtSubject = (EditText) findViewById(R.id.doubt_subject);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.send_text_doubt:
			textMsg = doubtText.getText().toString(); // READ MESSAGE
			textSubject = doubtSubject.getText().toString(); // READ SUBJECT
			if (textMsg.isEmpty() || textSubject.isEmpty()) { // CHECK IF ANY OF
																// THE TWO FIELD
																// IS EMPTY
				Toast.makeText(AudioMainActivity.this, "All fields are mandatory",
						Toast.LENGTH_LONG).show();
			} else {
				createDialog(); // CREATE A CONFIRMATION DIALOG
			}
			break;
		case R.id.audio_doubt_button:
			confirm = new Intent(AudioMainActivity.this, AudioDoubt.class); // START
																		// AUDIO
																		// DOUBT
																		// SESSION
			startActivity(confirm);
			break;
		}
	}

	private void createDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("Submit this message?");
		builder.setMessage("Subject:- " + textSubject + "\nDoubt:- \n"
				+ textMsg);

		builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss(); // DISMISS DIALOG IF NO IS CLICKED
			}
		});
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.e("yes", "yes");
				sendTextRequest(); // SEND DOUBT IF YES IS CLICKED
			}
		});
		AlertDialog alert = builder.create(); // CREATE ALERT DIALOG
		alert.show();
	}

	private void sendTextRequest() {
		// TODO Auto-generated method stub

		Toast.makeText(getBaseContext(), "Sending...", Toast.LENGTH_LONG)
				.show();
		// public class ClientThread implements Runnable {
		new Thread() {

			public void run() {
				// TODO Auto-generated method stub

				try {

					socket = new Socket("10.105.14.252", 8899);

					// Log.e("ClientActivity", "C: Sending command.");
					pw = new PrintWriter(socket.getOutputStream());

					pw.println(doubtSubject.getText().toString()); // SEND
																	// SUBJECT
					pw.println(doubtText.getText().toString()); // SEND
																// MESSAGE
					pw.println(getMacAddress()); // SEND MAC ID
					pw.flush();

					br = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));

					String msgServer = br.readLine(); // RECEIVE
														// CONFIRMATION
														// IF MESSAGE
														// RECEIVED BY
														// SERVER

					if (msgServer.contains("received")) {
						Toast.makeText(AudioMainActivity.this, "Doubt Sent",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(AudioMainActivity.this,
								"Server Error! Doubt not sent!",
								Toast.LENGTH_SHORT).show();
					}
				}
				catch (Exception e) {
					e.printStackTrace();

				}
				finally {
					if (socket != null) {
						try {
							// close socket connection after using it
							socket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}// End run

		}.start();		//MESSAGE SENDING THREAD STARTED
	}

	private String getMacAddress() {	//GET MAC ADDRESS ( WIFI CONNECTION NEEDED )
		// TODO Auto-generated method stub
		WifiManager wifiManager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wInfo = wifiManager.getConnectionInfo();
		return wInfo.getMacAddress();
	}

}

