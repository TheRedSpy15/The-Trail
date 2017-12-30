package com.TheRedSpy15.trail;

public class GreenCar implements Vehicle {
    @Override
    public void drive() {
        Gang.setDistance(Gang.getDistance() + 15);
    }
}
