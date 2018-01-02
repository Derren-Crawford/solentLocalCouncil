public class Totaller {

    private Double total;
    private Boolean invoiceOrPayment;

    public Totaller(Boolean invoiceOrPayment){
        this.total = 0.00;
        this.invoiceOrPayment = invoiceOrPayment;
        allDevTotals();
    }

    public Totaller(int refNo, Boolean invoiceOrPayment){
        this.total = 0.00;
        this.invoiceOrPayment = invoiceOrPayment;
        devTotals(refNo);
    }

    public Double getTotal() {
        return total;
    }

    private void devTotals(int refNo){

    Development development = Main.BootData.getCouncil().getDevelopment(refNo);

        for(Thoroughfare thoroughfare : development.getThoroughfares()){

            for(Property property : thoroughfare.getProperties()){

                for(Invoice invoice : property.getHousehold().getInvoices()){

                    if(invoiceOrPayment){

                        if(invoice.getPaidStatus()==invoiceOrPayment) {

                            this.total += invoice.getAmountPaid();
                        }
                    }else{

                        if(invoice.getPaidStatus()==invoiceOrPayment) {

                            this.total += invoice.getAmountDue();
                        }


                    }
                }
            }
        }

}

    private void allDevTotals(){

        for(Development development : Main.BootData.getCouncil().getDevelopments()){

            for(Thoroughfare thoroughfare : development.getThoroughfares()){

                for(Property property : thoroughfare.getProperties()){

                    for(Invoice invoice : property.getHousehold().getInvoices()){

                        if(invoiceOrPayment){

                            if(invoice.getPaidStatus()==invoiceOrPayment) {

                                this.total += invoice.getAmountPaid();
                            }
                        }else{

                            if(invoice.getPaidStatus()==invoiceOrPayment) {

                                this.total += invoice.getAmountDue();
                            }


                        }
                    }
                }
            }
        }
    }


}
