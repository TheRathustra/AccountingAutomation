import java.util.ArrayList;

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

        String answer = "месяц: " + month + " Самый прибыльный товар: " + maxProfitName +
                " " + maxProfit + System.lineSeparator() + "самая большая трата: " + maxExpenseName + " " + maxExpense;
        System.out.println(answer);

    }

    public static void showReports(ArrayList<MonthReport> monthReports) {
        for (MonthReport monthReport : monthReports) {
            for (int i = 0; i < monthReport.monthRecords.size(); i++) {
                MonthRecord monthRecord = monthReport.monthRecords.get(i);
                System.out.println(monthRecord);
            }
            monthReport.printReport();
        }
    }

}
