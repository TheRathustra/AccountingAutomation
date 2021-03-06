import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport {
    int year;
    int month;
    ArrayList<MonthRecord> monthRecords;

    public MonthReport() {
        year = 0;
        month = 0;
        monthRecords = new ArrayList<>();
    }

    public static void readReports(AccountingUtils utils) {

        utils.monthReportsSaved = true;
        utils.monthReports.clear();
        ArrayList<MonthReport> monthReports = utils.monthReports;

        ArrayList<ReportFile> monthReportFiles = utils.readFiles("m");

        for (ReportFile reportFile : monthReportFiles) {
            if (reportFile.content == null) {
                continue;
            }
            MonthReport monthReport = new MonthReport();
            monthReport.year = reportFile.year;
            monthReport.month = reportFile.month;
            monthReports.add(monthReport);
            String[] lines = reportFile.content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] values = line.split(";");

                String itemName = values[0];
                boolean isExpense = Boolean.valueOf(values[1]);
                int quantity = Integer.valueOf(values[2]);
                int sumOfOne = Integer.valueOf(values[3]);

                MonthRecord monthRecord = new MonthRecord(itemName, isExpense, quantity, sumOfOne);
                monthReport.monthRecords.add(monthRecord);
            }
        }
    }

    public void printReport() {
        int maxProfit = 0;
        String maxProfitName = "";
        int maxExpense = 0;
        String maxExpenseName = "";
        for (MonthRecord monthRecord : monthRecords) {
            int amount = monthRecord.quantity * monthRecord.sumOfOne;
            if (monthRecord.isExpense) {
                if (amount > maxExpense) {
                    maxExpense = amount;
                    maxExpenseName = monthRecord.itemName;
                }
            } else {
                if (amount > maxProfit) {
                    maxProfit = amount;
                    maxProfitName = monthRecord.itemName;
                }
            }
        }

        String answer = "??????????: " + month + " ?????????? ???????????????????? ??????????: " + maxProfitName +
                " " + maxProfit + System.lineSeparator() + "?????????? ?????????????? ??????????: " + maxExpenseName + " " + maxExpense;
        System.out.println(answer);

    }

    public HashMap<Boolean, Integer> getTotalSums() {
        HashMap<Boolean, Integer> totalSums = new HashMap<>();

        int profit = 0;
        int expense = 0;

        for (MonthRecord monthRecord : monthRecords) {
            int amount = monthRecord.quantity * monthRecord.sumOfOne;
            if (monthRecord.isExpense) {
                expense += amount;
            } else {
                profit += amount;
            }
        }

        totalSums.put(true, expense);
        totalSums.put(false, profit);

        return totalSums;
    }

    public static void showReports(ArrayList<MonthReport> monthReports) {
        for (MonthReport monthReport : monthReports) {
            /*
            for (int i = 0; i < monthReport.monthRecords.size(); i++) {
                MonthRecord monthRecord = monthReport.monthRecords.get(i);
                System.out.println(monthRecord);
            }
             */
            monthReport.printReport();
        }
    }

}
