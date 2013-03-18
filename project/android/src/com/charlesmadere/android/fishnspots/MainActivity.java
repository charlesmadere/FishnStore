package com.charlesmadere.android.fishnspots;


import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


/**
 * This class is the app's entry point.
 */
public class MainActivity extends Activity implements
	CreateLocationFragment.CreateLocationListeners,
	LocationListFragment.LocationListFragmentListeners,
	UpdateLocationFragment.UpdateLocationFragmentListeners
{


	private CreateLocationFragment createLocationFragment;
	private LocationListFragment locationListFragment;
	private UpdateLocationFragment updateLocationFragment;
	private ViewLocationFragment viewLocationFragment;




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


	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
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
			// TODO
			// set up showing titles for other fragments
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
				final int actionBarTitle;

				if (createLocationFragment != null && createLocationFragment.isVisible())
				{
					actionBarTitle = R.string.create_location;
				}
				else if (updateLocationFragment != null && updateLocationFragment.isVisible())
				{
					actionBarTitle = R.string.update_location;
				}
				else
				{
					actionBarTitle = R.string.view_location;
				}

				actionBar.setTitle(actionBarTitle);
			}
		}
	}


	/**
	 * Transitions from the currently being shown Fragment back to the
	 * LocationList Fragment.
	 * 
	 * @param fragment
	 * The Fragment currently being shown.
	 * 
	 * @param tag
	 * The tag to refer to the Fragment by.
	 */
	private void transitionBackToLocationList(Fragment fragment, final String tag)
	{
		final FragmentManager fManager = getFragmentManager();
		fManager.popBackStack();

		if (fragment == null)
		{
			fragment = fManager.findFragmentByTag(tag);
		}

		final FragmentTransaction fTransaction = fManager.beginTransaction();
		fTransaction.remove(fragment);
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

		refreshActionBar();
	}


	/**
	 * Transitions the screen from one Fragment to another. If the device has a
	 * large screen, then the Fragment will be shown as a dialog. Otherwise the
	 * Fragment will take up the entire screen.
	 * 
	 * @param fragment
	 * The Fragment to transition to. Must be a Fragment that extends the
	 * Android DialogFragment class.
	 * 
	 * @param tag
	 * A tag to assign to the Fragment (in the case that it is shown as a
	 * dialog).
	 * 
	 * @param bundle
	 * Arguments to pass into the Fragment. Can be null.
	 */
	private void transitionToFragment(DialogFragment fragment, final String tag, final Bundle bundle)
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

		if (bundle != null)
		{
			fragment.setArguments(bundle);
		}

		if (isDeviceLarge())
		{
			fragment.show(fManager, CreateLocationFragment.TAG);
		}
		else
		{
			fTransaction.remove(locationListFragment);
			fTransaction.add(R.id.main_activity_container, fragment);
			fTransaction.commit();

			fManager.executePendingTransactions();
			refreshActionBar();
		}
	}




	@Override
	public void onClickCreateLocation()
	{
		createLocationFragment = new CreateLocationFragment();
		transitionToFragment(createLocationFragment, CreateLocationFragment.TAG, null);
	}


	@Override
	public void onClickUpdateLocation(final SimpleLocation location)
	{
		final Bundle bundle = new Bundle();
		bundle.putLong(UpdateLocationFragment.KEY_LOCATION_ID, location.getId());
		bundle.putString(UpdateLocationFragment.KEY_LOCATION_NAME, location.getName());
		bundle.putDouble(UpdateLocationFragment.KEY_LOCATION_ALTITUDE, location.getAltitude());
		bundle.putDouble(UpdateLocationFragment.KEY_LOCATION_LATITUDE, location.getLatitude());
		bundle.putDouble(UpdateLocationFragment.KEY_LOCATION_LONGITUDE, location.getLongitude());

		updateLocationFragment = new UpdateLocationFragment();
		transitionToFragment(updateLocationFragment, UpdateLocationFragment.TAG, bundle);
	}


	@Override
	public void onClickViewLocation(final SimpleLocation location)
	{
		final Bundle bundle = new Bundle();
		bundle.putLong(ViewLocationFragment.KEY_LOCATION_ID, location.getId());
		bundle.putString(ViewLocationFragment.KEY_LOCATION_NAME, location.getName());
		bundle.putDouble(ViewLocationFragment.KEY_LOCATION_ALTITUDE, location.getAltitude());
		bundle.putDouble(ViewLocationFragment.KEY_LOCATION_LATITUDE, location.getLatitude());
		bundle.putDouble(ViewLocationFragment.KEY_LOCATION_LONGITUDE, location.getLongitude());

		viewLocationFragment = new ViewLocationFragment();
		transitionToFragment(viewLocationFragment, ViewLocationFragment.TAG, bundle);
	}


	@Override
	public void onLocationCreate(final SimpleLocation location)
	{
		transitionBackToLocationList(createLocationFragment, CreateLocationFragment.TAG);
		locationListFragment.createSimpleLocation(location);
	}


	@Override
	public void onLocationUpdate(final SimpleLocation location)
	{
		transitionBackToLocationList(updateLocationFragment, UpdateLocationFragment.TAG);
		locationListFragment.editSimpleLocation(location);
	}


}
