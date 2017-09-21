package com.dingcheng365.book;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClassnameaddActivity extends AppCompatActivity {

    private EditText et_classname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classnameadd_layout);
        et_classname=(EditText)findViewById(R.id.cn_et_classname);
    }
    public void  cn_bt_sub_Click(View view)
    {
        String classname=et_classname.getText().toString();
        if(classname=="")
        {
            Toast.makeText(ClassnameaddActivity.this,"不能为空",Toast.LENGTH_LONG).show();
        }
        else
        {
            DB db=new DB(ClassnameaddActivity.this);
            ContentValues contentValues=new ContentValues();
            contentValues.put("classname",classname);
            Cursor cursor= db.queryClassificationName(classname);
            if(cursor.getCount()<1)
            {
                db.insertClassification(contentValues);
                Toast.makeText(ClassnameaddActivity.this,"插入成功",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(ClassnameaddActivity.this,"分类已存在",Toast.LENGTH_LONG).show();
            }
        }
    }
    public  void cn_bt_ret_Click(View view)
    {
        finish();
    }

}
