import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LootMenuController extends Main {

    @FXML private Label moneyLbl;
    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;

    private int moneyLOOT;
    private int foodLOOT;
    private int waterLOOT;
    private int ammoLOOT;

    @FXML
    private void initialize(){

        moneyLOOT = (int) (Math.random() * 1500) + 50;
        foodLOOT = (int) (Math.random() * 500) + 50;
        waterLOOT = (int) (Math.random() * 500) + 50;
        ammoLOOT = (int) (Math.random() * 100) + 5;

        moneyLbl.setText("Money: $"+ moneyLOOT);
        foodLbl.setText("Food: "+foodLOOT);
        waterLbl.setText("Water: "+waterLOOT);
        ammoLbl.setText("Ammo: "+ammoLOOT);
    }

    @FXML
    private void setTakeBtn(){

        Money += moneyLOOT;
        Food += foodLOOT;
        Water += waterLOOT;
        Ammo += ammoLOOT;

        AlertWindow.close();
    }
}
