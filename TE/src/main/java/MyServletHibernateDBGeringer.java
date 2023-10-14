

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServletHibernateDBGeringer
 */
@WebServlet("/MyServletHibernateDBGeringer")
public class MyServletHibernateDBGeringer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	   static String url = "jdbc:mysql://ec2-18-223-110-33.us-east-2.compute.amazonaws.com:3306/myDB";
	   static String user = "Andrew_Remote";
	   static String password = "csci4830";
	   static Connection connection = null;



	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html;charset=UTF-8");
	      response.getWriter().println("-------- MySQL JDBC Connection Testing ------------<br>");
	      try {
	         Class.forName("com.mysql.cj.jdbc.Driver");//("com.mysql.jdbc.Driver");
	      } catch (ClassNotFoundException e) {
	         System.out.println("Where is your MySQL JDBC Driver?");
	         e.printStackTrace();
	         return;
	      }
	      response.getWriter().println("MySQL JDBC Driver Registered!<br>");
	      connection = null;
	      try {
	         connection = DriverManager.getConnection(url, user, password);
	      } catch (SQLException e) {
	         System.out.println("Connection Failed! Check output console");
	         e.printStackTrace();
	         return;
	      }
	      if (connection != null) {
	         response.getWriter().println("You made it, take control your database now!<br>");
	      } else {
	         System.out.println("Failed to make connection!");
	      }



	  

	      try {
	         String selectSQL = "SELECT * FROM EmployeeGeringer";// WHERE MYUSER LIKE ?";
//		         String theUserName = "user%";
	         response.getWriter().println(selectSQL + "<br>");
	         response.getWriter().println("------------------------------------------<br>");
	         PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
//		         preparedStatement.setString(1, theUserName);
	         ResultSet rs = preparedStatement.executeQuery();
	         while (rs.next()) {
	            String id = rs.getString("id");
	            String age = rs.getString("age");
	            String name = rs.getString("name");
	            String phone = rs.getString("phone");
	           
	            response.getWriter().append("ID: " + id + ", ");
	            response.getWriter().append("Age: " + age + ", ");
	            response.getWriter().append("Name: " + name + ", ");
	            response.getWriter().append("Phone: " + phone + "<br> ");
	           
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }
	   }

	   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      doGet(request, response);
	   }
	}