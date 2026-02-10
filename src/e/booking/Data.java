/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.booking;

/**
 *
 * @author kamva
 */
public class Data {
       
    private final String Name ;  
    private final String Surname ; 
       private final String TrainType ; 
        private final double Price ; 
          private final String Weekday ; 
       private final String SeatType ;  
      
     
       
       
       //Master Constructor  
       public Data (String Name ,String Surname , String TrainType  , double Price , String SeatType ,String Weekday ){ 
           this.Name = Name ; 
           this.Surname= Surname;  
           this.TrainType= TrainType ; 
           this.Price=Price ; 
           this.Weekday =Weekday ; 
           this.SeatType=SeatType ; 
           
           
           
           
       } 
       
       //Getters  
       public String getName (){ 
           return Name  ;
       } 
                
       
       public String getSurname(){ 
           return Surname ; 
       } 
       
       public String getTrainType (){ 
           return TrainType ; 
       }
        
       public String getWeekday (){ 
           return Weekday ; 
           
       }
       
       public String getSeatType(){ 
           return SeatType ; 
       } 
       
       public double getPrice (){ 
           return Price ;
       }  
       
//       public String Seat(){ 
//           return Seat
//       }
       
       @Override 
       public String toString(){ 
           return Name ; 
       }

}
