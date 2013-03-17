package com.charlesmadere.android.fishnspots;


import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


public class EditLocationFragment extends DialogFragment
{


	public final static String KEY_LOCATION_NAME = "KEY_LOCATION_NAME";
	public final static String KEY_LOCATION_ALTITUDE = "KEY_LOCATION_ALTITUDE";
	public final static String KEY_LOCATION_LATITUDE = "KEY_LOCATION_LATITUDE";
	public final static String KEY_LOCATION_LONGITUDE = "KEY_LOCATION_LONGITUDE";




	/**
	 * The SimpleLocation object that this Fragment will be editing.
	 */
	private SimpleLocation simpleLocation;


	/**
	 * 
	 */
	private EditLocationFragmentListeners listeners;


	/**
	 * 
	 */
	public interface EditLocationFragmentListeners
	{


		/**
		 * 
		 * 
		 * @param simpleLocation
		 * 
		 */
		public void onLocationSave(final SimpleLocation simpleLocation);


	}




	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.edit_location_fragment, null);
	}


	@Override
	public void onActivityCreated(final Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		simpleLocation = new SimpleLocation
		(
			savedInstanceState.getString(KEY_LOCATION_NAME),
			savedInstanceState.getDouble(KEY_LOCATION_ALTITUDE),
			savedInstanceState.getDouble(KEY_LOCATION_LATITUDE),
			savedInstanceState.getDouble(KEY_LOCATION_LONGITUDE)
		);
	}


	@Override
	public void onAttach(final Activity activity)
	{
		super.onAttach(activity);

		try
		{
			listeners = (EditLocationFragmentListeners) activity;
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement listeners!");
		}
	}


}
