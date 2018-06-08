package com.group4.group4;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventDetails extends AppCompatActivity implements NowMapFragment.OnFragmentInteractionListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle extras = getIntent().getExtras();

        final DatabaseReference databaseEvents = FirebaseDatabase.getInstance().getReference("events");

        setContentView(R.layout.activity_event_details);

        databaseEvents.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    final Event event = ds.getValue(Event.class);
                    String eventName = extras.getString("eventName");
                    if(eventName.equals(event.getName())) {
                        setTitle(event.getName());
                        TextView eventDescription = (TextView) findViewById(R.id.event_description);
                        TextView eventLocation = (TextView) findViewById(R.id.event_location);
                        TextView eventDate = (TextView) findViewById(R.id.event_date);
                        TextView eventTime = (TextView) findViewById(R.id.event_time);
                        final TextView eventAttendance = (TextView) findViewById(R.id.event_attendance);
                        Button checkIn = (Button) findViewById(R.id.check_in);

                        eventDescription.setText(event.getDescription());
                        eventDate.setText("Date: " + event.getDate());
                        eventTime.setText("Time: 9:30 AM to 12:15 PM");
                        eventAttendance.setText("Current Attendance: " + Integer.toString(event.getAttendance()));
                        checkIn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int counter = event.getAttendance() + 1;
                                databaseEvents.child(event.getName()).child("attendance").setValue(counter);
                                eventAttendance.setText("Current Attendance: " + Integer.toString(event.getAttendance()));
                                Toast toast = Toast.makeText(getApplicationContext(), "Successful check in.", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}