import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ShootOutController extends Main {

    private int thiefHealth = 100;

    public Button shootBtn;
    public Button letGoBtn;
    public Button grenadeBtn;
    public TextArea eventText;
    public Label thiefHealthLbl;
    public Label ammoLbl;
    public Label grenadeLbl;

    @FXML private void initialize(){

        ammoLbl.setText("Ammo: "+Ammo);
        grenadeLbl.setText("Grenades: "+Grenades);
        thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);

        thiefHealth = 100;

        PlayerSelectForEvent = gang.size();
        PlayerSelectForEvent-=1;
    }

    @FXML private void setShootBtn(){

        if (Ammo >= 1){

            eventText.appendText("you shot at them ("+Attack+" DMG) \n");
            thiefHealth -= Attack;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
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

            eventText.appendText("you threw a grenade ("+GrenadeDMG+" DMG) \n");
            thiefHealth -= GrenadeDMG;
            thiefHealthLbl.setText("THIEF HEALTH: "+thiefHealth);
            ammoLbl.setText("Ammo: "+Ammo);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
            gang.remove(PlayerSelectForEvent);

            if (gang.size() <= 0){

                IsMoving = false;

                AlertBox.gameOver();
            }
        }else if (rand.nextBoolean()){

            eventText.appendText("They shot "+gang.getLast() + "\n");
            HealthConditions += 50;
        }else {

            eventText.appendText("They shot and missed \n");

            if (rand.nextBoolean()) alert.alert("The thief ran away");
        }
    }

    private boolean isThiefDead(){

        return thiefHealth <= 0;
    }

    private void deadThief(){

        alert.alert("You have killed the thief");
    }
}
