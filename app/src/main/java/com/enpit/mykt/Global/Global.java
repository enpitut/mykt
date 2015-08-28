package com.enpit.mykt.Global;

import com.enpit.mykt.Model.TimeSet;

/**
 * Created by tianhang on 2015/08/21.
 */
public class Global {
    private static Global global;
    private static TimeSet SelectedTime;
    private  Global()
    {
        SelectedTime
//                =new TimeSet(-1,true);
        = new TimeSet(5, false);
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
        return SelectedTime;
    }
    public void setSelectedTime(int h)
    {
        SelectedTime.setH(h);
        SelectedTime.setM(0);
    }

}
