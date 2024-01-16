package src.model;

import java.time.LocalDate;


public class Bill {

	    private static int billNumber=0;
	    private final String billName;
	    public String supplier;
	    private final LocalDate billDate;
	    private final double price;
	    public int quantitySold;

		public Bill(String librarianUsername, String supplier, LocalDate billDate, double total, int quantity) {
			this.billName = librarianUsername;
			this.billDate = billDate;
			this.price = total;
			this.supplier = supplier;
			quantitySold = quantity;
			++billNumber;
		}
		
		public static int getBillNo() {
			return billNumber;
		}
		
		public String getlibrarianUsername() {
			return billName;
		}
		
		public LocalDate getBillDate() {
			return billDate;
		}
		
		public double getTotal() {
			return price;
		}
		
		@Override
		public String toString() {
			return "Bill \n"+"billName=" + billName + "\n supplier=" + supplier + "\n billDate=" + billDate + "\n price="
					+ price + "\n quantitySold=" + quantitySold+"\n\n";
		}
}
