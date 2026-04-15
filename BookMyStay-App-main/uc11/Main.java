// UC11: Concurrent Booking Simulation
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
    public synchronized boolean isAvailable() { return available; }
    public synchronized void setAvailable(boolean v) { available=v; }
}

class SharedInventory {
    private List<Room> rooms = new ArrayList<>();

    public SharedInventory() {
        rooms.add(new Room("101", "Single"));
        rooms.add(new Room("102", "Single"));
        rooms.add(new Room("201", "Double"));
    }

    public synchronized Room findAndBook(String type, String guest) {
        for (Room r : rooms) {
            if (r.getRoomType().equalsIgnoreCase(type) && r.isAvailable()) {
                r.setAvailable(false);
                System.out.println(Thread.currentThread().getName() + ": " + guest + " booked room " + r.getRoomNumber());
                return r;
            }
        }
        System.out.println(Thread.currentThread().getName() + ": " + guest + " - No room available!");
        return null;
    }

    public void displayStatus() {
        System.out.println("\n=== Room Status ===");
        for (Room r : rooms)
            System.out.println(r.getRoomNumber() + " - " + (r.isAvailable() ? "Available" : "Occupied"));
    }
}

class BookingThread extends Thread {
    private SharedInventory inventory;
    private String guestName;
    private String roomType;

    public BookingThread(SharedInventory inv, String guest, String type) {
        inventory=inv; guestName=guest; roomType=type;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((long)(Math.random()*100));
        } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        inventory.findAndBook(roomType, guestName);
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedInventory inventory = new SharedInventory();

        Thread t1 = new BookingThread(inventory, "Alice", "Single");
        Thread t2 = new BookingThread(inventory, "Bob", "Single");
        Thread t3 = new BookingThread(inventory, "Charlie", "Single");
        Thread t4 = new BookingThread(inventory, "Diana", "Double");

        t1.start(); t2.start(); t3.start(); t4.start();
        t1.join(); t2.join(); t3.join(); t4.join();

        inventory.displayStatus();
    }
}
