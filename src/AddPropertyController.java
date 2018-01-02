import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AddPropertyController {

    /*Attributes*/

    @FXML private TextField nameBox;
    @FXML private TextField numberBox;
    @FXML private ChoiceBox typeChoice;
    @FXML private DatePicker datePicker;

    private Boolean modifyChecker;
    private String name;
    private String type;
    private int propertyNumber;
    private Calendar completionDate;
    private Thoroughfare parentThoroughfare;

    /*Initializer*/

    @FXML private void initialize()
    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;
        typeChoice.setItems(FXCollections.observableArrayList("Detached", "Semi-detached", "Bungalow", "Terrace", "Block"));

        if(ref > 0)
        {
            modifyChecker = true;
            Property property = propertyFinder(Main.BootData.getRefNumber());
            name = property.getName();
            type = property.getType();
            propertyNumber = property.getPropertyNo();
            completionDate = property.getCompletionDate();

            nameBox.setPromptText(name);
            typeChoice.setValue(type);
            numberBox.setPromptText(Integer.toString(propertyNumber));
            datePicker.setValue(dateHandler(property));
        }

    }

    private LocalDate dateHandler(Property property){

        Calendar cal = property.getCompletionDate();
        Date defaultDate = cal.getTime();
        LocalDate displayDate = defaultDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return displayDate;

    }

    private Property propertyFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    if(property.getRef() == ref){
                        return property;
                    }
                }
            }
        }
        return null;
    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {

        String choice = (String) typeChoice.getValue();
        Boolean flats;
        if(choice.equals("Block")){
            flats = true;
        }else{
            flats = false;
        }

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                if(thoroughfare.getRef() == Main.BootData.getParentRef())
                {
                    parentThoroughfare = thoroughfare;
                }
            }
        }

        if(!modifyChecker)
        {
            Property property = parentThoroughfare.addProperty(nameGetter(),propertyNumberGetter(),choice,localDateToCalendar(), flats);
            int ref = property.getRef();

            Main.BootData.setRefNumber(ref);
        }
        else
        {
            Property property = propertyFinder(Main.BootData.getRefNumber());

            property.setName(nameGetter());
            property.setType(choice);
            property.setPropertyNo(propertyNumberGetter());
            property.setCompletionDate(localDateToCalendar());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewProperty.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    private Calendar localDateToCalendar(){

        Calendar calendar = Calendar.getInstance();
        LocalDate inputDate = datePicker.getValue();
        Instant instant = Instant.from(inputDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        calendar.setTime(date);
        return calendar;
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

    private int propertyNumberGetter(){

        if(numberBox.getText().isEmpty())
        {
            return Integer.parseInt(numberBox.getPromptText());
        }
        else{
            return Integer.parseInt(numberBox.getText());
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
