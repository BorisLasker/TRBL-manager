package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Logic.DBConnector;
import javafx.event.ActionEvent;

public class signInController {

	@FXML
	private TextField mailTxt;

	@FXML
	private Button enterBtn;

	@FXML
	private Label mailFormat;

	@FXML
	private Button registerBtn;

	@FXML
	private TextField usename;

	@FXML
	private TextField password;
	@FXML
	private CheckBox remembermesigin;

	// sign in to our application using the details saved in the db
	@FXML
	void signIn(ActionEvent event) {
		List<Object> params;
		int count;
		try {
			Connection conn = Main.con;
			// check empty field
			String username = usename.getText();
			String pass = password.getText();

			if (username.equals("") || pass.equals("")) {

				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("one or more of the fields are empty.");
				a.setContentText("השארת שדות ריקים אנא מלא אותם.");
				// show the dialog
				a.showAndWait();
				return;
			}

			params = new ArrayList<>();
			params.add(username);
			params.add(pass);

			count = Logic.DBConnector.getNumberOfUsers(params, conn);
			if (count > 0) {
				FXMLLoader loader = new FXMLLoader();
				((Node) event.getSource()).getScene().getWindow().hide();
				Stage primaryStage = new Stage();

				Pane root = loader.load(getClass().getResource("/fxml/login.fxml").openStream());
				logincontroller login = loader.getController();
				login.loadclient(username, pass);

				Scene scene = new Scene(root);
				primaryStage.setTitle("TRBL 1.0");
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);

				boolean readmeflag = DBConnector.getreadMe(params, conn);
				if (!readmeflag) {
					Alert a = new Alert(AlertType.NONE);
					a.setAlertType(AlertType.INFORMATION);
					// just think about the content part
					a.setTitle("Important Rules ");
					a.setHeaderText("Important Rules");
					a.setContentText("שלום לכולם!\r\n" + "זוהי גרסה 1.0 של travian manager.\r\n" + "כללים לשימוש:\r\n"
							+ "-המערכת עובדרת רק על שרת פרטי אחד (Zravian).\r\n"
							+ "-הגרסה של הכרום שלכם צריכה להיות Version 93.0.4577.63 (Official Build) (64-bit). \r\n"
							+ "-המערכת עובדת רק על שדרוג שדות משאב.\r\n"
							+ "-לא ניתן לבטל משימה דרך התוכנה (אם נתתם כמה משימות ואתם רוצים לבטל פשוט תסגרו את התוכנה ותכנסו מחדש).\r\n"
							+ "\r\n" + "הערה:\r\n" + "אנו נמשיך לפתח את התוכנה רק במידה ותיהיה הענות מספיק גדולה.\r\n"
							+ "");
					// show the dialog
					a.showAndWait();

					DBConnector.SetReadMe(params, conn);
				}
				primaryStage.show();

			} else {
				Alert a = new Alert(AlertType.NONE);
				a.setAlertType(AlertType.ERROR);
				a.setTitle("Error");
				a.setHeaderText("User not found");
				a.setContentText("Please try again!!");
				// show the dialog
				a.showAndWait();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	// when user wants to register to our system
	void signUp(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		Pane root;
		((Node) event.getSource()).getScene().getWindow().hide();
		try {
			root = loader.load(getClass().getResource("/fxml/signUp.fxml").openStream());
			Scene dashboard = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("TRBL 1.0");
			primaryStage.setScene(dashboard);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
