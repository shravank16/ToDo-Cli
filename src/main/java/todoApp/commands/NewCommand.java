package todoApp.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import todoApp.util.CsvUtil;

public class NewCommand implements Command {

	private String commandName;
	private String fileName;
	private CsvUtil csvUtil;
	private BufferedWriter bw;
	private int id;
	
	public NewCommand(String fileName) {
		this.commandName = "new";
		this.csvUtil = new CsvUtil();
		this.fileName = fileName;
		this.id = 0;
	}
	
	@Override
	public void execute() {
		File directory = new File(path);
		if(!directory.exists()) {
			directory.mkdir();
		}
		File file = new File(path + "\\" + fileName + ".csv");
		System.out.println(file.getAbsolutePath());
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		String status = "Pending";
		try {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return commandName;
	}
}
