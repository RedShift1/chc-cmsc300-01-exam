package edu.chc.appdev.glennmatthys.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ViewHostsActivity extends AppCompatActivity
{
    private DB db;
    private HostCursorAdapter hostAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hosts);

        this.db = DB.getInstance(this);

        this.hostAdapter = new HostCursorAdapter(this, this.db.getHosts(), 0);

        ListView lvMeetings = (ListView) this.findViewById(R.id.lvHosts);
        lvMeetings.setAdapter(this.hostAdapter);

    }
}
