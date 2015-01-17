package pl.edu.agh.owl2hmlConverter.utils;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.Constants;

public class Utils {
	
	public static List<Node> getNodeListWithSpecifiedNodeName(NodeList nodeList, String nodeName){
		List<Node> retrievedNodeList = new ArrayList<Node>();
		for (int i=0; i<nodeList.getLength(); i++){
			Node node = nodeList.item(i);
			if (node.getNodeName().equals(Constants.OwlNodeNames.SUBCLASS_OF)){
				retrievedNodeList.add(node);
			}
		}
		return retrievedNodeList;
	}

}
