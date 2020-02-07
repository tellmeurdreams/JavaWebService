package com.lyon.enterprises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lyon.enterprises.dto.XmlReader;

@Path("/businesscard")
public class EnterpriseBusinessCardService {
	
	@GET
	@Path("getbyid/{icd}:{enterpriseNumber}")
	@Produces("application/json")	
	public Response getById( @PathParam("icd") int icd, @PathParam("enterpriseNumber") int enterpriseNumber )
	{			
		XmlReader rdr = XmlReader.getInstance();
		JSONObject businessCard = new JSONObject();
		JSONArray businessCards = rdr.getBusinessCards();
		
		for (int j = 0; j < businessCards.length(); j++) {
			
			JSONObject participant = businessCards.getJSONObject(j).getJSONObject("participant");
			String[] partValue = participant.getString("value").split(":");				
			
			if ( Integer.valueOf(partValue[0]).equals(icd) ) {
				if ( Integer.valueOf(partValue[1]).equals(enterpriseNumber) ) {
					businessCard = businessCards.getJSONObject(j);
					break;
				}
			}
		}
				
		String result = "@Produces(\"application/json\") getbyid Output: \n\nbusinesscard: \n\n" + businessCard.toString();
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/searchbyname/{searchname}")
	@Produces("application/json")		
	public Response searchByName( @PathParam("searchname") String strName ) throws JSONException
	{
		JSONObject businessCard = null;
		JSONArray businessCardsResult = new JSONArray();
		
		XmlReader rdr = XmlReader.getInstance();
		JSONArray businessCards = rdr.getBusinessCards();
		
		for (int j = 0; j < businessCards.length(); j++) {
			JSONObject bCard = businessCards.getJSONObject(j);
			JSONObject participant = bCard.getJSONObject("participant");
			JSONObject entity = bCard.getJSONObject("entity");
			JSONObject jsonName = entity.getJSONObject("name");
			String nameValue = jsonName.getString("name").trim();
			System.out.println("NameValue : " + nameValue);
			
			String pattern = "(.*)" +strName +"(.*)";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(nameValue);
			
			if ( m.matches() ) {
				String[] enterpriseNumber = participant.getString("value").split(":");
				
				businessCard = new JSONObject();
				businessCard.put("name", nameValue);
				businessCard.put("enterpriseNumber", enterpriseNumber[1]);
				businessCard.put("countryCode", entity.getString("countrycode"));
				businessCardsResult.put(businessCard);
			}
		}
				
		String result = "@Produces(\"application/json\") searchbyname Output: \n\n Output: \n\n" + businessCardsResult.toString();
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/getbyname/{name}")
	@Produces("application/json")
	public Response getByName( @PathParam("name") String name ) 
	{
		XmlReader rdr = XmlReader.getInstance();
		JSONObject businessCard = new JSONObject();
		JSONArray businessCards = rdr.getBusinessCards();
		
		for (int j = 0; j < businessCards.length(); j++) {
			JSONObject jsonName = businessCards.getJSONObject(j).getJSONObject("entity").getJSONObject("name");
			String nameValue = jsonName.getString("name").trim();
			System.out.println("NameValue : " + nameValue);
			
			if ( nameValue.equals(name) ) {
				businessCard = businessCards.getJSONObject(j);
				break;
			}
		}
		
		String result = "@Produces(\"application/json\") getbyname Output: \n\nbusinesscard: \n\n" + businessCard.toString();
		return Response.status(200).entity(result).build();
	}
}
