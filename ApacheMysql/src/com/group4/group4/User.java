package com.group4.group4;
import java.lang.StringBuilder;
import java.io.Serializable;

/**
 * Created by Nicholas Huston-Lyon on 10/31/2017.
 */
// REMOVE/ADD EVENTS to personalList
    // PROCESS EVENT LIST
class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private String handle;
    private String name;
    private int userID;
    private String major;
    private int totalPoints;
    private int year;
    private String password;
    private StringBuilder personalList;

    User(){
        name = "Default";
        handle = "Not Real";
        userID = 0;
        major = "Not CompSci";
        totalPoints = 0;
        year = 8;
        password = "apoidsnfapoiwqnfoifndsaoifadsnfoiafnq;oifnf";
    }

    User(String inHandle, int inUserID, String inMajor, int inTotalPoints, int inYear, String inName, String inPassword){
        name = inName;
        handle = inHandle;
        userID = inUserID;
        major = inMajor;
        totalPoints = inTotalPoints;
        year = inYear;
        password = inPassword;
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

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPersonalList(String personalList) {
        this.personalList = new StringBuilder(personalList);
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getUserID() {
        return userID;
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

    public String getPassword(){return password;}

    public String getPersonalList() {
        return personalList.toString();
    }
}
