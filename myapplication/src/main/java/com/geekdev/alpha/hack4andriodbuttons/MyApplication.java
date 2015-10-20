package com.geekdev.alpha.hack4andriodbuttons;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

public class MyApplication extends Application {

    private MyApplication instance;
    private PowerManager.WakeLock wakeLock;
    private OnScreenOffReceiver onScreenOffReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerNoButtonsModeScreenOffReceiver();
        startNoButtonsService();

    }

    private void registerNoButtonsModeScreenOffReceiver() {
        // register screen off receiver
        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        onScreenOffReceiver = new OnScreenOffReceiver();
        registerReceiver(onScreenOffReceiver, filter);
    }

    public PowerManager.WakeLock getWakeLock() {
        if(wakeLock == null) {
            // lazy loading: first call, create wakeLock via PowerManager.
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP, "wakeup");

        }
        return wakeLock;
    }

    private void startNoButtonsService() {
        startService(new Intent(this, NoButtonsService.class));
    }
}