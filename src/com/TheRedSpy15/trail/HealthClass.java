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

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HealthClass {

    private static byte healthEventCoolDown = 10;

    // Used to determine health condition based upon tiredness, pace, and intake
    protected static void determineHealthCondition(){

        switch (Gang.getPace()){

            case 15:
                Gang.setHealthConditions(Gang.getHealthConditions() - 3);
                break;
            case 10:
                Gang.setHealthConditions(Gang.getHealthConditions() + 1);
                break;
            case 1:
                Gang.setHealthConditions(Gang.getHealthConditions() + 2);
                break;
        }

        switch (Gang.getFoodIntake()){

            case 1:
                if (Gang.getFood() != 0) Gang.setHealthConditions(Gang.getHealthConditions() - 5);
                break;
            case 2:
                if (Gang.getFood() != 0) Gang.setHealthConditions(Gang.getHealthConditions() + 1);
                break;
            case 3:
                if (Gang.getFood() != 0) Gang.setHealthConditions(Gang.getHealthConditions() + 5);
                break;
        }

        if (Gang.getFood() == 0){

            if (Gang.getWater() == 0){

                Gang.setHealthConditions(Gang.getHealthConditions() - 10);
            }

            Gang.setHealthConditions(Gang.getHealthConditions() - 10);
        }

        if (Gang.getHealthConditions() > 100) Gang.setHealthConditions(100);
        if (Gang.getHealthConditions() < 0) Gang.setHealthConditions(0);

        if (healthEventCoolDown >= 10){

            if (Main.getSickEventChance() > Gang.getHealthConditions()){

                HealthClass.poorHealthEvent();
            }

            healthEventCoolDown = 0;
        } healthEventCoolDown++;
    }

    // Events to happen with poor health conditions
    protected static void poorHealthEvent(){

        Gang.setMoving(false);
        TravelController.drivingTransition.pause();

        VBox PoorHealthLayout = new VBox(10);
        PoorHealthLayout.setPadding(new Insets(40,20,20,20));

        Label SickEventLbl = new Label("");
        SickEventLbl.setStyle("-fx-text-fill: white;");
        SickEventLbl.setFont(new Font(20));

        PoorHealthLayout.setStyle("-fx-background-color: RED;");
        PoorHealthLayout.getChildren().add(SickEventLbl);
        Main.setSickEventScene(new Scene(PoorHealthLayout));
        Main.getAlertWindow().setScene(Main.getSickEventScene());

        if (Main.getSickEventChance() <= 10){ // death

            SickEventLbl.setText(Gang.getGangMembers().pop()+" Passed away...");

            Gang.setHealthConditions(Gang.getHealthConditions() + 60);

            Main.getAlertWindow().showAndWait();
        }else{

            switch (Main.getSickEventChance()){ // non deadly

                case 11:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Got a cold");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 5);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 12:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Has a Fever");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 10);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 13:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Broke a leg.... at least they got two");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 10);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 14:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Broke an arm... at least they got two");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 10);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 15:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Threw up... there were some chunks in it too!");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 10);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 16:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Has an infection");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 15);
                    Main.getAlertWindow().showAndWait();
                    break;
                case 17:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Has the flu");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 15);
                    Main.getAlertWindow().showAndWait();
                    break;
                default:
                    SickEventLbl.setText(Gang.getGangMembers().peek()+" Is sick... of this game");
                    Gang.setHealthConditions(Gang.getHealthConditions() - 10);
                    Main.getAlertWindow().showAndWait();
                    break;
            }
        }

        // making sure values are not negative
        Main.checkValues();

        // Game over condition
        if (Gang.getGangMembers().size() <= 0){

            Gang.setMoving(false);

            AlertBox.gameOver();
        }
    }
}
