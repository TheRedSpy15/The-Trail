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

public class Store extends Main {

    private Media purchaseSound = new Media(ClassLoader.getSystemResource(
            "com/TheRedSpy15/Trail/Cha_Ching_Register-Muska666-173262285.wav"
    ).toExternalForm());

    void playPurchaseSound(){

        MediaPlayer playPurchaseSound = new MediaPlayer(purchaseSound);

        playPurchaseSound.setVolume(0.3f);
        playPurchaseSound.play();
    }

    void storeMethod() {

        // Store
        try {
            setStorePane(FXMLLoader.load(Main.class.getResource("storeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setStoreScene(new Scene(getStorePane()));

        /* Store for Alert box stage
        try {
            setMidStorePane(FXMLLoader.load(Main.class.getResource("storeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        // Sell scene for Alert box stage
        try {
            midSellStorePane = FXMLLoader.load(Main.class.getResource("SellScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Gun store
        try {
            setGunStorePane(FXMLLoader.load(Main.class.getResource("GunStore.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setGunStoreScene(new Scene(getGunStorePane()));
    }
}
