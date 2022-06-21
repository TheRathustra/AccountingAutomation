import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyRecord> monthlyRecords;

    public MonthlyReport() {
        this.monthlyRecords = new ArrayList<>();
    }

    public static ArrayList<MonthlyReport> readReports() {

        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

        for (int j = 1; j <= 12; j++) {

            String content = AccountingUtils.readFileContentsOrNull("src/main/resources/m.20200" + j + ".csv");
            if (content == null) {
                continue;
            }
            MonthlyReport monthlyReport = new MonthlyReport();
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

    public static void showReports(ArrayList<MonthlyReport> monthlyReports) {
        for (MonthlyReport monthlyReport: monthlyReports) {
            for (int i = 0; i < monthlyReport.monthlyRecords.size(); i++) {
                MonthlyRecord monthlyRecord = monthlyReport.monthlyRecords.get(i);
                System.out.println(monthlyRecord);
            }
        }
    }

}
