package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {
	
	// A common method to connect to the DB

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			System.out.println("Sucessfully connected!!!");
		} catch (Exception e) {
			System.out.println("Not connect to the database" );
			e.printStackTrace();
		}
		return con;

}


public String insertPatient(int patientID, String name, String address, String dob, String email, int phoneNo) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for inserting.";
		}
//create a prepared statement
		String query = " INTO INSERT INTO `patient`(`patientID`, `name`, `address`, `dob`, `email`, `phoneNo`) VALUES (?,?,?,?,?,?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, address);
		preparedStmt.setString(4,dob);
		preparedStmt.setString(5, email);
		preparedStmt.setInt(5, phoneNo);

//execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
	} catch (Exception e) {
		output = "Error while inserting the item.";
		System.err.println(e.getMessage());
	}
	return output;
}


public String readPatient() {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for reading.";
		}
//Prepare the html table to be displayed
		output = "<table border=\"1\"><tr><th>name</th><th>address</th><th>dob</th><th>email</th><th>phoneNo</th><th>Update</th><th>Remove</th></tr>";
		String query = "SELECT * FROM `patient`";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
//iterate through the rows in the result set
		while (rs.next()) {
			String patientID = Integer.toString(rs.getInt("patientID"));
			String name = rs.getString("name");
			String address = rs.getString("address");
			String dob = rs.getString("dob");
			String email = rs.getString("email");
			String phoneNo = Integer.toString(rs.getInt("phoneNo"));
			
			
//Add into the html table
			output += "<tr><td>" + name + "</td>";
			output += "<td>" + address + "</td>";
			output += "<td>" + dob + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + phoneNo + "</td>";
//buttons
			output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
					+ "<td><form method=\"post\" action=\"items.jsp\">"
					+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
					+ "<input name=\"itemID\" type=\"hidden\" value=\"" + patientID + "\">" + "</form></td></tr>";
		}
		con.close();
//Complete the html table
		output += "</table>";
	} catch (Exception e) {
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String updatePatient(String patientID, String name, String address, String dob, String email, int phoneNo) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for updating.";
		}
//create a prepared statement
		String query = "UPDATE items SET name=?,address=?,dob=?,email=?,phoneNo=? WHERE patientID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, address);
		preparedStmt.setString(2, dob);
		preparedStmt.setString(4, email);
		preparedStmt.setInt(5, phoneNo);
		preparedStmt.setInt(6, Integer.parseInt(patientID));
//execute the statement
		preparedStmt.execute();
		con.close();
		output = "Updated successfully";
	} catch (Exception e) {
		output = "Error while updating the item.";
		System.err.println(e.getMessage());
	}
	return output;
}



public String deletePatient(String patientID) {
	String output = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for deleting.";
		}
//create a prepared statement
		String query = "delete from items where patientID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
		preparedStmt.setInt(1, Integer.parseInt(patientID));
//execute the statement
		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
	} catch (Exception e) {
		output = "Error while deleting the item.";
		System.err.println(e.getMessage());
	}
	return output;
}
}

