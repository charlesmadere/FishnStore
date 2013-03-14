package com.charlesmadere.android.fishnspots;


import com.charlesmadere.android.fishnspots.utilities.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SQLiteHelper extends SQLiteOpenHelper
{


	private final static String LOG_TAG = Utilities.LOG_TAG + " - SQLiteHelper";


	private final static String DATABASE_NAME = "fishnstore.db";
	private final static int DATABASE_VERSION = 1;


	public final static String TABLE_SPOTS = "spots";




	public SQLiteHelper(final Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		final String sqlStatementString = "CREATE TABLE ? ";
		db.execSQL(sqlStatementString);
	}

	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion)
	{
		Log.i(LOG_TAG, "Upgrading database to version " + newVersion + " from " + oldVersion + ".");

		final String sqlStatementString = "DROP TABLE IF EXISTS " + TABLE_SPOTS;
		db.execSQL(sqlStatementString);
	}


}
