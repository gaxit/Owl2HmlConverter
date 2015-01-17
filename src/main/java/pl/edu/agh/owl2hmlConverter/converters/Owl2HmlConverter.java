package pl.edu.agh.owl2hmlConverter.converters;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.Constants;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.classes.SubClassOfDTO;
import pl.edu.agh.owl2hmlConverter.utils.Utils;

public class Owl2HmlConverter {

	public Document parseOwl2Hml(Document readDocument) {
		Element ontologyElement = readDocument.getDocumentElement();
		SubClassOfDTO subClassOfDTO = prepareSubClassOfDTO(ontologyElement);
		ConvertedClassDTO convertedClassDTO = Owl2ConvertedClassDTOConverter
				.convert(subClassOfDTO);

		Element hmlElement = ConvertedClassDTO2HmlConverter
				.convert(convertedClassDTO);
		Document hmlDocument = prepareHmlDocument(hmlElement);
		// TODO po konwersji ustawić hmlDocument jako zwracany obiekt
		return readDocument;
	}

	private SubClassOfDTO prepareSubClassOfDTO(Element ontologyElement) {
		SubClassOfDTO subClassOfDTO = new SubClassOfDTO();
		NodeList nodeList = ontologyElement.getChildNodes();
		
		List<Node> retrievedNodeList = Utils.getNodeListWithSpecifiedNodeName(
				nodeList, Constants.OwlNodeNames.SUBCLASS_OF);
		subClassOfDTO.addAll(retrievedNodeList);
		
		return subClassOfDTO;
	}

	private Document prepareHmlDocument(Element hmlElement) {
		// TODO dodać przygotowanie dokumentu na podstawie głównego elementu
		return null;
	}

}
