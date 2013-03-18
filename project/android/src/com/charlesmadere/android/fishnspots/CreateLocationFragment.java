package com.charlesmadere.android.fishnspots;


import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charlesmadere.android.fishnspots.models.SimpleLocation;


public class CreateLocationFragment extends DialogFragment
{


	public final static String TAG = "CreateLocationFragment";


	// these values are used to restore the values of the Fragment's EditText
	// layout items when the Fragment is destroyed / recreated on orientation
	// changes
	private final static String EDIT_TEXT_NAME_KEY = "EDIT_TEXT_NAME_KEY";
	private final static String EDIT_TEXT_ALTITUDE_KEY = "EDIT_TEXT_ALTITUDE_KEY";
	private final static String EDIT_TEXT_LATITUDE_KEY = "EDIT_TEXT_LATITUDE_KEY";
	private final static String EDIT_TEXT_LONGITUDE_KEY = "EDIT_TEXT_LONGITUDE_KEY";




	private TextView textView_header;
	private EditText editText_name;
	private EditText editText_altitude;
	private EditText editText_latitude;
	private EditText editText_longitude;
	private ProgressBar progressBar_header;
	private Button button_refresh;
	private Button button_createLocation;
	private Button button_manualEntry;


	/**
	 * Indicates whether or not a location search is currently running.
	 */
	private boolean locationSearchIsRunning = false;


	private LocationManager locationManager;
	private LocationListener locationListener;


	/**
	 * Object that allows us to run any of the methods that are defined in the
	 * CreateLocationListeners interface.
	 */
	private CreateLocationListeners listeners;


	/**
	 * A bunch of listener methods for this Fragment.
	 */
	public interface CreateLocationListeners
	{


		/**
		 * This is fired whenever the user clicks the Save this Location button
		 * in this Fragment's layout.
		 * 
		 * @param simpleLocation
		 * The SimpleLocation object as created from the Location object which
		 * was found by the Android GPS system.
		 */
		public void onLocationCreate(final SimpleLocation simpleLocation);


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
		return inflater.inflate(R.layout.create_location_fragment, container, false);
	}


