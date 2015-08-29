package com.enpit.mykt.JsonManager;

import com.enpit.mykt.Model.Date;
import com.enpit.mykt.Model.Schedule;
import com.enpit.mykt.Model.TimeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianhang on 2015/08/27.
 */
public class ScheduleManager
{
    public void AddSchedule(TimeSet schedule,Date time)
    {
        String fileName=Integer.toString(time.getYear())+Integer.toString(time.getMonth())+Integer.toString(time.getDay());
        JSONObject newSchedule=new JSONObject();
        try {
            newSchedule.put("Time_H",schedule.getH());
            newSchedule.put("Time_M",schedule.getM());
            newSchedule.put("ScheduleName",schedule.getSchedule().GetScheduleName());
            newSchedule.put("ScheduleColor",schedule.getSchedule().GetScheduleColor());
            DataManager.Insert(newSchedule,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<TimeSet> GetSchedule(Date time) {
        List<TimeSet> schedules = new ArrayList<TimeSet>();
        String fileName = Integer.toString(time.getYear()) + Integer.toString(time.getMonth()) + Integer.toString(time.getDay());
        try {
            String JSON = DataManager.Select(fileName);
            JSONObject mainNode = new JSONObject(JSON);
            JSONArray parentNode = mainNode.getJSONArray("MainNode");
            // 此时还未读取任何json文本，直接读取就是一个JSONObject对象。
            // 如果此时的读取位置在"name" : 了，那么nextValue就是"yuanzhifei89"（String）
            for (int i = 0; i < parentNode.length(); i++) {
                schedules.add(new TimeSet(
                        ((JSONObject) parentNode.get(i)).getInt("Time_H"),
                        ((JSONObject) parentNode.get(i)).getInt("Time_M"),
                        new Schedule(((JSONObject) parentNode.get(i)).getString("ScheduleName"), ((JSONObject) parentNode.get(i)).getInt("ScheduleColor"))
                ));
            }

        } catch (Exception e) {
            // 异常处理代码
        }
        return schedules;
    }
}
