package com.example.co_working_space1;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
import org.controlsfx.control.action.Action;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.*;

public class Users implements Initializable {

    public Label regText;
    public Button users;
    public Button DeleteUser;
    public Button reservationns;
    public Button roomm;
    public TableView roomDataa;
    public AnchorPane roomPage;
    public TableColumn name;
    public TableColumn id2;
    public TableColumn numberOfVis;
    public AnchorPane visPage;
    public Button deleteRooms;
    public Button editRoom;
    public AnchorPane resPage;
    public TableView ReservationsData;
    public TableColumn id1;
    public TableColumn userName;
    public TableColumn roomTypee;
    public TableColumn reservationDate;
    public TableColumn reservationTime;
    public Button DeleteReservation;
    public TableColumn reservationFees;
    public Button editData;
    public TextField editField = new TextField();
    public String newEdit;
    Admin adminn;
    public AnchorPane admin;
    public AnchorPane lab;
    public TableView visitorsData;
    public TableColumn id;
    public TableColumn usernamme;
    public TableColumn passwordd;
    public TableColumn adminType;
    public Button logout;
    public Button insData;
    String username, password, typee;
    String[] types = {"General", "Formal", "Instructor"};
    String[] userOptions = {"Username", "Password", "Type"};
    
    static Stage stage = new Stage();
    ArrayList<Visitor> usrData;
    DatePicker date;
    Visitor SelectedItem;
    Reservation SelectedReservation;
    ArrayList<Reservation> res;
    ObservableList resData;

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
    ChoiceBox<String> editVisitorOptions = new ChoiceBox<String>();
    @FXML
    Label fail;
    @FXML
    CheckBox showPassword;
    @FXML
    Hyperlink switchToRegister, switchToLogin;
    ObservableList<Visitor> visData;
    ObservableList<room> roomData;
    room SelectedRoom;
    ArrayList<room> roooms;
    String opt;
    private static final double ZOOM_FACTOR = 1.2;


    public Users() {
        visData = FXCollections.observableArrayList();
        adminn = new Admin();
        usrData = new ArrayList<>();
        SelectedItem = new Visitor();
        SelectedRoom= new room();
        roomData = FXCollections.observableArrayList();
        roooms = new ArrayList<>();
        SelectedReservation = new Reservation();
        resData = FXCollections.observableArrayList();
        res = new ArrayList<>();
        opt = new String();
        newEdit = new String();
    }

    public Users(Stage stage) {
        this.stage = stage;
    }



