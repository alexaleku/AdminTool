package cctools;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsInterfaceWindow implements ActionListener {
	private JFrame frameSettingsWindow;
	private JButton save, ddmsPath;
	private ReadSaveSettings readSaveSettings;
	private JTextField lvl;
	private JCheckBox chkbox;
	// next fields arenot private because class CopyPasteDropdownList need access to them - could make getters for them
	JTextField linkProd, linkQA1, linkQA2, linkQA3;
	JTextField f1, f2;

	SettingsInterfaceWindow(ReadSaveSettings readSettings, ProgramInterfaceWindow programInterfaceWindow) {
		this.readSaveSettings = readSettings;

		JPanel windowContent = new JPanel();
		FlowLayout fn = new FlowLayout();
		windowContent.setLayout(fn);

		JLabel label3 = new JLabel("PROD");
		linkProd = new JTextField(readSettings.getSettingsFromArrayByIndex(0), 30);
		JLabel label4 = new JLabel("QA-001");
		linkQA1 = new JTextField(readSettings.getSettingsFromArrayByIndex(1), 30);
		JLabel label5 = new JLabel("QA-002");
		linkQA2 = new JTextField(readSettings.getSettingsFromArrayByIndex(2), 30);
		JLabel label6 = new JLabel("QA-003");
		linkQA3 = new JTextField(readSettings.getSettingsFromArrayByIndex(3), 30);

		JLabel label1 = new JLabel("Login:");
		f1 = new JTextField(7);
		JLabel label2 = new JLabel("Pass:");
		f2 = new JTextField(7);
		JLabel label7 = new JLabel("Lvl");
		lvl = new JTextField(readSettings.getSettingsFromArrayByIndex(6));

		JLabel label8 = new JLabel("MW");
		chkbox = new JCheckBox();
		if (readSettings.getSettingsFromArrayByIndex(8).equals("MW")) {
			chkbox.setSelected(true);
		}

		save = new JButton("Save");
		ddmsPath = new JButton("DDMS Path");

		JButton setSavePath = new JButton("Set Settings Path");
		setSavePath.setName("setSavePath");

		windowContent.add(label3);
		windowContent.add(linkProd);
		windowContent.add(label4);
		windowContent.add(linkQA1);
		windowContent.add(label5);
		windowContent.add(linkQA2);
		windowContent.add(label6);
		windowContent.add(linkQA3);

		windowContent.add(label1);
		windowContent.add(f1);
		windowContent.add(label2);
		windowContent.add(f2);

		windowContent.add(label7);
		windowContent.add(lvl);

		windowContent.add(label8);
		windowContent.add(chkbox);

		windowContent.add(ddmsPath);

		ddmsPath.addActionListener(this);

		windowContent.add(save);

		frameSettingsWindow = new JFrame("Set tool links and SAVE");
		frameSettingsWindow.setContentPane(windowContent);
		frameSettingsWindow.setSize(435, 240);
		if (programInterfaceWindow != null) {
			frameSettingsWindow.setLocationRelativeTo(programInterfaceWindow.getFrame());
		}
		frameSettingsWindow.setVisible(true);

		save.addActionListener(this);
		new CopyPasteDropDownList(this);
	}

	SettingsInterfaceWindow(ReadSaveSettings readSettings) {
		this(readSettings, null);
	}

	public void actionPerformed(ActionEvent e) {
		JButton tBtn = (JButton) e.getSource();

		if (tBtn == ddmsPath) {
			System.out.println("ddmsPath selected");
			setDDMSpath();
		} else if (tBtn == save) {
			System.out.println("SAVE selected");
			getNewSettingsAndSave();
		}
	}

	private void setDDMSpath() {
		JFileChooser fileopen = new JFileChooser();
		int ret = fileopen.showDialog(frameSettingsWindow, "Get DDMS path");
		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fileopen.getSelectedFile();
			readSaveSettings.setSettingsToArrayByIndex(file.getAbsolutePath(), 7);
		}
	}

	private void getNewSettingsAndSave() {
		int n = 7;
		String[] links = new String[n];

		links[0] = linkProd.getText();
		links[1] = linkQA1.getText();
		links[2] = linkQA2.getText();
		links[3] = linkQA3.getText();
		links[4] = f1.getText();
		links[5] = f2.getText();
		links[6] = lvl.getText();

		for (int i = 0; i < links.length; i++) {
			if (!links[i].equals("")) {
				readSaveSettings.setSettingsToArrayByIndex(links[i], i);
			}
		}

		if (chkbox.isSelected()) {
			readSaveSettings.setSettingsToArrayByIndex("MW", 8);
			} else {
			readSaveSettings.setSettingsToArrayByIndex("CC", 8);
		}

		readSaveSettings.saveSettingsToFile();
		frameSettingsWindow.dispose();
	}


}
