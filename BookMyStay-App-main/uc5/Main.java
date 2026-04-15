// UC5: Booking Request
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

abstract class Room {
    protected String roomNumber;
    protected double price;
    protected boolean available;

    public Room(String roomNumber, double price) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.available = true;
    }

    public abstract String getRoomType();
    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean v) { this.available = v; }
    public double getPrice() { return price; }
}

class SingleRoom extends Room {
    public SingleRoom(String n) { super(n, 1500.0); }
    public String getRoomType() { return "Single"; }
}

class DoubleRoom extends Room {
    public DoubleRoom(String n) { super(n, 2500.0); }
    public String getRoomType() { return "Double"; }
}

class SuiteRoom extends Room {
    public SuiteRoom(String n) { super(n, 5000.0); }
    public String getRoomType() { return "Suite"; }
}

class BookingRequest {
    private static int counter = 1;
    private String bookingId;
    private String guestName;
    private String roomType;
    private int nights;
    private String status;

    public BookingRequest(String guestName, String roomType, int nights) {
        this.bookingId = "BK" + String.format("%03d", counter++);
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
        this.status = "PENDING";
    }

    public String getBookingId() { return bookingId; }
    public String getRoomType() { return roomType; }
    public void setStatus(String s) { this.status = s; }

    public void display() {
        System.out.println("Booking ID: " + bookingId + " | Guest: " + guestName
                + " | Room Type: " + roomType + " | Nights: " + nights + " | Status: " + status);
    }
}

class BookingSystem {
    private List<Room> inventory = new ArrayList<>();
    private List<BookingRequest> requests = new ArrayList<>();

    public void addRoom(Room r) { inventory.add(r); }

    public void submitRequest(BookingRequest req) {
        requests.add(req);
        System.out.println("Request submitted: " + req.getBookingId());
    }

    public void displayRequests() {
        System.out.println("\n=== Booking Requests ===");
        for (BookingRequest r : requests) r.display();
    }
}

public class Main {
    public static void main(String[] args) {
        BookingSystem system = new BookingSystem();
        system.addRoom(new SingleRoom("101"));
        system.addRoom(new DoubleRoom("201"));
        system.addRoom(new SuiteRoom("301"));

        BookingRequest r1 = new BookingRequest("Alice", "Single", 2);
        BookingRequest r2 = new BookingRequest("Bob", "Double", 3);
        BookingRequest r3 = new BookingRequest("Charlie", "Suite", 1);

        system.submitRequest(r1);
        system.submitRequest(r2);
        system.submitRequest(r3);

        system.displayRequests();
    }
}
