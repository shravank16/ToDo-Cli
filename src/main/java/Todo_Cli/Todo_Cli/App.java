package Todo_Cli.Todo_Cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
	
	static List<String> commandList = new ArrayList<>();
	
    public static void main(String[] args) throws IOException {
    	
    	// Reader to read data/todo items from the user
    	BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
    	
    	displayInformation();
    	
    	File currentFile = null;
    	String exitCommand = "exit";
    	String rawCommand = null;
    	String command = null;
    	
    	while(!(rawCommand = userReader.readLine()).equals(exitCommand)) {    		
    		commandList.clear();
    		processCommand(rawCommand);    		
    		command = commandList.get(0);
    		if(command.toLowerCase().equals("new") && commandList.size() == 1) {
    			currentFile = createNewTodoFile(userReader);
    			editTodoFile(userReader, currentFile);
    		}else if(command.toLowerCase().equals("new") && commandList.size() == 2) {
    			currentFile = createNewTodoFile(commandList.get(1));
    			editTodoFile(userReader, currentFile);
    		}else if(command.toLowerCase().equals("list")) {
    			listExistingTodoFiles();
    		}else if(command.toLowerCase().equals("edit") && commandList.size() == 1) {
    			System.out.println("Enter a file name with the command");    			
    		}else if(command.toLowerCase().equals("edit") && commandList.size() == 2) {
    			currentFile = getFile(commandList.get(1));
    			editTodoFile(userReader, currentFile);
    		}else if(command.toLowerCase().equals("help")) {
    			showHelp();
    		}else if(command.toLowerCase().equals("view") && commandList.size() == 1) {
    			System.out.println("Enter file name with the command");
    		}else if(command.toLowerCase().equals("view") && commandList.size() == 2) {
    			viewFile(commandList.get(1));
    		}else {
    			System.out.println("Invalid command!");
    		}
    	}
	}
    
    private static void viewFile(String name) throws IOException {
    	File file = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli\\" + name + ".txt");
    	
    	// File reader
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	
    	String input = null;
    	while((input = br.readLine()) != null) {
    		System.out.println(input);
    	}
    }
    
    private static File getFile(String name) {
    	File file = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli\\" + name + ".txt");
    	return file;
    }
    
    private static void processCommand(String rawCommand) {
    	String[] arr = rawCommand.split(" ");
    	
    	for(String s : arr) {
    		commandList.add(s);
    	}
    }
    
    // this method will print the initial information for the user to use
    // this application
    private static void displayInformation() {
    	
    	System.out.println("Welcome to the todo application");
    	
    	System.out.println("\nCreate a todo item by typing in the name!");
    	System.out.println("\nExit out of the application by using the exit command.");
    }
    
    private static File createNewTodoFile(BufferedReader inputReader) throws IOException {    	
    	String fileName = "";
    	fileName = inputReader.readLine();
    	// this file needs to exist or else filenotfoundexception will be thrown
    	File file = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli\\" + fileName + ".txt");
    	file.createNewFile();    	
    	return file;
    }
    
    private static File createNewTodoFile(String name) throws IOException {
    	File file = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli\\" + name + ".txt");
    	file.createNewFile();    	
    	return file;
    }
    
    private static void listExistingTodoFiles() {
    	File[] files = new File("C:\\Users\\Shravan\\Desktop\\Todo Cli").listFiles();
    	
    	if(files.length == 0) {
    		System.out.println("No Todo files");
    	}
    	
    	for(File f : files) {
    		System.out.println(f.getName());
    	}
    }
    
    private static void editTodoFile(BufferedReader inputReader, File file) throws IOException {
    	// File writer
    	BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
    	
    	System.out.println("Enter all the todo items!");
    	System.out.println("Enter exit once done!");
    	
    	String input = null;
    	
    	while(!(input = inputReader.readLine()).toLowerCase().equals("exit")) {
    		bw.write(input);
    		bw.newLine();
    		bw.flush();
    	}    	
    }
    
    private static void showHelp() {
    	System.out.println("List of commands : ");
    	String[] commands = new String[] {"new", "list", "edit -name", "view -name", "help", "exit"};
    	
    	for(String s : commands) {
    		System.out.println(s);
    	}
    }
}
