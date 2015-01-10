package pl.edu.agh.owl2hmlConverter;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFileReader {
	
	private static final String PATH_TO_OWL_FILE = "src/main/resources/mikrotest2.owl";
	
	public Document readFile() throws ParserConfigurationException, SAXException, IOException{
		File xmlFile = new File(PATH_TO_OWL_FILE);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(xmlFile);
		return document;
	}

}
