package com.charlesmadere.android.fishnspots.models;


/**
 * An object that stores only the portions of the built in Android Location
 * object (android.location.Location) that our app actually needs.
 */
public class SimpleLocation
{


	/**
	 * The ID of this SimpleLocation object in the database. If this
	 * SimpleLocation object was <strong>not</strong> created from the
	 * database, then the value of this variable will be Long.MIN_VALUE.
	 */
	private long id;


	/**
	 * The name of the given location.
	 */
	private String name;


	/**
	 * The altitude of the given location in meters above sea level. If this
	 * SimpleLocation object does not have an altitude then 0.0 will be this
	 * variable's value.
	 */
	private double altitude;


	/**
	 * The latitude of the given location in degrees.
	 */
	private double latitude;


	/**
	 * The longitude of the given location in degrees.
	 */
	private double longitude;




	/**
	 * Creates a new SimpleLocation object.
	 * 
	 * @param name
	 * This SimpleLocation object's name (a String). Can be anything: "The
	 * Canal", "The Awesome Beach", or maybe even "Twin Rigs".
	 * 
	 * @param altitude
	 * This SimpleLocation object's altitude (a double).
	 * 
	 * @param latitude
	 * This SimpleLocation object's latitude (a double).
	 * 
	 * @param longitude
	 * This SimpleLocation object's longitude (a double).
	 */
	public SimpleLocation(final String name, final double altitude, final double latitude, final double longitude)
	{
		this.name = name;
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;

		id = Long.MIN_VALUE;
	}


	/**
	 * Creates a new SimpleLocation object.
	 * 
	 * @param name
	 * This SimpleLocation object's name (a String). Can be anything: "The
	 * Canal", "The Awesome Beach", or maybe even "Twin Rigs".
	 * 
	 * @param altitude
	 * This SimpleLocation object's altitude (a String). If this String is null
	 * or empty then there will be a crash.
	 * 
	 * @param latitude
	 * This SimpleLocation object's latitude (a String). If this String is null
	 * or empty then there will be a crash.
	 * 
	 * @param longitude
	 * This SimpleLocation object's longitude (a String). If this String is
	 * null or empty then there will be a crash.
	 */
	public SimpleLocation(final String name, final String altitude, final String latitude, final String longitude)
	{
		this.name = name;
		this.altitude = Double.parseDouble(altitude);
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);

		id = Long.MIN_VALUE;
	}


	/**
	 * Creates a new SimpleLocation object out of database data. All of the
	 * inputs for these parameters should be taken directly from the database.
	 * 
	 * @param id
	 * The ID that was assigned to this SimpleLocation object when inserted
	 * into the database.
	 * 
	 * @param name
	 * This SimpleLocation object's name (a String). Can be anything: "The
	 * Canal", "The Awesome Beach", or maybe even "Twin Rigs".
	 * 
	 * @param altitude
	 * This SimpleLocation object's altitude (a double).
	 * 
	 * @param latitude
	 * This SimpleLocation object's latitude (a double).
	 * 
	 * @param longitude
	 * This SimpleLocation object's longitude (a double).
	 */
	public SimpleLocation(final long id, final String name, final double altitude, final double latitude, final double longitude)
	{
		this.id = id;
		this.name = name;
		this.altitude = altitude;
		this.latitude = latitude;
		this.longitude = longitude;
	}


	/**
	 * @return
	 * Returns this SimpleLocation object's id attribute.
	 */
	public long getId()
	{
		return id;
	}


	/**
	 * @return
	 * Returns this SimpleLocation object's name attribute.
	 */
	public String getName()
	{
		return name;
	}


	/**
	 * @return
	 * Returns this SimpleLocation object's altitude attribute.
	 */
	public double getAltitude()
	{
		return altitude;
	}


	/**
	 * @return
	 * Returns this SimpleLocation object's latitude attribute.
	 */
	public double getLatitude()
	{
		return latitude;
	}


	/**
	 * @return
	 * Returns this SimpleLocation object's longitude attribute.
	 */
	public double getLongitude()
	{
		return longitude;
	}


	/**
	 * Checks to see if this SimpleLocation object has a valid ID. An ID that
	 * is valid is not equal to Long.MIN_VALUE.
	 * 
	 * @return
	 * Returns true if this SimpleLocation object has a valid ID.
	 */
	public boolean hasValidId()
	{
		return id != Long.MIN_VALUE;
	}


	/**
	 * Sets this SimpleLocation's name.
	 * 
	 * @param name
	 * The new name.
	 */
	public void setName(final String name)
	{
		this.name = name;
	}


	/**
	 * Sets this SimpleLocation's altitude.
	 * 
	 * @param altitude
	 * The new altitude.
	 */
	public void setAltitude(final double altitude)
	{
		this.altitude = altitude;
	}


	/**
	 * Sets this SimpleLocation's altitude.
	 * 
	 * @param altitude
	 * The new altitude. If a null or empty String is passed into here then
	 * there will be a crash.
	 */
	public void setAltitude(final String altitude)
	{
		this.altitude = Double.parseDouble(altitude);
	}


	/**
	 * Sets this SimpleLocation's latitude.
	 * 
	 * @param latitude
	 * The new latitude.
	 */
	public void setLatitude(final double latitude)
	{
		this.latitude = latitude;
	}


	/**
	 * Sets this SimpleLocation's altitude.
	 * 
	 * @param latitude
	 * The new latitude. If a null or empty String is passed into here then
	 * there will be a crash.
	 */
	public void setLatitude(final String latitude)
	{
		this.latitude = Double.parseDouble(latitude);
	}


	/**
	 * Sets this SimpleLocation's longitude.
	 * 
	 * @param longitude
	 * The new longitude.
	 */
	public void setLongitude(final double longitude)
	{
		this.longitude = longitude;
	}


	/**
	 * Sets this SimpleLocation's altitude.
	 * 
	 * @param longitude
	 * The new longitude. If a null or empty String is passed into here then
	 * there will be a crash.
	 */
	public void setLongitude(final String longitude)
	{
		this.longitude = Double.parseDouble(longitude);
	}


}
