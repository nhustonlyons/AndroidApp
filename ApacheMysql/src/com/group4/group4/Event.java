package com.group4.group4;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nicholas Huston-Lyon on 11/2/2017.
 */

class Event implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    private String description;
    private String location;
    private int orgID;
    private int attendance;
    private float lat;
    private float lng;
    private java.sql.Date date;
    private Time startTime;
    private int duration; // in hours
    private int eventID;
    Event(){
        name="Empty Event";
        description = "Event Empty";
        attendance = 0;
        lat = 0;
        lng = 0;
        java.util.Date temp = new java.util.Date();
        date = new java.sql.Date(temp.getTime());
        startTime = new Time(date.getTime());
        duration = 0;
        eventID = 0;
    }

    Event(String inName, String inDescription, int inOrgID, int inAttendance, float inLat, float inLong, Date inDate, Time inStartTime, int inDuration, int inEventID){
        name = inName;
        description = inDescription;
        orgID = inOrgID;
        attendance = inAttendance;
        lat = inLat;
        lng = inLong;
        date = inDate;
        startTime = inStartTime;
        duration = inDuration;
        eventID = inEventID;
    }

    public static ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();

        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        Event event4 = new Event();
        Event event5 = new Event();

        event1.setName("Sprint 3 Demo");
        event1.setLocation("WOOD 125");
        event1.setDescription("CCI students present their senior capstone project poster and application demo.");
        event1.setLat(35.307135f);
        event1.setLng(-80.735668f);
        //event1.setDate("Tuesday, December 5, 2017");
        //event1.setStartTime("9:30 AM");
        event1.setEventID(1);

        event2.setName("Social Media and Linkedin");
        event2.setLocation("CC Education 103");
        event2.setDescription("Shay Prosser, Director of Member Engagement and Marketing of The Employer's Association, will bring a timely message on how human resource professionals use social media and how you should and should not use it.");
        event2.setLat(35.307564f);
        event2.setLng(-80.734091f);
        //event2.setDate("Tuesday, December 5, 2017");
        //event2.setStartTime("11:00 AM");
        event2.setEventID(2);

        event3.setName("Fall 2017 Late Night Breakfast");
        event3.setLocation("Popp Martin Student Union");
        event3.setDescription("It's been a long semester, but you made it to finals week!\n" +
                "\n" +
                "Let the faculty & staff of UNC Charlotte, along with Dining Services, treat you to a FREE late night breakfast in the Student Union! Join us for your breakfast favorites, music, bingo with prizes, and more!");
        event3.setLat(35.308625f);
        event3.setLng(-80.733737f);
        //event3.setDate("Tuesday, December 5, 2017");
        //event3.setStartTime("9:30 PM");
        event3.setEventID(3);

        event4.setName("Application Information System");
        event4.setLocation("Atkins 126");
        event4.setDescription("Information session to learn the basics about the application and admission process to the Graduate School at UNC Charlotte.  General information about the more than 150 doctoral, master's and certificate programs will be available. \n" +
                "\n" +
                "Special emphasis will be on fast tracking your career with a graduate certificate. A brief overview of funding, fellowships, financial aid, and assistantships will be presented.\n" +
                "\n" +
                "This session is for people who are interested in applying for admission. Make plans now to attend this valuable event and begin the process to pursue your educational dreams.");
        event4.setLat(35.305760f);
        event4.setLng(-80.732197f);
        //event4.setDate("Tuesday, December 5, 2017");
        //event4.setStartTime("5:00 PM");
        event4.setEventID(4);

        event5.setName("Workshop: There's an App for That!");
        event5.setLocation("Colvard 2006");
        event5.setDescription("Explore how technology and specific apps can facilitate your learning and academic success in college.\n" +
                "\n" +
                "Registering for workshops in advance is strongly suggested. Registration only guarantees your seat through the workshop start time; late arrivals may not be admitted.");
        event5.setLat(35.304854f);
        event5.setLng(-80.731787f);
        //event5.setDate("Tuesday, December 5, 2017");
        //event5.setStartTime("3:30 PM");
        event5.setEventID(5);

        events.add(event1);
        events.add(event2);
        events.add(event3);
        events.add(event4);
        events.add(event5);

        return events;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getDuration() {
        return duration;
    }

    public int getOrgID() {
        return orgID;
    }

    public String getDescription() {
        return description;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public void setDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        formatter.format(date);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public void setStartTime(String startTime) {
        DateFormat df = new SimpleDateFormat("h:mm a");
        df.format(startTime);
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getEventID() {
        return eventID;
    }
}
