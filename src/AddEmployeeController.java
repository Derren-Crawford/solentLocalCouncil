import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEmployeeController {

    /*Attributes*/
    @FXML private TextField firstNameBox;
    @FXML private TextField lastNameBox;
    @FXML private Spinner<String> accessLevelSpinner;
    @FXML private TextField developmentRefBox;
    @FXML private TextField userNameBox;
    @FXML private TextField passwordBox;

    @FXML private Label passwordLabel;
    @FXML private Label userNameView;
    @FXML private Label accessLevelMan;
    @FXML private Label devRefMan;

    private Boolean modifyChecker;
    private String firstName;
    private String lastName;
    private int developmentRef;

    private String LEVELONE = "Basic System User";
    private String LEVELTWO = "Manager";
    private String LEVELTHREE = "Admin";

    private SystemUser employee;

    /*Initializer*/

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;
        ObservableList<String> accessLevels = FXCollections.observableArrayList(LEVELONE,LEVELTWO,LEVELTHREE);
        SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(accessLevels);
        accessLevelSpinner.setValueFactory(valueFactory);


        if(ref > 0)
        {
            employee = Main.BootData.getCouncil().getSystemUser(Main.BootData.getRefNumber());

            modifyChecker = true;
            firstName = employee.getFirstName();
            lastName = employee.getLastName();
            developmentRef = employee.getDevelopmentRef();

            firstNameBox.setPromptText(firstName);
            lastNameBox.setPromptText(lastName);
            developmentRefBox.setPromptText(Integer.toString(developmentRef));
            accessLevelSpinner.getValueFactory().setValue(accessLevelPicker(employee));

            userNameBox.setVisible(false);
            userNameView.setText(employee.getUserName());
            userNameView.setVisible(true);
            passwordBox.setVisible(false);
            passwordLabel.setVisible(false);
        }
        else{
            userNameView.setVisible(false);
        }

        if(Main.BootData.getAccessLevel() == 3){
            accessLevelMan.setVisible(false);
            devRefMan.setVisible(false);
        }else{
            accessLevelSpinner.setVisible(false);
            developmentRefBox.setVisible(false);

            accessLevelMan.setText(LEVELONE);
            devRefMan.setText(Integer.toString(Main.BootData.getCurrentUser().getDevelopmentRef()));
        }

    }

    private String accessLevelPicker(SystemUser employee){

        if(employee.getAccessLevel() == 1){
            return LEVELONE;
        }
        else if(employee.getAccessLevel() == 2){
            return LEVELTWO;
        }
        else
        {
            return LEVELTHREE;
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
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {

        if(!modifyChecker)
        {
            SystemUser systemUser = Main.BootData.getCouncil().addSystemUser(firstNameGetter(),lastNameGetter(),accessLevelGetter(),userNameBox.getText(),passwordBox.getText(),developmentRefGetter());
            Main.BootData.setRefNumber(systemUser.getEmployeeNumber());

        }
        else
        {

            employee.setFirstName(firstNameGetter());
            employee.setLastName(lastNameGetter());
            employee.setAccessLevel(accessLevelGetter());
            employee.setDevelopmentRef(developmentRefGetter());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewEmployee.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
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

    private int developmentRefGetter()
    {
        if(Main.BootData.getAccessLevel() == 3) {

            if (developmentRefBox.getText().isEmpty()) {
                return Integer.parseInt(developmentRefBox.getPromptText());
            } else {
                return Integer.parseInt(developmentRefBox.getText());
            }
        }else{
            return Main.BootData.getCurrentUser().getDevelopmentRef();
        }

    }

    private int accessLevelGetter(){

        if(Main.BootData.getAccessLevel() == 3) {

            if (accessLevelSpinner.getValue().equals(LEVELONE)) {
                return 1;
            } else if (accessLevelSpinner.getValue().equals(LEVELTWO)) {
                return 2;
            } else {
                return 3;
            }
        }else{
            return 1;
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
