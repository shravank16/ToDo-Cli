package todoApp.commands;

import java.io.File;

public class DeleteCommand implements Command {

	private String commandName;
	private String fileName;
	
	public DeleteCommand(String fileName) {
		this.commandName = "delete";
		this.fileName = fileName;
	}

	@Override
	public void execute() {
		File file = new File(path + "\\" + fileName + ".csv");
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("File deleted succesfully!");
			}else {
				System.out.println("Unable to delete file!");
			}
		}else {
			System.out.println("File doesnt exist!");
		}
	}

	@Override
	public String getName() {
		return commandName;
	}
}
