package edu.chc.appdev.glennmatthys.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
{
    private DB db;
    private MeetingCursorAdapter meetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent addMeeting = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivityForResult(addMeeting, 0);
            }
        });

        this.db = DB.getInstance(this);

        this.meetingAdapter = new MeetingCursorAdapter(this, this.db.getMeetings(), 0);

        ListView lvMeetings = (ListView) this.findViewById(R.id.lvMeetings);
        lvMeetings.setAdapter(this.meetingAdapter);
    }

    public void openAddHost(View view)
    {
        Intent addHost = new Intent(MainActivity.this, AddHostActivity.class);
        startActivityForResult(addHost, 1);
    }

    public void openViewHosts(View view)
    {
        Intent viewHosts = new Intent(MainActivity.this, ViewHostsActivity.class);
        startActivityForResult(viewHosts, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        this.meetingAdapter.swapCursor(this.db.getMeetings());
    }
}
