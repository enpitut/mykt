package com.enpit.mykt.Global;

import com.enpit.mykt.Model.Date;
import com.enpit.mykt.Model.TimeSet;

/**
 * Created by tianhang on 2015/08/21.
 */
public class Global {
    private static Global global;
    private static TimeSet selectedTime;
    private static Date selectDate;
    private  Global()
    {
        selectedTime
//                =new TimeSet(-1,true);
        = new TimeSet(5, false);
        selectDate =new Date(2015,8,27);
    }
    public static Global getGlobal()
    {
        if(global!=null)
        {
            return  global;
        }
        else{
            global=new Global();
            return global;
        }
    }
    public TimeSet getSelectedTime()
    {
        return selectedTime;
    }
    public void setSelectedTime(int h)
    {
        selectedTime.setH(h);
        selectedTime.setM(0);
    }
    public void setSelectDate(int year,int month,int day)
    {
        selectDate.setYear(year);
        selectDate.setMonth(month);
        selectDate.setDay(day);
    }
    public Date getSelectDate()
    {
        return selectDate;
    }



}
