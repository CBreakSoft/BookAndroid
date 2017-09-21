package com.dingcheng365.book;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


    }

    public void cn_bt_add_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,ClassnameaddActivity.class);
        startActivity(intent);
    }
    public  void cn_bt_display_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,ClassificationActivity.class);
        startActivity(intent);
    }

    public void m_bt_bookAdd_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,BookAddActivity.class);
        startActivity(intent);
    }
    public void m_bt_bookDisplay_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,BookDisplayActivity.class);
        startActivity(intent);
    }
    public void m_bt_noteAdd_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,NoteAddActivity.class);
        startActivity(intent);
    }
    public void m_bt_noteDisplay_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,NoteDisplayActivity.class);
        startActivity(intent);
    }
    public void m_bt_sumBook_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,SumBookActivity.class);
        startActivity(intent);
    }
    public void m_bt_sumUser_Click(View view)
    {
        Intent intent=new Intent(MainActivity.this,SumUserActivity.class);
        startActivity(intent);
    }
    public void m_bt_exit_Click(View view)
    {
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuMainAbout:
                AboutActivity.actionStart(MainActivity.this);
                break;
            case R.id.mnuMainClose:
                finish();
                break;
        }
        return true;
    }
}
