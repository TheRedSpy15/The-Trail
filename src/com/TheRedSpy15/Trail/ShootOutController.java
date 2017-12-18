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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;

import static com.TheRedSpy15.trail.Gang.*;
import static com.TheRedSpy15.trail.Main.*;

public class ShootOutController extends ThiefMenuController {

    private Media victorySound = new Media(ClassLoader.getSystemResource(
            "com/TheRedSpy15/trail/Ta Da-SoundBible.com-1884170640.wav"
    ).toExternalForm());

    private Media gunSound = new Media(ClassLoader.getSystemResource(
            "com/TheRedSpy15/trail/Laser_Machine_Gun-Mike_Koenig-1194129298.wav"
    ).toExternalForm());

    private Media grenadeSound = new Media(ClassLoader.getSystemResource(
            "com/TheRedSpy15/trail/Molotov Cocktail Bomb-SoundBible.com-547160811.wav"
    ).toExternalForm());

    private int thiefHealth = 100;

    @FXML private TextArea eventText;
    @FXML private Label thiefHealthLbl;
    @FXML private Label ammoLbl;
    @FXML private Label grenadeLbl;
    @FXML private Label gangAmountLbl;

    @FXML private void initialize(){

        ammoLbl.setText("Ammo: "+ getAmmo());
        grenadeLbl.setText("Grenades: "+ getGrenades());
        thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
        gangAmountLbl.setText("Gang members: "+ getGangMembers().size());

        thiefHealth = 100;

        setMemberSelect(getGangMembers().size());
        setMemberSelect(getMemberSelect() - 1);
    }

    @FXML private void setShootBtn(){

        if (getAmmo() >= 1){

            playGunSound();

            int damageDealt = (int) (Math.random() * getBaseAttackDamage()) + 10;

            eventText.appendText("you shot at them ("+ damageDealt +" DMG) \n");
            thiefHealth -= damageDealt;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            setAmmo(getAmmo() - 1);
            ammoLbl.setText("Ammo: "+ getAmmo());

            if (isThiefDead()){

                deadThief();
            }else {

                thiefAttack();
            }

        }else {

            eventText.appendText("NO AMMO!!! \n");
        }
    }

    @FXML private void setGrenadeBtn(){

        int GrenadeDMG = (int) (Math.random() * 75) + 35;

        if (getGrenades() >= 1){

            playGrenadeSound();

            eventText.appendText("you threw a grenade ("+GrenadeDMG+" DMG) \n");
            thiefHealth -= GrenadeDMG;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            setGrenades(getGrenades() - 1);
            grenadeLbl.setText("Grenades: "+ getGrenades());

            if (isThiefDead()){

                deadThief();
            }else {

                thiefAttack();
            }

        }else {

            eventText.appendText("NO GRENADES!!! \n");
        }
    }

    @FXML private void setLetGoBtn(){

        alert.alert("You let them go");
    }

    private void thiefAttack(){

        if (rand.nextBoolean()){

            eventText.appendText("They shot and killed "+ getGangMembers().getLast() + "\n");
            getGangMembers().remove(getMemberSelect());
            gangAmountLbl.setText("Gang members: "+ getGangMembers().size());

            if (getGangMembers().size() <= 0){

                AlertBox.gameOver();
            }
        }else if (rand.nextBoolean()){

            eventText.appendText("They shot "+ getGangMembers().getLast() + "\n");
            setHealthConditions(getHealthConditions() + 50);
        }else {

            eventText.appendText("They shot and missed \n");
        }
    }

    private boolean isThiefDead(){

        return thiefHealth <= 0;
    }

    private void deadThief(){

        playVictorySound();

        try {
            setDeadThiefPane(FXMLLoader.load(Main.class.getResource("ThiefKilled.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDeadThiefScene(new Scene(getDeadThiefPane()));

        getAlertWindow().setScene(getDeadThiefScene());

        setScore(getScore() + 5000);
    }

    private void playGunSound(){

        MediaPlayer playSoundGun = new MediaPlayer(gunSound);

        playSoundGun.setVolume(0.3f);

        playSoundGun.play();
    }

    private void playGrenadeSound(){

        MediaPlayer playSoundGrenade = new MediaPlayer(grenadeSound);

        playSoundGrenade.setVolume(0.3f);

        playSoundGrenade.play();
    }

    private void playVictorySound(){

        MediaPlayer playSoundVictory = new MediaPlayer(victorySound);

        playSoundVictory.setVolume(0.3f);

        playSoundVictory.play();
    }
}
