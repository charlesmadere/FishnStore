package com.charlesmadere.android.fishnstore;


import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.charlesmadere.android.fishnstore.utilities.Utilities;


public class SaveCurrentLocationActivity extends Activity
{


	private final static String LOG_TAG = Utilities.LOG_TAG + " - SaveCurrentLocationActivity";




	/**
	 * 
	 */
	private boolean locationBeingFound = false;


	/**
	 * 
	 */
	private LocationManager locationManager;


	/**
	 * 
	 */
	private LocationListener locationListener;




	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_current_location_activity);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationListener = new LocationListener()
		{
			@Override
			public void onLocationChanged(final Location location)
			{
				locationBeingFound = false;
				locationManager.removeUpdates(locationListener);
				Log.d(LOG_TAG, "Location: \"" + location.toString() + "\"");
				invalidateOptionsMenu();
			}


			@Override
			public void onProviderDisabled(final String provider)
			{
				
			}


			@Override
			public void onProviderEnabled(final String provider)
			{
				
			}


			@Override
			public void onStatusChanged(final String provider, final int status, final Bundle extras)
			{
				
			}
		};

		refreshLocation();
	}


	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		getMenuInflater().inflate(R.menu.save_current_location_activity, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				break;

			case R.id.save_current_location_activity_activity_menu_refresh:
				refreshLocation();
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}


	@Override
	public boolean onPrepareOptionsMenu(final Menu menu)
	{
		menu.findItem(R.id.save_current_location_activity_activity_menu_refresh).setEnabled(!locationBeingFound);
		return super.onPrepareOptionsMenu(menu);
	}




	/**
	 * 
	 */
	private void refreshLocation()
	{
		if (!locationBeingFound)
		{
			locationBeingFound = true;
			invalidateOptionsMenu();
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		}
	}


}
