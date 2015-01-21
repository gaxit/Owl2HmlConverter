package pl.edu.agh.owl2hmlConverter.io;

import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

/**
 * Klasa odpowiedzialna za zapis wynikowego pliku XML w postaci HML
 */
public class XmlFileWriter {
	
	public void writeXmlFile(Document document, String pathToHmlFile) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(new File(pathToHmlFile));
		
		transformer.transform(source, result);
	}

}
