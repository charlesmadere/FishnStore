package com.charlesmadere.android.fishnspots;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class LocationListFragment extends ListFragment
{


	/**
	 * Object that allows us to run any of the methods that are defined in the
	 * FriendsListFragmentListeners interface.
	 */
	private LocationListFragmentListeners listeners;


	/**
	 * A bunch of listener methods for this Fragment.
	 */
	public interface LocationListFragmentListeners
	{


		/**
		 * This method will be fired when the user taps the Save Current
		 * Location button on the Action Bar.
		 */
		public void onClickSaveCurrentLocation();


	}




	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.location_list_fragment, null);
	}


	@Override
	public void onAttach(final Activity activity)
	// This makes sure that the Activity containing this Fragment has
	// implemented the callback interface. If the callback interface has not
	// been implemented, an exception is thrown.
	{
		super.onAttach(activity);

		try
		{
			listeners = (LocationListFragmentListeners) activity;
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement listeners!");
		}
	}


	@Override
	public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater)
	{
		inflater.inflate(R.menu.location_list_fragment, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}


	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.location_list_fragment_menu_save_current_location:
				listeners.onClickSaveCurrentLocation();
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}


}
