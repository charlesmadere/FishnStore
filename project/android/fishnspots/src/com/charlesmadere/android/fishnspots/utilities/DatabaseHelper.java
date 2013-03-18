package com.charlesmadere.android.fishnspots.utilities;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper
{


	private final static String LOG_TAG = Utilities.LOG_TAG + " - SQLiteHelper";


	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "fishnspots.db";


	// locations table data below
	public final static String TABLE_LOCATIONS = "LOCATIONS";
	public final static String TABLE_LOCATIONS_COLUMN_ID = "ID";
	public final static String TABLE_LOCATIONS_COLUMN_NAME = "NAME";
	public final static String TABLE_LOCATIONS_COLUMN_ALTITUDE = "ALTITUDE";
	public final static String TABLE_LOCATIONS_COLUMN_LATITUDE = "LATITUDE";
	public final static String TABLE_LOCATIONS_COLUMN_LONGITUDE = "LONGITUDE";

	public final static String[] TABLE_LOCATIONS_ALL_COLUMNS =
	{
		TABLE_LOCATIONS_COLUMN_ID,
		TABLE_LOCATIONS_COLUMN_NAME,
		TABLE_LOCATIONS_COLUMN_ALTITUDE,
		TABLE_LOCATIONS_COLUMN_LATITUDE,
		TABLE_LOCATIONS_COLUMN_LONGITUDE
	};

	public final static int TABLE_LOCATIONS_COLUMN_INDEX_ID = 0;
	public final static int TABLE_LOCATIONS_COLUMN_INDEX_NAME = 1;
	public final static int TABLE_LOCATIONS_COLUMN_INDEX_ALTITUDE = 2;
	public final static int TABLE_LOCATIONS_COLUMN_INDEX_LATITUDE = 3;
	public final static int TABLE_LOCATIONS_COLUMN_INDEX_LONGITUDE = 4;
	// end locations table data




	public DatabaseHelper(final Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		final String sqlStatementString = "CREATE TABLE IF NOT EXISTS " + TABLE_LOCATIONS + "(" +
			TABLE_LOCATIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			TABLE_LOCATIONS_COLUMN_NAME + " TEXT NOT NULL, " +
			TABLE_LOCATIONS_COLUMN_ALTITUDE + " REAL NOT NULL, " +
			TABLE_LOCATIONS_COLUMN_LATITUDE + " REAL NOT NULL, " +
			TABLE_LOCATIONS_COLUMN_LONGITUDE + " REAL NOT NULL " +
			");";

		db.execSQL(sqlStatementString);
	}


	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion)
	{
		Log.i(LOG_TAG, "Upgrading database to version " + newVersion + " from " + oldVersion + ".");

		final String sqlStatementString = "DROP TABLE IF EXISTS " + TABLE_LOCATIONS;
		db.execSQL(sqlStatementString);
	}


}
