package com.group4.group4;
import java.lang.StringBuilder;
import java.io.Serializable;

/**
 * Created by Nicholas Huston-Lyon on 10/31/2017.
 */
// REMOVE/ADD EVENTS to personalList
    // PROCESS EVENT LIST
class User implements Serializable{
    private String handle;
    private String name;
    private String major;
    private int year;
    private StringBuilder personalList;

    User(){
        name = "Default";
        handle = "Not Real";
        major = "Not CompSci";
        year = 8;

    }

    User(String inHandle, int inUserID, String inMajor, int inTotalPoints, int inYear, String inName, String inPassword){
        name = inName;
        handle = inHandle;
        major = inMajor;
        year = inYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setPersonalList(String personalList) {
        this.personalList = new StringBuilder(personalList);
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getHandle() {
        return handle;
    }

    public String getMajor() {
        return major;
    }

    public String getPersonalList() {
        return personalList.toString();
    }
}
