import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BlockOfFlats extends Property{

    /*Attributes*/

    private ArrayList<Property> flats;

    /*Constructors*/

    public BlockOfFlats(String name, int propertyNo, String type, Calendar completionDate, int parentThoroughfare, Boolean block)
    {
        super(name, propertyNo, type, completionDate, parentThoroughfare, block);
        this.flats = new ArrayList<>();
        this.setRef();

    }

    public BlockOfFlats(int propertyNo, String type, Calendar completionDate, int parentThoroughfare, Boolean block)
    {
        super(propertyNo, type, completionDate, parentThoroughfare, block);
        this.setRef();
    }

    /*Getter methods*/

    public Property getProperty(int reference)
    {
        for(Property property : flats)
        {
            if(reference == property.getRef())
            {
                return property;
            }
        }
        return null;
    }

    public Property addFlat(int propertyNo, String type, Calendar completionDate){

        Property flat = new Property(propertyNo, type, completionDate, this.parentThoroughfare, false);
        this.flats.add(flat);
        return flat;
    }


}
