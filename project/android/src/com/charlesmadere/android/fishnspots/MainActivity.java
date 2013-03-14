package com.charlesmadere.android.fishnspots;


import com.charlesmadere.android.fishnspots.utilities.Utilities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


/**
 * This class is the app's entry point.
 */
public class MainActivity extends Activity
{


	private final static String LOG_TAG = Utilities.LOG_TAG + " - MainActivity";


	@Override
	protected void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
	}


	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		getMenuInflater().inflate(R.menu.main_activity, menu);
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(final MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.main_activity_menu_save_current_location:
				startActivityForResult(new Intent(this, SaveCurrentLocationActivity.class), 0);
				break;

			default:
				return super.onOptionsItemSelected(item);
		}

		return true;
	}


}
