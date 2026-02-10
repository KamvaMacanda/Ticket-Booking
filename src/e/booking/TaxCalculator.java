public class TaxCalculater implements PricingDiscount {
    private static final double TAX_RATE = 0.15; 
    @Override
    public double calculateDiscountedPrice(double BasePrice) {
        return BasePrice * TAX_RATE; 

    @Override
    public String getDescription() {
        return "Tax (15%)"; 
    }
}
