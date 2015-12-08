package edu.chc.appdev.glennmatthys.exam;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Glenn on 8/12/2015.
 */
public class MeetingCursorAdapter extends CursorAdapter
{
    public MeetingCursorAdapter(Context context, Cursor c, int flags)
    {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.li_meeting, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        ((TextView) view.findViewById(R.id.tvName)).
            setText("Name: " + cursor.getString(cursor.getColumnIndex("name")));
        ((TextView) view.findViewById(R.id.tvDescription)).
            setText("Description: " + cursor.getString(cursor.getColumnIndex("description")));
        ((TextView) view.findViewById(R.id.tvLocation)).
            setText("Location: " +cursor.getString(cursor.getColumnIndex("location")));
        ((TextView) view.findViewById(R.id.tvDate)).
            setText("Date: " + cursor.getString(cursor.getColumnIndex("date")));
        ((TextView) view.findViewById(R.id.tvTime)).
            setText("Time: " + cursor.getString(cursor.getColumnIndex("time")));
        ((TextView) view.findViewById(R.id.tvHost)).
            setText("Host: " + cursor.getString(cursor.getColumnIndex("email")));
    }
}
