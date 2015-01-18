package pl.edu.agh.owl2hmlConverter.converters;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import pl.edu.agh.owl2hmlConverter.Constants;
import pl.edu.agh.owl2hmlConverter.classes.AbstractBaseDTO;
import pl.edu.agh.owl2hmlConverter.classes.ConvertedClassDTO;
import pl.edu.agh.owl2hmlConverter.utils.DocumentUtils;

public class ConvertedClassDTO2HmlConverter {

	private static Document document;

	public static Element convert(ConvertedClassDTO convertedClassDTO)
			throws ParserConfigurationException {
		document = DocumentUtils.getDocument();
		Element rootElement = document
				.createElement(Constants.HmlNodeNames.HML);

		// rootElement.appendChild(prepareTypesElement(convertedClassDTO));
		// rootElement.appendChild(prepareAttributesElement());
		rootElement.appendChild(prepareXttElement());

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

	private static Element prepareXttElement() {
		Element typesElement = document
				.createElement(Constants.HmlNodeNames.XTT);

		return typesElement;
	}

}
