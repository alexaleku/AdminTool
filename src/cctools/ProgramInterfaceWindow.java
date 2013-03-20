package cctools;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ProgramInterfaceWindow {
	private JComboBox<String> userIDComboBox1, userIDComboBox2;
	private JToggleButton user1, user2;
	private JToggleButton serverTB1, serverTB2, serverTB3, serverTB4;
	private JLabel labelUser1, labelUser2;
	private JButton opUsPg, sett, addRes, newItems, reset, dropAll, pVP, upgrB,
			ddms, syndBattle;
	private JCheckBox checkStrongest, chkboxGold;
	private JFrame frame;
	private String[] usersIDs = { "                   " };
	private DefaultComboBoxModel<String> model1;
	private DefaultComboBoxModel<String> model2;

	ProgramInterfaceWindow(ReadSaveSettings readSaveSettings) {

		JPanel windowContent = new JPanel();

		FlowLayout flowLayout = new FlowLayout(1, 5, 10);
		windowContent.setLayout(flowLayout);

		labelUser1 = new JLabel("User1 ID:");

		// Creating ComboBoxes and setting AutoCompleate to them
		model1 = new DefaultComboBoxModel<String>(usersIDs);
		userIDComboBox1 = new JComboBox<String>(model1);
		userIDComboBox1.setEditable(true);
		AutoCompleteDecorator.decorate(userIDComboBox1);

		labelUser2 = new JLabel("User2 ID:");

		model2 = new DefaultComboBoxModel<String>(usersIDs);
		userIDComboBox2 = new JComboBox<String>(model2);
		userIDComboBox2.setEditable(true);
		AutoCompleteDecorator.decorate(userIDComboBox2);

		// Adding toggle buttons
		user1 = new JToggleButton("User1", true);
		user2 = new JToggleButton("User2");
		ButtonGroup groupUs = new ButtonGroup();
		groupUs.add(user1);
		groupUs.add(user2);

		serverTB1 = new JToggleButton("PROD", true);
		serverTB2 = new JToggleButton("QA-001");
		serverTB3 = new JToggleButton("QA-002");
		serverTB4 = new JToggleButton("QA-003");
		ButtonGroup group = new ButtonGroup();
		group.add(serverTB1);
		group.add(serverTB2);
		group.add(serverTB3);
		group.add(serverTB4);

		JLabel label4 = new JLabel("MyItems");
		checkStrongest = new JCheckBox();

		JLabel label5 = new JLabel("More");
		chkboxGold = new JCheckBox();

		opUsPg = new JButton("OpenPage");
		sett = new JButton("Settings");
		addRes = new JButton("AddResources");
		newItems = new JButton("AddNewItems");
		reset = new JButton("Reset");
		dropAll = new JButton("DropAllItems");
		pVP = new JButton("PVPbattle");
		upgrB = new JButton("UpgradeBuils");
		ddms = new JButton("DDMS");
		syndBattle = new JButton("SyndBattle");

		JSeparator xA, xB, xC, xD;
		windowContent.add(Box.createHorizontalStrut(3));

		windowContent.add(sett);

		xA = new JSeparator(SwingConstants.VERTICAL);
		xA.setPreferredSize(new Dimension(2, 25));
		windowContent.add(Box.createHorizontalStrut(3));
		windowContent.add(xA);
		windowContent.add(Box.createHorizontalStrut(3));

		windowContent.add(labelUser1);
		windowContent.add(userIDComboBox1);
		windowContent.add(user1);

		windowContent.add(Box.createHorizontalStrut(3));

		windowContent.add(labelUser2);
		windowContent.add(userIDComboBox2);
		windowContent.add(user2);

		xB = new JSeparator(SwingConstants.VERTICAL);
		xB.setPreferredSize(new Dimension(2, 25));
		windowContent.add(Box.createHorizontalStrut(7));
		windowContent.add(xB);
		windowContent.add(Box.createHorizontalStrut(7));

		windowContent.add(serverTB1);
		windowContent.add(serverTB2);
		windowContent.add(serverTB3);
		windowContent.add(serverTB4);

		xC = new JSeparator(SwingConstants.VERTICAL);
		xC.setPreferredSize(new Dimension(2, 25));
		windowContent.add(Box.createHorizontalStrut(12));
		windowContent.add(reset);
		
		windowContent.add(Box.createHorizontalStrut(20));
		windowContent.add(xC);
		windowContent.add(Box.createHorizontalStrut(3));
		
		
		windowContent.add(opUsPg);

		windowContent.add(Box.createHorizontalStrut(12));
		windowContent.add(label5);
		windowContent.add(chkboxGold);
		windowContent.add(addRes);

		windowContent.add(label4);
		windowContent.add(checkStrongest);

		windowContent.add(newItems);
		windowContent.add(dropAll);
		windowContent.add(pVP);
		windowContent.add(upgrB);
		windowContent.add(syndBattle);
//		windowContent.add(Box.createHorizontalStrut(7));

		xD = new JSeparator(SwingConstants.VERTICAL);
		xD.setPreferredSize(new Dimension(2, 25));
		windowContent.add(Box.createHorizontalStrut(3));
		windowContent.add(xD);
		windowContent.add(Box.createHorizontalStrut(3));

		windowContent.add(ddms);

		frame = new JFrame("Admin");
		frame.setContentPane(windowContent);
		frame.setSize(1230, 110);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Creating SeleniumScriptTools and Engine objects
		SeleniumScriptTools seleniumScriptTools = new SeleniumScriptTools(this,
				readSaveSettings);

		Engine engine = new Engine(seleniumScriptTools, readSaveSettings, this);

		opUsPg.addActionListener(engine);
		addRes.addActionListener(engine);
		newItems.addActionListener(engine);
		reset.addActionListener(engine);
		sett.addActionListener(engine);
		dropAll.addActionListener(engine);
		pVP.addActionListener(engine);
		upgrB.addActionListener(engine);
		ddms.addActionListener(engine);
		syndBattle.addActionListener(engine);
	}

	// get text from text fields (combo boxes)
	public String getTextField1() {
		String textQA1 = (String) userIDComboBox1.getSelectedItem();
		return textQA1;
	}

	public String getTextField2() {
		String textQA2 = (String) userIDComboBox2.getSelectedItem();
		return textQA2;
	}

	// get frame to center all program dialogs to it
	public JFrame getFrame() {
		return frame;
	}

	// get ComboBoxes to set CopyPasteDropDownList
	public JComboBox<String> getComboBox1() {
		return userIDComboBox1;
	}

	public JComboBox<String> getComboBox2() {
		return userIDComboBox2;
	}

	// get Toggle Buttons states
	public char getUserToggleButtonState() {
		if (user1.isSelected()) {
			System.out.println("us1 button selected");
			return 'A';
		} else {
			System.out.println("us2 button selected");
			return 'B';
		}
	}

	public char getServerToggleButtonState() {
		if (serverTB1.isSelected()) {
			System.out.println("tb1 button selected");
			return 'A';
		} else if (serverTB2.isSelected()) {
			System.out.println("tb2 button selected");
			return 'B';
		} else if (serverTB3.isSelected()) {
			System.out.println("tb3 button selected");
			return 'C';
		} else {
			System.out.println("tb4 button selected");
			return 'D';
		}
	}

	// set user names to lables
	public void setTextUserLabel1(String userName) {
		labelUser1.setText(userName);
	}

	public void setTextUserLabel2(String userName) {
		labelUser2.setText(userName);
	}

	// get Checkboxes states
	public boolean isChkboxGoldSateChecked() {
		boolean answer = false;
		if (chkboxGold.isSelected()) {
			answer = true;
		}
		return answer;
	}

	public boolean isCheckStrongestChecked() {
		boolean answer = false;
		if (checkStrongest.isSelected()) {
			answer = true;
		}
		return answer;
	}

	// Tools buttons GETTERS
	public JButton getOpenUserPageButton() {
		return opUsPg;
	}

	public JButton getSettingsButton() {
		return sett;
	}

	public JButton getAddResourcesButton() {
		return addRes;
	}

	public JButton getNewItemsButton() {
		return newItems;
	}

	public JButton getResetButton() {
		return reset;
	}

	public JButton getDropAllItemsButton() {
		return dropAll;
	}

	public JButton getPVPbattleButton() {
		return pVP;
	}

	public JButton getUpgradeBuildingsButton() {
		return upgrB;
	}

	public JButton getDdmsPathButton() {
		return ddms;
	}
	public JButton getSyndBattleButton() {
		return syndBattle;
	}

	// adding new ID to ComboBoxes DropDown List
	public void addIDToComboBoxModel() {
		String idFromTextField;
		if (getUserToggleButtonState() == 'A') {
			idFromTextField = (String) userIDComboBox1.getSelectedItem();
		} else {
			idFromTextField = (String) userIDComboBox2.getSelectedItem();
		}
		if (model1.getIndexOf(idFromTextField) == -1) {
			model1.addElement(idFromTextField.trim());
			model2.addElement(idFromTextField.trim());
		}
		System.out.println(idFromTextField);
	}
}