	@Override
	public void onActivityCreated(final Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

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
					button_createLocation.setEnabled(true);
				}
				else
				{
					button_createLocation.setEnabled(false);
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

		button_refresh.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				if (v.getVisibility() == View.VISIBLE)
				{
					refreshLocation();
				}
			}
		});

		button_createLocation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				locationManager.removeUpdates(locationListener);

				final SimpleLocation location = new SimpleLocation
				(
					editText_name.getText().toString(),
					editText_altitude.getText().toString(),
					editText_latitude.getText().toString(),
					editText_longitude.getText().toString()
				);

				listeners.onLocationCreate(location);
			}
		});

		button_manualEntry.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(final View v)
			{
				locationManager.removeUpdates(locationListener);
				locationSearchIsRunning = false;

				findViews();

				editText_altitude.setHint(null);
				editText_latitude.setHint(null);
				editText_longitude.setHint(null);

				flushViews();

				textView_header.setText(R.string.manual_entry);
			}
		});

		locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener()
		{
			@Override
			public void onLocationChanged(final Location location)
			{
				locationSearchIsRunning = false;
				locationManager.removeUpdates(locationListener);

				findViews();

				editText_altitude.setText(String.valueOf(location.getAltitude()));
				editText_latitude.setText(String.valueOf(location.getLatitude()));
				editText_longitude.setText(String.valueOf(location.getLongitude()));

				flushViews();
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

		if (savedInstanceState != null && !savedInstanceState.isEmpty())
		{
			if (savedInstanceState.containsKey(EDIT_TEXT_NAME_KEY))
			{
				final String name = savedInstanceState.getString(EDIT_TEXT_NAME_KEY);
				editText_name.setText(name);
			}

			if (savedInstanceState.containsKey(EDIT_TEXT_ALTITUDE_KEY)
				&& savedInstanceState.containsKey(EDIT_TEXT_LATITUDE_KEY)
				&& savedInstanceState.containsKey(EDIT_TEXT_LONGITUDE_KEY))
			{
				final String altitude = savedInstanceState.getString(EDIT_TEXT_ALTITUDE_KEY);
				final String latitude = savedInstanceState.getString(EDIT_TEXT_LATITUDE_KEY);
				final String longitude = savedInstanceState.getString(EDIT_TEXT_LONGITUDE_KEY);

				editText_name.setText(altitude);
				editText_name.setText(latitude);
				editText_name.setText(longitude);
			}
			else
			{
				refreshLocation();
			}

			flushViews();
		}
		else
		{
			refreshLocation();
		}
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
			listeners = (CreateLocationListeners) activity;
		}
		catch (final ClassCastException e)
		{
			throw new ClassCastException(activity.toString() + " must implement listeners!");
		}
	}


	@Override
	public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater)
	{
		menu.removeItem(R.id.location_list_fragment_menu_create_location);
		super.onCreateOptionsMenu(menu, inflater);
	}


	@Override
	public void onSaveInstanceState(final Bundle outState)
	{
		if (editText_name != null && editText_name.length() >= 1)
		{
			outState.putString(EDIT_TEXT_NAME_KEY, editText_name.getText().toString());
		}

		if (editText_altitude != null && editText_altitude.length() >= 1)
		{
			outState.putString(EDIT_TEXT_ALTITUDE_KEY, editText_altitude.getText().toString());
		}

		if (editText_latitude != null && editText_latitude.length() >= 1)
		{
			outState.putString(EDIT_TEXT_LATITUDE_KEY, editText_latitude.getText().toString());
		}

		if (editText_longitude != null && editText_longitude.length() >= 1)
		{
			outState.putString(EDIT_TEXT_LONGITUDE_KEY, editText_longitude.getText().toString());
		}

		super.onSaveInstanceState(outState);
	}


	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				getActivity().onBackPressed();
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}




	/**
	 * Checks to see if all of the layout items that we're using from this
	 * class's layout have been found (and are not null). If any single one has
	 * not been found (and is therefore null), then this method will find every
	 * single layout item.
	 */
	private void findViews()
	{
		if (textView_header == null || editText_name == null || editText_altitude == null
			|| editText_latitude == null || editText_longitude == null || progressBar_header == null
			|| button_refresh == null || button_createLocation == null || button_manualEntry == null)
		{
			final View view = getView();

			textView_header = (TextView) view.findViewById(R.id.create_location_fragment_linearlayout_header_textview);
			editText_name = (EditText) view.findViewById(R.id.create_location_fragment_edittext_name);
			editText_altitude = (EditText) view.findViewById(R.id.create_location_fragment_edittext_altitude);
			editText_latitude = (EditText) view.findViewById(R.id.create_location_fragment_edittext_latitude);
			editText_longitude = (EditText) view.findViewById(R.id.create_location_fragment_edittext_longitude);
			progressBar_header = (ProgressBar) view.findViewById(R.id.create_location_fragment_linearlayout_header_progressbar);
			button_refresh = (Button) view.findViewById(R.id.create_location_fragment_linearlayout_header_button);
			button_createLocation = (Button) view.findViewById(R.id.create_location_fragment_button_create_location);
			button_manualEntry = (Button) view.findViewById(R.id.create_location_fragment_button_manual_entry);
		}
	}


	/**
	 * Enables or disables the EditText layout items that we're using in this
	 * class's layout. If we're currently searching for the phone's location,
	 * then this will disable the EditText layout items. If we're not currently
	 * searching for the phone's location, then this will enable the EditText
	 * layout items.
	 */
	private void flushViews()
	{
		if (locationSearchIsRunning)
		{
			textView_header.setText(R.string.searching_for_location);
			progressBar_header.setVisibility(View.VISIBLE);
			button_refresh.setVisibility(View.GONE);

			editText_altitude.setEnabled(false);
			editText_altitude.setHint(R.string.loading);
			editText_altitude.setText(null);
			editText_latitude.setEnabled(false);
			editText_latitude.setHint(R.string.loading);
			editText_latitude.setText(null);
			editText_longitude.setEnabled(false);
			editText_longitude.setHint(R.string.loading);
			editText_longitude.setText(null);

			button_manualEntry.setEnabled(true);
		}
		else
		{
			textView_header.setText(R.string.location_found);
			progressBar_header.setVisibility(View.GONE);
			button_refresh.setVisibility(View.VISIBLE);

			editText_altitude.setEnabled(true);
			editText_latitude.setEnabled(true);
			editText_longitude.setEnabled(true);

			button_manualEntry.setEnabled(false);
		}
	}


	/**
	 * If the location search is running, then this method will do nothing. If
	 * the location search is not running, then this will start the location
	 * search.
	 */
	private void refreshLocation()
	{
		if (!locationSearchIsRunning)
		{
			locationSearchIsRunning = true;

			findViews();
			flushViews();

			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		}
	}


}
