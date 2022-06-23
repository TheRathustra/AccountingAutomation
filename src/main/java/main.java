import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        AccountingUtils utils = new AccountingUtils();

        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        while (userInput != 0) {
            printMenu();
            userInput = scanner.nextInt();
            if (userInput == 1) {
                MonthReport.readReports(utils);
            } else if (userInput == 2) {
                YearReport.readReports(utils);
            } else if (userInput == 3){
                if (!utils.monthReportsSaved || !utils.yearReportsSaved) {
                    System.out.println("Прежде чем сверить отчеты, их необходимо прочитать");
                    continue;
                }
                AccountingUtils.dataReconciliation(utils.monthReports, utils.yearReports);
            } else if (userInput == 4) {
                if (!utils.monthReportsSaved) {
                    System.out.println("Прежде чем выводить отчеты, их необходимо прочитать");
                    continue;
                }
                MonthReport.showReports(utils.monthReports);
            } else if (userInput == 5) {
                if (!utils.yearReportsSaved) {
                    System.out.println("Прежде чем выводить отчеты, их необходимо прочитать");
                    continue;
                }
                YearReport.showReports(utils.yearReports);
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
