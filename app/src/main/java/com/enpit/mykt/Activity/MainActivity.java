package com.enpit.mykt.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.TextView;

import com.enpit.mykt.Global.Global;
import com.enpit.mykt.doim.CalendarViewBuilder;
import com.enpit.mykt.doim.CustomDate;
import com.enpit.mykt.widget.CalendarView;
import com.enpit.mykt.widget.CalendarView.CallBack;
import com.enpit.mykt.widget.CalendarViewPagerLisenter;
import com.enpit.mykt.widget.CircleTextView;
import com.enpit.mykt.widget.CustomViewPagerAdapter;

import com.enpit.mykt.R;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements OnClickListener,CallBack{

	private ViewPager viewPager;
	private CalendarView[] views;
	private TextView showYearView;
	private TextView showMonthView;
	private TextView showWeekView;
	private TextView monthCalendarView;
	private TextView weekCalendarView;
	private CalendarViewBuilder builder = new CalendarViewBuilder();
	private View mContentPager;
	private CustomDate mClickDate;

	public static final String MAIN_ACTIVITY_CLICK_DATE = "main_click_date";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewbyId();
	}


	private void findViewbyId() {
		viewPager = (ViewPager) this.findViewById(R.id.viewpager);
		showMonthView = (TextView)this.findViewById(R.id.show_month_view);
		showYearView = (TextView)this.findViewById(R.id.show_year_view);
		showWeekView = (TextView)this.findViewById(R.id.show_week_view);
		views = builder.createMassCalendarViews(this, 5, this);
		monthCalendarView = (TextView) this.findViewById(R.id.month_calendar_button);
		weekCalendarView = (TextView) this.findViewById(R.id.week_calendar_button);
		mContentPager = this.findViewById(R.id.contentPager);
		monthCalendarView.setOnClickListener(this);
		weekCalendarView.setOnClickListener(this);
		setViewPager();
//		setOnDrawListener();
	}


	private void setViewPager() {
		CustomViewPagerAdapter<CalendarView> viewPagerAdapter = new CustomViewPagerAdapter<CalendarView>(views);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setCurrentItem(498); 
		viewPager.setOnPageChangeListener(new CalendarViewPagerLisenter(viewPagerAdapter));
	}

//	private void setOnDrawListener() {
//		mSlidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
//
//			@Override
//			public void onDrawerOpened() {
//				builder.swtichCalendarViewsStyle(CalendarView.WEEK_STYLE);
//			}
//		});
//		mSlidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListener() {
//
//			@Override
//			public void onScrollStarted() {
//				builder.swtichCalendarViewsStyle(CalendarView.MONTH_STYLE);
//			}
//
//			@Override
//			public void onScrollEnded() {
//			}
//		});
//	}

 @Override
 protected void onDestroy() {  
     super.onDestroy();  
 }  

 public void setShowDateViewText(int year ,int month){
	 showYearView.setText(year+"");
	 showMonthView.setText(month+"");
	 //showWeekView.setText(DateUtil.weekName[DateUtil.getWeekDay()-1]);
 }

 	@Override
 	public void onClick(View v) {
 		switch (v.getId()) {
		case R.id.month_calendar_button:
			swtichBackgroundForButton(true);
			builder.swtichCalendarViewsStyle(CalendarView.MONTH_STYLE);
// 			mSlidingDrawer.close();
			break;
		case R.id.week_calendar_button:
			swtichBackgroundForButton(false);
//			mSlidingDrawer.open();
			break;
 		}
 	}
 	
 	private void swtichBackgroundForButton(boolean isMonth){
 		if(isMonth){
 			monthCalendarView.setBackgroundResource(R.drawable.press_left_text_bg);
 			weekCalendarView.setBackgroundColor(Color.TRANSPARENT);
 		}else{
 			weekCalendarView.setBackgroundResource(R.drawable.press_right_text_bg);
 			monthCalendarView.setBackgroundColor(Color.TRANSPARENT);
 		}
 	}


	@Override
	public void onMesureCellHeight(int cellSpace) {
//		mSlidingDrawer.getLayoutParams().height = mContentPager.getHeight() - cellSpace;
	}

	@Override
	public void clickDate(CustomDate date) {
		mClickDate = date;

			Global.getGlobal().setSelectDate(date.year, date.month, date.day);
//			Toast.makeText(this, date.year+"-"+date.month+"-"+date.day, Toast.LENGTH_SHORT).show();
			Intent i = new Intent(this, DayTimeActivity.class);
			startActivity(i);



	}

	@Override
	public void changeDate(CustomDate date) {
		setShowDateViewText(date.year,date.month);
	}
 
}  



	


