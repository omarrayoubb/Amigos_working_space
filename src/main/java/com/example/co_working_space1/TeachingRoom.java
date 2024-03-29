package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TeachingRoom extends room {


    public TeachingRoom() {
        super();
    }

    public TeachingRoom(int id) {
        this.id = id;
    }

    public TeachingRoom(ArrayList<TeachingRoom> teachingRooms) {
    }

    public boolean checkAvailability(LocalDate date, LocalTime time) {
        for (room s : FileManagment.roooms) {
            if (s.getName().equalsIgnoreCase("Teaching Room")) {
                for (Slott m : s.slots) {
                    if (date.equals(m.Date)) {
                        if (time.equals(m.getTime())) {
                            if (m.getSlotsCounter() > 0 && m.getAvailability()) {
                                m.setSlotsCounter(0);
                                this.id = s.getId();
                                this.fees = m.getFees();
                                if (!checkForComplete(s.getName(), date, time))
                                {
                                    changeAvailability(s.getName(), date, time);
                                }
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }
}

