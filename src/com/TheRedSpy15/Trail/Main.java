package com.TheRedSpy15.Trail;

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

/*

    BUG LIST

    * Transition on travel scene rarely stops
    * Jar file not working on other PCs
    * Images breaking scenes and throwing exceptions

        * Has to do with invalid URLs / not being able to find/load them

        * started when maven was removed


    WORKING ON

    * Car interface
    * Settings menu in mid game menu
    * Getters / Setters need some more testing
    * stop background music in shootout and play shootout music
    * View gang member scene in mid game menu
    * Removing "GOD" class
    * Different cars have different specs
    * Pace refactored to breaking freq. (as cars will have fixed speeds)
    * Reduce starting gang size to 5 (seats in starting car)


    Planned

    * Body armour
    * System time in mid game menu
    * More cities
    * Personal armoury (gun inventory for selecting different guns with different traits)
    * More guns
    * chance to miss shooting at thief

    */

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    // Javafx - WAY too many variables here
    static int distanceSinceCity = 0;
    private static Stage MainWindow;
    private static Scene FoodPortionsScene;
    static Stage MenuWindow;
    static Scene SickEventScene;
    private static Stage AlertWindow = new Stage();
    private static Scene PaceScene;
    private static Parent careerAnchor;
    private static Parent storePane;
    private static Parent possePane;
    static Parent travelPane;
    static Parent cityPane;
    static Parent midSellStorePane;
    static Scene cityScene;
    private static Scene storeScene;
    static Parent dealerPane;
    static Scene dealerScene;
    private static Parent gunStorePane;
    private static Scene gunStoreScene;
    static Parent inventoryPane;
    static Scene inventoryScene;
    static Parent shootOutPane;
    static Scene shootOutScene;
    static Parent thiefMenuPane;
    static Scene thiefMenuScene;
    private static Parent descripitionPane;
    static Scene lootScene;
    static Parent lootPane;
    static Scene deadThiefScene;
    static Parent deadThiefPane;
    static Scene hireScene;
    static Parent hirePane;

    // Core Java - WAY too many variables here
    private static final String trailVersion = "1.5.1";
    private static LinkedList <String> gang = new LinkedList<>();
    private static int HealthConditions = 100;
    private static int Distance = 0;
    private static String gunSpriteURL = "piq_119368_400x400.png";
    private static long Score = 0;
    private static String carSpriteURL = "spr_car4_0.png";
    private static int Days = 0;
    private static int wage = 0;
    private static int Food = 0;
    private static int Grenades = 0;
    private static String gunID = "Glock";
    private static int baseAttackDamage = 15;
    private static int Ammo = 0;
    private static int Water = 0;
    private static double Money = 0;
    private static int Pace = 10;
    private static int FoodIntake = 2;
    static Random rand = new Random();
    private static int SickEventChance;
    private static int memberSelect;
    static TravelClass travel = new TravelClass();
    static AlertBox alert = new AlertBox();
    static Store store = new Store();
    private static boolean Moving = false;
    private static int capturedThieves = 0;
    static CareerGang cp = new CareerGang();
    static ArrayList<String> cities = new ArrayList<>();

    public static void main(String args[]) {

        // setting up list of cities
        cities.add("Tallahassee, Florida");
        cities.add("Atlanta, Georgia");
        cities.add("Frankfort, Kentucky");
        cities.add("Denver, Colorado");
        cities.add("Salem, Oregon");

        // setting up description scene, needs to be moved
        try {
            setDescripitionPane(FXMLLoader.load(Main.class.getResource("DescriptionScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Music
        Thread backGroundMusicThread = new Thread(backGroundMusicTask);
        backGroundMusicThread.start();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AlertBox.alertMenuStart();

        Parent mainAnchor = FXMLLoader.load(getClass().getResource("StartScene.fxml"));

        // Start scene
        setMainWindow(primaryStage);

        getMainWindow().setResizable(false);

        getMainWindow().setTitle("The Trail "+trailVersion);

        getMainWindow().setScene(new Scene(mainAnchor));

        getMainWindow().show();
    }

    // Music task
    private static final Task backGroundMusicTask = new Task() {

        @Override
        protected Object call() throws Exception {

            // audio file NEEDS to be .wav
            AudioClip audio = new AudioClip(getClass().getResource("In_Seoul_Retro_80.wav").toExternalForm());

            audio.setVolume(0.3f);

            audio.setCycleCount(INDEFINITE);

            audio.play();
            return null;
        }
    };

    static boolean extremeLowChance(){

        int Chance = (int)(Math.random()*100+1);

        // return true if chance is equal to 90
        return Chance == 90;

        // Testing purposes
        //return true;
    }

    // Getters and Setters

    // Setter in the need of testing before use
    /*static void setMidStorePane(Parent midStorePane) {
        Main.midStorePane = midStorePane;
    }*/

    static Scene getFoodPortionsScene() {
        return FoodPortionsScene;
    }

    static void setFoodPortionsScene(Scene foodPortionsScene) {
        FoodPortionsScene = foodPortionsScene;
    }

    static Scene getPaceScene() {
        return PaceScene;
    }

    static void setPaceScene(Scene paceScene) {
        PaceScene = paceScene;
    }

    static LinkedList<String> getGang() {
        return gang;
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

    static void setWage(int wage) {
        Main.wage = wage;
    }

    public static int getFood() {
        return Food;
    }

    public static void setFood(int food) {
        Food = food;
    }

    static int getGrenades() {
        return Grenades;
    }

    static void setGrenades(int grenades) {
        Grenades = grenades;
    }

    public static int getAmmo() {
        return Ammo;
    }

    public static void setAmmo(int ammo) {
        Ammo = ammo;
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
        Pace = pace;
    }

    static int getFoodIntake() {
        return FoodIntake;
    }

    static void setFoodIntake(int foodIntake) {
        FoodIntake = foodIntake;
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
        HealthConditions = healthConditions;
    }

    static String getGunSpriteURL() {
        return gunSpriteURL;
    }

    static void setGunSpriteURL(String gunSpriteURL) {
        Main.gunSpriteURL = gunSpriteURL;
    }

    static String getCarSpriteURL() {
        return carSpriteURL;
    }

    static void setCarSpriteURL(String carSpriteURL) {
        Main.carSpriteURL = carSpriteURL;
    }

    static String getGunID() {
        return gunID;
    }

    static void setGunID(String gunID) {
        Main.gunID = gunID;
    }

    static int getCapturedThieves() {
        return capturedThieves;
    }

    static void setCapturedThieves(int capturedThieves) {
        Main.capturedThieves = capturedThieves;
    }

    static int getBaseAttackDamage() {
        return baseAttackDamage;
    }

    static void setBaseAttackDamage(int baseAttackDamage) {
        Main.baseAttackDamage = baseAttackDamage;
    }

    static int getSickEventChance() {
        return SickEventChance;
    }

    static void setSickEventChance(int sickEventChance) {
        SickEventChance = sickEventChance;
    }

    static int getMemberSelect() {
        return memberSelect;
    }

    static void setMemberSelect(int memberSelect) {
        Main.memberSelect = memberSelect;
    }

    static Stage getMainWindow() {
        return MainWindow;
    }

    private static void setMainWindow(Stage mainWindow) {
        MainWindow = mainWindow;
    }

    static Parent getCareerAnchor() {
        return careerAnchor;
    }

    static void setCareerAnchor(Parent careerAnchor) {
        Main.careerAnchor = careerAnchor;
    }

    static Parent getPossePane() {
        return possePane;
    }

    static void setPossePane(Parent possePane) {
        Main.possePane = possePane;
    }

    static Scene getStoreScene() {
        return storeScene;
    }

    static void setStoreScene(Scene storeScene) {
        Main.storeScene = storeScene;
    }

    static Parent getGunStorePane() {
        return gunStorePane;
    }

    static void setGunStorePane(Parent gunStorePane) {
        Main.gunStorePane = gunStorePane;
    }

    static Scene getGunStoreScene() {
        return gunStoreScene;
    }

    static void setGunStoreScene(Scene gunStoreScene) {
        Main.gunStoreScene = gunStoreScene;
    }

    public static Stage getAlertWindow() {
        return AlertWindow;
    }

    static Parent getStorePane() {
        return storePane;
    }

    static void setStorePane(Parent storePane) {
        Main.storePane = storePane;
    }

    static Parent getDescripitionPane() {
        return descripitionPane;
    }

    private static void setDescripitionPane(Parent descripitionPane) {
        Main.descripitionPane = descripitionPane;
    }
}