package com.TheRedSpy15.trail;

public class RallyCar implements Vehicle {
    @Override
    public void drive() {
        Gang.setDistance(Gang.getDistance() + 25);
    }
}
