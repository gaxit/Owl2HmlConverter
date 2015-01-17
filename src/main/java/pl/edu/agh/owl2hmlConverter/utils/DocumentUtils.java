package pl.edu.agh.owl2hmlConverter.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class DocumentUtils {
	
	private static Document document;
	
	public static Document getDocument() throws ParserConfigurationException{
		if (document == null){
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			document = docBuilder.newDocument();
		}
		return document;
	}

}
