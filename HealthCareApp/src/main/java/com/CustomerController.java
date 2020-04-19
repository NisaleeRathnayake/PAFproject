package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Customer;


@Path("customer")
public class CustomerController {
	
	CustomerService customerService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getCustomer() {
		return customerService.getCustomer();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void verify(Customer customer) {
		System.out.println(customer);
		customerService.insert(customer);
	}
	
	
	@DELETE
	@Path("/{userid}")
	public void delete(@PathParam("userid") String userid) {
		System.out.println(userid);
		
		customerService.delete(userid);
		
	}
	
	
	@PUT
	@Produces(MediaType.APPLICATION_XML)
	public void update(Customer customer) {
		System.out.println(customer);

		customerService.update(customer);
	}	
}

