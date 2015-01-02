package com.asynhkm.example;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.asynhkm.productchecker.Checker.CheckerCB;
import com.asynhkm.productchecker.Checker.HKMCheckerPlugable;
import com.asynhkm.productchecker.Util.Tool;
import com.asynhkm.productchecker.schema.DataProductVersion;


public class MainExample extends Activity {


    private Context ctx;
    private HKMCheckerPlugable main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
    public static class PlaceholderFragment extends Fragment implements CheckerCB, View.OnLongClickListener {
        private TextView checkingView;

        @Override
        public boolean onLongClick(View view) {
            if (!checking) {
                checking = true;
                Tool.trace(_act, "checking license now!");
                plugable = new HKMCheckerPlugable("54a36152417777b2152fe14b", getActivity());
                plugable.netStartCheck(this);
                checkingView.setText("Checking..");
            }


            return false;
        }

        @Override
        public void tr_success(DataProductVersion pv) {
            Tool.trace(_act, pv.toString());
            checkingView.setText("Done.." + pv.toString());
            checking = false;
        }

        @Override
        public void tr_fail(DataProductVersion pv) {
            Tool.trace(_act, pv.toString());
            checking = false;
            checkingView.setText("Done.." + pv.toString());
        }


        private boolean checking = false;
        private HKMCheckerPlugable plugable;
        private Activity _act;

        public PlaceholderFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_example, container, false);
            Button mbutton = (Button) rootView.findViewById(R.id.button_trigger);
            checkingView = (TextView) rootView.findViewById(R.id.colorView);
            mbutton.setOnLongClickListener(this);
            _act = getActivity();
            return rootView;
        }

        //public
    }
}
