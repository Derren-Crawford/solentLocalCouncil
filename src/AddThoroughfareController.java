import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddThoroughfareController {

    /*Attributes*/

    @FXML private TextField nameBox;
    @FXML private ChoiceBox typeChoice;

    private Boolean modifyChecker;
    private String name;
    private String type;

    /*Initializer*/

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;
        typeChoice.setItems(FXCollections.observableArrayList("Street", "Cul de sac", "Crescent", "Close", "Lane", "Drive"));


        if(ref > 0)
        {
            modifyChecker = true;
            Thoroughfare thoroughfare = thoroughfareFinder(Main.BootData.getRefNumber());
            name = thoroughfare.getName();
            type = thoroughfare.getType();

            nameBox.setPromptText(name);
            typeChoice.setValue(type);
        }

    }

    private Thoroughfare thoroughfareFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                if(thoroughfare.getRef() == ref)
                {
                    return thoroughfare;
                }
            }
        }
        return null;
    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {

        String choice = (String) typeChoice.getValue();
        Council council = Main.BootData.getCouncil();

        if(!modifyChecker)
        {
            Development development = council.getDevelopment(Main.BootData.getParentRef());
            Thoroughfare thoroughfare = development.addThoroughfare(nameGetter(), choice);
            int ref = thoroughfare.getRef();

            Main.BootData.setRefNumber(ref);
        }
        else
        {
            Thoroughfare thoroughfare = thoroughfareFinder(Main.BootData.getRefNumber());

            thoroughfare.setName(nameGetter());
            thoroughfare.setType(choice);

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewThoroughfare.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    private String nameGetter()
    {
        if(nameBox.getText().isEmpty())
        {
            return nameBox.getPromptText();
        }
        else
        {
            return nameBox.getText();
        }

    }

    @FXML
    public void homeButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setRefNumber(0);
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void logOutButtonAction(ActionEvent actionEvent) throws IOException
    {

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }
}
