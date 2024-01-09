package com.example.co_working_space1;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reservation implements Serializable {
    private LocalDate Date;
    private LocalTime Time;

    private String visitorName;
    private String Room_type;
    private int room_id = 0;

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRoom_id() {
        return room_id;
    }


    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getRoom_type() {
        return Room_type;
    }

    public void setRoom_type(String room_type) {
        Room_type = room_type;
    }



    public Reservation() {
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        this.Time = time;
    }

    public Reservation(LocalDate date, LocalTime time, String visitorName, LocalDate date1, LocalTime time1, int room_id) {
        Date = date;
        Time = time;
        this.visitorName = visitorName;
        this.room_id = room_id;
    }

    public Reservation(LocalDate date, LocalTime time, String visitorName, int room_id) {
        Date = date;
        Time = time;
        this.visitorName = visitorName;
        this.room_id = room_id;
    }

    public room setRoom(room room) {
        if (Room_type.equalsIgnoreCase("Teaching Room")) {
            room = new TeachingRoom();
            if (room instanceof TeachingRoom) {
                return (TeachingRoom) room;
            }
        } else if (Room_type.equalsIgnoreCase("Meeting Room")) {
            room = new MeetingRoom();
            if (room instanceof MeetingRoom) {
                return (MeetingRoom) room;
            }
        }
        else {
            room = new GeneralRoom();

            if (room instanceof GeneralRoom)
            {
                return (GeneralRoom) room;
            }

        }
        return null;
    }


    public void display() {
        System.out.println(visitorName);
        System.out.println(room_id);
        System.out.println(Date);
        System.out.println(Time);
    }


    public int teachingRoomReservation(room r12) {
        TeachingRoom r2 = (TeachingRoom) r12;
        boolean checkAvailability = r2.checkAvailability(Date, Time);
        if (checkAvailability == true) {
            room_id = r2.id;
            System.out.println("Successful Reservation");
            return 0;
        }
        System.out.println("Failed");
        return -1;
    }


    public int meetingRoomReservation(room r1) {
        MeetingRoom m1 = (MeetingRoom) r1;
        if (m1.bookSlot(Date, Time)) {
            room_id = m1.id;
            System.out.println("Successful Reservation");
            return 0;
        }

        return -1;
    }

    public int generalRoomReservation(room r1) {
        GeneralRoom m1 = (GeneralRoom) r1;
        if (m1.checkAvailability(Date, Time)) {
            room_id = m1.id;
            System.out.println("Successful Reservation");
            return 0;
        }

        return -1;
    }

    public boolean checkReservationx(LocalDate date, LocalTime time) {
        for (Reservation s : FileManagment.Reservations) {
            if (s.getTime().equals(time) && s.getDate().equals(date) && s.getVisitorName().equals(this.getVisitorName())) {
                return false;
            }
        }
        return true;

    }

    public ArrayList<Slott> showAvailableSlots(LocalDate date, String type) {
        ArrayList<Slott> returns = new ArrayList<>();
        for (room s : FileManagment.roooms) {
            if (s.getName().equalsIgnoreCase(type)) {
                for (Slott r : s.slots) {
                    if (r.getDate().equals(date) && r.getAvailability())
                        returns.add(r);
                }
                break;
            }
        }
        return returns;
    }

    public ArrayList<Reservation> UserReservations(String Username)
    {
        ArrayList<Reservation> reservationData = new ArrayList<Reservation>();
        for (Reservation r: FileManagment.Reservations)
        {
            if (Username.equalsIgnoreCase(r.getVisitorName()) && Visitor.CheckDate(r.getDate()))
            {
                reservationData.add(r);
            }
        }
        return reservationData;
    }
}
