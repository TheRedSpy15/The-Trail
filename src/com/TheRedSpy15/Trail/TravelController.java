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

public class TravelController extends Main {

    private static int payDayCountdown = 0;
    static int animationSpeed = 15;
    private TranslateTransition transition;
    @FXML private Button setOutBtn;
    @FXML private Label distanceLabel;
    @FXML private Label conditionsLabel;
    @FXML private Label daysLabel;
    @FXML private Label statusLabel;
    @FXML private AnchorPane sprite;
    @FXML private ImageView spriteImage;

    // when stop button is pressed
    @FXML
    private void stopMoving(){

        transition.pause();
        setMoving(false);
        transition.pause();
    }

    // when set out button is pressed
    @FXML
    private void setOut(){

        updateGUI();
        transition.play();
        runBackgroundTask();
    }

    // when menu button is pressed
    @FXML
    private void menu(){

        // Runs mid menu
        MidGameMenu.menuMethod();

        setMoving(false);
        MenuWindow.showAndWait();
    }

    private void runBackgroundTask() {

        new Thread(() -> {

            // Run your background task(s) here
            setMoving(true);

            while (isMoving()) {

                setSickEventChance(rand.nextInt(20)+1);

                setScore(getScore() + 25);

                distanceSinceCity += getPace();

                // Payday countdown
                payDay();

                // resource consumption
                setDays(getDays() + 1);
                setDistance(getDistance() + getPace());
                setFood(getFood() - (getGang().size()* getFoodIntake()));
                setWater(getWater() - (getGang().size()* getFoodIntake()));

                if (getFood() < 0) setFood(0);
                if (getWater() < 0) setWater(0);
                if (getMoney() > 10000) setMoney(10000);
                if (getMoney() < 0) setMoney(0);

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done

                    // city count down
                    if (distanceSinceCity > 500) alert.cityEvent();

                    // updating labels
                    distanceLabel.setText("Distance: "+ getDistance() +"Mi");
                    daysLabel.setText("Days: "+ getDays());
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+ getHealthConditions());
                    setOutBtn.setText("Speedup");

                    //Thief encounter
                    if (extremeLowChance()) alert.thiefEncounter();

                    // health events
                    HealthClass.determineHealthCondition();
                });

                if (!isMoving()) break;

                try {
                    Thread.sleep(2400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Platform.runLater(() -> {

                statusLabel.setText("Status: Resting");
                setOutBtn.setText("Set out");
                transition.pause();
            });

        }).start();
    }

    @FXML
    public void initialize(){

        getAlertWindow().setOnCloseRequest(e -> spriteImage.setImage(new Image(getCarSpriteURL())));

        // updating labels
        distanceLabel.setText("To go: "+ getDistance() +"Mi");
        conditionsLabel.setText("Condition: "+ getHealthConditions());
        daysLabel.setText("Days: "+ getDays());
        statusLabel.setText("Status: Resting");

        // setting up how the animation will work
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animationSpeed));
        transition.setToX(-850);
        transition.setNode(sprite);
        transition.setCycleCount(Animation.INDEFINITE);

        // fixes the bug with thread not ending when stage was closed
        getMainWindow().setOnCloseRequest(e -> setMoving(false));
    }

    private void payDay(){

        ++payDayCountdown;

        if (payDayCountdown == 30){

            setMoney(getMoney() + getWage());

            payDayCountdown = 0;
        }
    }

    @FXML
    private void updateGUI(){

        spriteImage.setImage(new Image(getCarSpriteURL()));

        // updating transition
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animationSpeed));
        transition.setToX(-850);
        transition.setNode(sprite);
        transition.setCycleCount(Animation.INDEFINITE);

        // updating labels
        distanceLabel.setText("To go: "+ getDistance() +"Mi");
        conditionsLabel.setText("Condition: "+ getHealthConditions());
        daysLabel.setText("Days: "+ getDays());
        statusLabel.setText("Status: Resting");
    }
}
