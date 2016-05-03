package com.app.jpalacio.weatherapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.app.jpalacio.weatherapp.data.WeatherContract;
import com.app.jpalacio.weatherapp.data.WeatherDBHelper;

import java.util.HashSet;

/**
 * Created by jpalacio on 4/28/16.
 */
public class TestDB extends AndroidTestCase {
    public static final String LOG_TAG = TestDB.class.getSimpleName();

    void deleteTheDatabase() {
        mContext.deleteDatabase(WeatherDBHelper.DATABASE_NAME);
    }

    public void setUp() {
        deleteTheDatabase();
    }

    //his function gets called before each test
    public void testCreateOb() throws Throwable {
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(WeatherContract.LocationEntry.TABLE_NAME);
        tableNameHashSet.add(WeatherContract.WeatherEntry.TABLE_NAME);

        mContext.deleteDatabase(WeatherDBHelper.DATABASE_NAME);

        SQLiteDatabase db = new WeatherDBHelper(
                this.mContext).getWritableDatabase();

        assertEquals("NOT CONNECT",true, db.isOpen()); // Deberá conectarse

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        assertTrue("Error: This means that the database has not been created correctly", c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while (c.moveToNext());

        // if this fails, it means that your databases doesn´t contain both the location entry
        assertTrue("Error: Your database was created without both the" +
        " location entry and weather entry tables",
                tableNameHashSet.isEmpty());

        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.LocationEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: this means that we were unable to query the database for table information.",
                c.moveToFirst());

        //Build a HashSet of all of the column names we want to look for
        final HashSet<String> locationColumnHashSet = new HashSet<>();
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            locationColumnHashSet.remove(columnName);
        } while (c.moveToNext());

        // if this fails, it means that your databases doesn´t contain both the location entry
        assertTrue("Error: Your database doesn't contain all of the required " +
        "location entry columns", locationColumnHashSet.isEmpty());

        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.WeatherEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: this means that we were unable to query the database for table information.",
                c.moveToFirst());

        //Build a HashSet of all of the column names we want to look for
        final HashSet<String> weatherColumnHashSet = new HashSet<>();
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_LOC_KEY);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_DATE);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_SHORT_DESC);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_WEATHER_ID);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_MIN_TEMP);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_MAX_TEMP);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_HUMIDITY);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_PRESSURE);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_WIND_SPEED);
        weatherColumnHashSet.add(WeatherContract.WeatherEntry.COLUMN_DEGREES);

        columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            weatherColumnHashSet.remove(columnName);
        } while (c.moveToNext());

        // if this fails, it means that your databases doesn´t contain both the location entry
        assertTrue("Error: Your database doesn't contain all of the required " +
                "weather entry columns", weatherColumnHashSet.isEmpty());

        db.close();
    }
}
