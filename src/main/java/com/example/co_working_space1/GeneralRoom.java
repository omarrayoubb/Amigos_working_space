package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GeneralRoom extends room {

    public boolean checkAvailability(LocalDate date, LocalTime time) {;
        for (room s: FileManagment.roooms) {

            if (s.getName().equalsIgnoreCase( "General Room" )) {
                for (Slott m : s.slots) {
                    if (date.equals(m.Date)){
                        if (time.equals(m.getTime())){
                            if (m.getAvailability()){
                                m.setSlotsCounter(m.getSlotsCounter() - 1);
                                if (checkForComplete("General Room", date, time))
                                {
                                    changeAvailability("General Room", date, time);
                                }
                                this.id = s.getId();
                                this.fees = m.getFees();
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }
}