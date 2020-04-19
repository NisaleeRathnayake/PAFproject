package com;

import model.Appointment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Appointment")
public class AppointmentService {
	Appointment appObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment() {
		//return "Hello";
		return appObj.readAppointment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("title") String title,
			@FormParam("fname") String fname,
			@FormParam("lname") String lname,
			@FormParam("phone") Integer phone,
			@FormParam("email") String email,
			@FormParam("date") String date,
			@FormParam("aCatergory") String aCatergory,
			@FormParam("dCatergory") String dCatergory,
			@FormParam("dName") String dName)
		
	{
	String output = appObj.insertAppointment(title, fname, lname, phone, email, date, aCatergory, dCatergory, dName);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String itemData)
	{
		
	//Convert the input string to a JSON object
	JsonObject appObject = new JsonParser().parse(appObj).getAsJsonObject();
	//Read the values from the JSON object
	String title = appObject.get("title").getAsString();
	String fname = appObject.get("fname").getAsString();
	String lname = appObject.get("lname").getAsString();
	String phone = appObject.get("phone").getAsString();
	String email = appObject.get("email").getAsString();
	String date = appObject.get("date").getAsString();
	String aCatergory = appObject.get("aCatergory").getAsString();
	String dCatergory = appObject.get("dCatergory").getAsString();
	String dName = appObject.get("dName").getAsString();
	String output = appObj.updateAppointment(title, fname, lname, phone, email, date, aCatergory, dCatergory, dName);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletename(String AppData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(appObj, "", Parser.xmlParser());
	//Read the value from the element <date>
	String date = doc.select("date").text();
	String output = appObj.deletename("fname");
	return output;
	}
	
}
	
