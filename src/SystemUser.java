public class SystemUser extends Person{

    /*Attributes*/
    private int employeeNumber;
    private int accessLevel;
    private String userName;
    private String password;
    private int developmentRef;

    public SystemUser(String firstName, String lastName, int accessLevel, String userName, String password, int developmentRef)
    {
        super(firstName,lastName);
        this.accessLevel = accessLevel;
        this.userName = userName;
        this.password = password;
        this.developmentRef = developmentRef;
        setRef();

    }

    /*Getter Methods*/

    public int getEmployeeNumber()
    {
        return employeeNumber;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getDevelopmentRef() {
        return developmentRef;
    }

    /*Setter Methods*/

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDevelopmentRef(int developmentRef) {
        this.developmentRef = developmentRef;
    }

    private void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        this.employeeNumber = refInc.getNextRef();
    }
}
