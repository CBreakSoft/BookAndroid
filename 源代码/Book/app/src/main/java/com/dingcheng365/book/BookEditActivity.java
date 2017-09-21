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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class BookEditActivity extends AppCompatActivity {
    public String  bookID="",bookName="",author="",publish="",ISBN="",cn="";
    private  DB db=new DB(this);
    private TextView tv_bookID;
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
        setContentView(R.layout.book_edit_layout);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        bookID= bundle.getString("bookID");
        bookName= bundle.getString("bookName");
        author=bundle.getString("author");
        publish=bundle.getString("publish");
        ISBN=bundle.getString("ISBN");
        cn=bundle.getString("classname");

        tv_bookID=(TextView)findViewById(R.id.bookEdit_tv_bookID);
        et_bookName=(EditText)findViewById(R.id.bookEdit_et_bookName);
        et_author=(EditText)findViewById(R.id.bookEdit_et_author);
        et_publish=(EditText)findViewById(R.id.bookEdit_et_publish);
        et_ISBN=(EditText)findViewById(R.id.bookEdit_et_ISBN);
        tv_classname=(TextView)findViewById(R.id.bookEdit_tv_classname);
        sp_classname=(Spinner)findViewById(R.id.bookEdit_sp_classname);
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

        int vis=0;
        String arrClassname="";
        while (true)
        {
            try
            {
                arrClassname=classnameArray.get(vis);
            }
            catch (Exception e)
            {
                break;
            }

            if(cn.equals(arrClassname))
            {
                break;
            }
            vis++;
        }
        //设置默认值
        sp_classname.setSelection(vis);
        tv_bookID.setText("编号："+bookID);
        et_author.setText(author);
        et_bookName.setText(bookName);
        et_publish.setText(publish);
        et_ISBN.setText(ISBN);
    }
    public void bookEdit_bt_sub_Click(View view)
    {
        bookName=et_bookName.getText().toString();
        author=et_author.getText().toString();
        publish=et_publish.getText().toString();
        ISBN=et_ISBN.getText().toString();
        cn=sp_classname.getSelectedItem().toString();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否保存记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //更新数据
                SQLiteDatabase dbo=db.getWritableDatabase();
                String sql="update book set bookName='"+bookName+"',author='"+author+"',publish='"+publish+"',ISBN='"+ISBN+"',classname='"+cn+"' where _id="+bookID+";";
                dbo.execSQL(sql);
                Intent intent=new Intent(BookEditActivity.this,BookDisplayActivity.class);
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
    public  void bookEdit_bt_del_Click(View view)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("是否删除记录？").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int i_id= Integer.parseInt(bookID);
                db.delBook(i_id);
                Intent intent=new Intent(BookEditActivity.this,BookDisplayActivity.class);
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
