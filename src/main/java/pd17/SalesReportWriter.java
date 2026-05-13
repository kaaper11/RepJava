package pd17;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalesReportWriter {
    public void saveToCsv(String path, List<CategorySummary> categorySummaries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("category,mean_price,revenue\n");

        for (CategorySummary categorySummary : categorySummaries) {
            writer.write(oneLine(categorySummary));
        }
    }

    public void saveToJson(List<CategorySummary> categorySummaries, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), categorySummaries);
    }

    private String oneLine(CategorySummary summary) {
        return summary.category().toString().toLowerCase() + "," + summary.mean() + "," + summary.totalRevenue() + "\n";
    }
}
