package com.dingcheng365.book;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
    }
    //函数自定义********************************************************************
    //启动
    public  static void actionStart(Context context)
    {
        Intent intent = new Intent(context,AboutActivity.class);
        context.startActivity(intent);
    }
    //函数事件*********************************************************************
    //关闭单击
    public  void btnAboutClose_Click(View view)
    {
        finish();
    }
}
