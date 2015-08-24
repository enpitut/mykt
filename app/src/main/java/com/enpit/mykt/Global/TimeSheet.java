package com.enpit.mykt.Global;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhang on 2015/08/21.
 */
public class TimeSheet {
    private static List<TimeSet> listTimeSet = new ArrayList<TimeSet>() {{
        add(new TimeSet(0, true));
        add(new TimeSet(0, false));
        add(new TimeSet(1, true));
        add(new TimeSet(1, false));
        add(new TimeSet(2, true));
        add(new TimeSet(2, false));
        add(new TimeSet(3, true));
        add(new TimeSet(3, false));
        add(new TimeSet(4, true));
        add(new TimeSet(4, false));
        add(new TimeSet(5, true));
        add(new TimeSet(5, false));
        add(new TimeSet(6, true));
        add(new TimeSet(6, false));
        add(new TimeSet(7, true));
        add(new TimeSet(7, false));
        add(new TimeSet(8, true));
        add(new TimeSet(8, false));
        add(new TimeSet(9, true));
        add(new TimeSet(9, false));
        add(new TimeSet(10, true));
        add(new TimeSet(10, false));
        add(new TimeSet(11, true));
        add(new TimeSet(11, false));
        add(new TimeSet(12, true));
        add(new TimeSet(12, false));
        add(new TimeSet(13, true));
        add(new TimeSet(13, false));
        add(new TimeSet(14, true));
        add(new TimeSet(14, false));
        add(new TimeSet(15, true));
        add(new TimeSet(15, false));
        add(new TimeSet(16, true));
        add(new TimeSet(16, false));
        add(new TimeSet(17, true));
        add(new TimeSet(17, false));
        add(new TimeSet(18, true));
        add(new TimeSet(18, false));
        add(new TimeSet(19, true));
        add(new TimeSet(19, false));
        add(new TimeSet(20, true));
        add(new TimeSet(20, true));
        add(new TimeSet(21, true));
        add(new TimeSet(21, false));
        add(new TimeSet(22, true));
        add(new TimeSet(22, false));
        add(new TimeSet(23, true));
        add(new TimeSet(23, false));
        add(new TimeSet(24, true));
        add(new TimeSet(24, false));
    }};

    public static List<TimeSet> getTimeSheet(TimeSet time) {
        return listTimeSet;
    }

    public static List<TimeSet> getScheduleList(TimeSet time) {
        List<TimeSet> scheduleList = new ArrayList<TimeSet>();
        int index = time.getH() * 2;
        int weight = 13;
        if (time.getM() == 30) {
            index++;
        }
        if (index + weight / 2 > listTimeSet.size()) {
            for (int i = listTimeSet.size() - weight - 1; i < listTimeSet.size(); i++) {
                scheduleList.add(listTimeSet.get(i));
            }
        } else if (index - weight / 2 < 0) {
            for (int i = 0; i < weight; i++) {
                scheduleList.add(listTimeSet.get(i));
            }
        } else {
            for (int i = index - weight / 2; i <= index + weight / 2; i++) {
                scheduleList.add(listTimeSet.get(i));
            }
        }
        return scheduleList;
    }
}
