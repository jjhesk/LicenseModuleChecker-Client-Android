package com.asynhkm.productchecker.Checker;

import com.asynhkm.productchecker.schema.DataProductVersion;

/**
 * Created by Hesk on 30/12/2014.
 */
public interface CheckerCB {

    public void tr_success(DataProductVersion mDataProduct);

    public void tr_fail(DataProductVersion mDataProduct);
}
