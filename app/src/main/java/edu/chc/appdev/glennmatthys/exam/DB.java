package edu.chc.appdev.glennmatthys.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Glenn on 8/12/2015.
 */
public class DB extends SQLiteOpenHelper
{
    private static DB instance = null;

    private static final int DATA_VERSION = 3;


    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory)
    {
        super(context, "exam.sqlite", factory, DB.DATA_VERSION);
    }

    public static DB getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DB(context.getApplicationContext(), null, null);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlHost = "CREATE TABLE host\n" +
            "(" +
            "    _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    email TEXT NOT NULL" +
            ");";

        String sqlMeeting = "CREATE TABLE meeting\n" +
            "(" +
            "    _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "    name TEXT NOT NULL," +
            "    description TEXT," +
            "    location TEXT," +
            "    date TEXT," +
            "    time TEXT," +
            "    _hostid INTEGER NOT NULL REFERENCES host(_id)" +
            ");";

        db.execSQL(sqlHost);
        db.execSQL(sqlMeeting);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS host");
        db.execSQL("DROP TABLE IF EXISTS meeting");
        this.onCreate(db);
    }

    public Cursor getMeetings()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM meeting ";
        sql += "INNER JOIN host ON meeting._hostid = host._id";

        return db.rawQuery(sql, null);
    }

    public long addHost(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        long id = db.insertOrThrow("host", null, values);

        db.close();

        return id;
    }

    public long addMeeting(String name, String description, String location, String date, String time, long hostId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);
        values.put("location", location);
        values.put("date", date);
        values.put("time", time);
        values.put("_hostid", hostId);

        long id = db.insertOrThrow("meeting", null, values);

        db.close();

        return id;
    }

    public Cursor getHosts()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM host";

        return db.rawQuery(sql, null);
    }

    public boolean doesHostExist(long hostId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM host WHERE _id = " + hostId, null);

        if (c.getCount() == 1)
        {
            c.close();
            return true;
        }
        else if (c.getCount() == 0)
        {
            c.close();
            return false;
        }
        else
        {
            c.close();
            return false;
        }
    }
}
