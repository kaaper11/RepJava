package pd17;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class SalesReportWriter {
    public void safe(String path, List<CategorySummary> categorySummaries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("category,mean_price,revenue\n");

        for (CategorySummary categorySummary : categorySummaries) {
            writer.write(oneLine(categorySummary));
        }
    }

    private String oneLine(CategorySummary summary) {
        return summary.category().toString().toLowerCase() + "," + summary.mean() + "," + summary.totalRevenue() + "\n";
    }
}
