package model;

import java.sql.*;

public class Payment { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labsheet6", "root", "");
			System.out.println("Sucessfully connected!!!");
		} catch (Exception e) {
			System.out.println("Not connect to the database" );
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String Charge_Of_Appoinments, String Charge_Of_Pharmacy, String Charge_Of_Services, String Total_Amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " INSERT INTO 'payments'(PaymentID', 'ChargeOfAppoinments', 'ChargeOfPharmacy', 'ChargeOfServices', 'TotalAmount') VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setDouble(2, Double.parseDouble(Charge_Of_Appoinments));
			preparedStmt.setDouble(3, Double.parseDouble(Charge_Of_Pharmacy));
			preparedStmt.setDouble(4, Double.parseDouble(Charge_Of_Services));
			preparedStmt.setDouble(5, Double.parseDouble(Total_Amount));
			//preparedStmt.setInt(6,);
			//preparedStmt.setInt(7,);
			//preparedStmt.setInt(8,);
			//preparedStmt.setInt(9,);
			//preparedStmt.setInt(10,);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Charge Of Appoinments</th><th>Charge Of Pharmacy</th><th>Charge Of Services</th><th>Total Amount</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String PaymentID = Integer.toString(rs.getInt("PaymentID"));
				String ChargeOfAppoinments = Double.toString(rs.getDouble("ChargeOfAppoinments"));
				String ChargeOfPharmacy = Double.toString(rs.getDouble("ChargeOfPharmacy"));
				String ChargeOfServices = Double.toString(rs.getDouble("ChargeOfServices"));
				String TotalAmount = Double.toString(rs.getDouble("TotalAmount"));
// Add into the html table
				output += "<tr><td>" + ChargeOfAppoinments + "</td>";
				output += "<td>" + ChargeOfPharmacy + "</td>";
				output += "<td>" + ChargeOfServices + "</td>";
				output += "<td>" + TotalAmount + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"payments.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"PaymentID\" type=\"hidden\" value=\"" + PaymentID + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String ID, String Charge_Of_Appoinments, String Charge_Of_Pharmacy, String Charge_Of_Services, String Total_Amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE payments SET ChargeOfAppoinments=?,ChargeOfPharmacy=?, ChargeOfServices=?,TotalAmount=? WHERE PaymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setDouble(1, Double.parseDouble(Charge_Of_Appoinments));
			preparedStmt.setDouble(2, Double.parseDouble(Charge_Of_Pharmacy));
			preparedStmt.setDouble(3, Double.parseDouble(Charge_Of_Services));
			preparedStmt.setDouble(4, Double.parseDouble(Total_Amount));
			preparedStmt.setInt(5, Integer.parseInt(ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayment(String PaymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from payments where PaymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(PaymentID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(int paymentId, Double chargeOfAppoinments, Double chargeOfPharmacy,
			Double chargeOfServices, Double totalAmount) {
		// TODO Auto-generated method stub
		return null;
	}
}

		

