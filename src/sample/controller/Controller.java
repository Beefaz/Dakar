package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.model.Vartotojas;
import sample.model.VartotojasDAO;
import sample.utils.Validation;

public class Controller {
    Stage stage = new Stage();
    //login
    @FXML
    TextField logUsernameField;
    @FXML
    PasswordField logPasswordField;
    @FXML
    Label logErrorField;
    @FXML
    Button logLoginButton;
    @FXML
    Button logRegisterButton;

    //registration
    @FXML
    TextField regUsernameField;
    @FXML
    TextField regEmailField;
    @FXML
    PasswordField regPasswordField;
    @FXML
    PasswordField regPasswordField1;
    @FXML
    Label regErrorField;
    @FXML
    Button regRegisterButton;
    @FXML
    Button regBackButton;

    //dashboard
    @FXML
    Button dashSelectButton;
    @FXML
    Button dashLogoutButton;
    @FXML
    Label dashUsername;
    @FXML
    Button testButton;

    public void loadLogin(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/loginScreen.fxml"));
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();
            closePreviousOnAction(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRegistration(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
            stage.setTitle("Registration");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();
            closePreviousOnAction(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDashboard(ActionEvent actionEvent, String userName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
            stage.setTitle("Dakar");
            stage.setScene(new Scene(root, 1060, 710));
            stage.show();
            closePreviousOnAction(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginErrorCheck(ActionEvent actionEvent) {
        String userName = logUsernameField.getText();
        String userEmail = logUsernameField.getText();
        String loginPass = logPasswordField.getText();
        if (!Validation.isValidUserName(userName) && !Validation.isValidEmail(userEmail)) {
            logErrorField.setText("Invalid username or e-mail.");
            logErrorField.setVisible(true);
        } else if (!VartotojasDAO.selectUsername(userName).toString().contains(userName) && !VartotojasDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            logErrorField.setText("Username or e-mail does not exist.");
            logErrorField.setVisible(true);
        } else if (!Validation.isValidPassword(loginPass)) {
            logErrorField.setText("Invalid password. Minimum 5 Uppercase, lowercase or numeric characters.");
            logErrorField.setVisible(true);
        } else if (!VartotojasDAO.selectUsernamePass(userName).toString().contains(loginPass) && !VartotojasDAO.selectEmailPass(userEmail).toString().contains(loginPass)) {
            logErrorField.setText("Wrong password");
            logErrorField.setVisible(true);
        } else {
            loadDashboard(actionEvent, userName);
        }
    }

    public void registrationErrorCheck() {
        String userName = regUsernameField.getText();
        String regPass = regPasswordField.getText();
        String regPass1 = regPasswordField1.getText();
        String userEmail = regEmailField.getText();
        if (!Validation.isValidUserName(userName)) {
            regErrorField.setText("Invalid username. Minimum 5 Uppercase, lowercase or numeric characters.");
            regErrorField.setVisible(true);
        } else if (VartotojasDAO.selectUsername(userName).toString().contains(userName)) {
            regErrorField.setText("Username already exists.");
            regErrorField.setVisible(true);
        } else if (!Validation.isValidEmail(userEmail)) {
            regErrorField.setText("Invalid e-mail. Example: mail@example.com");
            regErrorField.setVisible(true);
        } else if (VartotojasDAO.selectEmail(userEmail).toString().contains(userEmail)) {
            regErrorField.setText("This e-mail is already in use.");
            regErrorField.setVisible(true);
        } else if (!Validation.isValidPassword(regPass) && !Validation.isValidPassword(regPass1)) {
            regErrorField.setText("Invalid password. Minimum 5 Uppercase, lowercase or numeric characters.");
            regErrorField.setVisible(true);
        } else if (!regPass.equals(regPass1)) {
            regErrorField.setText("Passwords don't match.");
            regErrorField.setVisible(true);
        } else {
            Vartotojas vartotojas = new Vartotojas(userName, regPass, userEmail);
            VartotojasDAO.insert(vartotojas);
            regErrorField.setTextFill(Color.GREEN);
            regErrorField.setText("Registration successful.");
            regErrorField.setVisible(true);
            regRegisterButton.setText("Login");
            regRegisterButton.setOnAction(new EventHandler<ActionEvent>() {
                                              public void handle(ActionEvent actionEvent) {
                                                  loadDashboard(actionEvent, userName);
                                              }
                                          }
            );
        }
    }

    public void test(ActionEvent actionEvent) {
    }

    public void closePreviousOnAction(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
    }
}