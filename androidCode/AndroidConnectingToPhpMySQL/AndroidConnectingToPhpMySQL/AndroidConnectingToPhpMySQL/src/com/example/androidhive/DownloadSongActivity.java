package com.example.androidhive;
 
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
 
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
 
public class DownloadSongActivity extends Activity {
 
    // button to show progress dialog
    Button btnShowProgress;
    public static String newString;
 
    // Progress Dialog
    private ProgressDialog pDialog;
    ImageView my_image;
    
    public static final int progress_bar_type = 0;
    private static final String TAG_NAME = "name";
    private static final String URL = "url";
	private static final String TAG_URLb = "urlb";
	String file_url;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_product);
        
        Bundle bundle = getIntent().getExtras();
		newString = bundle.getString(TAG_NAME);
		
		Bundle bundle1 = getIntent().getExtras();
		final String URL = bundle.getString(TAG_URLb);

		final String file_url = "http://"+ URL +"/android_connect/songs/" + newString + ".mp3";
		     // show progress bar button
        btnShowProgress = (Button) findViewById(R.id.btnDownload);
        // Image view to show image after downloading
        my_image = (ImageView) findViewById(R.id.my_image);
        /**
         * Show Progress bar click event
         * */
        btnShowProgress.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                // starting new Async Task
                new DownloadFileFromURL().execute(file_url);
            }
        });
    }
 
    /**
     * Showing Dialog
     * */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case progress_bar_type: // we set this to 0
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Downloading file. Please wait...");
            
            pDialog.setCancelable(true);
            pDialog.show();
            
            return pDialog;
            
        default:
            return null;
        }
    }
 
    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {
 
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }
 
        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                String url = new String(f_url[0]);
                
              System.out.println(url);
             String output = "/sdcard";
              
              HttpDownloadUtility.downloadFile(url, output);
                  } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
 
            return null;
        }/**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);
             // Displaying downloaded image into image view
            // Reading image path from sdcard
            String imagePath = Environment.getExternalStorageDirectory().toString() + "/" + newString + ".mp3";
            // setting downloaded into image view
            my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        }
 
    }
}