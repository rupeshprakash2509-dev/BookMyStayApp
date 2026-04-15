// UC12: Data Persistence & System Recovery
// BookMyStay App

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class BookingRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bookingId;
    private String guestName;
    private String roomType;
    private int nights;
    private double totalCost;
    private String status;

    public BookingRecord(String id, String guest, String type, int nights, double cost) {
        this.bookingId=id; this.guestName=guest; this.roomType=type;
        this.nights=nights; this.totalCost=cost; this.status="CONFIRMED";
    }

    public void display() {
        System.out.println("ID: " + bookingId + " | Guest: " + guestName
                + " | " + roomType + " x" + nights + " | Rs." + totalCost + " | " + status);
    }
}

class DataPersistenceManager {
    private static final String FILE = "bookings.dat";

    public static void save(List<BookingRecord> records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(records);
            System.out.println("Data saved to " + FILE);
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<BookingRecord> load() {
        File f = new File(FILE);
        if (!f.exists()) {
            System.out.println("No existing data found. Starting fresh.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            List<BookingRecord> records = (List<BookingRecord>) ois.readObject();
            System.out.println("Recovered " + records.size() + " booking(s) from " + FILE);
            return records;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Recovery failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create and save bookings
        List<BookingRecord> bookings = new ArrayList<>();
        bookings.add(new BookingRecord("BK001", "Alice", "Suite", 3, 15000.0));
        bookings.add(new BookingRecord("BK002", "Bob", "Single", 2, 3000.0));
        bookings.add(new BookingRecord("BK003", "Charlie", "Double", 1, 2500.0));

        System.out.println("=== Saving bookings ===");
        for (BookingRecord r : bookings) r.display();
        DataPersistenceManager.save(bookings);

        System.out.println("\n=== System Recovery ===");
        List<BookingRecord> recovered = DataPersistenceManager.load();
        System.out.println("Recovered bookings:");
        for (BookingRecord r : recovered) r.display();
    }
}
