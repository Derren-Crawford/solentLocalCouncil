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

public class ViewCustodianController {

    /*Attributes*/
    @FXML Label firstNameOutput;
    @FXML Label lastNameOutput;
    @FXML Label telNoOutput;
    @FXML Label emailOutput;
    @FXML Label warningMessage;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button modify;

    private Custodian custodian;



    @FXML private void initialize()
    {
        custodian = custodianFinder(Main.BootData.getParentRef());
        firstNameOutput.setText(custodian.getFirstName());
        lastNameOutput.setText(custodian.getLastName());
        telNoOutput.setText(Integer.toString(custodian.getTelNo()));
        emailOutput.setText(custodian.getEmail());


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
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void modifyCustodianButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setRefNumber(99);
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddCustodian.fxml"));
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
