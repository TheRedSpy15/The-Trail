package com.TheRedSpy15.trail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.TheRedSpy15.trail.Gang.*;

public class InventoryController extends MidGameMenu {

    @FXML private Label armorLbl;
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

        foodLbl.setText("Food: "+ getFood());
        armorLbl.setText("Armored Vests: "+getBodyArmor());
        waterLbl.setText("Water: "+ getWater());
        ammoLbl.setText("Ammo: "+ getAmmo());
        moneyLbl.setText("Money: "+(int) getMoney());
        carImage.setImage(new Image(getCarSpriteURL()));
        scoreLbl.setText("Score: "+ getScore());
        gunModelLbl.setText("Model: "+ getGunID());
        damageLbl.setText("Damage: "+ getBaseAttackDamage());
        gunImage.setImage(new Image(getGunSpriteURL()));
        grenadeLbl.setText("Grenades: "+ getGrenades());
    }

    @FXML private void backBtn(){

        getMenuWindow().setScene(menuScene);
    }
}
