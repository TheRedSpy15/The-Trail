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

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;

import static com.TheRedSpy15.trail.Main.*;
import static com.TheRedSpy15.trail.Gang.*;

public class Store {

    short amountOver;

    private Media purchaseSound = new Media(ClassLoader.getSystemResource(
            "com/TheRedSpy15/Trail/Cha_Ching_Register-Muska666-173262285.wav"
    ).toExternalForm());

    void playPurchaseSound(){

        MediaPlayer playPurchaseSound = new MediaPlayer(purchaseSound);

        playPurchaseSound.setVolume(0.3f);
        playPurchaseSound.play();
    }

    void purchaseItem(short price, short quantity, String Item){

        short bonusPoints = price *= quantity;

        setScore(getScore() + bonusPoints);

        setMoney(getMoney() - (price * quantity));

        playPurchaseSound();

        alert.specialPurchase(Item);
    }

    void updateStores() {

        // Store
        try {
            setStorePane(FXMLLoader.load(Main.class.getResource("StoreScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStoreScene(new Scene(getStorePane()));

        // Sell
        try {
            setSellPane(FXMLLoader.load(Main.class.getResource("SellScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSellScene(new Scene(getSellPane()));

        // Dealer ship pane
        try {
            setDealerPane(FXMLLoader.load(Main.class.getResource("DealerShip.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDealerScene(new Scene(getDealerPane()));

        // Gun store
        try {
            setGunStorePane(FXMLLoader.load(Main.class.getResource("GunStore.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setGunStoreScene(new Scene(getGunStorePane()));
    }
}
