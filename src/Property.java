import java.util.Calendar;
import java.util.Date;

public class Property {

    /*Attributes*/

    protected String name;
    protected String type;
    protected int reference, propertyNo, parentThoroughfare;
    protected Calendar completionDate;
    private Household household;
    protected Custodian owner;
    protected Boolean block;

    /*Constructors*/

    public Property(String name, int propertyNo, String type, Calendar completionDate, int parentThoroughfare, Boolean block)
    {
        this.name = name;
        this.type = type;
        this.propertyNo = propertyNo;
        this.parentThoroughfare = parentThoroughfare;
        this.completionDate = completionDate;
        this.block = block;
        this.setRef();
    }

    public Property(int propertyNo, String type, Calendar completionDate, int parentThoroughfare, Boolean block)
    {
        this.type = type;
        this.propertyNo = propertyNo;
        this.parentThoroughfare = parentThoroughfare;
        this.completionDate = completionDate;
        this.block = block;
        this.setRef();
    }

    /*Getter methods*/

    public Boolean getBlock() {
        return block;
    }

    public String getName()
    {
        return this.name;
    }

    public String getType()
    {
        return this.type;
    }

    public int getRef()
    {
        return this.reference;
    }

    public int getPropertyNo()
    {
        return this.propertyNo;
    }

    public int getParentThoroughfare()
    {
        return this.parentThoroughfare;
    }

    public Calendar getCompletionDate()
    {
        return this.completionDate;
    }

    public Household getHousehold()
    {
        return household;
    }

    public Custodian getOwner()
    {
        return owner;
    }

    /*Setter methods*/

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setPropertyNo(int propertyNo)
    {
        this.propertyNo = propertyNo;
    }

    public void setParentThoroughfare(int parentThoroughfare)
    {
        this.parentThoroughfare = parentThoroughfare;
    }

    public void setCompletionDate(Calendar completionDate) {
        this.completionDate = completionDate;
    }

    protected void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        reference = refInc.getNextRef();
    }

    /*Other methods*/

    public void addOwner(String firstName, String lastName, int telNo, String email)
    {
        if(email == null)
        {
            this.owner = new Custodian(firstName, lastName, telNo);
        }
        else
        {
            this.owner = new Custodian(firstName, lastName, telNo, email);
        }
    }

    public Household addHousehold(String name)
    {
        this.household = new Household(name, this.reference);
        return this.household;
    }

    public void deleteHousehold()
    {
        this.household = null;
    }
}
