package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import overview.Sources;

public class logincontroller {
	public static String urltext1; // = "https://zravian.com";
	public static String serverComboBox1;// = "Server 5";
	public static String usernametxt1;// = "boris";
	public static String passtxt1;// = "qazwsx";

	List<Object> params;
	@FXML
	private TextField usernametxt;

	@FXML
	private TextField passtxt;

	@FXML
	private TextField urltext;

	@FXML
	private Button enterbtn;

	@FXML
	private MenuBar menuApp;

	@FXML
	private ComboBox<String> siteComBox;

	@FXML
	private Button backbtn1;

	@FXML
	private ComboBox<String> serverComboBox;
	@FXML
	ObservableList<String> listserverComboBox = FXCollections.observableArrayList("Nonstop", "Server 1", "Server 5",
			"Zravian 10k");

	@FXML
	private CheckBox remeberMe;
	// create a alert
	Alert a = new Alert(AlertType.NONE);

	@FXML
	public void initialize() {
		menuApp.getMenus().remove(0, 3);
		Menu file = new Menu("File");
		MenuItem signUp = new MenuItem("Sign up");
		file.getItems().add(signUp);
		MenuItem logout = new MenuItem("Log out");
		file.getItems().add(logout);
		Menu help = new Menu("Help");
		MenuItem aboutUs = new MenuItem("About us");
		help.getItems().add(aboutUs);
		signUp.setOnAction(e -> {

			try {
				FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/fxml/signUp.fxml").openStream());
				Scene dashboard = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(dashboard);
				primaryStage.setResizable(false);
				primaryStage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		logout.setOnAction(e -> {

			try {
				FXMLLoader loader = new FXMLLoader();
				MenuItem m = (MenuItem) e.getSource();
				while (m.getParentPopup() == null) {
					m = m.getParentMenu();
				}
				m.getParentPopup().getOwnerWindow().hide();

				Pane root = loader.load(getClass().getResource("/fxml/signIn.fxml").openStream());
				Scene dashboard = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setScene(dashboard);
				primaryStage.setResizable(false);
				primaryStage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});
		menuApp.getMenus().add(0, file);
		aboutUs.setOnAction(e -> {
			// set alert type
			a.setAlertType(AlertType.INFORMATION);
			a.setTitle("About us");
			a.setHeaderText("About us");
			a.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			a.setContentText("שלום לכולם!\r\n"
					+ "\r\n"
					+ "זוהי גרסה 1.0 של travian manager.\r\n"
					+ "\r\n"
					+ "כללים לשימוש:\r\n"
					+ "-המערכת עובדרת רק על שרת פרטי אחד (Zravian).\r\n"
					+ "-הגרסה של הכרום שלכם צריכה להיות Version 93.0.4577.63 (Official Build) (64-bit). \r\n"
					+ "-המערכת עובדת רק על שדרוג שדות משאב.\r\n"
					+ "-לא ניתן לבטל משימה דרך התוכנה (אם נתתם כמה משימות ואתם רוצים לבטל פשוט תסגרו את התוכנה ותכנסו מחדש).\r\n"
					+ "\r\n"
					+ "הערה:\r\n"
					+ "אנו נמשיך לפתח את התוכנה רק במידה ותיהיה הענות מספיק גדולה.\r\n"
					+ "\r\n"
					+ "Our mail for more info: \r\n"
					+ "borsh1992@gmail.com");
			// show the dialog
			a.show();

		});
		menuApp.getMenus().add(1, help);

		serverComboBox.setItems(listserverComboBox);

		siteComBox.setValue("Zravian");
		urltext.setText("https://zravian.com/");
	}

	@FXML
	void getenterbtn(ActionEvent event) throws IOException {
		List<Object> params;

		String urltext1 = urltext.getText();
		String serverComboBox1 = serverComboBox.getValue();
		String usernametxt1 = usernametxt.getText();
		String passtxt1 = passtxt.getText();
		String site = siteComBox.getValue();
		if (remeberMe.isSelected()) {
			Connection conn = Main.con;
			params = new ArrayList<>();
			params.add(site);
			params.add(serverComboBox1);
			params.add(urltext1);
			params.add(usernametxt1);
			params.add(passtxt1);
			params.add("yes");
			params.add(usernametxt1);
			Logic.DBConnector.insertToTableMore(params, conn);
		} else {
			Connection conn = Main.con;
			params = new ArrayList<>();
			params.add(site);
			params.add(serverComboBox1);
			params.add(urltext1);
			params.add(usernametxt1);
			params.add(passtxt1);
			params.add("no");
			params.add(usernametxt1);
			Logic.DBConnector.insertToTableMore(params, conn);
		}

		// Replace with variables
		Sources.login(urltext1, serverComboBox1, usernametxt1, passtxt1);

		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide(); // hiding primary window
		Stage primaryStage = new Stage();

		Pane root = loader.load(getClass().getResource("/fxml/overview_sources.fxml").openStream());

		Scene scene = new Scene(root);
		primaryStage.setTitle("TRBL 1.0");

		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	@FXML
	void getbackbtn(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();

		Pane root;
		try {
			root = loader.load(getClass().getResource("/fxml/signIn.fxml").openStream());
			Scene scene = new Scene(root);
			primaryStage.setTitle("TRBL 1.0");

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadclient(String username, String pass) {

		Connection conn = Main.con;
		params = new ArrayList<>();
		List<String> res = new ArrayList<>();
		params.add(username);
		params.add(pass);
		res = Logic.DBConnector.rememberMe(params, conn);
		if (res != null) {
			if (res.get(5).equals("yes")) {
				siteComBox.setValue(res.get(0));
				serverComboBox.setValue(res.get(1));
				urltext.setText(res.get(2));
				usernametxt.setText(res.get(3));
				passtxt.setText(res.get(4));
				remeberMe.setSelected(true);

			}
		}
	}

}
