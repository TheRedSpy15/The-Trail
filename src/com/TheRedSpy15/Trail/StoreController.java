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

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import static com.TheRedSpy15.trail.Gang.*;
import static com.TheRedSpy15.trail.Main.*;

public class StoreController extends Store {

    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Label moneyLabel;
    @FXML private Label currentWater;
    @FXML private Label currentFood;

    @FXML
    private void purchase(){

        double cartValue;

        cartValue = (waterSlider.getValue() * 0.25) + (foodSlider.getValue() * 0.50);

        if (getMoney() >= cartValue){

            setMoney(getMoney() - cartValue);

            setFood(getFood() + (int) foodSlider.getValue());

            setWater(getWater() + (int) waterSlider.getValue());

            TravelClass.travelSetup();

            // determines if the scene is being used in alert window or main window and changes depending on that
            if (getMainWindow().getScene().equals(getStoreScene())){

                // setting window scene to travel pane
                getMainWindow().setScene(new Scene(getTravelPane()));
            }else{

                // Changing alert window scene to settlement scene
                getAlertWindow().setScene(getCityScene());
            }

        // show not enough money scene in alert window with the amount over
        }else{

            amountOver = (short) (getMoney() - cartValue);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML
    private void gunStoreBtn(){

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(getAlertWindow().isShowing())){

            // setting window scene to travel pane
            getMainWindow().setScene(getGunStoreScene());
        }else{

            // Changing alert window scene to settlement scene
            getAlertWindow().setScene(getGunStoreScene());
        }
    }

    // code run on initialization of scene
    @FXML
    public void initialize(){

        // On starting class, update labels and max value of sliders
        moneyLabel.setText("Money: $"+(int) getMoney());
        currentFood.setText("FOOD: "+ getFood());
        currentWater.setText("WATER: "+ getWater());

        // random store inventory
        waterSlider.setMax((int) (Math.random() * 1500) + 1000);
        foodSlider.setMax((int) (Math.random() * 1500) + 1000);
    }
}
