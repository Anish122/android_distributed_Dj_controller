package com.example.androidhive;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProductActivity extends Activity {

	EditText txtName;
	EditText txtLength;
	EditText txtDesc;
	EditText txtCreatedAt;
	Button btnSave;
//	Button btnDelete;

	String sid;

	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SONGS = "song";
	private static final String TAG_SID = "pid";
	private static final String TAG_NAME = "name";
	private static final String TAG_LENGTH = "length";
	private static final String TAG_DESCRIPTION = "description";
	private static final String URL = "url";
	private static final String TAG_URLb = "urlb";
	String url_product_detials;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_product);
		System.out.println("hahaha");
		
		Bundle bundle = getIntent().getExtras();
		final String URL = bundle.getString(TAG_URLb);
		System.out.println(URL);
		url_product_detials = "http://"+ URL +"/android_connect/get_song_details.php";

		
		// getting product details from intent
		Intent i = getIntent();
		
		// getting product id (pid) from intent
		sid = i.getStringExtra(TAG_SID);

		// Getting complete product details in background thread
		new GetProductDetails().execute();



	}

	/**
	 * Background Async Task to Get complete product details
	 * */
	class GetProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Loading song details. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					// Check for success tag
					int success;
					try {
						// Building Parameters
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("pid", sid));

						// getting product details by making HTTP request
						// Note that product details url will use GET request
						JSONObject json = jsonParser.makeHttpRequest(
								url_product_detials, "GET", params);

						// check your log for json response
						Log.d("Single Product Details", json.toString());
						
						// json success tag
						success = json.getInt(TAG_SUCCESS);
						if (success == 1) {
							// successfully received product details
							JSONArray productObj = json
									.getJSONArray(TAG_SONGS); // JSON Array
							
							// get first product object from JSON Array
							JSONObject product = productObj.getJSONObject(0);

							// product with this pid found
							// Edit Text
							txtName = (EditText) findViewById(R.id.inputName);
						    txtLength = (EditText) findViewById(R.id.inputLength);
							txtDesc = (EditText) findViewById(R.id.inputDesc);

							// display product data in EditText
							txtName.setText(product.getString(TAG_NAME));
							txtLength.setText(product.getString(TAG_LENGTH));
							txtDesc.setText(product.getString(TAG_DESCRIPTION));

						}else{
							// product with pid not found
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			});

			return null;
		}


		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
		}
	}

	/**
	 * Background Async Task to  Save product Details
	 * */
	class SaveProductDetails extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(EditProductActivity.this);
			pDialog.setMessage("Saving song ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}
			/**
		 * Saving product
		 * */
		protected String doInBackground(String... args) {

			// getting updated data from EditTexts
			String name = txtName.getText().toString();
			String price = txtLength.getText().toString();
			String description = txtDesc.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair(TAG_SID, sid));
			params.add(new BasicNameValuePair(TAG_NAME, name));
			params.add(new BasicNameValuePair(TAG_LENGTH, price));
			params.add(new BasicNameValuePair(TAG_DESCRIPTION, description));
				return null;
		}
	}
}
