public interface PricingDiscount {
    double calculateDiscountedPrice(double basePrice); 


    String getDescription();
}