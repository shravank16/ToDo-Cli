package todoApp.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvUtil {

	public CsvUtil() {
		
	}
	
	public String convertToCsvFormat(String[] data) {
		return Stream.of(data).collect(Collectors.joining(","));
	}
	
	public String[] convertToArrayFormat(String data) {
		return Arrays.stream(data.split(",")).toArray(String[]::new);
	}
}
