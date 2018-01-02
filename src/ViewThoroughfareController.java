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

public class ViewThoroughfareController {

    /*Attributes*/

    @FXML Label referenceOutput;
    @FXML Label nameOutput;
    @FXML Label typeOutput;
    @FXML Label parentOutput;
    @FXML Label warningMessage;


    @FXML Button addProperty;@FXML Button delete;
    @FXML Button cancel;
    @FXML Button deleteThoroughfare;
    @FXML Button modifyThoroughfare;

    private Thoroughfare thoroughfare;

    @FXML private void initialize()
    {
        thoroughfare = thoroughfareFinder(Main.BootData.getRefNumber());
        referenceOutput.setText(Integer.toString(thoroughfare.getRef()));
        nameOutput.setText(thoroughfare.getName());
        typeOutput.setText(thoroughfare.getType());
        parentOutput.setText(Integer.toString(thoroughfare.getParentDevelopment()));
        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);

        if(Main.BootData.getAccessLevel() == 1){
            deleteThoroughfare.setVisible(false);
        }
        else{
            deleteThoroughfare.setVisible(true);
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
    public void deleteThoroughfareButtonAction()
    {
        warningMessage.setText("This will delete the thoroughfare and ALL associated properties and households. Do you wish to proceed?");
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
    public void deleteButtonAction()
    {
        warningMessage.setText("Thoroughfare has been deleted, press Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        referenceOutput.setText("");
        nameOutput.setText("");
        typeOutput.setText("");
        parentOutput.setText("");
        addProperty.setVisible(false);
        deleteThoroughfare.setVisible(false);
        modifyThoroughfare.setVisible(false);

        int parentRef = thoroughfare.getParentDevelopment();
        Development development = Main.BootData.getCouncil().getDevelopment(parentRef);
        development.deleteThoroughfare(thoroughfare.getRef());
    }

    @FXML
    public void modifyThoroughfareButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddThoroughfare.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void addPropertyButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setParentRef(Main.BootData.getRefNumber());
        Main.BootData.setRefNumber(0);
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddProperty.fxml"));
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
