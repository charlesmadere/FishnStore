package com.charlesmadere.android.fishnspots.utilities;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;



public class SQLiteHelper extends SQLiteOpenHelper
{


	private final static String LOG_TAG = Utilities.LOG_TAG + " - SQLiteHelper";


	private final static int DATABASE_VERSION = 1;
	private final static String DATABASE_NAME = "fishnspots.db";


	public final static String TABLE_SPOTS = "spots";




	public SQLiteHelper(final Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(final SQLiteDatabase db)
	{
		final String sqlStatementString = "CREATE TABLE IF NOT EXISTS ?";
		final SQLiteStatement sqlStatement = db.compileStatement(sqlStatementString);
		sqlStatement.bindString(1, TABLE_SPOTS);
		sqlStatement.execute();
	}


	@Override
	public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion)
	{
		Log.i(LOG_TAG, "Upgrading database to version " + newVersion + " from " + oldVersion + ".");

		final String sqlStatementString = "DROP TABLE IF EXISTS ?";
		final SQLiteStatement sqlStatement = db.compileStatement(sqlStatementString);
		sqlStatement.bindString(1, TABLE_SPOTS);
		sqlStatement.execute();
	}


}
