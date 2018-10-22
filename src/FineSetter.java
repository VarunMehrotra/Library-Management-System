
public class FineSetter {
	private String isbn;
	private String cardID;
	private String dateIn;
	private String dateDue;
	private String bfname;
	private String blname;
	private String fineAmount;
	
	FineSetter()
	{
		super();
	}
	FineSetter(String isbn, String cardID){
		this.isbn = isbn;
		this.cardID = cardID;
	}
	
	FineSetter(String cardID){
		this.cardID = cardID;
	}
	
	public String getfineAmount() {
		return fineAmount;
	}
	public void setfineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}
	
	public String getISBN() {
		return isbn;
	}
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getcardID() {
		return cardID;
	}
	public void setcardID(String cardID) {
		this.cardID = cardID;
	}
	
	public String getBFname() {
		return bfname;
	}
	public void setBFname(String bfname) {
		this.bfname = bfname;
	}
	
	public String getBLname() {
		return blname;
	}
	public void setBLname(String blname) {
		this.blname = blname;
	}
}
