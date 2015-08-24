package com.enpit.mykt.Global;

/**
 * Created by tianhang on 2015/08/21.
 */
public class Global {
    private static Global global;
    private TimeSet SelectedTime;
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
            return new Global();
        }
    }
    public TimeSet getSelectedTime()
    {
        return SelectedTime;
    }

}
