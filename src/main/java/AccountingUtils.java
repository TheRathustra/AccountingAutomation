import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountingUtils {

    ArrayList<MonthReport> monthReports;
    ArrayList<YearReport> yearReports;
    boolean monthReportsSaved;
    boolean yearReportsSaved;

    public AccountingUtils() {
        monthReports = new ArrayList<>();
        yearReports = new ArrayList<>();
        monthReportsSaved = false;
        yearReportsSaved = false;
    }

    public ArrayList<ReportFile>  readFiles(String typeOfReport) {

            //годовой или месячный / год / строка данных файла
            ArrayList<ReportFile> reportFiles = new ArrayList<>();

            File dir = new File("src/main/resources");
            for (File file: dir.listFiles()) {

                Path pathFile = file.toPath();
                String path = pathFile.toString();
                if (!path.contains(".csv")){
                    continue;
                }
                String[] fullName = path.split("/");
                String[] name = fullName[fullName.length-1].split("\\.");

                if (!name[0].equals(typeOfReport)) {
                    continue;
                }

                int year = 0;
                String type = null;
                int month = 0;
                if (name[0].equals("y")) {
                    year = Integer.valueOf(name[1]);
                    type = "year";
                } else if (name[0].equals("m")) {
                    year = Integer.valueOf(name[1].substring(0, 4));
                    type = "month";
                    month = Integer.valueOf(name[1].substring(5, 6));
                } else {
                    continue;
                }

                String content = readFileContentsOrNull(pathFile);
                ReportFile reportFile = new ReportFile(type, year, month, content);
                reportFiles.add(reportFile);
            }
            return reportFiles;
    }

    public static String readFileContentsOrNull(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            //System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    public static void dataReconciliation(ArrayList<MonthReport> monthReports, ArrayList<YearReport> yearReports) {

        for (YearReport yearReport : yearReports) {
            HashMap<Integer, HashMap<Boolean, Integer>> yearTotal = new HashMap<Integer, HashMap<Boolean, Integer>>();
            for (YearRecord yearRecord : yearReport.yearRecords) {
                int amount = yearRecord.amount;
                if (!yearTotal.containsKey(yearRecord.month)) {
                    yearTotal.put(yearRecord.month, new HashMap<>());
                } else {
                    if (!yearTotal.get(yearRecord.month).containsKey(yearRecord.isExpense)) {
                        yearTotal.get(yearRecord.month).put(yearRecord.isExpense, 0);
                    }
                }

                yearTotal.get(yearRecord.month).put(yearRecord.isExpense,
                        yearTotal.get(yearRecord.month).get(yearRecord.isExpense) + amount);
            }
        }

    }


}
