import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHouseholdController {

    /*Attributes*/

    @FXML Label referenceOutput;
    @FXML Label nameOutput;
    @FXML Label warningMessage;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button deleteHousehold;
    @FXML Button custodianButton;
    @FXML Button modify;

    private Household household;
    private Property property;
    private final String ADDCUSTODIAN = "Add Custodian";
    private final String VIEWCUSTODIAN = "View Custodian";

    @FXML private void initialize()
    {
        household = householdFinder(Main.BootData.getRefNumber());
        referenceOutput.setText(Integer.toString(household.getReference()));
        nameOutput.setText(household.getName());

        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);

        if(household.getCustodian() == null){
            custodianButton.setText(ADDCUSTODIAN);
        }
        else {
            custodianButton.setText(VIEWCUSTODIAN);
        }

        if(Main.BootData.getAccessLevel() == 1){
            deleteHousehold.setVisible(false);
        }
        else{
            deleteHousehold.setVisible(true);
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
    public void deleteHouseholdButtonAction()
    {
        warningMessage.setText("This will delete the Household, its invoices and the associated Custodian. Do you wish to proceed?");
        warningMessage.setVisible(true);
        delete.setVisible(true);
        cancel.setVisible(true);
    }

    @FXML
    public void cancelButtonAction()
    {
        warningMessage.setText("Warning");
        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);
    }

    @FXML
    public void homeButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void deleteButtonAction()
    {
        warningMessage.setText("Household has been deleted, press Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        referenceOutput.setText("");
        nameOutput.setText("");
        custodianButton.setVisible(false);
        deleteHousehold.setVisible(false);
        modify.setVisible(false);

        int parentRef = household.getParentProperty();

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getRef() == parentRef)
                    {
                        this.property = property;
                    }
                }


            }
        }

        property.deleteHousehold();
    }

    @FXML
    public void modifyHouseholdButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddHousehold.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void custodianButtonAction(ActionEvent actionEvent) throws IOException{

        if(custodianButton.getText().equals(ADDCUSTODIAN)){
            Main.BootData.setParentRef(Main.BootData.getRefNumber());
            Main.BootData.setRefNumber(0);
            Main.BootData.setOwnerOrCustodian(false);
            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddCustodian.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }
        else if(custodianButton.getText().equals(VIEWCUSTODIAN)){
            Main.BootData.setParentRef(Main.BootData.getRefNumber());
            Main.BootData.setRefNumber(0);
            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewCustodian.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
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
