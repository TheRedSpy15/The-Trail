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

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.Collections;

public class HealthClass {

    private static byte healthEventCoolDown = 0;

    // Used to determine health condition based upon tiredness, pace, and intake
    protected static void determineHealthCondition(){

        final byte highBrakeFrequency = 15;
        final byte lowBrakeFrequency = 5;

        final byte highFoodIntake = 15;
        final byte lowFoodIntake = 5;

        switch (Main.gang.getBrakeFrequency()){

            case highBrakeFrequency:
                Main.gang.setHealthConditions(Main.gang.getHealthConditions() - 3);
                break;
            case lowBrakeFrequency:
                Main.gang.setHealthConditions(Main.gang.getHealthConditions() + 2);
                break;
            default:
                Main.gang.setHealthConditions(Main.gang.getHealthConditions() + 1);
                break;
        }

        switch (Main.gang.getFoodIntake()){

            case lowFoodIntake:
                if (Main.gang.getFood() != 0) Main.gang.setHealthConditions(Main.gang.getHealthConditions() - 5);
                break;
            case highFoodIntake:
                if (Main.gang.getFood() != 0) Main.gang.setHealthConditions(Main.gang.getHealthConditions() + 5);
                break;
            default:
                if (Main.gang.getFood() != 0) Main.gang.setHealthConditions(Main.gang.getHealthConditions() + 1);
                break;
        }

        if (Main.gang.getFood() <= 0){

            Main.gang.setHealthConditions(Main.gang.getHealthConditions() - 10);
        }

        if (Main.gang.getWater() <= 0){

            Main.gang.setHealthConditions(Main.gang.getHealthConditions() - 10);
        }

        Main.main.checkValues();

        byte healthEventCoolDownDuration = 10;
        if (healthEventCoolDown >= healthEventCoolDownDuration){

            if (Main.main.getSickEventChance() > Main.gang.getHealthConditions()){

                HealthClass.poorHealthEvent();
            }

            healthEventCoolDown = 0;
        } healthEventCoolDown++;
    }

    // Events to happen with poor health conditions
    private static void poorHealthEvent(){

        Collections.shuffle(Main.gang.getGangMembers());

        final byte deathChance = 30;
        final byte healthBonus = 60;
        final byte healthLoss = 15;

        Main.gang.setMoving(false);
        TravelController.drivingTransition.pause();

        VBox PoorHealthLayout = new VBox(10);
        PoorHealthLayout.setPadding(new Insets(40,20,20,20));

        Label SickEventLbl = new Label("");
        SickEventLbl.setStyle("-fx-text-fill: white;");
        SickEventLbl.setFont(new Font(20));

        PoorHealthLayout.setStyle("-fx-background-color: RED;");
        PoorHealthLayout.getChildren().add(SickEventLbl);
        Main.main.setSickEventScene(new Scene(PoorHealthLayout));
        Main.getAlertWindow().setScene(Main.main.getSickEventScene());

        if (Main.main.getSickEventChance() <= deathChance){ // death

            SickEventLbl.setText(death()+" Passed away...");

            Main.gang.setHealthConditions(Main.gang.getHealthConditions() + healthBonus);

            alertSyncShowWait();
        }else{ // non-death messages

            String[] healthResponses = {
                    "got a cold","has a fever","broke a leg",
                    "broke an arm","threw up","has an infection",
                    "has the flu","is sick"
            };

            SickEventLbl.setText(Main.gang.getGangMembers().peek()+" "+healthResponses[(byte) (Math.random() * healthResponses.length)]);

            Main.gang.setHealthConditions(Main.gang.getHealthConditions() - healthLoss);
            Main.main.checkValues();

            Main.getAlertWindow().showAndWait();
        }
    }

    /**
     * Removes a member from the gang stack,
     * and returns it
     *
     * also, it shuffles the gang stack, as
     * well as adding that name to the
     * deceased list
     *
     * @return name of member that was killed
     */
    static String death(){

        Collections.shuffle(Main.gang.getGangMembers());

        Main.gang.getDeceased().push(Main.gang.getGangMembers().peek());

        return Main.gang.getGangMembers().pop();
    }

    private static synchronized void alertSyncShowWait(){

        Main.getAlertWindow().showAndWait();
    }

    static void checkGameOver(){

        // Game over condition
        if (Main.gang.getGangMembers().size() <= 0){

            Main.gang.setMoving(false);

            AlertBox.gameOver();
        }
    }
}
