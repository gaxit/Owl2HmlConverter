package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

public class ConvertedClassDTO {
	
	private List<InferenceDTO> inferenceList;
	private List<DataTypeDTO> dataTypeList;
	private List<SensorDTO> sensorList;
	
	public ConvertedClassDTO(){
		inferenceList = new ArrayList<InferenceDTO>();
		dataTypeList = new ArrayList<DataTypeDTO>();
		sensorList = new ArrayList<SensorDTO>();
	}
	
	public void addInference(InferenceDTO inference){
		inferenceList.add(inference);
	}
	
	public void addDataType(DataTypeDTO dataType){
		dataTypeList.add(dataType);
	}
	
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