    public void log(ActionEvent Event) {

        username = user.getText();
        password = pass.getText();
        vs = new Visitor();
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
                showVisitorsData();
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
            vs = new Visitor();
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
             if (event.getSource() == switchToLogin)
            {
            changeScene("Login.FXML", stage);

            }
             else {
                 // check this event is button or choiceBox (handling this event)
                 if (event.getSource() == type) {
                     typee = type.getValue();
                 }
                 typee = type.getSelectionModel().getSelectedItem();
                 System.out.println(typee);
                 if (event.getSource() == switchToLogin) {
                     changeScene("Login.fxml", stage);
                 }
                 int Register = vs.Register(username, password, typee);
                 if (Register == 0) {
                     System.out.println(username + password);
                     x = true;
                     System.out.println("User has registered successifully");
                 } else if (Register == -3) {
                     regText.setText("This Username is not available");
                     regText.setVisible(true);
                     System.out.println("This Username Has been Taken");

                 } else if (Register == -1) {
                     regText.setText("Please Choose a strong password");
                     regText.setVisible(true);
                 } else {
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
            }
            public void showVisitorsData() {
                ActionEvent event = new ActionEvent();
                usrData = FileManagment.usrData;
                visData = FXCollections.observableArrayList(usrData);
                visitorsData.setItems(visData);

                insData.setOnAction(e -> {
                    usrData = adminn.DisplayInstructorData();
                    visData = FXCollections.observableArrayList(usrData);
                    visitorsData.setItems(visData);
                });

                users.setOnAction(e-> {
                    visPage.setVisible(true);
                    roomPage.setVisible(false);
                    resPage.setVisible(false);
                    usrData = FileManagment.usrData;
                    visData = FXCollections.observableArrayList(usrData);
                    visitorsData.setItems(visData);

                });
                DeleteUser.setOnAction(e -> {
                    try {
                        SelectedItem = (Visitor) visitorsData.getSelectionModel().getSelectedItem();
                        System.out.println(SelectedItem.getId());
                        if (SelectedItem != null) {
                            adminn.deleteUser(SelectedItem.getId());
                            usrData = FileManagment.usrData;
                            visData = FXCollections.observableArrayList(usrData);
                            visitorsData.setItems(visData);
                        }
                    }
                    catch(Exception exception)
                    {
                        usrData = FileManagment.usrData;
                        visData = FXCollections.observableArrayList(usrData);
                        visitorsData.setItems(visData);
                    }
                });
                editData.setOnAction(e -> {
                    if (opt.isEmpty())
                        editVisitorOptions.setVisible(true);
                    else {
                        if (editField.getText() != null) {
                            newEdit = editField.getText();

                            try {
                                SelectedItem = (Visitor) visitorsData.getSelectionModel().getSelectedItem();
                                System.out.println(SelectedItem.getId());
                                if (SelectedItem != null) {
                                    if (adminn.EditUser(newEdit, opt, SelectedItem.getId()))
                                    {
                                        opt = new String();
                                        editField.setVisible(false);
                                        editVisitorOptions.setVisible(false);
                                    }
                                    usrData = FileManagment.usrData;
                                    visData = FXCollections.observableArrayList(usrData);
                                    visitorsData.setItems(visData);
                                    visitorsData.refresh();
                                }
                            } catch (Exception exception) {
                                usrData = FileManagment.usrData;
                                visData = FXCollections.observableArrayList(usrData);
                                visitorsData.setItems(visData);
                            }
                        }
                    }

                });
                editVisitorOptions.setOnAction(e -> {
                    opt = editVisitorOptions.getValue();
                    editField.setVisible(true);

                    System.out.println(opt);
                });

                usernamme.setCellValueFactory(new PropertyValueFactory<Visitor, String>("username"));
                id.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("id"));
                adminType.setCellValueFactory(new PropertyValueFactory<Visitor, String>("type"));
                passwordd.setCellValueFactory(new PropertyValueFactory<Visitor, String>("password"));
                visitorsData.refresh();
            }


            public void LogOut(ActionEvent event)
            {
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                if (event.getSource() == logout)
                {
                    changeScene("Login.fxml", stage);
                }
            }
            public void showRoomData(ActionEvent event)
            {

                if (event.getSource() == roomm) {
                    roomPage.setVisible(true);
                    visPage.setVisible(false);
                    resPage.setVisible(false);
                    roooms = FileManagment.roooms;
                    roomData = FXCollections.observableArrayList(roooms);
                    roomDataa.setItems(roomData);

                }
                    deleteRooms.setOnAction(e -> {
                        try {
                            SelectedRoom = (room) roomDataa.getSelectionModel().getSelectedItem();
                            System.out.println(SelectedRoom.getId());
                            if (SelectedRoom != null) {
                                adminn.deleteRoom(SelectedRoom.getId());
                                roooms = FileManagment.roooms;
                                roomData = FXCollections.observableArrayList(roooms);
                                roomDataa.setItems(roomData);
                            }
                        }

                        catch(Exception exception)
                        {
                            roooms = FileManagment.roooms;
                            roomData = FXCollections.observableArrayList(roooms);
                            roomDataa.setItems(roomData);                        }
                    });
                roomDataa.setItems(roomData);
                name.setCellValueFactory(new PropertyValueFactory<Visitor, String>("name"));
                id2.setCellValueFactory(new PropertyValueFactory<Visitor, Integer>("id"));
                numberOfVis.setCellValueFactory(new PropertyValueFactory<Visitor, String>("numberofvistors"));
                roomDataa.refresh();

            }

    public void reservationController(ActionEvent event)
    {
        if (event.getSource() == reservationns)
        {
            visPage.setVisible(false);
            resPage.setVisible(true);
            roomPage.setVisible(false);
            res = FileManagment.Reservations;
            Collections.sort(res);
            resData = FXCollections.observableArrayList(res);
            ReservationsData.setItems(resData);
        }
                DeleteReservation.setOnAction(e -> {
                    try {
                        SelectedReservation = (Reservation) ReservationsData.getSelectionModel().getSelectedItem();
                        System.out.println(SelectedReservation.getRoom_id());
                        if (SelectedReservation != null) {
                            adminn.deleteReservation(SelectedReservation.getDate(), SelectedReservation.getTime(), SelectedReservation.getRoom_id());
                            res = FileManagment.Reservations;
                            Collections.sort(res);
                            resData = FXCollections.observableArrayList(res);
                            ReservationsData.setItems(resData);
                        }
                    }
                    catch(Exception exception)
                    {
                        res = FileManagment.Reservations;
                        resData = FXCollections.observableArrayList(res);
                        ReservationsData.setItems(resData);                        }
                });

        userName.setCellValueFactory(new PropertyValueFactory<Reservation, String>("visitorName"));
        id1.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room_id"));
        reservationDate.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("Date"));
        reservationTime.setCellValueFactory(new PropertyValueFactory<Reservation, LocalTime>("Time"));
        roomTypee.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Room_type"));
        reservationFees.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("fees"));

        ReservationsData.refresh();
    }

    // to implement the choiceBox
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.getItems().addAll(types);
        editVisitorOptions.getItems().addAll(userOptions);
        fail.setVisible(false);
        editVisitorOptions.setVisible(false);
        editField.setVisible(false);
        //switchAdminPages();
    }

}