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
	
	public interface HmlNodeNames{
		public static final String HML = "hml";
		public static final String TYPES = "types";
		public static final String ATTRIBUTES = "attributes";
		public static final String XTT = "xtt";
		public static final String TYPE = "type";
		public static final String DOMAIN = "domain";
		public static final String VALUE = "value";
		public static final String ATTRIBUTE = "attribute";
	}
	
	public interface HmlAttributeNames{
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String BASE = "base";
		public static final String IS = "is";
		public static final String NUM = "num";
		public static final String TYPE = "type";
		public static final String CLASS = "class";
		public static final String COMM = "comm";
	}
	
	public interface Additional{
		public static final String TYPE_ID_BASE = "tpe_";
		public static final String ATT_BASE = "att_";
		public static final String SYMBOLIC = "symbolic";
		public static final String SIMPLE = "simple";
		public static final String IO = "io";
	}
	
	public interface DtoSuffix{
		public static final int INFERENCE_INDEX = 1;
		public static final int DATA_TYPE_INDEX = 2;
		public static final int SENSOR_INDEX = 3;
	}
	
	public interface DtoNames{
		public static final String INFERENCE_NAME = "Inference";
		public static final String DATA_TYPE_NAME = "DataType";
		public static final String SENSOR_NAME = "Sensor";
	}
}
