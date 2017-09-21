package com.dingcheng365.book;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText  et_name;
    private  EditText et_password;
    private Button bt_log;
    private  Button bt_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Log.d("book.db","构建数据库");
        DB db=new DB(getApplicationContext());
        db.getWritableDatabase();
        et_name=(EditText)findViewById(R.id.log_et_name);
        et_password=(EditText)findViewById(R.id.log_et_password);
    }
    public void log(String username, String password, Context context)
    {
        boolean msg=false;
        DB db=new DB(context);
        Cursor cursor= db.queryUserName(username);
        if(cursor.getCount()<1)
        {
            Toast.makeText( context,"账号不存在",Toast.LENGTH_LONG).show();

        }
        else
        {
            String pass="";
            String userID="";
            while (cursor.moveToNext())
            {
                pass=cursor.getString(cursor.getColumnIndex("password"));
                userID=cursor.getString(cursor.getColumnIndex("_id"));
            }

            if(password!=pass&&!pass.equals(password))
            {
                Toast.makeText( context,"密码不正确",Toast.LENGTH_LONG).show();
            }
           else
            {
                Data data=new Data();
                data.setApplication_userID(userID);
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
                finish();
            }
        }
    }
    public void log_bt_reg_Click(View view)
    {
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    public void log_bt_log_Click(View view)
    {
        String username=et_name.getText().toString();
        String password=et_password.getText().toString();
        if(username==""||username.equals(""))
        {

            Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
        }
        if(password==""||password.equals(""))
        {
            Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
        }
        log(username,password,LoginActivity.this);
    }
}
