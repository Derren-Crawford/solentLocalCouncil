public class Custodian extends Person{

    /*Attributes*/

    private int telNo;
    private String email;

    /*Constructors*/

    public Custodian(String firstName, String lastName, int telNo)
    {
        super(firstName, lastName);
        this.telNo = telNo;
    }

    public Custodian(String firstName, String lastName, int telNo, String email)
    {
        super(firstName, lastName);
        this.telNo = telNo;
        this.email = email;
    }

    /*Getter methods*/

    public int getTelNo()
    {
        return telNo;
    }

    public String getEmail()
    {
        return email;
    }

    /*Setter methods*/

    public void setTelNo(int telNo)
    {
        this.telNo = telNo;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
