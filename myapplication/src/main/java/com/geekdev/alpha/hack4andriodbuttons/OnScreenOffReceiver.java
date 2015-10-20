package com.geekdev.alpha.hack4andriodbuttons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class OnScreenOffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
            MyApplication ctx = (MyApplication) context.getApplicationContext();
            // is NoButtons Mode active?
            if(PreferenceUtils.isNoButtonsModeActive(ctx)) {
                wakeUpDevice(ctx);
            }
        }
    }

    private void wakeUpDevice(MyApplication context) {
        PowerManager.WakeLock wakeLock = context.getWakeLock(); // get WakeLock reference via MyApplication
        if (wakeLock.isHeld()) {
            wakeLock.release(); // release old wake lock
        }

        // create a new wake lock...
        wakeLock.acquire();

        // ... and release again
        wakeLock.release();
    }


}