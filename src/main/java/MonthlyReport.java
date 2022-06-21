import java.util.ArrayList;

public class MonthlyReport {
    int month;
    ArrayList<MonthlyRecord> monthlyRecords;

    public MonthlyReport() {
        month = 0;
        monthlyRecords = new ArrayList<>();
    }

    public static ArrayList<MonthlyReport> readReports() {

        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

        for (int j = 1; j <= 12; j++) {

            String content = AccountingUtils.readFileContentsOrNull("src/main/resources/m.20200" + j + ".csv");
            if (content == null) {
                continue;
            }
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.month = j;
            monthlyReports.add(monthlyReport);
            String[] lines = content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] values = line.split(";");

                String itemName = values[0];
                boolean isExpense = Boolean.valueOf(values[1]);
                int quantity = Integer.valueOf(values[2]);
                int sumOfOne = Integer.valueOf(values[3]);

                MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
                monthlyReport.monthlyRecords.add(monthlyRecord);
            }
        }
        return monthlyReports;
    }

    public void printReport() {
        int maxProfit = 0;
        String maxProfitName = "";
        int maxExpense = 0;
        String maxExpenseName = "";
        for (MonthlyRecord monthlyRecord: monthlyRecords) {
            int amount = monthlyRecord.quantity * monthlyRecord.sumOfOne;
            if (monthlyRecord.isExpense) {
                if (amount > maxExpense) {
                    maxExpense = amount;
                    maxExpenseName = monthlyRecord.itemName;
                }
            } else {
                if (amount > maxProfit) {
                    maxProfit = amount;
                    maxProfitName = monthlyRecord.itemName;
                }
            }
        }

        String answer = "месяц: " + month + " Самый прибыльный товар: " + maxProfitName +
                " " + maxProfit + System.lineSeparator() + "самая большая трата: " + maxExpenseName + " " + maxExpense;
        System.out.println(answer);

    }

    public static void showReports(ArrayList<MonthlyReport> monthlyReports) {
        for (MonthlyReport monthlyReport: monthlyReports) {
            for (int i = 0; i < monthlyReport.monthlyRecords.size(); i++) {
                MonthlyRecord monthlyRecord = monthlyReport.monthlyRecords.get(i);
                System.out.println(monthlyRecord);
            }
            monthlyReport.printReport();
        }
    }

}
