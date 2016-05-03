package com.app.jpalacio.weatherapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.app.jpalacio.weatherapp.data.WeatherContract.WeatherEntry;
import com.app.jpalacio.weatherapp.data.WeatherContract.LocationEntry;
import com.app.jpalacio.weatherapp.data.WeatherDBHelper;


/**
 * Created by jpalacio on 5/3/16.
 */

// it does test that at least the basic functionality has been implemented correctly
public class TestProvider extends AndroidTestCase {
    public static final String LOG_TAG = TestProvider.class.getSimpleName();

    //This helper function deletes all records from both database tables using the ContentProvider

    public void deleteAllRecordsFromProvider() {
        mContext.getContentResolver().delete(
                WeatherEntry.CONTENT_URI,
                null,
                null
        );
        mContext.getContentResolver().delete(
                LocationEntry.CONTENT_URI,
                null,
                null
        );

        Cursor cursor = mContext.getContentResolver().query(
                WeatherEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals("Error: Records not delete from Weather table during delete", 0, cursor.getCount());
        cursor.close();

        cursor = mContext.getContentResolver().query(
                LocationEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );

        assertEquals("Error: Records not delete from Location table during delete", 0, cursor.getCount());
        cursor.close();
    }

    //This helper function deletes all records
    public void deleteAllRecordsFromDB() {
        WeatherDBHelper dbHelper = new WeatherDBHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(WeatherEntry.TABLE_NAME, null, null);
        db.delete(LocationEntry.TABLE_NAME, null, null);
        db.close();
    }

    public void deleteAllRecords() {
        deleteAllRecordsFromDB();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deleteAllRecords();
    }
}
