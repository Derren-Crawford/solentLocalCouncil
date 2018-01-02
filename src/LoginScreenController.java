import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController {

    /*Attributes*/
    @FXML private TextField userNameBox;
    @FXML private TextField passwordBox;

    @FXML private Label errorMessage;

    @FXML private void initialize(){

        errorMessage.setVisible(false);
    }


    @FXML
    public void loginButtonAction(ActionEvent actionEvent) throws IOException
    {
        String userName = userNameBox.getText();
        String password = passwordBox.getText();

        for(SystemUser employee : Main.BootData.getCouncil().getSystemUsers()){

            if(employee.getUserName().equals(userName) && employee.getPassword().equals(password)){

                Main.BootData.setAccessLevel(employee.getAccessLevel());
                Main.BootData.setUser(employee);

                Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                Scene developmentMenuScene = new Scene(developmentMenuParent);
                Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(developmentMenuScene);
                app_stage.show();
            }

        }

        errorMessage.setVisible(true);


    }


}
