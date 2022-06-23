import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearReport {
    int year;
    ArrayList<YearRecord> yearRecords;

    public YearReport() {
        year = 0;
        yearRecords = yearRecords = new ArrayList<>();
    }

    public static void readReports(AccountingUtils utils) {

        utils.yearReportsSaved = true;
        utils.yearReports.clear();
        ArrayList<YearReport> yearReports = utils.yearReports;
        ArrayList<ReportFile> yearReportFiles = utils.readFiles("y");

        for (ReportFile reportFile : yearReportFiles) {
            if (reportFile.content == null) {
                continue;
            }
            YearReport yearReport = new YearReport();
            yearReport.year = reportFile.year;
            yearReports.add(yearReport);
            String[] lines = reportFile.content.split("\n");
            for (int i = 1; i < lines.length; i++) {
                String line = lines[i];
                String[] values = line.split(";");

                int month = Integer.valueOf(values[0]);;
                int amount = Integer.valueOf(values[1]);
                boolean isExpense = Boolean.valueOf(values[2]);

                YearRecord yearRecord = new YearRecord(month, amount, isExpense);
                yearReport.yearRecords.add(yearRecord);
            }
        }
    }

    public void printReport() {
        HashMap<Integer, Integer> yearSums = new HashMap<>();
        int profit = 0;
        int expense = 0;
        for (YearRecord yearRecord : yearRecords) {
            int amount = 0;
            if (yearSums.containsKey(yearRecord.month)) {
                amount = yearSums.get(yearRecord.month);
            }
            if (yearRecord.isExpense) {
                amount -= yearRecord.amount;
                expense += yearRecord.amount;
            } else {
                amount += yearRecord.amount;
                profit += yearRecord.amount;
            }
            yearSums.put(yearRecord.month, amount);
        }

        String answer = "год: " + year + " Средний расход за все месяцы в году: " +
                expense/12 + " Средний доход за все месяцы в году: " + profit/12 + System.lineSeparator();
        for (Map.Entry<Integer, Integer> entry : yearSums.entrySet()) {
            answer = answer + "месяц " + entry.getKey() + " прибыль " + entry.getValue() + System.lineSeparator();
        }

        System.out.println(answer);

    }

    public static void showReports(ArrayList<YearReport> yearReports) {
        for (YearReport yearReport : yearReports) {
            /*
            for (YearRecord yearRecord : yearReport.yearRecords) {
                System.out.println(yearRecord);
            }
             */
            yearReport.printReport();
        }
    }

    public HashMap<Integer, HashMap<Boolean, Integer>> totalSumPerMonth() {

        HashMap<Integer, HashMap<Boolean, Integer>> yearSums = new HashMap<>();

        for (YearRecord yearRecord : yearRecords) {
            HashMap<Boolean, Integer> monthSums = new HashMap<>();
            if (yearSums.containsKey(yearRecord.month)) {
                monthSums = yearSums.get(yearRecord.month);
            }
            int amount = 0;
            if (monthSums.containsKey(yearRecord.isExpense)) {
                amount = monthSums.get(yearRecord.isExpense);
            }
            amount += yearRecord.amount;
            monthSums.put(yearRecord.isExpense, amount);
            yearSums.put(yearRecord.month, monthSums);
        }

        return yearSums;

    }

}
