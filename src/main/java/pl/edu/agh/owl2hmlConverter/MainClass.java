package pl.edu.agh.owl2hmlConverter;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import pl.edu.agh.owl2hmlConverter.converters.Owl2HmlConverter;
import pl.edu.agh.owl2hmlConverter.io.XmlFileReader;
import pl.edu.agh.owl2hmlConverter.io.XmlFileWriter;

public class MainClass {
	
	private static final String PATH_TO_OWL_FILE = "src/main/resources/TestOntology.owl";
	private static final String PATH_TO_HML_FILE = "ConvertedHml.hml";
	
    public static void main(String[] args){        
        XmlFileReader xmlFileReader = new XmlFileReader();
        Owl2HmlConverter owl2HmlParser = new Owl2HmlConverter();
        XmlFileWriter xmlFileWriter = new XmlFileWriter();
        
        Document owlDocument = null;
        Document hmlDocument = null;
        try {
        	owlDocument = xmlFileReader.readFile(PATH_TO_OWL_FILE);
        	System.out.println("Owl file read");
        	
        	hmlDocument = owl2HmlParser.parseOwl2Hml(owlDocument);
        	System.out.println("File converted");
        	
        	xmlFileWriter.writeXmlFile(hmlDocument, PATH_TO_HML_FILE);
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
