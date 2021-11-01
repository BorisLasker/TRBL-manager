package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Logic.Multi;
import Logic.Tableinfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import overview.Sources;

public class overview_sources_controller implements Initializable {

	List<Button> button = new ArrayList<>();
	ObservableList<Tableinfo> list_tasks = FXCollections.observableArrayList();
	int index_process = 1;
	boolean flag_check_status = false;

	public int[] arr_status = new int[18];
	@FXML
	ObservableList<Integer> list = FXCollections.observableArrayList();

	@FXML
	ObservableList<Integer> list1 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list2 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list3 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list4 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list5 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list6 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list7 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list8 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list9 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list10 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list11 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list12 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list13 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list14 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list15 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list16 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list17 = FXCollections.observableArrayList();
	@FXML
	ObservableList<Integer> list18 = FXCollections.observableArrayList();
	@FXML
	private ImageView mapImg;
	@FXML
	private ComboBox<Integer> woodcutter;

	@FXML
	private ComboBox<Integer> claypit;

	@FXML
	private ComboBox<Integer> ironmine;

	@FXML
	private ComboBox<Integer> cropland;

	@FXML
	private ComboBox<Integer> id4;

	@FXML
	private ComboBox<Integer> id1;

	@FXML
	private ComboBox<Integer> id2;

	@FXML
	private ComboBox<Integer> id3;

	@FXML
	private ComboBox<Integer> id5;

	@FXML
	private ComboBox<Integer> id6;

	@FXML
	private ComboBox<Integer> id7;

	@FXML
	private ComboBox<Integer> id11;

	@FXML
	private ComboBox<Integer> id10;

	@FXML
	private ComboBox<Integer> id8;

	@FXML
	private ComboBox<Integer> id9;

	@FXML
	private ComboBox<Integer> id12;

	@FXML
	private ComboBox<Integer> id13;

	@FXML
	private ComboBox<Integer> id14;

	@FXML
	private ComboBox<Integer> id16;

	@FXML
	private ComboBox<Integer> id15;

	@FXML
	private ComboBox<Integer> id17;
	@FXML
	private ComboBox<Integer> id18;

	@FXML
	private Button UpgradeEachResources;

	@FXML
	private Button UpgradeAllResources;

	@FXML
	private Label warning;

	@FXML
	private TableView<Tableinfo> table;
	@FXML
	private TableColumn<Tableinfo, Integer> num_process;

	@FXML
	private TableColumn<Tableinfo, String> name_process;

	@FXML
	private TableColumn<Tableinfo, String> wanted_lvl;

	@FXML
	private TableColumn<Tableinfo, String> Cancel1;

	@FXML
	private Button logout;

	public static List<ComboBox<Integer>> comboboxes = new ArrayList<>();
	public static boolean flag_get_UpgradeAllResources;
	public static boolean flag_Cl_upgradeAll;
	public static boolean flag_Ir_upgradeAll;
	public static boolean flag_Wo_upgradeAll;
	public static boolean flag_Cr_upgradeAll;
	public static boolean flag_get_UpgradeEachResources;
	public static int get_UpgradeEachResources_index = -1;

	public static boolean flag_Cl_upgradeach;
	public static boolean flag_Ir_upgradeach;
	public static boolean flag_Wo_upgradeach;
	public static boolean flag_Cr_upgradeach;

	// clear combo box value
	public void clearCombo(ComboBox<Integer> cb) {
		cb.setValue(null);
	}

