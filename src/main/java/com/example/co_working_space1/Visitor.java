package com.example.co_working_space1;

import java.time.LocalDate;
import java.time.LocalTime;

public class Visitor extends Person {
    private String type;
    private int id;

    public Visitor(String username, String password, String type) {
        super(username, password);
        this.type = type;
        this.id = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Visitor() {
    }

    @Override
    public int login(String username, String password) {
            for (Visitor i : FileManagment.usrData) {
                if (username.equalsIgnoreCase(i.getUsername()) && password.equals(i.getPassword())) {
                    this.setPassword(i.getPassword());
                    this.setUsername(i.getUsername());
                    id = i.id;
                    type = i.type;
                    System.out.println("User has logged in Successfully");
                    return 0;
            }
        }
            return -1;
    }

    public int Register(String userName, String Password, String type) {


        for (Visitor i: FileManagment.usrData)
        {
            System.out.println(i.getUsername());
        }

            //constraints
            if (userName.isEmpty()) {
                System.out.println("Username field is required");
                return (-2);

            }
            if (Password.isEmpty())
            {
                System.out.println("password field is required");
                return -1;

            }
            if (checkPassword(Password) == false)
            {
                return -1;
            }
            int i = checkUsername(userName);
            if (i != 0)
            {
                return i;
            }
        // Create the user
        this.setUsername(userName);
        this.setPassword(Password);
        this.type = type;
        this.id = GenID();
        FileManagment.usrData.add(this);
        return 0;

    }

    public boolean checkPassword(String password) {
        String Password = password;
        boolean x = false, y = false, z = false, checkPasswordd = false;

        if (Password.length() <= 6) {
            return checkPasswordd;
        }
        // check = 0;
        for (int i = 0; i < Password.length(); i += 1) {

            if (Password.charAt(i) >= (int) 'A' && Password.charAt(i) <= (int) 'Z') ;
            {
                x = true;
            }
            if (Password.charAt(i) >= (int) '0' && Password.charAt(i) <= (int) '9') {
                y = true;
            } else if (Password.charAt(i) >= (int) 'a' && Password.charAt(i) <= (int) 'z') {
                z = true;
            }
        }
        if (x == false || y == false || z == false) {
            System.out.println("Please Enter A strong Password that contains upper and lower case letters and numbers and symbols");
            return checkPasswordd;
        }
        checkPasswordd = true;
        return checkPasswordd;

    }

    public int checkUsername(String name)
    {
        if (name.equalsIgnoreCase("admin"))
        {
            return -2;
        }
        else
        {
            for (Visitor v : FileManagment.usrData)
            {
                if (name.equalsIgnoreCase(v.getUsername()))
                {
                    return -3;
                }
            }
        }
        return 0;
    }

    public int makeReservation(String room_type, LocalDate date, int timee, int numberOfVisitors) {
        int check = 0;
        LocalTime time = LocalTime.MIN;
        room r1 = new room();
        Reservation reserve = new Reservation();

        reserve.setRoom_type(room_type);
        time = LocalTime.of(timee, 0);

            reserve.setVisitorName(getUsername());
            reserve.setDate(date);
            reserve.setRoom_type(room_type);
            reserve.setTime(time);
            r1 = reserve.setRoom(r1);
            if (!CheckTime(date, time))
            {
                return -1;
            }
            if (!r1.checkNumberOfVisitors(numberOfVisitors))
            {
                return -4;
            }
            if (!reserve.checkReservationx(date, time)) {
                return -2;
            }
            if (r1 instanceof TeachingRoom) {
                check = reserve.teachingRoomReservation(r1);
            } else if (r1 instanceof MeetingRoom) {
                check = reserve.meetingRoomReservation(r1);
            } else if (r1 instanceof GeneralRoom) {
                check = reserve.generalRoomReservation(r1);
            }

            if (check == 0) {
                FileManagment.Reservations.add(reserve);
                reserve.display();
            }
        return 0;
    }

    public int deleteReservation(LocalDate date, LocalTime time) {
        String type = "Not assigned yet";
        for (Reservation s : FileManagment.Reservations) {
            if (s.getDate().equals(date) && s.getTime().equals(time) && s.getVisitorName().equals(getUsername())) {
                id = s.getRoom_id();
                type = s.getRoom_type();
                FileManagment.Reservations.remove(s);
                break;
            }
        }
        for (room s : FileManagment.roooms) {
            if (s.getId() == id && type.equalsIgnoreCase(s.getName())) {
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
        return 0;
    }
    public static boolean CheckDate(LocalDate date)
    {
        if (date.compareTo(LocalDate.now()) < 0)
        {
            return false;
        }
        else {
            return true;
        }

    }
    public static boolean CheckTime(LocalDate date,LocalTime time) {
        if (time.compareTo(LocalTime.now()) < 0 && LocalDate.now().equals(date)) {
            return false;
        } else {
            return true;
        }

    }
    public int GenID()
    {
        int x = 0;
        for (Visitor v : FileManagment.usrData)
        {
            x = v.getId();
        }
        x++;
        return x;
    }
}
