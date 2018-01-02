import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DevelopmentMenuController implements Initializable
{
    /*Attributes*/

    @FXML AnchorPane developmentMenuAnchor;
    @FXML private TextField refBox;
    @FXML private Button addDevelopment;
    @FXML private Label errorMsg;

    private String INVALIDREF = "Please enter a valid reference number.";
    private String NOACCESS = "Please enter a reference number valid to your development.";
    private String THOROUGHFARE = "ViewThoroughfare.fxml";
    private String PROPERTY = "ViewProperty.fxml";
    private String HOUSEHOLD = "ViewHousehold.fxml";

    /*Other Methods*/

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if(Main.BootData.getAccessLevel() == 3){
            addDevelopment.setVisible(true);
        }
        else{
            addDevelopment.setVisible(false);
        }

        errorMsg.setVisible(false);

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
    public void addDevelopmentButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddDevelopment.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void viewDevelopmentButtonAction(ActionEvent actionEvent) throws IOException
    {
        try {

            if (Main.BootData.getAccessLevel() == 3) {
                Main.BootData.setRefNumber(Integer.parseInt(refBox.getText()));
            } else {
                Main.BootData.setRefNumber(Main.BootData.getCurrentUser().getDevelopmentRef());
            }


            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewDevelopment.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }catch (NumberFormatException e){
            errorMsg.setVisible(true);
            errorMsg.setText(INVALIDREF);
        }
    }

    @FXML
    public void viewThoroughfareButtonAction(ActionEvent actionEvent) throws IOException{
        try {

            int chosenRef = Integer.parseInt(refBox.getText());

            if(Main.BootData.getAccessLevel() != 3) {

                for (Development development : Main.BootData.getCouncil().getDevelopments()) {

                    for (Thoroughfare thoroughfare : development.getThoroughfares()) {

                        if (thoroughfare.getRef() == chosenRef) {

                            if (development.getRef() == Main.BootData.getCurrentUser().getDevelopmentRef()) {

                                this.loadNextScreen(THOROUGHFARE,chosenRef,actionEvent);

                            } else {
                                errorMsg.setVisible(true);
                                errorMsg.setText(NOACCESS);
                            }
                        }
                    }

                }
                errorMsg.setVisible(true);
                errorMsg.setText(INVALIDREF);
            }else{
                this.loadNextScreen(THOROUGHFARE,chosenRef,actionEvent);
            }



        }catch (NumberFormatException e){

            errorMsg.setVisible(true);
            errorMsg.setText(INVALIDREF);

        }


    }

    private void loadNextScreen(String nextScreen, int refNo, ActionEvent actionEvent) throws IOException{
        Main.BootData.setRefNumber(refNo);

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource(nextScreen));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void viewPropertyButtonAction(ActionEvent actionEvent) throws IOException
    {
        try {

            int chosenRef = Integer.parseInt(refBox.getText());

            if(Main.BootData.getAccessLevel() != 3) {

                for (Development development : Main.BootData.getCouncil().getDevelopments()) {

                    for (Thoroughfare thoroughfare : development.getThoroughfares()) {

                        for (Property property : thoroughfare.getProperties()){

                            if (property.getRef() == chosenRef) {

                                if (development.getRef() == Main.BootData.getCurrentUser().getDevelopmentRef()) {

                                    this.loadNextScreen(PROPERTY,chosenRef,actionEvent);

                                } else {
                                    errorMsg.setVisible(true);
                                    errorMsg.setText(NOACCESS);
                                }
                            }
                        }


                    }

                }
                errorMsg.setVisible(true);
                errorMsg.setText(INVALIDREF);
            }else{
                this.loadNextScreen(PROPERTY,chosenRef,actionEvent);
            }



        }catch (NumberFormatException e){

            errorMsg.setVisible(true);
            errorMsg.setText(INVALIDREF);

        }


    }


    @FXML
    public void viewHouseholdButtonAction(ActionEvent actionEvent) throws IOException
    {
        try {

            int chosenRef = Integer.parseInt(refBox.getText());

            if(Main.BootData.getAccessLevel() != 3) {

                for (Development development : Main.BootData.getCouncil().getDevelopments()) {

                    for (Thoroughfare thoroughfare : development.getThoroughfares()) {

                        for (Property property : thoroughfare.getProperties()){

                            if (property.getHousehold().getReference() == chosenRef) {

                                if (development.getRef() == Main.BootData.getCurrentUser().getDevelopmentRef()) {

                                    this.loadNextScreen(HOUSEHOLD,chosenRef,actionEvent);

                                } else {
                                    errorMsg.setVisible(true);
                                    errorMsg.setText(NOACCESS);
                                }
                            }
                        }


                    }

                }
                errorMsg.setVisible(true);
                errorMsg.setText(INVALIDREF);
            }else{
                this.loadNextScreen(HOUSEHOLD,chosenRef,actionEvent);
            }



        }catch (NumberFormatException e){

            errorMsg.setVisible(true);
            errorMsg.setText(INVALIDREF);

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
