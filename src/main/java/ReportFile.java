public class ReportFile {
    String type;
    int month;
    int year;
    String content;

    public ReportFile(String type, int year, int month, String content) {
        this.type = type;
        this.month = month;
        this.year = year;
        this.content = content;
    }
}
