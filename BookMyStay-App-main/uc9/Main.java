// UC9: Error Handling & Validation
// BookMyStay App

import java.util.ArrayList;
import java.util.List;

class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}

class RoomNotAvailableException extends BookingException {
    public RoomNotAvailableException(String roomType) {
        super("No available room of type: " + roomType);
    }
}

class InvalidInputException extends BookingException {
    public InvalidInputException(String field, String reason) {
        super("Invalid input for '" + field + "': " + reason);
    }
}

class Room {
    private String roomNumber;
    private String roomType;
    private double price;
    private boolean available;

    public Room(String num, String type, double price) {
        this.roomNumber = num; this.roomType = type;
        this.price = price; this.available = true;
    }
    public String getRoomType() { return roomType; }
    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean v) { available = v; }
}

class BookingValidator {
    public static void validateGuestName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty())
            throw new InvalidInputException("guestName", "cannot be empty");
        if (name.length() < 2)
            throw new InvalidInputException("guestName", "too short");
    }

    public static void validateNights(int nights) throws InvalidInputException {
        if (nights <= 0)
            throw new InvalidInputException("nights", "must be > 0");
        if (nights > 30)
            throw new InvalidInputException("nights", "cannot exceed 30");
    }
}

class BookingSystem {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room r) { rooms.add(r); }

    public void book(String guest, String roomType, int nights) throws BookingException {
        BookingValidator.validateGuestName(guest);
        BookingValidator.validateNights(nights);
        for (Room r : rooms) {
            if (r.getRoomType().equalsIgnoreCase(roomType) && r.isAvailable()) {
                r.setAvailable(false);
                System.out.println("Booked room " + r.getRoomNumber() + " for " + guest);
                return;
            }
        }
        throw new RoomNotAvailableException(roomType);
    }
}

public class Main {
    public static void main(String[] args) {
        BookingSystem sys = new BookingSystem();
        sys.addRoom(new Room("101", "Single", 1500.0));
        sys.addRoom(new Room("201", "Double", 2500.0));

        // Valid booking
        try {
            sys.book("Alice", "Single", 2);
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Empty name
        try {
            sys.book("", "Double", 1);
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Invalid nights
        try {
            sys.book("Bob", "Double", -1);
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Room not available
        try {
            sys.book("Charlie", "Suite", 2);
        } catch (BookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
