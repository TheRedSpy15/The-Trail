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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;
import java.util.Collections;

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
    @FXML private Label grenadeLbl, gangAmountLbl, ammoLbl, thiefHealthLbl;
    @FXML private Button shootBtn, grenadeBtn, letGoBtn;

    @FXML private void initialize(){

        ammoLbl.setText("Ammo: "+ Main.gang.getAmmo());
        grenadeLbl.setText("Grenades: "+ Main.gang.getGrenades());
        thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
        gangAmountLbl.setText("Gang members: "+ Main.gang.getGangMembers().size());

        resetShootOut();
    }

    @FXML private void shoot(){

        if (Main.gang.getAmmo() >= 1){ // if ammo

            playGunSound();

            if (Main.rand.nextBoolean()){ // hit

                int damageDealt = (int) (Math.random() * Main.gang.getBaseAttackDamage()) + 10;

                eventText.appendText("you shot at them ("+ damageDealt +" DMG) \n");
                thiefHealth -= damageDealt;
                thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
                Main.gang.setAmmo(Main.gang.getAmmo() - 1);
                ammoLbl.setText("Ammo: "+ Main.gang.getAmmo());

                if (isThiefDead()){

                    deadThief();
                }else {

                    thiefAttack();
                }
            }else { // miss

                eventText.appendText("You missed!!! \n");

                thiefAttack();
            }

        }else { // no ammo

            eventText.appendText("NO AMMO!!! \n");

            thiefAttack();
        }
    }

    @FXML private void grenade(){

        int GrenadeDMG = (int) (Math.random() * 75) + 35;

        if (Main.gang.getGrenades() >= 1){ // grenade

            playGrenadeSound();

            eventText.appendText("you threw a grenade ("+GrenadeDMG+" DMG) \n");
            thiefHealth -= GrenadeDMG;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            Main.gang.setGrenades(Main.gang.getGrenades() - 1);
            grenadeLbl.setText("Grenades: "+ Main.gang.getGrenades());

            if (isThiefDead()){

                deadThief();
            }else {

                thiefAttack();
            }

        }else { // no grenade

            eventText.appendText("NO GRENADES!!! \n");

            thiefAttack();
        }
    }

    @FXML private void letGo(){ // let go

        Main.alert.alert("You let them go");
    }

    private void thiefAttack(){

        Collections.shuffle(Main.gang.getGangMembers());

        if (Main.Chance((byte)3,(byte)1,(byte)2)){ // shot and killed

            eventText.appendText("They shot and killed "+ HealthClass.death() + "\n");
            gangAmountLbl.setText("Gang members: "+ Main.gang.getGangMembers().size());

            if (Main.gang.getGangMembers().size() <= 0){

                AlertBox.gameOver();
            }
        }else if (Main.gang.getBodyArmor() >= 1){ // hit body armor

            eventText.appendText("They shot, luckily hit body armor \n");

            Main.gang.setBodyArmor((byte) (Main.gang.getBodyArmor() - 1));
        }else if (Main.rand.nextBoolean()){ // shot

            eventText.appendText("They shot "+ Main.gang.getGangMembers().peek() + "\n");

            Main.gang.setHealthConditions(Main.gang.getHealthConditions() + 50);
            Main.main.checkValues();
        }else { // missed

            eventText.appendText("They shot and missed \n");
        }
    }

    private void resetShootOut(){

        enableButtons();

        thiefHealth = (byte) 100;
    }

    private boolean isThiefDead(){

        return thiefHealth <= 0;
    }

    private void deadThief(){

        short bonusPoints = 5000;

        Main.gang.setScore(Main.gang.getScore() + bonusPoints);

        disableButtons();

        playVictorySound();

        try {
            Main.main.setDeadThiefPane(FXMLLoader.load(Main.class.getResource("ThiefKilled.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setDeadThiefScene(new Scene(Main.main.getDeadThiefPane()));

        Main.getAlertWindow().setScene(Main.main.getDeadThiefScene());
    }

    private void playGunSound(){

        float volume = 0.3f;

        MediaPlayer playSoundGun = new MediaPlayer(gunSound);

        playSoundGun.setVolume(volume);

        playSoundGun.play();
    }

    private void playGrenadeSound(){

        float volume = 0.3f;

        MediaPlayer playSoundGrenade = new MediaPlayer(grenadeSound);

        playSoundGrenade.setVolume(volume);

        playSoundGrenade.play();
    }

    private void playVictorySound(){

        float volume = 0.3f;

        MediaPlayer playSoundVictory = new MediaPlayer(victorySound);

        playSoundVictory.setVolume(volume);

        playSoundVictory.play();
    }

    private void disableButtons(){

        shootBtn.setDisable(true);
        grenadeBtn.setDisable(true);
        letGoBtn.setDisable(true);
    }

    private void enableButtons(){

        shootBtn.setDisable(false);
        grenadeBtn.setDisable(false);
        letGoBtn.setDisable(false);
    }
}
