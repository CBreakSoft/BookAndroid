package com.dingcheng365.book;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sum_user_layout);
        //实例DBHelper
        final DB db=new DB(this);
        //获得Cuosor
        Cursor c=db.sumUser();
        int count= c.getCount();
        final ListView listView=(ListView) findViewById(R.id.sumUser_list);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
//遍历数据
        while (c.moveToNext())
        {
            map = new HashMap<String, Object>();
            map.put("userID",c.getString(c.getColumnIndex("userID")));
            map.put("count",c.getString(c.getColumnIndex("count")));
            list.add(map);
        }
        SimpleAdapter adapter2 = new SimpleAdapter(this,list,R.layout.sum_user_layout,new String[]{"userID","count"},new int[]{R.id.sumUser_tv_UserID,R.id.sumUser_tv_count});
        listView.setAdapter(adapter2);
        //关闭数据库
        db.close();
    }
}
