package cctools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ReadSaveSettings {
	private String settingsFilename = "AdminToolSettings.txt";
	private int numberOfSettings = 9;
	private String[] arrayOfReadSettings = { "", "", "", "", "", "", "180", "", "CC" };

	public void readSettings() throws IOException {
		File file = new File(settingsFilename);
		if (file.exists()) {
			FileReader fileReader = new FileReader(settingsFilename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

//			arrayOfReadSettings = new String[numberOfSettings];
			for (int i = 0; i < numberOfSettings; i++) {
				arrayOfReadSettings[i] = bufferedReader.readLine();
			}
			fileReader.close();
			// DEBUG: print settings to console
			for (int i = 0; i < numberOfSettings; i++) {
				System.out.println(arrayOfReadSettings[i]);
			}
		} else {
			// if the file doesn't exists open SettingsInterfaceWindow to get
			// settings input from the user
			JOptionPane.showMessageDialog(null,
					"Provide Admintool Links And Save", "Info",
					JOptionPane.WARNING_MESSAGE);
			new SettingsInterfaceWindow(this);
			System.out.println("file NOT exist");
		}
	}

	public String getSettingsFromArrayByIndex(int index) {
		return arrayOfReadSettings[index];
	}

	public void setSettingsToArrayByIndex(String value, int index) {
		this.arrayOfReadSettings[index] = value;
	}

	public void saveSettingsToFile() {
		File file = new File("AdminToolSettings.txt");
		System.out.println(file.getAbsolutePath());
		
		try {		
		if (file.exists()) {
			file.delete();
			file.createNewFile();
		} else {
			file.createNewFile();
		}
		} catch (IOException e) {
			System.out.println("Can't create new file: "
					+ e.toString());
			e.printStackTrace();
		}
			
			
		try {
			PrintWriter printWriter = new PrintWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
			for (int i = 0; i < arrayOfReadSettings.length; i++) {
				bufferedWriter.write(arrayOfReadSettings[i]);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();

		} catch (IOException e) {
			System.out.println("Can't write data to the file: "
					+ e.toString());
			e.printStackTrace();
		}
	}
}
