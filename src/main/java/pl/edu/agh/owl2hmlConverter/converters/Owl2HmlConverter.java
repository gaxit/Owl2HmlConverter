package pl.edu.agh.owl2hmlConverter.converters;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;

public class Owl2HmlConverter {
	
	public Document parseOwl2Hml(Document readDocument){
		Element ontologyElement = readDocument.getDocumentElement();
		ConvertedClassDTO convertedClassDTO = Owl2ConvertedClassDTOConverter.convert(ontologyElement);
		
		Element hmlElement = ConvertedClassDTO2HmlConverter.convert(convertedClassDTO);
		Document hmlDocument = prepareHmlDocument(hmlElement);
		// TODO po konwersji ustawić hmlDocument jako zwracany obiekt
		return readDocument;
	}

	private Document prepareHmlDocument(Element hmlElement) {
		// TODO dodać przygotowanie dokumentu na podstawie głównego elementu
		return null;
	}

}
