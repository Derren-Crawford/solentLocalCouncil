import java.text.DecimalFormat;
import java.util.Calendar;

public class Invoice {

    /*Attributes*/

    private int reference, householdRef;
    private Calendar invoiceDate, paidDate;
    private double amountDue, amountPaid;
    private Boolean paidStatus;

    /*Constructor*/

    public Invoice(double amountDue, Calendar invoiceDate, int householdRef)
    {
        this.amountDue = amountDue;
        this.invoiceDate = invoiceDate;
        this.householdRef = householdRef;
        this.amountPaid = 0.00;
        this.paidStatus = false;
        this.setRef();
    }

    /*Getter methods*/

    public int getReference()
    {
        return reference;
    }

    public int getHouseholdRef()
    {
        return householdRef;
    }

    public Calendar getInvoiceDate()
    {
        return invoiceDate;
    }

    public Calendar getPaidDate()
    {
        if(paidStatus == true)
        {
            return this.paidDate;
        }
        else
        {
            return null;
        }
    }

    public double getAmountDue()
    {

        return amountDue;
    }

    public Boolean getPaidStatus()
    {
        return paidStatus;
    }

    public double getAmountPaid()
    {
        return amountPaid;
    }

    public int getRef() {return this.reference;}

    /*Setter methods*/

    private void setRef()
    {
        ReferenceIncrementor refInc = new ReferenceIncrementor();
        reference = refInc.getNextRef();
    }

    public void setHouseholdRef(int householdRef)
    {
        this.householdRef = householdRef;
    }

    public void setInvoiceDate(Calendar invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public void setAmountDue(double amountDue)
    {
        this.amountDue = amountDue;
    }

    public void setPaidDate(Calendar paidDate)
    {
        this.paidDate = paidDate;
    }

    public void setPaidStatus(Boolean paidStatus)
    {
        this.paidStatus = paidStatus;
    }

    public void setAmountPaid(double amountPaid)
    {
        this.amountPaid = amountPaid;
    }

    /*Other methods*/

    public Boolean makePayment(double amountPaid)
    {
        if(amountPaid > amountDue){
            return false;
        }else {
            this.setAmountPaid(this.amountPaid += (amountPaid * 1.00));
            this.setAmountDue(this.amountDue -= (amountPaid * 1.00));

            if (Math.round(amountDue) == 0) {
                this.setAmountDue(0);
                this.setPaidStatus(true);
                Calendar today = Calendar.getInstance();
                this.setPaidDate(today);
                return true;
            } else {

                Calendar today = Calendar.getInstance();
                this.setPaidDate(today);
                return true;
            }
        }
    }


}
