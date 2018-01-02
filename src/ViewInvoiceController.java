import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class ViewInvoiceController{

    /*Attributes*/

    @FXML Label referenceOutput;
    @FXML Label amountDueOutput;
    @FXML Label amountPaidOutput;
    @FXML Label paymentStatusOutput;
    @FXML Label warningMessage;
    @FXML Label householdRefOutput;
    @FXML Label invoiceDateOutput;
    @FXML Label paidDateOutput;
    @FXML Label enterPaymentLabel;
    @FXML Label printMessage;

    @FXML TextField paymentInput;

    @FXML Button delete;
    @FXML Button cancel;
    @FXML Button deleteInvoice;
    @FXML Button modify;
    @FXML Button print;
    @FXML Button takePayment;
    @FXML Button pay;

    private Invoice invoice;
    private Household household;

    private final String PAID = "PAID";
    private final String UNPAID = "UNPAID";

    @FXML private void initialize()
    {
        invoice = invoiceFinder(Main.BootData.getRefNumber());
        referenceOutput.setText(Integer.toString(invoice.getRef()));
        DecimalFormat df = new DecimalFormat("##.##");
        amountDueOutput.setText("£" + df.format(invoice.getAmountDue()));
        amountPaidOutput.setText("£" + df.format(invoice.getAmountPaid()));
        householdRefOutput.setText(Integer.toString(invoice.getHouseholdRef()));
        statusHandler();
        invoiceDateOutput.setText(dateHandler(true));
        paidDateOutput.setText(dateHandler(false));
        warningMessage.setVisible(false);
        delete.setVisible(false);
        cancel.setVisible(false);
        pay.setVisible(false);
        paymentInput.setVisible(false);
        enterPaymentLabel.setVisible(false);
        takePayment.setVisible(true);
        printMessage.setText("print");
        printMessage.setVisible(false);
        paymentInput.setText("");

        if(invoice.getPaidStatus()){
            takePayment.setVisible(false);
        }

        if(Main.BootData.getAccessLevel() == 3){
            deleteInvoice.setVisible(true);
            modify.setVisible(true);
        }
        else{
            deleteInvoice.setVisible(false);
            modify.setVisible(false);
        }

    }

    private String dateHandler(Boolean unpaid){

        if(unpaid){
            Calendar cal = invoice.getInvoiceDate();
            cal.add(Calendar.DATE, 0);
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String formatted = format1.format(cal.getTime());
            return formatted;
        }else {
            if(!invoice.getPaidStatus()){
                return UNPAID;
            }else {
                Calendar cal = invoice.getPaidDate();
                cal.add(Calendar.DATE, 0);
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                String formatted = format1.format(cal.getTime());
                return formatted;
            }
        }
    }

    private void statusHandler(){

        if(invoice.getPaidStatus()== false){
            paymentStatusOutput.setTextFill(Color.web("#FF0000"));
            paymentStatusOutput.setText(UNPAID);
        }else{
            paymentStatusOutput.setTextFill(Color.web("#008000"));
            paymentStatusOutput.setText(PAID);
        }
    }

    private Invoice invoiceFinder(int ref){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    for(Invoice invoice : property.getHousehold().getInvoices()){
                        if(invoice.getRef() == ref){
                            this.household = property.getHousehold();
                            return invoice;
                        }
                    }
                }
            }
        }
        return null;
    }

    @FXML
    public void printButtonAction(ActionEvent actionEvent) throws IOException{

        printMessage.setText(".....bleep......bloop.....printing complete");
        printMessage.setVisible(true);

    }

    @FXML
    public void payButtonAction(ActionEvent actionEvent) throws IOException{


        invoice.makePayment(Double.parseDouble(paymentInput.getText()));
        initialize();

    }

    @FXML
    public void deleteButtonAction()
    {
        warningMessage.setText("Delete completed, click Home to continue.");
        delete.setVisible(false);
        cancel.setVisible(false);
        referenceOutput.setText("");
        amountDueOutput.setText("");
        amountPaidOutput.setText("");
        paymentStatusOutput.setText("");
        householdRefOutput.setText("");
        invoiceDateOutput.setText("");
        paidDateOutput.setText("");

        deleteInvoice.setVisible(false);
        print.setVisible(false);
        takePayment.setVisible(false);
        modify.setVisible(false);

        household.deleteInvoice(invoice.getRef());
    }

    @FXML
    public void cancelButtonAction()
    {

        initialize();
    }

    @FXML
    public void deleteInvoiceButtonAction()
    {
        warningMessage.setText("This will delete the invoice and ALL associated payments. Do you wish to proceed?");
        warningMessage.setVisible(true);
        delete.setVisible(true);
        cancel.setVisible(true);
    }

    @FXML
    public void takePaymentButtonAction(ActionEvent actionEvent) throws IOException{

        printMessage.setVisible(false);
        cancel.setVisible(true);
        pay.setVisible(true);
        paymentInput.setVisible(true);
        enterPaymentLabel.setVisible(true);
        takePayment.setVisible(false);
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
    public void modifyInvoiceButtonAction(ActionEvent actionEvent) throws IOException
    {
        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddInvoice.fxml"));
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
