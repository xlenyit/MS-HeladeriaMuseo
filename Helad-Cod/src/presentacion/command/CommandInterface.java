package presentacion.command;

public abstract interface CommandInterface {
	public abstract Contexto execute(Object o);
}
