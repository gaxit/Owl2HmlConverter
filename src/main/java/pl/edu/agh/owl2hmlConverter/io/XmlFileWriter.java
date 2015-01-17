package pl.edu.agh.owl2hmlConverter.io;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlFileWriter {
	
	private static final String PATH_TO_OWL_FILE = "converted.hml";
	
	public void writeXmlFile(Document document) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(PATH_TO_OWL_FILE));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		
		transformer.transform(source, result);
	}

}
