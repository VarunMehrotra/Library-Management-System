

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

public class ShowPaymentDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String cardID = request.getParameter("CardID");

		FineSetter fineSetter = new FineSetter(cardID);

		List<FineSetter> list = DbUpdate.getAllFines(fineSetter);
		request.getRequestDispatcher("Styling.jsp").include(request, response);
		out.println("<h2>Individual Records</h2>");

		out.println("<div class='container'>");
		out.println("<table class='table table-bordered'>");
		out.println("<br><tr><th>Card ID</th><th>First Name</th><th>Last Name</th><th>ISBN</th><th>Fine Amount</th><th>Pay Fine</th></tr>");

		for(FineSetter objFine:list){
			out.println("<tr><td align=\"center\">"+objFine.getcardID()+"</td><td align=\"center\">"+objFine.getBFname()+"</td><td align=\"center\">"+objFine.getBLname()+"</td><td align=\"center\">"+objFine.getISBN()+"</td><td align=\"center\">"+objFine.getfineAmount()+"</td><td align=\"center\"><a href='Pay?ISBN="+objFine.getISBN()+"&CardID="+objFine.getcardID()+"'>Pay</a></td></tr>");
		}
		out.println("</table>");
		out.println("</div>");
		out.println("</form>");

		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
