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
import java.net.URL;

public class Store {

    short amountOver;

    void playPurchaseSound(){
        URL url = ClassLoader.getSystemResource(
                "com/TheRedSpy15/Trail/Cha_Ching_Register-Muska666-173262285.wav"
        );

        if(url != null)
        {
            Media purchaseSound = new Media(url.toExternalForm());

            MediaPlayer playPurchaseSound = new MediaPlayer(purchaseSound);

            playPurchaseSound.setVolume(0.3f);
            playPurchaseSound.play();
        }
    }


    void purchaseItem(short price, short quantity, String Item){

        short bonusPoints = price *= quantity;

        Gang.setScore(Gang.getScore() + bonusPoints);

        Gang.setMoney(Gang.getMoney() - (price * quantity));

        playPurchaseSound();

        Main.alert.specialPurchase(Item);
    }

    void updateStores() {

        // Store
        try {
            Main.setStorePane(FXMLLoader.load(Main.class.getResource("StoreScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setStoreScene(new Scene(Main.getStorePane()));

        // Sell
        try {
            Main.setSellPane(FXMLLoader.load(Main.class.getResource("SellScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setSellScene(new Scene(Main.getSellPane()));

        // Dealer ship pane
        try {
            Main.setDealerPane(FXMLLoader.load(Main.class.getResource("DealerShip.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setDealerScene(new Scene(Main.getDealerPane()));

        // Gun store
        try {
            Main.setGunStorePane(FXMLLoader.load(Main.class.getResource("GunStore.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setGunStoreScene(new Scene(Main.getGunStorePane()));
    }
}
