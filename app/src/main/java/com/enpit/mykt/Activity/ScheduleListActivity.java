package com.enpit.mykt.Activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.enpit.mykt.Fragment.ScheduleToolsFragment;
import com.enpit.mykt.Global.Global;
import com.enpit.mykt.Global.RecyclerAdapter;
import com.enpit.mykt.Global.ScheduleTools;
import com.enpit.mykt.Global.TimeSheet;
import com.enpit.mykt.JsonManager.ScheduleManager;
import com.enpit.mykt.Model.Date;
import com.enpit.mykt.Model.Schedule;
import com.enpit.mykt.Model.TimeSet;
import com.enpit.mykt.R;

import java.util.List;

public class ScheduleListActivity extends Activity  {
    MyListAdapter myAdapter = null;
    private RecyclerView mRecyclerView;
    ListView listView;
    int screenWidth;
    int screenHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lis);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;

        listView=(ListView)findViewById(R.id.scheduleView);
        myAdapter = new MyListAdapter(this, R.layout.view_schedule);
        listView.setAdapter(myAdapter);



        mRecyclerView = (RecyclerView) findViewById(R.id.scheduleToolsView);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecyclerView.setLayoutManager(layoutManager);


        dataset[] mDataset = new dataset[ScheduleTools.GetSchedules().size()];

        for(int i = 0; i < ScheduleTools.GetSchedules().size(); i++){

            mDataset[i] = new dataset( ScheduleTools.GetSchedules().get(i).GetScheduleName()
                    , ScheduleTools.GetSchedules().get(i).GetScheduleColor());
        }

        RecyclerAdapter mAdapter = new RecyclerAdapter(mDataset, this);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnDragListener(new NoMyDragListener());

        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lis, menu);
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

    public class MyListAdapter extends ArrayAdapter<Object> {

        TimeSet touchTime = Global.getGlobal().getSelectedTime();

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
                scheduleListStr[i]="スケジュール";
            }
        }
        private int[] colors = new int[]{0xFFFFFF, 0x12626569};

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
            LinearLayout viewLayout;

//            if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    mTextViewResourceID, null);
            time = (TextView)convertView. findViewById(R.id.timeList);
            time.setOnDragListener(new NoMyDragListener());
//            schedule = (TextView)convertView. findViewById(R.id.scheduleList);
            viewLayout=(LinearLayout)convertView.findViewById(R.id.viewLayout);
            viewLayout.setOnDragListener(new MyDragListener());
//            }
            int colorPos = position % colors.length;
            convertView.setBackgroundColor(colors[colorPos]);

            time.setText(timeListStr[position]);
            /*
                スケジュールのアイコン設置、お願い致します。scheduleListの中にすでに選択された日のスケジュール
             */

            ScheduleManager scheduleManager=new ScheduleManager();
            List<TimeSet> scheduleList= scheduleManager.GetSchedule(Global.getGlobal().getSelectDate());
            for (int i=0;i<scheduleList.size();i++)
            {
                if(timeListStr[position].toString().matches(scheduleList.get(i).getH()+":"+scheduleList.get(i).getM())) {
                    TextView textView = new TextView(mContext);
                    textView.setHeight(250);
                    textView.setWidth(600);
                    textView.setText(scheduleList.get(i).getSchedule().GetScheduleName());
                    textView.setBackgroundColor(scheduleList.get(i).getSchedule().GetScheduleColor());
                    viewLayout.addView(textView);
                    textView.setOnTouchListener(new MyTouchListener());
                }
            }

//            schedule.setText(scheduleListStr[position]);


            return convertView;
        }
    }
    public static class dataset{
        public String item_name;
        public int color;

        public dataset(String item_name, int color){
            this.item_name = item_name;
            this.color = color;
        }

    }
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private dataset[] mDataset;
        private Context mContext;

        public RecyclerAdapter(dataset[] dataset, Context context) {
            mDataset = dataset;
            mContext = context;
        }

        // Not use static
        public class ViewHolder extends RecyclerView.ViewHolder{
            int lastX;
            int lastY;
            public TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text);
                itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Toast.makeText(
                                mContext,
                                "onItemClick - " + getPosition() + " - "
                                        + mTextView.getText().toString() + " - "
                                        + mDataset[getPosition()], Toast.LENGTH_SHORT).show();
                    }
                });
                itemView.setOnTouchListener(new MyTouchListener());
            }
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setBackgroundColor(mDataset[position].color);
            holder.mTextView.setText(mDataset[position].item_name);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }
    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    TextView mTextView = (TextView) view.findViewById(R.id.text);

                    ScheduleManager SM=new ScheduleManager();
                    String timeText=((TextView)((RelativeLayout)container.getParent()).findViewById(R.id.timeList)).getText().toString();
                    TimeSet schedule=new TimeSet(Integer.parseInt(timeText.split(":")[0]),Integer.parseInt(timeText.split(":")[1])==0);
                    schedule.setSchedule(new Schedule( mTextView.getText().toString(),((ColorDrawable)mTextView.getBackground()).getColor()));
                    SM.AddSchedule(schedule,Global.getGlobal().getSelectDate());

                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
    class NoMyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // Do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }
    }
}
