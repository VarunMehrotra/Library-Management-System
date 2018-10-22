

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddBorrower extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String bfname = request.getParameter("bfname");
		String blname = request.getParameter("blname");
		String SSN = request.getParameter("ssn");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");

		phone = "("+ phone.substring(0,3) + ") " + phone.substring(3,6) + "-" + phone.substring(6,10);
		BorrowerSetter objBorrower = new BorrowerSetter(bfname, blname, SSN, phone, address, city, state);

		int temp = DbUpdate.checkSSN(objBorrower);
		if ( temp >= 1) {
			out.println("<script>window.alert(\"Duplicate SSN. Borrower already exists\")</script>");
			request.getRequestDispatcher("AddBorrower.jsp").include(request, response);
		}
		else {
			DbUpdate.addBorrower(objBorrower);
			out.println("<script>window.alert(\"Borrower Added Successfully\")</script>");
			request.getRequestDispatcher("AddBorrower.jsp").include(request, response);
		}

		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
