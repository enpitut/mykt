package com.enpit.mykt.Activity;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.enpit.mykt.Global.TimeSet;
import com.enpit.mykt.Global.TimeSheet;
import com.enpit.mykt.R;

import java.util.List;

/**
 * Created by tianhang on 2015/08/21.
 */
public class ScheduleListActivity extends ListActivity {

    /**
     * 仮想データ初期化
     **/
    TimeSet touchTime = new TimeSet(5, false);
    /**
     * 終わり
     **/

    ListView mListView = null;
    MyListAdapter myAdapter = null;
    ScheduleListActivity scheduleListActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        scheduleListActivity = this;
        mListView = getListView();
        myAdapter = new MyListAdapter(this, R.layout.schedule_view);
        setListAdapter(myAdapter);
        super.onCreate(savedInstanceState);
    }

    public class MyListAdapter extends ArrayAdapter<Object> {
        int mTextViewResourceID = 0;
        private Context mContext;
        List<TimeSet> scheduleList = TimeSheet.getScheduleList(touchTime);
        int size = scheduleList.size();

        String[] timeListStr = new String[size];
        String[] scheduleListStr = new String[size];

        public MyListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
            mTextViewResourceID = textViewResourceId;
            mContext = context;
            for (int i = 0; i < size; i++) {
                timeListStr[i] = scheduleList.get(i).getH() + ":" + scheduleList.get(i).getM();
            }
            scheduleListStr=timeListStr;
        }


        private int[] colors = new int[]{0x0000000000, 0xff626569};

        public int getCount() {
            return timeListStr.length;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {


            TextView time = null;
            TextView schedule = null;


//            if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    mTextViewResourceID, null);
            time = (TextView)convertView. findViewById(R.id.array_time);
            schedule = (TextView)convertView. findViewById(R.id.array_schedule);
//            }
            int colorPos = position % colors.length;
            convertView.setBackgroundColor(colors[colorPos]);

            time.setText(timeListStr[position]);
            schedule.setText(scheduleListStr[position]);
            return convertView;
        }
    }
}
