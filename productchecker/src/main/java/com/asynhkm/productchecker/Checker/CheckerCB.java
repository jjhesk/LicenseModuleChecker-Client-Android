package com.asynhkm.productchecker.Checker;

import com.asynhkm.productchecker.schema.DataProductVersion;
import com.asynhkm.productchecker.schema.ReturnResult;

/**
 * Created by Hesk on 30/12/2014.
 */
public interface CheckerCB {
    public void success();

    public void success(DataProductVersion mDataProduct);

    public void fail();

    public void fail(ReturnResult mResult);

    public void fail(DataProductVersion mDataProduct);
}
