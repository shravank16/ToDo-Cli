package todoApp.commands;

import java.io.File;
import java.util.stream.Stream;

public class ListCommand implements Command {
	
	private String commandName;
	
	public ListCommand() {
		this.commandName = "list";
	}

	@Override
	public void execute() {

		File directory = new File(path);
		if(!directory.exists()) {
			directory.mkdir();
		}
		
		File folder = new File(path);
		File[] allFiles = folder.listFiles();
		
		Stream.of(allFiles).forEach((f) -> System.out.println(f.getName()));
	}
	
	@Override
	public String getName() {
		return commandName;
	}
}
