package cctools;

import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Main main = new Main();
		main.launch();
	}

	private void launch() {
		ReadSaveSettings readSaveSettings = new ReadSaveSettings();
		ProgramInterfaceWindow programInterfaceWindow = new ProgramInterfaceWindow(readSaveSettings);
		new CopyPasteDropDownList(programInterfaceWindow);
		try {
			readSaveSettings.readSettings();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(programInterfaceWindow.getFrame(),
					"Something wrong! Can't read settings", "Info",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
