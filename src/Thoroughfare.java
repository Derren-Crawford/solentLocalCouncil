import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Thoroughfare {

    /*Attributes*/

    private String name, type;
    private int reference, parentDevelopment;
    private ArrayList<Property> properties;

    /*Constructors*/

    public Thoroughfare(String name, String type, int parentDevelopment)
    {
        this.name = name;
        this.type = type;
        this.parentDevelopment = parentDevelopment;
        this.properties = new ArrayList<Property>();
        this.setRef();
    }

    /*Getter methods*/

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

    public int getParentDevelopment()
    {
        return this.parentDevelopment;
    }

    public Property getProperty(int reference)
    {
        for(Property property : properties)
        {
            if(reference == property.getRef())
            {
                return property;
            }
        }
        return null;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    /*Setters*/

    public void setName(String name)
    {
        this.name = name;
    }

    public void setParentDevelopment(int parentDevelopment)
    {
        this.parentDevelopment = parentDevelopment;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    private void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        reference = refInc.getNextRef();
    }

    /*Other methods*/

    public Property addProperty(String name, int propertyNo, String type, Calendar completionDate, Boolean flats)
    {
        if(name == null)
        {
            if(flats)
            {
                BlockOfFlats blockOfFlats = new BlockOfFlats(propertyNo, type, completionDate, this.reference, flats);
                this.properties.add(blockOfFlats);
                return blockOfFlats;
            }
            else
            {
                Property property = new Property(propertyNo, type, completionDate, this.reference, flats);
                this.properties.add(property);
                return property;
            }
        }
        else
        {
            if(flats)
            {
                BlockOfFlats blockOfFlats =new BlockOfFlats(name, propertyNo, type, completionDate, this.reference, flats);
                this.properties.add(blockOfFlats);
                return blockOfFlats;
            }
            else
            {
                Property property = new Property(name, propertyNo, type, completionDate, this.reference, flats);
                this.properties.add(property);
                return property;
            }
        }
    }

    public void deleteProperty(int reference)
    {
        for(Property property : properties)
        {
            if(reference == property.getRef())
            {
                properties.remove(property);
                break;
            }

            /*error handler here*/

        }
    }
}
