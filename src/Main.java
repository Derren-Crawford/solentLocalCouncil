import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Calendar;

public class Main extends Application{

    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
        BootData bootData = new BootData();
    }

    public static void main(String[] args)
    {
        launch(args);
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        refInc.resetFile(0);

    }

    public static class BootData
    {
        private static Council council;
        private static int refNumber;
        private static int parentRef;
        private static Boolean ownerOrCustodian;
        private static int accessLevel;
        private static SystemUser currentUser;

        public BootData()
        {
            refNumber = 0;
            BootData.council = new Council();
            Development development = BootData.council.addDevelopment("Stormwind", 123456, "Elwynn Forest");               //1
            Thoroughfare thoroughfare = development.addThoroughfare("Trade District", "Street");                        //2
            Property property = thoroughfare.addProperty("Auctionhouse", 1, "Detached", Calendar.getInstance(), false);                 //3
            Household household = property.addHousehold("Doomhammer");                                                                            //4
            double amountDue = 123.45;
            Invoice invoice = household.addInvoice(amountDue, Calendar.getInstance());                                                                  //5
            council.addSystemUser("Anduin", "Wrynn", 3, "admin", "password", 0);       //6
            council.addSystemUser("Jaina", "Proudmoore", 1, "bsu", "password", 1);     //7
            council.addSystemUser("Bolvar", "Fordragon", 2, "manager", "password", 1);  //8
            thoroughfare.addProperty("Bank", 2, "Block", Calendar.getInstance(), true);                                     //9

        }

        public static int getAccessLevel(){
            return accessLevel;
        }

        public static void setUser(SystemUser employee){
            currentUser = employee;
        }

        public static SystemUser getCurrentUser() {
            return currentUser;
        }

        public static void setAccessLevel(int accessNo){
            BootData.accessLevel = accessNo;
        }

        public static void setRefNumber(int refNo) {
            BootData.refNumber = refNo;
        }

        public static Council getCouncil() {
            return council;
        }


        public static int getRefNumber() {
            return refNumber;
        }

        public static void setParentRef(int parentRef) {
            BootData.parentRef = parentRef;
        }

        public static int getParentRef() {
            return parentRef;
        }

        public static Boolean getOwnerOrCustodian() {
            return ownerOrCustodian;
        }

        public static void setOwnerOrCustodian(Boolean ownerOrCustodian) {
            BootData.ownerOrCustodian = ownerOrCustodian;
        }
    }


}
