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

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class StoreController extends Store {

    @FXML private Label currentWater, currentFood, moneyLbl;
    @FXML private JFXSlider waterSlider, foodSlider;

    @FXML
    private void purchase(){

        double cartValue;

        final double waterPrice = 0.25;
        final double foodPrice = 0.50;
        cartValue = (waterSlider.getValue() * waterPrice) + (foodSlider.getValue() * foodPrice);

        if (Main.gang.getMoney() >= cartValue){

            Main.gang.setMoney(Main.gang.getMoney() - cartValue);

            Main.gang.setFood(Main.gang.getFood() + (int) foodSlider.getValue());
            Main.gang.setWater(Main.gang.getWater() + (int) waterSlider.getValue());

            updateSliders();

            playPurchaseSound();
        }else{ // show not enough money

            amountOver = (short) (Main.gang.getMoney() - cartValue);

            Main.alert.notEnoughMoney(amountOver);
        }

        updateGUI();
    }

    @FXML
    private void leave(){

        TravelClass.travelSetup();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (Main.main.getMainWindow().getScene().equals(Main.main.getStoreScene())){

            // setting window scene to travel pane
            Main.main.getMainWindow().setScene(new Scene(Main.main.getTravelPane()));
        }else{

            // Changing alert window scene to settlement scene
            Main.getAlertWindow().setScene(Main.main.getCityScene());
        }
    }


    @FXML
    private void gunStoreBtn(){

        updateStores();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(Main.getAlertWindow().isShowing())){

            // setting window scene to travel pane
            Main.main.getMainWindow().setScene(Main.main.getGunStoreScene());
        }else{

            // Changing alert window scene to settlement scene
            Main.getAlertWindow().setScene(Main.main.getGunStoreScene());
        }
    }

    // code run on initialization of scene
    @FXML
    public void initialize(){

        final short storageMAX  = 1500;
        final short storageMIN = 1000;

        // On starting class, update labels and max value of sliders
        moneyLbl.setText("Money: $"+(int) Main.gang.getMoney());
        currentFood.setText("FOOD: "+ Main.gang.getFood());
        currentWater.setText("WATER: "+ Main.gang.getWater());

        // random store inventory
        waterSlider.setMax((int) (Math.random() * storageMAX) + storageMIN);
        foodSlider.setMax((int) (Math.random() * storageMAX) + storageMIN);
    }

    private void updateGUI(){

        moneyLbl.setText("Money: $"+(int) Main.gang.getMoney());
        currentFood.setText("FOOD: "+ Main.gang.getFood());
        currentWater.setText("WATER: "+ Main.gang.getWater());
    }

    private void updateSliders(){

        foodSlider.setMax(foodSlider.getMax() - foodSlider.getValue());
        waterSlider.setMax(waterSlider.getMax() - waterSlider.getValue());
    }
}
