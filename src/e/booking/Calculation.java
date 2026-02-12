/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.booking;
import java.util.List;
import java.util.ArrayList;
import e.booking.discounts.WeekendDiscount;
import e.booking.discounts.WeekdayDiscount;


/**
 * 
 *
 * @author kamva
 */

//class to claculate all booking calculations 
 public class Calculation{  


private Data bookingData;
    private Seat seat;
    private String weekday;
    private double basePrice;
    private double PricingDiscount;
    private double subtotal;
    private double taxAmount;
    private double totalPrice;
    private String bookingReference;
    private List<String> priceBreakdown;


public Calculation (String name ,String surname ,String TrainType ,String SeatType ,String Weekday,Double basePrice){ 

    this.seat=SeatObjects.createSeat(SeatType) ;
    this.basePrice=seat.getBasePrice(); 
    this.bookingData=new Data( name ,surname ,TrainType ,basePrice ,Weekday, SeatType) ;  
    this.bookingReference=generateBookingReference();
    this.priceBreakdown=new ArrayList<>();
    CalculatePrice();  


 } 

private void CalculatePrice(){ 
priceBreakdown.add(String.format("Base Price: R%.2f", basePrice)); 

PricingDiscount = 0.0 ; 
String Weekday = bookingData.getWeekday(); 

//Weekend discounts checking  
if (isWeekend(Weekday)){ 
    WeekendDiscount Discount = new WeekendDiscount(); 
    double amount =Discount.CalculateDiscount(basePrice);
    PricingDiscount += amount; 
    priceBreakdown.add(String.format("Weekend Discount: -R%.2f", amount)); 
}

//weekday discounts checking 
if (isWeekday(Weekday)){ 
    WeekdayDiscount Discount = new WeekdayDiscount(); 
    double weekdayAmount =Discount.CalculateDiscount(basePrice);
    PricingDiscount += weekdayAmount; 
    priceBreakdown.add(String.format("Weekday Discount: -R%.2f", weekdayAmount)); 
} 

//Calculating subtotal + tax 
Subtotal = basePrice + PricingDiscount ; 

PricingDiscount tax = new TaxCalculater (); 
taxAmount = tax.CalculateDiscount(subtotal) ; 
priceBreakdown.add(String.format("Subtotal : R%.2f", subtotal)); 
priceBreakdown.add(String.format("Tax Amount: R%.2f", taxAmount)); 

totalPrice = subtotal + taxAmount ;  

private String generateBookingReference(){ 
   LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        int random = (int)(Math.random() * 1000);
        return "BK" + timestamp + String.format("%03d", random);  
} 


}
