package com.group4.group4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;



public class DBParser {
	ObjectOutputStream oos;
	public DBParser(){
		
	}
	
	public void testerMethod(Event in, HttpServletResponse response) throws IOException{
		
		in.setAttendance(in.getAttendance()+1);
		ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
        oos.writeObject(in);
        oos.flush();
        oos.close();
	}
	
	public void handleIt(String method, ObjectInputStream oin, HttpServletResponse response) throws IOException, ClassNotFoundException{
		DBManager DAL = new DBManager();
		switch(method){
		case "createUser":
			User newUser = (User) oin.readObject();
			oin.close();
			DAL.createUser(newUser);
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.flush();
	        oos.close();
			break;
				
		case "createEven":
			Event in = (Event) oin.readObject();
			oin.close();
			DAL.insertEvent(in);
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.flush();
	        oos.close();
			break;
			
		case "createOrgi":
			Organization in2 = (Organization) oin.readObject();
			oin.close();
			DAL.insertOrg(in2);
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.flush();
	        oos.close();
			break;
			
		case "getEventby":
			int in3 = oin.readInt();
			Event returnMe = DAL.getEventByID(in3);
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.writeObject(returnMe);
			oos.flush();
	        oos.close();
			break;
			
		case "getUserByI":
			int in4 = oin.readInt();
			oin.close();
			User returnMe1 = DAL.pullUserById(in4);
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.writeObject(returnMe1);
			oos.flush();
	        oos.close();
	        break;
			
		case "pullOrgani":
			int in5 = oin.readInt();
			oin.close();
			Organization returnMe2 = DAL.pullOrgByID(in5);
			ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
	        oos.writeObject(returnMe2);
			oos.flush();
	        oos.close();
	        break;
									
		case "deleteEven":
			
		case "deleteUser":
			
		case "deleteOrgi":
			
		case "updateEven":
			
		case "updateUser":
			
		case "updateOrgi":
			
		case "pullAllEve":
			oin.close();
			ArrayList<Event> returnThis = DAL.pullAllEvents();
			oos = new ObjectOutputStream(response.getOutputStream());
	        oos.writeObject(returnThis);
			oos.flush();
	        oos.close();
	        break;
		case "increaseAt":
			int eventID = oin.readInt();
			int studentID= oin.readInt();
			oin.close();
			DAL.increaseAttendance(eventID, studentID);
			oos = new ObjectOutputStream(response.getOutputStream());
			oos.flush();
	        oos.close();
	        break;
		case "decreaseAt":
			int eventID2 = oin.readInt();
			int studentID2= oin.readInt();
			oin.close();
			DAL.decreaseAttendance(eventID2, studentID2);
			oos = new ObjectOutputStream(response.getOutputStream());
			oos.flush();
	        oos.close();
	        break;
		}	
	}

}
