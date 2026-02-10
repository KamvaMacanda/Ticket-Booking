public class WeekendDiscount implements PricingDiscount {
    private static final double DiscountRate = 0.20; // 20% discount

    @Override
    public double calculateDiscountedPrice(double basePrice) {
        return basePrice * DiscountRate;
    }

    @Override
    public String getDescription() {
        return "Weekend Discount: 20% off";
    }
} 