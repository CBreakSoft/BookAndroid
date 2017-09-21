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
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteDisplayActivity extends AppCompatActivity {
    String userID="";
    Data data=new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_display_layout);
        //实例DBHelper
        final DB db=new DB(this);
        userID=data.getApplication_userID();
        //获得Cuosor
        Cursor c=db.queryNoteByUser(userID);
        int count= c.getCount();
        final ListView listView=(ListView) findViewById(R.id.noteDisplay_list);
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map;
//遍历数据
        while (c.moveToNext())
        {
            map = new HashMap<String, Object>();
            map.put("userID",c.getString(c.getColumnIndex("userID")));
            map.put("bookID",c.getString(c.getColumnIndex("bookID")));
            map.put("date",c.getString(c.getColumnIndex("date")));
            list.add(map);
        }
        SimpleAdapter adapter2 = new SimpleAdapter(this,list,R.layout.note_display_layout,new String[]{"bookID","date"},new int[]{R.id.noteDisplay_tv_bookID,R.id.noteDisplay_tv_date});
        listView.setAdapter(adapter2);
        //************************************************************************************************************************************

        //对话框
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //为listView添加监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String mytitleBookID = null;
                String mytitledate = null;
                String mycontent = null;

                switch(arg0.getId())
                {
                    case R.id.noteDisplay_list:
                        ListView templist = (ListView) arg0;
                        View mView = templist.getChildAt(arg2);
                        TextView mytextviewBookID= (TextView) mView.findViewById(R.id.noteDisplay_tv_bookID);
                        mytitleBookID = mytextviewBookID.getText().toString();
                        TextView mytextviewdate= (TextView) mView.findViewById(R.id.noteDisplay_tv_date);
                        mytitledate = mytextviewdate.getText().toString();
                }
                Intent intent=new Intent(NoteDisplayActivity.this,NoteEditActivity.class);
                intent.putExtra("bookID",mytitleBookID);
                intent.putExtra("date",mytitledate);
                startActivity(intent);
                finish();
            }
        });
        //关闭数据库
        db.close();
    }

}
