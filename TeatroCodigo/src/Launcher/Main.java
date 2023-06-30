package Launcher;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import presentación.VistaMenu;

public class Main {

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				@Override
				public void run() {
					new VistaMenu();
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
