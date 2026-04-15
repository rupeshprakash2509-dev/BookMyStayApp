// UC3: Centralized Room Inventory Management
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;
    protected double price;
    protected int beds;
    protected boolean available;

    public Room(String roomNumber, double price, int beds) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.beds = beds;
        this.available = true;
    }

    public abstract String getRoomType();

    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public void displayInfo() {
        System.out.println("Room No: " + roomNumber + " | Type: " + getRoomType()
                + " | Beds: " + beds + " | Price: Rs." + price
                + " | Available: " + available);
    }
}

class SingleRoom extends Room {
    public SingleRoom(String roomNumber) { super(roomNumber, 1500.0, 1); }
    public String getRoomType() { return "Single"; }
}

class DoubleRoom extends Room {
    public DoubleRoom(String roomNumber) { super(roomNumber, 2500.0, 2); }
    public String getRoomType() { return "Double"; }
}

class SuiteRoom extends Room {
    public SuiteRoom(String roomNumber) { super(roomNumber, 5000.0, 3); }
    public String getRoomType() { return "Suite"; }
}

class RoomInventory {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Added: " + room.getRoomType() + " Room " + room.getRoomNumber());
    }

    public void displayAll() {
        System.out.println("\n=== Room Inventory ===");
        for (Room r : rooms) {
            r.displayInfo();
        }
    }

    public int totalRooms() { return rooms.size(); }

    public long availableCount() {
        return rooms.stream().filter(Room::isAvailable).count();
    }
}

public class Main {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom(new SingleRoom("101"));
        inventory.addRoom(new SingleRoom("102"));
        inventory.addRoom(new DoubleRoom("201"));
        inventory.addRoom(new DoubleRoom("202"));
        inventory.addRoom(new SuiteRoom("301"));

        inventory.displayAll();
        System.out.println("\nTotal Rooms: " + inventory.totalRooms());
        System.out.println("Available Rooms: " + inventory.availableCount());
    }
}
