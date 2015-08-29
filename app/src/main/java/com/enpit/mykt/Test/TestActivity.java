package com.enpit.mykt.Test;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.enpit.mykt.JsonManager.ScheduleManager;
import com.enpit.mykt.Model.Date;
import com.enpit.mykt.Model.Schedule;
import com.enpit.mykt.Model.TimeSet;
import com.enpit.mykt.R;

import java.util.List;

public class TestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScheduleManager SM=new ScheduleManager();

        /*
            スケジュール　セーブ
         */
        TimeSet schedule=new TimeSet(5,false);
        schedule.setSchedule(new Schedule("Meeting", Color.RED));
        Date DateToday=new Date(2015,8,27);
        SM.AddSchedule(schedule,DateToday);
        /*
            スケジュール　ロード
         */

        List<TimeSet> schedules= SM.GetSchedule(DateToday);
        //schedulesを使用する

        setContentView(R.layout.activity_test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
}
