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
public class HostCursorAdapter extends CursorAdapter
{
    public HostCursorAdapter(Context context, Cursor c, int flags)
    {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.li_host, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        ((TextView) view.findViewById(R.id.tvEmail)).
            setText(cursor.getString(cursor.getColumnIndex("email")));
    }
}
