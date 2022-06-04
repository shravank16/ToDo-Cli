package todoApp.commands;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements Command {
	
	private String commandName;
	private List<String> commandList;
	
	public HelpCommand() {
		this.commandName = "help";
		commandList = new ArrayList<>();
		commandList.add("help");
		commandList.add("new -filename, By default filename is date");
		commandList.add("list");
		commandList.add("edit -filename");
		commandList.add("view -filename");
	}
	
	@Override
	public void execute() {
		commandList.forEach((e) -> System.out.println(e));
	}

	@Override
	public String getName() {
		return commandName;
	}
}
