// UC10: Booking Cancellation & Inventory Rollback
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

class Room {
    private String roomNumber;
    private String roomType;
    private boolean available;

    public Room(String num, String type) {
        roomNumber=num; roomType=type; available=true;
    }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean v) { available=v; }
}

class Booking {
    private String bookingId;
    private String guestName;
    private Room room;
    private String status;

    public Booking(String id, String guest, Room room) {
        bookingId=id; guestName=guest; this.room=room;
        this.status="CONFIRMED";
        room.setAvailable(false);
    }
    public String getBookingId() { return bookingId; }
    public String getStatus() { return status; }
    public Room getRoom() { return room; }

    public void cancel() {
        if (!status.equals("CONFIRMED")) {
            System.out.println("Booking " + bookingId + " cannot be cancelled - status: " + status);
            return;
        }
        status = "CANCELLED";
        room.setAvailable(true); // Inventory rollback
        System.out.println("Booking " + bookingId + " for " + guestName + " cancelled.");
        System.out.println("Room " + room.getRoomNumber() + " is now available again.");
    }

    public void display() {
        System.out.println("ID: " + bookingId + " | Guest: " + guestName
                + " | Room: " + room.getRoomNumber() + " | Status: " + status);
    }
}

class HotelSystem {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bCounter = 1;

    public void addRoom(Room r) { rooms.add(r); }

    public Booking book(String guest, String type) {
        for (Room r : rooms) {
            if (r.getRoomType().equalsIgnoreCase(type) && r.isAvailable()) {
                Booking b = new Booking("BK" + String.format("%03d", bCounter++), guest, r);
                bookings.add(b);
                System.out.println("Booked: " + b.getBookingId());
                return b;
            }
        }
        System.out.println("No room available for type: " + type);
        return null;
    }

    public void displayInventory() {
        System.out.println("\n=== Room Inventory ===");
        for (Room r : rooms)
            System.out.println(r.getRoomNumber() + " (" + r.getRoomType() + ") - " + (r.isAvailable() ? "Available" : "Occupied"));
    }
}

public class Main {
    public static void main(String[] args) {
        HotelSystem hotel = new HotelSystem();
        hotel.addRoom(new Room("101", "Single"));
        hotel.addRoom(new Room("201", "Double"));

        hotel.displayInventory();

        Booking b1 = hotel.book("Alice", "Single");
        Booking b2 = hotel.book("Bob", "Double");

        hotel.displayInventory();

        System.out.println("\n-- Cancelling booking --");
        if (b1 != null) b1.cancel();

        hotel.displayInventory();
    }
}
