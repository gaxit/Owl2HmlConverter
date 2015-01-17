package pl.edu.agh.owl2hmlConverter.classes;

import java.util.ArrayList;
import java.util.List;

public class InferenceDTO extends AbstractBaseDTO {
	
	private List<String> dataTypeNames;
	
	public InferenceDTO(){
		dataTypeNames = new ArrayList<String>();
	}
	
	public void addName(String dataTypeName){
		dataTypeNames.add(dataTypeName);
	}
	
	public List<String> getDataTypeNames(){
		return dataTypeNames;
	}

}
