import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddDevelopmentController {


    @FXML private TextField nameBox;
    @FXML private TextField locationBox;
    @FXML private TextField managerBox;
    private String name;
    private String location;
    private int manager;
    private Boolean modifyChecker;

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;

        if(ref > 0)
        {
            modifyChecker = true;
            Development development = Main.BootData.getCouncil().getDevelopment(ref);
            name = development.getName();
            location = development.getLocation();
            manager = development.getManager();

            nameBox.setPromptText(name);
            locationBox.setPromptText(location);
            managerBox.setPromptText(Integer.toString(manager));
        }



    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {
        Council council = Main.BootData.getCouncil();

        if(!modifyChecker)
        {
            Development development = council.addDevelopment(nameGetter(), managerGetter(), locationGetter());
            int ref = development.getRef();

            Main.BootData.setRefNumber(ref);
        }
        else
        {
            Development development = council.getDevelopment(Main.BootData.getRefNumber());

            development.setName(nameGetter());
            development.setManager(managerGetter());
            development.setLocation(locationGetter());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewDevelopment.fxml"));
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

    private int managerGetter()
    {
        if(managerBox.getText().isEmpty())
        {
            String managerString = managerBox.getPromptText();
            return Integer.parseInt(managerString);

        }
        else
        {
            String managerString = managerBox.getText();
            return Integer.parseInt(managerString);
        }
    }

    private String locationGetter()
    {
        if(locationBox.getText().isEmpty())
        {
            return locationBox.getPromptText();
        }
        else
        {
            return locationBox.getText();
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
