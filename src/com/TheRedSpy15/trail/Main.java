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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.scene.media.AudioClip.INDEFINITE;

/**
 * Serves as the class where the program gets launched.
 *
 * It also, provides some methods that
 * are useful utilises designed specifically
 * for the Trail
 */
public class Main extends Application implements Serializable{

    // JavaFX - WAY too many variables here
    private static Stage MainWindow;
    private static Scene FoodPortionsScene;
    private static Stage MenuWindow;
    private static Scene SickEventScene;
    private static Stage AlertWindow = new Stage();
    private static Scene PaceScene;
    private static Parent careerAnchor;
    private static Parent storePane;
    private static Parent gangPane;
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
    private static final String trailVersion = "2.4.7";
    private static byte SickEventChance;
    static Random rand = new Random();
    static AlertBox alert = new AlertBox();
    static Store store = new Store();
    static Career career = new Career();
    static Gang gang = new Gang();
    static Main main = new Main();
    static ArrayList<String> cities = new ArrayList<>();

    byte startingThreads = 6; // 6 because it is safe
    boolean autoSave = true;

    public static void main(String args[]) {

        /*
            - REALLY WANT DONE -

        FIX THE THIEF KILLED SCENE!!!
        Purchase alerts need auditing
        Move thief encounter to main window
        Jfoenix tab pane blue fix in gun store


        BUG LIST

        *

        */

        main.startingThreads += Thread.activeCount(); // used to limit travel threads to a safe and reasonable limit

        main.setupCities();

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

        getMainWindow().setTitle("The Trail " + trailVersion);

        getMainWindow().setScene(new Scene(mainAnchor));

        getMainWindow().show();

        getMainWindow().setOnCloseRequest(e -> {
            e.consume();
            quitGame();

            if (Main.gang.getGangMembers().size() > 0) saveGameState();
        });

        //Thread serverThread = new Thread(Main::setUpSever);
        //Thread clientThread = new Thread(Main::setUpNetworking);
        //serverThread.start();
        //clientThread.start();
    }

