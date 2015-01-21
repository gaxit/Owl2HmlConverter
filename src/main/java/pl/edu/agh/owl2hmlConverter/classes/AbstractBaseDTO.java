package pl.edu.agh.owl2hmlConverter.classes;

/**
 * Abstrakcyjna klasa służąca jako bazowa dla obiektów pośrednich konwersji -
 * DTO Sensor, Inference i DataType
 */
public abstract class AbstractBaseDTO {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
