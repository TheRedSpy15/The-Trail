import javafx.fxml.FXML;

public class MidMenuController extends MidGameMenu {

    @FXML
    private void paceBtn(){

        paceSetterMethod();
        MenuWindow.setScene(getPaceScene());
    }

    @FXML
    private void inventoryBtn(){

        inventoryMethod();
        MenuWindow.setScene(inventoryScene);
    }

    @FXML
    private void dietBtn(){

        foodPortionSet();
        MenuWindow.setScene(getFoodPortionsScene());
    }
}