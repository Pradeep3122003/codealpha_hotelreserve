import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        // Adding rooms
        hotel.addRoom(new Room(101, "Single", 50.0));
        hotel.addRoom(new Room(102, "Double", 80.0));
        hotel.addRoom(new Room(103, "Suite", 120.0));

        // Registration
        System.out.println("Welcome to the Hotel Reservation System!");
        System.out.print("Enter your name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDateString = scanner.nextLine();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDateString = scanner.nextLine();

        Date checkInDate = parseDate(checkInDateString);
        Date checkOutDate = parseDate(checkOutDateString);

        // Searching available rooms
        List<Room> availableRooms = hotel.searchAvailableRooms(checkInDate, checkOutDate);
        System.out.println("Available rooms:");
        for (Room room : availableRooms) {
            System.out.println("Room ID: " + room.getRoomId() + ", Type: " + room.getRoomType() + ", Price: " + room.getPrice());
        }

        // Making reservation
        if (!availableRooms.isEmpty()) {
            Room selectedRoom = availableRooms.get(0); // Selecting the first available room for simplicity
            hotel.makeReservation(guestName, selectedRoom, checkInDate, checkOutDate);
            System.out.println("Reservation made successfully.");
        }

        // Viewing reservations
        List<Reservation> reservations = hotel.getReservations();
        System.out.println("All reservations:");
        for (Reservation reservation : reservations) {
            System.out.println("Reservation ID: " + reservation.getReservationId() + ", Guest: " + reservation.getGuestName() +
                    ", Room Type: " + reservation.getRoom().getRoomType() + ", Check-in: " + reservation.getCheckInDate() +
                    ", Check-out: " + reservation.getCheckOutDate());
        }

        scanner.close();
    }

    private static Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.err.println("Invalid date format. Please use yyyy-mm-dd.");
            return null;
        }
    }
}
