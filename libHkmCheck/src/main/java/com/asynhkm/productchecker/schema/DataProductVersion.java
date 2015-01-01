package com.asynhkm.productchecker.schema;

/**
 * Created by Hesk on 30/12/2014.
 */
public class DataProductVersion {
    private String
            clientID,
            siteURL,
            licensePerson,
            product,
            createdAt,
            checked,
            expire,
            key,
            licenseHash;
    private ReturnResult rr;
    private boolean
            brandingRemoval,
            demoDisplay,
            useExpiration,
            licenseStatusLive;

    public void setRR(ReturnResult rlr) {
        rr = rlr;
    }

    public boolean isError() {
        return rr != null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(clientID).append(siteURL);
        return sb.toString();
    }
}
