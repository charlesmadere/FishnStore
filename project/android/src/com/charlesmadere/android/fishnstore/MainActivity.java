package com.charlesmadere.android.fishnstore;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity
{


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
		return true;
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
