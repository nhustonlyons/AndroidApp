package com.group4.group4;

import java.io.Serializable;
/**
 * Created by Nicholas Huston-Lyon on 11/2/2017.
 */

class Organization implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private String name;
    private String description;
    private int orgID;
    private String orgEvents;

    Organization(){
        name = "No Name";
        description = "No Description";
        orgID = 0;
        orgEvents = "None";
    }

    Organization(String inName, String inDescription, int inOrgID, String inOrgEvents){
        name = inName;
        description = inDescription;
        orgID = inOrgID;
        orgEvents = inOrgEvents;
    }

    public void setOrgID(int orgID) {
        this.orgID = orgID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrgEvents(String orgEvents) {
        this.orgEvents = orgEvents;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getOrgID() {
        return orgID;
    }

    public String getOrgEvents() {
        return orgEvents;
    }
}

