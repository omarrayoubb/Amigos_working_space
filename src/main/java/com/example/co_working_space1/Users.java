package com.example.co_working_space1;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class Users implements Initializable {

    public Label regText;
    public AnchorPane admin;
    public AnchorPane lab;
    String username, password, typee;
    String[] types = {"General", "Formal", "Instructor"};
    static Stage stage = new Stage();
    Scene scene;
    DatePicker date;

    public static Visitor vs = new Visitor();

    @FXML
    TextField user, newuser;

    @FXML
    PasswordField pass, newPassword;
    @FXML
    Button logg, bt;
    @FXML
    Button register;
    @FXML
    ChoiceBox<String> type = new ChoiceBox<String>();
    @FXML
    Label fail;
    @FXML
    CheckBox showPassword;
    @FXML
    Hyperlink switchToRegister, switchToLogin;
    private static final double ZOOM_FACTOR = 1.2;


    public Users() {
    }

    public Users(Stage stage) {
        this.stage = stage;
    }



    public void log(ActionEvent Event) {

        username = user.getText();
        password = pass.getText();
        Stage stage1 = (Stage)((Node) Event.getSource()).getScene().getWindow();

        if (Event.getSource() == switchToRegister)
        {
            changeScene("Register.fxml",stage1);
        }
        else {
            if (username.isEmpty() || password.isEmpty()) {
                fail.setText("Username and Password are required");
                fail.setVisible(true);
                fail.setTextFill(BEIGE);
            }
            else if (user.getText().equalsIgnoreCase("admin") && pass.getText().equalsIgnoreCase("admin"))
            {
                lab.setVisible(false);
                admin.setVisible(true);
            }
            else {
                if (vs.login(username, password) == 0) {

                    System.out.println("User has logged in successfully");
                    changeScene("Home_Page.fxml", stage1);
                } else {
                    stage1.getScene().getWindow();
                    fail.setText("Username or Password Are incorrect");
                    fail.setVisible(true);
                    fail.setTextFill(BEIGE);
                    pass.setStyle("-fx-border-color: #570303; -fx-border-width: 2.5;");
                    user.setStyle("-fx-border-color: #570303; -fx-border-width: 2.5;");
                    user.deleteText(0, user.getLength());
                    pass.deleteText(0, pass.getLength());
                    System.out.println("Login failed");
                }
            }
        }
    }
   /**
    public void homePage(ActionEvent event)
    {
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == logg)
        {
            changeScene("Login.fxml", stage);
        }
        else if (event.getSource() == register)
        {
            changeScene("Register.fxml", stage);
        }
    }
    */
    public void showPassword(ActionEvent event)
    {
        pass.setManaged(!showPassword.isSelected());
        pass.setVisible(!showPassword.isSelected());
    }

// method for changing scenes in user
    public void changeScene(String sceneName, Stage stage1) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = (Parent) loader.load();
            Scene newScene = new Scene(root, 1050, 600);
            stage1.setScene(newScene);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void reg(ActionEvent event) {

            boolean x = false;
            // store the user name and password

            username = newuser.getText();
            password = newPassword.getText();
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
             if (event.getSource() == switchToLogin)
            {
            changeScene("Login.FXML", stage);

            }
            // check this event is button or choiceBox (handling this event)
            if (event.getSource() == type) {
                typee = type.getValue();
            }
                typee = type.getSelectionModel().getSelectedItem();
                System.out.println(typee);
                int Register = vs.Register(username, password, typee);
                if ( Register == 0) {
                    System.out.println(username + password);
                    x = true;
                    System.out.println("User has registered successifully");
                } else if (Register == -3){
                    regText.setText("This Username is not available");
                    regText.setVisible(true);
                    System.out.println("This Username Has been Taken");

                }
                else if(Register == -1){
                    regText.setText("Please Choose a strong password");
                    regText.setVisible(true);
        }
                else {
                    regText.setText("Please Choose a valid name");
                    regText.setVisible(true);
                }
                if (x == false) {
                    stage.getScene().getWindow();
                    newuser.deleteText(0, username.length());
                    newPassword.deleteText(0, password.length());
                    type.setValue(null);
                } else {
                    changeScene("Login.FXML", stage);
                }
            }

    // to implement the choiceBox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(types);
        fail.setVisible(false);
    }

}