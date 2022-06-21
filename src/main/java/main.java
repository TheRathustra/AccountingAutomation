import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Хранит все прочитанные отчеты
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        ArrayList<YearlyReport> yearlyReports   = new ArrayList<>();
        boolean monthReportSaved = false;
        boolean yearReportSaved  = false;

        int userInput = -1;
        while (userInput != 0) {
            printMenu();
            userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReports = MonthlyReport.readReports();
                monthReportSaved = true;
            } else if (userInput == 2) {
                yearlyReports = YearlyReport.readReports();
                yearReportSaved = true;
            } else if (userInput == 3){
                if (!monthReportSaved || !yearReportSaved) {
                    System.out.println("Прежде чем сверить отчеты, их необходимо прочитать");
                    continue;
                }

            } else if (userInput == 4) {
                MonthlyReport.showReports(monthlyReports);
            } else if (userInput == 5) {
                YearlyReport.showReports(yearlyReports);
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Такой команды нет");
            }

        }

    }

    static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - выйти");
    }
}
