package com.enpit.mykt.Global;

import android.graphics.Color;

import com.enpit.mykt.Model.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhang on 2015/08/27.
 */
public class ScheduleTools {
    private static List<Schedule> schedules=new ArrayList<Schedule>(){{
        add(new Schedule("Report",Color.RED));
        add(new Schedule("Seminar",Color.BLUE));
        add(new Schedule("Meeting",Color.GRAY));
        add(new Schedule("Running",Color.MAGENTA));
        add(new Schedule("Sleep",Color.GREEN));
        add(new Schedule("Food",Color.DKGRAY));
        add(new Schedule("Pool",Color.YELLOW));
        add(new Schedule("Homework",Color.LTGRAY));
        add(new Schedule("Free",Color.WHITE));


    }};
    private ScheduleTools()
    {

    }
    public static List<Schedule> GetSchedules()
    {
        return schedules;
    }
    public void AddScheduleTool(Schedule schedule) {
        schedules.add(schedule);
    }
}
