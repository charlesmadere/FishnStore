package com.charlesmadere.android.fishnspots;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
	private Button button_updateLocation;


	/**
	 * The SimpleLocation object that this Fragment will be updating.
	 */
	private SimpleLocation location;


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

		final TextWatcher textWatcher = new TextWatcher()
		{
			@Override
			public void afterTextChanged(final Editable s)
			{
				findViews();

				if (editText_name.length() >= 1 && editText_altitude.length() >= 1
					&& editText_latitude.length() >= 1 && editText_longitude.length() >= 1)
				{
					button_updateLocation.setEnabled(true);
				}
				else
				{
					button_updateLocation.setEnabled(false);
				}
			}

			@Override
			public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after)
			{

			}


			@Override
			public void onTextChanged(final CharSequence s, final int start, final int before, final int count)
			{

			}
		};

		editText_name.addTextChangedListener(textWatcher);
		editText_altitude.addTextChangedListener(textWatcher);
		editText_latitude.addTextChangedListener(textWatcher);
		editText_longitude.addTextChangedListener(textWatcher);

		button_updateLocation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				location.setName(editText_name.getText().toString());
				location.setAltitude(editText_altitude.getText().toString());
				location.setLatitude(editText_latitude.getText().toString());
				location.setLongitude(editText_longitude.getText().toString());

				listeners.onLocationUpdate(location);
			}
		});

		flushViews();
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




	/**
	 * Checks to see if all of the layout items that we're using from this
	 * class's layout have been found (and are not null). If any single one has
	 * not been found (and is therefore null), then this method will find every
	 * single layout item.
	 */
	private void findViews()
	{
		if (editText_name == null || editText_altitude == null || editText_latitude == null
			|| editText_longitude == null || button_updateLocation == null)
		{
			final View view = getView();

			editText_name = (EditText) view.findViewById(R.id.update_location_fragment_edittext_name);
			editText_altitude = (EditText) view.findViewById(R.id.update_location_fragment_edittext_altitude);
			editText_latitude = (EditText) view.findViewById(R.id.update_location_fragment_edittext_latitude);
			editText_longitude = (EditText) view.findViewById(R.id.update_location_fragment_edittext_longitude);
			button_updateLocation = (Button) view.findViewById(R.id.update_location_fragment_button_update_location);
		}
	}


	/**
	 * Sets the text for all of this layout's TextView item's to the
	 * actual data in the SimpleLocation object.
	 */
	private void flushViews()
	{
		editText_name.setText(location.getName());
		editText_altitude.setText(String.valueOf(location.getAltitude()));
		editText_latitude.setText(String.valueOf(location.getLatitude()));
		editText_longitude.setText(String.valueOf(location.getLongitude()));
	}


}
