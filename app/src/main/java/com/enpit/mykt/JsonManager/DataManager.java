package com.enpit.mykt.JsonManager;
import android.os.Environment;


import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;


/**
 * Created by tianhang on 2015/08/27.
 */
public class DataManager{

    public static String Select(String fileName){
        String res="";
        try{
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            FileInputStream fin = new FileInputStream(file);
            int length = fin.available();
            byte [] buffer = new byte[length];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }
        return res;
    }
    public static void Insert(JSONObject data,String fileName)
    {
        try {
            String Json =Select(fileName);
            JSONArray fatherNode;
            if (Json == "") {
                 fatherNode = new JSONArray();

            } else {
                JSONObject mainNode=new JSONObject(Json);
                 fatherNode=mainNode.getJSONArray("MainNode");
            }
            fatherNode.put(data);
            data = new JSONObject();
            data.put("MainNode", fatherNode);
            Save(data, fileName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private static void Save(JSONObject data,String fileName) throws Exception{
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            if(!file.getParentFile().exists()){//判断父文件是否存在，如果不存在则创建
                file.getParentFile().mkdirs();
            }
            FileOutputStream outStream = new FileOutputStream(file);
            outStream.write(data.toString().getBytes());
            outStream.close();
        }
    }

    public void Update(String fileName)
    {

    }
    public void Delete()
    {

    }

}
