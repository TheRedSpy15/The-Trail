import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HireMenuController extends Main {

    public TextField nameField;
    public Button hireBtn;
    public Label emptyLbl;
    public Button backBtn;

    @FXML private void setHireBtn(){

        if (Money < 5000){

            int amountOver = (int) (Money - 5000);

            alert.notEnoughMoney(amountOver); // Back button support in alert box class needs added
        }else if (nameField.getText().trim().equals("")){

            emptyLbl.setText("Text field CANNOT be empty!");
        }else {

            gang.add(nameField.getText());
            AlertWindow.setScene(cityScene);
        }
    }

    @FXML private void setBackBtn(){

        AlertWindow.setScene(cityScene);
    }

    @FXML private void initialize(){

        emptyLbl.setText("");
        nameField.setText("");
    }
}
