package com.dingcheng365.book;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ClassnameEditActivity extends AppCompatActivity {
    DB db=new DB(ClassnameEditActivity.this);
    String classname="";
    String _id="";
    EditText et_classname;
    String nClassname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classname_edit_layout);
        et_classname=(EditText)findViewById(R.id.cn_edit_et_classname);
        String classname=getIntent().getStringExtra("classname");
        et_classname.setText(classname,null);
        Log.d("id",classname);
        Cursor cursor= db.queryClassificationName(classname);
        while (cursor.moveToNext())
        {
            _id=cursor.getString(cursor.getColumnIndex("_id"));
        }
    }
    public void cn_edit_bt_save_Click(View view)
    {

        nClassname=et_classname.getText().toString();
        if(nClassname.equals(classname))
        {
            finish();
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("是否保存记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //更新数据
                    SQLiteDatabase dbo=db.getWritableDatabase();
                    String sql="update classification set classname='"+nClassname+"' where _id="+_id+";";
                    dbo.execSQL(sql);

                    Intent intent=new Intent(ClassnameEditActivity.this,ClassificationActivity.class);
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
    //关闭数据库
        db.close();
    }
    public void cn_edit_bt_del_Click(View view)
    {
        nClassname=et_classname.getText().toString();
        if(nClassname.equals(classname))
        {
            finish();
        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("是否删除记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //更新数据
                    /*SQLiteDatabase dbo=db.getWritableDatabase();
                    String sql="update classification set classname='"+nClassname+"' where _id="+_id+";";
                    dbo.execSQL(sql);*/
                    int i_id= Integer.parseInt(_id);
                    db.delClassification(i_id);

                    Intent intent=new Intent(ClassnameEditActivity.this,ClassificationActivity.class);
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
        //关闭数据库
        db.close();
    }
}
