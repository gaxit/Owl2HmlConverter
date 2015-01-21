package pl.edu.agh.owl2hmlConverter.converters;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.edu.agh.owl2hmlConverter.Constants;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.classes.DataTypeDTO;
import pl.edu.agh.owl2hmlConverter.classes.InferenceDTO;
import pl.edu.agh.owl2hmlConverter.classes.SensorDTO;
import pl.edu.agh.owl2hmlConverter.classes.SubClassOfDTO;
import pl.edu.agh.owl2hmlConverter.utils.Utils;

/**
 * Konwerter z postaci OWL do postaci przejściowej
 */
public class Owl2ConvertedClassDTOConverter {

	/**
	 * Metoda konwertująca z postaci OWL do postaci przejściowej
	 * 
	 * @param subClassOfDTO Obiekt zawierający listę obiektów Node o type SubClassOf
	 * @return Główny obiekt klasy przejściowej
	 */
	public static ConvertedClassDTO convert(SubClassOfDTO subClassOfDTO) {
		ConvertedClassDTO convertedClassDTO = new ConvertedClassDTO();
		List<Node> doubleClassList = new ArrayList<Node>();
		List<Node> singleClassAndObjectSomeValuesFromList = new ArrayList<Node>();
		List<Node> subClassOfNodeList = subClassOfDTO.getSubClassOfNodeList();

		for (Node node : subClassOfNodeList) {
			analyzeSubClassOfElementAndAddToLists(node, doubleClassList,
					singleClassAndObjectSomeValuesFromList);
		}

		addNodesToConvertedClassDTO(convertedClassDTO, doubleClassList);
		addConnectionsBetweenDTOs(convertedClassDTO,
				singleClassAndObjectSomeValuesFromList);

		return convertedClassDTO;
	}

	private static void addConnectionsBetweenDTOs(
			ConvertedClassDTO convertedClassDTO,
			List<Node> singleClassAndObjectSomeValuesFromList) {
		for (Node node : singleClassAndObjectSomeValuesFromList) {
			addConnectionsBetweenDTO(convertedClassDTO, node);
		}

	}

	private static void addConnectionsBetweenDTO(
			ConvertedClassDTO convertedClassDTO, Node node) {
		Node mainClassNode = Utils.getNodeListWithSpecifiedNodeName(
				node.getChildNodes(), Constants.OwlNodeNames.CLASS).get(0);
		Node objectSomeValuesFromNode = Utils.getNodeListWithSpecifiedNodeName(
				node.getChildNodes(),
				Constants.OwlNodeNames.OBJECT_SOME_VALUE_FROM).get(0);
		Node connectedNode = Utils.getNodeListWithSpecifiedNodeName(
				objectSomeValuesFromNode.getChildNodes(),
				Constants.OwlNodeNames.CLASS).get(0);

		String mainClassNodeIriAttributeValue = getValueOfSpecificAttributeFromNode(
				mainClassNode, Constants.AttributeNames.IRI);
		String connectedNodeIriAttributeValue = getValueOfSpecificAttributeFromNode(
				connectedNode, Constants.AttributeNames.IRI);

		InferenceDTO inference = (InferenceDTO) Utils
				.findDTOByNameInOneOfConvertedClassDTOLists(
						convertedClassDTO.getInferenceList(),
						mainClassNodeIriAttributeValue);
		if (inference != null) {
			inference.addDataTypeName(connectedNodeIriAttributeValue);
			return;
		}

		DataTypeDTO dataType = (DataTypeDTO) Utils
				.findDTOByNameInOneOfConvertedClassDTOLists(
						convertedClassDTO.getDataTypeList(),
						mainClassNodeIriAttributeValue);
		if (dataType != null) {
			dataType.addSensorName(connectedNodeIriAttributeValue);
		}
	}

