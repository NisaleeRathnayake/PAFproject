package model;

import java.sql.*;

public class Pharmacy { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare", "root", "");
			System.out.println("Sucessfully connected!!!");
		} catch (Exception e) {
			System.out.println("Not connect to the database");
			e.printStackTrace();
		}
		return con;
	}

	public String insertPharmacy(String medicinetype, String medicinename, String price, String qtyofavailablemedicine,
			String reorderlimit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = "INSERT INTO `pharmacy`(`MedicineId`, `MedicineType`, `MedicineName`, `UnitPrice`, `QtyOfAvailableMedicine`, `ReorderLimit`) VALUES (?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, medicinetype);
			preparedStmt.setString(3, medicinename);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, qtyofavailablemedicine);
			preparedStmt.setString(6, reorderlimit);

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPharmacys() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Medicine Type</th><th>Medicine Name</th><th>Unit Price</th><th>QtyOfAvailableMedicine</th><th>ReorderLimit</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from pharmacy";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String MedicineId = Integer.toString(rs.getInt("Medicineid"));
				String MedicineType = rs.getString("MedicineType");
				String MedicineName = rs.getString("MedicineName");
				String UnitPrice = Double.toString(rs.getDouble("UnitPrice"));
				String QtyOfAvailableMedicine = Integer.toString(rs.getInt("QtyOfAvailableMedicine"));
				String ReorderLimit = Integer.toString(rs.getInt("ReorderLimit"));

// Add into the html table
				output += "<tr><td>" + MedicineType + "</td>";
				output += "<td>" + MedicineName + "</td>";
				output += "<td>" + UnitPrice + "</td>";
				output += "<td>" + QtyOfAvailableMedicine + "</td>";
				output += "<td>" + ReorderLimit + "</td>";

// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"pharmacy.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + MedicineId + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePharmacy(String ID, String medicinetype, String medicinename, String price,String qtyofavailablemedicine, String reorderlimit) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE pharmacy SET MedicineType=?,MedicineName=?,UnitPrice=?,QtyOfAvailableMedicine=?,ReorderLimit=? WHERE MedicineId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, medicinetype);
			preparedStmt.setString(2, medicinename);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setInt(4, Integer.parseInt(qtyofavailablemedicine));
			preparedStmt.setInt(5, Integer.parseInt(reorderlimit));
			preparedStmt.setInt(6, Integer.parseInt(ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletepharmacy(String MedicineId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from pharmacy where MedicineId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(MedicineId));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
