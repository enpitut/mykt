package com.enpit.mykt.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.enpit.mykt.Fragment.ScheduleToolsFragment;
import com.enpit.mykt.Fragment.TimeFragment;
import com.enpit.mykt.Global.Global;
import com.enpit.mykt.R;


public class ScheduleActivity extends Activity  implements ScheduleToolsFragment.OnFragmentInteractionListener,TimeFragment.OnFragmentInteractionListener {
    ScheduleToolsFragment scheduleToolsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_schedule);
        scheduleToolsFragment=(ScheduleToolsFragment)getFragmentManager().findFragmentById(R.id.fragment1);

        System.out.println(Global.getGlobal().getSelectDate().getYear()+"/"+Global.getGlobal().getSelectDate().getMonth()+"/"+Global.getGlobal().getSelectDate().getDay()+"/"+Global.getGlobal().getSelectedTime().getH()+":"+Global.getGlobal().getSelectedTime().getM());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(String id) {

    }
}
