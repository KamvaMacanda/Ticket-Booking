/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.booking;

/**
 *
 * @author kamva
 */ 

public class Seat {
    //variables & Construtors for seat type and base price 
 private String Type ;
 private double BasePrice  ; 

 public Seat (String Type , double BasePrice ){ 
     this.Type = Type ; 
     this.BasePrice = BasePrice ; 
 }

 public String getType (){ 
     return Type ; 
 }

 public void setType(String Type) {
        this.Type = Type ;
    }

    public double getBasePrice() {
            return BasePrice ;
            
        }
        public void setBasePrice(double BasePrice) {
           if (BasePrice > 0) {
            this.BasePrice = BasePrice ;
           } else {
               System.out.println("Base price must be positive.");
           }
        }

// Description of seat types and their base prices 
 public String getSeatInfo (){ 
     return "Seat Type : " + Type + " | Base Price : R " + BasePrice ;
 }





}