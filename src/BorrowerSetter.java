
public class BorrowerSetter {
	private String bfname;
	private String blname;
	private String SSN;
	private String address;
	private String phone;
	private String city;
	private String state;
	private String card_id;
	private String isbn;
	private String Date_out;
	private String Due_Date;
	
	public BorrowerSetter(){
		super();
	}
	
	public BorrowerSetter(String card_id, String isbn){
		this.card_id = card_id;
		this.isbn = isbn;
	}
	
	public BorrowerSetter(String SSN){
		this.SSN = SSN;
	}
	
	public BorrowerSetter(String bfname, String blname, String SSN, String phone, String address, String city, String state) {
		this.bfname = bfname;
		this.blname = blname;
		this.SSN = SSN;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.state = state;
	}
	
	public String getISBN() {
		return isbn;
	}
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public String getDate_Out() {
		return Date_out;
	}
	public void setDate_out(String Date_out) {
		this.Date_out = Date_out;
	}
	
	public String getDue_Date() {
		return Due_Date;
	}
	public void setDue_Date(String Due_Date) {
		this.Due_Date = Due_Date;
	}
	
	public String getBFname() {
		return bfname;
	}
	public void setBFname(String bfname) {
		this.bfname = bfname;
	}
	
	public String getCardID() {
		return card_id;
	}
	public void setCardID(String card_id) {
		this.card_id = card_id;
	}
	
	public String getBLname() {
		return blname;
	}
	public void setBLname(String blname) {
		this.blname = blname;
	}
	
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String SSN) {
		this.SSN = SSN;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
