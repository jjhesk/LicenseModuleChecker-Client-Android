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
        this.ctx = ctx;
    }

    /**
     * the net is just started now
     */
    public void netStartCheck(CheckerCB callback) {
        if (licenseKey.isEmpty()) {
            //check with the license from the customer

            final CheckerTask re = new CheckerTask(this.ctx, callback);
            re.setLicenseKey(licenseKey);
            re.setMac(mac_id);
            re.setRequestUrl(param.devCheck());
            re.execute();


        } else {
            //check and issue a new license or register


            final CheckerTask re = new CheckerTask(this.ctx, callback);
            re.setLicenseKey(licenseKey)
                    .setMac(mac_id)
                    .setRequestUrl(param.devReg())
                    .execute();
            //DialogCustomRedemptionSingle
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
