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

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.*;

public class TravelController implements Serializable {

    private static int payDayCountdown = 0;
    static int animationDuration = 15;
    public static TranslateTransition drivingTransition;
    @FXML private Button setOutBtn;
    @FXML private Label distanceLabel;
    @FXML private Label conditionsLabel;
    @FXML private Label daysLabel;
    @FXML private Label statusLabel;
    @FXML private AnchorPane sprite;
    @FXML private ImageView spriteImage;
    public static short distanceSinceCity = 0;

    // when stop button is pressed
    @FXML
    private void stopMoving(){

        Gang.setMoving(false);
        drivingTransition.stop();
    }

    // when set out button is pressed
    @FXML
    private void setOut(){

        updateGUI();
        drivingTransition.play();
        TravelTask();
    }

    // when menu button is pressed
    @FXML
    private void menu(){

        // Runs mid menu
        MidGameMenu.menuMethod();

        Gang.setMoving(false);
        Main.getMenuWindow().showAndWait();
    }

    private void TravelTask() {

        new Thread(() -> {

            // Run your background task(s) here
            Gang.setMoving(true);

            while (Gang.isMoving()) {

                Main.setSickEventChance(Main.rand.nextInt(20)+1);

                Gang.setScore(Gang.getScore() + 25);

                distanceSinceCity = (short) (distanceSinceCity + Gang.getPace());

                payDay();

                try {
                    saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                drive(Main.determineVehicle());

                // resource consumption
                Gang.setDays(Gang.getDays() + 1);
                Gang.setFood(Gang.getFood() - (Gang.getGangMembers().size()* Gang.getFoodIntake()));
                Gang.setWater(Gang.getWater() - (Gang.getGangMembers().size()* Gang.getFoodIntake()));

                Main.checkValues();

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done

                    // city count down
                    if (distanceSinceCity > 500) Main.alert.cityEvent();

                    // updating labels
                    distanceLabel.setText("Distance: "+ Gang.getDistance() +"Mi");
                    daysLabel.setText("Days: "+ Gang.getDays());
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+ Gang.getHealthConditions());
                    setOutBtn.setText("Speedup");

                    //Thief encounter
                    if (Main.Chance((byte)100,(byte)1,(byte)90)) Main.alert.thiefEncounter();

                    // health events
                    HealthClass.determineHealthCondition();
                });

                if (!Gang.isMoving()) break;

                try {
                    Thread.sleep(2400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Platform.runLater(() -> {

                updateGUI();
                drivingTransition.stop();
            });

        }).start();
    }

    @FXML
    public void initialize(){

        Main.checkFullScreen();

        Main.getAlertWindow().setOnCloseRequest(e -> spriteImage.setImage(new Image(Gang.getCarSpriteURL())));

        // updating labels
        distanceLabel.setText("To go: "+ Gang.getDistance() +"Mi");
        conditionsLabel.setText("Condition: "+ Gang.getHealthConditions());
        daysLabel.setText("Days: "+ Gang.getDays());
        statusLabel.setText("Status: Resting");

        // setting up how the animation will work
        drivingTransition = new TranslateTransition();
        drivingTransition.setDuration(Duration.seconds(animationDuration));
        drivingTransition.setToX(Main.getMainWindow().getWidth() - 850);
        drivingTransition.setNode(sprite);
        drivingTransition.setCycleCount(Animation.INDEFINITE);

        // fixes the bug with thread not ending when stage was closed
        Main.getMainWindow().setOnCloseRequest(e -> Gang.setMoving(false));
    }

    /**
     *
     * Does a countdown to payment, and player(s) receive money when it reaches 30 (30 days),
     * of which is determine by the wage of the career the player(s) have chosen
     *
     */
    private void payDay(){

        ++payDayCountdown;

        if (payDayCountdown == 30){

            Gang.setMoney(Gang.getMoney() + Gang.getWage());

            payDayCountdown = 0;
        }
    }

    @FXML
    private void updateGUI(){

        spriteImage.setImage(new Image(Gang.getCarSpriteURL()));

        // updating drivingTransition
        drivingTransition = new TranslateTransition();
        drivingTransition.setDuration(Duration.seconds(animationDuration));
        drivingTransition.setToX((Main.getMainWindow().getWidth() / -1) - 400);
        drivingTransition.setNode(sprite);
        drivingTransition.setCycleCount(Animation.INDEFINITE);

        // updating labels
        distanceLabel.setText("To go: "+ Gang.getDistance() +"Mi");
        conditionsLabel.setText("Condition: "+ Gang.getHealthConditions());
        daysLabel.setText("Days: "+ Gang.getDays());
        statusLabel.setText("Status: Resting");
    }

    private void drive(Vehicle v){

        v.drive();
    }

    private void saveData() throws IOException {

        FileOutputStream fileStream = new FileOutputStream("SaveGame.ser");

        ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("SaveGame.ser"));

        System.out.print(inputStream.read());

        outputStream.write(Gang.getFood());

        outputStream.close();
        fileStream.close();
    }
}
