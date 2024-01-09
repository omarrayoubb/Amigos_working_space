package com.example.co_working_space1;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class MeetingRoom extends room {

    private int number_of_Visitors;

    public MeetingRoom(String name, int id, int numberOfVisitors, ArrayList<Slott> slots) {
        super(name, id, numberOfVisitors,slots);

    }

    public MeetingRoom() {

    }
    public int getNumber_of_Visitors() {
        return number_of_Visitors;
    }

    public void setNumber_of_Visitors(int number_of_Visitors) {
        this.number_of_Visitors = number_of_Visitors;
    }


    public ArrayList<Slott> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slott> slots) {
        this.slots = slots;
    }

    public boolean checkVisitors( Visitor visitor) {
        int numberOfVisitors = numberofvistors;

        if (numberOfVisitors <= 10) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double calculateFees() {
        int numberOfVisitors = getNumberofvistors();
        double feePerSlot = 100.0;
        double totalFees = 0.0;

        for (Slott slot : slots) {
            if (!slot.getAvailability()) {
                totalFees += feePerSlot;
            }
        }
        return totalFees;
    }

    public boolean bookSlot(LocalDate date, LocalTime time) {
        for (room s : FileManagment.roooms) {
            if (s.getName().equalsIgnoreCase("Meeting Room")) {
                for (Slott slot : s.slots) {
                    if (slot.getDate().equals(date) && slot.getTime().equals(time)) {
                        if (slot.getAvailability()) {
                            slot.setAvailability(false);
                            slot.setSlotsCounter(slot.getSlotsCounter() - 1);
                            this.id = s.getId();
                            if (!checkForComplete(s.getName(), date, time))
                            {
                                changeAvailability(s.getName(), date, time);
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}

