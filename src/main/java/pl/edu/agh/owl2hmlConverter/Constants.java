package pl.edu.agh.owl2hmlConverter;

public class Constants {
	
	public interface OwlNodeNames{
		public static final String EMPTY_NODE = "#text";
		public static final String SUBCLASS_OF = "SubClassOf";
		public static final String DECLARATION = "Declaration";
		public static final String PREFIX = "Prefix";
		public static final String CLASS = "Class";
		public static final String OBJECT_SOME_VALUE_FROM = "ObjectSomeValuesFrom";
	}
	
	public interface HmlNodeTypes{
		public static final String DATA_TYPE = "#DataType";
		public static final String SENSOR = "#Sensor";
		public static final String INFERENCE = "#Inference";
	}
	
	public interface AttributeNames{
		public static final String IRI = "IRI";
	}
}
