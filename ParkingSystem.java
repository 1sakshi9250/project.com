package com.skillio;

import java.util.*;

class Vehicle {
    String plateNumber;
    long entryTime;

    public Vehicle(String plateNumber) {
        this.plateNumber = plateNumber;
        this.entryTime = System.currentTimeMillis(); // time in milliseconds
    }
}

class ParkingLot {
    private int capacity;
    private Map<String, Vehicle> parkedVehicles;
    private final double ratePerHour = 10.0; // Rate in currency/hour

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new HashMap<>();
    }

    public boolean parkVehicle(String plateNumber) {
        if (parkedVehicles.size() >= capacity) {
            System.out.println("Parking Lot is Full!");
            return false;
        }
        if (parkedVehicles.containsKey(plateNumber)) {
            System.out.println("Vehicle already parked.");
            return false;
        }
        Vehicle vehicle = new Vehicle(plateNumber);
        parkedVehicles.put(plateNumber, vehicle);
        System.out.println("Vehicle parked: " + plateNumber);
        return true;
    }

    public boolean removeVehicle(String plateNumber) {
        Vehicle vehicle = parkedVehicles.remove(plateNumber);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return false;
        }
        long exitTime = System.currentTimeMillis();
        long parkedDuration = (exitTime - vehicle.entryTime) / (1000 * 60); // in minutes
        double hours = Math.ceil(parkedDuration / 60.0);
        double fee = hours * ratePerHour;

        System.out.println("Vehicle removed: " + plateNumber);
        System.out.println("Parked Duration: " + parkedDuration + " minutes");
        System.out.println("Parking Fee: $" + fee);
        return true;
    }

    public void listParkedVehicles() {
        if (parkedVehicles.isEmpty()) {
            System.out.println(" No vehicles parked.");
            return;
        }
        System.out.println("Parked Vehicles:");
        for (String plate : parkedVehicles.keySet()) {
            System.out.println("- " + plate);
        }
    }
}

public class ParkingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot lot = new ParkingLot(5); // 5 parking slots

        while (true) {
            System.out.println("\n==== Parking Management System ====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View Parked Vehicles");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Vehicle Plate Number: ");
                    String plate = scanner.nextLine();
                    lot.parkVehicle(plate);
                    break;
                case 2:
                    System.out.print("Enter Vehicle Plate Number to Remove: ");
                    String removePlate = scanner.nextLine();
                    lot.removeVehicle(removePlate);
                    break;
                case 3:
                    lot.listParkedVehicles();
                    break;
                case 4:
                    System.out.println(" Exiting system...");
                    return;
                default:
                    System.out.println(" Invalid choice!");
            }
        }
    }
}
