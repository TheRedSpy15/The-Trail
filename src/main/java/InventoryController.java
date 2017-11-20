import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InventoryController extends MidGameMenu {

    public Label foodLbl;
    public Label waterLbl;
    public Label ammoLbl;
    public Label moneyLbl;
    public ImageView carImage;
    public Label scoreLbl;
    public Label gunModelLbl;
    public Label damageLbl;
    public Label grenadeLbl;
    public ImageView gunImage;

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
