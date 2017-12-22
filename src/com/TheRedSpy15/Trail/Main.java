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

/* - TODO

    BUG LIST

    * Jar file not working on other PCs
    * Back button in purchase alert - causing freezes

    Exception in thread "JavaFX Application Thread" java.lang.IllegalStateException: Stage already visible
	at javafx.stage.Stage.showAndWait(Stage.java:460)
	at com.TheRedSpy15.trail.HealthClass.poorHealthEvent(HealthClass.java:111)
	at com.TheRedSpy15.trail.HealthClass.determineHealthCondition(HealthClass.java:79)
	at com.TheRedSpy15.trail.TravelController.lambda$null$0(TravelController.java:119)
	at com.sun.javafx.application.PlatformImpl.lambda$null$173(PlatformImpl.java:295)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.javafx.application.PlatformImpl.lambda$runLater$174(PlatformImpl.java:294)
	at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
	at com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
	at com.sun.glass.ui.win.WinApplication.lambda$null$148(WinApplication.java:191)
	at java.lang.Thread.run(Thread.java:748)

	Exception in thread "JavaFX Application Thread" java.lang.IllegalStateException: Stage already visible
	at javafx.stage.Stage.showAndWait(Stage.java:460)
	at com.TheRedSpy15.trail.HealthClass.poorHealthEvent(HealthClass.java:128)
	at com.TheRedSpy15.trail.HealthClass.determineHealthCondition(HealthClass.java:79)
	at com.TheRedSpy15.trail.TravelController.lambda$null$0(TravelController.java:120)
	at com.sun.javafx.application.PlatformImpl.lambda$null$173(PlatformImpl.java:295)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.javafx.application.PlatformImpl.lambda$runLater$174(PlatformImpl.java:294)
	at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
	at com.sun.glass.ui.win.WinApplication._enterNestedEventLoopImpl(Native Method)
	at com.sun.glass.ui.win.WinApplication._enterNestedEventLoop(WinApplication.java:218)
	at com.sun.glass.ui.Application.enterNestedEventLoop(Application.java:511)
	at com.sun.glass.ui.EventLoop.enter(EventLoop.java:107)
	at com.sun.javafx.tk.quantum.QuantumToolkit.enterNestedEventLoop(QuantumToolkit.java:583)
	at javafx.stage.Stage.showAndWait(Stage.java:474)
	at com.TheRedSpy15.trail.HealthClass.poorHealthEvent(HealthClass.java:128)
	at com.TheRedSpy15.trail.HealthClass.determineHealthCondition(HealthClass.java:79)
	at com.TheRedSpy15.trail.TravelController.lambda$null$0(TravelController.java:120)
	at com.sun.javafx.application.PlatformImpl.lambda$null$173(PlatformImpl.java:295)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.javafx.application.PlatformImpl.lambda$runLater$174(PlatformImpl.java:294)
	at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
	at com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
	at com.sun.glass.ui.win.WinApplication.lambda$null$148(WinApplication.java:191)
	at java.lang.Thread.run(Thread.java:748)


    Features

    * A food portion scene with sliders
    * View gang member scene in mid game menu
    * Add red car to dealer ship
    * Different cars have different specs
    * help scene in mid game menu
    * More cities
    * Alert box need huge rework
    * Personal armoury (gun inventory for selecting different guns with different traits)
    * More guns

    */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import static com.TheRedSpy15.trail.Gang.*;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    // Javafx - WAY too many variables here
    private static Stage MainWindow;
    private static Scene FoodPortionsScene;
    private static Stage MenuWindow;
    private static Scene SickEventScene;
    private static Stage AlertWindow = new Stage();
    private static Scene PaceScene;
    private static Parent careerAnchor;
    private static Parent storePane;
    private static Parent possePane;
    private static Parent travelPane;
    private static Parent cityPane;
    private static Parent SellPane;
    private static Scene SellScene;
    private static Scene cityScene;
    private static Scene storeScene;
    private static Parent dealerPane;
    private static Scene dealerScene;
    private static Parent gunStorePane;
    private static Scene gunStoreScene;
    private static Parent inventoryPane;
    private static Scene inventoryScene;
    private static Parent shootOutPane;
    private static Scene shootOutScene;
    private static Parent thiefMenuPane;
    private static Scene thiefMenuScene;
    private static Parent descriptionPane;
    private static Scene lootScene;
    private static Parent lootPane;
    private static Scene deadThiefScene;
    private static Parent deadThiefPane;
    private static Scene hireScene;
    private static Parent hirePane;

    // Core Java
    private static final String trailVersion = "1.9.6";
    private static byte SickEventChance;
    static Boolean fullScreen = false;
    static Random rand = new Random();
    static AlertBox alert = new AlertBox();
    static Store store = new Store();
    static Career career = new Career();
    static Gang gang = new Gang();
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
            setDescriptionPane(FXMLLoader.load(Main.class.getResource("DescriptionScene.fxml")));
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

    static void checkValues(){

        if (getFood() < 0) setFood(0);
        if (getWater() < 0) setWater(0);
        if (getMoney() > 10000) setMoney(100000);
        if (getMoney() < 0) setMoney(0);
        if (getHealthConditions() < 0) setHealthConditions(0);
    }

    static void checkFullScreen(){

        if (fullScreen && !getAlertWindow().isShowing()){

            getMainWindow().setFullScreen(true);
        }
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

    static boolean Chance(byte max, byte min, byte target){

        int Chance = (int)(Math.random()*max+min);

        // return true if chance is equal to 90
        return Chance == target;
    }

    // Getters and Setters
    static Scene getSellScene() {
        return SellScene;
    }
    static void setSellScene(Scene midSellStoreScene) {
        Main.SellScene = midSellStoreScene;
    }
    static Scene getThiefMenuScene() {
        return thiefMenuScene;
    }
    static void setThiefMenuScene(Scene thiefMenuScene) {
        Main.thiefMenuScene = thiefMenuScene;
    }
    static Stage getMenuWindow() {
        return MenuWindow;
    }
    static void setMenuWindow(Stage menuWindow) {
        MenuWindow = menuWindow;
    }
    static Scene getSickEventScene() {
        return SickEventScene;
    }
    static void setSickEventScene(Scene sickEventScene) {
        SickEventScene = sickEventScene;
    }
    static Parent getTravelPane() {
        return travelPane;
    }
    static void setTravelPane(Parent travelPane) {
        Main.travelPane = travelPane;
    }
    static Parent getCityPane() {
        return cityPane;
    }
    static void setCityPane(Parent cityPane) {
        Main.cityPane = cityPane;
    }
    static Parent getSellPane() {
        return SellPane;
    }
    static void setSellPane(Parent sellPane) {
        Main.SellPane = sellPane;
    }
    static Scene getCityScene() {
        return cityScene;
    }
    static void setCityScene(Scene cityScene) {
        Main.cityScene = cityScene;
    }
    static Parent getDealerPane() {
        return dealerPane;
    }
    static void setDealerPane(Parent dealerPane) {
        Main.dealerPane = dealerPane;
    }
    static Scene getDealerScene() {
        return dealerScene;
    }
    static void setDealerScene(Scene dealerScene) {
        Main.dealerScene = dealerScene;
    }
    static Parent getInventoryPane() {
        return inventoryPane;
    }
    static void setInventoryPane(Parent inventoryPane) {
        Main.inventoryPane = inventoryPane;
    }
    static Scene getInventoryScene() {
        return inventoryScene;
    }
    static void setInventoryScene(Scene inventoryScene) {
        Main.inventoryScene = inventoryScene;
    }
    static Parent getShootOutPane() {
        return shootOutPane;
    }
    static void setShootOutPane(Parent shootOutPane) {
        Main.shootOutPane = shootOutPane;
    }
    static Scene getShootOutScene() {
        return shootOutScene;
    }
    static void setShootOutScene(Scene shootOutScene) {
        Main.shootOutScene = shootOutScene;
    }
    static Parent getThiefMenuPane() {
        return thiefMenuPane;
    }
    static void setThiefMenuPane(Parent thiefMenuPane) {
        Main.thiefMenuPane = thiefMenuPane;
    }
    static Scene getLootScene() {
        return lootScene;
    }
    static void setLootScene(Scene lootScene) {
        Main.lootScene = lootScene;
    }
    static Parent getLootPane() {
        return lootPane;
    }
    static void setLootPane(Parent lootPane) {
        Main.lootPane = lootPane;
    }
    static Scene getDeadThiefScene() {
        return deadThiefScene;
    }
    static void setDeadThiefScene(Scene deadThiefScene) {
        Main.deadThiefScene = deadThiefScene;
    }
    static Parent getDeadThiefPane() {
        return deadThiefPane;
    }
    static void setDeadThiefPane(Parent deadThiefPane) {
        Main.deadThiefPane = deadThiefPane;
    }
    static Scene getHireScene() {
        return hireScene;
    }
    static void setHireScene(Scene hireScene) {
        Main.hireScene = hireScene;
    }
    static Parent getHirePane() {
        return hirePane;
    }
    static void setHirePane(Parent hirePane) {
        Main.hirePane = hirePane;
    }
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
    static int getSickEventChance() {
        return SickEventChance;
    }
    static void setSickEventChance(int sickEventChance) {
        SickEventChance = (byte) sickEventChance;
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
    static Parent getDescriptionPane() {
        return descriptionPane;
    }
    private static void setDescriptionPane(Parent descriptionPane) {
        Main.descriptionPane = descriptionPane;
    }
}