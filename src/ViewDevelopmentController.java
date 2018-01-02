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

public class ViewDevelopmentController {

    @FXML Label referenceOutput;
    @FXML Label nameOutput;
    @FXML Label locationOutput;
    @FXML Label managerOutput;
    @FXML Label warningMessage;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button deleteDev;
    @FXML Button addThoroughfare;
    @FXML Button modifyDev;

    @FXML private void initialize()
    {
        Development development = Main.BootData.getCouncil().getDevelopment(Main.BootData.getRefNumber());
        referenceOutput.setText(Integer.toString(development.getRef()));
        nameOutput.setText(development.getName());
        locationOutput.setText(development.getLocation());
        managerOutput.setText(Integer.toString(development.getManager()));
        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);

        if(Main.BootData.getAccessLevel() == 3){
            deleteDev.setVisible(true);
            modifyDev.setVisible(true);

        }
        else{
            deleteDev.setVisible(false);
            modifyDev.setVisible(false);
        }

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
    public void deleteDevButtonAction()
    {
        warningMessage.setText("This will delete the development and ALL associated thoroughfares, properties and households. Do you wish to proceed?");
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
        warningMessage.setText("Development has been deleted, press Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        referenceOutput.setText("");
        nameOutput.setText("");
        locationOutput.setText("");
        managerOutput.setText("");
        addThoroughfare.setVisible(false);
        deleteDev.setVisible(false);
        int ref = Main.BootData.getRefNumber();
        Main.BootData.getCouncil().deleteDevelopment(ref);
    }

    @FXML
    public void modifyDevButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddDevelopment.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void addThoroughfareButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setParentRef(Main.BootData.getRefNumber());
        Main.BootData.setRefNumber(0);
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddThoroughfare.fxml"));
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
