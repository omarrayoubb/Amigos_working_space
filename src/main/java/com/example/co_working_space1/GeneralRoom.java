package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GeneralRoom extends room {
    private String[][] visitorsArray;
    private int feesPerSlot=40;
    private int places_left;
    private List<Slott> bookedSlots;
    private int bookedSlotsCounter;
    public GeneralRoom(String name, int id, int numberOfVisitors, ArrayList<Slott> slot)
    {
        super(name, id, 20, slot);
        this.bookedSlotsCounter = 0;
    }

    public GeneralRoom() {
        super();
        this.bookedSlotsCounter = 0;
    }

    private int countBookingsForHour(LocalDate date,LocalTime time) {
        int count = 0;
        for (Slott bookedSlot : bookedSlots) {
            if (bookedSlot.getTime().equals(time)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkAvailability(LocalDate date, LocalTime time) {
        int rooms=2;
        int bookedRooms = 0;
        for (room s: FileManagment.roooms) {
            /*for (int i = 0 ; i < 8; i ++)
            {
                if (Main.roooms.get(i).getName().equalsIgnoreCase("General Room"))
             }
             */

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
                                //checkListOfVisitors(v);
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }


    public double calculateFees(LocalDate date) {

        double totalFees = this.bookedSlotsCounter * feesPerSlot;

        return totalFees;
    }
  /*
    public boolean numberOfVisitors()
    {

    }
   */
}