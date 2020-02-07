package com.lyon.enterprises.dto;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class XmlReader {

	private static XmlReader reader;
	private JSONArray businesscards = new JSONArray();
	
	private XmlReader() {}
	
	public static XmlReader getInstance ()
	{
		if (reader == null) {
			reader = new XmlReader();
		}
		return reader;
	}
	
	public void XmlToJson(String fileLocation)
	{		
		XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = null;
		
        try {
			xsr = xif.createXMLStreamReader(new FileReader(fileLocation));
			xsr.nextTag(); 

			ObjectMapper mapper = new ObjectMapper();
			String str = "";
			JSONObject obj = null;
			Businesscard bcard = null;
			JAXBContext jc = JAXBContext.newInstance(Businesscard.class);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        
	        while(xsr.nextTag() == XMLStreamConstants.START_ELEMENT) {
	        	bcard = (Businesscard) unmarshaller.unmarshal(xsr); 
	        	 
	        	str = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bcard);
				obj = new JSONObject(str);
				businesscards.put(obj);
				
				obj = null;
				System.gc();
	        }		        
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (XMLStreamException e1) {
			e1.printStackTrace();
		} catch (JAXBException | JsonProcessingException e) {
			e.printStackTrace();
		} finally {
			try {
				xsr.close();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}       
	}
		
	public JSONArray getBusinessCards()
	{
		return this.businesscards;
	}
}
