package todoApp.commands;

import java.util.List;

public class CommandExecutor {
	
	List<Command> commandList;
	
	public CommandExecutor(List<Command> commandList) {
		this.commandList = commandList;
	}

	public void executeCommand(String command) {
		for(Command c : commandList) {
			if(c.getName().equals(command)) {
				c.execute();
				break;
			}
		}
	}
}
