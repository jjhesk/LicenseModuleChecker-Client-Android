package com.asynhkm.licensechecker;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.asynhkm.productchecker.Checker.CheckerCB;
import com.asynhkm.productchecker.Checker.HKMCheckerPlugable;
import com.asynhkm.productchecker.Util.Tool;
import com.asynhkm.productchecker.schema.DataProductVersion;
import com.asynhkm.productchecker.schema.ReturnResult;


public class MainExample extends Activity {
    private static HKMCheckerPlugable main;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getApplicationContext();


        main = new HKMCheckerPlugable("54a36152417777b2152fe14b", getApplicationContext());
        main.netStartCheck(new CheckerCB() {
            @Override
            public void success() {

            }

            @Override
            public void success(DataProductVersion mDataProduct) {
                Tool.trace(ctx, mDataProduct.toString());
            }

            @Override
            public void fail() {

            }

            @Override
            public void fail(ReturnResult mResult) {
                Tool.trace(ctx, mResult.toString());
            }

            @Override
            public void fail(DataProductVersion mDataProduct) {
                Tool.trace(ctx, mDataProduct.toString());
            }
        });


        setContentView(R.layout.activity_main_example);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_example, container, false);
            return rootView;
        }
    }
}
