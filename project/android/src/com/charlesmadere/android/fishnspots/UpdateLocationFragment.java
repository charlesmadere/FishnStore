package com.charlesmadere.android.fishnspots;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


public class UpdateLocationFragment extends DialogFragment
{


	public final static String TAG = "UpdateLocationFragment";

	public final static String KEY_LOCATION_ID = "KEY_LOCATION_ID";
	public final static String KEY_LOCATION_NAME = "KEY_LOCATION_NAME";
	public final static String KEY_LOCATION_ALTITUDE = "KEY_LOCATION_ALTITUDE";
	public final static String KEY_LOCATION_LATITUDE = "KEY_LOCATION_LATITUDE";
	public final static String KEY_LOCATION_LONGITUDE = "KEY_LOCATION_LONGITUDE";




	private EditText editText_name;
	private EditText editText_altitude;
	private EditText editText_latitude;
	private EditText editText_longitude;
	private Button button_saveLocationChanges;


	/**
	 * The SimpleLocation object that this Fragment will be updating.
	 */
	private SimpleLocation simpleLocation;


	/**
	 * Object that allows us to run any of the methods that are defined in the
	 * UpdateLocationFragmentListeners interface.
	 */
	private UpdateLocationFragmentListeners listeners;


	/**
	 * A bunch of listener methods for this Fragment.
	 */
	public interface UpdateLocationFragmentListeners
	{


		/**
		 * This is fired whenever the user clicks the Save Location Changes
		 * button in this Fragment's layout.
		 * 
		 * @param location
		 * The SimpleLocation object as edited by the user.
		 */
		public void onLocationUpdate(final SimpleLocation location);


	}




	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState)
	{
		final Dialog dialog = super.onCreateDialog(savedInstanceState);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		return dialog;
	}


	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.update_location_fragment, null);
	}


	@Override
	public void onActivityCreated(final Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		simpleLocation = new SimpleLocation
		(
			savedInstanceState.getLong(KEY_LOCATION_ID),
			savedInstanceState.getString(KEY_LOCATION_NAME),
			savedInstanceState.getDouble(KEY_LOCATION_ALTITUDE),
			savedInstanceState.getDouble(KEY_LOCATION_LATITUDE),
			savedInstanceState.getDouble(KEY_LOCATION_LONGITUDE)
		);
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
			listeners = (UpdateLocationFragmentListeners) activity;
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement listeners!");
		}
	}


}
