package pl.edu.agh.owl2hmlConverter.converters;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.Constants;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.classes.SubClassOfDTO;
import pl.edu.agh.owl2hmlConverter.utils.DocumentUtils;
import pl.edu.agh.owl2hmlConverter.utils.Utils;

/**
 * Konwerter z postaci OWL do postaci HML
 */
public class Owl2HmlConverter {

	/**
	 * Metoda konwertująca dokument w postaci OWL na dokument w postaci HML
	 * 
	 * @param readDocument Dokument w postaci OWL
	 * @return Dokument w postaci HML
	 * @throws ParserConfigurationException
	 */
	public Document convertOwl2Hml(Document readDocument) throws ParserConfigurationException {
		Element ontologyElement = readDocument.getDocumentElement();
		SubClassOfDTO subClassOfDTO = prepareSubClassOfDTO(ontologyElement);
		ConvertedClassDTO convertedClassDTO = Owl2ConvertedClassDTOConverter
				.convert(subClassOfDTO);

		Element hmlElement = ConvertedClassDTO2HmlConverter
				.convert(convertedClassDTO);
		return prepareHmlDocument(hmlElement);
	}

	private SubClassOfDTO prepareSubClassOfDTO(Element ontologyElement) {
		SubClassOfDTO subClassOfDTO = new SubClassOfDTO();
		NodeList nodeList = ontologyElement.getChildNodes();
		
		List<Node> retrievedNodeList = Utils.getNodeListWithSpecifiedNodeName(
				nodeList, Constants.OwlNodeNames.SUBCLASS_OF);
		subClassOfDTO.addAll(retrievedNodeList);
		
		return subClassOfDTO;
	}

	private Document prepareHmlDocument(Element hmlElement) throws ParserConfigurationException {
		Document document = DocumentUtils.getDocument();
		document.appendChild(hmlElement);
		return document;
	}

}
