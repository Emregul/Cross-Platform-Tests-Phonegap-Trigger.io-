package com.example.bridge;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {
	WebView myWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 myWebView = (WebView) findViewById(R.id.webView1);
		Log.i("app","start2");
		WebSettings webSettings = myWebView.getSettings();
		
		myWebView.setWebChromeClient(new WebChromeClient() {
			  public boolean onConsoleMessage(ConsoleMessage cm) {
			    Log.d("app", cm.message() + " -- From line "
			                         + cm.lineNumber() + " of "
			                         + cm.sourceId() );
			    return true;
			  }
			});
		
		

		webSettings.setJavaScriptEnabled(true);
		Log.i("app","start");
		myWebView.addJavascriptInterface(new WebAppInterface(this), "Android");
		myWebView.loadUrl("file:///android_asset/www/index.html");
		new MessageThread().start();
	}
	public class MessageThread extends Thread
	{
		public void run()
		{
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myWebView.loadUrl("javascript:welcome('merhabalar')");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class WebAppInterface {
		Context mContext;

		/** Instantiate the interface and set the context */
		WebAppInterface(Context c) {
			mContext = c;
		}

		/** Show a toast from the web page */
		@JavascriptInterface
		public String getLocation() {
			//Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
			LocationManager locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);         
			locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000L,500.0f, new MyLocationListener());
			Location location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			double latitude=0;
			double longitude=0;
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			
			Log.i("app", " " +latitude);
			Log.i("app", " " +longitude);
			JSONObject result = new JSONObject();
			try {
				result.put("latitude",latitude);
				result.put("longitude",longitude);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String toStringed= ""+result.toString();
			
			myWebView.loadUrl("javascript:welcome("+""+toStringed+")");
			
			return toStringed;
		}
	}
	public class MyLocationListener implements LocationListener
	{
		@Override

		public void onLocationChanged(Location loc)
		{
			
		}

		@Override

		public void onProviderDisabled(String provider)
		{

		}

		@Override
		public void onProviderEnabled(String provider)
		{
		}


		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{

		}

	}

}
