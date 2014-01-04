package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainScreenActivity extends Activity{
	
	Button btnViewSongs;
	Button btnNewSong;
	Button btnDownloadSong;
	Button btnChat;
	private static final String TAG_URL = "url";
	private static final String TAG_URLa = "urla";
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
		Bundle bundle = getIntent().getExtras();
		final String address = bundle.getString(TAG_URL);
		System.out.println(address);
		// Buttons
		btnViewSongs = (Button) findViewById(R.id.btnViewSongs);
		btnDownloadSong = (Button) findViewById(R.id.btnDownloadSong);
		btnChat = (Button) findViewById(R.id.btnChat);
		
		
           btnDownloadSong.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				System.out.println("download function");
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(), Download.class);
				i.putExtra(TAG_URLa, address);
				System.out.println(address);
				startActivity(i);
				                      
			}
		});
           btnChat.setOnClickListener(new View.OnClickListener() {
   			
   			@Override
   			public void onClick(View view) {
   				System.out.println("chat function");
   				// Launching All products Activity
   				Intent i = new Intent(getApplicationContext(), ChatActivity.class);
   				A.set(address);
   	//			System.out.println(address);
   				startActivity(i);
   				
   			}
   		});
		
		// view products click event
		btnViewSongs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// Launching All products Activity
				Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
				i.putExtra(TAG_URLa, address);
				System.out.println(address);
				startActivity(i);
				
			}
		});
		
		
		}
}
