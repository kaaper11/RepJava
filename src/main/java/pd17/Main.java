package pd17;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            SalesCsvReader csvReader = new SalesCsvReader();
            SalesAnalytics salesAnalytics = new SalesAnalytics(csvReader.read(Path.of("src\\pd17\\sales.csv")));
            System.out.println(salesAnalytics.getTotalRevenue());
            System.out.println(salesAnalytics.top3Products());
            List<CategorySummary> categorySummaries = salesAnalytics.getCategorySummary();

            SalesReportWriter salesReportWriter = new SalesReportWriter();
            salesReportWriter.saveToJson(categorySummaries, "src\\pd17\\sales3.json");
            salesReportWriter.saveToCsv("src\\pd17\\sales1.csv", categorySummaries);
        } catch (IOException e) {
            System.err.println("Brak pliku w podanej ścieżce!");
        }

    }
}
