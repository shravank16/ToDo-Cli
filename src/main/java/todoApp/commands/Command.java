package todoApp.commands;

public interface Command {

	public static final String path = System.getenv("APPDATA") + "\\TODOCli\\";
	public void execute();
	public String getName();
}
