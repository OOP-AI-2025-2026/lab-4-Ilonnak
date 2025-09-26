import ua.opnu.java.inheritance.account.BankingAccount;
import ua.opnu.java.inheritance.account.Startup;
import ua.opnu.java.inheritance.account.Debit;
import ua.opnu.java.inheritance.account.Credit;

public class MinMaxAccount extends BankingAccount {

    private int min;
    private int max;

    public MinMaxAccount(Startup s) {
        super(s);
        int b = getBalance();
        this.min = b;
        this.max = b;
    }

    @Override
    public void debit(Debit d) {
        super.debit(d);
        updateMinMax();
    }

    @Override
    public void credit(Credit c) {
        super.credit(c);
        updateMinMax();
    }

    private void updateMinMax() {
        int b = getBalance();
        if (b < min) min = b;
        if (b > max) max = b;
    }

    public int getMin() { return min; }

    public int getMax() { return max; }
}
