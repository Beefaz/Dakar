package sample.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.Vartotojas;
import sample.model.VartotojasDAO;
import sample.utils.Validation;

public class Controller {
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
    Button testButton;

    public void loadLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/loginScreen.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadRegistration() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Registration.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registration");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDashboard() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dakar");
            stage.setScene(new Scene(root, 1060, 710));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginErrorCheck() {
        String userName = logUsernameField.getText();
        String loginPass = logPasswordField.getText();
        if (!Validation.isValidUserName(userName)) {
            logErrorField.setText("Invalid username. Minimum 5 Uppercase, lowercase or numeric characters.");
            logErrorField.setVisible(true);
        } else if (!VartotojasDAO.selectUsername(userName).toString().contains(userName)) {
            logErrorField.setText("Username does not exist.");
            logErrorField.setVisible(true);
        } else if (!Validation.isValidPassword(loginPass)) {
            logErrorField.setText("Invalid password. Minimum 5 Uppercase, lowercase or numeric characters.");
            logErrorField.setVisible(true);
        } else if (!VartotojasDAO.selectUsernamePass(userName).toString().contains(loginPass)) {
            logErrorField.setText("Wrong password");
            logErrorField.setVisible(true);
        } else {
            loadDashboard();
        }
    }

    public void registrationErrorCheck() {
        String userName = regUsernameField.getText();
        String regPass = regPasswordField.getText();
        String regPass1 = regPasswordField1.getText();
        String email = regEmailField.getText();
        if (!Validation.isValidUserName(userName)) {
            regErrorField.setText("Invalid username. Minimum 5 Uppercase, lowercase or numeric characters.");
            regErrorField.setVisible(true);
        } else if (VartotojasDAO.selectUsername(userName).toString().contains(userName)) {
            regErrorField.setText("Username already exists.");
            regErrorField.setVisible(true);
        } else if (!Validation.isValidEmail(email)) {
            regErrorField.setText("Invalid email. Example: mail@example.com");
            regErrorField.setVisible(true);
        } else if (!Validation.isValidPassword(regPass) && !Validation.isValidPassword(regPass1)) {
            regErrorField.setText("Invalid password. Minimum 5 Uppercase, lowercase or numeric characters.");
            regErrorField.setVisible(true);
        } else if (!regPass.equals(regPass1)) {
            regErrorField.setText("Passwords don't match.");
            regErrorField.setVisible(true);
        } else {
            Vartotojas vartotojas = new Vartotojas(userName, regPass, email);
            VartotojasDAO.insert(vartotojas);
            regErrorField.setTextFill(Color.GREEN);
            regErrorField.setText("Registration successful.");
            regErrorField.setVisible(true);
            regRegisterButton.setText("Login");
            regRegisterButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
                                                     @Override
                                                     public void handle(MouseEvent mouseEvent) {
                                                         loadDashboard();
                                                     }
                                                 }
            );
        }
    }
}