package todoApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import todoApp.commands.AddCommand;
import todoApp.commands.Command;
import todoApp.commands.CommandExecutor;
import todoApp.commands.DeleteCommand;
import todoApp.commands.EditCommand;
import todoApp.commands.HelpCommand;
import todoApp.commands.ListCommand;
import todoApp.commands.NewCommand;
import todoApp.commands.ViewCommand;

public class Application {
	
	public static void main(String[] args) {
		List<String> commandList = new ArrayList<>();
		
		Stream.of(args).forEach((e) -> commandList.add(e));
		
		if(commandList.size() == 0 || commandList.isEmpty() || commandList.size() > 3) {
			System.out.println("Invalid command");
			return;
		}
		
		String fileName = (commandList.size() == 2) ? commandList.get(1) : "shravan";
		String type = commandList.size() == 3 ? commandList.get(2) : "all";
		String id = commandList.size() == 3 ? commandList.get(2) : "-1";
		
		Command newCommand = new NewCommand(fileName);
		Command helpCommand = new HelpCommand();
		Command editCommand = new EditCommand(fileName, id);
		Command listCommand = new ListCommand();
		Command viewCommand = new ViewCommand(fileName, type);
		Command deleteCommand = new DeleteCommand(fileName);
		Command addCommand = new AddCommand(fileName);
		
		List<Command> commands = Arrays.asList(newCommand, helpCommand, editCommand, listCommand,
											   viewCommand, deleteCommand, addCommand);
		
		CommandExecutor commandExecutor = new CommandExecutor(commands);
		
		commandExecutor.executeCommand(commandList.get(0));
	}
}
