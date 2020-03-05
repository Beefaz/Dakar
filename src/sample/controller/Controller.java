package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.*;
import sample.utils.Validation;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    @FXML
    Button testButton;

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
    @FXML
    CheckBox regAdminRightsCheckbox;

    //dashboard
    @FXML
    Button dashCreateButton;
    @FXML
    Button dashLogoutButton;
    @FXML
    Label dashUsername;
    @FXML
    Label dashErrorField;

    //dashboard table entries
    @FXML
    TextField dashTeamName;
    @FXML
    TextField dashName;
    @FXML
    TextField dashSurname;
    @FXML
    ComboBox dashTeamMembersCombobox;
    @FXML
    CheckBox checkBox1;
    @FXML
    CheckBox checkBox2;
    @FXML
    CheckBox checkBox3;
    @FXML
    CheckBox checkBox4;
    @FXML
    CheckBox checkBox5;
    @FXML
    CheckBox checkBox6;
    @FXML
    RadioButton radioButton1;
    @FXML
    RadioButton radioButton2;
    @FXML
    RadioButton radioButton3;
    @FXML
    RadioButton radioButton4;


    public void loadLogin(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/loginScreen.fxml"));
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 350, 400));
            stage.show();
            closePreviousWindow(actionEvent);
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
            closePreviousWindow(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDashboard(ActionEvent actionEvent, String userName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/Dashboard.fxml"));
            stage.setTitle("Dakaras");
            stage.setScene(new Scene(root, 1060, 710));
            stage.show();
            closePreviousWindow(actionEvent);
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
        boolean isAdmin = regAdminRightsCheckbox.isSelected();
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
            Vartotojas vartotojas = new Vartotojas(userName, regPass, userEmail, true);
            VartotojasDAO.insert(vartotojas);
            /*
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("kazkas atsitiko");
                }
            };
            timer.schedule(task, 1000);*/
            regErrorField.setTextFill(Color.GREEN);
            regErrorField.setText("Registration successful.");
            regErrorField.setVisible(true);
            regRegisterButton.setText("Login");
            regRegisterButton.setOnAction(new EventHandler<ActionEvent>() {
                                              @Override
                                              public void handle(ActionEvent actionEvent) {
                                                  loadDashboard(actionEvent, userName);
                                              }
                                          }
            );
        }
    }

    public void getTeamDetails() {
        String teamName = dashTeamName.getText();
        ArrayList nameSurname = new ArrayList();
        ArrayList sponsors = new ArrayList();
        ArrayList racingCars = new ArrayList();
        int members;
        if (dashTeamName.getText().isEmpty()) {
            dashErrorField.setText("Enter your team name");
            dashErrorField.setVisible(true);
        } else if (dashName.getText().isEmpty()) {
            dashErrorField.setText("Enter your name.");
            dashErrorField.setVisible(true);
        } else if (dashSurname.getText().isEmpty()) {
            dashErrorField.setText("Enter your surname.");
            dashErrorField.setVisible(true);
        } else if (dashTeamMembersCombobox.getSelectionModel().isEmpty()) {
            dashErrorField.setText("Select number of team members.");
            dashErrorField.setVisible(true);
        } else {
            nameSurname.add(dashName.getText());
            nameSurname.add(dashSurname.getText());
            if (checkBox1.isSelected()) {
                sponsors.add(checkBox1.getText());
            }
            if (checkBox2.isSelected()) {
                sponsors.add(checkBox2.getText());
            }
            if (checkBox3.isSelected()) {
                sponsors.add(checkBox3.getText());
            }
            if (checkBox4.isSelected()) {
                sponsors.add(checkBox4.getText());
            }
            if (checkBox5.isSelected()) {
                sponsors.add(checkBox5.getText());
            }
            if (checkBox6.isSelected()) {
                sponsors.add(checkBox6.getText());
            }
            if (radioButton1.isSelected()) {
                racingCars.add(radioButton1.getText());
            }
            if (radioButton2.isSelected()) {
                racingCars.add(radioButton2.getText());
            }
            if (radioButton3.isSelected()) {
                racingCars.add(radioButton3.getText());
            }
            if (radioButton4.isSelected()) {
                racingCars.add(radioButton4.getText());
            }
            members = Integer.valueOf(dashTeamMembersCombobox.getValue().toString());
            Dakar dakar = new Dakar(teamName, nameSurname.toString(), sponsors.toString(), racingCars.toString(), members);
            DakarDAO.insert(dakar);
            dashErrorField.setText("Registration successful.");
            dashErrorField.setTextFill(Color.GREEN);
            dashErrorField.setVisible(true);
        }
    }

    public void test(ActionEvent actionEvent) {
    }

    public void closePreviousWindow(ActionEvent actionEvent) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.hide();
    }

    public void run() {

    }
}