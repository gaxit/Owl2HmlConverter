package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

public class DataTypeDTO extends AbstractBaseDTO {
	
	private List<String> sensorNames;
	
	public DataTypeDTO(){
		sensorNames = new ArrayList<String>();
	}
	
	public void addSensorName(String sensorName){
		sensorNames.add(sensorName);
	}
	
	public List<String> getSensorNames(){
		return sensorNames;
	}

}
