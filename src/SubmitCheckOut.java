
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class SubmitCheckOut extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static String isbn;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String bID = request.getParameter("bID");

		BorrowerSetter objBorrower = new BorrowerSetter(bID, isbn);
		int temp = 0;

		temp = DbUpdate.isBorrrowerExists(objBorrower);

		if(temp == 0) {
			out.println("<script>window.alert(\"New Borrower. Please fill above form to proceed\")</script>");
			request.getRequestDispatcher("AddBorrower.jsp").include(request, response);
			//out.println("<h2>New Borrower. Please fill above form to proceed !!</h2>");
		}
		else {
			temp = DbUpdate.checkBorrowerBooks(objBorrower);
			if ( temp >= 3) {
				out.println("<script>window.alert(\"Checkout Failed. Borrower already have 3 books issued\")</script>");
				request.getRequestDispatcher("ViewBook.jsp").include(request, response);
				//out.println("<h2>Checkout Failed. Borrower already have 3 books issued !!</h2>");
			}
			else {
				temp = DbUpdate.addBorrowerBooks(objBorrower);
				if(temp != 0) {
					out.println("<script>window.alert(\"Checkout Successful\")</script>");
					request.getRequestDispatcher("home.jsp").include(request, response);
					//out.println("<h2>Checkout Successful !!</h2>");
				}
				else {
					out.println("<script>window.alert(\"Checkout Failed\")</script>");
					request.getRequestDispatcher("ViewBook.jsp").include(request, response);
					//out.println("<h2>Checkout Failed !!</h2>");
				}
			}
		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
