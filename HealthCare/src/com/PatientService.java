package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Patient;



@Path("/Patient")
public class PatientService {
	
	Patient patientObj = new Patient();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		//return "Hello";
		return patientObj.readPatient();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(
			@FormParam("name") String name,
			@FormParam("address") String address,
			@FormParam("dob") String dob,
			@FormParam("email") String email,
			@FormParam("phoneNo") int phoneNo)
	{
	String output = patientObj.insertPatient(phoneNo, name, address, dob, email, phoneNo);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String patientData)
	{
		
	//Convert the input string to a JSON object
	JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	String patientID = patientObject.get("patientID").getAsString();
	String name = patientObject.get("name").getAsString();
	String address = patientObject.get("address").getAsString();
	String dob = patientObject.get("dob").getAsString();
	String email = patientObject.get("email").getAsString();
	int phoneNo = patientObject.get("phoneNo").getAsInt();
	String output = patientObj.updatePatient(patientID, name, address, dob, email, phoneNo);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String patientID = doc.select("patientID").text();
	String output = patientObj.deletePatient(patientID);
	return output;
	}

}
