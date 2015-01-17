package pl.edu.agh.owl2hmlConverter.utils;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.classes.AbstractBaseDTO;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;

public class Utils {

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

	public static String removeUnnecessaryCharsFromString(String text) {
		String textWithoutUnnecessaryChars = text.replace("\"", "");
		textWithoutUnnecessaryChars = textWithoutUnnecessaryChars.replace("#",
				"");
		return textWithoutUnnecessaryChars;
	}

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
