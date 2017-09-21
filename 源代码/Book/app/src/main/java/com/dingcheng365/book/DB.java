package com.dingcheng365.book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/13 0013.
 */

public class DB extends SQLiteOpenHelper {
    // 函数 *********************************************************************
     private SQLiteDatabase db;
    // 构造
    public DB(Context context)
    {
        super(context, "book.db", null,1);
        Log.d("book.db","调用DB构造函数");
    }

    // 创建


    @Override
    public void onCreate(SQLiteDatabase db) {
        String classSQL =
                 "create table classification("
                + "_id INTEGER PRIMARY KEY,"
                + "classname text not null"
                 + ");";
        String bookSQL = "create table book("
                + "_id INTEGER PRIMARY KEY,"
                + "bookName text  not null,"
                + "author text ,"
                + "publish text ,"
                + "ISBN text,"
                + "classname text"
                + ");";
        String userSQL ="create table user("
                + "_id INTEGER PRIMARY KEY, "
                + "username text not null,"
                + "password text not null"
                + ");";
        String noteSQL ="create table note("
                + "userID INTEGER not null, "
                + "bookID INTEGER not null,"
                + "date text not null,"
                + "content text,"
                + "primary key(userID,bookID,date),"
                +"foreign key(userID) references user(_id) on delete cascade on update cascade,"
                +"foreign key(bookID) references book(_id) on delete cascade on update cascade"
                + ")";

        db.execSQL(classSQL);
        db.execSQL(bookSQL);
        db.execSQL(userSQL);
        db.execSQL(noteSQL);
        Log.d("book.db","调用DB.onCreate()");
    }

    public  void  exec(String sql)
    {
        db.execSQL(sql);
    }
    //classification************************************************************************************
    //classification插入方法
    public void insertClassification(ContentValues values) {
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入
        db.insert("classification", null, values);
        db.close();
        System.out.printf("数据库插入操作");
    }

    //classification查询方法
    public Cursor queryClassification() {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //查询获得Cursor
        Cursor cursor = db.query("classification", null, null, null, null, null, null);
        return cursor;
    }
    //classification查询方法
    public Cursor queryClassificationName(String classname) {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        String sql="select * from classification where classname='"+classname+"'";
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    public Cursor queryClassificationID(String classID) {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        String sql="select * from classification where _id='"+classID+"'";
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    //classification删除方法
    public void delClassification(int id)
    {
        System.out.printf("数据库删除方法");
        if(db==null)
        {
            //获得SQLiteDateBase实例
            SQLiteDatabase db=getWritableDatabase();
            //删除
            db.delete("classification","_id=?", new String[]{String.valueOf(id)});
        }
    }

    //book******************************************************************************************
    //book插入方法
    public void insertBook(ContentValues values) {
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入
        db.insert("book", null, values);
        db.close();
        System.out.printf("数据库插入操作");
    }

    //book查询方法
    public Cursor queryBook() {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //查询获得Cursor
        Cursor cursor = db.query("book", null, null, null, null, null, null);
        return cursor;
    }

    //book删除方法
    public void delBook(int id)
    {
        System.out.printf("数据库删除方法");
        if(db==null)
        {
            //获得SQLiteDateBase实例
            SQLiteDatabase db=getWritableDatabase();
            //删除
            db.delete("book","_id=?", new String[]{String.valueOf(id)});
        }
    }
//User**************************************************************************************************
    //User插入方法
    public void insertUser(ContentValues values) {
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入
        db.insert("user", "username", values);
        db.close();
        System.out.printf("数据库插入操作");
    }

    //User查询方法
    public Cursor queryUser() {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //查询获得Cursor
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        return cursor;
    }
    public Cursor queryUserName(String username)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql="select * from user where username='"+username+"'";
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }

    //User删除方法
    public void delUser(int id)
    {
        System.out.printf("数据库删除方法");
        if(db==null)
        {
            //获得SQLiteDateBase实例
            SQLiteDatabase db=getWritableDatabase();
            //删除
            db.delete("user","_id=?", new String[]{String.valueOf(id)});
        }
    }
    //Note**************************************************************************************************
    //Note插入方法
    public void insertNote(ContentValues values) {
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入
        db.insert("note", null, values);
        db.close();
        System.out.printf("数据库插入操作");
    }

    //Note查询方法
    public Cursor queryNote(String selection,String[]  selectionArgs) {
        System.out.printf("数据库查询方法");
        //获得SQLiteDataBase实例
        SQLiteDatabase db = getWritableDatabase();
        //查询获得Cursor
        Cursor cursor = db.query("note", null,selection, selectionArgs, null, null, null);
        return cursor;
    }
    public Cursor queryNoteByUser(String userID)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql="select * from note where userID="+userID;
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    public Cursor queryNoteByUBD(String userID,String bookID,String date)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql="select * from note where userID='"+userID+"' and bookID='"+bookID+"' and date='"+date+"'";
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    //Note删除方法
    public void delNote(int userID,int bookID,String data)
    {
        System.out.printf("数据库删除方法");
        if(db==null)
        {
            //获得SQLiteDateBase实例
            SQLiteDatabase db=getWritableDatabase();
            //删除
            db.delete("note","usrID=?,bookID=?,data=?", new String[]{String.valueOf(userID),String.valueOf(bookID),String.valueOf(data)});
        }
    }
//sum
    public Cursor sumBook(String userID)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql="select bookID ,count(*) as count from note group by bookID having userID="+userID;
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    public Cursor sumUser()
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql="select userID ,count(*) as count from note group by userID";
        Cursor cursor=db.rawQuery(sql,null);
        return  cursor;
    }
    //关闭数据库
    public void close()
    {
        System.out.printf("数据库删除方法");
        if(db!=null)
        {
            db.close();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("StudentMis","调用DB.onUpgrade()");
    }
}
