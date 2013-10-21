package com.ait.phone;

import com.phonegap.DroidGap;

import android.app.Activity;
import android.os.Bundle;

public class AitphoneuiActivity extends DroidGap {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/gb_login.html");
    }
}