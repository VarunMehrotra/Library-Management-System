

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class Pay extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String isbn = request.getParameter("ISBN");
		String cardId = request.getParameter("CardID");

		FineSetter objFine = new FineSetter(isbn, cardId);

		int checkReturned = DbUpdate.isBookAlreadyReturned(objFine);

		if (checkReturned != 0) {
			out.println("<script>window.alert(\"Please check-in the book before paying for dues\")</script>");
			request.getRequestDispatcher("CheckinBook.jsp").include(request, response);
		}
		else {
			int payFine = DbUpdate.payFine(objFine);

			if (payFine != 0)  {
				out.println("<script>window.alert(\"Dues Paid\")</script>");
				request.getRequestDispatcher("CheckinBook.jsp").include(request, response);
				//out.println("<h2>Dues Paid !!</h2>");
			}
			else {
				out.println("<script>window.alert(\"Payment Failed\")</script>");
				request.getRequestDispatcher("CheckinBook.jsp").include(request, response);
				//out.println("<h2>Payment Failed !!</h2>");
			}
		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
