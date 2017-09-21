package com.dingcheng365.book;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteAdd2Activity extends AppCompatActivity {
    DB db=new DB(this);
    String bookID="";
    String userID="";
    String date="";
    String content="";
    private EditText et_content;
    private TextView tv_bookID;
    private TextView tv_userID;
    Data data=new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_add2_layout);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        userID=data.getApplication_userID();
        bookID= bundle.getString("bookID");
        tv_bookID=(TextView)findViewById(R.id.noteAdd2_tv_bookID);
        tv_userID=(TextView)findViewById(R.id.noteAdd2_tv_userID);
        et_content=(EditText)findViewById(R.id.noteAdd2_et_content);
        tv_userID.setText("用户："+userID);
        tv_bookID.setText("书籍："+bookID);
    }
    public  void  noteAdd2_bt_sub_Click(View view)
    {
        content=et_content.getText().toString();
        SimpleDateFormat formatter    =   new    SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        date    =    formatter.format(curDate);
        ContentValues contentValues=new ContentValues();
        contentValues.put("userID",userID);
        contentValues.put("bookID",bookID);
        contentValues.put("date",date);
        contentValues.put("content",content);
        db.insertNote(contentValues);
        Toast.makeText(NoteAdd2Activity.this,"添加成功",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(NoteAdd2Activity.this,MainActivity.class);
        startActivity(intent);
    }
    public  void  noteAdd2_bt_ret_Click(View view)
    {
        finish();
    }

}
