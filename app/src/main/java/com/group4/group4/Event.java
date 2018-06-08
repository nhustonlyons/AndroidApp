package com.group4.group4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nicholas Huston-Lyon on 11/2/2017.
 */

class Event implements Comparable<Event> {

    private String name;
    private String description;
    private int attendance;
    private float lat;
    private float lng;
    private String date;


    Event(){
        name="Empty Event";
        description = "Event Empty";
        attendance = 0;
        lat = 0f;
        lng = 0f;
        //date = Calendar.getInstance();
    }

    Event(String inName, String inDescription, int inAttendance, float inLat, float inLong, String inDate) throws ParseException {
        name = inName;
        description = inDescription;
        attendance = inAttendance;
        lat = inLat;
        lng = inLong;
        date = inDate;
        /*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2017);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();*/
    }

    public static ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();
        return events;
    }

    public String getName() { return name; }
    public String getDescription() {
        return description;
    }
    public int getAttendance() {
        return attendance;
    }
    public float getLat() {
        return lat;
    }
    public float getLng() {
        return lng;
    }
    public String getDate(){
        return date;
    }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setLng(float lng) {
        this.lng = lng;
    }
    public void setDate(String inDate) throws ParseException {
        /*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        this.date = cal;
        */
        this.date = inDate;
    }

    @Override
    public int compareTo(Event event2) {
        if (getDate() == null || event2.getDate() == null)
            return 0;
        return getDate().compareTo(event2.getDate());
    }

}
