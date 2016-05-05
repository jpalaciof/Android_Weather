package com.app.jpalacio.weatherapp;

import android.content.Context;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

/**
 * Created by jpalacio on 5/5/16.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ComponentName comp =
                new ComponentName(context.getPackageName(),
                        GCMIntentService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
