public class YearRecord {
    int month;
    int amount;
    boolean isExpense;

    public YearRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @Override
    public String toString() {
        return "month=" + month +
                ", amount=" + amount +
                ", isExpense=" + isExpense;
    }
}
