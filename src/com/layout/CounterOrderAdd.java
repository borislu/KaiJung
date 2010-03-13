package com.layout;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CounterOrderAdd
 */
public class CounterOrderAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounterOrderAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int screenType = Integer.parseInt( request.getParameter( "screenType" ) );

		//System.out.println( "screenType: "+ screenType );
		switch( screenType ){
		        case 1:
		                RequestDispatcher d1 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd1.html");
		                d1.forward(request, response);
		                break;
		        case 2:
		                RequestDispatcher d2 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd2.html");
		                d2.forward(request, response);
		                break;
		        case 3:
		                RequestDispatcher d3 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd3.html");
		                d3.forward(request, response);
		                break;
		        case 4:
		                RequestDispatcher d4 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd4.html");
		                d4.forward(request, response);
		                break;
		        case 5:
		                RequestDispatcher d5 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd5.html");
		                d5.forward(request, response);
		                break;
		        case 6:
		                RequestDispatcher d6 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd6.html");
		                d6.forward(request, response);
		                break;
		        default:
		                RequestDispatcher d7 = getServletContext().getRequestDispatcher("/counterOrder/counterOrderAdd1.html");
		                d7.forward(request, response);
		                break;
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet( request, response );
	}

}
