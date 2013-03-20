package cctools;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Engine implements ActionListener {
	private ProgramInterfaceWindow programInterfaceWindow;
	private ReadSaveSettings readSaveSettings;
	private SeleniumScriptTools tools;

	Engine(SeleniumScriptTools tools, ReadSaveSettings readSaveSettings,
			ProgramInterfaceWindow programInterfaceWindow) {
		this.tools = tools;
		this.readSaveSettings = readSaveSettings;
		this.programInterfaceWindow = programInterfaceWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clickedButton = (JButton) e.getSource();
		programInterfaceWindow.addIDToComboBoxModel();

		if (clickedButton == programInterfaceWindow.getSettingsButton()) {
			System.out.println("settings choosen");
			new SettingsInterfaceWindow(readSaveSettings, programInterfaceWindow);
			//temp
//			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
//				tools.syndicateBattle(getChoosenUserIDFromComboBox(),
//						getChoosenServerLinkFromSettings());
//			}		
			//temp

		} else if (clickedButton == programInterfaceWindow.getDdmsPathButton()) {
			System.out.println("ddms selected");
			String dDMSpath = readSaveSettings.getSettingsFromArrayByIndex(7);
			launchDDMSprogramm(dDMSpath);

		} else if (clickedButton == programInterfaceWindow
				.getOpenUserPageButton()) {
			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
				tools.openPage(getChoosenUserIDFromComboBox(),
						getChoosenServerLinkFromSettings());
			}
		} else if (clickedButton == programInterfaceWindow
				.getDropAllItemsButton()) {
			int answer;
			answer = JOptionPane.showConfirmDialog(
					programInterfaceWindow.getFrame(),
					"Do you really want TO DROP ALL user's items?", "Info",
					JOptionPane.WARNING_MESSAGE);
			if (answer == 0) {
				if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
					tools.dropAll(getChoosenUserIDFromComboBox(),
							getChoosenServerLinkFromSettings());
				}
			}
		} else if (clickedButton == programInterfaceWindow.getResetButton()) {
			int answer;
			answer = JOptionPane.showConfirmDialog(
					programInterfaceWindow.getFrame(),
					"Do you really want TO RESET the USER?", "Info",
					JOptionPane.WARNING_MESSAGE);
			if (answer == 0) {
				if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
					tools.reset(getChoosenUserIDFromComboBox(),
							getChoosenServerLinkFromSettings());
				}
			}
		} else if (clickedButton == programInterfaceWindow
				.getAddResourcesButton()) {
			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
				tools.addRes(getChoosenUserIDFromComboBox(),
						getChoosenServerLinkFromSettings());
			}
		} else if (clickedButton == programInterfaceWindow.getNewItemsButton()) {
			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
		// if checkbox MyStrongestItems is checked than executing different method from Tools class  
				if (programInterfaceWindow.isCheckStrongestChecked()) {
					tools.newDefinedItems(getChoosenUserIDFromComboBox(),
							getChoosenServerLinkFromSettings());
				} else {
					tools.newItems(getChoosenUserIDFromComboBox(),
							getChoosenServerLinkFromSettings());
				}
			}
		} else if (clickedButton == programInterfaceWindow.getPVPbattleButton()) {
			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
				tools.pvpBattle(getChoosenUserIDFromComboBox(),
						getChoosenServerLinkFromSettings());
			}
		} else if (clickedButton == programInterfaceWindow
				.getUpgradeBuildingsButton()) {
			if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
				tools.upgradeBuilding(getChoosenUserIDFromComboBox(),
						getChoosenServerLinkFromSettings());
			}
		}
		 else if (clickedButton == programInterfaceWindow.getSyndBattleButton()) {
				if (userIDnotEmptyCheck(getChoosenUserIDFromComboBox())) {
					tools.syndicateBattle(getChoosenUserIDFromComboBox(),
							getChoosenServerLinkFromSettings());
				}
		 }
	}

	private String getChoosenUserIDFromComboBox() {
		String id = null;
		char n = programInterfaceWindow.getUserToggleButtonState();
		switch (n) {
		case 'A':
			id = programInterfaceWindow.getTextField1();
			break;
		case 'B':
			id = programInterfaceWindow.getTextField2();
			break;
		}
		return id;
	}

	private String getChoosenServerLinkFromSettings() {
		String server = null;
		char n = programInterfaceWindow.getServerToggleButtonState();

		switch (n) {
		case 'A':
			server = readSaveSettings.getSettingsFromArrayByIndex(0);
			break;
		case 'B':
			server = readSaveSettings.getSettingsFromArrayByIndex(1);
			break;
		case 'C':
			server = readSaveSettings.getSettingsFromArrayByIndex(2);
			break;
		case 'D':
			server = readSaveSettings.getSettingsFromArrayByIndex(3);
			break;
		}
		System.out.println(server);
		return server;
	}

	private void launchDDMSprogramm(String dDMSpath) {
		try {
			File ddmsFile = new File(dDMSpath);
			if (ddmsFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(ddmsFile);
				} else {
					JOptionPane.showMessageDialog(
							programInterfaceWindow.getFrame(),
							"Awt Desktop is not supported!", "Info",
							JOptionPane.WARNING_MESSAGE);
				}

			} else {
				System.out.println("File is not exists!");
				JOptionPane
						.showMessageDialog(
								programInterfaceWindow.getFrame(),
								"File is not exists! Provide correct path in settings!",
								"Info", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	private boolean userIDnotEmptyCheck(String userID) {
		boolean x = false;
		if (userID.equals(null) || userID.length() == 0) {
			JOptionPane.showMessageDialog(programInterfaceWindow.getFrame(),
					"Choose the User and provide USER ID", "Info",
					JOptionPane.WARNING_MESSAGE);
		} else {
			x = true;
		}
		return x;
	}
}
