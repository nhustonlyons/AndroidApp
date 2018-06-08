package com.group4.group4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


 public class DBManager {
     private Connection myConn;
     private Statement myStat;

     public DBManager() {

         Connection myConn = DBConnect();
         try {
             myStat = myConn.createStatement();
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         try {
             myConn.close();
             myStat.close();
         } catch (Exception exc) {
             exc.printStackTrace();
         }
     }

     private Connection DBConnect() {
         try {
             Class.forName("com.mysql.jdbc.Driver");
             myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "AndroidApp", "NinerNow1");
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         return myConn;
     }

     ArrayList<Event> pullAllEvents() {
         Connection myConn = DBConnect();

         try {
             myStat = myConn.createStatement();
         } catch (Exception exc) {
             exc.printStackTrace();
         }

         //Initializing Return Map
         ArrayList<Event> returnThis = new ArrayList<>();
         try {

             //Statement Prep
             String query = "Select * from Event";

             //SQL Access

             ResultSet myRs = myStat.executeQuery(query);

             //Creating Ticket Objects and putting into Database
             while (myRs.next()) {
                 Event temp = new Event();
                 temp.setName("John Doe");
                 temp.setDescription(myRs.getString("Subject"));
                 temp.setOrgID(myRs.getInt("OrgId"));
                // temp.setDate(myRs.getDate("EventDate"));
                 temp.setDuration(myRs.getInt("Duration"));
                 temp.setEventID(myRs.getInt("EventId"));
           //      temp.setStartTime(myRs.getTime("StartTime"));
                 temp.setLat(myRs.getFloat("Latitude"));
                 temp.setLng(myRs.getFloat("Longitude"));
                 temp.setAttendance(myRs.getInt("Attendance"));

                 // Creating Attachment
                 // Attachment tempattach = new Attachment();
                 // tempattach.setName("placeholder");
                 // tempattach.setContents(myRs.getBytes("Attachment"));
                 // temp.addAttachment(tempattach);
                 // int tempInt = myRs.getInt("RefID");

                 returnThis.add(temp);
             }
             //End of Try
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         
         return returnThis;
     }

     Event getEventByID(int eventID) {
         Connection myConn = DBConnect();

         try {
             myStat = myConn.createStatement();
         } catch (Exception exc) {
             exc.printStackTrace();
         }

         //Initializing Return Var
         Event returnThis = new Event();
         try {

             //Statement Prep
             String query = "Select * From Event Where EventID =" + eventID +";";

             //SQL Access

             ResultSet myRs = myStat.executeQuery(query);


             //Creating Ticket Objects and putting into Database
             returnThis.setName(myRs.getString("Name"));
             returnThis.setDescription(myRs.getString("Description"));
             returnThis.setOrgID(myRs.getInt("OrgID"));
             returnThis.setDate(myRs.getDate("EventDate"));
             returnThis.setDuration(myRs.getInt("Duration"));
             returnThis.setEventID(myRs.getInt("EventID"));
             returnThis.setStartTime(myRs.getTime("StartTime").toString());
             returnThis.setLat(myRs.getFloat("Latitude"));
             returnThis.setLng(myRs.getFloat("Longitude"));
             returnThis.setAttendance(myRs.getInt("Attendance"));

             //End of Try
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         return returnThis;
     }

     Organization pullOrgByID(int orgID) {
         Connection myConn = DBConnect();

         try {
             myStat = myConn.createStatement();
         } catch (Exception exc) {
             exc.printStackTrace();
         }

         //Initializing Return Var
         Organization returnThis = new Organization();
         try {

             //Statement Prep
             String query = "Select * from Organization where OrgID=" + orgID + ";";

             //SQL Access

             ResultSet myRs = myStat.executeQuery(query);


             //Creating Ticket Objects and putting into Database
             returnThis.setName(myRs.getString("Name"));
             returnThis.setDescription(myRs.getString("Description"));
             returnThis.setOrgID(myRs.getInt("OrgID"));

             //End of Try
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         return returnThis;
     }

     User pullUserById(int UserID) {
         Connection myConn = DBConnect();

         try {
             myStat = myConn.createStatement();
         } catch (Exception exc) {
             exc.printStackTrace();
         }

         //Initializing Return Var
         User returnThis = new User();
         try {

             //Statement Prep
             String query = "Select * from User where UserID=" + UserID;

             //SQL Access

             ResultSet myRs = myStat.executeQuery(query);

             //Creating Ticket Objects and putting into Database
             returnThis.setName(myRs.getString("Name"));
             returnThis.setHandle(myRs.getString("Handle"));
             returnThis.setUserID(myRs.getInt("UserID"));
             returnThis.setTotalPoints(myRs.getInt("TotalPoints"));
             returnThis.setMajor(myRs.getString("Major"));
             returnThis.setYear(myRs.getInt("Year"));
             //End of Try
         } catch (Exception exc) {
             exc.printStackTrace();
         }
         return returnThis;
     }

     public void createUser(User user) {
         Connection connection = DBConnect();
         PreparedStatement ps = null;
         String query
                 = "INSERT INTO User (UserID, Name, Handle, Password, Major, TotalPoints, Year, EventList) " //Date closed was removed temporarily
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, user.getUserID());
             ps.setString(2, user.getName());
             ps.setString(3, user.getHandle());
             ps.setString(4, user.getPassword());
             ps.setString(5, user.getMajor());
             ps.setInt(6, user.getTotalPoints());
             ps.setInt(7, user.getYear());
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {

             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
     }

     void insertEvent(Event event) {
         Connection connection = DBConnect();
         PreparedStatement ps = null;
         String query
                 = "INSERT INTO Events (EventID, OrgID, Name, Description, Attendance, Latitude, Longitude, Date, StartTime, Duration) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, event.getEventID());
             ps.setInt(2, event.getOrgID());
             ps.setString(3, event.getName());
             ps.setString(4, event.getDescription());
             ps.setInt(5, event.getAttendance());
             ps.setFloat(6, event.getLat());
             ps.setFloat(7, event.getLng());
             ps.setDate(8, event.getDate());
             ps.setTime(9, event.getStartTime());
             ps.setInt(10, event.getDuration());
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
         
         connection = DBConnect();
         PreparedStatement ps2 = null;
         query
                 = "INSERT INTO OrgEvents (OrgID, EventID)"
                 + "VALUES (?, ?)";
         try{
             ps2 = connection.prepareStatement(query);
             ps2.setInt(1, event.getOrgID());
             ps2.setInt(2, event.getEventID());
             ps2.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps2.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
     }

     void insertOrg(Organization org) {
         Connection connection = DBConnect();
         PreparedStatement ps = null;
         String query
                 = "INSERT INTO Organizations (OrgID, Name, Description) "
                 + "VALUES (?, ?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, org.getOrgID());
             ps.setString(2, org.getName());
             ps.setString(3, org.getDescription());
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
     }
 
     void increaseAttendance(int eventID, int studentID){
    	 Connection connection = DBConnect();
         PreparedStatement ps = null;
         String query
                 = "INSERT INTO EventUsers (EventID, UserID) "
                 + "VALUES (?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, eventID);
             ps.setInt(2, studentID);
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
             connection = DBConnect();
             PreparedStatement ps2 = null;
             query
                     = "Update Events Set attendance = attendance +1 Where EventID = (EventID)"
                     + "VALUES (?)";
             try{
                 ps2 = connection.prepareStatement(query);
                 ps2.setInt(1, eventID);
                 ps2.executeUpdate();
             } catch (SQLException e) {
                 System.out.println(e);
             } finally {
                 try {
                     myConn.close();
                     ps2.close();
                 } catch (Exception exc) {
                     System.out.println(exc);
                 }
             }
             
             
         
     }
     
     void decreaseAttendance(int eventID, int studentID){
    	 Connection connection = DBConnect(  );
         PreparedStatement ps = null;
         String query
                 = "DELETE FROM EventUsers WHERE EventID = (EventID) AND UserID = (StudentID)"
                 + "VALUES (?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, eventID);
             ps.setInt(2, studentID);
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
         connection = DBConnect();
         PreparedStatement ps2 = null;
         query
                 = "Update Events Set attendance = attendance -1 Where EventID = (EventID)"
                 + "VALUES (?)";
         try{
             ps2 = connection.prepareStatement(query);
             ps2.setInt(1, eventID);
             ps2.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps2.close();
             } catch (Exception exc) {
                 System.out.println(exc);
             }
         }
     }
 }


  /*   void insertShopItem(Event event) {
         Connection connection = DBConnect();
         PreparedStatement ps = null;
         String query
                 = "INSERT INTO User (EventID, OrgID, Name, Description, Attendance, Latitude, Longitude, Date, StartTime, Duration) "
                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         try {
             ps = connection.prepareStatement(query);
             ps.setInt(1, event.getEventID());
             ps.setInt(2, event.getOrgID());
             ps.setString(3, event.getName());
             ps.setString(4, event.getDescription());
             ps.setInt(5, event.getAttendance());
             ps.setFloat(6, event.getLat());
             ps.setFloat(7, event.getLng());
             ps.setDate(8, event.getDate());
             ps.setTime(9, event.getStartTime());
             ps.setInt(10, event.getDuration());
             ps.executeUpdate();
         } catch (SQLException e) {
             System.out.println(e);
         } finally {
             try {
                 myConn.close();
                 ps.close();
             } catch (Exception exc) {
                 exc.printStackTrace();
             }
         }
     }
 }*/ 
  /*  public class DBManager {
        private Connection myConn;
        private Statement myStat;
        private int nextRef;

        public DBManager() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ticket", "ITAdmin", "ticketDB");
                nextRef = 0;
                myStat = myConn.createStatement();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

        public Map<Integer, Ticket> pullAllTickets() {
            //Initializing Return Map
            Map<Integer, Ticket> returnThis = new LinkedHashMap<>();
            int max = 0;
            try {

                //Statement Prep
                String query = "Select * from tickettable";

                //SQL Access

                ResultSet myRs = myStat.executeQuery(query);

                //Creating Ticket Objects and putting into Database
                while (myRs.next()) {
                    Ticket temp = new Ticket();
                    temp.setCustomerName("John Doe");
                    temp.setSubject(myRs.getString("Subject1"));
                    temp.setBody(myRs.getString("Body"));

                    //Creating Attachment
                    Attachment tempattach = new Attachment();
                    tempattach.setName("placeholder");
                    tempattach.setContents(myRs.getBytes("Attachment"));
                    temp.addAttachment(tempattach);
                    int tempInt = myRs.getInt("RefID");

                    if (tempInt > nextRef) {
                        nextRef = tempInt;
                    }
                    returnThis.put(tempInt, temp);
                }
                //End of Try
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return returnThis;
        }

        public void insert(Ticket ticket) {
            Connection connection = myConn;
            PreparedStatement ps = null;
            Random rando = new Random();
            String query
                    = "INSERT INTO tickettable (RefID, UserID, Subject1, Body, Date_Opened) " //Date closed was removed temporarily
                    + "VALUES (?, ?, ?, ?, ?)";
            try {
                ps = connection.prepareStatement(query);
                ps.setString(4, ticket.getBody());
                ps.setInt(1, ticket.getRefID());
                java.sql.Date sqlDate = null;
                if (ticket.getDateCreated() != null) {
                    sqlDate = new java.sql.Date(ticket.getDateCreated().getTime());
                }
                java.sql.Date sqlDateClosed = null;
                if (ticket.getDateClosed() != null) {
                    sqlDateClosed = new java.sql.Date(ticket.getDateClosed().getTime());
                }
                ps.setDate(5, sqlDate); //Instance vs Date issue
                ps.setString(3, ticket.getSubject());
                ps.setInt(2, 2);
                //ps.setDate(6, sqlDateClosed);
                //Again figure out attachment issue
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                DBUtil.closePreparedStatement(ps);
            }
        }


        public void createUser(int UserID, String Username, String pass, String First, String Last) {
            PreparedStatement ps = null;

            String query
                    = "INSERT IGNORE INTO Users(UserID, Password, First, Last, Date_Created, Last_Session) "
                    + "VALUES(?, ?, ?, ?, ?, ?)";

            try {
                ps = myConn.prepareStatement(query);
                ps.setInt(1, UserID);
                ps.setString(2, Username);
                ps.setString(3, pass);
                ps.setString(4, First);
                ps.setString(5, Last);
                java.sql.Date sqlDate = new Java.sql.Date(utilDate.getTime());
                ps.setDate(6, sqlDate);
                ps.setDate(7, sqlDate);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                DBUtil.closePreparedStatement(ps);
            }
        }
    }
*/
  /*java.sql.Date sqlDate = null;
            if (ticket.getDateCreated() != null) {
                    sqlDate = new java.sql.Date(ticket.getDateCreated().getTime());
                    }
                    java.sql.Date sqlDateClosed = null;
                    if (ticket.getDateClosed() != null) {
                    sqlDateClosed = new java.sql.Date(ticket.getDateClosed().getTime());
                    }*/