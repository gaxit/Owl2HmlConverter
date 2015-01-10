package pl.edu.agh.owl2hmlConverter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MainClass 
{
    public static void main( String[] args )
    {        
        XmlFileReader xmlFileReader = new XmlFileReader();
        Owl2HmlParser owl2HmlParser = new Owl2HmlParser();
        XmlFileWriter xmlFileWriter = new XmlFileWriter();
        
        Document owlDocument = null;
        Document hmlDocument = null;
        try {
        	owlDocument = xmlFileReader.readFile();
        	System.out.println("Owl file read");
        	
        	hmlDocument = owl2HmlParser.parseOwl2Hml(owlDocument);
        	System.out.println("File converted");
        	
        	xmlFileWriter.writeXmlFile(hmlDocument);
        	System.out.println("Hml file written");
		} catch (ParserConfigurationException e) {
			printErrorMessage(e);
		} catch (SAXException e) {
			printErrorMessage(e);
		} catch (IOException e) {
			printErrorMessage(e);
		} catch (TransformerException e) {
			printErrorMessage(e);
		}
    }
    
    private static void printErrorMessage(Exception e){
    	System.out.println("ERROR: " + e.getMessage());
    	e.printStackTrace();
    }
}
