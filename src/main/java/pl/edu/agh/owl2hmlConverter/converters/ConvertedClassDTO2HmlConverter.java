package pl.edu.agh.owl2hmlConverter.converters;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.utils.DocumentUtils;

public class ConvertedClassDTO2HmlConverter {

	private static Document document;

	public static Element convert(ConvertedClassDTO convertedClassDTO) throws ParserConfigurationException {
		document = DocumentUtils.getDocument();
		Element rootElement = document.createElement("company");

		return rootElement;
	}

}
