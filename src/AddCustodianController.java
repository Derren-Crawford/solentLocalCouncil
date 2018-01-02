import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCustodianController {

    /*Attributes*/
    @FXML private TextField firstNameBox;
    @FXML private TextField lastNameBox;
    @FXML private TextField telNoBox;
    @FXML private TextField emailBox;

    private Boolean modifyChecker;
    private Boolean ownerOrCustodian;
    private String firstName;
    private String lastName;
    private int telNo;
    private String email;

    private Custodian custodian;

    /*Initializer*/

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;
        ownerOrCustodian = Main.BootData.getOwnerOrCustodian();

        if(ref > 0)
        {
            modifyChecker = true;
            custodian = custodianFinder(Main.BootData.getParentRef());
            firstName = custodian.getFirstName();
            lastName = custodian.getLastName();
            telNo = custodian.getTelNo();
            email = custodian.getEmail();

            firstNameBox.setPromptText(firstName);
            lastNameBox.setPromptText(lastName);
            telNoBox.setPromptText(Integer.toString(telNo));
            emailBox.setPromptText(email);
        }

    }

    private Custodian custodianFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getRef() == ref){
                        return property.getOwner();
                    }
                    else if(property.getHousehold().getReference() == ref){

                        return property.getHousehold().getCustodian();
                    }
                }
            }
        }
        return null;
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
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {

        if(!modifyChecker)
        {
            if(ownerOrCustodian){
                Property parentProperty = propertyFinder(Main.BootData.getParentRef());
                parentProperty.addOwner(firstNameGetter(), lastNameGetter(), telNoGetter(), emailGetter());


            }else if(!ownerOrCustodian) {
                Household parentHousehold = householdFinder(Main.BootData.getParentRef());
                parentHousehold.addCustodian(firstNameGetter(), lastNameGetter(), telNoGetter(), emailGetter());
            }

        }
        else
        {

            custodian.setFirstName(firstNameGetter());
            custodian.setLastName(lastNameGetter());
            custodian.setTelNo(telNoGetter());
            custodian.setEmail(emailGetter());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewCustodian.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    private Property propertyFinder(int ref){
        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getRef() == ref)
                    {
                        return property;
                    }
                }


            }
        }
        return null;
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

    private String firstNameGetter()
    {
        if(firstNameBox.getText().isEmpty())
        {
            return firstNameBox.getPromptText();
        }
        else
        {
            return firstNameBox.getText();
        }

    }

    private String lastNameGetter()
    {
        if(lastNameBox.getText().isEmpty())
        {
            return lastNameBox.getPromptText();
        }
        else
        {
            return lastNameBox.getText();
        }

    }

    private int telNoGetter(){

        if(telNoBox.getText().isEmpty())
        {
            return Integer.parseInt(telNoBox.getPromptText());
        }
        else{
            return Integer.parseInt(telNoBox.getText());
        }
    }

    private String emailGetter()
    {
        if(emailBox.getText().isEmpty())
        {
            return emailBox.getPromptText();
        }
        else
        {
            return emailBox.getText();
        }

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
