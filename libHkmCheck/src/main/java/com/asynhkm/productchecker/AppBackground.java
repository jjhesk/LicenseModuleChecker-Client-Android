package com.asynhkm.productchecker;

import android.app.Application;
import android.content.Context;

import com.asynhkm.productchecker.Checker.CheckerCB;
import com.asynhkm.productchecker.Checker.HKMCheckerPlugable;
import com.asynhkm.productchecker.schema.DataProductVersion;

/**
 * Created by Hesk on 2/1/2015.
 */
public class AppBackground extends Application implements CheckerCB {
    private Context ctx;
    private static HKMCheckerPlugable main;
    protected String product_id = "-key-";

    @Override
    public void tr_success(DataProductVersion mDataProduct) {
        // Tool.trace(ctx, mDataProduct.toString());
    }

    protected void setProductId(String id) {
        product_id = id;
    }

    @Override
    public void tr_fail(DataProductVersion mDataProduct) {
        //Tool.trace(ctx, mDataProduct.toString());
    }

    @Override
    public void onCreate() {
        ctx = getApplicationContext();
        main = new HKMCheckerPlugable(product_id, getApplicationContext());
        main.netStartCheck(this);

    }
}
