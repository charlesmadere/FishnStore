package com.charlesmadere.android.fishnspots;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
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
	private Button button_editLocation;
	private Button button_deleteLocation;


	/**
	 * The SimpleLocation object that this Fragment will be viewing.
	 */
	private SimpleLocation location;


	




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

		final Bundle arguments = getArguments();

		location = new SimpleLocation
		(
			arguments.getLong(KEY_LOCATION_ID),
			arguments.getString(KEY_LOCATION_NAME),
			arguments.getDouble(KEY_LOCATION_ALTITUDE),
			arguments.getDouble(KEY_LOCATION_LATITUDE),
			arguments.getDouble(KEY_LOCATION_LONGITUDE)
		);

		findViews();

		button_editLocation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				
			}
		});

		button_deleteLocation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				
			}
		});

		flushViews();
	}




	/**
	 * Checks to see if all of the layout items that we're using from this
	 * class's layout have been found (and are not null). If any single one has
	 * not been found (and is therefore null), then this method will find every
	 * single layout item.
	 */
	private void findViews()
	{
		if (textView_name == null || textView_altitude == null || textView_latitude == null
			|| textView_longitude == null || button_editLocation == null || button_deleteLocation == null)
		{
			final View view = getView();

			textView_name = (TextView) view.findViewById(R.id.view_location_fragment_textview_name);
			textView_altitude = (TextView) view.findViewById(R.id.view_location_fragment_textview_altitude);
			textView_latitude = (TextView) view.findViewById(R.id.view_location_fragment_textview_latitude);
			textView_longitude = (TextView) view.findViewById(R.id.view_location_fragment_textview_longitude);
			button_editLocation = (Button) view.findViewById(R.id.view_location_fragment_button_edit_location);
			button_deleteLocation = (Button) view.findViewById(R.id.view_location_fragment_button_delete_location);
		}
	}


	/**
	 * Sets the text for all of this layout's TextView item's to the
	 * actual data in the SimpleLocation object.
	 */
	private void flushViews()
	{
		textView_name.setText(location.getName());
		textView_altitude.setText(String.valueOf(location.getAltitude()));
		textView_latitude.setText(String.valueOf(location.getLatitude()));
		textView_longitude.setText(String.valueOf(location.getLongitude()));
	}


}
