package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * Klasa przechowująca informacje o obiektach Node - obiektach wczytanych z
 * ontologii, których nazwą tagu jest SubClassOf
 */
public class SubClassOfDTO {

	private List<Node> subClassOfNodeList;

	public SubClassOfDTO() {
		subClassOfNodeList = new ArrayList<Node>();
	}

	/**
	 * Dodaje element typu Node do listy
	 * 
	 * @param node Obiekt typu Node - tag xml
	 */
	public void add(Node node) {
		subClassOfNodeList.add(node);
	}

	/**
	 * Dodaje listę elementów typu Node do listy
	 * 
	 * @param nodeList Lista obiektów typu Node - tag xml
	 */
	public void addAll(List<Node> nodeList) {
		if (nodeList == null) {
			return;
		}
		for (Node node : nodeList) {
			add(node);
		}
	}

	public List<Node> getSubClassOfNodeList() {
		return subClassOfNodeList;
	}

}
