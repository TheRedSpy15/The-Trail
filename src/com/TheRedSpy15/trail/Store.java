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
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;
import java.net.URL;

public class Store {

    short amountOver;

    protected void playPurchaseSound(){
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

    /**
     * Multiplies price and quantity by 2, then doubles
     * that to get bonus points.
     *
     * subtracting appropiate amount of money is done
     * by this method.
     *
     * HOWEVER: it assumes that the user has enough
     * money
     *
     * @param price value of item
     * @param quantity amount of item to purchase
     * @param Item name of item to be used to notify user
     */
    void purchaseItem(short price, short quantity, String Item){

        int bonusPoints = ((price * quantity) * 2);

        Main.gang.setScore(Main.gang.getScore() + bonusPoints);

        Main.gang.setMoney(Main.gang.getMoney() - (price * quantity));

        playPurchaseSound();

        Main.alert.specialPurchase(Item);
    }

    /**
     * Loads/Reloads all store scenes,
     * such as: store, gun store, dealership,
     * and sell
     */
    protected void updateStores() {

        // Store
        try {
            Main.main.setStorePane(FXMLLoader.load(Main.class.getResource("StoreScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setStoreScene(new Scene(Main.main.getStorePane()));

        // Sell
        try {
            Main.main.setSellPane(FXMLLoader.load(Main.class.getResource("SellScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setSellScene(new Scene(Main.main.getSellPane()));

        // Dealer ship pane
        try {
            Main.main.setDealerPane(FXMLLoader.load(Main.class.getResource("DealerShip.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setDealerScene(new Scene(Main.main.getDealerPane()));

        // Gun store
        try {
            Main.main.setGunStorePane(FXMLLoader.load(Main.class.getResource("GunStore.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setGunStoreScene(new Scene(Main.main.getGunStorePane()));
    }
}
