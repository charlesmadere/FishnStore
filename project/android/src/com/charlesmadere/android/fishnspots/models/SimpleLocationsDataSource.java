package com.charlesmadere.android.fishnspots.models;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.charlesmadere.android.fishnspots.utilities.DatabaseHelper;


/**
 * Much of this code was taken from this tutorial:
 * http://www.vogella.com/articles/AndroidSQLite/article.html
 */
public class SimpleLocationsDataSource
{


	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;


	public SimpleLocationsDataSource(final Context context)
	{
		dbHelper = new DatabaseHelper(context);
	}


	public void open()
	{
		db = dbHelper.getWritableDatabase();
	}


	public void close()
	{
		dbHelper.close();
	}


	public SimpleLocation createSimpleLocation(final Location location)
	{
		final ContentValues values = new ContentValues();
		values.put(DatabaseHelper.TABLE_LOCATIONS_COLUMN_ALTITUDE, location.getAltitude());
		values.put(DatabaseHelper.TABLE_LOCATIONS_COLUMN_LATITUDE, location.getLatitude());
		values.put(DatabaseHelper.TABLE_LOCATIONS_COLUMN_LONGITUDE, location.getLongitude());

		final long id = db.insert(DatabaseHelper.TABLE_LOCATIONS, null, values);

		final Cursor cursor = db.query(DatabaseHelper.TABLE_LOCATIONS, DatabaseHelper.TABLE_LOCATIONS_ALL_COLUMNS, DatabaseHelper.TABLE_LOCATIONS_COLUMN_ID + " = " + id, null, null, null, null);
		cursor.moveToFirst();
		final SimpleLocation simpleLocation = cursorToSimpleLocation(cursor);
		cursor.close();

		return simpleLocation;
	}


	private SimpleLocation cursorToSimpleLocation(final Cursor cursor)
	{
		final SimpleLocation simpleLocation = new SimpleLocation
		(
			cursor.getLong(DatabaseHelper.TABLE_LOCATIONS_COLUMN_INDEX_ID),
			cursor.getString(DatabaseHelper.TABLE_LOCATIONS_COLUMN_INDEX_NAME),
			cursor.getDouble(DatabaseHelper.TABLE_LOCATIONS_COLUMN_INDEX_ALTITUDE),
			cursor.getDouble(DatabaseHelper.TABLE_LOCATIONS_COLUMN_INDEX_LATITUDE),
			cursor.getDouble(DatabaseHelper.TABLE_LOCATIONS_COLUMN_INDEX_LONGITUDE)
		);

		return simpleLocation;
	}


	public void deleteSimpleLocation(final SimpleLocation simpleLocation)
	{
		final long id = simpleLocation.getId();
		db.delete(DatabaseHelper.TABLE_LOCATIONS, DatabaseHelper.TABLE_LOCATIONS_COLUMN_ID + " = " + id, null);
	}


	public ArrayList<SimpleLocation> getAllSimpleLocations()
	{
		final ArrayList<SimpleLocation> simpleLocations = new ArrayList<SimpleLocation>();

		final Cursor cursor = db.query(DatabaseHelper.TABLE_LOCATIONS, DatabaseHelper.TABLE_LOCATIONS_ALL_COLUMNS, null, null, null, null, null);
		cursor.moveToFirst();

		while (!cursor.isAfterLast())
		{
			final SimpleLocation simpleLocation = cursorToSimpleLocation(cursor);
			simpleLocations.add(simpleLocation);
			cursor.moveToNext();
		}

		cursor.close();

		return simpleLocations;
	}


}