    /**
     * Used to save game progress ONLY if
     * auto save is true. NOTE: game progress,
     * not game state (despite name). As in it will not
     * save that the player was i.e fighting a thief,
     * just their inventory etc.
     */
    void saveGameState(){

        if (autoSave && Main.gang.isPassedSetup()){

            try {
                Gang.saveData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * For a secret upcoming feature
     */
    private static void setUpNetworking(){

        try {
            Socket sock = new Socket("127.0.0.1", 4242);
            InputStreamReader inputStreamReader = new InputStreamReader(sock.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.print("Connected - " + bufferedReader.readLine());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * For a secret upcoming feature
     */
    static private void setUpSever(){

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4242);

            while (true){

                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.print("Bananas");
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to limit Food, Water, Money, and
     * Health conditions. It also,
     * prevents the values from being negative.
     */
    void checkValues(){

        byte maxHealthConditions = 100;

        if (gang.getFood() < 0) gang.setFood(0);
        if (gang.getWater() < 0) gang.setWater(0);
        if (gang.getMoney() < 0) gang.setMoney(0);
        if (gang.getHealthConditions() < 0) gang.setHealthConditions(0);
        if (gang.getHealthConditions() > maxHealthConditions) gang.setHealthConditions(100);
    }

    /**
     * Adds all cities in the game,
     * to the Cities array list
     */
    private void setupCities(){

        cities.add("Tallahassee, Florida");
        cities.add("Atlanta, Georgia");
        cities.add("Nashville, Tennessee");
        cities.add("Frankfort, Kentucky");
        cities.add("Jefferson City, Missouri");
        cities.add("Kansas City, Kansas");
        cities.add("Denver, Colorado");
        cities.add("Salt Lake City, Utah");
        cities.add("Las Vegas, Nevada");
        cities.add("Salem, Oregon");
        cities.add("Sacramento, California");
        cities.add("Los Angeles, California");
        cities.add("Tucson, Arizona");
        cities.add("Albuquerque, New Mexico");
        cities.add("Oklahoma City, Oklahoma");
        cities.add("Dallas, Texas");
        cities.add("Jackson, Mississippi");
        cities.add("Tuscaloosa, Alabama");
    }

    /**
     * Creates a window and scene that
     * asks the user if they wish to quit.
     *
     * And, will save on quit, IF auto-save
     * is true.
     */
    private void quitGame(){

        HBox buttonLayout = new HBox(20);
        VBox layout = new VBox(20);
        Label exitLbl = new Label("Are you sure you want to quit?");
        Button yesBtn = new Button("Yes, I'm bored to death");
        Button noBtn = new Button("NO! THIS GAME IS SO FUN!");
        Stage exitWindow = new Stage();

        yesBtn.setOnAction(e -> {
            saveGameState();
            System.exit(0);
        });

        noBtn.setOnAction(e -> exitWindow.close());

        exitLbl.setStyle("-fx-text-fill: white;");
        exitLbl.setFont(new Font(20));

        noBtn.setStyle("-fx-text-fill: Black;");
        noBtn.setFont(new Font(20));

        yesBtn.setStyle("-fx-text-fill: Black;");
        yesBtn.setFont(new Font(20));

        buttonLayout.getChildren().addAll(yesBtn,noBtn);
        layout.getChildren().addAll(exitLbl,buttonLayout);
        layout.setStyle("-fx-background-color: #cf1020");
        layout.setPadding(new Insets(20,20,20,20));

        Scene exitScene = new Scene(layout);

        exitWindow.initModality(Modality.APPLICATION_MODAL);
        exitWindow.setTitle("Quit?");
        exitWindow.setScene(exitScene);
        exitWindow.showAndWait();
    }

    /**
     * It's return object is based upon the
     * value of "vehicleID"
     *
     * @return
     * Objects that implement the vehicle interface
     */
    Vehicle determineVehicle(){

        StarterCar starterCar = new StarterCar();
        MonsterTruck monsterTruck = new MonsterTruck();
        RallyCar rallyCar = new RallyCar();
        SpeedDemon speedDemon = new SpeedDemon();

        switch (Main.gang.getVehicleID()){
            case "Monster Truck": return monsterTruck;
            case "Rally Car": return rallyCar;
            case "Speed Demon": return speedDemon;
            default: return starterCar;
        }
    }

    /**
     * Where the game's background
     * music is configured
     */
    private static final Task backGroundMusicTask = new Task() {

        @Override
        protected Object call() {

            float volume = 0.3f;

            // audio file NEEDS to be .wav
            AudioClip audio = new AudioClip(getClass().getResource("In_Seoul_Retro_80.wav").toExternalForm());

            audio.setVolume(volume);

            audio.setCycleCount(INDEFINITE);

            audio.play();
            return null;
        }
    };

    /**
     * Creates a random values between
     * the Max and Min values. If it is equal to Target,
     * it returns true
     *
     * @param max Max range
     * @param min Min range
     * @param target Value the random value must be
     *              equal to in order to return true
     * @return True / False
     */
    static boolean Chance(byte max, byte min, byte target){

        int Chance = (int)(Math.random()*max+min);

        return Chance == target;
    }

    // Getters and Setters
    Scene getSellScene() {
        return SellScene;
    }
    void setSellScene(Scene midSellStoreScene) {
        Main.SellScene = midSellStoreScene;
    }
    Scene getThiefMenuScene() {
        return thiefMenuScene;
    }
    void setThiefMenuScene(Scene thiefMenuScene) {
        Main.thiefMenuScene = thiefMenuScene;
    }
    Stage getMenuWindow() {
        return MenuWindow;
    }
    void setMenuWindow(Stage menuWindow) {
        MenuWindow = menuWindow;
    }
    Scene getSickEventScene() {
        return SickEventScene;
    }
    void setSickEventScene(Scene sickEventScene) {
        SickEventScene = sickEventScene;
    }
    Parent getTravelPane() {
        Travel.travelSceneSetup();
        return travelPane;
    }
    void setTravelPane(Parent travelPane) {
        Main.travelPane = travelPane;
    }
    Parent getCityPane() {
        return cityPane;
    }
    void setCityPane(Parent cityPane) {
        Main.cityPane = cityPane;
    }
    Parent getSellPane() {
        return SellPane;
    }
    void setSellPane(Parent sellPane) {
        Main.SellPane = sellPane;
    }
    Scene getCityScene() {
        return cityScene;
    }
    void setCityScene(Scene cityScene) {
        Main.cityScene = cityScene;
    }
    Parent getDealerPane() {
        return dealerPane;
    }
    void setDealerPane(Parent dealerPane) {
        Main.dealerPane = dealerPane;
    }
    Scene getDealerScene() {
        return dealerScene;
    }
    void setDealerScene(Scene dealerScene) {
        Main.dealerScene = dealerScene;
    }
    Parent getInventoryPane() {
        return inventoryPane;
    }
    void setInventoryPane(Parent inventoryPane) {
        Main.inventoryPane = inventoryPane;
    }
    Scene getInventoryScene() {
        return inventoryScene;
    }
    void setInventoryScene(Scene inventoryScene) {
        Main.inventoryScene = inventoryScene;
    }
    Parent getShootOutPane() {
        return shootOutPane;
    }
    void setShootOutPane(Parent shootOutPane) {
        Main.shootOutPane = shootOutPane;
    }
    Scene getShootOutScene() {
        return shootOutScene;
    }
    void setShootOutScene(Scene shootOutScene) {
        Main.shootOutScene = shootOutScene;
    }
    Parent getThiefMenuPane() {
        return thiefMenuPane;
    }
    void setThiefMenuPane(Parent thiefMenuPane) {
        Main.thiefMenuPane = thiefMenuPane;
    }
    Scene getLootScene() {
        return lootScene;
    }
    void setLootScene(Scene lootScene) {
        Main.lootScene = lootScene;
    }
    Parent getLootPane() {
        return lootPane;
    }
    void setLootPane(Parent lootPane) {
        Main.lootPane = lootPane;
    }
    Scene getDeadThiefScene() {
        return deadThiefScene;
    }
    void setDeadThiefScene(Scene DeadThiefScene) {
        deadThiefScene = DeadThiefScene;
    }
    Parent getDeadThiefPane() {
        return deadThiefPane;
    }
    void setDeadThiefPane(Parent DeadThiefPane) {
        deadThiefPane = DeadThiefPane;
    }
    Scene getHireScene() {
        return hireScene;
    }
    void setHireScene(Scene hireScene) {
        Main.hireScene = hireScene;
    }
    Parent getHirePane() {
        return hirePane;
    }
    void setHirePane(Parent hirePane) {
        Main.hirePane = hirePane;
    }
    Scene getFoodPortionsScene() {
        return FoodPortionsScene;
    }
    void setFoodPortionsScene(Scene foodPortionsScene) {
        FoodPortionsScene = foodPortionsScene;
    }
    Scene getPaceScene() {
        return PaceScene;
    }
    void setPaceScene(Scene paceScene) {
        PaceScene = paceScene;
    }
    int getSickEventChance() {
        return SickEventChance;
    }
    void setSickEventChance(int sickEventChance) {
        SickEventChance = (byte) sickEventChance;
    }
    Stage getMainWindow() {
        return MainWindow;
    }
    private void setMainWindow(Stage mainWindow) {
        MainWindow = mainWindow;
    }
    Parent getCareerAnchor() {
        return careerAnchor;
    }
    void setCareerAnchor(Parent careerAnchor) {
        Main.careerAnchor = careerAnchor;
    }
    Parent getGangPane() {
        return gangPane;
    }
    void setGangPane(Parent possePane) {
        Main.gangPane = possePane;
    }
    Scene getStoreScene() {
        return storeScene;
    }
    void setStoreScene(Scene storeScene) {
        Main.storeScene = storeScene;
    }
    Parent getGunStorePane() {
        return gunStorePane;
    }
    void setGunStorePane(Parent gunStorePane) {
        Main.gunStorePane = gunStorePane;
    }
    Scene getGunStoreScene() {
        return gunStoreScene;
    }
    void setGunStoreScene(Scene gunStoreScene) {
        Main.gunStoreScene = gunStoreScene;
    }
    static Stage getAlertWindow() {
        return AlertWindow;
    }
    Parent getStorePane() {
        return storePane;
    }
    void setStorePane(Parent storePane) {
        Main.storePane = storePane;
    }
    Parent getDescriptionPane() {
        return descriptionPane;
    }
    void setDescriptionPane(Parent descriptionPane) {
        Main.descriptionPane = descriptionPane;
    }
}