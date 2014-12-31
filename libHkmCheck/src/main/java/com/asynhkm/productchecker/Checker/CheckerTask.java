package com.asynhkm.productchecker.Checker;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.asynhkm.productchecker.Util.Tool;
import com.asynhkm.productchecker.schema.DataProductVersion;
import com.asynhkm.productchecker.schema.ReturnResult;
import com.asynhkm.productchecker.schema.requestRegister;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Hesk on 30/12/2014.
 */
public class CheckerTask extends AsyncTask<Void, Void, DataProductVersion> {
    private HttpParams httpParams;
    private Context c;
    public static final String TAG = "Redeem Class";
    private OkHttpClient client = new OkHttpClient();
    private CheckerCB checker_cb;

    public CheckerTask(Context cctxx, CheckerCB cb) {
        httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
        HttpConnectionParams.setSoTimeout(httpParams, 5000);
        c = cctxx;
        checker_cb = cb;
    }

    public boolean isSuccess(String str) throws JSONException {
        final JSONObject js = new JSONObject(str);
        final boolean t = js.getBoolean("success");
        return t;
    }


    private DataProductVersion return_result(String resultString) throws Exception {
        final GsonBuilder gsonb = new GsonBuilder();


        DataProductVersion mDataProductVersion = new DataProductVersion();
        ReturnResult rt;


        try {
            System.out.println("RESPONSE: " + resultString);


            if (isSuccess(resultString)) {
                mDataProductVersion = gsonb.create().fromJson(resultString, DataProductVersion.class);
            } else {
                ReturnResult result = gsonb.create().fromJson(resultString, ReturnResult.class);
                mDataProductVersion.setRR(result);
            }


        } catch (JsonParseException e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        } catch (JSONException e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        } catch (Exception e) {
            rt = new ReturnResult(e.getMessage());
            mDataProductVersion.setRR(rt);
        }

        return mDataProductVersion;

    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private String
            licenseKey = "",
            productKey = "",
            mac_id = "",
            request_url = "";

    public CheckerTask setProductKey(String key) {
        productKey = key;
        return this;
    }

    public CheckerTask setLicenseKey(String license) {
        licenseKey = license;
        return this;
    }

    public CheckerTask setMac(String mac) {
        mac_id = mac;
        return this;
    }


    public CheckerTask setRequestUrl(String url) {
        request_url = url;
        return this;
    }


    protected String consolidate() {
        final GsonBuilder gsonb = new GsonBuilder();
        requestRegister raw = new requestRegister();
        if (productKey.isEmpty()) {
            raw.domain = mac_id;
            raw.product_key = licenseKey;
        } else {
            raw.domain = mac_id;
            raw.product_key = productKey;
        }
        Gson gson = gsonb.create();
        final String request_body = gson.toJson(raw);
        return request_body;
    }


    @Override
    protected DataProductVersion doInBackground(Void... c) {
        DataProductVersion h;
        try {
            RequestBody body = RequestBody.create(JSON, consolidate());
            Request request = new Request.Builder()
                    .url(request_url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            String res = response.body().string();
            h = return_result(res);
        } catch (Exception e) {
            Log.d("work ERROR", e.getMessage());
            h = new DataProductVersion();
            h.setRR(new ReturnResult(e.getMessage()));
        }
        return h;
    }

    @Override
    protected void onPostExecute(DataProductVersion result) {
        Log.d(TAG, "onPostExecute result == " + result.toString());
        super.onPostExecute(result);
        if (result.isError()) {
            checker_cb.fail(result);
        } else {
            checker_cb.success(result);
        }
    }

    @Override
    protected void onPreExecute() {
        if (Tool.isOnline(c)) {
            super.onPreExecute();
            // assertEquals(0, progressBar.getProgress());
        } else {
            //Tool.trace(c, R.string.warning_online_alert);
        }
    }


}
