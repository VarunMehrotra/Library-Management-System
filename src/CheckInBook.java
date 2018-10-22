

import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class CheckInBook extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String isbn = request.getParameter("ISBN");
		String cardId = request.getParameter("CardID");

		FineSetter objFine = new FineSetter(isbn, cardId);
		
		DbUpdate.returnBook(objFine);
		out.println("<script>window.alert(\"Check-in Successful\")</script>");
		
		
		double temp = DbUpdate.getFine(objFine);
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		temp = Double.parseDouble(decimalFormat.format(temp));

		if (temp > 0) {
			out.println("<script>window.alert(\"Please pay pending dues\")</script>");
			request.getRequestDispatcher("CheckinBook.jsp").include(request, response);

			out.println("<div class='container'>");	
			out.println("<table class='table table-bordered'>");

			out.println("<br><br><tr><th>Card ID</th><th>ISBN</th><th>Fine Amount</th><th>Pay Fine</th></tr>");
			out.println("<tr><td align=\"center\">"+objFine.getcardID()+"</td><td align=\"center\">"+objFine.getISBN()+"</td><td align=\"center\">"+temp+"</td><td align=\"center\"><a href='Pay?ISBN="+objFine.getISBN()+"&CardID="+objFine.getcardID()+"'>Pay</a></td></tr>");
			out.println("</table>");

			out.println("</div>");
		}
		else {
			request.getRequestDispatcher("home.jsp").include(request, response);
		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
