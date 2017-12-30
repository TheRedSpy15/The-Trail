package com.TheRedSpy15.trail;

public class BlueTruck implements Vehicle {
    @Override
    public void drive() {
        Gang.setDistance(Gang.getDistance() + 10);
    }
}
