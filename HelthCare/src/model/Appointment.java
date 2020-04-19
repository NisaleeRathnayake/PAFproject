package model;

import java.sql.*;

public class Appointment { // A common method to connect to the DB
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

	public String insertAppointment(String title, String fname, String lname, Integer phone, String email, String date, String aCatergory, String dCatergory, String dName  ) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " INSERT INTO `appointment`(`title`, `fname`, `lname`, `phone`, `email`, `date`, 'aCatergory', 'dCatergory', 'dName') VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setInt(4, 0);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, aCatergory);
			preparedStmt.setString(7, dCatergory);s
			preparedStmt.setString(8, dName);

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

	public String readAppointment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Preparing the table to be displayed
			output = "<table border=\"1\"><tr><th>Title</th><th>First Name</th><th>Last Name</th><th>Phone Number</th><th>Email</th><th>Date</th><th>Appointment Catergory</th><th>Doctor Catergory</th><th>Doctor Name</th></tr>";
			String query = "select * from appointment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String title = rs.getString("title");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				String date = rs.getString("date");
				String aCatergory = rs.getString("aCatergory");
				String dCatergory = rs.getString("dCatergory");
				String dName = rs.getString("dName");
				
// Add into the html table
				output += "<tr><td>" + title + "</td>";
				output += "<td>" + fname + "</td>";
				output += "<td>" + lname + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + aCatergory + "</td>";
				output += "<td>" + dCatergory + "</td>";
				output += "<td>" + dName + "</td>";
			
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"appointment.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"date\" type=\"hidden\" value=\"" + date + "\">" + "</form></td></tr>";
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

	public String updateAppointment(String title, String fname, String lname, Integer phone, String email, Date date, String aCatergory, String dCatergory, String dName  )  {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE appointment SET title=?,fname=?,lname=?,phone=?,email=?,date=?,aCatergory=?,dCatergory=?,dName=? WHERE idAppointment=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, title);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setInt(4, phone);
			preparedStmt.setString(5, email);
			preparedStmt.setDate(6, date);
			preparedStmt.setString(7, aCatergory);
			preparedStmt.setString(8, dCatergory);
			preparedStmt.setString(9, dName);
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletename(String fname) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from items where fname=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(fname));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the name.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertAppointment1(String title, String fname, String lname, Integer phone, String email, String date,
			String aCatergory, String dCatergory, String dName) {
		// TODO Auto-generated method stub
		return null;
	}
}
