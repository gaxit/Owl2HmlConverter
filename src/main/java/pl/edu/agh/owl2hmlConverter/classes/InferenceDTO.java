package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowująca informacje o obiekcie Inference
 */
public class InferenceDTO extends AbstractBaseDTO {

	private List<String> dataTypeNames;

	public InferenceDTO() {
		dataTypeNames = new ArrayList<String>();
	}

	/**
	 * Dodaje nazwę typu danych do listy
	 * 
	 * @param dataTypeName Nazwa typu danych
	 */
	public void addDataTypeName(String dataTypeName) {
		dataTypeNames.add(dataTypeName);
	}

	public List<String> getDataTypeNames() {
		return dataTypeNames;
	}

}
