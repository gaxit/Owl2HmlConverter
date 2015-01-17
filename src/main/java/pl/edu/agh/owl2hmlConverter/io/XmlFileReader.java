package pl.edu.agh.owl2hmlConverter.io;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFileReader {
	
	public Document readFile(String pathToFile) throws ParserConfigurationException, SAXException, IOException{
		File xmlFile = new File(pathToFile);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document document = docBuilder.parse(xmlFile);
		return document;
	}

}