	// action when the user wants to upgrade specific family of resources
	@FXML
	void get_UpgradeAllResources(ActionEvent event) {

		refreshScreen();
		Integer wood = woodcutter.getValue();
		Integer clay = claypit.getValue();
		Integer iron = ironmine.getValue();
		Integer crop = cropland.getValue();
		woodcutter.setValue(null);
		claypit.setValue(null);
		ironmine.setValue(null);
		cropland.setValue(null);
		try {
			if (wood != null) {
				new Thread(() -> {
					Button but = new Button();
					but.setOnAction(this::handleButtonAction);
					button.add(but);
					Tableinfo element = new Tableinfo(index_process, "woodcutter", "" + wood + "", "X", but);
					addTaskToTable(element);
					Sources.updatelvl(wood, 1);
					Sources.updatelvl(wood, 3);
					Sources.updatelvl(wood, 14);
					Sources.updatelvl(wood, 17);
					Sources.timer_time();
					list_tasks.remove(element);
					table.setItems(list_tasks);

				}).start();

			}
			if (clay != null) {
				new Thread(() -> {
					Button but = new Button();
					but.setOnAction(this::handleButtonAction);
					button.add(but);
					Tableinfo element = new Tableinfo(index_process, "claypit", "" + clay + "", "X", but);
					addTaskToTable(element);
					Sources.updatelvl(clay, 5);
					Sources.updatelvl(clay, 6);
					Sources.updatelvl(clay, 16);
					Sources.updatelvl(clay, 18);
					Sources.timer_time();
					list_tasks.remove(element);
					table.setItems(list_tasks);
				}).start();

			}

			if (iron != null) {
				new Thread(() -> {
					Button but = new Button();
					but.setOnAction(this::handleButtonAction);
					button.add(but);
					Tableinfo element = new Tableinfo(index_process, "ironmine", "" + iron + "", "X", but);
					addTaskToTable(element);
					Sources.updatelvl(iron, 4);
					Sources.updatelvl(iron, 7);
					Sources.updatelvl(iron, 10);
					Sources.updatelvl(iron, 11);
					Sources.timer_time();
					list_tasks.remove(element);
					table.setItems(list_tasks);
				}).start();

			}

			if (crop != null) {
				new Thread(() -> {
					Button but = new Button();
					but.setOnAction(this::handleButtonAction);
					button.add(but);
					Tableinfo element = new Tableinfo(index_process, "cropland", "" + crop + "", "X", but);
					addTaskToTable(element);
					Sources.updatelvl(crop, 2);
					Sources.updatelvl(crop, 8);
					Sources.updatelvl(crop, 9);
					Sources.updatelvl(crop, 12);
					Sources.updatelvl(crop, 13);
					Sources.updatelvl(crop, 15);
					Sources.timer_time();
					list_tasks.remove(element);
					table.setItems(list_tasks);
				}).start();

			}

		} catch (Exception e) {
			returnAfterTimeout(event);

		}
	}

	// create new table info object with given name and wanted level
	public Tableinfo getdetails(String name_proc, Integer wanted_lvl) {

		Button but = new Button();

		but.setOnAction(this::handleButtonAction);
		button.add(but);

		String[] arr = name_proc.split(" ");
		arr[arr.length - 1] = "" + wanted_lvl + "";
		String wanted = String.join(" ", arr);

		return new Tableinfo(index_process, name_proc, wanted, "X", but);

	}

	// add specific table info element to the tasks table
	void addTaskToTable(Tableinfo element) {
		if (!list_tasks.contains(element)) {
			if (list_tasks.size() > 0)
				element.setNum_process(list_tasks.get(list_tasks.size() - 1).getNum_process() + 1);
			else
				element.setNum_process(1);
			list_tasks.add(element);
			table.setItems(list_tasks);
		}
	}

	public static void refreshScreen() {

		Sources.driver.navigate().refresh();
		WebElement overview = Sources.driver.findElement(By.xpath("//*[@id=\"n1\"]/img"));
		overview.click();

	}

