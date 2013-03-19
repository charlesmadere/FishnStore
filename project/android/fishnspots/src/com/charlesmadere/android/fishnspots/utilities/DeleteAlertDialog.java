package com.charlesmadere.android.fishnspots.utilities;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.charlesmadere.android.fishnspots.R;
import com.charlesmadere.android.fishnspots.models.SimpleLocation;


/**
 * Shows an AlertDialog that will ask the user whether or not they want to
 * delete a given SimpleLocation object.
 */
public final class DeleteAlertDialog
{


	/**
	 * The Context of the Android Activity that created an instance of this
	 * class.
	 */
	private Context context;


	/**
	 * The SimpleLocation object to delete (or not).
	 */
	private SimpleLocation location;


	/**
	 * Object that allows us to run any of the methods that are defined in
	 * the DeleteAlertDialogListeners interface.
	 */
	private DeleteAlertDialogListeners listeners;


	/**
	 * A bunch of listener methods for this class.
	 */
	public interface DeleteAlertDialogListeners
	{


		/**
		 * Fired when the user chooses that they do not want to delete the
		 * given SimpleLocation object.
		 */
		public void cancel();


		/**
		 * Fired when the user chooses that they do indeed want to delete
		 * the given SimpleLocation object.
		 * 
		 * @param location
		 * The SimpleLocation object to delete.
		 */
		public void delete(final SimpleLocation location);


	}


	/**
	 * Creates a DeleteAlertDialog object.
	 * 
	 * @param context
	 * The Context of the Android Activity that is creating this object.
	 * 
	 * @param location
	 * The SimpleLocation object to ask the user to delete.
	 */
	public DeleteAlertDialog(final Context context, final SimpleLocation location, final DeleteAlertDialogListeners listeners)
	{
		this.context = context;
		this.location = location;
		this.listeners = listeners;
	}


	/**
	 * Shows an AlertDialog that asks the user if they are sure that they
	 * want to delete the given location.
	 */
	public void show()
	{
		final AlertDialog.Builder builder = new AlertDialog.Builder(context)
			.setMessage(context.getString(R.string.are_you_sure_that_you_want_to_delete_location_x, location.getName()))
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(final DialogInterface dialog, final int which)
				{
					dialog.dismiss();
					listeners.cancel();
				}
			})
			.setOnCancelListener(new DialogInterface.OnCancelListener()
			{
				@Override
				public void onCancel(final DialogInterface dialog)
				{
					dialog.dismiss();
					listeners.cancel();
				}
			})
			.setPositiveButton(R.string.okay, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(final DialogInterface dialog, final int which)
				{
					dialog.dismiss();
					listeners.delete(location);
				}
			})
			.setTitle(R.string.delete_location);

		builder.show();
	}


}
