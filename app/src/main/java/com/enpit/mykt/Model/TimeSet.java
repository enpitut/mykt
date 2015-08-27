package com.enpit.mykt.Model;

/**
 * Created by tianhang on 2015/08/21.
 */
public class TimeSet {
    private int h;
    private int m;
    private Schedule schedule;
    public TimeSet(int h, boolean isIntegralPoint) {
        this.h = h;
        m = isIntegralPoint ? 0 : 30;
    }
    public TimeSet(int h, int m,Schedule schedule) {
        this.h = h;
        this.m = m;
        this.schedule=schedule;
    }
    public int GetH() {
        return h;
    }

    public int GetM() {
        return m;
    }
    public void SetSchedule(Schedule schedule)
    {
        this.schedule=schedule;
    }
    public Schedule GetSchedule()
    {
        return this.schedule;
    }
}
