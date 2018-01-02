import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Household {

    /*Attributes*/

    private String name;
    private int reference;
    private Custodian custodian;
    private ArrayList<Invoice> invoices;
    private int parentProperty;

    /*Constructors*/

    public Household(String name, int parentProperty)
    {
        this.name = name;
        this.invoices = new ArrayList<Invoice>();
        this.setRef();
        this.parentProperty = parentProperty;
    }

    /*Getter methods*/

    public String getName()
    {
        return name;
    }

    public int getReference()
    {
        return reference;
    }

    public Custodian getCustodian()
    {
        return custodian;
    }

    public Invoice getInvoice(int reference)
    {
        for(Invoice invoice : invoices)
        {
            if(invoice.getRef() == reference)
            {
                return invoice;
            }
        }
        return null;
    }

    public ArrayList<Invoice> getInvoices()
    {

        return this.invoices;
    }

    public int getParentProperty() {
        return parentProperty;
    }

    /*Setter methods*/

    public void setName(String name)
    {
        this.name = name;
    }

    public void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        reference = refInc.getNextRef();
    }

    /*Other invoices*/

    public Custodian addCustodian(String firstName, String lastName, int telNo, String email)
    {
        if(email == null)
        {
            this.custodian = new Custodian(firstName, lastName, telNo);
            return this.custodian;
        }
        else
        {
            this.custodian = new Custodian(firstName, lastName, telNo, email);
            return this.custodian;
        }
    }

    public Invoice addInvoice(double amountDue, Calendar invoiceDate)
    {
        Invoice invoice = new Invoice(amountDue, invoiceDate, this.reference);
        this.invoices.add(invoice);
        return invoice;
    }

    public void deleteInvoice(int invoiceNumber){

        for(Invoice invoice : invoices){
            if(invoice.getRef() == invoiceNumber){
                invoices.remove(invoice);
                break;
            }
        }
    }
}
