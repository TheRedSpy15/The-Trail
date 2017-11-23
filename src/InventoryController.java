import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InventoryController extends MidGameMenu {

    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;
    @FXML private Label moneyLbl;
    @FXML private ImageView carImage;
    @FXML private Label scoreLbl;
    @FXML private Label gunModelLbl;
    @FXML private Label damageLbl;
    @FXML private Label grenadeLbl;
    @FXML private ImageView gunImage;

    @FXML private void initialize(){

        foodLbl.setText("Food: "+Food);
        waterLbl.setText("Water: "+Water);
        ammoLbl.setText("Ammo: "+Ammo);
        moneyLbl.setText("Money: "+(int)Money);
        carImage.setImage(new Image(carSpriteURL));
        scoreLbl.setText("Score: "+Score);
        gunModelLbl.setText("Model: "+gunID);
        damageLbl.setText("Damage: "+ baseAttackDamage);
        gunImage.setImage(new Image(gunSpriteURL));
        grenadeLbl.setText("Grenades: "+Grenades);
    }

    @FXML private void backBtn(){

        MenuWindow.setScene(menuScene);
    }
}
