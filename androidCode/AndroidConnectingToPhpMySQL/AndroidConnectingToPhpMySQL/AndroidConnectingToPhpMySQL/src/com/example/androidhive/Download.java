package com.example.androidhive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Download extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;
	
	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> songsList;

	// url to get all products list
	 

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SONGS = "songs";
	private static final String TAG_SID = "pid";
	private static final String TAG_NAME = "name";
	private static final String URL = "url";
	private static final String TAG_URLa = "urla";
	private static final String TAG_URLb = "urlb";
	
	String url_all_products;
	

	// products JSONArray
	JSONArray songs = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_list);
		Bundle bundle = getIntent().getExtras();
		final String URL = bundle.getString(TAG_URLa);
		url_all_products = "http://"+ URL +"/android_connect/get_all_songs.php";

		// Hashmap for ListView
		songsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		ListView lv = getListView();

		// on seleting single product
		// launching Edit Product Screen
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String sid = ((TextView) view.findViewById(R.id.pid)).getText()
						.toString();
				String sname = ((TextView) view.findViewById(R.id.name)).getText()
						.toString();
				System.out.println(sname);
				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						DownloadSongActivity.class);
				// sending pid to next activity
				
				in.putExtra(TAG_NAME, sname);
				in.putExtra(TAG_URLb, URL);
				System.out.println(sname);
				System.out.println("ncdjnkk");
				System.out.println(TAG_NAME);
				// starting new activity and expecting some response back
				startActivityForResult(in, 100);
			}
		});

	}

	// Response from Download Song Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Download.this);
			pDialog.setMessage("Loading songs. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					songs = json.getJSONArray(TAG_SONGS);

					// looping through All Products
					for (int i = 0; i < songs.length(); i++) {
						JSONObject c = songs.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_SID);
						String name = c.getString(TAG_NAME);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_SID, id);
						map.put(TAG_NAME, name);

						// adding HashList to ArrayList
						songsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							Download.this, songsList,
							R.layout.list_item, new String[] { TAG_SID,
									TAG_NAME},
							new int[] { R.id.pid, R.id.name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}