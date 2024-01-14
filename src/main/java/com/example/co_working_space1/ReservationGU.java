package com.example.co_working_space1;

import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReservationGU {

    public Button Log_out_id;
    public VBox availableTime;
    public ListView Time;
    public Button home_id1;
    public Button Log_out_id1;
    public Label error2;
    public TableView reservationTable;
    public TableColumn resType;
    public TableColumn resID;
    public TableColumn resDate;
    public TableColumn resTime;
    public Button confirm;
    public AnchorPane reservationPage;
    public Button home_id110;
    public Button Log_out_id110;
    public AnchorPane agreePage;
    public Button Yes;
    public Button No;
    public Button deleteButton;
    public Button editRes;
    public AnchorPane agreePage1;
    public Button Yes1;
    public Button No1;
    public Button myReservations_id;
    public Button myReservations_id1;
    public Label nov;
    public TableColumn resPrice;
    int numberOfVisitors;
    int timeInNumbers;
    public TextField numberofVisitors;
    public DatePicker datee;
    public Button addSlot;
    public TextField time;
    public AnchorPane revPage;
    public AnchorPane hmPage;
    @FXML
    private Button home_id;
    protected String Room_type;
    Stage stage;
    Users usrScene = new Users();
    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;
    Reservation reserve;
    ArrayList<Slott> availableSlots;
    Reservation selectedItem;

    private static final double ZOOM_FACTOR = 1.2;

    LocalDate dateee;
    ArrayList<String> timeInString;
    ObservableList<Reservation> reservation;
    int makeReservation;


    @FXML
    private void initialize() {
        // Attach event handlers during initialization
        attachHoverEvents(image1);
        attachHoverEvents(image2);
        attachHoverEvents(image3);
        Room_type = "Not Assigned Yet";

        image1.setOnMouseClicked(event -> AssignRoomType(image1));
        image2.setOnMouseClicked(event -> AssignRoomType(image2));
        image3.setOnMouseClicked(event -> AssignRoomType(image3));
        // Attach similar events for other images
        reserve = new Reservation();
        timeInNumbers = 0;
        numberOfVisitors = 0;
        dateee = LocalDate.MIN;
        availableSlots = new ArrayList<Slott>();
        availableTime.setVisible(false);
        timeInString = new ArrayList<>();
        Time = new ListView();
        reservation = FXCollections.observableArrayList();
        makeReservation = 0;

    }

    public void attachHoverEvents(ImageView imageView) {
        imageView.setOnMouseEntered(event -> handleMouseEntered(event, imageView));
        imageView.setOnMouseExited(event -> handleMouseExited(event, imageView));
    }

    private void handleMouseEntered(MouseEvent event, ImageView imageView) {
        applyZoomEffect(imageView);
    }

    private void handleMouseExited(MouseEvent event, ImageView imageView) {
        removeZoomEffect(imageView);
    }

    private void applyZoomEffect(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setToX(ZOOM_FACTOR);
        scaleTransition.setToY(ZOOM_FACTOR);
        scaleTransition.play();

    }

    private void removeZoomEffect(ImageView imageView) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();
    }

    public void AssignRoomType(ImageView img) {
        if (img == image1) {
            Room_type = "General Room";
            nov.setVisible(false);
            numberofVisitors.setVisible(false);

        } else if (img == image2) {
            Room_type = "Teaching Room";
            nov.setVisible(true);
            numberofVisitors.setVisible(true);
        } else {
            Room_type = "Meeting Room";
            nov.setVisible(true);
            numberofVisitors.setVisible(true);
        }
        hmPage.setVisible(false);
        revPage.setVisible(true);

    }

    public void Logout(ActionEvent event) {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == Log_out_id) {
            usrScene.changeScene("Login.fxml", stage);
        } else if (event.getSource() == Log_out_id1) {
            datee.setValue(null);
            availableTime.getChildren().clear();
            usrScene.changeScene("Login.fxml", stage);
        } else if (event.getSource() == Log_out_id110) {
            datee.setValue(null);
            availableTime.getChildren().clear();
            reservationTable.getItems().clear();
            usrScene.changeScene("Login.fxml", stage);

        }
    }

    public void homePage(ActionEvent event) {
        if (event.getSource() == home_id1) {
            clearRevPage();
            hmPage.setVisible(true);
            revPage.setVisible(false);

        }
        if (event.getSource() == home_id) {
            hmPage.setVisible(true);
            revPage.setVisible(false);
        }
        if (event.getSource() == home_id110) {
            datee.setValue(null);
            availableTime.getChildren().clear();
            reservationTable.getItems().clear();
            hmPage.setVisible(true);
            reservationPage.setVisible(false);
        }
    }

    public void makeReservation(ActionEvent event) {
        /*
            this part get the available slot for the user to show it in a list
            take availableslots from reservation function
            convert it to string
            convert arrayof strings to a observable list to show it in a list and show it
            if the date is correct and valid
         */
        if (event.getSource() == datee) {

            dateee = datee.getValue();
            if (dateee == null) {
                dateee = LocalDate.MIN;
            }
            if (Visitor.CheckDate(dateee)) {
                error2.setVisible(false);

                availableSlots = reserve.showAvailableSlots(dateee, Room_type);
                timeInString = Slott.changeType(availableSlots);
                availableTime.setPadding(new Insets(10));
                Time.getItems().clear();
                availableTime.getChildren().clear();

                ObservableList<String> observableList = FXCollections.observableArrayList(timeInString);
                Time.setItems(observableList);
                Time.setStyle("-fx-background-color:  #164863");
                availableTime.getChildren().addAll(Time);

                Time.setOnMouseClicked(e -> {
                    try {
                        String TimeSelected = (String) Time.getSelectionModel().getSelectedItem();
                        TimeSelected = TimeSelected.substring(0, 2);
                        timeInNumbers = Integer.parseInt(TimeSelected);
                    }
                    catch (Exception exception)
                    {
                        error2.setText("Please Choose A number");
                        error2.setVisible(true);

                    }
                    });
                availableTime.setVisible(true);

            } else {
                availableTime.setVisible(false);
                error2.setText("Please Choose A valid date");
                error2.setVisible(true);
            }
        } else {
            try {
                /*
                    next if condition to check for if room is general room to set number of visitors to 1 if true
                 */
                if (Room_type.equalsIgnoreCase("General Room"))
                    numberOfVisitors = 1;
                else
                    numberOfVisitors = Integer.parseInt(numberofVisitors.getText());
                makeReservation = Users.vs.makeReservation(Room_type, dateee, timeInNumbers, numberOfVisitors);
                if (makeReservation == 0) {
                    System.out.println("Reservation great");
                    if (event.getSource() == confirm) {
                        clearRevPage();
                        revPage.setVisible(false);
                        myReservations();
                        reservationPage.setVisible(true);
                    }
                    else if (event.getSource() == addSlot)
                    {
                        error2.setText("Slot has added successfully");
                        error2.setVisible(true);
                    }
                } else if (makeReservation == -4){
                    error2.setText("Number of Visitors is less than 10");
                    error2.setVisible(true);
                    System.out.println("No reservation are done");
                }
                else if (makeReservation == -2)
                {
                    error2.setText("You had Reserved this slot");
                    error2.setVisible(true);
                }
                else {
                    error2.setVisible(true);
                    error2.setText("Please Enter time after " + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
                }
            }
            catch (Exception exception)
            {
                error2.setText("Please Enter Number of Visitors");
                error2.setVisible(true);
            }
        }

    }

    /*
     * this function get all reservation from reservation table to put in a table to show user Reservation
     * w btbd2 t3red kul attribute lw7do
     */
    public void myReservations() {
        ArrayList<Reservation> r = reserve.UserReservations(Users.vs.getUsername());
        Collections.sort(r);
        reservation = FXCollections.observableArrayList(r);
        reservationTable.setItems(reservation);

        resDate.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("Date")); // Use "Date" instead of "date"
        resID.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("room_id"));
        resTime.setCellValueFactory(new PropertyValueFactory<Reservation, LocalTime>("Time"));
        resType.setCellValueFactory(new PropertyValueFactory<Reservation, String>("Room_type"));
        resPrice.setCellValueFactory(new PropertyValueFactory<Reservation, Double>("Fees"));
        reservationTable.refresh();
        System.out.println(reservation.size());
    }


    /*
     * Delete Reservation: Delete Reservation of user if he wants to cancel the reservation
     *  event: when the user clicks on Delete Reservation this method happens
     * @Description: make the reserve variable new to take the reservation data he wants to change
     * save the date and time that the user stand over them
     * Call deleteReservation function from Visitor class
     */

    public void DeleteReservation(ActionEvent event) {

        reserve = new Reservation();
        selectedItem = (Reservation) reservationTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            reserve.setDate(selectedItem.getDate());
            reserve.setTime(selectedItem.getTime());

            // Show agreePage and hide reservationTable

            if (event.getSource() == deleteButton) {
                agreePage.setVisible(true);
                reservationTable.setVisible(false);
            } else {
                agreePage.setVisible(false);
                reservationTable.setVisible(true);
            }
            // Check if the event source is the "Yes" button in agreePage
            if (event.getSource() == Yes) {
                // Perform deletion logic
                if (Users.vs.deleteReservation(reserve.getDate(), reserve.getTime()) == 0) {
                    myReservations();
                }

            }
            }
        }
        public void showReservations(ActionEvent event)
        {
            if (event.getSource() == myReservations_id)
            {
                hmPage.setVisible(false);
                    myReservations();
                    reservationPage.setVisible(true);
            }
            else if (event.getSource() == myReservations_id1)
            {
                clearRevPage();
                revPage.setVisible(false);
                myReservations();
                reservationPage.setVisible(true);
            }
        }
        public void clearRevPage()
        {
            datee.setValue(null);
            availableTime.getChildren().clear();
            availableTime.setVisible(false);
            error2.setVisible(false);
            numberofVisitors.clear();
        }
        public void editReservation(ActionEvent event) {
            reserve = new Reservation();
            selectedItem = (Reservation) reservationTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                reserve.setRoom_type(selectedItem.getRoom_type());
                reserve.setDate(selectedItem.getDate());
                reserve.setTime(selectedItem.getTime());

                if (event.getSource() == editRes) {
                    agreePage1.setVisible(true);
                    reservationTable.setVisible(false);

                }
                if (event.getSource() == Yes1) {
                    if (Users.vs.deleteReservation(reserve.getDate(), reserve.getTime()) == 0) {
                        agreePage1.setVisible(false);
                        reservationTable.setVisible(true);
                        reservationPage.setVisible(false);
                        revPage.setVisible(true);
                    }
                }
                else if (event.getSource() == No1)
                {
                    agreePage1.setVisible(false);
                    reservationTable.setVisible(true);
                }

            }
        }
}