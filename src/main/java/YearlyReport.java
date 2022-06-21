import java.util.ArrayList;

public class YearlyReport {

    ArrayList<YearlyRecord> yearlyRecords;

    public YearlyReport() {
        this.yearlyRecords = yearlyRecords = new ArrayList<>();
    }

    public static ArrayList<YearlyReport> readReports() {

        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();

        for (int j = 0; j <= 12; j++) {

            String content = AccountingUtils.readFileContentsOrNull("src/main/resources/y.202" + j + ".csv");
            if (content == null) {
                continue;
            }
            YearlyReport yearlyReport = new YearlyReport();
            yearlyReports.add(yearlyReport);
            String[] lines = content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] values = line.split(";");

                int month = Integer.valueOf(values[0]);;
                int amount = Integer.valueOf(values[1]);
                boolean isExpense = Boolean.valueOf(values[2]);

                YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
                yearlyReport.yearlyRecords.add(yearlyRecord);
            }
        }
        return yearlyReports;
    }

    public static void showReports(ArrayList<YearlyReport> yearlyReports) {
        for (YearlyReport yearlyReport: yearlyReports) {
            for (YearlyRecord yearlyRecord: yearlyReport.yearlyRecords) {
                System.out.println(yearlyRecord);
            }
        }
    }
}
