package com.example.co_working_space1;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class MeetingRoom extends room {


    public MeetingRoom(String name, int id, int numberOfVisitors, ArrayList<Slott> slots) {
        super(name, id, numberOfVisitors,slots);

    }

    public MeetingRoom() {

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

    public boolean checkAvailability(LocalDate date, LocalTime time) {
        for (room s : FileManagment.roooms) {
            if (s.getName().equalsIgnoreCase("Meeting Room")) {
                for (Slott slot : s.slots) {
                    if (slot.getDate().equals(date) && slot.getTime().equals(time)) {
                        if (slot.getAvailability() && slot.getSlotsCounter() > 0) {
                            slot.setSlotsCounter(slot.getSlotsCounter() - 1);
                            this.id = s.getId();
                            this.fees = slot.getFees();
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

