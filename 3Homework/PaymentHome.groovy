import groovy.transform.*;
@ToString
abstract class Payment {
    Date datePayment;
    int creditSum;
    int countIndicator; 
    int tariff;
    boolean paid;
    
    public boolean validate(){
        ((creditSum >= (countIndicator * tariff)) && (paid == true))? true : false;
    }    

}

class WaterPayment extends Payment {

    String toString(){
         'WaterPayment: date:' + datePayment + ' creditSum: ' + creditSum + ' , countIndicator:' + countIndicator + ' coube/m3, tariff: ' + tariff + ', Do you paid -' + paid ;
    }    
}

class PhonePayment extends Payment {
    
    String toString(){
         'PhonePayment: date:' + datePayment + ' creditSum: ' + creditSum + ' , countIndicator:' + countIndicator + ' Wt, tariff: ' + tariff + ', Do you paid -' + paid;    }    
    }

@ToString(includeSuperProperties = true)
class RubishPayment extends Payment {
}

class SeweragePayment extends Payment {

    String toString(){
         'SeweragePayment: date:' + datePayment + ' creditSum: ' + creditSum + ' , countIndicator:' + countIndicator + ' coube/m3, tariff: ' + tariff + ', Do you paid -' + paid;    }    
    }

def paymentArray = [] as ArrayList;

WaterPayment waterP = new WaterPayment(datePayment: (new GregorianCalendar(2015, Calendar.DECEMBER, 01)).getTime(), creditSum:60 , countIndicator:20, tariff:3, paid: true);
PhonePayment phoneP = new PhonePayment(datePayment: (new GregorianCalendar(2015, Calendar.SEPTEMBER, 01)).getTime(), creditSum:100 , countIndicator:25, tariff:3, paid: false);
RubishPayment rubishP = new RubishPayment(datePayment: (new GregorianCalendar(2015, Calendar.OCTOBER, 10)).getTime(), creditSum:75 , countIndicator:15, tariff:5, paid: true);
SeweragePayment sewerageP = new SeweragePayment(datePayment: (new GregorianCalendar(2015, Calendar.DECEMBER, 03)).getTime(), creditSum:80 , countIndicator:20, tariff:4, paid: true);

paymentArray.add(waterP);
paymentArray.add(phoneP );
paymentArray.add(rubishP);
paymentArray.add(sewerageP);

paymentArray.each{payment->
        if(payment.validate()){
            println payment;
        }
    }



