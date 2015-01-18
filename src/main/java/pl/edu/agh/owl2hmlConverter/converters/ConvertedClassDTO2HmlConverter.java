package pl.edu.agh.owl2hmlConverter.converters;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import pl.edu.agh.owl2hmlConverter.Constants;
import pl.edu.agh.owl2hmlConverter.classes.AbstractBaseDTO;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.classes.DataTypeDTO;
import pl.edu.agh.owl2hmlConverter.classes.InferenceDTO;
import pl.edu.agh.owl2hmlConverter.utils.DocumentUtils;

public class ConvertedClassDTO2HmlConverter {

	private static Document document;

	public static Element convert(ConvertedClassDTO convertedClassDTO)
			throws ParserConfigurationException {
		document = DocumentUtils.getDocument();
		Element rootElement = document
				.createElement(Constants.HmlNodeNames.HML);

		rootElement.appendChild(prepareTypesElement(convertedClassDTO));
		rootElement.appendChild(prepareAttributesElement());
		rootElement.appendChild(prepareXttElement(convertedClassDTO));

		return rootElement;
	}

	private static Element prepareTypesElement(
			ConvertedClassDTO convertedClassDTO) {
		Element typesElement = document
				.createElement(Constants.HmlNodeNames.TYPES);

		typesElement.appendChild(prepareTypeElement(
				Constants.DtoSuffix.INFERENCE_INDEX,
				Constants.DtoNames.INFERENCE_NAME,
				convertedClassDTO.getInferenceList()));

		typesElement.appendChild(prepareTypeElement(
				Constants.DtoSuffix.DATA_TYPE_INDEX,
				Constants.DtoNames.DATA_TYPE_NAME,
				convertedClassDTO.getDataTypeList()));

		typesElement.appendChild(prepareTypeElement(
				Constants.DtoSuffix.SENSOR_INDEX,
				Constants.DtoNames.SENSOR_NAME,
				convertedClassDTO.getSensorList()));

		return typesElement;
	}

	private static Element prepareTypeElement(int id, String name,
			List<? extends AbstractBaseDTO> dtoList) {
		Element typeElement = document
				.createElement(Constants.HmlNodeNames.TYPE);
		int indexSuffix = 1;

		typeElement.setAttribute(Constants.HmlAttributeNames.ID,
				Constants.Additional.TYPE_ID_BASE + id);
		typeElement.setAttribute(Constants.HmlAttributeNames.NAME, name);
		typeElement.setAttribute(Constants.HmlAttributeNames.BASE,
				Constants.Additional.SYMBOLIC);
		typeElement.appendChild(prepareDomainElement(dtoList, indexSuffix));

		return typeElement;
	}

	private static Node prepareDomainElement(
			List<? extends AbstractBaseDTO> dtoList, int indexSuffix) {
		Element domainElement = document
				.createElement(Constants.HmlNodeNames.DOMAIN);
		for (AbstractBaseDTO baseDTO : dtoList) {
			domainElement
					.appendChild(prepareValueElement(baseDTO, indexSuffix));
			indexSuffix++;
		}

		return domainElement;
	}

	private static Node prepareValueElement(AbstractBaseDTO baseDTO,
			int indexSuffix) {
		Element valueElement = document
				.createElement(Constants.HmlNodeNames.VALUE);

		valueElement.setAttribute(Constants.HmlAttributeNames.IS,
				baseDTO.getName());
		valueElement.setAttribute(Constants.HmlAttributeNames.NUM,
				String.valueOf(indexSuffix));

		return valueElement;
	}

