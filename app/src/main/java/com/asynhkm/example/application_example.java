package com.asynhkm.example;

import com.asynhkm.productchecker.AppBackground;

/**
 * Created by Hesk on 2/1/2015.
 */
public class application_example extends AppBackground {
    @Override
    protected void setProductId(String id) {
        super.setProductId(id);
    }

    @Override
    public void onCreate() {
        this.setProductId("54a36152417777b2152fe14b");
        super.onCreate();
    }

}
