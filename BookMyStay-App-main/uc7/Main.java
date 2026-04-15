// UC7: Add-On Service Selection
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

interface AddOnService {
    String getServiceName();
    double getServiceCost();
}

class BreakfastService implements AddOnService {
    public String getServiceName() { return "Breakfast"; }
    public double getServiceCost() { return 300.0; }
}

class LaundryService implements AddOnService {
    public String getServiceName() { return "Laundry"; }
    public double getServiceCost() { return 150.0; }
}

class SpaService implements AddOnService {
    public String getServiceName() { return "Spa"; }
    public double getServiceCost() { return 800.0; }
}

class Booking {
    private String bookingId;
    private String guestName;
    private String roomType;
    private double roomCost;
    private List<AddOnService> addOns = new ArrayList<>();

    public Booking(String id, String guest, String roomType, double roomCost) {
        this.bookingId = id;
        this.guestName = guest;
        this.roomType = roomType;
        this.roomCost = roomCost;
    }

    public void addService(AddOnService service) {
        addOns.add(service);
        System.out.println("Added service: " + service.getServiceName());
    }

    public double getTotalCost() {
        double total = roomCost;
        for (AddOnService s : addOns) total += s.getServiceCost();
        return total;
    }

    public void displaySummary() {
        System.out.println("\n=== Booking Summary ===");
        System.out.println("ID: " + bookingId + " | Guest: " + guestName);
        System.out.println("Room: " + roomType + " | Room Cost: Rs." + roomCost);
        System.out.println("Add-On Services:");
        for (AddOnService s : addOns) {
            System.out.println("  - " + s.getServiceName() + ": Rs." + s.getServiceCost());
        }
        System.out.println("Total Cost: Rs." + getTotalCost());
    }
}

public class Main {
    public static void main(String[] args) {
        Booking b = new Booking("BK001", "Alice", "Suite", 5000.0);
        b.addService(new BreakfastService());
        b.addService(new LaundryService());
        b.addService(new SpaService());
        b.displaySummary();

        Booking b2 = new Booking("BK002", "Bob", "Single", 1500.0);
        b2.addService(new BreakfastService());
        b2.displaySummary();
    }
}
