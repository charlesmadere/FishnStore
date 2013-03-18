package com.charlesmadere.android.fishnspots;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


public class ViewLocationFragment extends DialogFragment
{


	public final static String TAG = "ViewLocationFragment";

	public final static String KEY_LOCATION_ID = "KEY_LOCATION_ID";
	public final static String KEY_LOCATION_NAME = "KEY_LOCATION_NAME";
	public final static String KEY_LOCATION_ALTITUDE = "KEY_LOCATION_ALTITUDE";
	public final static String KEY_LOCATION_LATITUDE = "KEY_LOCATION_LATITUDE";
	public final static String KEY_LOCATION_LONGITUDE = "KEY_LOCATION_LONGITUDE";




	private TextView textView_name;
	private TextView textView_altitude;
	private TextView textView_latitude;
	private TextView textView_longitude;


	/**
	 * The SimpleLocation object that this Fragment will be viewing.
	 */
	private SimpleLocation simpleLocation;




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
		return inflater.inflate(R.layout.view_location_fragment, null);
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


}
