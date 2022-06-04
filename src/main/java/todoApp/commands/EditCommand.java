package todoApp.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import todoApp.util.CsvUtil;

public class EditCommand implements Command {
	
	private String commandName;
	private String fileName;
	private CsvUtil csvUtil;
	private String id;
	private List<String[]> fileData;
	
	public EditCommand(String fileName, String id) {
		this.commandName = "edit";
		this.fileName = fileName;
		this.csvUtil = new CsvUtil();
		this.id = id;
		this.fileData = new ArrayList<>();
	}

	@Override
	public void execute() {
		File file = new File(path + "\\" + fileName + ".csv");
		
		if(!file.exists()) {
			System.out.println("File doesnt exist!");
			return;
		}
		
		if(id.equals("-1")) {
			System.out.println("No task id provided!");
			return;
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String input = "";
			
			while((input = br.readLine()) != null) {
				String[] incomingDataFromFile = csvUtil.convertToArrayFormat(input);
				fileData.add(incomingDataFromFile);
			}
			
			for(String[] s : fileData) {
				if(s[0].equals(id)) {
					s[2] = "Completed";
				}
			}
			
			bw.close();
			BufferedWriter bw1 = new BufferedWriter(new FileWriter(file));
			for(String[] s : fileData) {
				bw1.write(csvUtil.convertToCsvFormat(s));
				bw1.newLine();
				bw1.flush();
			}
			br.close();
			bw1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return commandName;
	}
}
