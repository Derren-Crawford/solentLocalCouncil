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

public class ViewEmployeeController {

    /*Attributes*/
    @FXML Label firstNameOutput;
    @FXML Label lastNameOutput;
    @FXML Label accessLevelOutput;
    @FXML Label developmentRefOutput;
    @FXML Label userNameOutput;
    @FXML Label employeeNumberOutput;
    @FXML Label warningMessage;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button modify;
    @FXML Button deleteEmployee;

    private String LEVELONE = "Basic System User";
    private String LEVELTWO = "Manager";
    private String LEVELTHREE = "Admin";

    private SystemUser employee;

    @FXML private void initialize()
    {
        employee = Main.BootData.getCouncil().getSystemUser(Main.BootData.getRefNumber());
        firstNameOutput.setText(employee.getFirstName());
        lastNameOutput.setText(employee.getLastName());
        accessLevelOutput.setText(accessLevelPicker(employee));
        developmentRefOutput.setText(Integer.toString(employee.getDevelopmentRef()));
        userNameOutput.setText(employee.getUserName());
        employeeNumberOutput.setText(Integer.toString(employee.getEmployeeNumber()));

        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);

        if(Main.BootData.getAccessLevel() == 2){
            deleteEmployee.setVisible(false);
            modify.setVisible(false);
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
    public void modifyEmployeeButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setRefNumber(employee.getEmployeeNumber());
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddEmployee.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();

    }

    @FXML
    public void deleteemployeeButtonAction()
    {
        warningMessage.setText("This will delete the employee. Do you wish to proceed?");
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
        warningMessage.setText("Employee has been deleted, press Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        firstNameOutput.setText("");
        lastNameOutput.setText("");
        accessLevelOutput.setText("");
        developmentRefOutput.setText("");
        userNameOutput.setText("");
        employeeNumberOutput.setText("");

        deleteEmployee.setVisible(false);
        modify.setVisible(false);

        Main.BootData.getCouncil().deleteSystemUser(Main.BootData.getRefNumber());
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
