package cctools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JOptionPane;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumScriptTools {
	private ReadSaveSettings readSaveSettings;
	private ProgramInterfaceWindow programInterfaceWindow;
	private WebDriver driver = null;
	private int max_energy = 5000;
	private long money = 900000000;
	private int gold1 = 900000;
	private int gold2 = 9000;
	private long honor = 4000;
	private long clanSize = 50;
	private long heroStrength = 500;
	
	private String eachStrongItemQuantity = "1";
	private int newDefinedItemsQuantityInFile = 100;

	public SeleniumScriptTools(ProgramInterfaceWindow interf,
			ReadSaveSettings readSettings) {
		this.programInterfaceWindow = interf;
		this.readSaveSettings = readSettings;
	}

	public void openPage(String userID, String server) {
		
		System.out.println(userID);
		System.out.println(server);
		String userNam = "Undefined";

		if (driver == null) {
			driver = new FirefoxDriver();
			System.out.println("new instance");
		} 
			try {
				if (server != "") {
				driver.get(server);
				}
				else {showMassageToUser("Server link is empty");}
			} catch (Exception e) {
				driver.quit();
				driver = new FirefoxDriver();
				driver.get(server);
			}

		try {
	// if it's prod account > typing credentials
			if (server == readSaveSettings.getSettingsFromArrayByIndex(0)) {
				driver.findElement(By.name("username")).sendKeys(
						readSaveSettings.getSettingsFromArrayByIndex(4));
				driver.findElement(By.name("password")).sendKeys(
						readSaveSettings.getSettingsFromArrayByIndex(5));
				driver.findElement(By.cssSelector("input[type=\"submit\"]"))
						.click();
			}
			
			driver.findElement(By.name("unique")).sendKeys(userID);
			driver.findElement(By.cssSelector("input[type=\"submit\"]"))
					.click();
			driver.findElement(By.linkText("Player")).click();

			userNam = driver.findElement(By.name("username")).getAttribute(
					"value");
		// set player name to program interface
			if (programInterfaceWindow.getUserToggleButtonState() == 'A') {
				programInterfaceWindow.setTextUserLabel1(userNam);
			} else if (programInterfaceWindow.getUserToggleButtonState() == 'B') {
				programInterfaceWindow.setTextUserLabel2(userNam);
			}
		// test account checkbox check
			if (driver.findElement(By.name("is_test_account")).isSelected() == false) {
				driver.findElement(By.name("is_test_account")).click();
				driver.findElement(By.xpath("//input[@value='Update Player']"))
						.click();
		// "test account" set text to confirmation  pop up
				try {
					Alert alert = driver.switchTo().alert();
					alert.sendKeys("test account");
					System.out.println(alert.getText());
					alert.accept();
				} catch (Exception e) {				}
			}
		} catch (Exception e) {
			showMassageToUser("The User Not Found. Check settings, servers, and User ID and retry");		
			e.printStackTrace();
		}
	}

	public void dropAll(String userID, String server) {
		this.openPage(userID, server);
		int j = 1;
		try {
			driver.findElement(By.linkText("Items")).click();
			int itemsQuantities = driver.findElements(By.name("quantities[]")).size();
			System.out.println(itemsQuantities);

			while (j <= itemsQuantities) {
				driver.findElement(
						By.xpath("(//input[@name='quantities[]'])[" + j + "]"))
						.clear();
				driver.findElement(
						By.xpath("(//input[@name='quantities[]'])[" + j + "]"))
						.sendKeys("0");
				j++;
			}
			driver.findElement(
					By.cssSelector("#items_tab > form > input[type=\"submit\"]"))
					.click();
			driver.findElement(By.linkText("Items")).click();

		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
	}

	public void reset(String userID, String server) {
		this.openPage(userID, server);
		driver.findElement(By.xpath("//input[@value='Reset']")).click();
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText());
			alert.accept();
		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
	}

	public void addRes(String userID, String server) {
		this.openPage(userID, server);
		int level;
		long ex_energy, ex_money, ex_gold, ex_level, ex_honor, ex_clanSize, ex_heroStrength;
		long p_energy, p_money, p_gold, p_level, p_honor, p_clanSize, p_heroStrength;
		String a, b, c, d, e, f, g;
		String s_energy, s_money, s_gold, s_level, s_honor, s_clanSize, s_heroStrength;
		String aa, bb, cc, dd, ee, ff, gg;
		int gold;
		boolean chkboxGold = programInterfaceWindow.isChkboxGoldSateChecked(), x = true;
		if (chkboxGold) {
			gold = gold1;
		} else {
			gold = gold2;
		}
		
		while (x == true) {
		if (readSaveSettings.getSettingsFromArrayByIndex(8).equals("KA")) {
			aa = "//div[@id='player_tab']/form[3]/table/tbody/tr[14]/td[2]";
			bb = "//div[@id='player_tab']/form[3]/table/tbody/tr[18]/td[2]";
			cc = "//div[@id='player_tab']/form[3]/table/tbody/tr[23]/td[2]";
			dd = "//div[@id='player_tab']/form[3]/table/tbody/tr[25]/td[2]";
			ee = "//div[@id='player_tab']/form[3]/table/tbody/tr[22]/td[2]";
			ff = 	"//div[@id='player_tab']/form[3]/table/tbody/tr[29]/td[2]";
			gg = "//div[@id='player_tab']/form[3]/table/tbody/tr[17]/td[2]";
			x = false;
			
		} else {
			showMassageToUser("uncheck MW check box in your settings and repeat");
			break;
		} 

		level = Integer.parseInt(readSaveSettings.getSettingsFromArrayByIndex(6));
	
	// resources Delta numbers calculations:
		a = driver.findElement(By.xpath(aa)).getText();
		b = driver.findElement(By.xpath(bb)).getText();
		c = driver.findElement(By.xpath(cc)).getText();
		d = driver.findElement(By.xpath(dd)).getText();
		e = driver.findElement(By.xpath(ee)).getText();
		f = driver.findElement(By.xpath(ff)).getText();
		g = driver.findElement(By.xpath(gg)).getText();

		System.out.println("gteText " + b);

		ex_energy = Long.parseLong(a);
		ex_money = Long.parseLong(b);
		ex_gold = Long.parseLong(c);
		ex_level = Long.parseLong(d);
		ex_honor = Long.parseLong(e);
		ex_clanSize = Long.parseLong(f);
		ex_heroStrength = Long.parseLong(g);

		System.out.println("ex_money " + ex_money);

		p_energy = max_energy - ex_energy;
		p_money = money - ex_money;
		p_gold = gold - ex_gold;
		p_level = level - ex_level;
		p_honor = honor - ex_honor;
		p_clanSize = clanSize - ex_clanSize;
		p_heroStrength = heroStrength - ex_heroStrength;

		s_energy = Long.toString(p_energy);
		s_money = Long.toString(p_money);
		s_gold = Long.toString(p_gold);
		s_level = Long.toString(p_level);
		s_honor = Long.toString(p_honor);
		s_clanSize = Long.toString(p_clanSize);
		s_heroStrength = Long.toString(p_heroStrength);


		driver.findElement(By.name("max_energy")).clear();
		driver.findElement(By.name("max_energy")).sendKeys(s_energy);
		driver.findElement(By.name("money")).clear();
		driver.findElement(By.name("money")).sendKeys(s_money);
		driver.findElement(By.name("gold")).clear();
		driver.findElement(By.name("gold")).sendKeys(s_gold);
		driver.findElement(By.name("level")).clear();
		driver.findElement(By.name("level")).sendKeys(s_level);
	
		if (driver.findElement(By.name("protect_money")).isSelected() == true) {
			driver.findElement(By.name("protect_money")).click();
		}
			
		driver.findElement(By.name("hero_strength")).clear();
		driver.findElement(By.name("hero_strength")).sendKeys(s_heroStrength);
		driver.findElement(By.name("clan_size")).clear();
		driver.findElement(By.name("clan_size")).sendKeys(s_clanSize);
		driver.findElement(By.name("respect")).clear();
		driver.findElement(By.name("respect")).sendKeys(s_honor);
		
		driver.findElement(By.xpath("//input[@value='Update Player']")).click();
		
		}
	}

	public void newItems(String userID, String server) {
		this.openPage(userID, server);
		String itemID = "";
		try {
			driver.findElement(By.linkText("Items")).click();
		// take items IDs from user input
			while (itemID != null) {
				itemID = JOptionPane.showInputDialog(programInterfaceWindow.getFrame(),
						"Enter item's ID : ", "Input", 1);

				if (itemID == null) {
					break;
				}
				driver.findElement(By.name("item_id")).clear();
				driver.findElement(By.name("item_id")).sendKeys(itemID);
				driver.findElement(By.name("quantity")).clear();
				driver.findElement(By.name("quantity")).sendKeys("1");
				driver.findElement(
						By.cssSelector("#giveItemForm > input[type=\"submit\"]"))
						.click();
				driver.findElement(By.linkText("Items")).click();
			}

		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
	}

	public void pvpBattle(String userID, String server) {
		this.openPage(userID, server);
		String attacker = "";
		String iD = "";
		if (programInterfaceWindow.getUserToggleButtonState() == 'B') {
			attacker = programInterfaceWindow.getTextField1();
		} else {
			attacker = programInterfaceWindow.getTextField2();
		}
		
		try {
			driver.findElement(By.name("unique")).sendKeys(attacker);
			driver.findElement(By.cssSelector("input[type=\"submit\"]"))
					.click();
			iD = driver
					.findElement(
							By.xpath("//div[@id='player_tab']/form[3]/table/tbody/tr[2]/td[2]"))
					.getText();
			driver.findElement(By.name("unique")).sendKeys(userID);
			driver.findElement(By.cssSelector("input[type=\"submit\"]"))
					.click();
			driver.findElement(By.name("attacker_id")).sendKeys(iD);
			driver.findElement(By.xpath("//input[@value='Place']")).click();
		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
	}

	public void upgradeBuilding(String userID, String server) {
		this.openPage(userID, server);
		int j = 1;
		try {
			driver.findElement(By.linkText("Buildings")).click();
			int buildingsQuantity = driver.findElements(By.name("upgrade_rank[]")).size();
			System.out.println(buildingsQuantity);

			while (j <= buildingsQuantity) {
				driver.findElement(
						By.xpath("(//input[@name='upgrade_rank[]'])[" + j + "]"))
						.clear();
				driver.findElement(
						By.xpath("(//input[@name='upgrade_rank[]'])[" + j + "]"))
						.sendKeys("10");
				j++;
			}
			driver.findElement(By.xpath("(//input[@value='Update All'])[2]"))
					.click();
		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
	}

	public void newDefinedItems(String userID, String server) {
		this.openPage(userID, server);

		String filename = "MyItems.txt";
		File file = new File(filename);

		if (!file.exists()) {
			try {
				file.createNewFile();
				showMassageToUser("Provide strongest items IDs (up to 100) one per line to file MyItems.txt located in the same folder with the program and RETRY");
			} catch (Exception e) {
				showMassageToUser("Couldn't create file for items. Check writing permissions");
			}
		}
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String[] k = new String[newDefinedItemsQuantityInFile ];
			String itemID = "";
			driver.findElement(By.name("clan_size")).clear();
			driver.findElement(By.name("clan_size")).sendKeys("100");
			driver.findElement(By.xpath("//input[@value='Update Player']"))
					.click();
			driver.findElement(By.linkText("Items")).click();

			for (int i = 0; i < newDefinedItemsQuantityInFile; i++) {
				k[i] = bufferedReader.readLine();
				if (k[i] != null && k[i].length() != 0) {

					itemID = k[i].trim();

					driver.findElement(By.name("item_id")).clear();
					driver.findElement(By.name("item_id")).sendKeys(itemID);
					driver.findElement(By.name("quantity")).clear();
					driver.findElement(By.name("quantity")).sendKeys(
							eachStrongItemQuantity);
					driver.findElement(
							By.cssSelector("#giveItemForm > input[type=\"submit\"]"))
							.click();
					driver.findElement(By.linkText("Items")).click();

				} else {
					System.out.println("k [i] = null");
					break;
				}
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
			showMassageToUser("Something went wrong. Check your items file and RETRY");
		}

	}
	
	public void syndicateBattle(String userID, String server) {
		this.openPage(userID, server);
		String secondUserID = "";
		String guildID1 = "";
		String guildID2 = "";
		String partOfServerURL = "";
		String battleInitiationURL = "";
		if (programInterfaceWindow.getUserToggleButtonState() == 'B') {
			secondUserID = programInterfaceWindow.getTextField1();
		} else {
			secondUserID = programInterfaceWindow.getTextField2();
		}
		
		try {
			
			driver.findElement(By.name("unique")).sendKeys(userID);
			driver.findElement(By.cssSelector("input[type=\"submit\"]"))
					.click();
			driver.findElement(By.linkText("Guild")).click();			
			guildID1 = driver
					.findElement(
							By.xpath("//div[@id='guild_tab']/form/table/tbody/tr/td[2] "))
					.getText();  
			if (guildID1.equals("")) {showMassageToUser("The player isn't in Syndicate");}
			else {
			driver.findElement(By.name("unique")).sendKeys(secondUserID);
			driver.findElement(By.cssSelector("input[type=\"submit\"]"))
					.click();
			driver.findElement(By.linkText("Guild")).click();			
			guildID2 = driver
					.findElement(
							By.xpath("//div[@id='guild_tab']/form/table/tbody/tr/td[2] "))
					.getText();            
			if (guildID2.equals("")) {showMassageToUser("The player isn't in Syndicate");}
			else {
			partOfServerURL = server.substring(0, server.indexOf("php/")+4);
			System.out.println(partOfServerURL);
			battleInitiationURL = partOfServerURL + "internal_service_controller/start_gvg_war/[\""  + guildID1 + "\",\"" + guildID2 + "\"]";
			System.out.println(battleInitiationURL);
			driver.get(battleInitiationURL);
			}
			}
			
		} catch (Exception e) {
			showMassageToUser("Something went wrong, try one more time");
		}
		
	}

	private void showMassageToUser(String message) {
		JOptionPane.showMessageDialog(programInterfaceWindow.getFrame(), message, "Info",
				JOptionPane.WARNING_MESSAGE);
	}

}
