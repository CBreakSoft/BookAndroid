package com.dingcheng365.book;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookAddActivity extends AppCompatActivity {
    private  DB db=new DB(this);
    private EditText et_bookName;
    private EditText et_author;
    private EditText et_publish;
    private EditText et_ISBN;
    private TextView tv_classname;
    private Spinner sp_classname;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_add_layout);
        et_bookName=(EditText)findViewById(R.id.bookAdd_et_bookName);
        et_author=(EditText)findViewById(R.id.bookAdd_et_author);
        et_publish=(EditText)findViewById(R.id.bookAdd_et_publish);
        et_ISBN=(EditText)findViewById(R.id.bookAdd_et_ISBN);
        tv_classname=(TextView)findViewById(R.id.bookAdd_tv_classname);
        sp_classname=(Spinner)findViewById(R.id.bookAdd_sp_classname);
        Cursor cursor=db.queryClassification();
        ArrayList<String> classnameArray=new ArrayList<String>();
        String classname;
        while (cursor.moveToNext())
        {
            classname=cursor.getString(cursor.getColumnIndex("classname"));
            classnameArray.add(classname);
        }
        //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,classnameArray);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //将adapter 添加到spinner中
        sp_classname.setAdapter(adapter);

        //添加事件Spinner事件监听
        sp_classname.setOnItemSelectedListener(new Spinner(this).getOnItemSelectedListener());

        //设置默认值
        sp_classname.setVisibility(android.view.View.VISIBLE);
    }
    public void bookAdd_bt_sub_Click(View view)
    {
        String bookName="";
        String author="";
        String publish="";
        String ISBN="";
        String classname="";
        bookName=et_bookName.getText().toString();
        author=et_author.getText().toString();
        publish=et_publish.getText().toString();
        ISBN=et_ISBN.getText().toString();
        classname=sp_classname.getSelectedItem().toString();
        ContentValues contentValues=new ContentValues();
        contentValues.put("bookName",bookName);
        contentValues.put("author",author);
        contentValues.put("publish",publish);
        contentValues.put("ISBN",ISBN);
        contentValues.put("classname",classname);
        db.insertBook(contentValues);
        Toast.makeText(BookAddActivity.this,"添加成功",Toast.LENGTH_LONG).show();
    }
    public void  bookAdd_bt_ret_Click(View view)
    {
        finish();
    }
}
