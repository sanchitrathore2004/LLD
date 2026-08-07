package ParkingLot;

import java.util.List;
import java.lang.Integer;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

interface VehicleStrategy {
    public void park(Map<ParkingLocation, String> booking);
    public void unpark();
}

class BikeStrategy implements VehicleStrategy {
    @Override
    public void park (Map<ParkingLocation, String> booking) {
        System.out.println("Bike Parked!!");
        for (Map.Entry<ParkingLocation, String> entry : booking.entrySet()) {
            System.out.println("Floor: "+entry.getKey().getFloor()+" Slot: "+entry.getKey().getSlot() + " Token: " + entry.getValue());
        }
    }

    @Override
    public void unpark () {
        System.out.println("Bike Removed!!");
    }
}

class CarStrategy implements VehicleStrategy {
    @Override
    public void park (Map<ParkingLocation, String> booking) {
        System.out.println("Car Parked!!");
        for (Map.Entry<ParkingLocation, String> entry : booking.entrySet()) {
            System.out.println("Floor: "+entry.getKey().getFloor()+" Slot: "+entry.getKey().getSlot() + " Token: " + entry.getValue());
        }
    }

    @Override
    public void unpark () {
    System.out.println("Car Removed!!");
    }
}

class TruckStrategy implements VehicleStrategy {
    @Override
    public void park (Map<ParkingLocation, String> booking) {
        System.out.println("Truck Parked!!");
        for (Map.Entry<ParkingLocation, String> entry : booking.entrySet()) {
            System.out.println("Floor: "+entry.getKey().getFloor()+" Slot: "+entry.getKey().getSlot() + " Token: " + entry.getValue());
        }
    }

    @Override
    public void unpark () {
        System.out.println("Truck Removed!!");
    }
}

class VehicleStrategyProcessor {
    private VehicleStrategy vehicleStrategy=null;

    public VehicleStrategyProcessor (VehicleStrategy vehicleStrategy) {
        this.vehicleStrategy=vehicleStrategy;
    }

    public void processParking (Map<ParkingLocation, String> booking) {
        vehicleStrategy.park(booking);
    }

    public void processUnParking () {
        vehicleStrategy.unpark();
    }
}

class ParkingSlot {
    private Integer floor;
    private Integer slot;
    public Integer getFloor () {return floor;}
    public Integer getSlot () {return slot;}
}
enum VehicleType {
    Bike,
    Car,
    Truck,
}

class ParkingLocation {
    private Integer floor;
    private Integer slot;
    private VehicleType vehicleType;
    private Boolean available; 

    public ParkingLocation (Integer floor, Integer slot, VehicleType vehicleType) {
        this.floor=floor;
        this.slot=slot;
        this.vehicleType=vehicleType;
        this.available=true;
    }

    public Integer getFloor () {return floor;}
    public Integer getSlot () {return slot;}
    public VehicleType getVehicleType () {return vehicleType;}
    public Boolean getAvailable () {return available;}
    public void setAvailable (Boolean available) {this.available=available;}
}

class ParkingManager {
    private List<ParkingLocation> locations;
    private static ParkingManager parkingManager=null;

    private ParkingManager () {
        locations = new ArrayList<>();
        for(int i=0;i<10;i++){
            ParkingLocation parkingLocation = new ParkingLocation(i, i*67, VehicleType.Bike); //random
            locations.add(parkingLocation);
        }
    }

    public static ParkingManager createParkingManager () {
        if(parkingManager!=null) return parkingManager;
        parkingManager=new ParkingManager();
        return parkingManager;
    }

    public Map<ParkingLocation, String> allotParkingSlot (VehicleType vehicleType) {
        ParkingLocation parkingLocation = isSlotAvailable(vehicleType);
        if(parkingLocation==null){
            System.out.println("Slot Allocation Failed");
            return null;
        }
        String token = generateToken (parkingLocation);
        return Map.of(parkingLocation, token);
    }

    private String generateToken (ParkingLocation parkingLocation) {
        String token = new String();
        return token + String.valueOf(parkingLocation.getFloor()) + "-" + String.valueOf(parkingLocation.getSlot());
    }

    private ParkingLocation isSlotAvailable (VehicleType vehicleType) {
        if (locations == null) return null;
        for (ParkingLocation location : locations) {
            if (location.getVehicleType() == vehicleType && location.getAvailable()) {
                location.setAvailable(false);
                return location;
            }
        }
        return null;
    }

    public void unpark(String token) {
        String floor=token.split("-")[0];
        String slot=token.split("-")[1];
        for (ParkingLocation location : locations) {
            if (location.getFloor()==Integer.parseInt(floor) && location.getSlot()==Integer.parseInt(slot)) {
                location.setAvailable(false);
                break;
            }
        }
        System.out.println("Removed " + floor + " -> " + slot);
    }
}

public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        ParkingManager parkingManager = ParkingManager.createParkingManager();
        while(true) {
            VehicleStrategyProcessor vehicleStrategyProcessor=null;
            Map<ParkingLocation, String> booking=new HashMap<>();
            System.out.println("Sanchit's Parking Lot");
            System.out.println("1. Bike");
            System.out.println("2. Car");
            System.out.println("3. Truck");
            System.out.println("4. Exit");
            System.out.print("Enter Number: ");
            int type = sc.nextInt();

            switch (type) {
                case 1:
                    vehicleStrategyProcessor=new VehicleStrategyProcessor(new BikeStrategy());
                    booking=parkingManager.allotParkingSlot(VehicleType.Bike);
                    if(booking==null) continue;
                    break;
                case 2:
                    vehicleStrategyProcessor=new VehicleStrategyProcessor(new CarStrategy());
                    booking=parkingManager.allotParkingSlot(VehicleType.Car);
                    if(booking==null) continue;
                    break;
                case 3:
                    vehicleStrategyProcessor=new VehicleStrategyProcessor(new TruckStrategy());
                    booking=parkingManager.allotParkingSlot(VehicleType.Truck);
                    if(booking==null) continue;
                    break;
                case 4:
                    sc.close();
                    return ;
                default:
                    break;
            }

            System.out.println("1. Park");
            System.out.println("2. Unpark");
            System.out.print("Enter number: ");
            int action = sc.nextInt();

            switch (action) {
                case 1:
                    vehicleStrategyProcessor.processParking(booking);
                    break;
                case 2:
                    System.out.print("Enter Token: ");
                    sc.nextLine();
                    String token = sc.nextLine();
                    parkingManager.unpark(token);
                    vehicleStrategyProcessor.processUnParking();
                    break;
                default:
                    break;
            }
        }
    }
}
