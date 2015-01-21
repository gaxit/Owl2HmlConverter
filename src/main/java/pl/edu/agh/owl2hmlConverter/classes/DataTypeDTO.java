package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowująca informacje o obiekcie DataType
 */
public class DataTypeDTO extends AbstractBaseDTO {

	private List<String> sensorNames;

	public DataTypeDTO() {
		sensorNames = new ArrayList<String>();
	}

	/**
	 * Dodaje nazwę sensora do listy
	 * 
	 * @param sensorName Nazwa sensora
	 */
	public void addSensorName(String sensorName) {
		sensorNames.add(sensorName);
	}

	public List<String> getSensorNames() {
		return sensorNames;
	}

}
