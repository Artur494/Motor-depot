package by.grsu.by.datamodel;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Characteristics extends AbstractModel implements Serializable {
	private static List<String> cruisingRange = Arrays
			.asList(new String[] { "0 - 50", "0 - 100", "0 - 500", "0 - 1000", "0 - 5000" });
	
	private static List<String> bodyType = Arrays.asList(new String[] { "Sedan", "Estate", "Hatchback", "Minivan", "Suv",
			"Coupe", "Cabriolet", "Minibus", "Pickup", "Van" });

	public static List<String> getCruisingRange() {
		return cruisingRange;
	}

	public static List<String> getBodyType() {
		return bodyType;
	}

}
