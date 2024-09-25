package com.mycompany.oodj_assignment;

public class Hall {
    private String hallType;
    private String hallName;
    private String equipment;
    private String availabilityStart;
    private String availabilityEnd;
    private double totalPrice;

    public Hall(String hallType, String hallName, String equipment, String availabilityStart, String availabilityEnd, double totalPrice) {
        this.hallType = hallType;
        this.hallName = hallName;
        this.equipment = equipment;
        this.availabilityStart = availabilityStart;
        this.availabilityEnd = availabilityEnd;
        this.totalPrice = totalPrice;
    }


    public String getHallType() {
        return hallType;
    }

    public String getHallName() {
        return hallName;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getAvailabilityStart() {
        return availabilityStart;
    }

    public String getAvailabilityEnd() {
        return availabilityEnd;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
}
