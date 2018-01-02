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
import javafx.stage.Stage;

import java.io.IOException;


public class PaymentMenuController{

    /*Attributes*/

    @FXML private TextField refBox;
    @FXML private Label infoMessage;

    @FXML Button addInvoice;
    @FXML Button totalInvoices;
    @FXML Button totalPaid;
    @FXML Button devInvoices;
    @FXML Button devPaid;

    private final String INVOICEALL = "The total outstanding invoice amount across all Developments is: £";
    private final String PAIDALL = "The total amount paid across all Developments is: £";
    private final String INVOICEDEV = "The total outstanding invoice for the chosen Development is: £";
    private final String PAIDDEV = "The total amount paid for the chosen Development is: £";

    private Double total;

    /*Other Methods*/

    public void initialize() {
        this.total = 0.00;

        if(Main.BootData.getAccessLevel() == 1){
            addInvoice.setVisible(false);
            totalInvoices.setVisible(false);
            totalPaid.setVisible(false);
            devInvoices.setVisible(false);
            devPaid.setVisible(false);

        }
        else if(Main.BootData.getAccessLevel() == 2) {
            totalInvoices.setVisible(false);
            totalPaid.setVisible(false);
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
    public void viewInvoiceButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setRefNumber(Integer.parseInt(refBox.getText()));

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("ViewInvoice.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void addInvoiceButtonAction(ActionEvent actionEvent) throws IOException
    {
        Main.BootData.setParentRef(Integer.parseInt(refBox.getText()));

        Parent developmentMenuParent = FXMLLoader.load(getClass().getResource("AddInvoice.fxml"));
        Scene developmentMenuScene = new Scene(developmentMenuParent);
        Stage app_stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(developmentMenuScene);
        app_stage.show();
    }

    @FXML
    public void totalInvoicesButtonAction(){

        initialize();
        this.total += Main.BootData.getCouncil().getAllTotals(false);
        infoMessage.setText(INVOICEALL + total);
    }

    @FXML
    public void totalPaidButtonAction(){

        initialize();
        this.total += Main.BootData.getCouncil().getAllTotals(true);
        infoMessage.setText(PAIDALL + total);
    }

    @FXML
    public void devInvoiceButtonAction(){

        if(Main.BootData.getAccessLevel() == 3) {

            int refNo = Integer.parseInt(refBox.getText());
            initialize();

            this.total += Main.BootData.getCouncil().getDevTotals(refNo, false);
            infoMessage.setText(INVOICEDEV + total);
        }else{

            int refNo = Main.BootData.getCurrentUser().getDevelopmentRef();
            initialize();

            this.total += Main.BootData.getCouncil().getDevTotals(refNo, false);
            infoMessage.setText(INVOICEDEV + total);
        }
    }

    @FXML
    public void devPaidButtonAction(){

        if(Main.BootData.getAccessLevel() == 3) {
            int refNo = Integer.parseInt(refBox.getText());
            initialize();

            this.total += Main.BootData.getCouncil().getDevTotals(refNo, true);
            infoMessage.setText(PAIDDEV + total);
        }else{
            int refNo = Main.BootData.getCurrentUser().getDevelopmentRef();
            initialize();

            this.total += Main.BootData.getCouncil().getDevTotals(refNo, true);
            infoMessage.setText(PAIDDEV + total);
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