	private static Element prepareAttributesElement() {
		Element attributesElement = document
				.createElement(Constants.HmlNodeNames.ATTRIBUTES);

		attributesElement.appendChild(prepareAttributeElement(
				Constants.DtoSuffix.INFERENCE_INDEX,
				Constants.DtoNames.INFERENCE_NAME));
		attributesElement.appendChild(prepareAttributeElement(
				Constants.DtoSuffix.DATA_TYPE_INDEX,
				Constants.DtoNames.DATA_TYPE_NAME));
		attributesElement.appendChild(prepareAttributeElement(
				Constants.DtoSuffix.SENSOR_INDEX,
				Constants.DtoNames.SENSOR_NAME));

		return attributesElement;
	}

	private static Element prepareAttributeElement(int idSuffix, String name) {
		Element attributeElement = document
				.createElement(Constants.HmlNodeNames.ATTRIBUTE);

		attributeElement.setAttribute(Constants.HmlAttributeNames.ID,
				Constants.Additional.ATT_BASE + idSuffix);
		attributeElement.setAttribute(Constants.HmlAttributeNames.TYPE,
				Constants.Additional.TYPE_ID_BASE + idSuffix);
		attributeElement.setAttribute(Constants.HmlAttributeNames.NAME, name);
		attributeElement.setAttribute(Constants.HmlAttributeNames.CLASS,
				Constants.Additional.SIMPLE);
		attributeElement.setAttribute(Constants.HmlAttributeNames.COMM,
				Constants.Additional.IO);

		return attributeElement;
	}

	private static Element prepareXttElement(ConvertedClassDTO convertedClassDTO) {
		Element xttElement = document.createElement(Constants.HmlNodeNames.XTT);

		int suffix = 1;
		xttElement.appendChild(prepareTableElement(suffix,
				Constants.DtoSuffix.INFERENCE_INDEX,
				Constants.DtoSuffix.DATA_TYPE_INDEX,
				convertedClassDTO.getInferenceList()));
		suffix++;
		xttElement.appendChild(prepareTableElement(suffix,
				Constants.DtoSuffix.DATA_TYPE_INDEX,
				Constants.DtoSuffix.SENSOR_INDEX,
				convertedClassDTO.getDataTypeList()));

		return xttElement;
	}

	private static Element prepareTableElement(int tableSuffix,
			int preconditionSuffix, int conclusionSuffix,
			List<? extends AbstractBaseDTO> baseDtoList) {
		Element tableElement = document
				.createElement(Constants.HmlNodeNames.TABLE);

		tableElement.setAttribute(Constants.HmlAttributeNames.ID,
				Constants.Additional.TAB_ID_BASE + tableSuffix);
		tableElement.setAttribute(Constants.HmlAttributeNames.NAME,
				Constants.Additional.TAB_NAME_BASE + tableSuffix);

		tableElement.appendChild(prepareSchemaElement(preconditionSuffix,
				conclusionSuffix));

		int ruleSuffix = 1;
		if (preconditionSuffix == Constants.DtoSuffix.INFERENCE_INDEX) {
			List<InferenceDTO> inferenceList = (List<InferenceDTO>) baseDtoList;
			for (InferenceDTO inferenceDTO : inferenceList) {
				for (String dataTypeName : inferenceDTO.getDataTypeNames()) {
					tableElement.appendChild(prepareRuleElement(ruleSuffix,
							inferenceDTO.getName(),
							Constants.DtoSuffix.INFERENCE_INDEX,
							Constants.DtoSuffix.DATA_TYPE_INDEX, dataTypeName));
					ruleSuffix++;
				}
			}
			return tableElement;
		}

		if (preconditionSuffix == Constants.DtoSuffix.DATA_TYPE_INDEX) {
			List<DataTypeDTO> dataTypeList = (List<DataTypeDTO>) baseDtoList;
			for (DataTypeDTO dataTypeDTO : dataTypeList) {
				for (String sensorName : dataTypeDTO.getSensorNames()) {
					tableElement.appendChild(prepareRuleElement(ruleSuffix,
							dataTypeDTO.getName(),
							Constants.DtoSuffix.DATA_TYPE_INDEX,
							Constants.DtoSuffix.SENSOR_INDEX, sensorName));
					ruleSuffix++;
				}
			}
		}

		return tableElement;
	}

