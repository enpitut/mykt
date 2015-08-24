package com.enpit.mykt.Global;

/**
 * Created by tianhang on 2015/08/21.
 */
public class TimeSet {
    private int h;
    private int m;

    public TimeSet(int h, boolean isIntegralPoint) {
        this.h = h;
        m = isIntegralPoint ? 0 : 30;
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

}
