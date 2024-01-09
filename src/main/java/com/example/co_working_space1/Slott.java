package com.example.co_working_space1;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Slott implements Serializable {
    public LocalTime Time;
    public LocalDate Date;
    public double Fees;
    public  boolean Available;
    public int SlotsCounter;


    /*
    NumberOfReservations --> kam mra el slot de momkn tt7gz y3ny lw general room momkn el slot tt7gz mrteen
    lw teaching aw meeting momkn tt7gz 3
     double HourPrice --> hayt7t bdlha el method bta3t el price per hour elly mawgoda fe class el room
    */
    public Slott(int NumberOfReservations, double HourPrice) {

        this.Time = LocalTime.MIN;
        this.Date = LocalDate.MIN;
        this.Fees = HourPrice;
        this.Available = true;
        this.SlotsCounter = NumberOfReservations;

    }

    public Slott() {

    }

    public static  ArrayList<Slott> CreateListOfSlots(int NumberOfReservations, double HourPrice){
        Duration duration =  Duration.between(DailyWorkspaceInfo.OpeningHour,DailyWorkspaceInfo.ClosingHour);
        long hours = duration.toHours();
        int SlotsPerMonth=(int)hours*32;

        ArrayList<Slott> Slots = new ArrayList<>((int) SlotsPerMonth);

        long DaysIncrement = 0;
        long HourIncrement = 0;

        for (int i = 0; i <SlotsPerMonth; i++) {

            Slott slot = new Slott(NumberOfReservations,HourPrice);

            LocalTime x = DailyWorkspaceInfo.OpeningHour.plusHours(HourIncrement);
            LocalDate y = DailyWorkspaceInfo.CurrentDate.plusDays(DaysIncrement);

            slot.setTime(x);
            slot.setDate(y);
            Slots.add(slot);

            if (x.equals(DailyWorkspaceInfo.getClosingHour().minusHours(1))) {

                HourIncrement = 0;
                DaysIncrement = DaysIncrement + 1;

            } else {

                HourIncrement = HourIncrement + 1;

            }
        }

        return Slots;
    }
    public static void DisplayListOfSlots(ArrayList<Slott> slot){
        for (int i = 0; i < slot.size(); i++) {
            System.out.println(slot.get(i).getDate() + "\t" + slot.get(i).getTime() + "\t" +slot.get(i).getAvailability()+"\t" +slot.get(i).getSlotsCounter()+"\t" +slot.get(i).getFees());
        }
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setAvailability(boolean available) {
        Available = available;
    }

    public boolean getAvailability() {
        return Available;
    }

    public void setSlotsCounter(int x) {SlotsCounter = x;}

    /*
    public static void DecrementSlotsCounter() {
        SlotsCounter--;
        if (SlotsCounter <= 0) {
            Available =  false;
        } else {
            Available = true;
        }
    }

     */

    public int getSlotsCounter() {return SlotsCounter;}

    public void setFees(double fees) {
        Fees = fees;
    }

    public double getFees() {
        return Fees;
    }

    public void DisplaySlotInfo(){
        System.out.println(getDate() + "\t" + getTime() + "\t" +getAvailability()+"\t" +getSlotsCounter()+"\t" +getFees());
    }
    //  CheckSlotAvailability --> method fe classes el rooms msh hna feel slott
    public void CheckSlotAvailability(LocalDate date , LocalTime time , ArrayList<Slott> slot){
        for (int i = 0; i < 360; i++) {
            if (slot.get(i).getDate() == date && slot.get(i).getTime() == time) {
                if (slot.get(i).getAvailability() == true) {
                    slot.get(i).DisplaySlotInfo();
                } else {
                    System.out.println("Not Available");
                }
            }

        }

    }
    public  static ArrayList<String> changeType(ArrayList<Slott> availableSlott)
    {
        ArrayList<String> timeInString = new ArrayList<>();
        for (Slott s: availableSlott)
        {
            timeInString.add(s.getTime().toString());
        }
        return timeInString;
    }
}