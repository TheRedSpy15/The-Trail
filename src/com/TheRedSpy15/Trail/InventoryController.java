package com.TheRedSpy15.trail;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

        foodLbl.setText("Food: "+ Gang.getFood());
        armorLbl.setText("Armored Vests: "+ Gang.getBodyArmor());
        waterLbl.setText("Water: "+ Gang.getWater());
        ammoLbl.setText("Ammo: "+ Gang.getAmmo());
        moneyLbl.setText("Money: "+(int) Gang.getMoney());
        carImage.setImage(new Image(Gang.getCarSpriteURL()));
        scoreLbl.setText("Score: "+ Gang.getScore());
        gunModelLbl.setText("Model: "+ Gang.getGunID());
        damageLbl.setText("Damage: "+ Gang.getBaseAttackDamage());
        gunImage.setImage(new Image(Gang.getGunSpriteURL()));
        grenadeLbl.setText("Grenades: "+ Gang.getGrenades());
    }

    @FXML private void backBtn(){

        getMenuWindow().setScene(menuScene);
    }
}
