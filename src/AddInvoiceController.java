import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

public class AddInvoiceController {

    /*Attributes*/
    @FXML private TextField amountDueBox;

    private Boolean modifyChecker;
    private Invoice invoice;
    private Household household;

    @FXML private void initialize()    {
        int ref = Main.BootData.getRefNumber();
        modifyChecker = false;

        if(ref > 0)
        {
            modifyChecker = true;
            invoice = invoiceFinder(ref);

            amountDueBox.setPromptText(Double.toString(invoice.getAmountDue()));

        }

    }

    @FXML
    public void saveButtonAction(ActionEvent actionEvent) throws IOException
    {


        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()) {

                    if (property.getHousehold().getReference() == Main.BootData.getParentRef()) {
                        this.household = property.getHousehold();
                    }
                }
            }
        }

        if(!modifyChecker)
        {
            Invoice invoice = household.addInvoice(amountDueGetter(), Calendar.getInstance());
            int ref = invoice.getReference();

            Main.BootData.setRefNumber(ref);
        }
        else
        {

            invoice.setAmountDue(amountDueGetter());

        }

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewInvoice.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    private Double amountDueGetter()
    {
        if(amountDueBox.getText().isEmpty())
        {
            return Double.parseDouble(amountDueBox.getPromptText()) * 1.00;
        }
        else
        {
            return Double.parseDouble(amountDueBox.getText()) * 1.00;
        }

    }

    private Invoice invoiceFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    for(Invoice invoice : property.getHousehold().getInvoices()){
                        if(invoice.getRef() == ref){
                            return invoice;
                        }
                    }
                }
            }
        }
        return null;
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
