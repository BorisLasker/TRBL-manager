package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class signUpController {

	private static final String regex = "^(.+)@(.+)$";
	@FXML
	private TextField fullNameTxt;

	@FXML
	private TextField emailTxt;

	@FXML
	private Button submitBtn;

	@FXML
	private Label mailFormat;

	@FXML
	private Label emptyFields;

	@FXML
	private TextField usename;

	@FXML
	private TextField password;

	@FXML
	private TextField repassword;
	@FXML
	private Label passwordmatch;
	@FXML
    private Button backbtn;

	Alert a = new Alert(AlertType.NONE);

	// sign up function
	@FXML
	void submitSignUp(ActionEvent event) {
		boolean flag;
		List<Object> params;
		Connection conn = Main.con;

		String fullName = fullNameTxt.getText();
		String emailTxt1 = emailTxt.getText();
		String username = usename.getText();
		String password1 = password.getText();
		String repassword1 = repassword.getText();

		if (fullName.equals("") || emailTxt1.equals("") || username.equals("") || password1.equals("")
				|| repassword1.equals("")) {
			emptyFields.setVisible(true);
			return;

		}

		emptyFields.setVisible(false);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailTxt1);

		if (!matcher.matches()) {
			mailFormat.setVisible(true);
			return;
		}

		if (!password1.equals(repassword1)) {
			passwordmatch.setVisible(true);
			return;
		}
		params = new ArrayList<>();
		params.add(username);
		params.add(password1);
		params.add(fullName);
		params.add(emailTxt1);

		flag = Logic.DBConnector.insertToTable(params, conn);
		if (flag) {
			a.setAlertType(AlertType.INFORMATION);
			a.setTitle("Sign up successfully");
			a.setHeaderText("Sign up successfully");
			a.setContentText("Thank you for joining to our community\nbest wishes!!!");
			// show the dialog
			a.showAndWait();
			((Node) event.getSource()).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			Pane root;
			try {
				root = loader.load(getClass().getResource("/fxml/login.fxml").openStream());
				
				Scene dashboard = new Scene(root);
				Stage primaryStage = new Stage();
				primaryStage.setTitle("TRBL 1.0");
				primaryStage.setResizable(false);
				primaryStage.setScene(dashboard);
				primaryStage.show();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}


    @FXML
    void get_backbtn(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/fxml/signIn.fxml").openStream());
			Scene dashboard = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setTitle("TRBL 1.0");
			primaryStage.setResizable(false);
			primaryStage.setScene(dashboard);
			primaryStage.show();

    }

}
