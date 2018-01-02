import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddHouseholdController {

    /*Attributes*/

    @FXML private TextField nameBox;

    private Boolean modifyChecker;
    private String name;
    private Property parentProperty;

    /*Initializer*/

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;

        if(ref > 0)
        {
            modifyChecker = true;
            Household household = householdFinder(Main.BootData.getRefNumber());
            name = household.getName();

            nameBox.setPromptText(name);

        }

    }

    private Household householdFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getHousehold().getReference() == ref){
                        return property.getHousehold();
                    }
                }
            }
        }
        return null;
    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {


        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getRef() == Main.BootData.getParentRef())
                    {
                        parentProperty = property;
                    }
                }
            }
        }

        if(!modifyChecker)
        {
            Household household = parentProperty.addHousehold(nameGetter());
            int ref = household.getReference();

            Main.BootData.setRefNumber(ref);
        }
        else
        {
            Household household = householdFinder(Main.BootData.getRefNumber());

            household.setName(nameGetter());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewHousehold.fxml"));
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
