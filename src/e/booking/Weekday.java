public class WeekdayDiscount implements Pricing{ 
    private static final doublr DicountRtae=0.10 ; 

    // methd to calcculate the dicount price 

    @Override
    public double calculatePrice(double BasePrice) {
        return -(BasePrice *DiscountRtae) ; 

        @Override
        public String getDescription() {
            return "Weekday Discount (10% off)"; 
            
        }
         
    } 
}