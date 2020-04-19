package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerService {

	Connection con = null ;
	 Statement statement ;
	 PreparedStatement preStatement ;
	
	public void Resource() {
		
		String dbURL = "jdbc:mysql://127.0.0.1:3306/helthcare" ;
		//String dbName = "" ;
		String dbUsername = "root" ;
		String dbPassword = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbURL,dbUsername , dbPassword);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public List<Customer> getCustomer() {
		
		List<Customer> customer = new ArrayList<>();
		
		String sql = "SELECT * FROM user";
		
	try {
		Statement st = con.createStatement();
		ResultSet results = st.executeQuery(sql);
		
		while (results.next()) {
			Customer cus = new Customer();
			
			cus.setUserid(results.getInt(1));
			cus.setFname(results.getString(2));
			cus.setLname(results.getString(3));
			cus.setAddress(results.getString(4));
			cus.setDoB(results.getDate(5));
			cus.setPhone(results.getString(6));
			cus.setEmail(results.getString(7));
			
			customer.add(cus);
			
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return customer;

	}
	
	
	
	public void insert(Customer customer) {
		
		String sql = "INSERT INTO user(userid,fname,lname,address,DoB,phone,email) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setLong(1, customer.getUserid());
			st.setString(2, customer.getFname());
			st.setString(3, customer.getLname());
			st.setString(4, customer.getAddress());
			st.setString(5, customer.getDoB().toString());
			st.setString(6, customer.getPhone());
			st.setString(7, customer.getEmail());
			
			System.out.println("query "+st);
			st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void delete(String userid) {
		String sql = "DELETE FROM user WHERE userid=?;";
		
		try {
			PreparedStatement state = con.prepareStatement(sql);
			
			state.setString(1, userid);
			
			state.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public void update(Customer customer) {
		
		String sql = "UPDATE user SET fame =?, lame = ?, address = ?, DoB = ?, phone = ?, email = ? WHERE doctorID = ?";
		
		try {
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, customer.getFname());
			st.setString(2, customer.getLname());
			st.setString(3, customer.getAddress());
			st.setDate(4, customer.getDoB());
			st.setString(5, customer.getPhone());
			st.setString(6, customer.getEmail());
			
			
			st.executeUpdate();
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
}
