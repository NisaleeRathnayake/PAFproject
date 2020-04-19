package com;

import model.Pharmacy;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Pharmacys")
public class PharmacyService {
	Pharmacy PharObj = new Pharmacy();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPharmacys() {
		//return "Hello";
		return PharObj.readPharmacys();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("MedicineType") String MedicineType,
			@FormParam("MedicineName") String MedicineName,
			@FormParam("UnitPrice") String UnitPrice,
			@FormParam("QtyOfAvailableMedicine") String QtyOfAvailableMedicine,
			@FormParam("ReorderLimit") String ReorderLimit

			)
	{
	String output = PharObj.insertPharmacy(MedicineType, MedicineName, UnitPrice, QtyOfAvailableMedicine,ReorderLimit);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String PharmacyData)
	{
		
	//Convert the input string to a JSON object
	JsonObject PharmacyObject = new JsonParser().parse(PharmacyData).getAsJsonObject();
	//Read the values from the JSON object
	String MedicineId = PharmacyObject.get("MedicineId").getAsString();
	String MedicineType  = PharmacyObject.get("MedicineType").getAsString();
	String MedicineName = PharmacyObject.get("MedicineName").getAsString();
	String UnitPrice = PharmacyObject.get("UnitPrice").getAsString();
	String QtyOfAvailableMedicine = PharmacyObject.get("QtyOfAvailableMedicine").getAsString();
	String ReorderLimit = PharmacyObject.get("ReorderLimit").getAsString();

	
	String output = PharObj.updatePharmacy(MedicineId, MedicineType, MedicineName, UnitPrice, QtyOfAvailableMedicine, ReorderLimit);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletepharmacy(String PharmacyData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(PharmacyData, "", Parser.xmlParser());
	//Read the value from the element <MedicineId>
	String MedicineId = doc.select("MedicineId").text();
	String output = PharObj.deletepharmacy(MedicineId);
	return output;
	}
	
}
	
