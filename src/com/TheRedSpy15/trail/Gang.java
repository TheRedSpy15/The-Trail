package com.TheRedSpy15.trail;

/*

   Copyright [2017] [TheRedSpy15]

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
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

/**
 *
 * Includes the values of everything relating to the player(s) gang i.e : resources
 *
 */
public class Gang implements Serializable {

    private static byte HealthConditions = 100;
    private static int Distance = 0;
    private static String defaultGunSpriteURL = "com/TheRedSpy15/trail/glock.png";
    private static String gunSpriteURL = getDefaultGunSpriteURL();
    private static long Score = 0;
    private static String defaultCarURL = "com/TheRedSpy15/trail/greencar.png";
    private static String carSpriteURL = getDefaultCarURL();
    private static int Days = 0;
    private static short wage = 0;
    private static short Food = 0;
    private static byte Grenades = 0;
    private static String defaultGunID = "Glock";
    private static String gunID = getDefaultGunID();
    private static byte defaultAttackDMG = 15;
    private static byte baseAttackDamage = getDefaultAttackDMG();
    private static byte bodyArmor = 0;
    private static short Ammo = 0;
    private static int Water = 0;
    private static double Money = 0;
    private static byte Pace = 10;
    private static boolean Moving = false;
    private static int capturedThieves = 0;
    private static byte FoodIntake = 2;
    private static Stack <String> gangMembers = new Stack<>();
    private static vehicleIDs vehicleID = vehicleIDs.GREENCAR;
    public enum vehicleIDs{GREENCAR, RALLYCAR, BLUETRUCK}

    protected void gangSetupMethod(){

        // Posse scene
        try {
            // assigns posse scene fxml file to posse pane object
            Main.setPossePane(FXMLLoader.load(getClass().getResource("GangScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    static Stack<String> getGangMembers() {
        return gangMembers;
    }
    public static vehicleIDs getVehicleID() {
        return vehicleID;
    }
    public static void setVehicleID(vehicleIDs VehicleID) {
        Gang.vehicleID = VehicleID;
    }
    static String getDefaultGunID() {
        return defaultGunID;
    }
    static String getDefaultGunSpriteURL() {
        return defaultGunSpriteURL;
    }
    public static void setDefaultGunSpriteURL(String DefaultGunSpriteURL) {
        defaultGunSpriteURL = DefaultGunSpriteURL;
    }
    public static void setDefaultGunID(String DefaultGunID) {
        defaultGunID = DefaultGunID;
    }
    static byte getDefaultAttackDMG() {
        return defaultAttackDMG;
    }
    public static void setDefaultAttackDMG(byte DefaultAttackDMG) {
        defaultAttackDMG = DefaultAttackDMG;
    }
    static int getCapturedThieves() {
        return capturedThieves;
    }
    static String getDefaultCarURL() {
        return defaultCarURL;
    }
    static void setDefaultCarURL(String DefaultCarURL) {
        defaultCarURL = DefaultCarURL;
    }
    public static byte getBodyArmor() {
        return bodyArmor;
    }
    static void setBodyArmor(byte bodyArmor) {
        Gang.bodyArmor = bodyArmor;
    }
    static void setCapturedThieves(int CapturedThieves) {
        capturedThieves = CapturedThieves;
    }
    static int getDistance() {
        return Distance;
    }
    static void setDistance(int distance) {
        Distance = distance;
    }
    static long getScore() {
        return Score;
    }
    static void setScore(long score) {
        Score = score;
    }
    static int getDays() {
        return Days;
    }
    static void setDays(int days) {
        Days = days;
    }
    static int getWage() {
        return wage;
    }
    static void setWage(int Wage) {
        wage = (short) Wage;
    }
    public static int getFood() {
        return Food;
    }
    public static void setFood(int food) {
        Food = (short) food;
    }
    static int getGrenades() {
        return Grenades;
    }
    static void setGrenades(int grenades) {
        Grenades = (byte) grenades;
    }
    public static int getAmmo() {
        return Ammo;
    }
    public static void setAmmo(int ammo) {
        Ammo = (short) ammo;
    }
    static int getWater() {
        return Water;
    }
    static void setWater(int water) {
        Water = water;
    }
    public static double getMoney() {
        return Money;
    }
    public static void setMoney(double money) {
        Money = money;
    }
    static int getPace() {
        return Pace;
    }
    static void setPace(int pace) {
        Pace = (byte) pace;
    }
    static int getFoodIntake() {
        return FoodIntake;
    }
    static void setFoodIntake(int foodIntake) {
        FoodIntake = (byte) foodIntake;
    }
    static boolean isMoving() {
        return Moving;
    }
    static void setMoving(boolean moving) {
        Moving = moving;
    }
    static int getHealthConditions() {
        return HealthConditions;
    }
    static void setHealthConditions(int healthConditions) {
        HealthConditions = (byte) healthConditions;
    }
    static String getGunSpriteURL() {
        return gunSpriteURL;
    }
    static void setGunSpriteURL(String GunSpriteURL) {
        gunSpriteURL = GunSpriteURL;
    }
    static String getCarSpriteURL() {
        return carSpriteURL;
    }
    static void setCarSpriteURL(String CarSpriteURL) {
        carSpriteURL = CarSpriteURL;
    }
    static String getGunID() {
        return gunID;
    }
    static void setGunID(String GunID) {
        gunID = GunID;
    }
    static int getBaseAttackDamage() {
        return baseAttackDamage;
    }
    static void setBaseAttackDamage(int BaseAttackDamage) {
        baseAttackDamage = (byte) BaseAttackDamage;
    }
}
