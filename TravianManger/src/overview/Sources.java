package overview;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.RefreshPage;

import Controller.overview_sources_controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Sources extends overview_sources_controller {
	public static int temp_lvl_up = 0;
	public static WebDriver driver;
	static WebElement check_woodcutter;
	static WebElement check_clay;
	static WebElement check_iron_mine;
	static WebElement check_cropland;
	static WebElement check_weat;

	static String[] parts_Woodcutter;

	static String[] parts_clay;

	static String[] parts_iron;

	static String[] parts_cropland;
	static String[] parts_weat;
	private static String check_woodcutter1;
	private static String check_clay1;
	private static String check_weat1;
	private static String check_cropland1;
	private static String check_iron_mine1;
	static boolean flag_enough_resources = true;
	private static boolean flag_upgrade_warehouse = true;
	private static boolean flag_upgrade_Granary = true;
	private static boolean flag_weat = true;
	private static boolean flag_glob = true;

	public static void login_after_timeout(String url, String server, String username1, String pass1) {

		int index = 0;
		switch (server) {
		case "Nonstop":
			index = 1;
			break;

		case "Server 1":
			index = 2;
			break;

		case "Server 5":
			index = 3;
			break;

		case "Zravian 10k":
			index = 4;
			break;

		}

		WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"f\"]/a[" + index + "]/ins/h1"));
		searchbox.click();

		WebElement username = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		username.click();
		username.sendKeys(username1);

		WebElement pass = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
		pass.sendKeys(pass1);

		WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]/div/input[3]"));
		login.click();

	}

	public static void login(String url, String server, String username1, String pass1) {
		// setting the driver executable

		System.setProperty("webdriver.chrome.driver", ".\\src\\chromedriver.exe");

//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\Boris\\eclipse-workspace\\TravianManger\\src\\chromedriver.exe");

		// to do:
		// add combobox with server option

		// Initiating your chromedriver
		driver = new ChromeDriver();
		// maximize window
		driver.manage().window().maximize();

		// open browser with desried URL
		driver.get(url);
		int index = 0;
		switch (server) {
		case "Nonstop":
			index = 1;
			break;

		case "Server 1":
			index = 2;
			break;

		case "Server 5":
			index = 3;
			break;

		case "Zravian 10k":
			index = 4;
			break;

		}

		WebElement searchbox = driver.findElement(By.xpath("//*[@id=\"f\"]/a[" + index + "]/ins/h1"));
		searchbox.click();

		WebElement username = driver.findElement(By.xpath("//*[@id=\"name\"]"));
		username.click();
		username.sendKeys(username1);

		WebElement pass = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
		pass.sendKeys(pass1);

		WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]/div/input[3]"));
		login.click();
	}

	public static String getnamesource(int index) {

		WebElement name_Resource_fields = driver.findElement(By.xpath("//*[@id=\"rx\"]/area[" + index + "]"));
		String source_name = name_Resource_fields.getAttribute("alt");

		switch (source_name.substring(0, 2)) {

		// if cropland
		case "Cr": {
			// if cropland under lvl 10
			if (source_name.length() == 16 || source_name.length() == 18) {
				return source_name.substring(0, 16);

			}

			// if cropland above lvl 10
			if (source_name.length() == 17 || source_name.length() == 19) {
				return source_name.substring(0, 17);
			}
			break;
		}

		// if clay pit
		case "Cl": {
			// if clay pit under lvl 10
			if (source_name.length() == 16 || source_name.length() == 18) {
				return source_name.substring(0, 16);
			}
			// if claypit above lvl 10
			if (source_name.length() == 17 || source_name.length() == 19) {
				return source_name.substring(0, 17);

			}
			break;
		}

		// if woodcutter
		case "Wo": {
			// if woodcutter under lvl 10
			if (source_name.length() == 18 || source_name.length() == 20) {
				return source_name.substring(0, 18);
			}
			// if woodcutter above lvl 10
			if (source_name.length() == 19 || source_name.length() == 21) {
				return source_name.substring(0, 19);
			}
			break;
		}

		// if iron mine
		case "Ir": {
			// if iron mine under lvl 10
			if (source_name.length() == 17 || source_name.length() == 19) {
				return source_name.substring(0, 17);

			}
			// if iron mine above lvl 10
			if (source_name.length() == 18 || source_name.length() == 20) {
				return source_name.substring(0, 18);

			}
			break;
		}

		}
		return source_name;

	}

	public static int[] checkstatuslvl() {
		int a[] = new int[18];
		int i = 1;
		int lvl = 0;
		String lvlstr;
		String source_lvl;
		// timer_time();
		driver.navigate().refresh();
		check_cone();

		while (i != 19) {


			
			WebElement update_Resource_fields = driver.findElement(By.xpath("//*[@id=\"rx\"]/area[" + i + "]"));
			source_lvl = update_Resource_fields.getAttribute("alt");

			switch (source_lvl.substring(0, 2)) {

			// if cropland
			case "Cr": {
				// if cropland under lvl 10
				if (source_lvl.length() == 16 || source_lvl.length() == 18) {
					lvl = Integer.parseInt(source_lvl.substring(15, 16));
					break;

				}

				// if cropland above lvl 10
				if (source_lvl.length() == 17 || source_lvl.length() == 19) {
					lvlstr = source_lvl.substring(15, 17);
					lvl = Integer.parseInt(lvlstr);
				}
				break;
			}

			// if clay pit
			case "Cl": {
				// if clay pit under lvl 10
				if (source_lvl.length() == 16 || source_lvl.length() == 18) {
					lvl = Integer.parseInt(source_lvl.substring(15, 16));

				}
				// if claypit above lvl 10
				if (source_lvl.length() == 17 || source_lvl.length() == 19) {
					lvlstr = source_lvl.substring(15, 16) + source_lvl.substring(16, 17);
					lvl = Integer.parseInt(lvlstr);

				}
				break;
			}

			// if woodcutter
			case "Wo": {
				// if woodcutter under lvl 10
				if (source_lvl.length() == 18 || source_lvl.length() == 20) {
					lvl = Integer.parseInt(source_lvl.substring(17, 18));
				}
				// if woodcutter above lvl 10
				if (source_lvl.length() == 19 || source_lvl.length() == 21) {
					lvlstr = source_lvl.substring(17, 18) + source_lvl.substring(18, 19);
					lvl = Integer.parseInt(lvlstr);

				}
				break;
			}

			// if iron mine
			case "Ir": {
				// if iron mine under lvl 10
				if (source_lvl.length() == 17 || source_lvl.length() == 19) {
					lvl = Integer.parseInt(source_lvl.substring(16, 17));

				}
				// if iron mine above lvl 10
				if (source_lvl.length() == 18 || source_lvl.length() == 20) {
					lvlstr = source_lvl.substring(16, 17) + source_lvl.substring(17, 18);
					lvl = Integer.parseInt(lvlstr);

				}
				break;
			}

			}
			a[i - 1] = lvl;
			i++;

		}

		return a;

	}

	public static void cancel_task(String task_name) {
		int numofClicks=0;
		try {
			String[] task = task_name.split(" ");
			String a = "";
			if (task.length == 3)
				a = task[0] + "";
			else
				a = task[0] + " " + task[1];
			
			WebElement cancelTask2 = driver.findElement(By.xpath("//*[@id=\"building_contract\"]/tbody/tr/td[2]/a"));
			String name = cancelTask2.getText();
			if (name.contains(a)) {
				WebElement cancelTask1 = driver
						.findElement(By.xpath("//*[@id=\"building_contract\"]/tbody/tr/td[1]/a/img"));
				System.out.println(temp_lvl_up);
				while (temp_lvl_up >= 0) {
					
					flag_glob = false;
					try {
					cancelTask1.click();
					numofClicks++;
					}
					catch(Exception e) {
						System.out.println("click not succeed");
					}
					temp_lvl_up--;
//					String cancelTask = cancelTask1.getAttribute("alt");
//					if (cancelTask.equals("Cancel")) {
//						
//					}     

			}
			}
			else
				System.out.println("not the task you want ");
		} catch (Exception e) {
		}
		System.out.println(numofClicks);
	}

	public static void timer_time() {
		String time;
		try {
			WebElement timer = driver.findElement(By.xpath("//*[@id=\"timer1\"]"));

			time = timer.getAttribute("id");
			while (time.equals("timer1")) {
				timer = driver.findElement(By.xpath("//*[@id=\"timer1\"]"));
				time = timer.getAttribute("id");
			}
		} catch (Exception e) {
		}
	}

	private static void if_can_upgrade(int wood, int clay, int iron, int crop, int weat, int index, int lvl) {

		if (wood > Integer.parseInt(parts_Woodcutter[1]) || clay > Integer.parseInt(parts_clay[1])
				|| iron > Integer.parseInt(parts_iron[1]) || crop > Integer.parseInt(parts_cropland[1])
				|| weat > Integer.parseInt(parts_weat[1])) {
			if (flag_upgrade_warehouse)
				new Thread(() -> {
					Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.ERROR);
					a.setTitle("error");
					a.setHeaderText("error");
					a.setContentText("You must upgrade Warehouse");
					// show the dialog
					a.show();

				}).start();
			flag_upgrade_warehouse = false;
			return;
		}

		if (crop > Integer.parseInt(parts_cropland[1])) {
			if (flag_upgrade_Granary)
				new Thread(() -> {
					Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.ERROR);
					a.setTitle("error");
					a.setHeaderText("error");
					a.setContentText("You must upgrade Granary");
					// show the dialog
					a.show();

				}).start();
			flag_upgrade_Granary = false;
			return;
		}

		if (weat > Integer.parseInt(parts_weat[1])) {
			if (flag_weat)
				new Thread(() -> {
					Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.ERROR);
					a.setTitle("error");
					a.setHeaderText("error");
					a.setContentText("You have to wait until you have enough weat ");
					// show the dialog
					a.show();

				}).start();
			flag_weat = false;
			return;
		}
		while ((Integer.parseInt(parts_Woodcutter[0]) - wood < 0 || Integer.parseInt(parts_clay[0]) - clay < 0
				|| Integer.parseInt(parts_iron[0]) - iron < 0 || Integer.parseInt(parts_cropland[0]) - crop < 0
				|| Integer.parseInt(parts_weat[0]) - weat < 0) && flag_glob == true) {
			if (flag_enough_resources)
				new Thread(() -> {
					Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.ERROR);
					a.setTitle("error");
					a.setHeaderText("error");
					a.setContentText(
							"You do not have enough resources at this moment\nThe system will wait until you have enough resources");
					// show the dialog
					a.show();

				}).start();
			flag_enough_resources = false;
			try {
				WebElement check_woodcutter = driver.findElement(By.xpath("//*[@id=\"l4\"]"));
				check_woodcutter1 = check_woodcutter.getText();

			} catch (Exception e) {
			}

			parts_Woodcutter = check_woodcutter1.split("/");

			try {
				WebElement check_clay = driver.findElement(By.xpath("//*[@id=\"l3\"]"));
				check_clay1 = check_clay.getText();
			} catch (Exception e) {
			}

			parts_clay = check_clay1.split("/");

			try {
				WebElement check_iron_mine = driver.findElement(By.xpath("//*[@id=\"l2\"]"));
				check_iron_mine1 = check_iron_mine.getText();
			} catch (Exception e) {
			}

			parts_iron = check_iron_mine1.split("/");

			try {
				WebElement check_cropland = driver.findElement(By.xpath("//*[@id=\"l1\"]"));
				check_cropland1 = check_cropland.getText();
			} catch (Exception e) {
			}

			parts_cropland = check_cropland1.split("/");

			try {
				check_weat = driver.findElement(By.xpath("//*[@id=\"resWrap\"]/table/tbody/tr[2]/td[3]"));

			} catch (Exception e) {
				check_weat = driver.findElement(By.xpath("//*[@id=\"resWrap\"]/table/tbody/tr/td[10]"));

			}
			try {
				check_weat1 = check_weat.getText();
			} catch (Exception e) {
			}

			parts_weat = check_weat1.split("/");

		}
		if (flag_glob == false) {
			flag_glob = true;
			return;
		}
		driver.findElement(By.xpath("//*[@id=\"rx\"]/area[" + index + "]")).click();
		flag_enough_resources = true;
		flag_upgrade_warehouse = true;
		flag_upgrade_Granary = true;
		flag_weat = true;

		try {
		} catch (Exception e) {
		}

	}

	public static synchronized int updatelvl(Integer lvl_up_amount, int index) {
		int wood;
		int clay;
		int iron;
		int crop;
		int weat;

		int lvl = 0;
		String lvlstr;
		String source_lvl;

		if (lvl_up_amount == null)
			return 1;
		check_cone();
		timer_time();
		WebElement update_Resource_fields = driver.findElement(By.xpath("//*[@id=\"rx\"]/area[" + index + "]"));
		source_lvl = update_Resource_fields.getAttribute("alt");
		WebElement check_woodcutter = driver.findElement(By.xpath("//*[@id=\"l4\"]"));
		WebElement check_clay = driver.findElement(By.xpath("//*[@id=\"l3\"]"));
		WebElement check_iron_mine = driver.findElement(By.xpath("//*[@id=\"l2\"]"));
		WebElement check_cropland = driver.findElement(By.xpath("//*[@id=\"l1\"]"));
		try {
			check_weat = driver.findElement(By.xpath("//*[@id=\"resWrap\"]/table/tbody/tr[2]/td[3]"));

		} catch (Exception e) {
			check_weat = driver.findElement(By.xpath("//*[@id=\"resWrap\"]/table/tbody/tr/td[10]"));

		}

		check_woodcutter1 = check_woodcutter.getText();
		parts_Woodcutter = check_woodcutter1.split("/");

		check_clay1 = check_clay.getText();
		parts_clay = check_clay1.split("/");

		check_iron_mine1 = check_iron_mine.getText();
		parts_iron = check_iron_mine1.split("/");

		check_cropland1 = check_cropland.getText();
		parts_cropland = check_cropland1.split("/");

		check_weat1 = check_weat.getText();
		parts_weat = check_weat1.split("/");

		switch (source_lvl.substring(0, 2)) {

		// if cropland
		case "Cr": {

			// if cropland under lvl 10
			if (source_lvl.length() == 16) {
				lvl = Integer.parseInt(source_lvl.substring(15, 16));
				temp_lvl_up = lvl_up_amount - lvl;

				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();
					try {
						switch (lvl) {
						case 0: {
							wood = 70;
							clay = 90;
							iron = 70;
							crop = 20;
							weat = 0;
							driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 1: {
							wood = 115;
							clay = 150;
							iron = 115;
							crop = 35;
							weat = 0;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;
						}
						case 2: {
							wood = 195;
							clay = 250;
							iron = 195;
							crop = 55;
							weat = 0;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 3: {
							wood = 325;
							clay = 420;
							iron = 325;
							crop = 95;
							weat = 0;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 4: {
							wood = 545;
							clay = 700;
							iron = 545;
							crop = 155;
							weat = 0;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 5: {
							wood = 910;
							clay = 1170;
							iron = 910;
							crop = 260;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 6: {
							wood = 1520;
							clay = 1950;
							iron = 1520;
							crop = 435;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 7: {
							wood = 2535;
							clay = 3260;
							iron = 2535;
							crop = 725;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 8: {
							wood = 4235;
							clay = 5445;
							iron = 4235;
							crop = 1210;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 9: {
							wood = 7070;
							clay = 9095;
							iron = 7070;
							crop = 2020;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}

						}

					}

					catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					}
				}

			}
			// if cropland above lvl 10
			else {
				lvlstr = source_lvl.substring(15, 17);
				lvl = Integer.parseInt(lvlstr);
				temp_lvl_up = lvl_up_amount - lvl;
				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();

					try {
						switch (lvl) {
						case 10: {
							wood = 11810;
							clay = 15185;
							iron = 11810;
							crop = 3375;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 11: {
							wood = 19725;
							clay = 25360;
							iron = 19725;
							crop = 5635;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 12: {
							wood = 32940;
							clay = 42350;
							iron = 32940;
							crop = 9410;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 13: {
							wood = 55005;
							clay = 70720;
							iron = 55005;
							crop = 15715;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 14: {
							wood = 91860;
							clay = 118105;
							iron = 91860;
							crop = 26245;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 15: {
							wood = 153405;
							clay = 197240;
							iron = 153405;
							crop = 43830;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 16: {
							wood = 256190;
							clay = 329385;
							iron = 256190;
							crop = 73195;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 17: {
							wood = 427835;
							clay = 550075;
							iron = 427835;
							crop = 122240;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 18: {
							wood = 714485;
							clay = 918625;
							iron = 714485;
							crop = 204140;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 19: {
							wood = 1193195;
							clay = 1534105;
							iron = 1193195;
							crop = 340915;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						}

					} catch (Exception e) {

					}
				}

			}
			break;
		}

		// if clay pit
		case "Cl": {
			// if clay pit under lvl 10
			if (source_lvl.length() == 16) {
				lvl = Integer.parseInt(source_lvl.substring(15, 16));

				temp_lvl_up = lvl_up_amount - lvl;

				for (int j = 0; j < temp_lvl_up; j++) {
					;
					timer_time();
					try {
						switch (lvl) {
						case 0: {
							wood = 80;
							clay = 40;
							iron = 80;
							crop = 50;
							weat = 2;
							driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 1: {
							wood = 135;
							clay = 65;
							iron = 135;
							crop = 85;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;
						}
						case 2: {
							wood = 225;
							clay = 110;
							iron = 225;
							crop = 140;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 3: {
							wood = 375;
							clay = 185;
							iron = 375;
							crop = 235;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 4: {
							wood = 620;
							clay = 310;
							iron = 620;
							crop = 390;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 5: {
							wood = 1040;
							clay = 520;
							iron = 1040;
							crop = 650;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 6: {
							wood = 1735;
							clay = 870;
							iron = 1735;
							crop = 1085;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 7: {
							wood = 2900;
							clay = 1450;
							iron = 2900;
							crop = 1810;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 8: {
							wood = 4840;
							clay = 2420;
							iron = 4840;
							crop = 3025;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 9: {
							wood = 8080;
							clay = 4040;
							iron = 8080;
							crop = 5050;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}

						}

					}

					catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					}
				}
			}

			// if claypit above lvl 10
			else {
				lvlstr = source_lvl.substring(15, 17);
				lvl = Integer.parseInt(lvlstr);
				temp_lvl_up = lvl_up_amount - lvl;

				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();

					try {
						switch (lvl) {
						case 10: {
							wood = 13500;
							clay = 6750;
							iron = 13500;
							crop = 8435;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 11: {
							wood = 22540;
							clay = 11270;
							iron = 22540;
							crop = 14090;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 12: {
							wood = 37645;
							clay = 18820;
							iron = 37645;
							crop = 23525;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 13: {
							wood = 62865;
							clay = 31430;
							iron = 62865;
							crop = 39290;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 14: {
							wood = 104985;
							clay = 52490;
							iron = 104985;
							crop = 65615;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 15: {
							wood = 175320;
							clay = 87660;
							iron = 175320;
							crop = 109575;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 16: {
							wood = 292790;
							clay = 146395;
							iron = 292790;
							crop = 182995;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 17: {
							wood = 488955;
							clay = 244480;
							iron = 488955;
							crop = 305600;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 18: {
							wood = 816555;
							clay = 408280;
							iron = 816555;
							crop = 510350;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 19: {
							wood = 1363650;
							clay = 681825;
							iron = 1363650;
							crop = 852280;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						}

					} catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					}
				}
			}
			break;
		}

		// if woodcutter
		case "Wo": {
			// if woodcutter under lvl 10
			if (source_lvl.length() == 18) {
				lvl = Integer.parseInt(source_lvl.substring(17, 18));
				temp_lvl_up = lvl_up_amount - lvl;

				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();
					try {
						switch (lvl) {
						case 0: {
							wood = 40;
							clay = 100;
							iron = 50;
							crop = 60;
							weat = 2;
							driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 1: {
							wood = 65;
							clay = 165;
							iron = 85;
							crop = 100;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;
						}
						case 2: {
							wood = 110;
							clay = 280;
							iron = 140;
							crop = 165;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 3: {
							wood = 185;
							clay = 465;
							iron = 235;
							crop = 280;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 4: {
							wood = 310;
							clay = 780;
							iron = 390;
							crop = 465;
							weat = 1;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 5: {
							wood = 520;
							clay = 1300;
							iron = 650;
							crop = 780;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 6: {
							wood = 870;
							clay = 2170;
							iron = 1085;
							crop = 1300;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 7: {
							wood = 1450;
							clay = 3625;
							iron = 1810;
							crop = 2175;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 8: {
							wood = 2420;
							clay = 6050;
							iron = 3025;
							crop = 3630;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 9: {
							wood = 4040;
							clay = 10105;
							iron = 5050;
							crop = 6060;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}

						}

					}

					catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					}
				}

			}
			// if woodcutter above lvl 10
			else {
				lvlstr = source_lvl.substring(17, 18) + source_lvl.substring(18, 19);
				lvl = Integer.parseInt(lvlstr);
				temp_lvl_up = lvl_up_amount - lvl;
				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();

					try {
						switch (lvl) {
						case 10: {
							wood = 6750;
							clay = 16870;
							iron = 8435;
							crop = 10125;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 11: {
							wood = 11270;
							clay = 28175;
							iron = 14090;
							crop = 16905;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 12: {
							wood = 18820;
							clay = 47055;
							iron = 23525;
							crop = 28230;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 13: {
							wood = 31430;
							clay = 78580;
							iron = 39290;
							crop = 47150;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 14: {
							wood = 52490;
							clay = 131230;
							iron = 65615;
							crop = 78740;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 15: {
							wood = 87660;
							clay = 219155;
							iron = 109575;
							crop = 131490;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 16: {
							wood = 146395;
							clay = 365985;
							iron = 182995;
							crop = 219590;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 17: {
							wood = 244480;
							clay = 611195;
							iron = 305600;
							crop = 366715;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 18: {
							wood = 408280;
							clay = 1020695;
							iron = 510350;
							crop = 612420;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 19: {
							wood = 681825;
							clay = 1704565;
							iron = 852280;
							crop = 1022740;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						}

					} catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
					}
				}
			}
			break;
		}

		// if iron mine
		case "Ir": {
			// if iron mine under lvl 10
			if (source_lvl.length() == 17) {
				lvl = Integer.parseInt(source_lvl.substring(16, 17));
				temp_lvl_up = lvl_up_amount - lvl;

				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();
					try {
						switch (lvl) {
						case 0: {
							wood = 100;
							clay = 80;
							iron = 30;
							crop = 60;
							weat = 3;
							driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 1: {
							wood = 165;
							clay = 135;
							iron = 50;
							crop = 100;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;
						}
						case 2: {
							wood = 280;
							clay = 225;
							iron = 85;
							crop = 165;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 3: {
							wood = 465;
							clay = 375;
							iron = 40;
							crop = 280;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 4: {
							wood = 780;
							clay = 620;
							iron = 235;
							crop = 465;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 5: {
							wood = 1300;
							clay = 1040;
							iron = 390;
							crop = 780;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 6: {
							wood = 2170;
							clay = 1735;
							iron = 650;
							crop = 1300;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 7: {
							wood = 3625;
							clay = 2900;
							iron = 1085;
							crop = 2175;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 8: {
							wood = 6050;
							clay = 4840;
							iron = 1815;
							crop = 3630;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 9: {
							wood = 10105;
							clay = 8080;
							iron = 3030;
							crop = 6060;
							weat = 2;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}

						}

					}

					catch (Exception e) {
						// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

					}
				}
			}
			// if iron mine above lvl 10
			else {
				lvlstr = source_lvl.substring(16, 17) + source_lvl.substring(17, 18);
				lvl = Integer.parseInt(lvlstr);
				temp_lvl_up = lvl_up_amount - lvl;
				for (int j = 0; j < temp_lvl_up; j++) {

					timer_time();

					try {
						switch (lvl) {
						case 10: {
							wood = 16870;
							clay = 13500;
							iron = 5060;
							crop = 10125;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 11: {
							wood = 28175;
							clay = 22540;
							iron = 8455;
							crop = 16905;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 12: {
							wood = 47055;
							clay = 37645;
							iron = 14115;
							crop = 28230;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 13: {
							wood = 78580;
							clay = 62865;
							iron = 23575;
							crop = 47150;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 14: {
							wood = 131230;
							clay = 104985;
							iron = 39370;
							crop = 78740;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 15: {
							wood = 219155;
							clay = 175320;
							iron = 65745;
							crop = 131490;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 16: {
							wood = 365985;
							clay = 292790;
							iron = 109795;
							crop = 219590;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 17: {
							wood = 611195;
							clay = 488955;
							iron = 183360;
							crop = 366715;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 18: {
							wood = 1020695;
							clay = 816555;
							iron = 306210;
							crop = 612420;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						case 19: {
							wood = 1704565;
							clay = 1363650;
							iron = 511370;
							crop = 1022740;
							weat = 3;
							if_can_upgrade(wood, clay, iron, crop, weat, index, lvl);
							lvl++;
							break;

						}
						}

					} catch (Exception e) {
					}
				}
			}
			break;
		}

		}

		return lvl;

	}

	public static void check_cone() {

		WebElement conelement = driver.findElement(By.xpath("//*[@id=\"cone\"]"));
		String cone = conelement.getAttribute("style");

		if (!cone.equals("")) {
			conelement.click();
		}

	}

}
