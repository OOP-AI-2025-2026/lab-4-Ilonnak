import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;

public class DiscountBill extends GroceryBill {

    private final boolean regularCustomer;

    private int discountCount;
    private double discountAmount;
    private double fullTotal;
    private double discountedTotal;

    public DiscountBill(Employee clerk, boolean regularCustomer) {
        super(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.fullTotal = 0.0;
        this.discountedTotal = 0.0;
    }

    @Override
    public void add(Item i) {
        super.add(i);

        fullTotal += i.getPrice();

        if (regularCustomer) {
            double d = i.getDiscount();
            if (d > 0.0) {
                discountCount++;
                discountAmount += d;
            }
            discountedTotal += (i.getPrice() - d);
        } else {
            discountedTotal += i.getPrice();        }
    }

    @Override
    public double getTotal() {
        double total = regularCustomer ? discountedTotal : fullTotal;
        return Math.round(total * 100.0) / 100.0;
    }

    public int getDiscountCount() {
        return regularCustomer ? discountCount : 0;
    }

    public double getDiscountAmount() {
        return regularCustomer ? discountAmount : 0.0;
    }

    public double getDiscountPercent() {
        if (!regularCustomer || fullTotal == 0.0) return 0.0;
        double p = (discountAmount * 100.0) / fullTotal;
        return Math.round(p * 1e13) / 1e13;
    }

}
