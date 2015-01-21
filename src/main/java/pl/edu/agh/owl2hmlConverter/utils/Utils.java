package pl.edu.agh.owl2hmlConverter.utils;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.classes.AbstractBaseDTO;

/**
 * Klasa narzędziowa
 */
public class Utils {

	/**
	 * Metoda pobierająca z listy obiektów Node obiekty o podanej nazwie tagu XML
	 * 
	 * @param nodeList Lista obiektów klasy Node
	 * @param nodeName Nazwa tagu XML
	 * @return Lista obiektów klasy Node o podanej nazwie
	 */
	public static List<Node> getNodeListWithSpecifiedNodeName(
			NodeList nodeList, String nodeName) {
		List<Node> retrievedNodeList = new ArrayList<Node>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equals(nodeName)) {
				retrievedNodeList.add(node);
			}
		}
		return retrievedNodeList;
	}

	/**
	 * Metoda usuwająca '"' oraz '#' z napisu
	 * 
	 * @param text Tekst, z którego należy usunąć zbędne znaki
	 * @return Tekst z usuniętymi znakami
	 */
	public static String removeUnnecessaryCharsFromString(String text) {
		String textWithoutUnnecessaryChars = text.replace("\"", "");
		textWithoutUnnecessaryChars = textWithoutUnnecessaryChars.replace("#",
				"");
		return textWithoutUnnecessaryChars;
	}

	/**
	 * Metoda znajdująca klasę DTO o podanej nazwie w podanej liście
	 * 
	 * @param dtoList Lista obiektów pośrednich
	 * @param name Nazwa obiektu pośredniego
	 * @return Obiekt pośredni
	 */
	public static AbstractBaseDTO findDTOByNameInOneOfConvertedClassDTOLists(
			List<? extends AbstractBaseDTO> dtoList, String name) {
		for (AbstractBaseDTO baseDTO : dtoList) {
			if (name.equals(baseDTO.getName())) {
				return baseDTO;
			}
		}
		return null;
	}

}
