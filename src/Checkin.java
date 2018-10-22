
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.List;


public class Checkin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String checkin = request.getParameter("checkin");
		BookSetter objBook = new BookSetter(checkin);
		
		request.getRequestDispatcher("CheckinBook.jsp").include(request, response);
		
		out.println("<div class='container'>");		
		List<BorrowerSetter> list = DbUpdate.searchCheckin(objBook);
		
		if (list.isEmpty()) {
			out.println("<script>window.alert(\"Book is not checked-out !!\")</script>");
		}
		else {
			out.println("<table class='table table-bordered'>");
			out.println("<br><br><tr><th>Card ID</th><th>First Name</th><th>Last Name</th><th>ISBN</th><th>Date Out</th><th>Due Date</th><th>Check-in</th></tr>");
				
			for(BorrowerSetter borrowerSetter:list){
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><a href=\"#textboxid\" style=\"margin:auto; text-align:center; display:block;\" onClick=\"myPopUp()\">Click</a></td></tr>");
				out.println("<tr><td align=\"center\">"+borrowerSetter.getCardID()+"</td><td align=\"center\">"+borrowerSetter.getBFname()+"</td><td align=\"center\">"+borrowerSetter.getBLname()+"</td><td align=\"center\">"+borrowerSetter.getISBN()+"</td><td align=\"center\">"+borrowerSetter.getDate_Out()+"</td><<td align=\"center\">"+borrowerSetter.getDue_Date()+"</td><td align=\"center\"><a href='CheckInBook?ISBN="+borrowerSetter.getISBN()+"&CardID="+borrowerSetter.getCardID()+"'>Click</a></td></tr>");
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td></tr>");
			}
			out.println("</table>");
			
			out.println("</div>");
		}
		
	out.close();
	}
}
