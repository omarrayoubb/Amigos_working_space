package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

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
        ArrayList<Reservation> resData = new ArrayList<>(FileManagment.Reservations);

        Iterator<Visitor> usrIterator = FileManagment.usrData.iterator();
        while (usrIterator.hasNext()) {
            Visitor s = usrIterator.next();
            if (s.getId() == id) {
                x = true;
                Name = s;
                usrIterator.remove();
                break;
            }
        }

        for (Reservation m : resData) {
            if (Name.getUsername().equalsIgnoreCase(m.getVisitorName()) && m.getDate().compareTo(LocalDate.now()) >= 0) {
                for (room s : FileManagment.roooms) {
                    if (s.getId() == m.getRoom_id()) {
                        Iterator<Slott> slotIterator = s.slots.iterator();
                        while (slotIterator.hasNext()) {
                            Slott r = slotIterator.next();
                            if (m.getDate().equals(r.getDate()) && m.getTime().equals(r.getTime())) {
                                if (!r.getAvailability()) {
                                    s.openRoom(s.getName(), m.getDate(), m.getTime());
                                }
                                r.setSlotsCounter(r.getSlotsCounter() + 1);

                                Iterator<Reservation> reservationIterator = FileManagment.Reservations.iterator();
                                while (reservationIterator.hasNext()) {
                                    Reservation reservation = reservationIterator.next();
                                    if (reservation.equals(m)) {
                                        reservationIterator.remove();
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        return x;
    }



    public boolean deleteRoom(int id) {
        boolean deleted = false;

        Iterator<room> roomIterator = FileManagment.roooms.iterator();
        while (roomIterator.hasNext()) {
            room s = roomIterator.next();
            if (s.getId() == id) {
                roomIterator.remove();
                deleted = true;
                break;
            }
        }

        Iterator<Reservation> reservationIterator = FileManagment.Reservations.iterator();
        while (reservationIterator.hasNext()) {
            Reservation s = reservationIterator.next();
            if (s.getRoom_id() == id) {
                reservationIterator.remove();
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
        boolean userName = false;
        String username = new String();
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
                    username = v.getUsername();
                    v.setUsername(edit);
                    deleted = true;
                    userName = true;
                }
                else if (field.equalsIgnoreCase("type"))
                {
                    v.setType(edit);
                    deleted = true;
                }
            }
            if (userName)
            {
                for (Reservation s: FileManagment.Reservations)
                {
                    if (s.getVisitorName().equalsIgnoreCase(username))
                    {
                        s.setVisitorName(edit);
                    }
                }
            }
        }

        return deleted;
    }
}