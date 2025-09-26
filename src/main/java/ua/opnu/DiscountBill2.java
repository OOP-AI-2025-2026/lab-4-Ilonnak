import ua.opnu.java.inheritance.bill.Employee;
import ua.opnu.java.inheritance.bill.GroceryBill;
import ua.opnu.java.inheritance.bill.Item;

public class DiscountBill2 {

    private final boolean regularCustomer;
    private final GroceryBill bill;
    private int discountCount;
    private double discountAmount;
    private double fullTotal;
    private double discountedTotal;

    public DiscountBill2(Employee clerk, boolean regularCustomer) {
        this.bill = new GroceryBill(clerk);
        this.regularCustomer = regularCustomer;
        this.discountCount = 0;
        this.discountAmount = 0.0;
        this.fullTotal = 0.0;
        this.discountedTotal = 0.0;
    }

    public Employee getClerk() { return bill.getClerk(); }

    public void add(Item i) {
        bill.add(i);
        fullTotal += i.getPrice();

        if (regularCustomer) {
            double d = i.getDiscount();
            if (d > 0.0) {
                discountCount++;
                discountAmount += d;
            }
            discountedTotal += (i.getPrice() - d);
        } else {
            discountedTotal += i.getPrice();
        }
    }

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

