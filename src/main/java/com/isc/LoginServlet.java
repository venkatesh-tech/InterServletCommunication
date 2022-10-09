package com.isc;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "Venky1234@");
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("select * from user where email='" + userName + "' and '" + password + "'");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("homeServlet");

			if (resultSet.next()) {
				request.setAttribute("message", "Welcone to InterServletCommunication" + userName);
				requestDispatcher.forward(request, response);
			} else {
				requestDispatcher = request.getRequestDispatcher("login.html");
				requestDispatcher.include(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		doGet(request, response);
	}

}
