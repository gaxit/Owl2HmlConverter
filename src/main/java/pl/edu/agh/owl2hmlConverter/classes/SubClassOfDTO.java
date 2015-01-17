package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class SubClassOfDTO {
	
	private List<Node> subClassOfNodeList;
	
	public SubClassOfDTO(){
		subClassOfNodeList = new ArrayList<Node>();
	}
	
	public void add(Node node){
		subClassOfNodeList.add(node);
	}
	
	public void addAll(List<Node> nodeList){
		if (nodeList == null){
			return;
		}
		for (Node node : nodeList) {
			add(node);
		}
	}
	
	public List<Node> getSubClassOfNodeList(){
		return subClassOfNodeList;
	}

}
