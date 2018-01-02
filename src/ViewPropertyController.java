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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewPropertyController {

    /*Attributes*/

    @FXML Label referenceOutput;
    @FXML Label nameOutput;
    @FXML Label typeOutput;
    @FXML Label parentOutput;
    @FXML Label warningMessage;
    @FXML Label numberOutput;
    @FXML Label dateOutput;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button deleteProperty;
    @FXML Button householdButton;
    @FXML Button ownerButton;
    @FXML Button modify;
    @FXML Button addFlat;

    private Property property;
    private Thoroughfare thoroughfare;
    private final String ADDHOUSEHOLD = "Add Household";
    private final String VIEWHOUSEHOLD = "View Household";
    private final String ADDOWNER = "Add Owner";
    private final String VIEWOWNER = "View Owner";

    @FXML private void initialize()
    {
        property = propertyFinder(Main.BootData.getRefNumber());
        referenceOutput.setText(Integer.toString(property.getRef()));
        nameOutput.setText(property.getName());
        typeOutput.setText(property.getType());
        parentOutput.setText(Integer.toString(property.getParentThoroughfare()));
        numberOutput.setText(Integer.toString(property.getPropertyNo()));
        dateOutput.setText(dateHandler());
        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);

        if(property.getHousehold() == null){
            householdButton.setText(ADDHOUSEHOLD);
        }
        else {
            householdButton.setText(VIEWHOUSEHOLD);
        }

        if(property.getOwner() == null) {
            ownerButton.setText(ADDOWNER);
        }
        else {
            ownerButton.setText(VIEWOWNER);
        }

        if(Main.BootData.getAccessLevel() == 1){
            deleteProperty.setVisible(false);
        }
        else{
            deleteProperty.setVisible(true);
        }

        if(property.getBlock()){
            addFlat.setVisible(true);
        }else{
            addFlat.setVisible(false);
        }

    }



    private Property propertyFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property1 : thoroughfare.getProperties()){


                    if(property1.getRef() == ref){
                        return property1;
                    }

                    if(property1.getClass() == BlockOfFlats.class){

                        BlockOfFlats blockOfFlats = (BlockOfFlats) property1;
                        return blockOfFlats.getProperty(ref);
                    }
                }
            }
        }
        return null;
    }

    private String dateHandler(){

        Calendar cal = property.getCompletionDate();
        cal.add(Calendar.DATE, 0);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String formatted = format1.format(cal.getTime());
        return formatted;

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
    public void deletePropertyButtonAction()
    {
        warningMessage.setText("This will delete the property and ALL associated households. Do you wish to proceed?");
        warningMessage.setVisible(true);
        delete.setVisible(true);
        cancel.setVisible(true);
    }

    @FXML
    public void cancelButtonAction()
    {
        initialize();
    }

    @FXML
    public void deleteButtonAction()
    {
        warningMessage.setText("Property has been deleted, press Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        referenceOutput.setText("");
        nameOutput.setText("");
        typeOutput.setText("");
        parentOutput.setText("");
        householdButton.setVisible(false);
        ownerButton.setVisible(false);
        deleteProperty.setVisible(false);
        modify.setVisible(false);

        int parentRef = property.getParentThoroughfare();

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare1 : development.getThoroughfares()){

                if(thoroughfare1.getRef() == parentRef)
                {
                    thoroughfare = thoroughfare1;
                }
            }
        }

        thoroughfare.deleteProperty(property.getRef());
    }

    @FXML
    public void modifyPropertyButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddProperty.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void addFlatButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddFlat.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void householdButtonAction(ActionEvent actionEvent) throws IOException{

        if(householdButton.getText().equals(ADDHOUSEHOLD)){
            Main.BootData.setParentRef(Main.BootData.getRefNumber());
            Main.BootData.setRefNumber(0);
            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddHousehold.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }
        else if(householdButton.getText().equals(VIEWHOUSEHOLD)){
            Main.BootData.setParentRef(Main.BootData.getRefNumber());
            Main.BootData.setRefNumber(0);
            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewHousehold.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }
    }
    @FXML
    public void custodianButtonAction(ActionEvent actionEvent) throws IOException{

        if(ownerButton.getText().equals(ADDOWNER)){
            Main.BootData.setParentRef(Main.BootData.getRefNumber());
            Main.BootData.setRefNumber(0);
            Main.BootData.setOwnerOrCustodian(true);
            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddCustodian.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }
        else if(ownerButton.getText().equals(VIEWOWNER)){
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
