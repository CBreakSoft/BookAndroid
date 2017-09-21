package com.dingcheng365.book;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class NoteAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add_layout);
        //实例DBHelper
        final DB db=new DB(this);
        //获得Cuosor
        Cursor c=db.queryBook();
        int count= c.getCount();
        //列表项数组
        String[] from={"_id","bookName","author","publish","ISBN","classname"};
        //列表项id
        int[] to={R.id.noteAdd_tv_bookID,R.id.noteAdd_tv_bookName,R.id.noteAdd_tv_author,R.id.noteAdd_tv_publish,R.id.noteAdd_tv_ISBN,R.id.noteAdd_tv_classname};
        //列表视图
        final ListView listView=(ListView) findViewById(R.id.noteAdd_list);
        //适配器
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.note_add_layout,c,from,to,0);//this,R.layout.display_layout,c,from,to
        //为列表添加适配器
        listView.setAdapter(adapter);
        //对话框
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //为listView添加监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                String mytitleID = null;
                String mytitleName = null;
                String mytitlePublish = null;
                String mytitleISBN = null;
                String mytitleAuthor = null;
                String mytitleCN = null;
                String mycontent = null;

                switch(arg0.getId())
                {
                    case R.id.noteAdd_list:
                        ListView templist = (ListView) arg0;
                        View mView = templist.getChildAt(arg2);
                        TextView mytextviewID = (TextView) mView.findViewById(R.id.noteAdd_tv_bookID);
                        mytitleID = mytextviewID.getText().toString();
                        TextView mytextviewName = (TextView) mView.findViewById(R.id.noteAdd_tv_bookName);
                        mytitleName = mytextviewName.getText().toString();
                        TextView mytextviewAuthor = (TextView) mView.findViewById(R.id.noteAdd_tv_author);
                        mytitleAuthor = mytextviewAuthor.getText().toString();
                        TextView mytextviewPublish = (TextView) mView.findViewById(R.id.noteAdd_tv_publish);
                        mytitlePublish = mytextviewPublish.getText().toString();
                        TextView mytextviewISBN = (TextView) mView.findViewById(R.id.noteAdd_tv_ISBN);
                        mytitleISBN = mytextviewISBN.getText().toString();
                        TextView mytextviewCN = (TextView) mView.findViewById(R.id.noteAdd_tv_classname);
                        mytitleCN = mytextviewCN.getText().toString();
                }
                Bundle bundle=new Bundle();
                bundle.putString("bookID",mytitleID);
                bundle.putString("bookName",mytitleName);
                bundle.putString("author",mytitleAuthor);
                bundle.putString("publish",mytitlePublish);
                bundle.putString("ISBN",mytitleISBN);
                bundle.putString("classname",mytitleCN);
                Intent intent=new Intent(NoteAddActivity.this,NoteAdd2Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //关闭数据库
        db.close();
    }
}
