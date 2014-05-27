package com.baba.chat;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.*;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Login extends FragmentActivity
{
	CheckBox acceptRules;
	Button register;
	ImageView dp;
	Button selectPic;
	EditText username,password,rollno;
	String tagdapath=null;
	
//
	private static final int REQUEST_CAMERA = 0;
	private static final int SELECT_FILE = 1;
	ImageView ivImage;
	Button btnSelectPhoto1;
	
	
	
	
	@Override
	protected void onCreate(Bundle babaji) {
		// TODO Auto-generated method stub
		super.onCreate(babaji);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		setContentView(R.layout.login);
			this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		// Buttons and checkbox working
		register=(Button)findViewById(R.id.regbuttonk);
		dp=(ImageView)findViewById(R.id.pic);
		//selectPic=(Button)findViewById(R.id.picselect);
		acceptRules=(CheckBox)findViewById(R.id.acceptk);
		
		username=(EditText)findViewById(R.id.uname);
		password=(EditText)findViewById(R.id.passk);
		rollno=(EditText)findViewById(R.id.rollnok);
	
		
		register.setEnabled(false);
		
		
		// Selecting Pic
		
		dp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				selectImage();
			
			}
		});
		
		
		
		// Checkbox managing
		acceptRules.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	register.setAlpha((float)1.0);
		        	register.setEnabled(true);
		        }

		        else
		        {   register.setAlpha((float) 0.5);
		        	register.setEnabled(false);
		        }
		    }
		});
		
		
		// Register button
		
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				

				InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE); 

				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                           InputMethodManager.HIDE_NOT_ALWAYS);
				// TODO Auto-generated method stub
			
				String name= username.getText().toString();
				String roll = rollno.getText().toString();
				String pass = password.getText().toString();
				
				
				
				File dir = new File(Environment.getExternalStorageDirectory().toString()+"/AakashApp/"+name);
				
				// Validation of all fields
				if (name.equals("")||roll.equals("")||pass.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please Fill All The Fields", Toast.LENGTH_SHORT).show();
					
				}
				
				else if (tagdapath==null||tagdapath.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please Select Your Profile Pic", Toast.LENGTH_SHORT).show();
					
				}
				
				
				else if (dir.exists() && dir.isDirectory())
				{
					Toast.makeText(getApplicationContext(), "User Already Exists\nPlease Choose A Different Username ", Toast.LENGTH_SHORT).show();
				}
				
				
				else{
				
				//Here it starts // Making directory of user's name and putting apt files inside
				
				
				PrintWriter writer,writer1,writer2,writer3;
				
				String currentPath=Environment.getExternalStorageDirectory().toString()+"/AakashApp/"+name+"/";
				File folder = new File(currentPath);
				  folder.mkdirs();
				  
				  try {
					  
					  new File(folder, "user.txt");	
					  new File(folder,"pass.txt");
					  new File(folder,"roll.txt");
					  
					  	writer = new PrintWriter(currentPath+"user.txt");
						writer.print(name);
						writer1 = new PrintWriter(currentPath+"pass.txt");
						writer1.print(pass);
						writer2 = new PrintWriter(currentPath+"roll.txt");
						writer2.print(roll);
						writer.close();writer1.close();writer2.close();
					  
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
				  
				// Copying Started of pic
					
					
					File newFile= new File(tagdapath);
					try{
						Toast.makeText(getApplicationContext(), tagdapath.toString(), Toast.LENGTH_LONG).show();
					InputStream ini = new FileInputStream(newFile);
					File fl=new File(currentPath,"dp.jpg");
					fl.createNewFile();
					
	        		    OutputStream outi = new FileOutputStream(currentPath+"dp.jpg");
	        		 
	        		    // Transfer bytes from in to out
	        		    byte[] buf = new byte[1024];
	        		    int len;
	        		    while ((len = ini.read(buf)) > 0) {
	        		        outi.write(buf, 0, len);
	        		    }
	        		    ini.close();
	        		    outi.close();
	        		 }
	        		 
	        		 catch(Exception e)
	        		 {
	        			 Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
	        		 }
					
					// Copying ended
				
				
				//  Going for checking connection
				
				Intent i=new Intent("com.baba.chat.TESTCONNECTION");
				startActivity(i);
				finish();
				
				// Here it ends
				
				}
			}
		});		
		
	}
	
	
	// Selectimage Function for selecting dp
	 void selectImage() {
		final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

		
		AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
		builder.setTitle("Add Photo!");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {
				
				if (items[item].equals("Take Photo")) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File f = new File(android.os.Environment
							.getExternalStorageDirectory(), "temp.jpg");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
					startActivityForResult(intent, REQUEST_CAMERA);
				}
				
				
				else if (items[item].equals("Choose from Library")) {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					intent.setType("image/*");
					startActivityForResult(
							Intent.createChooser(intent, "Select File"),
							SELECT_FILE);
				} 
				
				else if (items[item].equals("Cancel")) {
					dialog.dismiss();
				}
				
				
			}
		});
		builder.show();
	}
	
	
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			String tempPath;
			
			
			
			if (resultCode == RESULT_OK) {
				
				
				
				
				
				if (requestCode == REQUEST_CAMERA) {
					File f = new File(Environment.getExternalStorageDirectory()
							.toString());
					for (File temp : f.listFiles()) {
						if (temp.getName().equals("temp.jpg")) {
							f = temp;
							break;
						}
					}
					try {
						Bitmap bm;
						BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
						tempPath=f.getAbsolutePath();
						tagdapath=tempPath;
						
						bm = BitmapFactory.decodeFile(tempPath,
								btmapOptions);

						// Setting view to bmp image created
						dp.setImageBitmap(bm);
						
						

						String path = android.os.Environment
								.getExternalStorageDirectory()
								+ File.separator
								+ "Phoenix" + File.separator + "default";
						//f.delete();
						OutputStream fOut = null;
						File file = new File(path, String.valueOf(System
								.currentTimeMillis()) + ".jpg");
						try {
							fOut = new FileOutputStream(file);
							bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
							fOut.flush();
							fOut.close();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				
				else if (requestCode == SELECT_FILE) {
					Uri selectedImageUri = data.getData();

					 tempPath = getPath(selectedImageUri,Login.this);
					 tagdapath=tempPath;
					Bitmap bm;
					BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
					bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
					dp.setImageBitmap(bm);
					
					
				}
				
				
				
			}
		}
		
		
		public String getPath(Uri uri, Activity activity) {
			String[] projection = { MediaColumns.DATA };
			Cursor cursor = activity
					.managedQuery(uri, projection, null, null, null);
			int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
			cursor.moveToFirst();
			return cursor.getString(column_index);
		}
		
	    @Override
	    public void onBackPressed() {
	       
	        startActivity(new Intent("com.baba.chat.FIRSTMAINACTIVITY"));
	        finish();
	    }


	
	
}
