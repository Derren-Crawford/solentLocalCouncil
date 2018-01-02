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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class AddFlatController {

        /*Attributes*/

        @FXML private TextField numberBox;
        @FXML private DatePicker datePicker;


        private String FLAT = "Flat";
        private Thoroughfare parentThoroughfare;


        @FXML
        public void saveButtonAction(ActionEvent actionEvent) throws IOException
        {

            String choice = FLAT;

            for(Development development : Main.BootData.getCouncil().getDevelopments()){

                for(Thoroughfare thoroughfare : development.getThoroughfares()){

                    if(thoroughfare.getRef() == Main.BootData.getParentRef())
                    {
                        parentThoroughfare = thoroughfare;
                    }
                }
            }



            BlockOfFlats parentBlock = propertyFinder(Main.BootData.getRefNumber());
            Property newFlat = parentBlock.addFlat(propertyNumberGetter(),choice,localDateToCalendar());
            int ref = newFlat.getRef();
            Main.BootData.setRefNumber(ref);

            Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewProperty.fxml"));
            Scene developmentMenuScene = new Scene(developmentMenuParent);
            Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(developmentMenuScene);
            app_stage.show();
        }

    private BlockOfFlats propertyFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                return (BlockOfFlats) thoroughfare.getProperty(ref);

            }
        }
        return null;
    }

        private Calendar localDateToCalendar(){

            Calendar calendar = Calendar.getInstance();
            LocalDate inputDate = datePicker.getValue();
            Instant instant = Instant.from(inputDate.atStartOfDay(ZoneId.systemDefault()));
            Date date = Date.from(instant);
            calendar.setTime(date);
            return calendar;
        }


        private int propertyNumberGetter(){

            return Integer.parseInt(numberBox.getText());

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
