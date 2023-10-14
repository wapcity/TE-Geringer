import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Info;

import util.UtilDBGeringer;

@WebServlet("/SimpleInsertHB")
public class SimpleInsertHB extends HttpServlet implements Info {
   private static final long serialVersionUID = 1L;

   public SimpleInsertHB() {
      super();
   }

   
   
   
   
   
   
   
   
   
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   
	   
	      Connection connection = null;
	      String Type = request.getParameter("Type").trim();
	      String Ingrediants = request.getParameter("Ingrediants").trim();
	      String Duration = request.getParameter("Duration").trim();
	      String Directions = request.getParameter("Directions").trim();


	      String insertSql = " INSERT INTO RecipeTable (id, type, Ingrediants, Duration, Directions) values (default, ?, ?, ?, ?)";

	      try {
	         DBConnection.getDBConnection();
	         connection = DBConnection.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, Type);
	         preparedStmt.setString(2, Ingrediants);
	         preparedStmt.setString(3, Duration);
	         preparedStmt.setString(3, Directions);
	         preparedStmt.execute();
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
     /* String Type = request.getParameter("Type").trim();
      String Ingrediants = request.getParameter("Ingrediants").trim();
      String Duration = request.getParameter("Duration").trim();
      String Directions = request.getParameter("Directions").trim();
      UtilDBGeringer.Recipe(Type, Ingrediants, Duration, Directions );
*/
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      out.println("<li> Name: " + Type);
      out.println("<li> Age: " + Ingrediants);
      out.println("<li> phone: " + Duration);
      out.println("<li> phone: " + Directions);
      out.println("</ul>");
    
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
