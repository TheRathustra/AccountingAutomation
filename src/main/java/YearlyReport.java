import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearlyReport {
    int year;
    ArrayList<YearlyRecord> yearlyRecords;

    public YearlyReport() {
        year = 0;
        yearlyRecords = yearlyRecords = new ArrayList<>();
    }

    public static ArrayList<YearlyReport> readReports() {

        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();

        for (int j = 0; j <= 12; j++) {

            String content = AccountingUtils.readFileContentsOrNull("src/main/resources/y.202" + j + ".csv");
            if (content == null) {
                continue;
            }
            YearlyReport yearlyReport = new YearlyReport();
            yearlyReport.year = 2020 + j;
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

    public void printReport() {
        HashMap<Integer, Integer> yearSums = new HashMap<>();
        int profit = 0;
        int expense = 0;
        for (YearlyRecord yearlyRecord: yearlyRecords) {
            int amount = 0;
            if (yearSums.containsKey(yearlyRecord.month)) {
                amount = yearSums.get(yearlyRecord.month);
            }
            if (yearlyRecord.isExpense) {
                amount -= yearlyRecord.amount;
                expense += yearlyRecord.amount;
            } else {
                amount += yearlyRecord.amount;
                profit += yearlyRecord.amount;
            }
            yearSums.put(yearlyRecord.month, amount);
        }

        String answer = " " + year + " Средний расход за все месяцы в году: " +
                expense/12 + " Средний доход за все месяцы в году: " + profit/12 + System.lineSeparator();
        for (Map.Entry<Integer, Integer> entry : yearSums.entrySet()) {
            answer = answer + entry.getKey() + " " + entry.getValue() + System.lineSeparator();
        }

        System.out.println(answer);

    }

    public static void showReports(ArrayList<YearlyReport> yearlyReports) {
        for (YearlyReport yearlyReport: yearlyReports) {
            for (YearlyRecord yearlyRecord: yearlyReport.yearlyRecords) {
                System.out.println(yearlyRecord);
            }
            yearlyReport.printReport();
        }
    }
}
