/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.booking;

/**
 *
 * @author kamva
 */ 

// abstract class for all seat types 

public abstract class Seat {
  protected String type;
    
    public Seat(String type) {
        this.type = type;
    }
    
    
    public abstract double getBasePrice();
    public String getType() {
        return type;
    }
  
    public String getDescription() {
        return type + " Class Seat - Base Price: R" + String.format("%.2f", getBasePrice());
    }



// Economy seat 

class EconomySeat extends Seat {
    public EconomySeat() {
        super("Economy");
    }
    
    @Override
    public double getBasePrice() {
        return 50.00;
    }
}

// Business seat 

class BusinessSeat extends Seat {
    public BusinessSeat() {
        super("Business");
    }
    
    @Override
    public double getBasePrice() {
        return 120.00;
    }
}

// Private seat 
class PrivateSeat extends Seat {
    public PrivateSeat() {
        super("Private");
    }
    
    @Override
    public double getBasePrice() {
        return 250.00;
    }
}

// Presidential seat 
 
class PresidentialSeat extends Seat {
    public PresidentialSeat() {
        super("Presidential");
    }
    
    @Override
    public double getBasePrice() {
        return 500.00;
    }
} 

                        //logic for pricing interface  // 

interface PricingDiscount{ 
    double calculateDiscount(double basePrice); 
    String getDescription(); 
}

//weekend discount calculation 

class WeekendDiscount implements PricingDiscount{ 
    private static final double Discount_rate =0.20 ; 
    
    @Override 
    public double calculateDiscount(double basePrice){ 
        return basePrice *Discount_rate ; 
    }
    
    @Override 
    public String getDescription(){ 
        return "Weekend Discount(20%)" ;
    }
          
    //Midweek discount  calculator 
     class MidweekDiscount implements PricingDiscount{ 
         private static final double Discount_rate = 0.10 ; 
         
         @Override
         public double calculatedDiscount(double basePrice){
             return -(basePrice * Discount_rate); 
         }
         
         @Override 
         public String getDescription(){ 
             return "Midweek Discount (10%)" ;
         }
         
     } 
     
     
     //Tax calculator 
     
    
    
    
}


 
    
 
} 
