// UC4: Room Search & Availability Check
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
    public double getPrice() { return price; }

    public void displayInfo() {
        System.out.println("Room: " + roomNumber + " | Type: " + getRoomType()
                + " | Price: Rs." + price + " | Available: " + available);
    }
}

class SingleRoom extends Room {
    public SingleRoom(String n) { super(n, 1500.0, 1); }
    public String getRoomType() { return "Single"; }
}

class DoubleRoom extends Room {
    public DoubleRoom(String n) { super(n, 2500.0, 2); }
    public String getRoomType() { return "Double"; }
}

class SuiteRoom extends Room {
    public SuiteRoom(String n) { super(n, 5000.0, 3); }
    public String getRoomType() { return "Suite"; }
}

class RoomSearch {
    private List<Room> inventory = new ArrayList<>();

    public void addRoom(Room r) { inventory.add(r); }

    public List<Room> searchByType(String type) {
        List<Room> result = new ArrayList<>();
        for (Room r : inventory) {
            if (r.getRoomType().equalsIgnoreCase(type) && r.isAvailable()) result.add(r);
        }
        return result;
    }

    public List<Room> searchByMaxPrice(double maxPrice) {
        List<Room> result = new ArrayList<>();
        for (Room r : inventory) {
            if (r.getPrice() <= maxPrice && r.isAvailable()) result.add(r);
        }
        return result;
    }

    public List<Room> getAllAvailable() {
        List<Room> result = new ArrayList<>();
        for (Room r : inventory) { if (r.isAvailable()) result.add(r); }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        RoomSearch search = new RoomSearch();
        search.addRoom(new SingleRoom("101"));
        search.addRoom(new SingleRoom("102"));
        search.addRoom(new DoubleRoom("201"));
        search.addRoom(new SuiteRoom("301"));

        System.out.println("=== Search: Double Rooms ===");
        for (Room r : search.searchByType("Double")) r.displayInfo();

        System.out.println("\n=== Search: Rooms under Rs.3000 ===");
        for (Room r : search.searchByMaxPrice(3000)) r.displayInfo();

        System.out.println("\n=== All Available Rooms ===");
        for (Room r : search.getAllAvailable()) r.displayInfo();
    }
}
