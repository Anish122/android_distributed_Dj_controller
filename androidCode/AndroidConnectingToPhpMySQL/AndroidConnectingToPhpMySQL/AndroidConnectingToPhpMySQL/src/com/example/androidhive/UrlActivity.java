package com.example.androidhive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class UrlActivity extends Activity implements OnClickListener{
	
	Button btnUrl;
	private static final String TAG_URL = "url";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url);
		
		// Buttons
		btnUrl = (Button) findViewById(R.id.btnUrl);
		btnUrl.setOnClickListener(this);
		
	}
         
			public void onClick(View v) {
				
				EditText url = ((EditText) findViewById(R.id.inputUrl));
				System.out.println(url);
				String urla = url.getText().toString();
	//			System.out.println("ffdvdfv");
	//			System.out.println(urla);
				Intent i = new Intent(getApplicationContext(), MainScreenActivity.class);
				i.putExtra(TAG_URL, urla);
	//			System.out.println(TAG_URL);
				startActivity(i);
				
			}
		
		
}
