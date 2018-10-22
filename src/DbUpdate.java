

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class DbUpdate {

	public static int enterBook(BookSetter objBook) {
		int status1 = 0, status2 = 0, status3 = 0, status4 = 0, status5 = 0, status6 = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement;
			ResultSet resultSet;
			
			statement = connection.prepareStatement("select count(*) AS total from book where isbn = (?) and title = (?)");
			statement.setString(1, objBook.getIsbn());
			statement.setString(2, objBook.getbookName());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				status1 = resultSet.getInt("total");
			}
			
			if (status1 == 0){
				statement = connection.prepareStatement("insert into BOOK(Isbn, Title) values(?,?)");
				statement.setString(1, objBook.getIsbn());
				statement.setString(2, objBook.getbookName());
				status2 = statement.executeUpdate();
			}
			
			statement = connection.prepareStatement("select count(*) AS total from AUTHORS where Name = (?)");
			statement.setString(1, objBook.getauthorName());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				status3 = resultSet.getInt("total");
			}
			
			if (status3 == 0){
				statement = connection.prepareStatement("insert into AUTHORS(Name) values(?)");
				statement.setString(1, objBook.getauthorName());
				status4 = statement.executeUpdate();
			}
			
			
			statement = connection.prepareStatement("select count(*) AS total from BOOK_AUTHORS where Isbn = (?) and Author_id = (select Author_id from AUTHORS where Name = (?))");
			statement.setString(1, objBook.getIsbn());
			statement.setString(2, objBook.getauthorName());
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				status5 = resultSet.getInt("total");
			}
			
			
			if (status5 == 0) {
				statement = connection.prepareStatement("insert into BOOK_AUTHORS(Author_id, Isbn) select Author_id, Isbn from AUTHORS, BOOK where Title = (?) and Name = (?)");
				statement.setString(1, objBook.getbookName());
				statement.setString(2, objBook.getauthorName());
				status6 = statement.executeUpdate();
			}
			
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (status1 != 0 && status3 != 0 && status5 != 0)
			return 0; // Duplicate Entry
		else
			return 1;
	}

	public static List<BookSearch> searchBook(BookSetter objBook) {
		List<BookSearch> list = new ArrayList<BookSearch>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			//PreparedStatement statement = connection.prepareStatement("select BOOK.Isbn, BOOK.Title, GROUP_CONCAT(AUTHORS.Name) AS Name from (BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id where BOOK.Isbn like (?) or BOOK.Title like (?) or AUTHORS.Name like (?) group by BOOK.Isbn");
			//PreparedStatement statement = connection.prepareStatement("select BOOK.Isbn, BOOK.Title, GROUP_CONCAT(AUTHORS.Name) AS Name from ((BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id) where book.isbn in (select distinct book.isbn from (BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id where BOOK.Isbn like (?) or BOOK.Title like (?) or AUTHORS.Name like (?)) group by BOOK.Isbn");
			
			PreparedStatement statement = connection.prepareStatement("SELECT TempView.*, if(Status IS NULL, 'Available', status) status from (select B.*, GROUP_CONCAT(DISTINCT A.name SEPARATOR ', ') as author_name from Book B inner join Book_authors BA on B.isbn = BA.Isbn inner join Authors A on BA.author_id = A.Author_id WHERE ((B.isbn like (?) OR B.title like (?) OR A.name like (?))) GROUP BY B.isbn) TempView LEFT OUTER JOIN (SELECT isbn, if((count(*)<1),'Available','Not Available') AS status FROM Book_loans WHERE (DATE_IN IS NULL)Group By isbn) Temp_Book_loans ON TempView.isbn = Temp_Book_loans.isbn");
			String str = '%' + objBook.getsearchField() + '%';
			statement.setString(1, str);
			statement.setString(2, str);
			statement.setString(3, str);

			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BookSearch bookSearch = new BookSearch();
				
				bookSearch.setIsbn(resultSet.getString("Isbn"));
				bookSearch.setTitle(resultSet.getString("Title"));
				bookSearch.setName(resultSet.getString("author_name"));
				bookSearch.setStatus(resultSet.getString("status"));
				
				list.add(bookSearch);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<BookSearch> searchAvailableBook(BookSetter objBook) {
		List<BookSearch> list = new ArrayList<BookSearch>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			//PreparedStatement statement = connection.prepareStatement("select BOOK.Isbn, BOOK.Title, GROUP_CONCAT(AUTHORS.Name) AS Name from (BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id where BOOK.Isbn like (?) or BOOK.Title like (?) or AUTHORS.Name like (?) group by BOOK.Isbn");
			//PreparedStatement statement = connection.prepareStatement("select BOOK.Isbn, BOOK.Title, GROUP_CONCAT(AUTHORS.Name) AS Name from ((BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id) where book.isbn in (select distinct book.isbn from ((BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id) JOIN BOOK_LOANS ON BOOK.Isbn = BOOK_LOANS.ISBN where Date_in is not null and (BOOK.Isbn like (?) or BOOK.Title like (?) or AUTHORS.Name like (?))) group by BOOK.Isbn");
			PreparedStatement statement = connection.prepareStatement("SELECT TempView.*, if(Status IS NULL, 'Available', status) status from (select B.*, GROUP_CONCAT(DISTINCT A.name SEPARATOR ', ') as author_name from Book B inner join Book_authors BA on B.isbn = BA.Isbn inner join Authors A on BA.author_id = A.Author_id WHERE ((B.isbn like (?) OR B.title like (?) OR A.name like (?))) GROUP BY B.isbn) TempView LEFT OUTER JOIN (SELECT isbn, if((count(*)<1),'Available','Not Available') AS status FROM Book_loans WHERE (DATE_IN IS NULL)Group By isbn) Temp_Book_loans ON TempView.isbn = Temp_Book_loans.isbn");

			String str = '%' + objBook.getsearchField() + '%';
			statement.setString(1, str);
			statement.setString(2, str);
			statement.setString(3, str);

			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BookSearch bookSearch = new BookSearch();
				
				bookSearch.setIsbn(resultSet.getString("Isbn"));
				bookSearch.setTitle(resultSet.getString("Title"));
				bookSearch.setName(resultSet.getString("author_name"));
				bookSearch.setStatus(resultSet.getString("status"));

				list.add(bookSearch);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<BookSearch> fetchBook(BookLoanSetter bLSetter) {
		List<BookSearch> list = new ArrayList<BookSearch>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			PreparedStatement statement = connection.prepareStatement("select BOOK.Isbn, BOOK.Title, GROUP_CONCAT(AUTHORS.Name) As Name from (BOOK Join BOOK_AUTHORS ON BOOK.Isbn = BOOK_AUTHORS.Isbn) Join AUTHORS ON AUTHORS.Author_id = BOOK_AUTHORS.Author_id where BOOK.Isbn = (?) Group by BOOK.Isbn");
			statement.setString(1, bLSetter.getIsbn());

			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BookSearch bookSearch = new BookSearch();
				
				bookSearch.setIsbn(resultSet.getString("Isbn"));
				bookSearch.setTitle(resultSet.getString("Title"));
				bookSearch.setName(resultSet.getString("Name"));
				
				list.add(bookSearch);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<BorrowerSetter> searchCheckin(BookSetter bookSetter) {
		List<BorrowerSetter> list = new ArrayList<BorrowerSetter>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			PreparedStatement statement = connection.prepareStatement("select BOOK_LOANS.Card_id, Bfname, Blname, Isbn, Date_out, Due_date from BOOK_LOANS JOIN BORROWER ON BOOK_LOANS.Card_id =  BORROWER.Card_id and Date_in is NULL where Isbn like (?) or BOOK_LOANS.card_id like (?) or Bfname like (?) or Blname like (?)");
			String str = bookSetter.getsearchField();
			statement.setString(1, str);
			statement.setString(2, str);
			statement.setString(3, str);
			statement.setString(4, str);
			
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BorrowerSetter borrowerSetter = new BorrowerSetter();
				borrowerSetter.setCardID(resultSet.getString("Card_id"));
				borrowerSetter.setBFname(resultSet.getString("Bfname"));
				borrowerSetter.setBLname(resultSet.getString("Blname"));
				borrowerSetter.setISBN(resultSet.getString("Isbn"));
				borrowerSetter.setDate_out(resultSet.getString("Date_out"));
				borrowerSetter.setDue_Date(resultSet.getString("Due_Date"));
				
				list.add(borrowerSetter);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int checkSSN(BorrowerSetter objBorrower) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) AS total from borrower where SSN = (?)");
			statement.setString(1, objBorrower.getSSN());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				status = resultSet.getInt("total");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int checkBorrowerBooks(BorrowerSetter objBorrower) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) AS total from BOOK_LOANS where Card_id = (?) and Date_in is NULL");
			statement.setString(1, objBorrower.getCardID());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				status = resultSet.getInt("total");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int isBorrrowerExists(BorrowerSetter objBorrower) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) AS total from BORROWER where Card_id = (?)");
			statement.setString(1, objBorrower.getCardID());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				status = resultSet.getInt("total");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static double getFine(FineSetter objFine) {
		int status = 0;
		double fine = 0.00;
		try {
			int loanID = 0;
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select Loan_id from BOOK_LOANS where Card_id = (?) and Isbn = (?)");
			statement.setString(1, objFine.getcardID());
			statement.setString(2, objFine.getISBN());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				loanID = resultSet.getInt("Loan_id");
			}
			
			statement = connection.prepareStatement("select sum(Fine_amt) as Dues from FINES where Loan_id = (?) and Paid = false");
			statement.setString(1, String.valueOf(loanID));
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				fine = resultSet.getFloat("Dues");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return fine;
	}
	
	public static int payFine(FineSetter objFine) {
		int status = 0;
		try {
			int loanID = 0;
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select Loan_id from BOOK_LOANS where Card_id = (?) and Isbn = (?)");
			statement.setString(1, objFine.getcardID());
			statement.setString(2, objFine.getISBN());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				loanID = resultSet.getInt("Loan_id");
			}
			
			statement = connection.prepareStatement("Update Fines set Paid = true where Loan_id = (?)");
			statement.setString(1, String.valueOf(loanID));
			status = statement.executeUpdate();
			
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int addBorrowerBooks(BorrowerSetter objBorrower) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("insert into BOOK_LOANS(Isbn, Card_id, Date_out, Due_date) values ((?),(?),CURRENT_DATE,DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY))");
			statement.setString(1, objBorrower.getISBN());
			statement.setString(2, objBorrower.getCardID());
			status = statement.executeUpdate();

			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int returnBook(FineSetter objFine) {
		int status = 0;
		try {
			int loanID = 0;
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select Loan_id from BOOK_LOANS where Card_id = (?) and Isbn = (?) and Date_in is NULL");
			statement.setString(1, objFine.getcardID());
			statement.setString(2, objFine.getISBN());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				loanID = resultSet.getInt("Loan_id");
			}
			
			//executeTrigger();
			
			statement = connection.prepareStatement("insert into FINES(Loan_id, Fine_amt) select loan_id, (DATEDIFF(CURRENT_DATE, DUE_Date))*0.25 from BOOK_LOANS where date_in is null and not exists (select * from fines where fines.loan_id = BOOK_LOANS.Loan_id and paid = 0 and BOOK_LOANS.Loan_id = (?)) and due_date < CURRENT_DATE and loan_id = (?)");
			statement.setString(1, String.valueOf(loanID));
			statement.setString(2, String.valueOf(loanID));
			statement.executeUpdate();

			statement = connection.prepareStatement("update FINES set Fine_amt = (select Temp_View.Fine_amt from Temp_View where Temp_View.Loan_id = FINES.Loan_id) where FINES.Loan_id = (?)");			
			statement.setString(1, String.valueOf(loanID));
			statement.executeUpdate();
			
			
			connection = DatabaseConnection.getConnection();
			statement = connection.prepareStatement("Update BOOK_LOANS set Date_in = CURRENT_DATE where Loan_id = (?)");
			statement.setString(1, String.valueOf(loanID));
			status = statement.executeUpdate();

			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int isBookAlreadyReturned(FineSetter objFine) {
		int loanID = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(Loan_id) AS Loan_id from BOOK_LOANS where Card_id = (?) and Isbn = (?) and Date_in is NULL");
			statement.setString(1, objFine.getcardID());
			statement.setString(2, objFine.getISBN());
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				loanID = resultSet.getInt("Loan_id");
			}

			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return loanID;
	}
	
	public static int isBookAvailable(BookLoanSetter bLSetter) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) AS total from BOOK_LOANS where Isbn = (?) and Date_in is NULL");
			statement.setString(1, bLSetter.getIsbn());
			
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				status = resultSet.getInt("total");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static void addBorrower(BorrowerSetter objBorrower) {
		int cardID = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select max(Card_id) AS ID from borrower");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				cardID = resultSet.getInt("ID");
			}
			
			statement = connection.prepareStatement("insert into borrower values(?,?,?,?,?,?,?,?)");
			
			statement.setInt(1, (cardID + 1));
			statement.setString(2, objBorrower.getSSN());
			statement.setString(3, objBorrower.getBFname());
			statement.setString(4, objBorrower.getBLname());
			statement.setString(5, objBorrower.getPhone());
			statement.setString(6, objBorrower.getAddress());
			statement.setString(7, objBorrower.getCity());
			statement.setString(8, objBorrower.getState());
		
			statement.executeUpdate();
			
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static int isBorrowerPresent(BorrowerSetter objBorrower) {
		int status = 0;
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("select count(*) AS total from borrower where SSN = (?)");
			statement.setString(1, objBorrower.getSSN());

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				status = resultSet.getInt("total");
			}
			
			if (status > 0) {
				statement = connection.prepareStatement("select count(*) AS total from borrower where SSN = (?)");
			}
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static List<FineSetter> getFines(FineSetter obj) {
		List<FineSetter> list = new ArrayList<FineSetter>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			PreparedStatement statement;
			
			
			statement = connection.prepareStatement("select BORROWER.Card_id, BORROWER.Bfname, BORROWER.Blname, sum(FINES.Fine_amt) AS Total_Amt from (BORROWER JOIN BOOK_LOANS ON BORROWER.card_id = BOOK_LOANS.card_id) JOIN FINES ON BOOK_LOANS.Loan_id = FINES.Loan_id where Fines.Paid = false group by BORROWER.Card_id, BORROWER.Bfname, BORROWER.Blname order by BORROWER.Card_id");

			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				FineSetter objFine = new FineSetter();
				
				objFine.setcardID(resultSet.getString("Card_id"));
				objFine.setBFname(resultSet.getString("Bfname"));
				objFine.setBLname(resultSet.getString("Blname"));
				objFine.setfineAmount(resultSet.getString("Total_Amt"));
				
				list.add(objFine);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<FineSetter> getAllFines(FineSetter obj) {
		List<FineSetter> list = new ArrayList<FineSetter>();
		try {
			Connection connection = DatabaseConnection.getConnection();
			ResultSet resultSet;
			PreparedStatement statement = connection.prepareStatement("select BORROWER.Card_id, BORROWER.Bfname, BORROWER.Blname, BOOK_LOANS.Isbn, FINES.Fine_amt from (BORROWER JOIN BOOK_LOANS ON BORROWER.Card_id = BOOK_LOANS.Card_id) JOIN FINES ON FINES.Loan_id = BOOK_LOANS.Loan_id where BORROWER.Card_id = (?) and Paid = 0");
			statement.setString(1, obj.getcardID());

			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				FineSetter objFine = new FineSetter();
				
				objFine.setcardID(resultSet.getString("Card_id"));
				objFine.setBFname(resultSet.getString("Bfname"));
				objFine.setBLname(resultSet.getString("Blname"));
				objFine.setISBN(resultSet.getString("Isbn"));
				objFine.setfineAmount(resultSet.getString("Fine_amt"));
				
				list.add(objFine);
			}
			connection.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void executeTrigger() {
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement statement = connection.prepareStatement("insert into FINES(Loan_id, Fine_amt) select loan_id, (DATEDIFF(CURRENT_DATE, DUE_Date))*0.25 from BOOK_LOANS where date_in is null and not exists (select * from fines where fines.loan_id = BOOK_LOANS.Loan_id) and due_date < CURRENT_DATE");
			statement.executeUpdate();

			statement = connection.prepareStatement("update FINES set Fine_amt = (select Temp_View.Fine_amt from Temp_View where Temp_View.Loan_id = FINES.Loan_id)");			
			statement.executeUpdate();
			
//			statement = connection.prepareStatement("update FINES set Paid = 1 where Loan_id in (select Loan_id from BOOK_LOANS where Date_in is not NULL)");
//			statement.executeUpdate();
			
			connection.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
