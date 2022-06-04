package Todo_Cli.Todo_Cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvFile {
	
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli\\csvFile.csv");
		file.createNewFile();
		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));		
		
		String[] data = {"wake up tomorrow at 9 AM", "pending"};
		String[] data1 = {"send email to friend", "complete"};
		String[] data2 = {"setup dental appointment", "pending"};
		
		bw.write(csvDataFormatter(data));
		bw.newLine();
		bw.write(csvDataFormatter(data1));
		bw.newLine();
		bw.write(csvDataFormatter(data2));
		bw.flush();
		
		String fileData = "";
		while((fileData = br.readLine()) != null) {
			String[] test = dataToStringArray(fileData);

			if(test[1].equals("complete")) {
				System.out.println(test[0]);
			}
		}
	}
	
	private static String csvDataFormatter(String[] data) {
		String s = Stream.of(data).collect(Collectors.joining(","));
		return s;
	}
	
	private static String[] dataToStringArray(String s) {
		return Arrays.stream(s.split(",")).toArray(String[]::new);
	}
}
