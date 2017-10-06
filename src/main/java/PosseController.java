import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class PosseController extends Main {
    
    @FXML  private TextField membername1;
    @FXML  private TextField membername2;
    @FXML  private TextField membername3;
    @FXML  private TextField membername4;
    @FXML  private TextField membername5;
    @FXML  private TextField membername6;

    @FXML
    private void confirmNamesMethod(){

        // If statement of any of the text fields not being empty
        if (!(membername1.getText().trim().equals("") || membername2.getText().trim().equals("") || membername3.getText().trim().equals("") || membername4.getText().trim().equals("") || membername5.getText().trim().equals("") || membername6.getText().trim().equals(""))){

            // Runs store method
            store.storeMethod();

            // Sets the scene to store pane
            MainWindow.setScene(new Scene(storePane));

            // adds the value of the text fields to the linked list
            PosseLinkedList.add(membername1.getText());
            PosseLinkedList.add(membername2.getText());
            PosseLinkedList.add(membername3.getText());
            PosseLinkedList.add(membername4.getText());
            PosseLinkedList.add(membername5.getText());
            PosseLinkedList.add(membername6.getText());
        }
        else {

            // when the if statement is false, run the empty name alert
            alert.emptyNames();
        }
    }
}