	public void returnAfterTimeout(ActionEvent event) {
		Sources.login_after_timeout(logincontroller.urltext1, logincontroller.serverComboBox1,
				logincontroller.usernametxt1, logincontroller.passtxt1);
		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();

		Pane root;
		try {
			root = loader.load(getClass().getResource("/fxml/overview_sources.fxml").openStream());
			Scene scene = new Scene(root);
			primaryStage.setTitle("TRBL 1.0");

			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	void get_UpgradeEachResources(ActionEvent event) {
		// update the map screen of travian
		refreshScreen();

		try {

			int i;
			// reads the statuses of the entire places
			arr_status = Sources.checkstatuslvl();
			// iterate over the 18 places in the map
			for (i = 0; i < 18; i++) {
				ComboBox<Integer> specificComboBox = comboboxes.get(i);
				// if there is need to update the index
				if (!specificComboBox.getValue().equals(arr_status[i])) {
					Tableinfo element = getdetails(Sources.getnamesource(i + 1), specificComboBox.getValue());
					final int j = i;
					// if the task is not already in the tasks list
					if (!list_tasks.contains(element)) {

						Thread task = new Thread(() -> {
							try {
								Sources.updatelvl(specificComboBox.getValue(), j + 1);//
								Sources.timer_time();
								// Thread.sleep(1000);
								System.out.println(element.getName_process() + " finished");
								list_tasks.remove(element);
								table.setItems(list_tasks);
								button.remove(element.getButton());
								System.out.println("thread ends");
							} catch (Exception e) {
								System.out.println("problem here!");
							}

						});

						Multi t1 = new Multi(task, Sources.getnamesource(i + 1));
						element.setTask(t1);
						Thread th1 = new Thread(t1);
						th1.start();
						index_process++;
						list_tasks.add(element);
						table.setItems(list_tasks);

					}

				}

			}
		}

		catch (Exception e) {
			returnAfterTimeout(event);
		}
	}

	public void addit(int num, ComboBox<Integer> id, ObservableList<Integer> list) {

		int i;
		for (i = num; i < 21; i++) {

			list.add(i);

		}
		id.setItems(list);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboboxes = new ArrayList<>();
		comboboxes.add(id1);
		comboboxes.add(id2);
		comboboxes.add(id3);
		comboboxes.add(id4);
		comboboxes.add(id5);
		comboboxes.add(id6);
		comboboxes.add(id7);
		comboboxes.add(id8);
		comboboxes.add(id9);
		comboboxes.add(id10);
		comboboxes.add(id11);
		comboboxes.add(id12);
		comboboxes.add(id13);
		comboboxes.add(id14);
		comboboxes.add(id15);
		comboboxes.add(id16);
		comboboxes.add(id17);
		comboboxes.add(id18);

		num_process.setCellValueFactory(new PropertyValueFactory<Tableinfo, Integer>("num_process"));
		num_process.setStyle("-fx-alignment: CENTER;");
		name_process.setCellValueFactory(new PropertyValueFactory<Tableinfo, String>("name_process"));
		name_process.setStyle("-fx-alignment: CENTER;");
		wanted_lvl.setCellValueFactory(new PropertyValueFactory<Tableinfo, String>("wanted_lvl"));
		wanted_lvl.setStyle("-fx-alignment: CENTER;");
		Cancel1.setCellValueFactory(new PropertyValueFactory<Tableinfo, String>("button"));
		Cancel1.setStyle("-fx-alignment: CENTER;");

		for (int i = 1; i < 21; i++) {
			list.add(i);

		}
		woodcutter.setItems(list);
		claypit.setItems(list);
		ironmine.setItems(list);
		cropland.setItems(list);

		arr_status = Sources.checkstatuslvl();

		for (int i = 0; i < 18; i++) {
			comboboxes.get(i).setValue(arr_status[i]);
		}

		addit(arr_status[0], id1, list1);
		addit(arr_status[1], id2, list2);
		addit(arr_status[2], id3, list3);
		addit(arr_status[3], id4, list4);
		addit(arr_status[4], id5, list5);
		addit(arr_status[5], id6, list6);
		addit(arr_status[6], id7, list7);
		addit(arr_status[7], id8, list8);
		addit(arr_status[8], id9, list9);
		addit(arr_status[9], id10, list10);
		addit(arr_status[10], id11, list11);
		addit(arr_status[11], id12, list12);
		addit(arr_status[12], id13, list13);
		addit(arr_status[13], id14, list14);
		addit(arr_status[14], id15, list15);
		addit(arr_status[15], id16, list16);
		addit(arr_status[16], id17, list17);
		addit(arr_status[17], id18, list18);

	}

	@FXML
	void get_log_out(ActionEvent event) throws IOException {

		Sources.driver.close();
		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();

		Pane root = loader.load(getClass().getResource("/fxml/signIn.fxml").openStream());

		Scene scene = new Scene(root);
		primaryStage.setTitle("TRBL 1.0");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void handleButtonAction(ActionEvent event) {
		/*
		 * for (int i = 0; i < button.size(); i++) { if (event.getSource() ==
		 * button.get(i)) { Tableinfo row = table.getItems().get(i); Multi source_task =
		 * row.getTask(); String name_task = row.getName_process();
		 * Sources.cancel_task(name_task);
		 * 
		 * if(source_task.getT()==Thread.currentThread()) {
		 * System.out.println("current thread wanted to stop");
		 * source_task.getT().interrupt(); } else { source_task.stop(); }
		 * 
		 * 
		 * 
		 * 
		 * System.out.println(source_task.getName() + "cancelled"); //
		 * table.getItems().remove(i); button.remove(i); break; // Sources.timer_time();
		 * 
		 * } } refreshScreen(); arr_status = Sources.checkstatuslvl(); for (int i = 0; i
		 * < 18; i++) { comboboxes.get(i).setValue(arr_status[i]); }
		 * 
		 * 
		 */
	}

}
