package com.TheRedSpy15.trail;

/*

   Copyright 2018 TheRedSpy15

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

import javafx.fxml.FXMLLoader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

/**
 * Includes the values of everything relating
 * to the player(s)' gang i.e : resources
 */
public class Gang implements Serializable {

    private byte HealthConditions = 100;
    private byte defaultAttackDMG = 15;
    private byte brakeFrequency = 10;
    private byte capturedThieves = 0;
    private byte Grenades = 0;
    private byte FoodIntake = 2;
    private byte baseAttackDamage = getDefaultAttackDMG();
    private byte bodyArmor = 0;
    private short Ammo = 0;
    private short Water = 0;
    private short carSpeed = 150;
    private short distanceSinceCity = 0;
    private short wage = 0;
    private short Food = 0;
    private int Distance = 0;
    private int Days = 0;
    private long Score = 0;
    private double Money = 0;
    private String defaultGunSpriteURL = "com/TheRedSpy15/trail/glock.png";
    private String gunSpriteURL = getDefaultGunSpriteURL();
    private String vehicleID = "Starter Car";
    private String defaultCarURL = "com/TheRedSpy15/trail/greencar.png";
    private String carSpriteURL = getDefaultCarURL();
    private String defaultGunID = "Glock";
    private String gunID = getDefaultGunID();
    private Stack <String> gangMembers = new Stack<>();
    private Stack <String> deceased = new Stack<>();
    private volatile boolean Moving = false;

    protected void gangSetupMethod(){

        // Posse scene
        try {
            // assigns posse scene fxml file to posse pane object
            Main.main.setPossePane(FXMLLoader.load(getClass().getResource("GangScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveData() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("SaveGame.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(Main.gang);
        objectOutputStream.writeObject(Main.gang.gangMembers);
        objectOutputStream.writeObject(Main.gang.deceased);

        objectOutputStream.close();
        fileOutputStream.close();
    }

    static void loadData() throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("SaveGame.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Main.gang = (Gang) objectInputStream.readObject();
        Main.gang.gangMembers = (Stack<String>) objectInputStream.readObject();
        Main.gang.deceased = (Stack<String>) objectInputStream.readObject();

        fileInputStream.close();
        objectInputStream.close();
    }

    // Getters and Setters
    Stack<String> getGangMembers() {
        return gangMembers;
    }
    public Stack<String> getDeceased() {
        return deceased;
    }
    public short getCarSpeed() {
        return carSpeed;
    }
    public short getDistanceSinceCity() {
        return distanceSinceCity;
    }
    public void setDistanceSinceCity(short DistanceSinceCity) {
        distanceSinceCity = DistanceSinceCity;
    }
    public void setCarSpeed(short CarSpeed) {
        carSpeed = CarSpeed;
    }
    String getDefaultGunID() {
        return defaultGunID;
    }
    String getDefaultGunSpriteURL() {
        return defaultGunSpriteURL;
    }
    public void setDefaultGunSpriteURL(String DefaultGunSpriteURL) {
        defaultGunSpriteURL = DefaultGunSpriteURL;
    }
    public void setDefaultGunID(String DefaultGunID) {
        defaultGunID = DefaultGunID;
    }
    byte getDefaultAttackDMG() {
        return defaultAttackDMG;
    }
    public void setDefaultAttackDMG(byte DefaultAttackDMG) {
        defaultAttackDMG = DefaultAttackDMG;
    }
    int getCapturedThieves() {
        return capturedThieves;
    }
    String getDefaultCarURL() {
        return defaultCarURL;
    }
    public byte getBodyArmor() {
        return bodyArmor;
    }
    void setBodyArmor(byte BodyArmor) {
        bodyArmor = BodyArmor;
    }
    void setCapturedThieves(int CapturedThieves) {
        capturedThieves = (byte) CapturedThieves;
    }
    int getDistance() {
        return Distance;
    }
    void setDistance(int distance) {
        Distance = distance;
    }
    long getScore() {
        return Score;
    }
    void setScore(long score) {
        Score = score;
    }
    int getDays() {
        return Days;
    }
    void setDays(int days) {
        Days = days;
    }
    int getWage() {
        return wage;
    }
    void setWage(int Wage) {
        wage = (short) Wage;
    }
    int getFood() {
        return Food;
    }
    void setFood(int food) {
        Food = (short) food;
    }
    int getGrenades() {
        return Grenades;
    }
    void setGrenades(int grenades) {
        Grenades = (byte) grenades;
    }
    public int getAmmo() {
        return Ammo;
    }
    public void setAmmo(int ammo) {
        Ammo = (short) ammo;
    }
    int getWater() {
        return Water;
    }
    void setWater(int water) {
        Water = (short) water;
    }
    public double getMoney() {
        return Money;
    }
    public void setMoney(double money) {
        Money = money;
    }
    int getBrakeFrequency() {
        return brakeFrequency;
    }
    void setBrakeFrequency(int BrakeFrequency) {
        brakeFrequency = (byte) BrakeFrequency;
    }
    int getFoodIntake() {
        return FoodIntake;
    }
    void setFoodIntake(int foodIntake) {
        FoodIntake = (byte) foodIntake;
    }
    boolean isMoving() {
        return Moving;
    }
    void setMoving(boolean moving) {
        Moving = moving;
    }
    int getHealthConditions() {
        return HealthConditions;
    }
    void setHealthConditions(int healthConditions) {
        HealthConditions = (byte) healthConditions;
    }
    String getGunSpriteURL() {
        return gunSpriteURL;
    }
    void setGunSpriteURL(String GunSpriteURL) {
        gunSpriteURL = GunSpriteURL;
    }
    String getCarSpriteURL() {
        return carSpriteURL;
    }
    void setCarSpriteURL(String CarSpriteURL) {
        carSpriteURL = CarSpriteURL;
    }
    String getGunID() {
        return gunID;
    }
    void setGunID(String GunID) {
        gunID = GunID;
    }
    int getBaseAttackDamage() {
        return baseAttackDamage;
    }
    void setBaseAttackDamage(int BaseAttackDamage) {
        baseAttackDamage = (byte) BaseAttackDamage;
    }
    String getVehicleID() {
        return vehicleID;
    }
    void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }
}
