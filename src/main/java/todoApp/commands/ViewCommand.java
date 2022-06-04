package todoApp.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import todoApp.util.CsvUtil;

public class ViewCommand implements Command {
	
	private String commandName;
	private String fileName;
	private String viewType;
	private CsvUtil csvUtil;
	private BufferedReader br;
	private String[] currentRead;
	
	public ViewCommand(String fileName) {
		this.commandName = "view";
		this.fileName = fileName;
		this.csvUtil = new CsvUtil();
	}
	
	public ViewCommand(String fileName, String viewType) {
		this.commandName = "view";
		this.fileName = fileName;
		this.viewType = viewType;
		this.csvUtil = new CsvUtil();
	}

	@Override
	public void execute() {
		File file = new File(path + "\\" + fileName + ".csv");
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		if(!file.exists()) {
			System.out.println("File doesnt exist!");
		}
		
		String input = null;
		try {
			while((input = br.readLine()) != null) {
				currentRead = csvUtil.convertToArrayFormat(input);
				if(viewType.equals("all")) {
					System.out.println(Arrays.toString(currentRead));
				}else if(viewType.equals("pending") && currentRead[1].toLowerCase().equals("pending")) {
					System.out.println(currentRead[0] + " " + currentRead[2]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return commandName;
	}
}
