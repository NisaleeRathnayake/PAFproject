package com;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


	@Path("/Payment")
	public class PaymentService {
		Payment paymentObj = new Payment();

		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readPayments() {
			//return "Hello";
			return paymentObj.readPayments();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertPayment(@FormParam("ChargeOfAppoinments") String ChargeOfAppoinments,
				@FormParam("ChargeOfPharmacy") String ChargeOfPharmacy,
				@FormParam("ChargeOfServices") String ChargeOfServices,
				@FormParam("TotalAmount") String TotalAmount)
		{
			String output = paymentObj.insertPayment(ChargeOfAppoinments, ChargeOfPharmacy, ChargeOfServices, TotalAmount);
		return output;
		}
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updatePayment(String paymentData)
		{
			
		//Convert the input string to a JSON object
		JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
		//Read the values from the JSON object
		int PaymentId = paymentObject.get("PaymentId").getAsInt();
		Double ChargeOfAppoinments = paymentObject.get("ChargeOfAppoinments").getAsDouble();
		Double ChargeOfPharmacy = paymentObject.get("Charge_Of_Pharmacy").getAsDouble();
		Double ChargeOfServices = paymentObject.get("ChargeOfServices").getAsDouble();
		Double TotalAmount = paymentObject.get("TotalAmount").getAsDouble();
		String output = paymentObj.updatePayment(PaymentId, ChargeOfAppoinments, ChargeOfPharmacy, ChargeOfServices, TotalAmount);
		return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deletePayment(String paymentmData)
		{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentmData, "", Parser.xmlParser());
		//Read the value from the element <PaymentId>
		String PaymentId = doc.select("PaymentId").text();
		String output = paymentObj.deletePayment(PaymentId);
		return output;
		}
		
	}
		

