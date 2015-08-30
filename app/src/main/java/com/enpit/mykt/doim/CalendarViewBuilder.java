package com.enpit.mykt.doim;

import android.content.Context;

import com.enpit.mykt.widget.CalendarView;
import com.enpit.mykt.widget.CalendarView.CallBack;

public class CalendarViewBuilder {
		private CalendarView[] calendarViews;

		public  CalendarView[] createMassCalendarViews(Context context,int count,int style,CallBack callBack){
			calendarViews = new CalendarView[count];
			for(int i = 0; i < count;i++){
				calendarViews[i] = new CalendarView(context, style,callBack);
			}
			return calendarViews;
		}
		
		public  CalendarView[] createMassCalendarViews(Context context,int count,CallBack callBack){
			
			return createMassCalendarViews(context, count, CalendarView.MONTH_STYLE,callBack);
		}

		public void swtichCalendarViewsStyle(int style){
			if(calendarViews != null)
			for(int i = 0 ;i < calendarViews.length;i++){
				calendarViews[i].switchStyle(style);
			}
		}

		
		public void backTodayCalendarViews(){
			if(calendarViews != null)
			for(int i = 0 ;i < calendarViews.length;i++){
				calendarViews[i].backToday();
			}
		}
}
