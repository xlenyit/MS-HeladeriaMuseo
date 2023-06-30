package presentacion.command;

public abstract class CommandFactory {
	private static CommandFactory instance;

	public static synchronized CommandFactory getInstance() {
		if (instance == null)
			instance = new CommandFactoryImp();
		return instance;
	}

	public abstract CommandInterface getCommand(int e);
	
	public abstract int mostrarEvento (int e);

}
