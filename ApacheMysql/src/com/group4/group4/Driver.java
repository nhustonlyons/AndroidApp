package com.group4.group4;

public class Driver {
	public static void main (String[] args){
		Event test = new Event();
		DBManager pleasework = new DBManager();
		pleasework.insertEvent(test);
	}
}
