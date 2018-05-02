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

    short foodStock;
    short waterStock;

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

            Main.alert.amountOver(amountOver);
        }

        updateLabels();
    }

    @FXML
    private void leave(){

        if (!Main.gang.isPassedSetup()) {
            Travel.travelSceneSetup();
            Main.main.getMainWindow().setScene(new Scene(Main.main.getTravelPane()));
            Main.gang.setPassedSetup(true);
        } else Main.main.getMainWindow().setScene(Main.main.getCityScene());
    }


    @FXML
    private void gunStoreBtn(){

        updateStores();

        Main.main.getMainWindow().setScene(Main.main.getGunStoreScene());
    }

    @FXML
    public void initialize(){

        final short storageMAX = 3_000;
        final short storageMIN = 1_500;

        // random store inventory
        waterStock = (short) ((Math.random() * storageMAX) + storageMIN);
        foodStock = (short) ((Math.random() * storageMAX) + storageMIN);

        updateSliders();
        updateLabels();
    }

    private void updateLabels(){

        moneyLbl.setText("Money: $"+(int) Main.gang.getMoney());
        currentFood.setText("FOOD: "+ Main.gang.getFood());
        currentWater.setText("WATER: "+ Main.gang.getWater());
    }

    private void updateSliders(){

        foodSlider.setMax(foodStock);
        waterSlider.setMax(waterStock);
    }
}
