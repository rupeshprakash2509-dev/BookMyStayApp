// UC8: Booking History & Reporting
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

class BookingRecord {
    private String bookingId;
    private String guestName;
    private String roomType;
    private int nights;
    private double totalCost;
    private String status;
    private String date;

    public BookingRecord(String id, String guest, String roomType, int nights, double cost, String date) {
        this.bookingId = id;
        this.guestName = guest;
        this.roomType = roomType;
        this.nights = nights;
        this.totalCost = cost;
        this.status = "CONFIRMED";
        this.date = date;
    }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public double getTotalCost() { return totalCost; }

    public void display() {
        System.out.printf("%-8s %-12s %-8s %5d %10.2f %-12s %s%n",
                bookingId, guestName, roomType, nights, totalCost, status, date);
    }
}

class BookingHistory {
    private List<BookingRecord> records = new ArrayList<>();

    public void addRecord(BookingRecord r) { records.add(r); }

    public void displayAll() {
        System.out.println("\n=== Booking History ===");
        System.out.printf("%-8s %-12s %-8s %5s %10s %-12s %s%n",
                "ID", "Guest", "Room", "Nights", "Cost", "Status", "Date");
        System.out.println("-".repeat(70));
        for (BookingRecord r : records) r.display();
    }

    public void generateReport() {
        System.out.println("\n=== Report ===");
        long confirmed = records.stream().filter(r -> r.getStatus().equals("CONFIRMED")).count();
        long cancelled = records.stream().filter(r -> r.getStatus().equals("CANCELLED")).count();
        double revenue = records.stream().filter(r -> r.getStatus().equals("CONFIRMED"))
                .mapToDouble(BookingRecord::getTotalCost).sum();
        System.out.println("Total Bookings: " + records.size());
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Cancelled: " + cancelled);
        System.out.println("Total Revenue: Rs." + revenue);
    }
}

public class Main {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();
        history.addRecord(new BookingRecord("BK001","Alice","Suite",2,10000.0,"2024-03-01"));
        history.addRecord(new BookingRecord("BK002","Bob","Single",3,4500.0,"2024-03-02"));
        BookingRecord r3 = new BookingRecord("BK003","Charlie","Double",1,2500.0,"2024-03-03");
        r3.setStatus("CANCELLED");
        history.addRecord(r3);
        history.addRecord(new BookingRecord("BK004","Diana","Suite",4,20000.0,"2024-03-04"));

        history.displayAll();
        history.generateReport();
    }
}
