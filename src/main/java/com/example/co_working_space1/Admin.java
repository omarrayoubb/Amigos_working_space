package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Admin extends Person {
    public Admin() {
        super();
        setUsername("admin");
        setPassword("admin");
    }

    @Override
    public int login(String username, String password) {
        return 0;
    }

    public ArrayList<Visitor> DisplayInstructorData() {
        ArrayList<Visitor> r1 = new ArrayList<>();
        for (Visitor s : FileManagment.usrData) {
            if (s.getType().equalsIgnoreCase("instructor")) {
                r1.add(s);
            }
        }
        return r1;
    }

    public boolean deleteUser(int id) {
        boolean x = false;
        Visitor Name = new Visitor();
        for (Visitor s : FileManagment.usrData) {
            if (s.getId() == id) {

                x = true;
                Name = s;
                FileManagment.usrData.remove(s);
                break;
            }
        }
        for (Reservation s : FileManagment.Reservations) {
            if (Name.getUsername().equalsIgnoreCase(s.getVisitorName()) && s.getDate().compareTo(LocalDate.now()) >= 0) {
                Name.deleteReservation(s.getDate(), s.getTime());
                return x;
            }
        }
        return x;
    }

    public boolean deleteRoom(int id) {
        boolean deleted = false;
        for (room s : FileManagment.roooms) {
            if (s.getId() == id) {
                FileManagment.roooms.remove(s);
                deleted = true;
                break;

            }
        }
        for (Reservation s : FileManagment.Reservations) {
            if (s.getRoom_id() == id) {
                FileManagment.Reservations.remove(s);
            }
        }
        return deleted;
    }

    public boolean deleteReservation(LocalDate date, LocalTime time, int roomId) {
        boolean deleted = false;
        for (Reservation s : FileManagment.Reservations) {
            if (date.equals(date) && time.equals(time) && roomId == s.getRoom_id()) {
                FileManagment.Reservations.remove(s);
                deleted = true;
                break;
            }
        }
        for (room s : FileManagment.roooms) {
            if (s.getId() == roomId) {
                for (Slott m : s.slots) {
                    if (m.getDate().equals(date) && m.getTime().equals(time)) {
                        if (!m.getAvailability()) {
                            s.openRoom(s.getName(), date, time);
                        }
                        m.setSlotsCounter(m.getSlotsCounter() + 1);
                        break;
                    }

                }
            }
        }
        return deleted;
    }
    public boolean EditUser(String edit, String field, int id)
    {
        boolean deleted = false;
        for (Visitor v: FileManagment.usrData)
        {
            if (v.getId() == id)
            {
                if (field.equalsIgnoreCase("password"))
                {
                    v.setPassword(edit);
                    deleted = true;
                }
                else if(field.equalsIgnoreCase("Username"))
                {
                    v.setUsername(edit);
                    deleted = true;
                }
                else if (field.equalsIgnoreCase("type"))
                {
                    v.setType(edit);
                    deleted = true;
                }
            }
        }
        return deleted;
    }
}