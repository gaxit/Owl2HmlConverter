package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowująca informacje o wszystkich obiektach pośrednich
 */
public class ConvertedClassDTO {
	
	private List<InferenceDTO> inferenceList;
	private List<DataTypeDTO> dataTypeList;
	private List<SensorDTO> sensorList;
	
	public ConvertedClassDTO(){
		inferenceList = new ArrayList<InferenceDTO>();
		dataTypeList = new ArrayList<DataTypeDTO>();
		sensorList = new ArrayList<SensorDTO>();
	}
	
	/**
	 * Dodaje obiekt klasy InferenceDTO do listy
	 * 
	 * @param inference Obiekt klasy InferenceDTO
	 */
	public void addInference(InferenceDTO inference){
		inferenceList.add(inference);
	}
	
	/**
	 * Dodaje obiekt klasy DataTypeDTO do listy
	 * 
	 * @param dataType Obiekt klasy DataTypeDTO
	 */
	public void addDataType(DataTypeDTO dataType){
		dataTypeList.add(dataType);
	}
	
	/**
	 * Dodaje obiekt klasy SensorDTO do listy
	 * 
	 * @param sensor Obiekt klasy SensorDTO
	 */
	public void addSensor(SensorDTO sensor){
		sensorList.add(sensor);
	}

	public List<InferenceDTO> getInferenceList() {
		return inferenceList;
	}

	public List<DataTypeDTO> getDataTypeList() {
		return dataTypeList;
	}

	public List<SensorDTO> getSensorList() {
		return sensorList;
	}

}
