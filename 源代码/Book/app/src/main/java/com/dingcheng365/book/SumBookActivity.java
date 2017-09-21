package com.dingcheng365.book;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumBookActivity extends AppCompatActivity {
    String userID="";
    Data data=new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sum_book_layout);
        //实例DBHelper
        final DB db=new DB(this);
        userID=data.getApplication_userID();
        //获得Cuosor
        Cursor c=db.sumBook(userID);
        int count= c.getCount();
        final ListView listView=(ListView) findViewById(R.id.sumBook_list);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
//遍历数据
        while (c.moveToNext())
        {
            map = new HashMap<String, Object>();
            map.put("bookID",c.getString(c.getColumnIndex("bookID")));
            map.put("count",c.getString(c.getColumnIndex("count")));
            list.add(map);
        }
        SimpleAdapter adapter2 = new SimpleAdapter(this,list,R.layout.sum_book_layout,new String[]{"bookID","count"},new int[]{R.id.sumBook_tv_bookID,R.id.sumBook_tv_count});
        listView.setAdapter(adapter2);
        //关闭数据库
        db.close();

    }
}
