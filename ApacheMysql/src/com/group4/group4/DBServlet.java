package com.group4.group4;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class DBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
            ObjectInputStream oin = new ObjectInputStream(request.getInputStream());
           // Event in = new Event();
            char methodName[] = new char[10];
            for(int k = 0; k < 10; k++){
            	methodName[k] = oin.readChar();
            }

            oin.close();
            String thisOne = new String(methodName);

            DBParser newStuff = new DBParser();
            newStuff.handleIt(thisOne, oin, response);
/*            response.setStatus(HttpServletResponse.SC_OK);
            //OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());

           ObjectOutputStream oos = new ObjectOutputStream(response.getOutputStream());
           oos.writeObject(in);
           oos.flush();
           oos.close();
*/
        } catch (Exception e) {
            try{
            	e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            	
            }
        }   
        }

	}


