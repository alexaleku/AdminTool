package cctools;

import java.awt.event.*;
import javax.swing.*;

public class CopyPasteDropDownList implements ActionListener {
//faverv
	final JPopupMenu cutpasteMenu = new JPopupMenu();
	JMenuItem cutMenuItem = new JMenuItem("Cut");
	JMenuItem copyMenuItem = new JMenuItem("Copy");
	JMenuItem pasteMenuItem = new JMenuItem("Paste");
// j7777777777
	public CopyPasteDropDownList(ProgramInterfaceWindow programInterfaceWindow) {
		cutMenuItem.addActionListener(this);
		copyMenuItem.addActionListener(this);
		pasteMenuItem.addActionListener(this);

		cutpasteMenu.add(cutMenuItem);
		cutpasteMenu.add(copyMenuItem);
		cutpasteMenu.add(pasteMenuItem);

		programInterfaceWindow.getComboBox1().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					System.out.println(e.getSource().getClass().getName());
					break;
				}
				}
			}
		});

		programInterfaceWindow.getComboBox2().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});
	}

	public CopyPasteDropDownList(SettingsInterfaceWindow ipass) {
		cutMenuItem.addActionListener(this);
		copyMenuItem.addActionListener(this);
		pasteMenuItem.addActionListener(this);

		cutpasteMenu.add(cutMenuItem);
		cutpasteMenu.add(copyMenuItem);
		cutpasteMenu.add(pasteMenuItem);

		ipass.linkProd.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					System.out.println(e.getSource().getClass().getName());
					break;
				}
				}
			}
		});

		ipass.linkQA1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});

		ipass.linkQA2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});

		ipass.linkQA3.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});
		ipass.f1.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});
		ipass.f2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				switch (e.getModifiers()) {
				case InputEvent.BUTTON3_MASK: {
					cutpasteMenu.show(e.getComponent(), e.getX(), e.getY());
					cutpasteMenu.setInvoker(e.getComponent());
					break;
				}
				}
			}
		});

	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == cutMenuItem) {
			JTextField jte = (JTextField) cutpasteMenu.getInvoker();
			jte.cut();
		}
		if (source == copyMenuItem) {
			JTextField jte = (JTextField) cutpasteMenu.getInvoker();
			jte.copy();
		}
		if (source == pasteMenuItem) {
			JTextField jte = (JTextField) cutpasteMenu.getInvoker();
			jte.paste();
		}
	}

}
