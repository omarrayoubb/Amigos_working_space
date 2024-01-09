package com.example.co_working_space1;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class room implements Serializable {
        protected String name;
        protected int id;
        protected int numberofvistors;
        public ArrayList<Slott> slots;
        protected ArrayList<String> listOfVisitors;

        public room(int id, ArrayList<Slott> slots) {
            this.id = id;
            this.slots = slots;
        }

        public room(String name, int id, int numberofvisitors, ArrayList <Slott> slots) {
            this.name = name;
            this.id=id;
            this.numberofvistors = numberofvisitors;
            this.slots = slots;

        }
        public room() {

        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setNumberofvistors(int numberofvistors) {
            this.numberofvistors = numberofvistors;
        }

        public int getNumberofvistors() {
            return numberofvistors;
        }

        public room(ArrayList<String> listOfVisitors) {
            this.listOfVisitors = listOfVisitors;
        }


        public  double calculateFees()
        {
            return 0.0;
        }
        public boolean checkForComplete(String type, LocalDate date, LocalTime time) {
            boolean checkAvailability = false;
            for (room r : FileManagment.roooms) {
                if (type.equalsIgnoreCase(r.getName())) {
                    for (Slott s : r.slots) {
                        if (s.getDate().equals(date) && s.getTime().equals(time))
                        {
                            if (s.getSlotsCounter() != 0)
                            {
                                checkAvailability = true;
                                return checkAvailability;
                            }
                            break;
                        }
                    }
                }
            }
            return checkAvailability;
        }
        public void changeAvailability(String type, LocalDate date, LocalTime time)
        {
            for (room r : FileManagment.roooms) {
                if (type.equalsIgnoreCase(r.getName())) {
                    for (Slott s : r.slots) {
                        if (s.getDate().equals(date) && s.getTime().equals(time))
                        {
                            s.setAvailability(false);
                        }
                    }
                }
            }
        }
        public boolean checkNumberOfVisitors(int numberOfVisitors)
        {
            if (numberOfVisitors < 10)
                return true;
            else
                return false;
        }
    }

