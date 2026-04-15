// UC6: Reservation Confirmation & Room Allocation
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;
    protected double price;
    protected boolean available;

    public Room(String n, double p) { roomNumber=n; price=p; available=true; }
    public abstract String getRoomType();
    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean v) { available=v; }
    public double getPrice() { return price; }
}

class SingleRoom extends Room {
    public SingleRoom(String n) { super(n,1500.0); }
    public String getRoomType() { return "Single"; }
}
class DoubleRoom extends Room {
    public DoubleRoom(String n) { super(n,2500.0); }
    public String getRoomType() { return "Double"; }
}
class SuiteRoom extends Room {
    public SuiteRoom(String n) { super(n,5000.0); }
    public String getRoomType() { return "Suite"; }
}

class Reservation {
    private String reservationId;
    private String guestName;
    private Room allocatedRoom;
    private int nights;
    private double totalCost;

    public Reservation(String id, String guest, Room room, int nights) {
        this.reservationId = id;
        this.guestName = guest;
        this.allocatedRoom = room;
        this.nights = nights;
        this.totalCost = room.getPrice() * nights;
        room.setAvailable(false);
    }

    public void display() {
        System.out.println("=== Reservation Confirmed ===");
        System.out.println("ID: " + reservationId);
        System.out.println("Guest: " + guestName);
        System.out.println("Room: " + allocatedRoom.getRoomNumber() + " (" + allocatedRoom.getRoomType() + ")");
        System.out.println("Nights: " + nights);
        System.out.println("Total Cost: Rs." + totalCost);
    }
}

class ReservationSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private int resCounter = 1;

    public void addRoom(Room r) { rooms.add(r); }

    public Reservation confirmBooking(String guest, String type, int nights) {
        for (Room r : rooms) {
            if (r.getRoomType().equalsIgnoreCase(type) && r.isAvailable()) {
                String id = "RES" + String.format("%03d", resCounter++);
                Reservation res = new Reservation(id, guest, r, nights);
                reservations.add(res);
                return res;
            }
        }
        System.out.println("No available " + type + " room for " + guest);
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ReservationSystem sys = new ReservationSystem();
        sys.addRoom(new SingleRoom("101"));
        sys.addRoom(new DoubleRoom("201"));
        sys.addRoom(new SuiteRoom("301"));

        Reservation r1 = sys.confirmBooking("Alice", "Single", 2);
        if (r1 != null) r1.display();

        Reservation r2 = sys.confirmBooking("Bob", "Suite", 3);
        if (r2 != null) r2.display();

        // Try to book same type again
        sys.confirmBooking("Charlie", "Single", 1);
    }
}
