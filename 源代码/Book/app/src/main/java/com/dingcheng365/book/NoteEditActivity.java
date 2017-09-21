package com.dingcheng365.book;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NoteEditActivity extends AppCompatActivity {
    String userID="",bookID="",date="",content="";
    Data data=new Data();
    DB db=new DB(this);
    private EditText et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_layout);
        Intent intent=getIntent();
        et_content=(EditText)findViewById(R.id.noteEdit_et_content) ;
        bookID=intent.getStringExtra("bookID");
        date=intent.getStringExtra("date");
        userID=data.getApplication_userID();
        Cursor cursor= db.queryNoteByUBD(userID,bookID,date);
        while (cursor.moveToNext())
        {
            content=cursor.getString(cursor.getColumnIndex("content"));
        }
        et_content.setText(content);

    }
    public void noteEdit_bt_sub_Click(View view)
    {
        content=et_content.getText().toString();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否保存记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //更新数据
                SQLiteDatabase dbo=db.getWritableDatabase();
                String sql="update note set content='"+content+"' where userID="+userID+" and bookID="+bookID+" and date='"+date+"';";
                dbo.execSQL(sql);
                db.close();
                finish();
                Intent intent=new Intent(NoteEditActivity.this,NoteDisplayActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //提示对话框
        AlertDialog alertDialog=builder.create();
        //显示对话框
        alertDialog.show();
    }
    public void noteEdit_bt_del_Click(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否删除记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLiteDatabase dbo=db.getWritableDatabase();
                String sql="delete from note where userID="+userID+" and bookID="+bookID+" and date='"+date+"';";
                dbo.execSQL(sql);
                db.close();
                finish();
                Intent intent=new Intent(NoteEditActivity.this,NoteDisplayActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //提示对话框
        AlertDialog alertDialog=builder.create();
        //显示对话框
        alertDialog.show();
    }
}
