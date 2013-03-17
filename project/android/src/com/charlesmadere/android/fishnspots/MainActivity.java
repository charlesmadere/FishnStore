package com.charlesmadere.android.fishnspots;


import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


/**
 * This class is the app's entry point.
 */
public class MainActivity extends Activity implements
	LocationListFragment.LocationListFragmentListeners,
	SaveCurrentLocationFragment.SaveCurrentLocationListeners
{


	private LocationListFragment locationListFragment;
	private SaveCurrentLocationFragment saveCurrentLocationFragment;




	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);

		final FragmentManager fManager = getFragmentManager();
		final FragmentTransaction fTransaction = fManager.beginTransaction();

		if (savedInstanceState == null)
		{
			if (isDeviceLarge())
			{
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_fragment_location_list_fragment);
			}
			else
			{
				locationListFragment = new LocationListFragment();
				fTransaction.add(R.id.main_activity_container, locationListFragment);
			}

			fTransaction.commit();
		}

		refreshActionBar();
	}


	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		refreshActionBar();
	}




	/**
	 * Checks to see what size screen this device has. This method will return
	 * true if the device has a large screen, and as such said device should be
	 * using the dual pane, fragment based, layout stuff.
	 * 
	 * @return
	 * Returns true if this device has a large screen.
	 */
	private boolean isDeviceLarge()
	{
		return findViewById(R.id.main_activity_container) == null;
	}


	/**
	 * Updates the title shown in the Action Bar as well as whether or not the
	 * back arrow is showing.
	 */
	private void refreshActionBar()
	{
		final ActionBar actionBar = getActionBar();

		if (isDeviceLarge())
		{
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setTitle(R.string.fish_n_spots);
		}
		else
		{
			try
			{
				final FragmentManager fManager = getFragmentManager();
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_container);

				actionBar.setDisplayHomeAsUpEnabled(false);
				actionBar.setTitle(R.string.fish_n_spots);
			}
			catch (final ClassCastException e)
			{
				actionBar.setDisplayHomeAsUpEnabled(true);
				actionBar.setTitle(R.string.save_current_location);
			}
		}
	}




	@Override
	public void onClickSaveCurrentLocation()
	{
		final FragmentManager fManager = getFragmentManager();
		final FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.addToBackStack(null);

		if (locationListFragment == null)
		{
			if (isDeviceLarge())
			{
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_fragment_location_list_fragment);
			}
			else
			{
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_container);
			}
		}

		saveCurrentLocationFragment = new SaveCurrentLocationFragment();

		if (isDeviceLarge())
		{
			saveCurrentLocationFragment.show(fManager, SaveCurrentLocationFragment.TAG);
		}
		else
		{
			fTransaction.remove(locationListFragment);
			fTransaction.add(R.id.main_activity_container, saveCurrentLocationFragment);
			fTransaction.commit();

			fManager.executePendingTransactions();
		}

		refreshActionBar();
	}


	@Override
	public void onLocationSave(final SimpleLocation location)
	{
		final FragmentManager fManager = getFragmentManager();
		fManager.popBackStack();

		if (saveCurrentLocationFragment == null)
		{
			saveCurrentLocationFragment = (SaveCurrentLocationFragment) fManager.findFragmentByTag(SaveCurrentLocationFragment.TAG);
		}

		final FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.remove(saveCurrentLocationFragment);
		fTransaction.commit();

		fManager.executePendingTransactions();

		if (locationListFragment == null)
		{
			if (isDeviceLarge())
			{
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_fragment_location_list_fragment);
			}
			else
			{
				locationListFragment = (LocationListFragment) fManager.findFragmentById(R.id.main_activity_container);
			}
		}

		locationListFragment.createSimpleLocation(location);

		refreshActionBar();
	}


}
