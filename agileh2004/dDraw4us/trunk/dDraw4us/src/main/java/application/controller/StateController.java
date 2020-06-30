package application.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import application.model.Canevas;

public class StateController {
	private static final String CONFIG_FILE_PATH = "src/main/resources/config.xml";
	private Canevas canevas;
	private CanevasController canevasController;
	private MenuUIController menuController;

	public StateController(CanevasController canevasController, MenuUIController menuController) {
		this.canevasController = canevasController;
		this.menuController = menuController;
		this.loadState();
	}

	public void saveState() {
		canevas = new Canevas(canevasController, menuController);
		try {
			canevas.getPrefs().exportNode(new FileOutputStream(CONFIG_FILE_PATH));

		} catch (IOException | BackingStoreException e) {
			e.printStackTrace(new PrintWriter(new StringWriter()));
		}
	}

	public void loadState() {
		Preferences preferences = Preferences.userNodeForPackage(Canevas.class);
		try {
			Preferences.importPreferences(new FileInputStream(CONFIG_FILE_PATH));
		} catch (Exception e) {
			this.saveState();
			try {
				Preferences.importPreferences(new FileInputStream(CONFIG_FILE_PATH));
			} catch (Exception e1) {
				// Show Exception Dialog
				e.printStackTrace(new PrintWriter(new StringWriter()));
			}
		}

		canevas = new Canevas(preferences);
		canevasController.updateScreenState(canevas);
	}
}
