package com.dingcheng365.book;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_name;
    private  EditText  et_password;
    private Button bt_reg;
    private  Button bt_reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        et_name=(EditText)findViewById(R.id.reg_et_name);
        et_password=(EditText)findViewById(R.id.reg_et_password);
        bt_reg=(Button)findViewById(R.id.reg_bt_reg);
        bt_reset=(Button)findViewById(R.id.reg_bt_reset);

    }
    public void reg_bt_reg_Click(View view)
    {
        String username=et_name.getText().toString();
        String password=et_password.getText().toString();
        if(username==""||username.equals(""))
        {

            Toast.makeText(RegisterActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
        }
        if(password==""||password.equals(""))
        {
            Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
        }
        DB db=new DB(getApplicationContext());

        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        Cursor cursor=db.queryUserName(username);
        if(cursor.getCount()==0)
        {
            db.insertUser(contentValues);
            LoginActivity loginActivity=new LoginActivity();
            loginActivity.log(username,password,RegisterActivity.this);
        }
        else
        {
            Toast.makeText(RegisterActivity.this,"账号已存在",Toast.LENGTH_LONG).show();
        }

    }
    public  void btnRegClose_Click(View view)
    {
        finish();
    }
}
