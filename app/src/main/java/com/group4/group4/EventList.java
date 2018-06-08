package com.group4.group4;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;

/**
 * Created by realm on 12/10/2017.
 */

public class EventList extends ArrayAdapter<Event> {
    private Activity context;
    private List<Event> eventList;

    public EventList(Activity context, List<Event> eventList){
        super(context, R.layout.list_layout, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewEventName);
        TextView textDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);
        TextView textDate = (TextView) listViewItem.findViewById(R.id.textDate);

        Event event = eventList.get(position);

        textViewName.setText(event.getName());
        textDescription.setText(event.getDescription());
        textDate.setText(event.getDate());

        return listViewItem;
    }
}
