// UC2: Basic Room Types & Static Availability
// BookMyStay App

abstract class Room {
    protected String roomNumber;
    protected double price;
    protected int beds;

    public Room(String roomNumber, double price, int beds) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.beds = beds;
    }

    public abstract String getRoomType();

    public void displayInfo() {
        System.out.println("Room No: " + roomNumber);
        System.out.println("Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: Rs." + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom(String roomNumber) {
        super(roomNumber, 1500.0, 1);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(String roomNumber) {
        super(roomNumber, 2500.0, 2);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(String roomNumber) {
        super(roomNumber, 5000.0, 3);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

public class Main {
    public static void main(String[] args) {
        Room r1 = new SingleRoom("101");
        Room r2 = new DoubleRoom("201");
        Room r3 = new SuiteRoom("301");

        // Static availability variables
        boolean r1Available = true;
        boolean r2Available = false;
        boolean r3Available = true;

        System.out.println("=== BookMyStay - Room Availability ===");
        System.out.println();

        r1.displayInfo();
        System.out.println("Available: " + r1Available);
        System.out.println();

        r2.displayInfo();
        System.out.println("Available: " + r2Available);
        System.out.println();

        r3.displayInfo();
        System.out.println("Available: " + r3Available);
        System.out.println();

        System.out.println("Application terminated.");
    }
}
