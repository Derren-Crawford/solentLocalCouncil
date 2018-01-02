import java.util.ArrayList;

public class Development{

    /*Attributes*/

    private String name, location;
    private int reference, manager;
    private ArrayList<Thoroughfare> thoroughfares;

    /*Constructors*/

    public Development(String name, int manager, String location)
    {
        this.name = name;
        this.manager = manager;
        this.location = location;
        this.thoroughfares = new ArrayList<Thoroughfare>();
        this.setRef();
    }

    /*Getter methods*/

    public String getName()
    {
        return this.name;
    }

    public String getLocation()
    {
        return this.location;
    }

    public int getRef()
    {
        return this.reference;
    }

    public int getManager()
    {
        return this.manager;
    }

    public Thoroughfare getThoroughfare(int thoroughfareRef)
    {
        for(Thoroughfare thoroughfare : thoroughfares)
        {
            if(thoroughfareRef == thoroughfare.getRef())
            {
                return thoroughfare;
            }
        }
        return null;
    }

    public ArrayList<Thoroughfare> getThoroughfares() {
        return thoroughfares;
    }

    /*Setter methods*/

    public void setName(String name)
    {
        this.name = name;
    }

    public void setManager(int manager)
    {
        this.manager = manager;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    private void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        reference = refInc.getNextRef();
    }

    /*Other methods*/

    public Thoroughfare addThoroughfare(String thoroughfareName, String thoroughfareType)
    {
        Thoroughfare newThoroughfare = new Thoroughfare(thoroughfareName, thoroughfareType, this.reference);
        this.thoroughfares.add(newThoroughfare);
        return newThoroughfare;
    }

    public void deleteThoroughfare(int reference)
    {
        for(Thoroughfare thoroughfare : thoroughfares)
        {
            if(reference == thoroughfare.getRef())
            {
                thoroughfares.remove(thoroughfare);
                break;
            }

            /*error handler here*/

        }
    }
}
