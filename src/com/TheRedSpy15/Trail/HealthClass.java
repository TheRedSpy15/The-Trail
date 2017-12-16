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

public class HealthClass extends Main {

    private static int healthEventCooldown = 10;

    // Used to determine health condition based upon tiredness, pace, and intake
    protected static void determineHealthCondition(){

        switch (getPace()){

            case 15:
                setHealthConditions(getHealthConditions() - 3);
                break;
            case 10:
                setHealthConditions(getHealthConditions() + 1);
                break;
            case 1:
                setHealthConditions(getHealthConditions() + 2);
                break;
        }

        switch (getFoodIntake()){

            case 1:
                if (getFood() != 0) setHealthConditions(getHealthConditions() - 5);
                break;
            case 2:
                if (getFood() != 0) setHealthConditions(getHealthConditions() + 1);
                break;
            case 3:
                if (getFood() != 0) setHealthConditions(getHealthConditions() + 5);
                break;
        }

        if (getFood() == 0){

            if (getWater() == 0){

                setHealthConditions(getHealthConditions() - 10);
            }

            setHealthConditions(getHealthConditions() - 10);
        }

        if (getHealthConditions() > 100) setHealthConditions(100);
        if (getHealthConditions() < 0) setHealthConditions(0);

        if (healthEventCooldown >= 10){

            if (getSickEventChance() > getHealthConditions()){

                setMemberSelect(getGang().size());
                setMemberSelect(getMemberSelect() - 1);
                HealthClass.poorHealthEvent();
            }

            healthEventCooldown = 0;
        } healthEventCooldown++;
    }

    // Events to happen with poor health conditions
    protected static void poorHealthEvent(){

        setMoving(false);

        VBox PoorHealthLayout = new VBox(10);
        PoorHealthLayout.setPadding(new Insets(40,20,20,20));

        Label SickEventLbl = new Label("");
        SickEventLbl.setStyle("-fx-text-fill: white;");
        SickEventLbl.setFont(new Font(20));

        PoorHealthLayout.setStyle("-fx-background-color: RED;");
        PoorHealthLayout.getChildren().add(SickEventLbl);
        SickEventScene = new Scene(PoorHealthLayout);
        getAlertWindow().setScene(SickEventScene);

        if (getSickEventChance() <= 10){

            SickEventLbl.setText(getGang().get(getMemberSelect())+" Passed away...");

            setHealthConditions(getHealthConditions() + 60);

            getGang().remove(getMemberSelect());

            getAlertWindow().showAndWait();
        }else{

            switch (getSickEventChance()){

                case 11:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Got a cold");
                    setHealthConditions(getHealthConditions() - 5);
                    getAlertWindow().showAndWait();
                    break;
                case 12:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Has a Fever");
                    setHealthConditions(getHealthConditions() - 10);
                    getAlertWindow().showAndWait();
                    break;
                case 13:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Broke a leg.... at least they got two");
                    setHealthConditions(getHealthConditions() - 10);
                    getAlertWindow().showAndWait();
                    break;
                case 14:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Broke an arm... at least they got two");
                    setHealthConditions(getHealthConditions() - 10);
                    getAlertWindow().showAndWait();
                    break;
                case 15:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Threw up... there were some chunks in it too!");
                    setHealthConditions(getHealthConditions() - 10);
                    getAlertWindow().showAndWait();
                    break;
                case 16:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Has an infection");
                    setHealthConditions(getHealthConditions() - 15);
                    getAlertWindow().showAndWait();
                    break;
                case 17:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Has the flu");
                    setHealthConditions(getHealthConditions() - 15);
                    getAlertWindow().showAndWait();
                    break;
                default:
                    SickEventLbl.setText(getGang().get(getMemberSelect())+" Is sick... of this game");
                    setHealthConditions(getHealthConditions() - 10);
                    getAlertWindow().showAndWait();
                    break;
            }
        }

        // Game over condition
        if (getGang().size() <= 0){

            setMoving(false);

            AlertBox.gameOver();
        }
    }
}
