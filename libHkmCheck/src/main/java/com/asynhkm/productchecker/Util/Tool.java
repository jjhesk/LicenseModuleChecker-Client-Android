package com.asynhkm.productchecker.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hesk on
 */
public class Tool {
    public static String get_mac_address(Context ctx) {
        WifiManager manager = (WifiManager) ctx
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getMacAddress();
        return address;
    }

    public static boolean isOnline(Context ctx) {
        try {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isEmpty(EditText t) {
        try {
            final String contenttext = t.getText().toString();
            Log.d("TAG STRING CONTENT", contenttext);
            if (contenttext.equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.d("TAG STRING CONTENT", "NO CONTEST");
            e.printStackTrace();
            return false;
        }
    }

    public static void trace(Context ctx, int resId, Object... param) {
        String f = "resId not found";
        try {
            f = ctx.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(ctx, f, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(ctx, String.format(f, param), Toast.LENGTH_LONG).show();
    }

    public static void trace(Context ctx, String format, Object... param) {
        Toast.makeText(ctx, String.format(format, param), Toast.LENGTH_LONG)
                .show();
    }

    public static void trace(Context ctx, String str) {
        Toast.makeText(ctx, str, Toast.LENGTH_LONG).show();
    }

    public static void trace(Context ctx, int resId) {
        String f = "resId not found";
        try {
            f = ctx.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(ctx, f, Toast.LENGTH_LONG).show();
        }
    }

    public static void showKeyBoard(Context ctx, EditText focused_textfield) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(focused_textfield, InputMethodManager.SHOW_FORCED);
    }

    public static void hideKeyBoard(Context ctx, EditText focused_textfield) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(focused_textfield.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

}
