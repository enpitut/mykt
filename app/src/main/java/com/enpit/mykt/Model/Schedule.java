package com.enpit.mykt.Model;

/**
 * Created by tianhang on 2015/08/27.
 */
public class Schedule {
    String scheduleName;
    int scheduleColor;
    public Schedule(String scheduleName,int scheduleColor)
    {
        this.scheduleColor=scheduleColor;
        this.scheduleName=scheduleName;
    }
    public String GetScheduleName()
    {
        return  this.scheduleName;
    }
    public int GetScheduleColor()
    {
        return this.scheduleColor;
    }

}