	private static Node prepareRuleElement(int ruleSuffix,
			String conditionalDtoName, int conditionSuffix, int decisionSuffix,
			String decisionDtoName) {
		Element ruleElement = document
				.createElement(Constants.HmlNodeNames.RULE);

		ruleElement.setAttribute(Constants.HmlAttributeNames.ID,
				Constants.Additional.RULE_ID_BASE + ruleSuffix);
		ruleElement.appendChild(prepareConditionElement(conditionSuffix,
				conditionalDtoName));
		ruleElement.appendChild(prepareDecisionElement(decisionSuffix,
				decisionDtoName));

		return ruleElement;
	}

	private static Element prepareDecisionElement(int decisionSuffix,
			String dtoName) {
		Element decisionElement = document
				.createElement(Constants.HmlNodeNames.DECISION);

		decisionElement
				.appendChild(prepareTransElement(decisionSuffix, dtoName));

		return decisionElement;
	}

	private static Element prepareTransElement(int decisionSuffix,
			String dtoName) {
		Element decisionElement = document
				.createElement(Constants.HmlNodeNames.TRANS);

		decisionElement.appendChild(prepareAttrefElement(decisionSuffix));
		decisionElement.appendChild(prepareSetElement(dtoName));

		return decisionElement;
	}

	private static Element prepareConditionElement(int conditionSuffix,
			String conditionDtoName) {
		Element conditionElement = document
				.createElement(Constants.HmlNodeNames.CONDITION);

		conditionElement.appendChild(prepareRelationElement(conditionSuffix,
				conditionDtoName));

		return conditionElement;
	}

	private static Element prepareRelationElement(int conditionSuffix,
			String conditionDtoName) {
		Element conditionElement = document
				.createElement(Constants.HmlNodeNames.RELATION);

		conditionElement.setAttribute(Constants.HmlAttributeNames.NAME,
				Constants.Additional.EQ);
		conditionElement.appendChild(prepareAttrefElement(conditionSuffix));
		conditionElement.appendChild(prepareSetElement(conditionDtoName));

		return conditionElement;
	}

	private static Element prepareSetElement(String dtoName) {
		Element setElement = document.createElement(Constants.HmlNodeNames.SET);

		setElement.appendChild(prepareValueElement(dtoName));

		return setElement;
	}

	private static Node prepareValueElement(String dtoName) {
		Element valueElement = document
				.createElement(Constants.HmlNodeNames.VALUE);

		valueElement.setAttribute(Constants.HmlAttributeNames.IS, dtoName);

		return valueElement;
	}

	private static Element prepareSchemaElement(int preconditionSuffix,
			int conclusionSuffix) {
		Element schemaElement = document
				.createElement(Constants.HmlNodeNames.SCHEMA);

		schemaElement
				.appendChild(preparePreconditionElement(preconditionSuffix));
		schemaElement.appendChild(prepareConclusionElement(conclusionSuffix));

		return schemaElement;
	}

	private static Element prepareConclusionElement(int suffix) {
		Element conclusionElement = document
				.createElement(Constants.HmlNodeNames.CONCLUSION);

		conclusionElement.appendChild(prepareAttrefElement(suffix));

		return conclusionElement;
	}

	private static Element prepareAttrefElement(int suffix) {
		Element attrefElement = document
				.createElement(Constants.HmlNodeNames.ATTREF);

		attrefElement.setAttribute(Constants.HmlAttributeNames.REF,
				Constants.Additional.ATT_BASE + suffix);

		return attrefElement;
	}

	private static Element preparePreconditionElement(int suffix) {
		Element preconditionElement = document
				.createElement(Constants.HmlNodeNames.PRECONDITION);

		preconditionElement.appendChild(prepareAttrefElement(suffix));

		return preconditionElement;
	}

}