	private static void analyzeSubClassOfElementAndAddToLists(Node node,
			List<Node> doubleClassList,
			List<Node> singleClassAndObjectSomeValuesFromList) {
		NodeList nodeList = node.getChildNodes();
		List<Node> classElementList = Utils.getNodeListWithSpecifiedNodeName(
				nodeList, Constants.OwlNodeNames.CLASS);
		List<Node> objectSomeValuesFromList = Utils
				.getNodeListWithSpecifiedNodeName(nodeList,
						Constants.OwlNodeNames.OBJECT_SOME_VALUE_FROM);
		if (classElementList.size() == 2
				&& objectSomeValuesFromList.size() == 0) {
			doubleClassList.add(node);
			return;
		}
		if (classElementList.size() == 1
				&& objectSomeValuesFromList.size() == 1) {
			singleClassAndObjectSomeValuesFromList.add(node);
		}
	}

	private static void addNodesToConvertedClassDTO(
			ConvertedClassDTO convertedClassDTO, List<Node> doubleClassList) {
		for (Node node : doubleClassList) {
			addNodeToConvertedClassDTO(convertedClassDTO, node);
		}
	}

	private static void addNodeToConvertedClassDTO(
			ConvertedClassDTO convertedClassDTO, Node node) {
		List<Node> listNode = Utils.getNodeListWithSpecifiedNodeName(
				node.getChildNodes(), Constants.OwlNodeNames.CLASS);
		addInferenceIfExists(convertedClassDTO, listNode);
		addDataTypeIfExists(convertedClassDTO, listNode);
		addSensorIfExists(convertedClassDTO, listNode);
	}

	private static void addSensorIfExists(ConvertedClassDTO convertedClassDTO,
			List<Node> listNode) {
		String sensorName = getNameOfBaseDTO(listNode,
				Constants.HmlNodeTypes.SENSOR);
		if (sensorName != null) {
			SensorDTO sensor = new SensorDTO();
			sensor.setName(sensorName);
			convertedClassDTO.addSensor(sensor);
		}
	}

	private static void addDataTypeIfExists(
			ConvertedClassDTO convertedClassDTO, List<Node> listNode) {
		String dataTypeName = getNameOfBaseDTO(listNode,
				Constants.HmlNodeTypes.DATA_TYPE);
		if (dataTypeName != null) {
			DataTypeDTO dataType = new DataTypeDTO();
			dataType.setName(dataTypeName);
			convertedClassDTO.addDataType(dataType);
		}
	}

	private static void addInferenceIfExists(
			ConvertedClassDTO convertedClassDTO, List<Node> listNode) {
		String inferenceName = getNameOfBaseDTO(listNode,
				Constants.HmlNodeTypes.INFERENCE);
		if (inferenceName != null) {
			InferenceDTO inference = new InferenceDTO();
			inference.setName(inferenceName);
			convertedClassDTO.addInference(inference);
		}
	}

	private static String getNameOfBaseDTO(List<Node> listNode,
			String hmlNodeType) {
		int indexOfMainNode = findNodeWithIRIAttribute(listNode, hmlNodeType);
		if (indexOfMainNode != -1) {
			int entityIndex = 0;
			if (indexOfMainNode == 0) {
				entityIndex = 1;
			}
			Node entityNode = listNode.get(entityIndex);
			return getValueOfSpecificAttributeFromNode(
					entityNode, Constants.AttributeNames.IRI);
		}
		return null;
	}

	private static String getValueOfSpecificAttributeFromNode(Node node,
			String attributeName) {
		for (int j = 0; j < node.getAttributes().getLength(); j++) {
			Node attribute = node.getAttributes().item(j);
			if (attributeName.equals(attribute.getNodeName())) {
				String attributeValue = attribute.getNodeValue();
				attributeValue = Utils.removeUnnecessaryCharsFromString(attributeValue);
				return attributeValue;
			}
		}
		return null;
	}

	private static int findNodeWithIRIAttribute(List<Node> listNode,
			String nodeType) {
		for (int i = 0; i < listNode.size(); i++) {
			Node node = listNode.get(i);
			for (int j = 0; j < node.getAttributes().getLength(); j++) {
				Node attribute = node.getAttributes().item(j);
				if (nodeType.equals(attribute.getNodeValue())) {
					return i;
				}
			}
		}
		return -1;
	}

}
