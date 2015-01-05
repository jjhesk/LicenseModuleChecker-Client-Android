package com.asynhkm.productchecker.Checker;

import android.content.Context;
import android.content.SharedPreferences;

import com.asynhkm.productchecker.Util.Tool;

/**
 * Created by Hesk on 30/12/2014.
 */
public class HKMCheckerPlugable {

    private Context ctx;
    private SharedPreferences SP;
    private String
            licenseKey = "",
            productKey = "",
            mac_id = "";

    public HKMCheckerPlugable(String productkey, Context ctx) {
        SP = ctx.getApplicationContext().getSharedPreferences("license_data", Context.MODE_PRIVATE);
        mac_id = Tool.get_mac_address(ctx);
        this.productKey = productkey;
        this.ctx = ctx;
    }

    /**
     * the net is just started now
     */
    public void netStartCheck(CheckerCB callback) {
        final CheckerTask re = new CheckerTask(this.ctx, callback);

        if (licenseKey.isEmpty()) {
            //check with the license from the customer
            re.setProductKey(productKey)
                    .setMac(mac_id)
                    .setRequestUrl(param.devReg())
                    .execute();
        } else {
            //check and issue a new license or register
            re.setLicenseKey(licenseKey)
                    .setMac(mac_id)
                    .setRequestUrl(param.devCheck())
                    .execute();
        }
    }

    /**
     * public initialization
     *
     * @param productKey
     * @param ctx
     * @return
     */
    public static HKMCheckerPlugable init(String productKey, Context ctx) {
        HKMCheckerPlugable instance = new HKMCheckerPlugable(productKey, ctx);

        return instance;
    }

    /**
     * chain method
     *
     * @param checker
     * @return
     */
    public HKMCheckerPlugable trigger_check(String checker) {
        licenseKey = checker;
        return this;
    }


}
