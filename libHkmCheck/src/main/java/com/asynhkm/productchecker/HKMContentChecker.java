package com.asynhkm.productchecker;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.asynhkm.productchecker.Checker.HKMCheckerPlugable;

/**
 * Created by hesk on 11/23/2014.
 */
public class HKMContentChecker extends Application {
    /*private static MainThread ourInstance = new MainThread();

    public static MainThread getInstance() {
        return ourInstance;
    }*/
    private SharedPreferences SP;
    private static HKMCheckerPlugable main;

    public void onCreate() {
        main = new HKMCheckerPlugable("___enter_your_key_here", getApplicationContext());
        SP = getApplicationContext().getSharedPreferences("skc", Context.MODE_PRIVATE);
        final int transaction_mode = SP.getInt("transaction_mode", 1);
        //  main.set_transaction_verification_via(transaction_mode);
    }

    public int getSettingRedemption() {
        return SP.getInt("transaction_mode", 1);
    }

    public HKMCheckerPlugable getCheckerPlug() {
        return main;
    }
}
