package com.example.co_working_space1;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1050, 600);
        Image iconImage = new Image(HelloApplication.class.getResourceAsStream("Black_And_White_Aesthetic_Minimalist_Modern_Simple_Typography_Coconut_Cosmetics_Logo_1.png"));
        stage.getIcons().add(iconImage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
/*
       Visitor v = new Visitor("Omar", "20102003Omar", "Formal");
       ArrayList<Visitor> usrData = new ArrayList<Visitor>();
        usrData.add(v);
        FileManagment.WriteUsers();

        Reservation reserve = new Reservation(LocalDate.of(2023, 12, 15), LocalTime.of(7,0), "Omar", LocalDate.of(2023, 12, 15), LocalTime.of(7, 0), 1);
        FileManagment.Reservations.add(reserve);
        FileManagment.arrayDeclaration();
        FileManagment.WriteUsers();



        for (Visitor s : FileManagment.usrData)
        {
            System.out.println(s.getUsername() + " " + s.getPassword() + " " + s.getType());
        }
        for (room s : FileManagment.roooms)
        {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getNumberofvistors());
        }
        for (Reservation s : FileManagment.Reservations)
        {
            System.out.println(s.getDate() + " " + s.getTime() + " " + s.getRoom_type());
        }

*/
        FileManagment.ReadUsers();
        launch();
        FileManagment.WriteUsers();

    }
}