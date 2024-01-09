package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DailyWorkspaceInfo {
    // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EE dd/HH/yyyy");
    public static LocalTime OpeningHour=LocalTime.parse("10:00");
    public static LocalTime ClosingHour=LocalTime.parse("22:00");
    public static LocalDate CurrentDate=LocalDate.now();
    public static LocalTime CurrentTime=LocalTime.now();

    public DailyWorkspaceInfo(){

        OpeningHour=LocalTime.parse("10:00");
        ClosingHour=LocalTime.parse("22:00");
        CurrentTime=LocalTime.now();
        CurrentDate=LocalDate.now();
    }

    public static LocalTime getTime(){
        return CurrentTime;
    }

    public static LocalDate getDate(){
        return CurrentDate;
    }
    public static LocalTime getOpeningHour(){
        return OpeningHour;
    }
    public static LocalTime getClosingHour(){
        return ClosingHour;
    }

}