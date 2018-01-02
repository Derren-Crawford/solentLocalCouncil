import java.util.ArrayList;

public class Council {

    /*Attributes*/

    private String name;
    private ArrayList<Development> developments;
    private ArrayList<SystemUser> systemUsers;

    /*Constructors*/

    public Council()
    {
        this.name = "Solent Local Council";
        this.developments = new ArrayList<>();
        this.systemUsers = new ArrayList<>();
    }

    /*Getter methods*/

    public String getName()
    {
        return name;
    }


    public Development getDevelopment(int developmentRef)

    {
        for(Development development : developments)
        {
            if(developmentRef == development.getRef())
            {
                return development;
            }
        }
        return null;
    }

    public SystemUser getSystemUser(int employeeNumber)
    {
        for(SystemUser systemUser : systemUsers)
        {
            if(employeeNumber == systemUser.getEmployeeNumber())
            {
                return systemUser;
            }

            /*error handler here*/

        }
        return null;
    }

    /*Other methods*/

    public Development addDevelopment(String name, int managerEmpNo, String location)
    {
        Development newDev = new Development(name, managerEmpNo, location);
        this.developments.add(newDev);
        return newDev;
    }

    public SystemUser addSystemUser(String firstName, String lastName, int accessLevel, String userName, String password, int developmentRef)
    {
        SystemUser systemUser = new SystemUser(firstName, lastName, accessLevel, userName, password, developmentRef);
        this.systemUsers.add(systemUser);
        return systemUser;
    }

    public void deleteSystemUser(int employeeNumber)
    {
        for(SystemUser systemUser : systemUsers)
        {
            if(employeeNumber == systemUser.getEmployeeNumber())
            {
                systemUsers.remove(systemUser);
                break;
            }

            /*error handler here*/

        }
    }

    public void deleteDevelopment(int developmentRef)
    {
        for(Development development: developments)
        {
            if(developmentRef == development.getRef())
            {
                developments.remove(development);
                break;
            }
        }
    }

    public ArrayList<Development> getDevelopments() {
        return developments;
    }

    public Double getAllTotals(Boolean invoiceOrPayment){

        Totaller totaller = new Totaller(invoiceOrPayment);
        return totaller.getTotal();
    }

    public ArrayList<SystemUser> getSystemUsers() {
        return systemUsers;
    }

    public Double getDevTotals(int refNo, Boolean invoiceOrPayment){
        Totaller totaller = new Totaller(refNo, invoiceOrPayment);
        return totaller.getTotal();
    }

}
