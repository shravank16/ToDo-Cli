package todoApp.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import todoApp.util.CsvUtil;

public class AddCommand implements Command {

	private String commandName;
	private String fileName;
	private CsvUtil csvUtil;
	private int id;
	
	public AddCommand(String fileName) {
		this.commandName = "add";
		this.fileName = fileName;
		this.csvUtil = new CsvUtil();
		this.id = 0;
	}
	
	@Override
	public void execute() {
		File file = new File(path + "\\" + fileName + ".csv");
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader br1 = new BufferedReader(new FileReader(file));
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			
			String inData = "";
			
			String[] strArray = null;
			while((inData = br1.readLine()) != null) {
				strArray = csvUtil.convertToArrayFormat(inData);
			}
			
			id = Integer.parseInt(strArray[0]) + 1;
			
			String input = "";
			String status = "Pending";
			while(!(input = br.readLine()).equals("exit")) {
				String[] data = new String[4];
				data[0] = String.valueOf(id++);
				data[1] = input;
				data[2] = status;
				data[3] = new Date().toString();
				bw.write(csvUtil.convertToCsvFormat(data));
				bw.newLine();
				bw.flush();				
			}
			br.close();
			bw.close();
			br1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		return this.commandName;
	}
}
