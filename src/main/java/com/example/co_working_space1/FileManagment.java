package com.example.co_working_space1;
import java.io.*;
import java.util.ArrayList;

public class FileManagment {
    public static ArrayList<Visitor> usrData = new ArrayList<Visitor>(); //array of users data
    public static ArrayList<room> roooms = new ArrayList<room>(); // array of rooms data
    public static ArrayList<Reservation> Reservations = new ArrayList<Reservation>(); // array of reservations data

    /**
     * ReadUsers - read the data from file and save it to arraylists
     */
    public static ArrayList<Visitor> ReadUsers()
    {
        try
        {
            FileInputStream users = new FileInputStream("Users.txt");

            ObjectInputStream objusers = new ObjectInputStream(users);
            usrData = (ArrayList<Visitor>) objusers.readObject();

            FileInputStream reservations = new FileInputStream("Reservations.txt");
            ObjectInputStream objReservations = new ObjectInputStream(reservations);
            Reservations  = (ArrayList<Reservation>) objReservations.readObject();

            reservations.close();
            objReservations.close();

            FileInputStream roomFile = new FileInputStream("room.txt");
            ObjectInputStream roomObj = new ObjectInputStream(roomFile);
            roooms  = (ArrayList<room>) roomObj.readObject();

            roomFile.close();
            roomObj.close();
            return usrData;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * WriteUsers provide that after finishing the program write from arraylists to files
     */
    public static  void WriteUsers() {
        try {
            /*
                this open file stream to send the data through
                data pass as binaries to save it as objects so we open object output stream to convert it from binary and to
                these classes that save implements the interface serializable;
             */
            FileOutputStream fileUsr = new FileOutputStream("Users.txt");
            ObjectOutputStream objUsr = new ObjectOutputStream(fileUsr);
            objUsr.writeObject(usrData);
            objUsr.close();
            fileUsr.close();

            FileOutputStream fileRes = new FileOutputStream("Reservations.txt");
            ObjectOutputStream objRes = new ObjectOutputStream(fileRes);
            ArrayList<Reservation> Reserve = Reservations;
            objRes.writeObject(Reserve);
            objRes.close();
            fileRes.close();

            FileOutputStream roomFile = new FileOutputStream("room.txt");
            ObjectOutputStream roomT = new ObjectOutputStream(roomFile);
            ArrayList<room> t1 = roooms;
            roomT.writeObject(t1);
            roomT.close();
            roomFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * arrayDecelaration method initialize our 8 rooms data for the first time to save it 3la madar shahr
     */
    public static void arrayDeclaration()
    {
        ArrayList<Slott> s1 = Slott.CreateListOfSlots(20, 20F);
        ArrayList<Slott> s2 = Slott.CreateListOfSlots(20, 20F);
        ArrayList<Slott> s3 = Slott.CreateListOfSlots(1, 80F);
        ArrayList<Slott> s4 = Slott.CreateListOfSlots(1, 80);

        ArrayList<Slott> s5 = Slott.CreateListOfSlots(1, 80);
        ArrayList<Slott> s6 = Slott.CreateListOfSlots(1, 100);
        ArrayList<Slott> s7 = Slott.CreateListOfSlots(1, 100);
        ArrayList<Slott> s8 = Slott.CreateListOfSlots(1, 100);
        room r1 = new room("General Room", 1, 20, new ArrayList<Slott>(s1));
        room r2 = new room("General Room", 2, 20, new ArrayList<Slott>(s2));
        room r3 = new room("Meeting Room", 3, 10, new ArrayList<Slott>(s3));
        room r4 = new room("Meeting Room", 4, 10, new ArrayList<Slott>(s4));
        room r5 = new room("Meeting Room", 5, 10, new ArrayList<Slott>(s5));
        room r6 = new room("Teaching Room", 6, 10, new ArrayList<Slott>(s6));
        room r7 = new room("Teaching Room", 7, 10, new ArrayList<Slott>(s7));
        room r8 = new room("Teaching Room", 8, 10, new ArrayList<Slott>(s8));

        roooms.add(r1);
        roooms.add(r2);
        roooms.add(r3);
        roooms.add(r4);
        roooms.add(r5);
        roooms.add(r6);
        roooms.add(r7);
        roooms.add(r8);
    }
}
