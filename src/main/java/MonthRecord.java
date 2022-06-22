public class MonthRecord {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthRecord(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    @Override
    public String toString() {
        return "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne;
    }
}
