import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jetbrains.annotations.Contract;

import java.io.File;
import java.io.IOException;

public class ShootOutController extends Main {

    private Media victorySound = new Media(new File("C:\\Users\\Hunter\\IdeaProjects\\The Trail JAVAFX VER\\src\\main\\resources\\Ta Da-SoundBible.com-1884170640.wav").toURI().toString());
    private Media gunSound = new Media(new File("C:\\Users\\Hunter\\IdeaProjects\\The Trail JAVAFX VER\\src\\main\\resources\\Laser_Machine_Gun-Mike_Koenig-1194129298.wav").toURI().toString());
    private Media grenadeSound = new Media(new File("C:\\Users\\Hunter\\IdeaProjects\\The Trail JAVAFX VER\\src\\main\\resources\\Molotov Cocktail Bomb-SoundBible.com-547160811.wav").toURI().toString());

    private int thiefHealth = 100;

    public Button shootBtn;
    public Button letGoBtn;
    public Button grenadeBtn;
    public TextArea eventText;
    public Label thiefHealthLbl;
    public Label ammoLbl;
    public Label grenadeLbl;
    public Label gangAmountLbl;

    @FXML private void initialize(){

        ammoLbl.setText("Ammo: "+Ammo);
        grenadeLbl.setText("Grenades: "+Grenades);
        thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
        gangAmountLbl.setText("Gang members: "+gang.size());

        thiefHealth = 100;

        memberSelect = gang.size();
        memberSelect -= 1;
    }

    @FXML private void setShootBtn(){

        if (Ammo >= 1){

            playGunSound();

            int damageDealt = (int) (Math.random() * baseAttackDamage) + 10;

            eventText.appendText("you shot at them ("+ damageDealt +" DMG) \n");
            thiefHealth -= damageDealt;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            --Ammo;
            ammoLbl.setText("Ammo: "+Ammo);

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

        if (Grenades >= 1){

            playGrenadeSound();

            eventText.appendText("you threw a grenade ("+GrenadeDMG+" DMG) \n");
            thiefHealth -= GrenadeDMG;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            --Grenades;
            grenadeLbl.setText("Grenades: "+Grenades);

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

            eventText.appendText("They shot and killed "+gang.getLast() + "\n");
            gang.remove(memberSelect);
            gangAmountLbl.setText("Gang members: "+gang.size());

            if (gang.size() <= 0){

                AlertBox.gameOver();
            }
        }else if (rand.nextBoolean()){

            eventText.appendText("They shot "+gang.getLast() + "\n");
            HealthConditions += 50;
        }else {

            eventText.appendText("They shot and missed \n");
        }
    }

    @Contract(pure = true)
    private boolean isThiefDead(){

        return thiefHealth <= 0;
    }

    private void deadThief(){

        playVictorySound();

        try {
            deadThiefPane = FXMLLoader.load(Main.class.getResource("ThiefKilled.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        deadThiefScene = new Scene(deadThiefPane);

        AlertWindow.setScene(deadThiefScene);

        Score += 5000;
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
