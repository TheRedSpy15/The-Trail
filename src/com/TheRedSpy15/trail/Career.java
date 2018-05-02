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

import java.io.IOException;

public class Career {

    private static int payDayCountdown = 0;

    protected void careerPicker() throws IOException {

        // Career scene
        // assigns career scene fxml file to career anchor object
        Main.main.setCareerAnchor(FXMLLoader.load(getClass().getResource("CareerScene.fxml")));
    }

    /**
     *
     * Does an +1 increment to payment countdown,
     * and player(s) receive
     * money when it reaches 30 (30 days),
     * of which is determine by the wage of the
     * career the player(s) have chosen
     *
     */
    static void payDay(){

        final byte countdownDuration = 30;

        ++payDayCountdown;

        if (payDayCountdown == countdownDuration){

            Main.gang.setMoney(Main.gang.getMoney() + Main.gang.getWage());

            payDayCountdown = 0;
        }
    }
}
